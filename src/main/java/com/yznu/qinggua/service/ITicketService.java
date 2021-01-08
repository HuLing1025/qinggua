package com.yznu.qinggua.service;

import com.yznu.qinggua.pojo.Ticket;
import com.yznu.qinggua.utils.Result;

import java.util.List;

public interface ITicketService {
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
