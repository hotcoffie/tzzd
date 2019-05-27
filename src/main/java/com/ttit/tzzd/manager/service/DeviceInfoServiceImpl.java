package com.ttit.tzzd.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.manager.dao.DeviceGroupDao;
import com.ttit.tzzd.manager.dao.DeviceInfoDao;
import com.ttit.tzzd.manager.entity.DeviceGroup;
import com.ttit.tzzd.manager.entity.DeviceInfo;
import com.ttit.tzzd.manager.enums.DevLogTypeEnum;
import com.ttit.tzzd.manager.enums.DeviceStatusEnum;
import com.ttit.tzzd.manager.enums.ReportTypeEnum;
import com.ttit.tzzd.manager.vo.DevReportVo;
import com.ttit.tzzd.manager.vo.DeviceInfoVo;
import com.ttit.tzzd.sys.common.Constant;
import com.ttit.tzzd.sys.common.DictHadler;
import com.ttit.tzzd.sys.enums.DictTypeEnum;
import com.ttit.tzzd.sys.enums.SysLogTypeEnum;
import com.ttit.tzzd.sys.exceptions.BusinessException;
import com.ttit.tzzd.sys.exceptions.NotExistException;
import com.ttit.tzzd.sys.exceptions.NotNullException;
import com.ttit.tzzd.sys.service.SysLogService;
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
public class DeviceInfoServiceImpl implements DeviceInfoService {
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
        PageHelper.startPage(pageNum, pageSize, orderBy);
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
    @Transactional(rollbackFor = Exception.class)
    public DeviceInfoVo regist(DeviceInfo deviceInfo, String userId) {
        //1.数据校验
        if (deviceInfo == null) {
            throw new NotNullException();
        }

        //2.固定项配置
        String id = UuidUtils.generate();
        deviceInfo.setId(id);
        deviceInfo.setDeviceStatus(DeviceStatusEnum.online.getCode());
        deviceInfo.setCreator(userId);

        //3.持久化
        Date now = new Date();
        deviceInfoDao.add(deviceInfo);

        //4.记录日志
        String content = "设备注册：" + deviceInfo.toString();
        sysLogService.addLog(SysLogTypeEnum.regist.getCode(), content, userId, now);
        deviceLogService.addLog(deviceInfo.getSerialNum(), DevLogTypeEnum.regist.getCode(), content, now);

        return findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        sysLogService.addLog(SysLogTypeEnum.del.getCode(), content, userId, now);
        //记录设备日志
        deviceLogService.addLog(deviceInfo.getSerialNum(), DevLogTypeEnum.del.getCode(), content, now);
        return deviceInfo;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
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
        sysLogService.addLog(SysLogTypeEnum.modify.getCode(), content, userId, now);

        return findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        sysLogService.addLog(SysLogTypeEnum.modify.getCode(), content, userId, now);
        //记录设备日志
        deviceLogService.addLog(deviceInfo.getSerialNum(), DevLogTypeEnum.modify.getCode(), content, now);

        return deviceInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void report(DevReportVo reportVo) {
        if (reportVo == null || StringUtils.isBlank(reportVo.getDevId())) {
            throw new NotNullException();
        }
        String devId = reportVo.getDevId();
        DeviceInfoVo deviceInfo = legalize(devId, reportVo.getDevSerialNum(), reportVo.getDevSerialCode());
        //定义了6种设备日志类型，但设备上报接口只接收其中5上线6下线7异常三种

        //记录设备日志
        String reportType = reportVo.getReportType();
        Date now = new Date();
        if (ReportTypeEnum.login.getCode().equalsIgnoreCase(reportType)) {
            //redis中更新设备状态
            stringRedisTemplate.opsForValue().set(devId, String.valueOf(now.getTime()));
            //记录设备日志
            deviceLogService.addLog(deviceInfo.getSerialNum(), reportType, "设备上线", now);
        } else if (ReportTypeEnum.logout.getCode().equalsIgnoreCase(reportType)) {
            //redis中移除下线的设备
            stringRedisTemplate.delete(devId);
            //数据库记录下线时间
            deviceInfoDao.updateLogout(devId, DeviceStatusEnum.offline.getCode(), now);
            //记录设备日志
            deviceLogService.addLog(deviceInfo.getSerialNum(), reportType, "设备下线", now);
        } else if (ReportTypeEnum.error.getCode().equalsIgnoreCase(reportType)) {
            deviceLogService.addLog(deviceInfo.getSerialNum(), reportType, reportVo.getReportMsg(), now);
        }

        throw new BusinessException("日志上报类型有误！");
    }

    @Override
    public DeviceInfoVo legalize(String id, String serialNum, String serialCode) {
        if (StringUtils.isBlank(id) || StringUtils.isBlank(serialNum) || StringUtils.isBlank(serialCode)) {
            throw new NotNullException();
        }
        DeviceInfoVo deviceInfoVo = deviceInfoDao.findById(id);
        if (deviceInfoVo == null ||
                !serialNum.equalsIgnoreCase(deviceInfoVo.getSerialNum()) ||
                !serialCode.equalsIgnoreCase(deviceInfoVo.getSerialCode())) {
            throw new NotExistException(" 设备序列号：" + serialNum + " 设备串码：" + serialCode);
        }
        return deviceInfoVo;
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
            status = DeviceStatusEnum.offline.getCode();
        } else {
            //如果Redis中有设备信息，用时间信息与当前时间做比对，超过中断阈值的视为中断， 超过离线阈值的视为离线
            long time = Long.parseLong(redisValue);
            long now = System.currentTimeMillis();
            long second = TimeUnit.MILLISECONDS.toSeconds(now - time);
            if (second <= timeLimitOnlie) {
                status = DeviceStatusEnum.online.getCode();
            } else if (second <= timeLimitbreaked) {
                status = DeviceStatusEnum.breaked.getCode();
            } else {
                status = DeviceStatusEnum.offline.getCode();
                stringRedisTemplate.delete(deviceInfo.getId());
            }
        }
        deviceInfo.setDeviceStatus(status);
        String statusName = dictHadler.get(DictTypeEnum.deviceStatus, status);
        deviceInfo.setDeviceStatusName(statusName);
    }
}
