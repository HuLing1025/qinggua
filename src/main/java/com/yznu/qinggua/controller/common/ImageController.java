package com.yznu.qinggua.controller.common;

import com.yznu.qinggua.service.IImageService;
import com.yznu.qinggua.utils.BarCodeUtil;
import com.yznu.qinggua.utils.ResponseUtil;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
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
@RequestMapping("/common")
public class ImageController {

    @Autowired
    IImageService iImageService;

    @ApiOperation(value = "条形码生成")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @PostMapping("/barcode/{code}")
    public ResponseEntity<Resource> createBarCode(HttpServletRequest req, HttpServletResponse response, @PathVariable String code) throws IOException {
        // code是电影的唯一编号
        BufferedImage image = BarCodeUtil.insertWords(BarCodeUtil.getBarCode(code), code);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //把BufferedImage写入ByteArrayOutputStream
        ImageIO.write(image, "jpg", os);
        //ByteArrayOutputStream转成InputStream
        InputStream input = new ByteArrayInputStream(os.toByteArray());
        //InputStream转成MultipartFile
        MultipartFile multipartFile =new MockMultipartFile(code, "barcode.jpg", "text/plain", input);
        iImageService.upload(req, multipartFile);
        Resource resource = iImageService.download(multipartFile.getName());
        ImageIO.write(image, "jpg", response.getOutputStream());
        if (resource != null) {
            String contentType = null;
            try {
                System.out.println("=================================="+resource.getFile().getAbsolutePath());
                contentType = req.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException e) {
                System.out.println("无法获取文件类型,异常: " + e);
            }
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(resource.getFilename().getBytes("UTF-8"),"iso-8859-1")  + "\"")
                    .body(resource);
        }
        return null;
    }

    @ApiOperation(value = "上传图片")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @PostMapping("/image/upload")
    public Result uploadImage(HttpServletRequest req, @RequestParam("file") MultipartFile file) {
        HashMap<String,Object> result = iImageService.upload(req, file);
        if((boolean) result.get("flag")){
            return ResponseUtil.success(result.get("path"), 200, (String)result.get("message"));
        }else{
            return ResponseUtil.error(500, (String)result.get("message"));
        }
    }

    @ApiOperation(value = "下载图片")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @GetMapping("/image/{filename}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String filename, HttpServletRequest request) throws MalformedURLException, UnsupportedEncodingException {
        Resource resource = iImageService.download(filename);
        if (resource != null) {
            String contentType = null;
            try {
                System.out.println("=================================="+resource.getFile().getAbsolutePath());
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException e) {
                System.out.println("无法获取文件类型,异常: " + e);
            }
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(resource.getFilename().getBytes("UTF-8"),"iso-8859-1")  + "\"")
                    .body(resource);
        }
        return null;
    }
}
