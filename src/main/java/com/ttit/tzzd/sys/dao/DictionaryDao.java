package com.ttit.tzzd.sys.dao;

import com.ttit.tzzd.sys.entity.Dictionary;

import java.util.List;

/**
 * Description:数据字典
 *
 * @author 小谢
 * Date: 2019/5/2115:13
 */
public interface DictionaryDao {
    List<Dictionary> listForDict();
}
