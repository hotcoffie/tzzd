package com.ttit.tzzd.sys.vo;

import com.ttit.tzzd.sys.common.BusinessException;
import com.ttit.tzzd.sys.common.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Description: 封装的返回结果
 *
 * @author 小谢
 * Date: 2019/5/2016:30
 */
@Data
@ApiModel(description = "通用返回对象")
public class ResultVo implements Serializable {
    @ApiModelProperty(value = "消息编码：success成功，errer失败")
    private String code;
    @ApiModelProperty(value = "系统消息")
    private String message;
    @ApiModelProperty(value = "具体返回信息")
    private Object date;

    public static ResultVo success(Object date) {
        ResultVo vo = new ResultVo();

        vo.setCode(Constant.SUCCESS);
        vo.setDate(date);

        return vo;
    }

    public static ResultVo error(String msg) {
        ResultVo vo = new ResultVo();

        vo.setCode(Constant.ERROR);
        vo.setMessage(msg);

        return vo;
    }

    /**
     * 将异常信息完整返回前台，这种接口仅在spring.profiles.active=test时生效，便于测试阶段定位问题
     */
    public static ResultVo error(String msg, Exception e) {
        ResultVo vo = new ResultVo();

        vo.setCode(Constant.ERROR);
        vo.setMessage(msg);
        vo.setDate(e);

        return vo;
    }

    public static ResultVo error(BusinessException e) {
        ResultVo vo = new ResultVo();

        vo.setCode(Constant.ERROR);
        vo.setMessage(e.getMessage());

        return vo;
    }
}
