package com.ttit.tzzd.sys.service;

import com.github.pagehelper.PageHelper;
import com.ttit.tzzd.sys.common.DictHadler;
import com.ttit.tzzd.sys.exceptions.BusinessException;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * Description: 基础的Service，提供一些公共方法
 *
 * @author 小谢
 * Date: 2019/5/2115:51
 */
public class BaseService {

    @Resource
    private DictHadler dictHadler;

    /**
     * 配置分页查询
     *
     * @param pageNum  当前页码，为空/小于0不分页
     * @param pageSize 每页容量，为空/小于0不分页
     * @param orderBy  排序方式，为空不排序
     */
    protected void startPage(Integer pageNum, Integer pageSize, String orderBy) {
        if (pageNum != null && pageNum > 0
                && pageSize != null && pageSize > 0) {
            if (StringUtils.isBlank(orderBy)) {
                PageHelper.startPage(pageNum, pageSize);
            } else {
                PageHelper.startPage(pageNum, pageSize, orderBy);
            }
        } else {
            throw new BusinessException("分页查询失败：输入的分页信息有误！");
        }
    }

}
