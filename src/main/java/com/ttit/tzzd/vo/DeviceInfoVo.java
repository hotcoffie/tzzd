package com.ttit.tzzd.vo;

import com.ttit.tzzd.entity.DeviceInfo;
import lombok.*;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/228:32
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DeviceInfoVo extends DeviceInfo {
    private String deviceStatusName;
    private String isDelName;
}
