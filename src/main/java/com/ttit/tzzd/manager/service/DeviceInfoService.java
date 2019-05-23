package com.ttit.tzzd.manager.service;

import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.manager.vo.DeviceInfoVo;
import com.ttit.tzzd.manager.entity.DeviceInfo;

/**
 * Description:设备信息
 *
 * @author 小谢
 * Date: 2019/5/2114:00
 */
public interface DeviceInfoService {
    /**
     * 分页查询设备信息列表
     */
    PageInfo searchPage(String groupId, String keyword, Integer pageNum, Integer pageSize, String orderBy);

    /**
     * 根据ID查询设备信息
     */
    DeviceInfoVo findById(String id);

    /**
     * 从设备列表中逻辑删除指定设备
     */
    DeviceInfoVo del(String id, String userId);

    /**
     * 注册新的设备
     */
    DeviceInfoVo regist(DeviceInfo deviceInfo);

    /**
     * 根据设备串号查询设备信息
     */
    DeviceInfoVo findBySerialNum(String devSerialNum);

    /**
     * 修改设备信息，只提供业主信息修改
     */
    DeviceInfoVo modify(DeviceInfo deviceInfo, String userId);

    /**
     * 修改设备分组信息
     */
    DeviceInfoVo modifyGroup(String id, String groupId, String userId);
}
