package com.yznu.qinggua.controller.admin;

import com.yznu.qinggua.service.IUserService;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "后台用户管理接口")
@RestController
@RequestMapping("/cms/user")
public class CmsUserController {
    @Autowired
    IUserService iUserService;

    @ApiOperation("获取用户列表")
    @GetMapping("/")
    public Result getUserList() {
        return iUserService.getUserList();
    }

    @ApiOperation("根据用户名获取用户列表")
    @GetMapping("/{name}")
    public Result getUsersByName(@PathVariable String name) {
        return iUserService.searchUsersByName(name);
    }

}
