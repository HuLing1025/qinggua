package com.yznu.qinggua.controller.admin;

import com.yznu.qinggua.service.ICommentService;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "后台评论管理接口")
@RestController
@RequestMapping("/cms/comment")
public class CmsCommentController {

    @Autowired
    ICommentService iCommentService;

    @ApiOperation(value = "获取全部评论")
    @GetMapping("/")
    public Result getCommentList() {
        return iCommentService.getCommentList();
    }

}
