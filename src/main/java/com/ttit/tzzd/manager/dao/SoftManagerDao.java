package com.ttit.tzzd.manager.dao;

import com.ttit.tzzd.manager.entity.SoftManager;
import com.ttit.tzzd.manager.vo.SoftManagerVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2415:42
 */
public interface SoftManagerDao {
    List<SoftManagerVo> list();

    SoftManager findById(@Param("id") String id);

    List<SoftManager> findBySoftType(@Param("softType") String softType);

    Integer add(SoftManager softManager);

    Integer del(@Param("id") String id);

    Integer modify(@Param("id") String id, @Param("softId") String softId, @Param("version") String version);

}
