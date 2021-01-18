package com.yznu.qinggua.service.serviceImpl;

import com.yznu.qinggua.dao.ITicketDao;
import com.yznu.qinggua.pojo.Ticket;
import com.yznu.qinggua.service.ITicketService;
import com.yznu.qinggua.utils.ResponseUtil;
import com.yznu.qinggua.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    ITicketDao iTicketDao;

    @Override
    public Result getTicketList() {
        try {
            List<Map<String, Object>> tickets = iTicketDao.selectTicketList();
            if (tickets.size() != 0){
                return ResponseUtil.success(tickets, 200, "获取所有电影票成功!");
            }
            return ResponseUtil.error(400, "电影票列表为空!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "获取所有电影票失败,异常: " + e);
        }
    }

    @Override
    public Result getTicketsByFid(int fid) {
        try {
            List<Map<String, Object>> tickets = iTicketDao.selectTicketsByFid(fid);
            if (tickets.size() != 0){
                return ResponseUtil.success(tickets, 200, "根据电影ID获取电影票成功!");
            }
            return ResponseUtil.error(400, "电影票列表为空!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "根据电影ID获取电影票失败,异常: " + e);
        }
    }

    @Override
    public Result getTicketsByFlag(int flag) {
        try {
            List<Map<String, Object>> tickets = iTicketDao.selectTicketsByFlag(flag);
            if (tickets.size() != 0){
                return ResponseUtil.success(tickets, 200, "根据支付状态获取电影票成功!");
            }
            return ResponseUtil.error(400, "没有该状态电影!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "根据支付状态获取电影票失败,异常: " + e);
        }
    }

    @Override
    public Result getMyTicketsByUid(int uid) {
        try {
            // 根据用户id查询
            List<Map<String, Object>> tickets = iTicketDao.selectTicketByUid(uid);
            if(tickets.size() != 0){
                return ResponseUtil.success(tickets, 200, "获取电影票列表成功!");
            }
            return ResponseUtil.error(400, "电影票列表为空!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "获取电影票列表失败,异常: " + e);
        }
    }

    @Override
    public Result getTicketById(int id) {
        try {
            // 根据ID查询
            Map<String, Object> ticket = iTicketDao.selectTicketById(id);
            if(ticket != null){
                return ResponseUtil.success(ticket, 200, "获取电影票信息成功!");
            }
            return ResponseUtil.error(400, "获取电影票信息失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "获取电影票信息失败,异常: " + e);
        }
    }

    @Override
    public Result addTicket(Ticket ticket) {
        try {
            // 新增
            if(iTicketDao.insertOne(ticket) == 1){
                return ResponseUtil.success(200, "新增一张电影票成功!");
            }
            return ResponseUtil.error(400, "新增一张电影票失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "新增一张电影票失败,异常: " + e);
        }
    }

    @Override
    public Result pay(int id) {
        try {
            // 修改flag的值 0:未支付, 1:已支付
            if(iTicketDao.updateFlag(id, 0, 1) == 1){
                return ResponseUtil.success(200, "付款成功!");
            }
            return ResponseUtil.error(400, "付款失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "付款失败,异常: " + e);
        }
    }

    @Override
    public Result refundRequest(int id) {
        try {
            // 查看是否满足退票条件: 放映前半小时
            // TODO: 2021/1/18
            // 修改flag的值 1:已支付, 2:已申请退票
            if(iTicketDao.updateFlag(id, 1, 2) == 1){
                return ResponseUtil.success(200, "退票申请成功!");
            }
            return ResponseUtil.error(400, "退票申请失败!");
        }catch (Exception e){
            return ResponseUtil.error(500, "退票失败,异常: " + e);
        }
    }

    @Override
    public Result refund(int id) {
        try {
            // 修改flag的值 2:已申请退票, 3:已退票
            if(iTicketDao.updateFlag(id, 2, 3) == 1){
                // TODO: 2021/1/18
                // 退票后，返钱给用户
                return ResponseUtil.success(200, "退票成功!");
            }
            return ResponseUtil.error(400, "退票失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "退票失败,异常: " + e);
        }
    }

    @Override
    public Result deleteTicketById(int id) {
        try {
            // 根据ID删除
            if(iTicketDao.deleteTicketById(id) == 1){
                return ResponseUtil.success(200, "成功删除电影票!");
            }
            return ResponseUtil.error(400, "删除电影票失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "删除电影票失败,异常: " + e);
        }
    }

    @Override
    public Result deleteTickets(List<Integer> ids) {
        try {
            // 批量删除
            if(iTicketDao.deleteTickets(ids) == ids.size()){
                return ResponseUtil.success(200, "批量删除电影票成功!");
            }
            return ResponseUtil.error(400, "批量删除电影票失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "批量删除电影票失败,异常: " + e);
        }
    }
}
