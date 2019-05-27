package com.ttit.tzzd.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.manager.dao.DeviceGroupDao;
import com.ttit.tzzd.manager.entity.DeviceGroup;
import com.ttit.tzzd.sys.common.Constant;
import com.ttit.tzzd.sys.enums.SysLogTypeEnum;
import com.ttit.tzzd.sys.exceptions.NotExistException;
import com.ttit.tzzd.sys.exceptions.NotNullException;
import com.ttit.tzzd.sys.service.SysLogService;
import com.ttit.tzzd.sys.utils.UuidUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2316:02
 */
@Service
public class DeviceGroupServiceImpl implements DeviceGroupService {
    @Resource
    private DeviceGroupDao deviceGroupDao;
    @Resource
    private SysLogService sysLogService;

    @Override
    public PageInfo searchPage(String keyword, Integer pageNum, Integer pageSize, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<DeviceGroup> list = deviceGroupDao.searchPage(keyword);
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeviceGroup add(DeviceGroup deviceGroup, String userId) {
        //1.数据校验
        if (deviceGroup == null) {
            throw new NotNullException();
        }

        //2.固定项配置
        String id = UuidUtils.generate();
        deviceGroup.setId(id);
        deviceGroup.setCreator(userId);

        //3.持久化
        deviceGroupDao.add(deviceGroup);

        //4.记录设备日志
        String content = "新增设备分组：" + deviceGroup.toString();
        Date now = new Date();
        sysLogService.addLog(SysLogTypeEnum.modify.getCode(), content, userId, now);

        return deviceGroupDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeviceGroup del(String id, String userId) {
        //1.数据校验
        if (StringUtils.isBlank(id)) {
            throw new NotNullException();
        }
        DeviceGroup deviceGroup = deviceGroupDao.findById(id);
        if (deviceGroup == null || Constant.IS_DEL.equals(deviceGroup.getIsDel())) {
            throw new NotExistException("设备分组ID:" + id);
        }

        deviceGroupDao.del(id);
        String content = "删除设备分组：" + deviceGroup.toString();
        Date now = new Date();
        //记录系统日志
        sysLogService.addLog(SysLogTypeEnum.del.getCode(), content, userId, now);

        return deviceGroup;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeviceGroup modify(DeviceGroup deviceGroup, String userId) {
        //1.数据校验
        if (deviceGroup == null) {
            throw new NotNullException();
        }
        String id = deviceGroup.getId();
        if (StringUtils.isBlank(id)) {
            throw new NotNullException();
        }
        DeviceGroup deviceGroupTest = deviceGroupDao.findById(id);
        if (deviceGroupTest == null || Constant.IS_DEL.equals(deviceGroupTest.getIsDel())) {
            throw new NotExistException("设备分组ID:" + id);
        }

        //2.持久化
        deviceGroupDao.modify(deviceGroup);

        //3.记日志，因为只是修改了业主信息，不计入设备日志
        String content = "修改设备分组信息，原数据：" + deviceGroupTest.toString() + "；新数据：" + deviceGroup.toString();
        Date now = new Date();
        sysLogService.addLog(SysLogTypeEnum.modify.getCode(), content, userId, now);

        return deviceGroupDao.findById(id);
    }
}
