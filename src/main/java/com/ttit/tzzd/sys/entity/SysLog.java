package com.ttit.tzzd.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 系统日志
 *
 * @author xiaoxie
 * Date: 2019/5/2110:22
 */
@Data
@ApiModel(description = "系统日志")
public class SysLog implements Serializable {

	/** id */
  	@ApiModelProperty(value = "id" )
	private String id;

	/** 日志类别 */
  	@ApiModelProperty(value = "日志类别" )
	private String sysLogType;

	/** 内容 */
  	@ApiModelProperty(value = "内容" )
	private String content;

	/** 创建人ID,暂时默认1 */
  	@ApiModelProperty(value = "创建人ID,暂时默认1" )
	private String creator;

	/** 创建时间 */
  	@ApiModelProperty(value = "创建时间" )
	private Date createTime;

	/** 删除标识:1删除0未删除 */
  	@ApiModelProperty(value = "删除标识:1删除0未删除" )
	private String isDel;


}
