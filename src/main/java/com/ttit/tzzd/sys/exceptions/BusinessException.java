package com.ttit.tzzd.sys.exceptions;

/**
 * Description: 默认的全局通用业务异常
 *
 * @author 小谢
 * Date: 2019/5/2016:27
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }
}
