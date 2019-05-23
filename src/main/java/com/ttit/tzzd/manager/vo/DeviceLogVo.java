package com.ttit.tzzd.manager.vo;

import com.ttit.tzzd.manager.entity.DeviceLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2310:21
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(description = "设备日志")
public class DeviceLogVo extends DeviceLog {
    @ApiModelProperty(value = "设备日志类型名称")
    private String devLogTypeName;
}
