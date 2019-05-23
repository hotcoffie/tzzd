package com.ttit.tzzd.manager.exceptions;

import com.ttit.tzzd.sys.exceptions.BusinessException;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Description: 设备异常，此类异常会被记录到设备日志中
 *
 * @author 小谢
 * Date: 2019/5/239:29
 */
@Getter
@Setter
public class DeviceException extends BusinessException {
    private Date time;
    private String serialNum;

    public DeviceException(String serialNum, String msg, Date time) {
        super(msg);
        this.serialNum = serialNum;
        this.time = time;
    }

    public DeviceException(String serialNum, String msg) {
        super(msg);
        this.serialNum = serialNum;
        this.time = new Date();
    }
}
