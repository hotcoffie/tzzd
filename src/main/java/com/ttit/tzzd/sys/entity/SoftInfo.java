package com.ttit.tzzd.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 软件信息
 *
 * @author xiaoxie
 * Date: 2019/5/2110:22
 */
@Data
@ApiModel(description = "软件信息")
public class SoftInfo implements Serializable {

	/** id */
  	@ApiModelProperty(value = "id" )
	private String id;

	/** (字典)软件所属类型编码 */
  	@ApiModelProperty(value = "(字典)软件所属类型编码" )
	private String softType;

	/** 软件名称 */
  	@ApiModelProperty(value = "软件名称" )
	private String name;

	/** 下载路径 */
  	@ApiModelProperty(value = "下载路径" )
	private String path;

	/** 创建人ID,暂时默认1 */
  	@ApiModelProperty(value = "创建人ID,暂时默认1" )
	private String creator;

	/** 创建时间 */
  	@ApiModelProperty(value = "创建时间" )
	private Date createTime;

	/** 更新时间 */
  	@ApiModelProperty(value = "更新时间" )
	private Date updateTime;

	/** 删除标识:1删除0未删除 */
  	@ApiModelProperty(value = "删除标识:1删除0未删除" )
	private String isDel;


}
