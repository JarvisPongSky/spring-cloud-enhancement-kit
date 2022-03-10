package com.pongsky.kit.storage.controller;

import com.pongsky.kit.config.SystemConfig;
import com.pongsky.kit.response.annotation.ResponseResult;
import com.pongsky.kit.storage.config.StorageConfig;
import com.pongsky.kit.utils.storage.StorageUtils;
import com.pongsky.kit.utils.storage.UploadFileInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 公共组件 by 云存储
 *
 * @author pengsenhao
 */
@Api(tags = "公共组件 by 云存储")
@Slf4j
@ResponseResult
@RestController
@RequiredArgsConstructor
@ConditionalOnProperty(value = "storage.is-enable-controller", havingValue = "true", matchIfMissing = true)
@RequestMapping(value = "${storage.controller-base-uri:/console/${application.module}/common/storage}")
public class ConsoleStorageController {

    private final SystemConfig systemConfig;
    private final StorageConfig storageConfig;

    /**
     * 文件上传
     *
     * @param file 文件
     * @return 文件访问路径
     * @throws IOException IOException
     * @author pengsenhao
     */
    @ApiOperation("文件上传")
    @PostMapping
    public UploadFileInfo upload(@RequestParam MultipartFile file) throws IOException {
        StorageUtils storageUtils = storageConfig.getUtils();
        if (storageUtils == null) {
            return null;
        }
        String fileName = storageUtils.buildFileName(file.getOriginalFilename(), systemConfig.getActive().name());
        String url = storageUtils.upload(fileName, file.getContentType(), file.getInputStream());
        return new UploadFileInfo(url);
    }

}
