package com.yznu.qinggua.service;

import com.yznu.qinggua.pojo.Ticket;
import com.yznu.qinggua.utils.Result;

import java.util.List;

public interface ITicketService {

    /**
     * 获取所有电影票
     * @return
     * */
    Result getTicketList();

    /**
     * 根据电影ID获取电影票
     * @param fid
     * @return
     * */
    Result getTicketsByFid(int fid);

    /**
     * 根据支付状态获取电影票
     * @param flag
     * @return
     * */
    Result getTicketsByFlag(int flag);

    /**
     * 查询当前用户下购买的电影票
     * @param uid
     * @return
     * */
    Result getMyTicketsByUid(int uid);

    /**
     * 根据ID查询电影票
     * @param id
     * @return
     * */
    Result getTicketById(int id);

    /**
     * 生成一张电影票
     * @param ticket
     * @return
     * */
    Result addTicket(Ticket ticket);

    /**
     * 付款
     * @param id
     * @return
     * */
    Result pay(int id);

    /**
     * 退票
     * @param id
     * @return
     * */
    Result refund(int id);

    /**
     * 申请退票
     * @param id
     * @return
     * */
    Result refundRequest(int id);

    /**
     * 根据ID删除电影票
     * @param id
     * @return
     * */
    Result deleteTicketById(int id);

    /**
     * 批量删除电影票
     * @param ids
     * @return
     * */
    Result deleteTickets(List<Integer> ids);
}
