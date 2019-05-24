package com.ttit.tzzd.sys.controller;

import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.sys.service.SysLogService;
import com.ttit.tzzd.sys.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2417:18
 */
@RestController
@RequestMapping("/sys/log")
@Api(tags = "系统-操作日志")
public class SysLogController {
    @Resource
    private SysLogService sysLogService;

    @GetMapping("page")
    @ApiOperation(value = "分页查询系统日志列表", notes = "")
    public ResultVo searchPage(@ApiParam(value = "日志类型编码") @RequestParam(required = false) String sysLogType,
                               @ApiParam(value = "搜索关键字") @RequestParam(required = false) String keyword,
                               @ApiParam(value = "当前页码，为空/小于0不分页", required = true, example = "1") @RequestParam Integer pageNum,
                               @ApiParam(value = "每页容量，为空/小于0不分页", required = true, example = "10") @RequestParam Integer pageSize,
                               @ApiParam(value = "排序方式，为空不排序") @RequestParam(required = false) String orderBy) {
        PageInfo page = sysLogService.searchPage(sysLogType, keyword, pageNum, pageSize, orderBy);
        return ResultVo.success(page);
    }
}
