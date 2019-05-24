package com.ttit.tzzd.sys.service;

import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.sys.entity.Attachment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2414:09
 */
public interface AttachmentService {
    PageInfo searchPage(String keyword, Integer pageNum, Integer pageSize, String orderBy);

    Attachment upload(MultipartFile webFile,String userId);

    ResponseEntity<FileSystemResource> download(String id);

/*    Attachment del(String id, String userIdAdmin);*/
}
