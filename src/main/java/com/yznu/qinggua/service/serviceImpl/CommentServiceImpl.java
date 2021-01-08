package com.yznu.qinggua.service.serviceImpl;

import com.yznu.qinggua.dao.ICommentDao;
import com.yznu.qinggua.pojo.Comment;
import com.yznu.qinggua.service.ICommentService;
import com.yznu.qinggua.utils.ResponseUtil;
import com.yznu.qinggua.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    ICommentDao iCommentDao;

    @Override
    public Result getCommentsByFid(int fid) {
        try {
            // 根据电影ID查询
            List<Map<String, Object>> comments = iCommentDao.selectCommentsByFid(fid);
            if (comments.size() == 0) {
                return ResponseUtil.error(400, "该电影没有评论!");
            }
            return ResponseUtil.success(comments, 200, "获取电影评论成功!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "获取电影评论失败,异常: " + e);
        }
    }

    @Override
    public Result getCommentsByUid(int uid) {
        try {
            // 根据用户ID查询评论
            List<Map<String, Object>> comments = iCommentDao.selectCommentsByUid(uid);
            if (comments.size() == 0) {
                return ResponseUtil.error(400, "该用户没有评论!");
            }
            return ResponseUtil.success(comments, 200, "获取用户评论成功!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "获取用户评论失败,异常: " + e);
        }
    }

    @Override
    public Result addComment(Comment comment) {
        try {
            // 新增
            if (iCommentDao.insertComment(comment) == 1) {
                return ResponseUtil.success(200, "评论成功!");
            }
            return ResponseUtil.error(400, "评论失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "评论失败,异常: " + e);
        }
    }

    @Override
    public Result deleteCommentById(int id) {
        try {
            // 根据ID删除
            if (iCommentDao.deleteCommentById(id) == 1) {
                return ResponseUtil.success(200, "根据ID删除评论成功!");
            }
            return ResponseUtil.error(400, "根据ID删除评论失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "根据ID删除评论失败,异常: " + e);
        }
    }

    @Override
    public Result deleteComments(List<Integer> ids) {
        try {
            // 批量删除
            if (iCommentDao.deleteComments(ids) == ids.size()) {
                return ResponseUtil.success(200, "批量删除评论成功!");
            }
            return ResponseUtil.error(400, "批量删除评论失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "批量删除评论失败,异常: " + e);
        }
    }
}
