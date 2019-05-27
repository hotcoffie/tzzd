package com.ttit.tzzd.manager.controller;

import com.ttit.tzzd.manager.entity.SoftManager;
import com.ttit.tzzd.manager.service.SoftManagerService;
import com.ttit.tzzd.manager.vo.SoftManagerVo;
import com.ttit.tzzd.sys.common.Constant;
import com.ttit.tzzd.sys.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Description: 管理-软件管理
 *
 * @author 小谢
 * Date: 2019/5/2415:22
 */
@RestController
@RequestMapping("/manager/soft/manager")
@Slf4j
@Api(tags = "管理-软件管理")
public class SoftManagerController {
    @Autowired
    private SoftManagerService softManagerService;

    @GetMapping("list")
    @ApiOperation(value = "软件管理信息列表", notes = "")
    public ResultVo list() {
        List<SoftManagerVo> list = softManagerService.list();
        return ResultVo.success(list);
    }

    @PutMapping("add")
    @ApiOperation(value = "新增软件管理信息", notes = "")
    public ResultVo add(@RequestBody SoftManager softManager) {
        SoftManager softManagerNew = softManagerService.add(softManager, Constant.USER_ID_ADMIN);
        return ResultVo.success(softManagerNew);
    }

    @DeleteMapping("del/{id}")
    @ApiOperation(value = "从列表中逻辑删除指定软件管理信息", notes = "")
    public ResultVo del(@ApiParam(value = "软件分类ID") @PathVariable String id) {
        //用户体系暂未规划，这里默认用1
        SoftManager softManager = softManagerService.del(id, Constant.USER_ID_ADMIN);
        return ResultVo.success(softManager);
    }

    @PostMapping("modify")
    @ApiOperation(value = "修改软件版本管理信息", notes = "")
    public ResultVo modify(@ApiParam(value = "软件分类ID") @RequestParam String id,
                           @ApiParam(value = "对应软件ID") @RequestParam String softId,
                           @ApiParam(value = "软件版本号") @RequestParam String version) {
        //用户体系暂未规划，这里默认用1
        SoftManager softManagerNew = softManagerService.modify(id, softId, version, Constant.USER_ID_ADMIN);
        return ResultVo.success(softManagerNew);
    }

    @GetMapping("update")
    @ApiOperation(value = "软件更新，获取最新版软件信息", notes = "")
    public ResultVo updateInfo() {
        List<Map<String, String>> info = softManagerService.updateInfo();
        return ResultVo.success(info);
    }
}
