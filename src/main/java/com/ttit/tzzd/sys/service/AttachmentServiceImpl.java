package com.ttit.tzzd.sys.service;

import com.github.pagehelper.PageInfo;
import com.ttit.tzzd.sys.common.Constant;
import com.ttit.tzzd.sys.dao.AttachmentDao;
import com.ttit.tzzd.sys.entity.Attachment;
import com.ttit.tzzd.sys.enums.SysLogTypeEnum;
import com.ttit.tzzd.sys.exceptions.BusinessException;
import com.ttit.tzzd.sys.exceptions.NotExistException;
import com.ttit.tzzd.sys.exceptions.NotNullException;
import com.ttit.tzzd.sys.utils.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author 小谢
 * Date: 2019/5/2414:10
 */
@Service
@Slf4j
public class AttachmentServiceImpl extends BaseService implements AttachmentService {
    @Resource
    private AttachmentDao attachmentDao;
    @Resource
    private SysLogService sysLogService;
    /**
     * 文件上传路径
     */
    @Value("${business.file-upload-path}")
    private String fileUploadPath;

    @Override
    public PageInfo searchPage(String keyword, Integer pageNum, Integer pageSize, String orderBy) {
        startPage(pageNum, pageSize, orderBy);
        List<Attachment> list = attachmentDao.searchPage(keyword);
        return new PageInfo<>(list);
    }

    @Override
    public Attachment upload(MultipartFile webFile, String userId) {
        //1.有效性验证
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
        Attachment attachment = new Attachment(id, fileName, webFile.getSize(), path, userId);
        attachmentDao.add(attachment);

        return attachment;
    }

    @Override
    public ResponseEntity<FileSystemResource> download(String id) {
        //1.获取附件信息
        Attachment attachment = getAttaWithCheck(id);
        FileSystemResource file = new FileSystemResource(attachment.getPath());

        //2.配置头文件
        String fileName = new String(attachment.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment;fileName=" + fileName);
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        //3.返回附件数据
        try {
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(file.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(file);
        } catch (IOException e) {
            log.error("文件下载失败！", e);
            throw new BusinessException("文件下载失败！");
        }
    }

//    @Override
//    public Attachment del(String id, String userId) {
//        //1.数据校验
//        Attachment attachment = getAttaWithCheck(id);
//
//        attachmentDao.del(id);
//        String content = "删除附件：" + attachment.toString();
//        Date now = new Date();
//        //记录系统日志
//        sysLogService.addLog(SysLogTypeEnum.del.getCode(), content, userId, now);
//
//        return attachment;
//    }

    /**
     * 获取附件信息并校验
     * 参数有误或附件不存在则抛出NotExistException
     *
     * @param id 附件ID
     * @return 附件信息
     */
    private Attachment getAttaWithCheck(String id) {
        if (StringUtils.isBlank(id)) {
            throw new NotNullException();
        }
        Attachment attachment = attachmentDao.findById(id);
        if (attachment == null || Constant.IS_DEL.equals(attachment.getIsDel())) {
            throw new NotExistException("附件ID:" + id);
        }
        return attachment;
    }
}
