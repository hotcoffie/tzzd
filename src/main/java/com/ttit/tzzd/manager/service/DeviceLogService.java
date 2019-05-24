package com.ttit.tzzd.manager.service;

import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.manager.entity.DeviceLog;
import com.ttit.tzzd.manager.exceptions.DeviceException;

import java.util.Date;

/**
 * Description: 设备日志
 *
 * @author 小谢
 * Date: 2019/5/239:32
 */
public interface DeviceLogService {
    PageInfo searchPage(String devLogType, String keyword, Integer pageNum, Integer pageSize, String orderBy);
    /**
     * 记录设备日志
     *
     * @param log 设备日志
     */
    DeviceLog addLog(DeviceLog log);

    /**
     * 记录设备日志
     */
    DeviceLog addLog(String devSerialNum, String devLogType, String content, Date createTime);

    /**
     * 记录设备异常信息的日志
     *
     * @param e 设备异常
     */
    DeviceLog addLog(DeviceException e);

}
