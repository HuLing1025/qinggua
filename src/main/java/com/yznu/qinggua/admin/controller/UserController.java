package com.yznu.qinggua.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yznu.qinggua.admin.entity.User;
import com.yznu.qinggua.admin.service.IUserService;
import com.yznu.qinggua.utils.ResponseUtil;
import com.yznu.qinggua.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huling
 * @since 2020-12-22
 */
@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    IUserService iUserService;

    /*
    * 获取用户列表
    * @return
    * */
    @GetMapping("/user")
    public Result getUserList() {
        try{
            List<User> users = iUserService.list();
            if(users.size() != 0){
                return ResponseUtil.success(users, 200, "获取用户列表成功!");
            }else{
                return ResponseUtil.success(200, "列表为空!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e.getMessage());
        }
    }

    /*
    * 根据ID获取用户
    * @param id
    * @return
    * */
    @GetMapping("/user/{id}")
    public Result getUserById(@PathVariable String id) {
        try{
            User user = iUserService.getById(id);
            if(user != null){
                return ResponseUtil.success(user, 200, "成功!");
            }else{
                return ResponseUtil.success(400, "未找到该用户");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e.getMessage());
        }
    }

    /*
    * 新增用户
    * @param
    * @return
    * */
    @PostMapping("/user")
    public Result addUser(@RequestBody User user) {
        try{
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name",user.getName());
            // 该用户名是否已经注册
            if(iUserService.getOne(queryWrapper) != null){
                return ResponseUtil.error(200, "该用户名已经存在!");
            }
            boolean flag = iUserService.save(user);
            if(flag){
                return ResponseUtil.success(200, "添加成功!");
            }else{
                return ResponseUtil.success(500, "添加失败");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e.getMessage());
        }
    }

    /*
    * 修改用户信息
    * @param user
    * @return
    * */
    @PutMapping("/user/{id}")
    public Result updateUser(@PathVariable int id, @RequestBody User user) {
        try{
            boolean flag = iUserService.updateById(user);
            if(flag){
                return ResponseUtil.success(200, "修改成功!");
            }else{
                return ResponseUtil.success(500, "修改失败");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e.getMessage());
        }
    }

    /*
    * 根据ID删除用户
    * @param id
    * @return
    * */
    @DeleteMapping("/user/{id}")
    public Result deleteUser(@PathVariable int id) {
        try{
            boolean flag = iUserService.removeById(id);
            if(flag){
                return ResponseUtil.success(200, "删除成功!");
            }else{
                return ResponseUtil.success(500, "删除失败");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e.getMessage());
        }
    }
    /*
    * 批量删除用户
    * @param ids
    * @return
    * */
    @DeleteMapping("/user")
    public Result deleteUsers(@RequestBody List<Integer> ids) {
        try{
            boolean flag = iUserService.removeByIds(ids);
            if(flag){
                return ResponseUtil.success(200, "批量删除成功!");
            }else{
                return ResponseUtil.success(500, "批量删除失败");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e.getMessage());
        }
    }
}
