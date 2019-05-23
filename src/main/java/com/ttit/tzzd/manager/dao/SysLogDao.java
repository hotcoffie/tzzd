package com.ttit.tzzd.manager.dao;

import com.ttit.tzzd.manager.entity.SysLog;
import com.ttit.tzzd.manager.vo.SysLogVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: 系统日志
 *
 * @author 小谢
 * Date: 2019/5/2310:05
 */
public interface SysLogDao {
    List<SysLogVo> searchPage(@Param("sysLogType") String sysLogType, @Param("keyword") String keyword);

    SysLogVo findById(@Param("id") String id);

    Integer add(SysLog log);

    Integer del(@Param("id") String id);

    Integer update(SysLog sysLogVo);

}
