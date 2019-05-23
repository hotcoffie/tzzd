package com.ttit.tzzd.manager.entity;

import com.ttit.tzzd.sys.common.Constant;
import com.ttit.tzzd.manager.enums.DevLogType;
import com.ttit.tzzd.sys.exceptions.BusinessException;
import com.ttit.tzzd.manager.exceptions.DeviceException;
import com.ttit.tzzd.sys.utils.UuidUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 设备日志
 *
 * @author xiaoxie
 * Date: 2019/5/2110:22
 */
@Data
@NoArgsConstructor
@ApiModel(description = "设备日志")
public class DeviceLog implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 设备串码
     */
    @ApiModelProperty(value = "设备串码")
    private String devSerialNum;

    /**
     * (字典)日志类型
     */
    @ApiModelProperty(value = "(字典)日志类型")
    private String devLogType;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private String creator;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 删除标识:1删除0未删除
     */
    @ApiModelProperty(value = "删除标识:1删除0未删除")
    private String isDel = Constant.IS_NOT_DEL;

    public DeviceLog(DeviceException e) {
        this.id = UuidUtils.generate();
        this.devSerialNum = e.getSerialNum();
        this.devLogType = DevLogType.error.getCode();
        this.content = e.getMessage();
        this.createTime = e.getTime();
    }

    public DeviceLog(String devSerialNum, String devLogType, String content, Date createTime) {

        this.id = UuidUtils.generate();
        this.devSerialNum = devSerialNum;
        this.setDevLogType(devLogType);
        this.content = content;
        this.createTime = createTime;
    }

    public void setDevLogType(String devLogType) {
        DevLogType type = DevLogType.get(devLogType);
        if (type == null) {
            throw new BusinessException("不存在编码[" + devLogType + "]的设备日志类型！");
        }
        this.devLogType = devLogType;
    }
}
