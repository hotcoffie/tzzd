package com.ttit.tzzd.manager.dao;

import com.ttit.tzzd.manager.entity.DeviceInfo;
import com.ttit.tzzd.manager.vo.DeviceInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:设备信息
 *
 * @author 小谢
 * Date: 2019/5/2114:09
 */
public interface DeviceInfoDao {
    List<DeviceInfoVo> searchPage(@Param("groupId") String groupId, @Param("keyword") String keyword);

    Integer add(DeviceInfo deviceInfo);

    DeviceInfoVo findById(@Param("id") String id);

    void del(@Param("id") String id);

    void modify(DeviceInfo deviceInfo);

    DeviceInfoVo findBySerialNum(@Param("serialNum") String serialNum);

    Integer changeGroup(@Param("id") String id, @Param("groupId") String groupId);
}
