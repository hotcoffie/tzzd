package com.ttit.tzzd.manager.vo;

import com.ttit.tzzd.manager.entity.SoftInfo;
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
 * Date: 2019/5/2414:38
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(description = "软件信息")
public class SoftInfoVo extends SoftInfo {
    @ApiModelProperty(value = "下载地址")
    private String url;

    @ApiModelProperty(value = "软件名称")
    private String name;

    public SoftInfoVo(String id, String softType, String attaId, String creator,String url,String name) {
        super(id, softType, attaId, creator);
        this.url = url;
        this.name = name;
    }
}
