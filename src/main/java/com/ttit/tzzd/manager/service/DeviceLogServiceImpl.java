package com.ttit.tzzd.manager.service;

import com.ttit.tzzd.manager.dao.DeviceInfoDao;
import com.ttit.tzzd.manager.dao.DeviceLogDao;
import com.ttit.tzzd.manager.entity.DeviceInfo;
import com.ttit.tzzd.manager.entity.DeviceLog;
import com.ttit.tzzd.manager.exceptions.DeviceException;
import com.ttit.tzzd.sys.exceptions.BusinessException;
import com.ttit.tzzd.sys.exceptions.NotNullException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Description: 设备日志
 *
 * @author 小谢
 * Date: 2019/5/239:32
 */
@Service
public class DeviceLogServiceImpl implements DeviceLogService {
    @Resource
    private DeviceLogDao deviceLogDao;
    @Resource
    private DeviceInfoDao deviceInfoDao;

    @Override
    public DeviceLog addLog(DeviceLog log) {
        if (log == null) {
            throw new NotNullException();
        }
        String devSerialNum = log.getDevSerialNum();
        DeviceInfo deviceInfo = deviceInfoDao.findBySerialNum(devSerialNum);
        if (deviceInfo == null) {
            throw new BusinessException("不存在串号[" + devSerialNum + "]的设备！");
        }
        deviceLogDao.add(log);
        return log;
    }

    @Override
    public DeviceLog addLog(String devSerialNum, String devLogType, String content, Date createTime) {
        DeviceLog log = new DeviceLog(devSerialNum, devLogType, content, createTime);
        return addLog(log);
    }

    @Override
    public DeviceLog addLog(DeviceException e) {
        DeviceLog log = new DeviceLog(e);
        return addLog(log);
    }

}
