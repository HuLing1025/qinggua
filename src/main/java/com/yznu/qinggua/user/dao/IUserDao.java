package com.yznu.qinggua.user.dao;

import com.yznu.qinggua.user.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface IUserDao {
    @Insert("INSERT INTO user(id,name,pwd,tel,email,qq,regtime) " +
            "VALUES(id,#{name},#{pwd},#{tel},#{email},#{qq},#{regtime})"
            )
    int insertOne(User user);

    @Select("SELECT * " +
            "FROM user " +
            "WHERE id=#{id}"
    )
    User selectUserById(int id);

    @Select("SELECT * " +
            "FROM user " +
            "WHERE name=#{name}")
    List<Map<String, Object>> selectUserByName(String name);

    @Update("UPDATE user " +
            "SET name=#{name}," +
            "tel=#{tel}," +
            "email=#{email}," +
            "qq=#{qq} " +
            "WHERE id=#{id}"
    )
    int updateBaseInfo(User user);

    @Update("UPDATE user " +
            "SET pwd=#{pwd} " +
            "WHERE id=#{id} "
    )
    int updatePwd(int id, String pwd);

    @Update("UPDATE user " +
            "SET balance=#{balance} " +
            "WHERE id=#{id}"
    )
    int updateBalance(int id, float balance);

    @Delete("DELETE FROM user " +
            "WHERE id=#{id}"
            )
    int deleteUserById(int id);
}
