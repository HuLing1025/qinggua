package com.yznu.qinggua.dao;

import com.yznu.qinggua.pojo.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface IAdminDao {

    @Select("SELECT * FROM admin " +
            "ORDER BY name")
    List<Map<String, Object>> selectAdminList();

    @Select("SELECT * FROM admin " +
            "WHERE name LIKE CONCAT(CONCAT('%',#{search},'%')) " +
            "ORDER BY name")
    List<Map<String, Object>> selectAdminListByName(String search);

    @Select("SELECT * FROM admin " +
            "WHERE name=#{name}")
    Admin selectAdminByName(String name);

    @Insert("INSERT INTO admin(name,pwd) " +
            "VALUES(#{name},#{pwd})")
    int insertOne(Admin admin);

    @Update("UPDATE admin SET " +
            "flag=1 WHERE id=#{id}")
    int updateFlagOpen(int id);

    @Update("UPDATE admin SET " +
            "flag=0 WHERE id=#{id}")
    int updateFlagClose(int id);

    @Update("UPDATE admin " +
            "SET pwd=#{pwd} " +
            "WHERE name=#{name}")
    int updatePwd(String name, String pwd);

    @Delete("DELETE FROM admin " +
            "WHERE id=#{id}")
    int deleteAdminById(int id);

    @Delete("<script>" +
            "DELETE FROM admin " +
            " WHERE id IN " +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>"
    )
    int deleteAdminList(@Param("ids") List<Integer> ids);

}
