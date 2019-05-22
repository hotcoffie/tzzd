package com.ttit.tzzd.service.manager;

import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.common.DictHadler;
import com.ttit.tzzd.dao.DeviceInfoDao;
import com.ttit.tzzd.enums.DeviceStatus;
import com.ttit.tzzd.enums.DictType;
import com.ttit.tzzd.service.BaseService;
import com.ttit.tzzd.vo.DeviceInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:管理系统-设备信息
 *
 * @author 小谢
 * Date: 2019/5/2114:01
 */
@Service
public class DeviceInfoServiceImpl extends BaseService implements DeviceInfoService {
    @Resource
    private DeviceInfoDao deviceInfoDao;

    @Override
    public PageInfo searchPage(String keyword, Integer pageNum, Integer pageSize, String orderBy) {
        startPage(pageNum, pageSize, orderBy);
        List<DeviceInfoVo> list = deviceInfoDao.searchPage(keyword);
        //从Redis查询最新的注册信息
        list.forEach(deviceInfo -> {
                    //以Redis中的设备信息为准
                    String status = queryCache(deviceInfo.getId(), DeviceStatus.offline.getValue());
                    String statusName = DictHadler.get(DictType.deviceStatus, status);
                    deviceInfo.setDeviceStatus(status);
                    deviceInfo.setDeviceStatusName(statusName);
                }
        );
        return new PageInfo<>(list);
    }
}
