package com.ttit.tzzd.manager.service;

import com.ttit.tzzd.manager.dao.SysLogDao;
import com.ttit.tzzd.manager.entity.SysLog;
import com.ttit.tzzd.sys.exceptions.NotNullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public SysLog addLog(SysLog log) {
        if (log == null) {
            throw new NotNullException();
        }
        sysLogDao.add(log);
        return log;
    }

    @Override
    public SysLog addLog(String sysLogType, String content, String userId, Date createTime) {
        SysLog log = new SysLog(sysLogType, content, userId, createTime);
        return addLog(log);
    }
}
