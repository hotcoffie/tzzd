package com.ttit.tzzd.sys.controller;

import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.sys.common.Constant;
import com.ttit.tzzd.sys.common.DictHadler;
import com.ttit.tzzd.sys.entity.Attachment;
import com.ttit.tzzd.sys.service.AttachmentService;
import com.ttit.tzzd.sys.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Description: 附件管理
 *
 * @author 小谢
 * Date: 2019/5/2317:10
 */
@RestController
@RequestMapping("/sys/atta")
@Slf4j
@Api(tags = "附件管理")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;
    @Resource
    private DictHadler dictHadler;

    @GetMapping("page")
    @ApiOperation(value = "分页查询附件列表", notes = "")
    public ResultVo searchPage(
            @ApiParam(value = "搜索关键字") @RequestParam(required = false) String keyword,
            @ApiParam(value = "当前页码，为空/小于0不分页", required = true, example = "1") @RequestParam Integer pageNum,
            @ApiParam(value = "每页容量，为空/小于0不分页", required = true, example = "10") @RequestParam Integer pageSize,
            @ApiParam(value = "排序方式，为空不排序") @RequestParam(required = false) String orderBy) {
        PageInfo page = attachmentService.searchPage(keyword, pageNum, pageSize, orderBy);
        return ResultVo.success(page);
    }


    @PostMapping("upload")
    @ApiOperation(value = "上传附件", notes = "")
    public ResultVo upload(@ApiParam(value = "上传的附件") MultipartFile webFile) {
        Attachment attachment = attachmentService.upload(webFile, Constant.USER_ID_ADMIN);
        return ResultVo.success(attachment);
    }

    @GetMapping("download/{id}")
    @ApiOperation(value = "下载软件", notes = "")
    public ResponseEntity<FileSystemResource> download(@ApiParam(value = "软件ID") @PathVariable String id) {
        return attachmentService.download(id);

    }

/*    @DeleteMapping("del/{id}")
    @ApiOperation(value = "从列表中逻辑删除指定设备分组", notes = "")
    public ResultVo del(@ApiParam(value = "设备分组ID") @PathVariable String id) {
        //用户体系暂未规划，这里默认用1
        Attachment attachment = attachmentService.del(id, Constant.USER_ID_ADMIN);
        return ResultVo.success(attachment);
    }*/
}
