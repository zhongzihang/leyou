package com.leyou.upload.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif");//记录文件允许类型

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);//记录日志

    public String upload(MultipartFile file){

        /**
         * 1. 校验文件大小
         2. 校验文件的媒体类型
         3. 校验文件的内容
         */
        String originalFilename = file.getOriginalFilename();
        // 检验文件类型
        String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)){
            LOGGER.info("文件类型不合法：{}", originalFilename);
            return null;
        }

        try {
            // 检验文件内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null){
                LOGGER.info("文件内容不合法：{}", originalFilename);
                return null;
            }
            // 保存到服务器
            file.transferTo(new File("E:\\javapro\\utils" + originalFilename));

            // 生成url地址，返回
            return "http://image.leyou.com/" + originalFilename;
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}", originalFilename);
            e.printStackTrace();
        }

        return null;
    }
}
