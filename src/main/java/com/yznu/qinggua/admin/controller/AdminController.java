package com.yznu.qinggua.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yznu.qinggua.admin.entity.Admin;
import com.yznu.qinggua.admin.service.IAdminService;
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
public class AdminController {
    @Autowired
    IAdminService iAdminService;

    /**
    * 获取管理员列表
    * @return
    * */
    @GetMapping("/admin")
    public Result getAdminList() {
        try{
            List<Admin> admins = iAdminService.list();
            if(admins.size() != 0){
                return ResponseUtil.success(admins, 200, "获取管理员列表成功!");
            }else{
                return ResponseUtil.error(400, "列表为空!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
    * 根据ID获取管理员
    * @param id
    * @return
    * */
    @GetMapping("/admin/{id}")
    public Result getAdmin(@PathVariable int id) {
        try{
            Admin admin = iAdminService.getById(id);
            if(admin != null){
                return ResponseUtil.success(admin, 200, "成功!");
            }else{
                return ResponseUtil.error(400, "未找到该管理员!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
    * 新增管理员
    * @param admin
    * @return
    * */
    @PostMapping("/admin")
    public Result addAdmin(@RequestBody Admin admin) {
        try{
            // 超级管理员 flag = 1,其他的管理员 flag = 0
            if(admin.getFlag() != 0){
                admin.setFlag(0);
            }
            QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name",admin.getName());
            // 该用户名是否已经注册
            if(iAdminService.getOne(queryWrapper) != null){
                return ResponseUtil.error(300, "该用户名已经存在!");
            }
            boolean flag = iAdminService.save(admin);
            if(flag){
                return ResponseUtil.success(200, "添加成功!");
            }else{
                return ResponseUtil.error(500, "添加失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
    * 修改管理员信息
    * @param admin
    * @return
    * */
    @PutMapping("/admin")
    public Result updateAdmin(@RequestBody Admin admin) {
        try{
            boolean flag = iAdminService.updateById(admin);
            if(flag){
                return ResponseUtil.success(200, "修改成功!");
            }else{
                return ResponseUtil.error(500, "修改失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
    * 根据ID删除管理员
    * @param id
    * @return
    * */
    @DeleteMapping("/admin/{id}")
    public Result deleteAdmin(@PathVariable int id) {
        try{
            boolean flag = iAdminService.removeById(id);
            if(flag){
                return ResponseUtil.success(200, "删除成功!");
            }else{
                return ResponseUtil.error(500, "删除失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
    * 批量删除管理员
    * @param ids
    * @return
    * */
    @DeleteMapping("/admin")
    public Result deleteAdmins(@RequestBody List<Integer> ids) {
        try{
            boolean flag = iAdminService.removeByIds(ids);
            if(flag){
                return ResponseUtil.success(200, "批量删除成功!");
            }else{
                return ResponseUtil.error(500, "批量删除失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }
}
