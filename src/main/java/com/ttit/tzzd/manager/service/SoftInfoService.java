package com.ttit.tzzd.manager.service;

import com.ttit.tzzd.manager.entity.SoftInfo;

/**
 * Description:软件信息
 *
 * @author 小谢
 * Date: 2019/5/2317:16
 */
public interface SoftInfoService {
    SoftInfo add(SoftInfo softInfo);

    SoftInfo findById(String id);
}
