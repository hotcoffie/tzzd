package com.ttit.tzzd.manager.entity;

import com.ttit.tzzd.manager.enums.SysLogType;
import com.ttit.tzzd.sys.common.Constant;
import com.ttit.tzzd.sys.exceptions.BusinessException;
import com.ttit.tzzd.sys.utils.UuidUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 系统日志
 *
 * @author xiaoxie
 * Date: 2019/5/2110:22
 */
@Data
@NoArgsConstructor
@ApiModel(description = "系统日志")
public class SysLog implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 日志类别
     */
    @ApiModelProperty(value = "日志类别")
    private String sysLogType;

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

    public SysLog(String sysLogType, String content, String userId, Date createTime) {

        this.id = UuidUtils.generate();
        this.setSysLogType(sysLogType);
        this.content = content;
        this.creator = userId;
        this.createTime = createTime;
    }

    public void setSysLogType(String sysLogType) {
        SysLogType type = SysLogType.get(sysLogType);
        if (type == null) {
            throw new BusinessException("不存在编码[" + sysLogType + "]的设备日志类型！");
        }
        this.sysLogType = sysLogType;
    }
}
