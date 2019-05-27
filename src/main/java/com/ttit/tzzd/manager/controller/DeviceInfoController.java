package com.ttit.tzzd.manager.controller;

import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.manager.entity.DeviceInfo;
import com.ttit.tzzd.manager.service.DeviceInfoService;
import com.ttit.tzzd.manager.vo.DevReportVo;
import com.ttit.tzzd.manager.vo.DeviceInfoVo;
import com.ttit.tzzd.sys.common.Constant;
import com.ttit.tzzd.sys.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Description:管理-设备信息
 *
 * @author 小谢
 * Date: 2019/5/2113:50
 */
@RestController
@RequestMapping("/manager/device/info")
@Api(tags = "管理-设备信息")
public class DeviceInfoController {

    @Resource
    private DeviceInfoService deviceInfoService;

    @GetMapping("page")
    @ApiOperation(value = "分页查询设备信息列表", notes = "")
    public ResultVo searchPage(@ApiParam(value = "分组ID") @RequestParam(required = false) String groupId,
                               @ApiParam(value = "搜索关键字") @RequestParam(required = false) String keyword,
                               @ApiParam(value = "当前页码，为空/小于0不分页", required = true, example = "1") @RequestParam Integer pageNum,
                               @ApiParam(value = "每页容量，为空/小于0不分页", required = true, example = "10") @RequestParam Integer pageSize,
                               @ApiParam(value = "排序方式，为空不排序") @RequestParam(required = false) String orderBy) {
        PageInfo page = deviceInfoService.searchPage(groupId, keyword, pageNum, pageSize, orderBy);
        return ResultVo.success(page);
    }

    @PutMapping("regist")
    @ApiOperation(value = "注册新的设备", notes = "")
    public ResultVo regist(@RequestBody DeviceInfo deviceInfo) {
        DeviceInfoVo info = deviceInfoService.regist(deviceInfo, Constant.USER_ID_ADMIN);
        return ResultVo.success(info);
    }

    @DeleteMapping("del/{id}")
    @ApiOperation(value = "从列表中逻辑删除指定设备", notes = "")
    public ResultVo del(@ApiParam(value = "设备ID", required = true) @PathVariable String id) {
        //用户体系暂未规划，这里默认用1
        DeviceInfoVo deviceInfoVo = deviceInfoService.del(id, Constant.USER_ID_ADMIN);
        return ResultVo.success(deviceInfoVo);
    }

    @PostMapping("modify")
    @ApiOperation(value = "修改设备信息，只提供业主信息修改", notes = "")
    public ResultVo modify(@ApiParam(value = "设备信息") @RequestBody DeviceInfo deviceInfo) {
        DeviceInfoVo info = deviceInfoService.modify(deviceInfo, Constant.USER_ID_ADMIN);
        return ResultVo.success(info);
    }

    @PostMapping("modify/group")
    @ApiOperation(value = "修改设备信息，只提供业主信息修改", notes = "")
    public ResultVo modifyGroup(@ApiParam(value = "设备ID", required = true) @RequestParam String id,
                                @ApiParam(value = "设备分组ID", required = true) @RequestParam String groupId) {
        DeviceInfoVo info = deviceInfoService.modifyGroup(id, groupId, Constant.USER_ID_ADMIN);
        return ResultVo.success(info);
    }

    @PostMapping("report")
    @ApiOperation(value = "设备上报,可以向服务器提交在线/下线状态或异常日志，限定时间内未提交在线日志的，判定为下线", notes = "")
    public ResultVo report(@ApiParam(value = "设备ID", required = true) @RequestParam String id,
                           @ApiParam(value = "上报信息", required = true) @RequestParam DevReportVo reportVo) {
        deviceInfoService.report(reportVo);
        return ResultVo.success("OK");
    }

    @PostMapping("legalize")
    @ApiOperation(value = "设备认证", notes = "")
    public ResultVo legalize(@ApiParam(value = "设备ID", required = true) @RequestParam String id,
                             @ApiParam(value = "序列号", required = true) @RequestParam String serialNum,
                             @ApiParam(value = "串码", required = true) @RequestParam String serialCode) {
        deviceInfoService.legalize(id, serialNum, serialCode);
        return ResultVo.success("OK");
    }
}
