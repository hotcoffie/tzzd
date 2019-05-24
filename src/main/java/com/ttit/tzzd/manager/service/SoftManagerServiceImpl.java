package com.ttit.tzzd.manager.service;

import com.ttit.tzzd.manager.dao.SoftInfoDao;
import com.ttit.tzzd.manager.dao.SoftManagerDao;
import com.ttit.tzzd.manager.entity.SoftInfo;
import com.ttit.tzzd.manager.entity.SoftManager;
import com.ttit.tzzd.manager.vo.SoftManagerVo;
import com.ttit.tzzd.sys.common.Constant;
import com.ttit.tzzd.sys.enums.SysLogTypeEnum;
import com.ttit.tzzd.sys.exceptions.BusinessException;
import com.ttit.tzzd.sys.exceptions.NotExistException;
import com.ttit.tzzd.sys.exceptions.NotNullException;
import com.ttit.tzzd.sys.service.DictionaryService;
import com.ttit.tzzd.sys.service.SysLogService;
import com.ttit.tzzd.sys.utils.UuidUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2415:24
 */
@Service
public class SoftManagerServiceImpl implements SoftManagerService {
    @Resource
    private SoftManagerDao softManagerDao;
    @Resource
    private SoftInfoDao softInfoDao;
    @Resource
    private SysLogService sysLogService;
    @Resource
    private DictionaryService dictionaryService;

    @Override
    public List<SoftManagerVo> list() {
        List<SoftManagerVo> managers = softManagerDao.list();
        List<SoftInfo> softs = softInfoDao.searchPage(null, null);
        //通过遍历，将当前所有软件信息附加到对应软件管理信息中，便于用户下拉切换
        managers.forEach(manager -> {
            List<SoftInfo> children = manager.getSofts();
            if (children == null) {
                children = new ArrayList<>();
            }
            String managerSoftType = manager.getSoftType();
            for (SoftInfo soft : softs) {
                String softType = soft.getSoftType();
                if (StringUtils.isBlank(softType)) {
                    continue;
                }
                if (softType.equals(managerSoftType)) {
                    children.add(soft);
                }
            }
            manager.setSofts(children);
        });
        return managers;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SoftManager add(SoftManager softManager, String userId) {
        //1.数据校验
        if (softManager == null) {
            throw new NotNullException();
        }
        String softType = softManager.getSoftType();
        if (StringUtils.isBlank(softType)) {
            throw new NotNullException();
        }
        List<SoftManager> listTest = softManagerDao.findBySoftType(softType);
        if (listTest != null && !listTest.isEmpty()) {
            throw new BusinessException("不能添加重复的软件分类！");
        }

        //2.持久化
        String id = UuidUtils.generate();
        softManager.setId(id);
        softManager.setCreator(userId);
        softManagerDao.add(softManager);

        //3.记日志
        String content = "新增软件分类：" + softManager.toString();
        Date now = new Date();
        sysLogService.addLog(SysLogTypeEnum.modify.getCode(), content, userId, now);

        return softManagerDao.findById(id);
    }

    @Override
    public SoftManager del(String id, String userId) {
        //1.数据校验
        if (StringUtils.isBlank(id)) {
            throw new NotNullException();
        }
        SoftManager softManager = softManagerDao.findById(id);
        if (softManager == null || Constant.IS_DEL.equals(softManager.getIsDel())) {
            throw new NotExistException("软件分类ID:" + id);
        }

        softManagerDao.del(id);
        String content = "软件分类：" + softManager.toString();
        Date now = new Date();
        //记录系统日志
        sysLogService.addLog(SysLogTypeEnum.del.getCode(), content, userId, now);

        return softManager;
    }

}
