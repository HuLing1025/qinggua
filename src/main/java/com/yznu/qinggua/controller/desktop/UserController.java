package com.yznu.qinggua.controller.desktop;


import com.yznu.qinggua.pojo.User;
import com.yznu.qinggua.service.IUserService;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "客户端用户操作接口")
@RestController
@RequestMapping("/desktop/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @ApiOperation(value = "注册")
    @PostMapping("/logon")
    public Result logon(@RequestBody User user) {
        return iUserService.logon(user);
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return iUserService.login(user.getName(), user.getPwd());
    }

    @ApiOperation(value = "修改账户基本信息")
    @PutMapping("/updateinfo")
    public Result updateInfo(@RequestBody User user) {
        return iUserService.updateBaseInfo(user);
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/updatepwd")
    public Result updatePwd(@RequestBody Map<String, Object> params) {
        return iUserService.updatePwd((int)params.get("id"), (String)params.get("pwdOld"), (String)params.get("pwdNew"));
    }

    @ApiOperation(value = "账户充值")
    @PutMapping("/recharge")
    public Result recharge(@RequestBody Map<String, Object> params) {
        return iUserService.recharge((int)params.get("id"), new Float(params.get("price").toString()));
    }

    @ApiOperation(value = "账户消费")
    @PutMapping("/consumption")
    public Result consumption(@RequestBody Map<String, Object> params) {
        return iUserService.consumption((int)params.get("id"), new Float(params.get("price").toString()));
    }

    @ApiOperation(value = "注销账户")
    @DeleteMapping("/{id}")
    public Result logout(@PathVariable int id) {
        return iUserService.logout(id);
    }
}
