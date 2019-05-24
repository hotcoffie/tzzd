package com.ttit.tzzd.manager.dao;

import com.ttit.tzzd.manager.entity.SoftInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:软件信息
 *
 * @author 小谢
 * Date: 2019/5/2317:44
 */
public interface SoftInfoDao {

    List<SoftInfo> searchPage(@Param("keyword") String keyword);

    SoftInfo findById(@Param("id") String id);

    Integer add(SoftInfo softInfo);

    Integer del(@Param("id") String id);

    Integer modify(SoftInfo softInfo);
}
