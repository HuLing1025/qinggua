package com.yznu.qinggua.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yznu.qinggua.admin.entity.Comment;
import com.yznu.qinggua.admin.entity.Seat;
import com.yznu.qinggua.admin.service.ICommentService;
import com.yznu.qinggua.utils.ResponseUtil;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huling
 * @since 2020-12-24
 */
@Api(tags = "电影评论操作接口")
@RestController
@RequestMapping("/admin")
public class CommentController {
    @Autowired
    ICommentService iCommentService;

    /**
     * 根据电影ID获取全部评论
     * @param fid
     * @return
     */
    @ApiOperation(value = "根据电影ID获取全部评论")
    @GetMapping("/comment/{fid}")
    public Result getCommentListByFid(@PathVariable int fid) {
        try{
            // 查询条件 fid=#{fid}
            QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("fid", fid);
            List<Comment> comments = iCommentService.list();
            if(comments.size() != 0){
                return ResponseUtil.success(comments, 200, "获取评论列表成功!");
            }else{
                return ResponseUtil.error(400, "列表为空!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
     * 新增评论
     * @param comment
     * @return
     * */
    @ApiOperation(value = "新增评论", notes = "createtime参数传字符串就可以")
    @PostMapping("/comment")
    public Result addComment(@Ignore @RequestBody Comment comment) {
        try{
            boolean flag = iCommentService.save(comment);
            if(flag){
                return ResponseUtil.success(comment, 200, "成功新增一条评论!");
            }else{
                return ResponseUtil.error(500, "新增评论失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
     * 根据ID删除评论
     * @param id
     * @return
     * */
    @ApiOperation(value = "根据ID删除评论")
    @DeleteMapping("/comment{id}")
    public Result deleteCommentById(@PathVariable int id) {
        try{
            boolean flag = iCommentService.removeById(id);
            if(flag){
                return ResponseUtil.success(200, "删除成功!");
            }else{
                return ResponseUtil.error(500, "删除失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
     * 批量删除管理员
     * @param ids
     * @return
     * */
    @ApiOperation(value = "批量删除评论")
    @DeleteMapping("/comment")
    public Result deleteComments(@RequestBody List<Integer> ids) {
        try{
            boolean flag = iCommentService.removeByIds(ids);
            if(flag){
                return ResponseUtil.success(200, "批量删除成功!");
            }else{
                return ResponseUtil.error(500, "批量删除失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }
}
