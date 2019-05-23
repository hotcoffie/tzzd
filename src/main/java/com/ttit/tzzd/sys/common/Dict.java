package com.ttit.tzzd.sys.common;

import com.ttit.tzzd.sys.entity.Dictionary;
import com.ttit.tzzd.sys.enums.DictType;

import java.util.List;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2210:44
 */
public interface Dict {
    void init(List<Dictionary> list);

    String get(String type, String key);

    String get(DictType type, String key);
}
