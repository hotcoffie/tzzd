package com.ttit.tzzd.manager.enums;

/**
 * Description: 设备上报接口使用的上报类型，与设备日志类型使用同一组编码，但只支持其中三种
 *
 * @author 小谢
 * Date: 2019/5/2714:04
 */
public enum ReportTypeEnum {
    /**
     * 登入
     */
    login("4"),
    /**
     * 登出
     */
    logout("5"),
    /**
     * 异常
     */
    error("6");

    private String code;

    ReportTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ReportTypeEnum get(String typeCode) {
        for (ReportTypeEnum type : ReportTypeEnum.values()) {
            if (type.code.equalsIgnoreCase(typeCode)) {
                return type;
            }
        }
        return null;
    }
}
