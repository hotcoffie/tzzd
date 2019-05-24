package com.ttit.tzzd.sys.service;

import com.ttit.tzzd.sys.dao.DictionaryDao;
import com.ttit.tzzd.sys.entity.Dictionary;
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

    @Override
    public List<Dictionary> listByType(String type) {
        return dictionaryDao.listByType(type);
    }

}
