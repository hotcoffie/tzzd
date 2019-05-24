package com.ttit.tzzd.manager.entity;

import com.ttit.tzzd.sys.common.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 软件信息
 *
 * @author xiaoxie
 * Date: 2019/5/2110:22
 */
@Data
@NoArgsConstructor
@ApiModel(description = "软件信息")
public class SoftInfo implements Serializable {

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
     * 软件名称
     */
    @ApiModelProperty(value = "软件名称")
    private String name;

    /**
     * 下载地址
     */
    @ApiModelProperty(value = "下载地址")
    private String url;

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
     * 删除标识:1删除0未删除
     */
    @ApiModelProperty(value = "删除标识:1删除0未删除", hidden = true)
    private String isDel = Constant.IS_NOT_DEL;


    public SoftInfo(String id, String softType, String name, String url, String creator) {
        this.id = id;
        this.softType = softType;
        this.name = name;
        this.url = url;
        this.creator = creator;
    }

}
