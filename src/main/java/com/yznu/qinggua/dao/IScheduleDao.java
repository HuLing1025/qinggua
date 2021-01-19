package com.yznu.qinggua.dao;

import com.yznu.qinggua.pojo.Schedule;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface IScheduleDao {
    @Select("SELECT * FROM " +
            "schedule sc,filmInfo f,room r " +
            "WHERE sc.fid=f.id AND sc.rid=r.id " +
            "ORDER BY startTime")
    List<Map<String, Object>> selectScheduleList();

    @Select("SELECT * FROM " +
            "schedule sc,filmInfo f,room r " +
            "WHERE sc.id=#{id} " +
            "AND sc.fid=f.id AND sc.rid=r.id " +
            "ORDER BY startTime")
    Map<String, Object> selectScheduleById(int id);

    @Select("SELECT * FROM " +
            "schedule sc,filmInfo f,room r " +
            "WHERE sc.fid=#{fid} " +
            "AND sc.fid=f.id AND sc.rid=r.id " +
            "ORDER BY startTime")
    List<Map<String, Object>> selectSchedulesByFid(int fid);

    @Insert("INSERT INTO schedule(id,fid,startTime,standard,rid,price) " +
            "VALUES(id,#{fid},#{startTime},#{standard},#{rid},#{price})")
    int insertOne(Schedule schedule);

    @Update("UPDATE schedule " +
            "SET fid=#{fid},startTime=#{startTime},standard=#{standard},rid=#{rid},price=#{price} " +
            "WHERE id=#{id}")
    int updateOne(Schedule schedule);

    @Delete("DELETE FROM schedule " +
            "WHERE id=#{id}")
    int deleteScheduleById(int id);

    @Delete("<script>" +
            "DELETE FROM schedule " +
            " WHERE id IN " +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>"
    )
    int deleteSchedules(@Param("ids") List<Integer> ids);
}
