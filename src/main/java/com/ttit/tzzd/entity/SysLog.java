package com.ttit.tzzd.entity;

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
@ApiModel("系统日志")
public class SysLog implements Serializable {

	/** id */
  	@ApiModelProperty(name = "id" )
	private String id;

	/** 日志类别 */
  	@ApiModelProperty(name = "日志类别" )
	private String sysLogType;

	/** 内容 */
  	@ApiModelProperty(name = "内容" )
	private String content;

	/** 创建人ID,暂时默认1 */
  	@ApiModelProperty(name = "创建人ID,暂时默认1" )
	private String creator;

	/** 创建时间 */
  	@ApiModelProperty(name = "创建时间" )
	private Date createTime;

	/** 删除标识:1删除0未删除 */
  	@ApiModelProperty(name = "删除标识:1删除0未删除" )
	private String isDel;


}
