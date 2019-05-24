package com.ttit.tzzd.sys.common;

import com.ttit.tzzd.sys.entity.Dictionary;
import com.ttit.tzzd.sys.enums.DictTypeEnum;
import com.ttit.tzzd.sys.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:全局的码表工具，支持程序内建码表和基于Redis的码表
 * 但是由于时间有限，加上Redis版得注册成bean还得调试，先使用内建版
 *
 * @author 小谢
 * Date: 2019/5/2115:02
 */
@Component
@Scope("singleton")
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
    private static Dict dict;

    @Resource
    private DictionaryService dictionaryService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("初始化数据字典...");
        initDict();
    }

    /**
     * 初始化码表
     */
    private void initDict() {
        log.info("从数据库查询字典信息...");
        List<Dictionary> list = dictionaryService.listForDict();
        if (list == null || list.isEmpty()) {
            log.warn("未查询到字典信息！");
            return;
        }

        int len = list.size();
        log.info("共查询到{}条字典信息", len);
        if (Constant.DICT_SAVE_TYPE_REDIS.equals(saveType)) {
            dict = new DictInRedis();
        } else {
            dict = new DictInApplication();
        }
        dict.init(list);
        log.info("数据字典初始化完毕！");
    }

    public String get(String type, String key) {
        if (dict == null) {
            refresh();
        }
        if (dict == null) {
            return "";
        }
        return dict.get(type, key);
    }

    public String get(DictTypeEnum type, String key) {
        return get(type.getCode(), key);
    }

    public void refresh() {
        log.info("刷新数据字典...");
        initDict();
    }
}
