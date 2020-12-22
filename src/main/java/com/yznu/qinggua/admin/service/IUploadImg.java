package com.yznu.qinggua.admin.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface IUploadImg {
    public HashMap<String, Object> upload(HttpServletRequest req, MultipartFile file);
}
