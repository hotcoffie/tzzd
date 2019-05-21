package com.ttit.tzzd.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 软件管理
 *
 * @author xiaoxie
 * Date: 2019/5/2110:22
 */
@Data
@ApiModel("软件管理")
public class SoftManager implements Serializable {

	/** id */
  	@ApiModelProperty(name = "id" )
	private String id;

	/** (字典)软件所属类型编码 */
  	@ApiModelProperty(name = "(字典)软件所属类型编码" )
	private String softType;

	/** 当前版本号 */
  	@ApiModelProperty(name = "当前版本号" )
	private String version;

	/** 软件ID */
  	@ApiModelProperty(name = "软件ID" )
	private String softId;

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
