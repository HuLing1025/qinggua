package com.yznu.qinggua.controller.desktop;

import com.yznu.qinggua.pojo.Comment;
import com.yznu.qinggua.service.ICommentService;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "客户端评论操作接口")
@RestController
@RequestMapping("/desktop/comment")
public class CommentController {
    @Autowired
    ICommentService iCommentService;

    @ApiOperation(value = "获取电影评论")
    @GetMapping("/fid/{fid}")
    public Result getCommentsByFid(@PathVariable int fid) {
        return iCommentService.getCommentsByFid(fid);
    }

    @ApiOperation(value = "获取当前用户评论")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @GetMapping("/uid/{uid}")
    public Result getCommentsByUid(@PathVariable int uid) {
        return iCommentService.getCommentsByUid(uid);
    }

    @ApiOperation(value = "新增评论")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @PostMapping("/")
    public Result addComment(@RequestBody Comment comment) {
        return iCommentService.addComment(comment);
    }

    @ApiOperation(value = "根据ID删除评论")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @DeleteMapping("/{id}")
    public Result deleteCommentById(@PathVariable int id) {
        return iCommentService.deleteCommentById(id);
    }

    @ApiOperation(value = "批量删除评论")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @DeleteMapping("/")
    public Result deleteComments(@RequestBody Map<String, Object> params) {
        return iCommentService.deleteComments((List<Integer>)params.get("ids"));
    }
}
