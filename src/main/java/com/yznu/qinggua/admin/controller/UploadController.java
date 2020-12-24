package com.yznu.qinggua.admin.controller;

import com.yznu.qinggua.admin.service.IUploadImg;
import com.yznu.qinggua.utils.ResponseUtil;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huling
 * @since 2020-12-23
 */
@Api(tags = "文件上传接口")
@RestController
@RequestMapping("/uoload")
public class UploadController {

    @Autowired
    IUploadImg iUploadImg;

    /**
     * 上传图片
     * @param file
     * @return
     * */
    @ApiOperation(value = "上传图片")
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
