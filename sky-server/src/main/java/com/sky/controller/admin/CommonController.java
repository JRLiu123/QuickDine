package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * ClassName: CommonController
 * Package: com.sky.controller.admin
 * Description:
 * General interface
 * @Author Jingran Liu
 * @Create 2025/1/14 20:19
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "General interface")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;
    /**
     * upload file
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("Upload file")
    public Result<String> upload(MultipartFile file){
        log.info("{} uploading...", file);
        try {
            // 1. get original file name
            String originalFilename = file.getOriginalFilename();

            // 2. Extract the suffix of the original file name.
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            // 3. new file name
            String objectName = UUID.randomUUID().toString() + extension;

            String filePath = aliOssUtil.upload(file.getBytes(), objectName);

            return Result.success(filePath);
        } catch (IOException e) {
            log.error("File upload failed: {}", e);

        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
