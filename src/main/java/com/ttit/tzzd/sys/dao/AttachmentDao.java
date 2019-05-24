package com.ttit.tzzd.sys.dao;

import com.ttit.tzzd.sys.entity.Attachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2414:11
 */
public interface AttachmentDao {
    List<Attachment> searchPage(@Param("keyword") String keyword);

    Attachment findById(@Param("id") String id);

    Integer add(Attachment attachment);

/*    Integer del(@Param("id") String id);*/
}
