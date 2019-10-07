package com.moon.minisys.busi.controller;

import com.moon.minisys.constant.FilePrefixStrategy;
import com.moon.minisys.util.StringUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传控制器
 * @author yujiangtao
 * @date 2018/4/24 17:43
 */
@Controller
@RequestMapping(value = "/fileUpload")
public class FileUploadController {

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    /**
     * 跳转文件上传页面
     * @return
     */
    @RequestMapping("/toUploadPage")
    public String toUploadPage() {
        return "integration/fileUpload";
    }

    /**
     * 文件上传
     * @param files
     * @param request
     * @return
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam MultipartFile[] files, HttpServletRequest request) {
        String filePath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
        filePath = "C://tmp";

        //如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
        //如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
        //并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
        for(MultipartFile file : files) {
            if(file.isEmpty()) {
                logger.debug("文件未上传");
            } else {
                logger.info("文件长度：{}，文件类型：{}，文件名称：{}，文件原名：{}",
                        new Object[]{file.getSize(), file.getContentType(), file.getName(), file.getOriginalFilename()});
                String fileName = StringUtils.generateFilePrefix(FilePrefixStrategy.DAYS) + "_" + file.getOriginalFilename();
                try {
                    // 不需要主动关闭流
                    FileUtils.copyInputStreamToFile(file.getInputStream(),
                            new File(filePath, fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "success/success";
    }

    /**
     * 文件上传
     * @param files
     * @param request
     * @return
     */
    @RequestMapping("/upload2")
    @ResponseBody
    public Map<String, Object> upload2(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        String filePath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
        filePath = "C://tmp";

        //如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
        //如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
        //并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
        for(MultipartFile f : files) {
            if(f.isEmpty()) {
                logger.debug("文件未上传");
            } else {
                logger.info("文件长度：{}，文件类型：{}，文件名称：{}，文件原名：{}",
                        new Object[]{f.getSize(), f.getContentType(), f.getName(), f.getOriginalFilename()});
                String fileName = StringUtils.generateFilePrefix(FilePrefixStrategy.DAYS) + "_" + f.getOriginalFilename();
                try {
                    // 不需要主动关闭流
                    FileUtils.copyInputStreamToFile(f.getInputStream(),
                            new File(filePath, fileName));
                    result.put("isSuccess", true);
                    result.put("fileName", fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
