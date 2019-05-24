package com.ttit.tzzd.manager.dao;

import com.ttit.tzzd.manager.entity.SoftInfo;
import com.ttit.tzzd.manager.vo.SoftInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:软件信息
 *
 * @author 小谢
 * Date: 2019/5/2317:44
 */
public interface SoftInfoDao {

    List<com.ttit.tzzd.manager.vo.SoftInfoVo> searchPage(@Param("softType") String softType, @Param("keyword") String keyword);

    SoftInfoVo findById(@Param("id") String id);

    Integer add(SoftInfo softInfo);

    Integer del(@Param("id") String id);
}
