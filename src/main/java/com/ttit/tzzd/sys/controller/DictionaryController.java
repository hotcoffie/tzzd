package com.ttit.tzzd.sys.controller;

import com.ttit.tzzd.sys.common.DictHadler;
import com.ttit.tzzd.sys.entity.Dictionary;
import com.ttit.tzzd.sys.service.DictionaryService;
import com.ttit.tzzd.sys.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private DictionaryService dictionaryService;

    @PostMapping("refresh")
    @ApiOperation(value = "刷新字典数据", notes = "")
    public ResultVo refresh() {
        dictHadler.refresh();
        return ResultVo.success("OK");
    }

    @GetMapping("list")
    @ApiOperation(value = "查询字典列表，主要用于下拉菜单", notes = "")
    public ResultVo list(@ApiParam(value = "字典类型编码，不输入则返回全部") @RequestParam(required = false) String type) {
        List<Dictionary> list = dictionaryService.list(type);
        return ResultVo.success(list);
    }

}
