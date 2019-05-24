package com.ttit.tzzd.manager.vo;

import com.ttit.tzzd.manager.entity.SoftManager;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2415:25
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(description = "软件管理")
public class SoftManagerVo extends SoftManager {
    @ApiModelProperty(value = "软件类型名称")
    private String typeName;

    @ApiModelProperty(value = "软件文件名称")
    private String softName;

}
