package com.ttit.tzzd.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 设备信息
 *
 * @author xiaoxie
 * Date: 2019/5/2110:22
 */
@Data
@ApiModel("设备信息")
public class DeviceInfo implements Serializable {

	/** id */
  	@ApiModelProperty(name = "id" )
	private String id;

	/** 序列号 */
  	@ApiModelProperty(name = "序列号" )
	private String serialNum;

	/** 串码 */
  	@ApiModelProperty(name = "串码" )
	private String serialCode;

	/** 通告时间 */
  	@ApiModelProperty(name = "通告时间" )
	private Date noticeTime;

	/** (字典)设备状态 */
  	@ApiModelProperty(name = "(字典)设备状态" )
	private String deviceStatus;

	/** 分组编码 */
  	@ApiModelProperty(name = "分组编码" )
	private String groupCode;

	/** 业主姓名 */
  	@ApiModelProperty(name = "业主姓名" )
	private String ownerName;

	/** 业主电话 */
  	@ApiModelProperty(name = "业主电话" )
	private String ownerPhone;

	/** 地址/备注 */
  	@ApiModelProperty(name = "地址/备注" )
	private String remark;

	/** 创建时间 */
  	@ApiModelProperty(name = "创建时间" )
	private Date createTime;

	/** 更新时间 */
  	@ApiModelProperty(name = "更新时间" )
	private Date updateTime;

	/** 删除标识:1删除0未删除 */
  	@ApiModelProperty(name = "删除标识:1删除0未删除" )
	private String isDel;


}
