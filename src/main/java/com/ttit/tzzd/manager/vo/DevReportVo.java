package com.ttit.tzzd.manager.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Description: 设备上报信息封装
 *
 * @author 小谢
 * Date: 2019/5/2714:00
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "设备上报信息封装")
public class DevReportVo {
    /**
     * 设备ID
     */
    @ApiModelProperty(value = "设备ID" )
    private String devId;
    /**
     * 设备序列号
     */
    @ApiModelProperty(value = "设备序列号" )
    private String devSerialNum;
    /**
     * 设备串号
     */
    @ApiModelProperty(value = "设备串号" )
    private String devSerialCode;
    /**
     * 通告时间
     */
    @ApiModelProperty(value = "通告时间")
    private Date noticeTime;
    /**
     * 上报类型
     */
    @ApiModelProperty(value = "上报类型：1在线2下线3异常")
    private String reportType;
    /**
     * 上报详情
     */
    @ApiModelProperty(value = "上报详情")
    private String reportMsg;

}
