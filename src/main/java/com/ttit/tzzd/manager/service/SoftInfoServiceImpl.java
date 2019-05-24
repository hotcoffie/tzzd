package com.ttit.tzzd.manager.service;

import com.ttit.tzzd.manager.dao.SoftInfoDao;
import com.ttit.tzzd.manager.entity.SoftInfo;
import com.ttit.tzzd.sys.exceptions.NotNullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Description:软件信息
 *
 * @author 小谢
 * Date: 2019/5/2317:17
 */
@Service
public class SoftInfoServiceImpl implements SoftInfoService {
    @Resource
    private SoftInfoDao softInfoDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SoftInfo add(SoftInfo softInfo) {
        softInfoDao.add(softInfo);
        return softInfoDao.findById(softInfo.getId());
    }

    @Override
    public SoftInfo findById(String id) {
        if (StringUtils.isBlank(id)) {
            throw new NotNullException();
        }
        return softInfoDao.findById(id);
    }
}
