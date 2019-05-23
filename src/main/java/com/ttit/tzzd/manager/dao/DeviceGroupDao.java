package com.ttit.tzzd.manager.dao;

import com.ttit.tzzd.manager.entity.DeviceGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2315:49
 */
public interface DeviceGroupDao {

    List<DeviceGroup> searchPage(@Param("keyword") String keyword);

    DeviceGroup findById(@Param("id") String id);

    Integer add(DeviceGroup deviceGroup);

    Integer del(@Param("id") String id);

    Integer modify(DeviceGroup deviceGroup);

}
