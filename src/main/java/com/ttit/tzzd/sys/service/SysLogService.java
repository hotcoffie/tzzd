package com.ttit.tzzd.sys.service;

import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.sys.entity.SysLog;

import java.util.Date;

/**
 * Description: 系统日志
 *
 * @author 小谢
 * Date: 2019/5/239:32
 */
public interface SysLogService {
    /**
     * 记录设备日志
     *
     * @param log 设备日志
     */
    SysLog addLog(SysLog log);

    /**
     * 记录设备日志
     */
    SysLog addLog(String sysLogType, String content, String userId, Date createTime);

    PageInfo searchPage(String sysLogType, String keyword, Integer pageNum, Integer pageSize, String orderBy);
}
