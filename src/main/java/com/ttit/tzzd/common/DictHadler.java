package com.ttit.tzzd.common;

import com.ttit.tzzd.entity.Dictionary;
import com.ttit.tzzd.enums.DictType;
import com.ttit.tzzd.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:全局的码表工具，支持程序内建码表和基于Redis的码表
 * 但是由于时间有限，加上Redis版很容易实现，这里先实现内建码表
 *
 * @author 小谢
 * Date: 2019/5/2115:02
 */
@Component
@Slf4j
public class DictHadler implements ApplicationListener<ContextRefreshedEvent> {
    /**
     * 读取码表缓存方式
     */
    @Value("${business.dict.save-type}")
    private String saveType;

    /**
     * 码表
     */
    private static Map<String, HashMap<String, String>> dict;

    private final DictionaryService dictionaryService;

    public DictHadler(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initDict();
    }

    /**
     * 初始化码表
     */
    private void initDict() {
        log.info("开始初始化数据字典");
        List<Dictionary> list = dictionaryService.listForDict();
        if (list == null || list.isEmpty()) {
            log.warn("未查询到字典信息！");
            return;
        }

        int len = list.size();
        log.info("共查询到{}字典信息", len);
        if (dict == null) {
            dict = new HashMap<>();
        }
        list.forEach(dictionary -> {
            String type = dictionary.getType();
            HashMap<String, String> map;
            if (dict.containsKey(type)) {
                map = dict.get(type);
            } else {
                map = new HashMap<>();
                dict.put(type, map);
            }
            map.put(dictionary.getCode(), dictionary.getValue());
        });
        log.info("数据字典初始化完毕");
    }

    public static String get(String type, String key) {
        if (dict == null) {
            return "";
        }

        Map<String, String> group = dict.get(type);
        if (group == null || group.isEmpty()) {
            return "";
        }
        String value = group.get(key);
        return value == null ? "" : value;
    }

    public static String get(DictType type, String key) {
        return get(type.getCode(), key);
    }
}
