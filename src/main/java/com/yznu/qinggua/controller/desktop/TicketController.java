package com.yznu.qinggua.controller.desktop;

import com.yznu.qinggua.pojo.Ticket;
import com.yznu.qinggua.service.ITicketService;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "客户端电影票操作接口")
@RestController
@RequestMapping("/desktop/ticket")
public class TicketController {

    @Autowired
    ITicketService iTicketService;

    @ApiOperation(value = "获取当前用户的多有电影票")
    @GetMapping("/uid/{uid}")
    public Result getMyTickets(@PathVariable int uid) {
        return iTicketService.getMyTicketsByUid(uid);
    }

    @ApiOperation(value = "根据ID获取电影票信息")
    @GetMapping("/{id}")
    public Result getTicketById(@PathVariable int id) {
        return iTicketService.getTicketById(id);
    }

    @ApiOperation(value = "生成一张电影票")
    @PostMapping("/")
    public Result addTicket(@RequestBody Ticket ticket) {
        return iTicketService.addTicket(ticket);
    }

    @ApiOperation(value = "电影票付款")
    @PutMapping("/{id}")
    public Result pay(@PathVariable int id) {
        return iTicketService.pay(id);
    }

    @ApiOperation(value = "根据ID删除电影票")
    @DeleteMapping("/{id}")
    public Result deleteTicketById(@PathVariable int id) {
        return iTicketService.deleteTicketById(id);
    }

    @ApiOperation(value = "批量删除电影票", notes = "参数传json对象,其中包含ids(Array类型)")
    @DeleteMapping("/")
    public Result deleteTickets(@RequestBody Map<String, Object> params) {
        return iTicketService.deleteTickets((List<Integer>)params.get("ids"));
    }

}
