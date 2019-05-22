package com.ttit.tzzd.service;

import com.ttit.tzzd.dao.DictionaryDao;
import com.ttit.tzzd.entity.Dictionary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:数据字典
 *
 * @author 小谢
 * Date: 2019/5/2115:12
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Resource
    private DictionaryDao dictionaryDao;

    @Override
    public List<Dictionary> listForDict() {
        return dictionaryDao.listForDict();
    }
}
