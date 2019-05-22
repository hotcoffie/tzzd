package com.ttit.tzzd.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 数据字典
 *
 * @author xiaoxie
 * Date: 2019/5/2110:22
 */
@Data
@ApiModel(description = "数据字典")
public class Dictionary implements Serializable {

	/** id */
  	@ApiModelProperty(value = "id" )
	private String id;

	/** 编码类型 */
  	@ApiModelProperty(value = "编码类型" )
	private String type;

	/** 编码类型名称 */
  	@ApiModelProperty(value = "编码类型名称" )
	private String typeName;

	/** 编码 */
  	@ApiModelProperty(value = "编码" )
	private String code;

	/** 值 */
  	@ApiModelProperty(value = "值" )
	private String value;

	/** 值4 */
  	@ApiModelProperty(value = "值4" )
	private String value4;

	/** 值3 */
  	@ApiModelProperty(value = "值3" )
	private String value3;

	/** 值2 */
  	@ApiModelProperty(value = "值2" )
	private String value2;

	/** 排序，越大越前 */
  	@ApiModelProperty(value = "排序，越大越前" )
	private long orderNum;

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
