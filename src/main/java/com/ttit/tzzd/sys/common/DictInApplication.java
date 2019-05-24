package com.ttit.tzzd.sys.common;

import com.ttit.tzzd.sys.entity.Dictionary;
import com.ttit.tzzd.sys.enums.DictTypeEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2210:37
 */
public class DictInApplication implements Dict {
    /**
     * 码表
     */
    private Map<String, HashMap<String, String>> CodeData = new HashMap<>();

    @Override
    public void init(List<Dictionary> list) {
        list.forEach(dictionary -> {
            String type = dictionary.getType();
            HashMap<String, String> map;
            if (CodeData.containsKey(type)) {
                map = CodeData.get(type);
            } else {
                map = new HashMap<>();
                CodeData.put(type, map);
            }
            map.put(dictionary.getCode(), dictionary.getValue());
        });
    }

    @Override
    public String get(String type, String key) {
        if (CodeData == null) {
            return "";
        }

        Map<String, String> group = CodeData.get(type);
        if (group == null || group.isEmpty()) {
            return "";
        }
        String value = group.get(key);
        return value == null ? "" : value;
    }

    @Override
    public String get(DictTypeEnum type, String key) {
        return get(type.getCode(), key);
    }
}
