package com.ttit.tzzd.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 设备日志
 *
 * @author xiaoxie
 * Date: 2019/5/2110:22
 */
@Data
@ApiModel(description = "设备日志")
public class DeviceLog implements Serializable {

	/** id */
  	@ApiModelProperty(value = "id" )
	private String id;

	/** 设备串码 */
  	@ApiModelProperty(value = "设备串码" )
	private String devSerialNum;

	/** (字典)日志类型 */
  	@ApiModelProperty(value = "(字典)日志类型" )
	private String logType;

	/** 内容 */
  	@ApiModelProperty(value = "内容" )
	private String content;

	/** 创建时间 */
  	@ApiModelProperty(value = "创建时间" )
	private Date createTime;

	/** 删除标识:1删除0未删除 */
  	@ApiModelProperty(value = "删除标识:1删除0未删除" )
	private String isDel;


}
