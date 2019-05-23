package com.ttit.tzzd.manager.service;

import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.manager.dao.DeviceGroupDao;
import com.ttit.tzzd.manager.dao.DeviceInfoDao;
import com.ttit.tzzd.manager.entity.DeviceGroup;
import com.ttit.tzzd.manager.entity.DeviceInfo;
import com.ttit.tzzd.manager.enums.DevLogType;
import com.ttit.tzzd.manager.enums.DeviceStatus;
import com.ttit.tzzd.manager.enums.SysLogType;
import com.ttit.tzzd.manager.vo.DeviceInfoVo;
import com.ttit.tzzd.sys.common.Constant;
import com.ttit.tzzd.sys.common.DictHadler;
import com.ttit.tzzd.sys.enums.DictType;
import com.ttit.tzzd.sys.exceptions.NotExistException;
import com.ttit.tzzd.sys.exceptions.NotNullException;
import com.ttit.tzzd.sys.service.BaseService;
import com.ttit.tzzd.sys.utils.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Description:设备信息
 *
 * @author 小谢
 * Date: 2019/5/2114:01
 */
@Service
@Slf4j
public class DeviceInfoServiceImpl extends BaseService implements DeviceInfoService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private DictHadler dictHadler;
    @Resource
    private DeviceInfoDao deviceInfoDao;
    @Resource
    private DeviceGroupDao deviceGroupDao;
    @Resource
    private DeviceLogService deviceLogService;
    @Resource
    private SysLogService sysLogService;

    @Override
    public PageInfo searchPage(String groupId, String keyword, Integer pageNum, Integer pageSize, String orderBy) {
        startPage(pageNum, pageSize, orderBy);
        List<DeviceInfoVo> list = deviceInfoDao.searchPage(groupId, keyword);
        //从Redis查询最新的注册信息
        list.forEach(this::updateByRedis);
        return new PageInfo<>(list);
    }

    @Override
    public DeviceInfoVo findById(String id) {
        if (StringUtils.isBlank(id)) {
            throw new NotNullException();
        }
        DeviceInfoVo deviceInfo = deviceInfoDao.findById(id);
        updateByRedis(deviceInfo);
        return deviceInfo;
    }

    @Override
    public DeviceInfoVo findBySerialNum(String serialNum) {
        if (StringUtils.isBlank(serialNum)) {
            throw new NotNullException();
        }
        DeviceInfoVo deviceInfo = deviceInfoDao.findBySerialNum(serialNum);
        //这里更新了一下设备最后在线信息给前台，没什么用，但是考虑到Redis速度够用，不妨提供完备的正确信息
        updateByRedis(deviceInfo);
        return deviceInfo;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public DeviceInfoVo regist(DeviceInfo deviceInfo) {
        //1.数据校验
        if (deviceInfo == null) {
            throw new NotNullException();
        }

        //2.固定项配置
        String id = UuidUtils.generate();
        deviceInfo.setId(id);
        deviceInfo.setDeviceStatus(DeviceStatus.online.getCode());

        //3.持久化
        Date now = new Date();
        deviceInfoDao.add(deviceInfo);
        //注册时间以long的字符串存入，这样是为了方便取出
        stringRedisTemplate.opsForValue().set(id, "" + now.getTime());

        //4.记录设备日志
        String content = "设备注册：" + deviceInfo.toString();
        deviceLogService.addLog(deviceInfo.getSerialNum(), DevLogType.regist.getCode(), content, now);

        return findById(id);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public DeviceInfoVo del(String id, String userId) {
        if (StringUtils.isBlank(id)) {
            throw new NotNullException();
        }
        DeviceInfoVo deviceInfo = findById(id);

        //如果设备信息不存在或者设备本身已处于被删除状态，则不进行多余操作
        if (deviceInfo == null || Constant.IS_DEL.equals(deviceInfo.getIsDel())) {
            throw new NotExistException("设备ID:" + id);
        }

        deviceInfoDao.del(id);
        String content = "删除设备：" + deviceInfo.toString();
        Date now = new Date();
        //记录系统日志
        sysLogService.addLog(SysLogType.del.getCode(), content, userId, now);
        //记录设备日志
        deviceLogService.addLog(deviceInfo.getSerialNum(), DevLogType.del.getCode(), content, now);
        return deviceInfo;
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public DeviceInfoVo modify(DeviceInfo deviceInfo, String userId) {
        //1.数据校验
        if (deviceInfo == null) {
            throw new NotNullException();
        }
        String id = deviceInfo.getId();
        if (StringUtils.isBlank(id)) {
            throw new NotNullException();
        }
        DeviceInfoVo deviceInfoTest = deviceInfoDao.findById(id);
        if (deviceInfoTest == null || Constant.IS_DEL.equals(deviceInfoTest.getIsDel())) {
            throw new NotExistException("设备ID:" + id);
        }

        //2.持久化
        deviceInfoDao.modify(deviceInfo);

        //3.记日志，因为只是修改了业主信息，不计入设备日志
        String content = "修改设备信息，原数据：" + deviceInfoTest.toString() + "；新数据：" + deviceInfo.toString();
        Date now = new Date();
        sysLogService.addLog(SysLogType.modify.getCode(), content, userId, now);

        return findById(id);
    }

    @Override
    public DeviceInfoVo modifyGroup(String id, String groupId, String userId) {
        //1.数据校验
        if (StringUtils.isBlank(id) || StringUtils.isBlank(groupId)) {
            throw new NotNullException();
        }
        DeviceInfoVo deviceInfo = deviceInfoDao.findById(id);
        if (deviceInfo == null || Constant.IS_DEL.equals(deviceInfo.getIsDel())) {
            throw new NotExistException("设备ID:" + id);
        }
        DeviceGroup deviceGroup = deviceGroupDao.findById(groupId);
        if (deviceGroup == null || Constant.IS_DEL.equals(deviceGroup.getIsDel())) {
            throw new NotExistException("设备分组ID:" + groupId);
        }

        //2.持久化
        deviceInfoDao.changeGroup(id, groupId);
        deviceInfo = findById(id);

        //3.记日志
        String content = "修改设备分组，原分组：" + deviceGroup.getName() + "；新分组：" + deviceInfo.getGroupName();
        Date now = new Date();
        //记录系统日志
        sysLogService.addLog(SysLogType.modify.getCode(), content, userId, now);
        //记录设备日志
        deviceLogService.addLog(deviceInfo.getSerialNum(), DevLogType.modify.getCode(), content, now);

        return deviceInfo;
    }

    /**
     * 设备连接时限，单位秒
     * 上次收到信号，在online时间内，视为在线
     */
    @Value("${business.time-limit.online}")
    public Long timeLimitOnlie;
    /**
     * 设备连接时限，单位秒
     * 上次收到信号，超过online时间，小于breaked时间，视为中断；大于breaked时间，视为离线
     */
    @Value("${business.time-limit.breaked}")
    public Long timeLimitbreaked;

    /**
     * 以Redis中的设备信息为准,更新设备在线信息
     */
    private void updateByRedis(DeviceInfoVo deviceInfo) {
        String redisValue = stringRedisTemplate.opsForValue().get(deviceInfo.getId());

        String status;
        if (StringUtils.isBlank(redisValue)) {
            status = DeviceStatus.offline.getCode();
        } else {
            //如果Redis中有设备信息，用时间信息与当前时间做比对，超过中断阈值的视为中断， 超过离线阈值的视为离线
            long time = Long.parseLong(redisValue);
            long now = System.currentTimeMillis();
            long second = TimeUnit.MILLISECONDS.toSeconds(now - time);
            if (second <= timeLimitOnlie) {
                status = DeviceStatus.online.getCode();
            } else if (second <= timeLimitbreaked) {
                status = DeviceStatus.breaked.getCode();
            } else {
                status = DeviceStatus.offline.getCode();
            }
        }
        deviceInfo.setDeviceStatus(status);
        String statusName = dictHadler.get(DictType.deviceStatus, status);
        deviceInfo.setDeviceStatusName(statusName);
    }
}
