package com.yznu.qinggua.user.controller;


import com.yznu.qinggua.user.pojo.User;
import com.yznu.qinggua.user.service.ICountService;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "客户端用户操作接口")
@RestController
@RequestMapping("/user")
public class CountController {
    @Autowired
    private ICountService iCountService;

    @ApiOperation(value = "注册")
    @PostMapping("/logon")
    public Result logon(@RequestBody User user) {
        return iCountService.logon(user);
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return iCountService.login(user.getName(), user.getPwd());
    }

    @ApiOperation(value = "修改账户基本信息")
    @PutMapping("/updateinfo")
    public Result updateInfo(@RequestBody User user) {
        return iCountService.updateBaseInfo(user);
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/updatepwd")
    public Result updatePwd(@RequestBody Map<String, Object> params) {
        return iCountService.updatePwd((int)params.get("id"), (String)params.get("pwdOld"), (String)params.get("pwdNew"));
    }

    @ApiOperation(value = "账户充值")
    @PutMapping("/recharge")
    public Result recharge(@RequestBody Map<String, Object> params) {
        return iCountService.recharge((int)params.get("id"), new Float(params.get("price").toString()));
    }

    @ApiOperation(value = "账户消费")
    @PutMapping("/consumption")
    public Result consumption(@RequestBody Map<String, Object> params) {
        return iCountService.consumption((int)params.get("id"), new Float(params.get("price").toString()));
    }

    @ApiOperation(value = "注销账户")
    @DeleteMapping("/{id}")
    public Result logout(@PathVariable int id) {
        return iCountService.logout(id);
    }
}
