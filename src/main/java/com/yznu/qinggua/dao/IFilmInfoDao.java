package com.yznu.qinggua.dao;

import com.yznu.qinggua.pojo.Filminfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface IFilmInfoDao {

    @Select("SELECT * " +
            "FROM filmInfo " +
            "ORDER BY releaseTime DESC")
    List<Map<String, Object>> selectAllFilm();

    @Select("SELECT * FROM filmInfo " +
            "WHERE title LIKE CONCAT(CONCAT('%',#{search},'%')) " +
            "OR director LIKE CONCAT(CONCAT('%',#{search},'%')) " +
            "OR type LIKE CONCAT(CONCAT('%',#{search},'%')) " +
            "ORDER BY releaseTime DESC")
    List<Map<String, Object>> searchFilm(String search);

    @Select("SELECT * FROM filmInfo WHERE id=#{id}")
    Filminfo selectFilmById(int id);

    @Select("SELECT count(*) " +
            "FROM filminfo " +
            "WHERE title=#{title}")
    int isFilmExists(String title);

    @Insert("INSERT INTO filmInfo(id,title,director,actors,country,type,duration,releaseTime,image,details,score,votecount) " +
            "VALUES(id,#{title},#{director},#{actors},#{country},#{type},#{duration},#{releaseTime},#{image},#{details},#{score},#{voteCount})")
    int insertOne(Filminfo filmInfo);

    @Update("UPDATE filmInfo SET " +
            "title=#{title},director=#{director}," +
            "actors=#{actors},country=#{country}," +
            "type=#{type},duration=#{duration}," +
            "releaseTime=#{releaseTime},image=#{image}," +
            "details=#{details} " +
            "WHERE id=#{id}")
    int updateFilm(Filminfo filmInfo);

    @Delete("DELETE FROM filmInfo " +
            "WHERE id=#{id}")
    int deleteFilmById(int id);

//    @Delete("DELETE FROM filmInfo " +
//            "WHERE id IN (#{ids})")
    @Delete("<script>" +
                "DELETE FROM filmInfo " +
                " WHERE id IN " +
                "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" +
                    "#{item}" +
                "</foreach>" +
            "</script>"
            )
    int deleteFilms(@Param("ids") List<Integer> ids);

}
