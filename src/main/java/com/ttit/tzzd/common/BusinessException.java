package com.ttit.tzzd.common;

/**
 * Description:业务异常
 *
 * @author 小谢
 * Date: 2019/5/2016:27
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }
}
