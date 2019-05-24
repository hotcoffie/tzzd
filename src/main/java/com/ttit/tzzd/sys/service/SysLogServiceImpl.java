package com.ttit.tzzd.sys.service;

import com.ttit.tzzd.sys.dao.SysLogDao;
import com.ttit.tzzd.sys.entity.SysLog;
import com.ttit.tzzd.sys.exceptions.NotNullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Description: 系统日志
 *
 * @author 小谢
 * Date: 2019/5/2311:08
 */
@Service
@Slf4j
public class SysLogServiceImpl implements SysLogService {
    @Resource
    private SysLogDao sysLogDao;

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
