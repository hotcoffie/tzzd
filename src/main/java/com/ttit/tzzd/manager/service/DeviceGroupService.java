package com.ttit.tzzd.manager.service;

import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.manager.entity.DeviceGroup;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2316:02
 */
public interface DeviceGroupService {
    PageInfo searchPage(String keyword, Integer pageNum, Integer pageSize, String orderBy);

    DeviceGroup add(DeviceGroup deviceGroup, String userId);

    DeviceGroup del(String id, String userId);

    DeviceGroup modify(DeviceGroup deviceGroup, String userId);
}
