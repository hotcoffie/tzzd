package com.ttit.tzzd.enums;

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
     * 下线
     */
    offline("3");
    private String value;

    DeviceStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
