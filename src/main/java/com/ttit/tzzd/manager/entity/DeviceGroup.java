package com.ttit.tzzd.manager.entity;

import com.ttit.tzzd.sys.common.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 设备分组信息
 *
 * @author xiaoxie
 * Date: 2019/5/2110:22
 */
@Data
@ApiModel(description = "设备分组信息")
public class DeviceGroup implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 分组名称
     */
    @ApiModelProperty(value = "分组名称", required = true)
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

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

    @Override
    public String toString() {
        return "DeviceGroup{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", creator='" + creator + '\'' +
                ", isDel='" + isDel + '\'' +
                '}';
    }
}
