package com.yznu.qinggua.user.controller;

import com.yznu.qinggua.user.pojo.Comment;
import com.yznu.qinggua.user.service.ICommentService;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "评论操作接口")
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    ICommentService iCommentService;

    @ApiOperation(value = "获取电影评论")
    @GetMapping("/fid/{fid}")
    public Result getCommentsByFid(@PathVariable int fid) {
        return iCommentService.getCommentsByFid(fid);
    }

    @ApiOperation(value = "获取当前用户评论")
    @GetMapping("/uid/{uid}")
    public Result getCommentsByUid(@PathVariable int uid) {
        return iCommentService.getCommentsByUid(uid);
    }

    @ApiOperation(value = "新增评论")
    @PostMapping("/")
    public Result addComment(@RequestBody Comment comment) {
        return iCommentService.addComment(comment);
    }

    @ApiOperation(value = "根据ID删除评论")
    @DeleteMapping("/{id}")
    public Result deleteCommentById(@PathVariable int id) {
        return iCommentService.deleteCommentById(id);
    }

    @ApiOperation(value = "批量删除评论")
    @DeleteMapping("/")
    public Result deleteComments(@RequestBody Map<String, Object> params) {
        return iCommentService.deleteComments((List<Integer>)params.get("ids"));
    }
}
