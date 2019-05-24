package com.ttit.tzzd.manager.controller;

import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.manager.entity.SoftInfo;
import com.ttit.tzzd.manager.service.SoftInfoService;
import com.ttit.tzzd.sys.common.Constant;
import com.ttit.tzzd.sys.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description: 软件信息
 *
 * @author 小谢
 * Date: 2019/5/2317:10
 */
@RestController
@RequestMapping("/manager/soft/info")
@Slf4j
@Api(tags = "业务-软件信息")
public class SoftInfoController {

    @Autowired
    private SoftInfoService softInfoService;

    @GetMapping("page")
    @ApiOperation(value = "分页查询文件列表", notes = "")
    public ResultVo searchPage(
            @ApiParam(value = "软件所属类型编码") @RequestParam(required = false) String softType, @ApiParam(value = "搜索关键字") @RequestParam(required = false) String keyword,
            @ApiParam(value = "当前页码，为空/小于0不分页", required = true, example = "1") @RequestParam Integer pageNum,
            @ApiParam(value = "每页容量，为空/小于0不分页", required = true, example = "10") @RequestParam Integer pageSize,
            @ApiParam(value = "排序方式，为空不排序") @RequestParam(required = false) String orderBy) {
        PageInfo page = softInfoService.searchPage(softType, keyword, pageNum, pageSize, orderBy);
        return ResultVo.success(page);
    }

    @PostMapping("upload")
    @ApiOperation(value = "上传软件", notes = "")
    public ResultVo upload(@ApiParam(value = "软件所属类型编码") @RequestParam String softType,
                           @ApiParam(value = "上传的软件") MultipartFile webFile) {
        SoftInfo info = softInfoService.add(softType, webFile, Constant.USER_ID_ADMIN);
        return ResultVo.success(info);
    }


    @DeleteMapping("del/{id}")
    @ApiOperation(value = "从列表中逻辑删除指定设备分组", notes = "")
    public ResultVo del(@ApiParam(value = "设备分组ID") @PathVariable String id) {
        //用户体系暂未规划，这里默认用1
        SoftInfo info = softInfoService.del(id, Constant.USER_ID_ADMIN);
        return ResultVo.success(info);
    }
}
