package com.ttit.tzzd.manager.service;

import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.manager.entity.SoftInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:软件信息
 *
 * @author 小谢
 * Date: 2019/5/2317:16
 */
public interface SoftInfoService {
    SoftInfo add(String softType, MultipartFile webFile, String userId);

    SoftInfo del(String id, String userId);

    PageInfo searchPage(String softType, String keyword, Integer pageNum, Integer pageSize, String orderBy);

}
