package com.ttit.tzzd.vo;

import com.ttit.tzzd.common.BusinessException;
import com.ttit.tzzd.common.Constant;
import lombok.Data;

import java.io.Serializable;

/**
 * Description: 封装的返回结果
 *
 * @author 小谢
 * Date: 2019/5/2016:30
 */
@Data
public class ResultVo implements Serializable {
    private String code;
    private Object date;
    private String message;

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
