package com.ttit.tzzd.sys.enums;

/**
 * Description: 所有字典类别枚举
 *
 * @author 小谢
 * Date: 2019/5/2116:57
 */
public enum DictTypeEnum {
    /**
     * 删除状态
     */
    isDel("isDel"),
    /**
     * 设备状态
     */
    deviceStatus("deviceStatus"),
    /**
     * 设备日志
     */
    devLogType("devLogType"),
    /**
     * 系统日志
     */
    sysLogType("sysLogType"),
    /**
     * 软件类型
     */
    softType("softType");

    private String code;

    DictTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
