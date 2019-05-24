package com.ttit.tzzd.manager.enums;

/**
 * Description: 设备日志类型
 *
 * @author 小谢
 * Date: 2019/5/239:53
 */
public enum DevLogTypeEnum {
    /**
     * 注册、删除、修改、登入、登出、报错
     */
    regist("1"),
    del("2"),
    modify("3"),
    login("4"),
    logout("5"),
    error("6");

    private String code;

    DevLogTypeEnum(String code) {
        this.code = code;
    }

    public static DevLogTypeEnum get(String devLogType) {
        for (DevLogTypeEnum type : DevLogTypeEnum.values()) {
            if (type.code.equals(devLogType)) {
                return type;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}
