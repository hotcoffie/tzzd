package com.ttit.tzzd.sys.common;

import com.ttit.tzzd.sys.entity.Dictionary;
import com.ttit.tzzd.sys.enums.DictTypeEnum;

import java.util.List;

/**
 * Description: 数据字典Redis版，先空着
 *
 * @author 小谢
 * Date: 2019/5/2210:47
 */
public class DictInRedis implements Dict {
    @Override
    public void init(List<Dictionary> list) {

    }

    @Override
    public String get(String type, String key) {
        return null;
    }

    @Override
    public String get(DictTypeEnum type, String key) {
        return null;
    }
}
