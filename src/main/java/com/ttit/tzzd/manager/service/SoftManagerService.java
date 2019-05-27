package com.ttit.tzzd.manager.service;

import com.ttit.tzzd.manager.entity.SoftManager;
import com.ttit.tzzd.manager.vo.SoftManagerVo;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2415:24
 */
public interface SoftManagerService {
    List<SoftManagerVo> list();

    SoftManager add(SoftManager softManager, String userId);

    SoftManager del(String id, String userId);

    List<Map<String, String>> updateInfo();

    SoftManager modify(String id, String softId, String version, String userId);
}
