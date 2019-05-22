package com.ttit.tzzd.manager.service;

import com.github.pagehelper.PageInfo;

/**
 * Description:管理系统-设备信息
 *
 * @author 小谢
 * Date: 2019/5/2114:00
 */
public interface DeviceInfoService {
    /**
     * 分页查询设备信息列表
     */
    PageInfo searchPage(String keyword, Integer pageNum, Integer pageSize, String orderBy);
}
