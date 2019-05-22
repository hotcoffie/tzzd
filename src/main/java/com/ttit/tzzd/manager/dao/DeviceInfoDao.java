package com.ttit.tzzd.manager.dao;

import com.ttit.tzzd.sys.entity.DeviceInfo;
import com.ttit.tzzd.manager.vo.DeviceInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2114:09
 */
public interface DeviceInfoDao {
    List<DeviceInfoVo> searchPage(@Param("keyword") String keyword);

    int add(DeviceInfo deviceInfo);

    DeviceInfo findById(@Param("id")String id);
}
