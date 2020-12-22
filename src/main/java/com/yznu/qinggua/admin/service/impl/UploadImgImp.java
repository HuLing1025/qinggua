package com.yznu.qinggua.admin.service.impl;

import com.yznu.qinggua.admin.service.IUploadImg;
import com.yznu.qinggua.utils.Globle;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
public class UploadImgImp implements IUploadImg {
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
            result.put("message", "创建成功!");
            result.put("path", destFileName);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", e);
            return result;
        }
    }
}
