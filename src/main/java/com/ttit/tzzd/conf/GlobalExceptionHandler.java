package com.ttit.tzzd.conf;

import com.ttit.tzzd.common.BusinessException;
import com.ttit.tzzd.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * Description:全局的异常处理
 *
 * @author 小谢
 * Date: 2019/5/219:28
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 系统测试状态标识
     */
    private static final String ACTIVE_TEST = "test";
    /**
     * 系统开发状态标识
     */
    private static final String ACTIVE_DEV = "dev";
    /**
     * 系统当前状态
     */
    @Value("${spring.profiles.active}")
    private String active;

    /**
     * 通用的记录日志，返回指定错误信息方法
     */
    private ResultVo error(String msg, Exception e) {
        log.error(msg, e);
        //开发和测试状态，返回完整的错误栈到前台
        if (ACTIVE_TEST.equals(active) || ACTIVE_DEV.equals(active)) {
            return ResultVo.error(msg, e);
        }
        return ResultVo.error(msg);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResultVo handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return error("缺少请求参数", e);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResultVo handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return error("参数解析失败！", e);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultVo handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String msg = "参数验证失败！" + String.format("%s:%s", field, code);
        return error(msg, e);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultVo handleBindException(BindException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String msg = "参数绑定失败！" + String.format("%s:%s", field, code);
        return error(msg, e);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResultVo handleServiceException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String msg = "参数验证失败！" + violation.getMessage();
        return error(msg, e);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResultVo handleValidationException(ValidationException e) {
        return error("参数验证失败", e);
    }

    /**
     * 404 - Not Found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResultVo noHandlerFoundException(NoHandlerFoundException e) {
        return error("访问路径不存在！", e);
    }


    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResultVo handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return error("不支持当前请求方法！", e);
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ResultVo handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return error("不支持当前媒体类型！", e);
    }

    /**
     * 自定义的业务异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResultVo handleServiceException(BusinessException e) {
        String msg = "业务异常：" + e.getMessage();
        return error(msg, e);
    }

    /**
     * 获取其它异常。包括500
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVo defaultErrorHandler(Exception e) {
        return error("系统异常：请稍后再试！", e);

    }
}
