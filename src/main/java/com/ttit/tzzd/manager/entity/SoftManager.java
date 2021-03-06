package com.ttit.tzzd.manager.entity;

import com.ttit.tzzd.sys.common.Constant;
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
@ApiModel(description = "软件管理")
public class SoftManager implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * (字典)软件所属类型编码
     */
    @ApiModelProperty(value = "(字典)软件所属类型编码")
    private String softType;

    /**
     * 当前版本号
     */
    @ApiModelProperty(value = "当前版本号")
    private String version;

    /**
     * 软件ID
     */
    @ApiModelProperty(value = "软件ID")
    private String softId;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID", hidden = true)
    private String creator;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updateTime;

    /**
     * 删除标识:1删除0未删除
     */
    @ApiModelProperty(value = "删除标识:1删除0未删除", hidden = true)
    private String isDel = Constant.IS_NOT_DEL;


}
