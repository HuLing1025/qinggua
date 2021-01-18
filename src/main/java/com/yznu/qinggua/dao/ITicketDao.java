package com.yznu.qinggua.dao;

import com.yznu.qinggua.pojo.Ticket;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ITicketDao {

    @Select("SELECT * FROM " +
            "ticket t,user u,room r,seat s,filmInfo f,schedule sc " +
            "WHERE t.uid=u.id AND t.rid=sc.id AND t.sid=s.id AND f.id=sc.fid AND r.id=s.rid " +
            "ORDER BY flag")
    List<Map<String, Object>> selectTicketList();

    @Select("SELECT * FROM " +
            "ticket t,user u,room r,seat s,filmInfo f,schedule sc " +
            "WHERE sc.fid=#{fid} AND t.uid=u.id AND t.rid=sc.id AND t.sid=s.id AND f.id=sc.fid AND r.id=s.rid " +
            "ORDER BY flag")
    List<Map<String, Object>> selectTicketsByFid(int fid);

    @Select("SELECT * FROM " +
            "ticket t,user u,room r,seat s,filmInfo f,schedule sc " +
            "WHERE t.flag=#{flag} AND t.uid=u.id AND t.rid=sc.id AND t.sid=s.id AND f.id=sc.fid AND r.id=s.rid " +
            "ORDER BY createTime")
    List<Map<String, Object>> selectTicketsByFlag(int flag);

    @Select("SELECT * " +
            "FROM ticket t,user u,room r,seat s,filmInfo f,schedule sc " +
            "WHERE t.uid=#{uid} AND t.uid=u.id AND t.rid=sc.id AND t.sid=s.id AND f.id=sc.fid AND r.id=s.rid " +
            "ORDER BY createTime DESC")
    List<Map<String, Object>> selectTicketByUid(int uid);

    @Select("SELECT * " +
            "FROM ticket t,user u,room r,seat s,filmInfo f,schedule sc " +
            "WHERE t.id=#{id} AND t.uid=u.id AND t.rid=sc.id AND t.sid=s.id AND f.id=sc.fid AND r.id=s.rid ")
    Map<String, Object> selectTicketById(int id);

    @Insert("INSERT INTO ticket(id,uid,rid,sid,number) " +
            "VALUES(id,#{uid},#{rid},#{sid},#{number})")
    int insertOne(Ticket ticket);

    @Update("UPDATE ticket " +
            "SET flag=#{to} " +
            "WHERE id=#{id} AND flag=#{from}")
    int updateFlag(int id, int from, int to);

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
