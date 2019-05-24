package com.ttit.tzzd.manager.service;

import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.manager.dao.SoftInfoDao;
import com.ttit.tzzd.manager.entity.SoftInfo;
import com.ttit.tzzd.manager.vo.SoftInfoVo;
import com.ttit.tzzd.sys.common.Constant;
import com.ttit.tzzd.sys.entity.Attachment;
import com.ttit.tzzd.sys.enums.SysLogTypeEnum;
import com.ttit.tzzd.sys.exceptions.NotExistException;
import com.ttit.tzzd.sys.exceptions.NotNullException;
import com.ttit.tzzd.sys.service.AttachmentService;
import com.ttit.tzzd.sys.service.BaseService;
import com.ttit.tzzd.sys.service.SysLogService;
import com.ttit.tzzd.sys.utils.UuidUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Description:软件信息
 *
 * @author 小谢
 * Date: 2019/5/2317:17
 */
@Service
public class SoftInfoServiceImpl extends BaseService implements SoftInfoService {
    @Resource
    private SoftInfoDao softInfoDao;
    @Resource
    private SysLogService sysLogService;
    @Resource
    private AttachmentService attachmentService;

    /**
     * 服务器对外暴露的路径
     */
    @Value("${business.url}")
    private String applicationUrl;

    @Value("${server.port}")
    private String serverPort;

    @Override
    public PageInfo searchPage(String softType, String keyword, Integer pageNum, Integer pageSize, String orderBy) {
        startPage(pageNum, pageSize, orderBy);
        List<com.ttit.tzzd.manager.vo.SoftInfoVo> list = softInfoDao.searchPage(softType, keyword);
        list.forEach(vo -> vo.setUrl(applicationUrl + ":" + serverPort + Constant.DOWNLOAD_PATH + vo.getAttaId()));
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SoftInfoVo add(String softType, MultipartFile webFile, String userId) {
        //1.调用系统附件服务
        Attachment attachment = attachmentService.upload(webFile, userId);

        //2.持久化软件信息
        SoftInfo softInfo = new SoftInfo(UuidUtils.generate(), softType, attachment.getId(), userId);
        softInfoDao.add(softInfo);

        SoftInfoVo vo = softInfoDao.findById(softInfo.getId());
        vo.setUrl(applicationUrl + ":" + serverPort + Constant.DOWNLOAD_PATH + vo.getAttaId());
        return vo;
    }

    @Override
    @Transactional
    public SoftInfo del(String id, String userId) {
        //1.数据校验
        if (StringUtils.isBlank(id)) {
            throw new NotNullException();
        }
        SoftInfo softInfo = softInfoDao.findById(id);
        if (softInfo == null || Constant.IS_DEL.equals(softInfo.getIsDel())) {
            throw new NotExistException("文件ID:" + id);
        }

        softInfoDao.del(id);
        String content = "删除软件：" + softInfoDao.toString();
        Date now = new Date();
        //记录系统日志
        sysLogService.addLog(SysLogTypeEnum.del.getCode(), content, userId, now);

        return softInfo;
    }

}
