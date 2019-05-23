package com.ttit.tzzd.manager.entity;

import com.ttit.tzzd.sys.common.Constant;
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
@ApiModel(description = "设备信息")
public class DeviceInfo implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 序列号
     */
    @ApiModelProperty(value = "序列号")
    private String serialNum;

    /**
     * 串码
     */
    @ApiModelProperty(value = "串码")
    private String serialCode;

    /**
     * 通告时间
     */
    @ApiModelProperty(value = "通告时间")
    private Date noticeTime;

    /**
     * (字典)设备状态
     */
    @ApiModelProperty(value = "(字典)设备状态")
    private String deviceStatus;

    /**
     * 分组ID
     */
    @ApiModelProperty(value = "分组ID")
    private String groupId = "1";

    /**
     * 业主姓名
     */
    @ApiModelProperty(value = "业主姓名")
    private String ownerName;

    /**
     * 业主电话
     */
    @ApiModelProperty(value = "业主电话")
    private String ownerPhone;

    /**
     * 地址/备注
     */
    @ApiModelProperty(value = "地址/备注")
    private String remark;

    /**
     * 创建人
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
        return "DeviceInfo{" +
                "id='" + id + '\'' +
                ", serialNum='" + serialNum + '\'' +
                ", serialCode='" + serialCode + '\'' +
                ", noticeTime=" + noticeTime +
                ", deviceStatus='" + deviceStatus + '\'' +
                ", groupId='" + groupId + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                ", remark='" + remark + '\'' +
                ", creator='" + creator + '\'' +
                ", isDel='" + isDel + '\'' +
                '}';
    }
}
