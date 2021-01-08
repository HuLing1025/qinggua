package com.yznu.qinggua.dao;

import com.yznu.qinggua.pojo.Ticket;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ITicketDao {

    @Select("SELECT * " +
            "FROM ticket t,user u,room r,seat s " +
            "WHERE t.uid=#{uid} AND t.uid=u.id AND t.rid=r.id AND t.sid=s.id " +
            "ORDER BY createTime DESC")
    List<Map<String, Object>> selectTicketByUid(int uid);

    @Select("SELECT * " +
            "FROM ticket t,user u,room r,seat s " +
            "WHERE t.id=#{id} AND t.uid=u.id AND t.rid=r.id AND t.sid=s.id")
    Map<String, Object> selectTicketById(int id);

    @Insert("INSERT INTO ticket(id,uid,rid,sid,number) " +
            "VALUES(id,#{uid},#{rid},#{sid},#{number})")
    int insertOne(Ticket ticket);

    @Update("UPDATE ticket " +
            "SET flag=1 " +
            "WHERE id=#{id}")
    int updateFlag(int id);

    @Delete("DELETE FROM ticket " +
            "WHERE id=#{id}")
    int deleteTicketById(int id);

    @Delete("<script>" +
            "DELETE FROM ticket " +
            " WHERE id IN " +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>"
    )
    int deleteTickets(@Param("ids") List<Integer> ids);

}
