package com.ttit.tzzd.sys.exceptions;

import com.ttit.tzzd.sys.common.Constant;

/**
 * Description: 业务异常：必填项不能为空
 *
 * @author 小谢
 * Date: 2019/5/2315:33
 */
public class NotNullException extends BusinessException{

    public NotNullException() {
        super(Constant.EXC_MSG_NOT_NULL);
    }
}
