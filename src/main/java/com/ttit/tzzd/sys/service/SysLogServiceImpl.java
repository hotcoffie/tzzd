package com.ttit.tzzd.sys.service;

import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.sys.common.DictHadler;
import com.ttit.tzzd.sys.dao.SysLogDao;
import com.ttit.tzzd.sys.entity.SysLog;
import com.ttit.tzzd.sys.enums.DictTypeEnum;
import com.ttit.tzzd.sys.exceptions.NotNullException;
import com.ttit.tzzd.sys.vo.SysLogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Description: 系统日志
 *
 * @author 小谢
 * Date: 2019/5/2311:08
 */
@Service
@Slf4j
public class SysLogServiceImpl extends BaseService implements SysLogService {
    @Resource
    private SysLogDao sysLogDao;
    @Resource
    private DictHadler dictHadler;

    @Override
    public PageInfo searchPage(String sysLogType, String keyword, Integer pageNum, Integer pageSize, String orderBy) {
        startPage(pageNum, pageSize, orderBy);
        List<SysLogVo> list = sysLogDao.searchPage(sysLogType, keyword);
        //遍历翻译日志类型
        list.forEach(sysLog -> {
            String logTypeName = dictHadler.get(DictTypeEnum.sysLogType, sysLog.getSysLogType());
            sysLog.setSysLogTypeName(logTypeName);
        });
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysLog addLog(SysLog log) {
        if (log == null) {
            throw new NotNullException();
        }
        sysLogDao.add(log);
        return log;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysLog addLog(String sysLogType, String content, String userId, Date createTime) {
        SysLog log = new SysLog(sysLogType, content, userId, createTime);
        return addLog(log);
    }
}
