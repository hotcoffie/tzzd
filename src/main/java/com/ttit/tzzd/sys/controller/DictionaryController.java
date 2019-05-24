package com.ttit.tzzd.sys.controller;

import com.ttit.tzzd.sys.common.DictHadler;
import com.ttit.tzzd.sys.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: 数据字典
 *
 * @author 小谢
 * Date: 2019/5/2415:13
 */
@RestController
@RequestMapping("/sys/dict")
@Slf4j
@Api(tags = "系统-数据字典")
public class DictionaryController {
    @Resource
    private DictHadler dictHadler;

    @PostMapping("refresh")
    @ApiOperation(value = "刷新字典数据", notes = "")
    public ResultVo refresh() {
        dictHadler.refresh();
        return ResultVo.success("OK");
    }
}
