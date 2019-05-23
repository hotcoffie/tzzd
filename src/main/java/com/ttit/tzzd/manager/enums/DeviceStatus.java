package com.ttit.tzzd.manager.enums;

/**
 * Description: 设备状态
 *
 * @author 小谢
 * Date: 2019/5/2116:22
 */
public enum DeviceStatus {
    /**
     * 在线
     */
    online("1"),
    /**
     * 中断
     */
    breaked("2"),
    /**
     * 离线
     */
    offline("3");
    private String code;

    DeviceStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
