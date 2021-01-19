package com.yznu.qinggua.controller.admin;

import com.yznu.qinggua.service.ITicketService;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "后台电影票管理接口")
@RestController
@RequestMapping("/cms/ticket")
public class CmsTicketController {
    @Autowired
    ITicketService iTicketService;

    @ApiOperation(value = "获取全部电影票")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @GetMapping("")
    public Result getTicketList() {
        return iTicketService.getTicketList();
    }

    @ApiOperation(value = "根据电影ID获取电影票")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @GetMapping("/fid/{fid}")
    public Result getTicketsByFid(@PathVariable int fid) {
        return iTicketService.getTicketsByFid(fid);
    }

    @ApiOperation(value = "根据支付状态获取电影票")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @GetMapping("/flag/{flag}")
    public Result getTicketsByFlag(@PathVariable int flag) {
        return iTicketService.getTicketsByFlag(flag);
    }

    @ApiOperation(value = "退票审核")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @PutMapping("/{id}")
    public Result refundReview(@PathVariable int id) {
        return iTicketService.refund(id);
    }

}
