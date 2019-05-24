package com.ttit.tzzd.sys.entity;

import com.ttit.tzzd.sys.common.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 附件
 *
 * @author xiaoxie
 * Date: 2019/5/2110:22
 */
@Data
@NoArgsConstructor
@ApiModel(description = "附件")
public class Attachment implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称")
    private String name;

    /**
     * 附件大小
     */
    @ApiModelProperty(value = "附件大小")
    private Long size;

    /**
     * 下载路径
     */
    @ApiModelProperty(value = "下载路径")
    private String path;

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


    public Attachment(String id, String name, Long size, String path, String creator) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.path = path;
        this.creator = creator;
    }

}
