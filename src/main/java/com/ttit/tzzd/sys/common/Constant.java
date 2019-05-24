package com.ttit.tzzd.sys.common;

/**
 * Description:存放公共常量
 *
 * @author 小谢
 * Date: 2019/5/2016:33
 */
public class Constant {
    public static final String SUCCESS = "success";
    public static final String ERROR = "errer";
    public static final String TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public static final String DICT_SAVE_TYPE_APP = "app";
    public static final String DICT_SAVE_TYPE_REDIS = "redis";

    /**
     * 删除标识
     */
    public static final String IS_DEL = "1";
    public static final String IS_NOT_DEL = "0";
    /**
     * 默认的管理员ID
     */
    public static final String USER_ID_ADMIN = "1";

    public static final String DOWNLOAD_PATH = "/sys/atta/download/";

    /**
     * 业务异常提示语，必填项校验提示
     */
    public static final String EXC_MSG_NOT_NULL = "必填项不能为空！";
    public static final String EXC_MSG_NOT_EXIST = "指定数据不存在！";
}
