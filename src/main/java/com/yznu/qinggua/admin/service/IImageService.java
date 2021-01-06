package com.yznu.qinggua.admin.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.util.HashMap;

public interface IImageService {
    /**
     * 上传图片
     * @param file
     * @return
     * */
    HashMap<String, Object> upload(HttpServletRequest req, MultipartFile file);

    /**
     * 下载图片
     * @param filename
     * @return
     * */
    Resource download(String filename) throws MalformedURLException;
}
