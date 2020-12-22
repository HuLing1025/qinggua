package com.yznu.qinggua.admin.controller;

import com.yznu.qinggua.admin.service.IUploadImg;
import com.yznu.qinggua.utils.ResponseUtil;
import com.yznu.qinggua.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    IUploadImg iUploadImg;

    @PostMapping("/uploadimg")
    public Result uploadImage(HttpServletRequest req, @RequestParam("file") MultipartFile file) {
        HashMap<String,Object> result = iUploadImg.upload(req, file);
        if((boolean) result.get("flag")){
            return ResponseUtil.success(result.get("path"), 200, (String)result.get("message"));
        }else{
            return ResponseUtil.error(500, (String)result.get("message"));
        }
    }
}
