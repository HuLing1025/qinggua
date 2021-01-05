package com.yznu.qinggua.user.service;

import com.yznu.qinggua.user.pojo.Comment;
import com.yznu.qinggua.utils.Result;

import java.util.List;

public interface ICommentService {
    /**
     * 根据电影ID获取评论列表
     * @param fid
     * @return
     * */
    Result getCommentsByFid(int fid);

    /**
     * 根据用户ID获取评论列表
     * @param uid
     * @return
     * */
    Result getCommentsByUid(int uid);

    /**
     * 新增一天评论
     * @param comment
     * @return
     * */
    Result addComment(Comment comment);

    /**
     * 根据ID删除一条评论
     * @param id
     * @return
     * */
    Result deleteCommentById(int id);

    /**
     * 批量删除评论
     * @param ids
     * @return
     * */
    Result deleteComments(List<Integer> ids);
}
