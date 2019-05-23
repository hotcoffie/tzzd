package com.ttit.tzzd.manager.conf;

import com.ttit.tzzd.manager.service.DeviceLogService;
import com.ttit.tzzd.sys.conf.GlobalExceptionHandler;
import com.ttit.tzzd.manager.exceptions.DeviceException;
import com.ttit.tzzd.sys.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Description:管理终端的异常处理
 *
 * @author 小谢
 * Date: 2019/5/219:28
 */
@ControllerAdvice
@Slf4j
public class ManagerExceptionHandler extends GlobalExceptionHandler {
    private final DeviceLogService deviceLogService;

    public ManagerExceptionHandler(DeviceLogService deviceLogService) {
        this.deviceLogService = deviceLogService;
    }

    /**
     * 自定义的业务异常,除了返回异常信息，还要记录到设备日志（数据库）中
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DeviceException.class)
    @ResponseBody
    public ResultVo handleDeviceException(DeviceException e) {
        String msg = "设备异常：" + e.getMessage();
        deviceLogService.addLog(e);
        return error(msg, e);
    }
}
