package com.ttit.tzzd.sys.utils;

import com.ttit.tzzd.sys.exceptions.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2313:20
 */
public class ExceptionAnalysisTools {
    public static void DuplicateKeyExceptionAnalyse(DuplicateKeyException e){
        String msg = e.getMessage();
        if (StringUtils.isNotBlank(msg)) {
            System.out.println(msg);
            Pattern pattern = Pattern.compile("Duplicate entry '(\\w+)' for key");
            Matcher matcher = pattern.matcher(msg);
            if (matcher.find()) {
                String code = matcher.group(1);
                throw new BusinessException("设备信息[" + code + "]已存在，请勿重复注册！");
            }
        }
        throw new BusinessException("数据库操作失败！");
    }
}
