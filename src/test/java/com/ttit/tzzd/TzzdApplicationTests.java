package com.ttit.tzzd;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@RunWith(SpringRunner.class)
//@SpringBootTest

public class TzzdApplicationTests {

    @Test
    public void contextLoads() {
        String str = "### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'TLM1001516010002' for key 'unq_device_info_serial_num'\n" +
                "### The error may exist in file [E:\\Projects\\tzzd\\target\\classes\\mapper\\DeviceInfoMapper.xml]\n" +
                "### The error may involve com.ttit.tzzd.manager.dao.DeviceInfoDao.add-Inline\n" +
                "### The error occurred while setting parameters\n" +
                "### SQL: INSERT INTO device_info(id,                                 serial_num,                                 serial_code,                                 notice_time,                                 dict_device_status,                                 group_code,                                 owner_name,                                 owner_phone,                                 remark,                                 create_time,                                 is_del)         values (?,                 ?,                 ?,                 now(),                 ?,                 ?,                 ?,                 ?,                 ?,                 now(),                 ?)\n" +
                "### Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'TLM1001516010002' for key 'unq_device_info_serial_num'\n" +
                "; Duplicate entry 'TLM1001516010002' for key 'unq_device_info_serial_num'; nested exception is java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'TLM1001516010002' for key 'unq_device_info_serial_num'\n";
        // 邮箱验证规则
        String regEx = "Duplicate entry ('\\w+') for key";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()){
            System.out.println(matcher.group(1));
        }
    }
}
