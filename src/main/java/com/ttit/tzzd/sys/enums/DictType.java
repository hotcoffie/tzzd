package com.ttit.tzzd.sys.enums;

/**
 * Description: 所有字典类别枚举
 *
 * @author 小谢
 * Date: 2019/5/2116:57
 */
public enum DictType {
    /**
     * 设备状态
     */
    deviceStatus("deviceStatus"),
    devLogType("devLogType"),
    sysLogType("sysLogType");

    private String code;

    DictType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
