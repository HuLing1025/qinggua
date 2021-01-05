package com.yznu.qinggua.user.dao;

import com.yznu.qinggua.user.pojo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ICommentDao {

    @Select("SELECT c.content,c.createTime,u.name " +
            "FROM comment c,user u " +
            "WHERE c.fid=#{fid} AND c.uid=u.id " +
            "ORDER BY c.createTime DESC")
    List<Map<String, Object>> selectCommentsByFid(int fid);

    @Select("SELECT f.image,f.title,c.content,c.createTime " +
            "FROM comment c,filmInfo f " +
            "WHERE c.uid=#{uid} AND c.fid=f.id " +
            "ORDER BY c.createTime DESC")
    List<Map<String, Object>> selectCommentsByUid(int uid);

    @Insert("INSERT INTO comment(fid,uid,content) " +
            "VALUES(#{fid},#{uid},#{content})")
    int insertComment(Comment comment);

    @Delete("DELETE FROM comment " +
            "WHERE id=#{id}")
    int deleteCommentById(int id);

    @Delete("<script>" +
            "DELETE FROM comment " +
            " WHERE id IN " +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>"
    )
    int deleteComments(@Param("ids") List<Integer> ids);
}
