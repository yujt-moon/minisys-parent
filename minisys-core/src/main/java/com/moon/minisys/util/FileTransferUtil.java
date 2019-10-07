package com.moon.minisys.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

/**
 * 文件传输工具（包括文件上传文件下载）
 * @author yujiangtao
 * @date 2018/4/27 11:53
 * @since 1.0
 */
public class FileTransferUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileTransferUtil.class);

    public static String fileUpload(InputStream in, String fileName, String destination) {
        // 边界检查
        if(in == null || StringUtils.isBlank(fileName) || StringUtils.isBlank(destination)) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        File outputFile = new File(destination);
        if(!outputFile.exists()) {
            logger.info("文件夹不存在，创建文件夹！");
            outputFile.mkdirs();
        }
        return null;
    }
}
