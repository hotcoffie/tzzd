package com.ttit.tzzd.sys.exceptions;

import com.ttit.tzzd.sys.common.Constant;

/**
 * Description: 业务异常：对应数据不存在
 *
 * @author 小谢
 * Date: 2019/5/2315:33
 */
public class NotExistException extends BusinessException {

    public NotExistException(String msg) {
        super(Constant.EXC_MSG_NOT_EXIST + "，" + msg);
    }
}
