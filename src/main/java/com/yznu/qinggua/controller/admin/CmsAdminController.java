package com.yznu.qinggua.controller.admin;

import com.yznu.qinggua.pojo.Admin;
import com.yznu.qinggua.service.IAdminService;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "后台管理员管理接口")
@RestController
@RequestMapping("/cms/admin")
public class CmsAdminController {
    @Autowired
    IAdminService iAdminService;

    @ApiOperation(value = "获取管理员列表")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @GetMapping("")
    public Result getAdminList() {
        return iAdminService.getAdminList();
    }

    @ApiOperation(value = "根据账户名搜索管理员列表")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @PostMapping("/{search}")
    public Result getAdminListByName(@PathVariable String search) {
        return iAdminService.getAdminListByName(search);
    }

    @ApiOperation(value = "注册管理员")
    @PostMapping("/logon")
    public Result logon(@RequestBody Admin admin) {
        return iAdminService.logon(admin);
    }

    @ApiOperation(value = "管理员登录")
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        return iAdminService.login(admin.getName(), admin.getPwd());
    }

    @ApiOperation(value = "启用账户")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @PutMapping("/allow/{id}")
    public Result allow(@PathVariable int id) {
        return iAdminService.allow(id);
    }

    @ApiOperation(value = "禁用账户")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @PutMapping("/ban/{id}")
    public Result ban(@PathVariable int id) {
        return iAdminService.ban(id);
    }

    @ApiOperation(value = "根据ID删除管理员")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @DeleteMapping("/{id}")
    public Result deleteAdminById(@PathVariable int id) {
        return iAdminService.deleteAdminById(id);
    }

    @ApiOperation(value = "批量删除管理员")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @DeleteMapping("")
    public Result deleteAdmins(@RequestBody Map<String, Object> param) {
        return iAdminService.deleteAdminList((List<Integer>) param.get("ids"));
    }
}
