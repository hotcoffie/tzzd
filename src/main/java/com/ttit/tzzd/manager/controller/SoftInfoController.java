package com.ttit.tzzd.manager.controller;

import com.ttit.tzzd.manager.entity.SoftInfo;
import com.ttit.tzzd.manager.service.SoftInfoService;
import com.ttit.tzzd.sys.common.Constant;
import com.ttit.tzzd.sys.common.DictHadler;
import com.ttit.tzzd.sys.enums.DictTypeEnum;
import com.ttit.tzzd.sys.exceptions.BusinessException;
import com.ttit.tzzd.sys.exceptions.NotExistException;
import com.ttit.tzzd.sys.exceptions.NotNullException;
import com.ttit.tzzd.sys.utils.UuidUtils;
import com.ttit.tzzd.sys.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Description: 软件信息
 *
 * @author 小谢
 * Date: 2019/5/2317:10
 */
@Controller
@RequestMapping("/manager/soft/info")
@Slf4j
@Api(tags = "软件信息")
public class SoftInfoController {

    @Autowired
    private SoftInfoService softInfoService;
    @Resource
    private DictHadler dictHadler;
    @Value("${business.file-upload-path}")
    private String fileUploadPath;

    @PostMapping("upload")
    @ResponseBody
    @ApiOperation(value = "上传软件", notes = "")
    public ResultVo upload(@ApiParam(value = "软件所属类型编码") @RequestParam String softType,
                           @ApiParam(value = "上传的软件") MultipartFile webFile) {
        //1.有效性验证
        if (StringUtils.isBlank(softType)) {
            throw new NotNullException();
        }
        String typeName = dictHadler.get(DictTypeEnum.softType, softType);
        if (StringUtils.isBlank(typeName)) {
            throw new BusinessException("系统没有指定的软件类型！");
        }
        if (webFile.isEmpty()) {
            throw new NotExistException("上传了空文件");
        }

        //2.上传文件
        String fileName = webFile.getOriginalFilename();
        String id = UuidUtils.generate();
        String path = fileUploadPath + id;

        File dir = new File(fileUploadPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File localFile = new File(dir, id);

        try (
                InputStream in = webFile.getInputStream();
                FileOutputStream out = new FileOutputStream(localFile)
        ) {
            FileCopyUtils.copy(in, out);
        } catch (IOException e) {
            log.error("附件上传失败！", e);
            throw new BusinessException("附件上传失败！");
        }

        //3.持久化
        SoftInfo info = new SoftInfo(id, fileName, path, Constant.USER_ID_ADMIN);
        info = softInfoService.add(info);
        return ResultVo.success(info);
    }

    @GetMapping("download/{id}")
    @ApiOperation(value = "下载软件", notes = "")
    public ResponseEntity<FileSystemResource> download(@ApiParam(value = "软件ID") @PathVariable String id) {
        //1.获取附件信息
        SoftInfo info = softInfoService.findById(id);
        if (info == null) {
            throw new NotExistException("附件ID：" + id);
        }
        FileSystemResource file = new FileSystemResource(info.getPath());
        if (!file.exists()) {
            throw new NotExistException("附件ID：" + id);
        }
        String fileName = new String(info.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment;fileName=" + fileName);
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        try {
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(file.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(file);
        } catch (IOException e) {
            log.error("附件下载失败！", e);
            throw new BusinessException("附件下载失败！");
        }
    }
}
