package com.yznu.qinggua.service.serviceImpl;

import com.yznu.qinggua.service.IImageService;
import com.yznu.qinggua.utils.Globle;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * <p>
 *  图片上传
 * </p>
 *
 * @author huling
 * @since 2020-12-22
 */
@Service
public class ImageServiceImp implements IImageService {
    @Override
    public HashMap<String, Object> upload(HttpServletRequest req, MultipartFile file) {
        HashMap<String, Object> result = new HashMap<>();
        if(file == null){
            result.put("flag", false);
            result.put("message", "文件为空!");
            return result;
        }
        try {
            // 根据时间戳重组文件名
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            // 文件保存路径
            String destFileName = Globle.FILEPATH + File.separator + fileName;
            File destFile = new File(destFileName);
            // 不存在文件夹则创建
            if(!destFile.getParentFile().exists()){
                boolean flag = destFile.getParentFile().mkdirs();
                if(!flag){
                    result.put("flag", false);
                    result.put("message", "无法创建文件夹!");
                    return result;
                }
            }
            // 复制文件到指定文件夹
            file.transferTo(destFile);
            result.put("flag", true);
            result.put("message", "上传成功!");
            result.put("path", destFileName);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", e);
            return result;
        }
    }

    @Override
    public Resource download(String filename) {
        try {
            Path path =  Paths.get(Globle.FILEPATH).toAbsolutePath().resolve(filename).normalize();
            UrlResource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            }
            return null;
        }catch (Exception e) {
            System.out.println("文件下载异常: " + e);
            return null;
        }
    }
}
