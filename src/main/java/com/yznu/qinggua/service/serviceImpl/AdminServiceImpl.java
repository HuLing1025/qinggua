package com.yznu.qinggua.service.serviceImpl;

import com.yznu.qinggua.dao.IAdminDao;
import com.yznu.qinggua.pojo.Admin;
import com.yznu.qinggua.service.IAdminService;
import com.yznu.qinggua.utils.MD5;
import com.yznu.qinggua.utils.ResponseUtil;
import com.yznu.qinggua.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    IAdminDao iAdminDao;

    @Override
    public Result getAdminList() {
        try {
            // 查询列表
            List<Map<String, Object>> admins = iAdminDao.selectAdminList();
            if(admins.size() != 0){
                return ResponseUtil.success(admins, 200, "获取管理员列表成功!");
            }
            return ResponseUtil.error(400, "管理员列表为空!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "获取管理员列表失败,异常: " + e);
        }
    }

    @Override
    public Result getAdminListByName(String search) {
        try {
            // 根据名称查管理员列表
            List<Map<String, Object>> admins = iAdminDao.selectAdminListByName(search);
            if(admins.size() != 0){
                return ResponseUtil.success(admins, 200, "根据名称获取管理员列表成功!");
            }
            return ResponseUtil.error(400, "管理员列表为空!");
        }catch (Exception e){
            return ResponseUtil.error(500, "根据名称获取管理员列表失败,异常: " + e);
        }
    }

    @Override
    public Result logon(Admin admin) {
        try {
            // 验证账户名唯一性
            if (iAdminDao.selectAdminByName(admin.getName()) == null) {
                // 加密
                admin.setPwd(MD5.getMD5(admin.getPwd()));
                // 注册
                if(iAdminDao.insertOne(admin) == 1){
                    return ResponseUtil.success(200, "注册成功!");
                }
                return ResponseUtil.error(400, "注册失败!");
            }
            return ResponseUtil.error(400, "该账户名已经被注册过了!");
        }catch (Exception e){
            return ResponseUtil.error(500, "注册失败,异常: " + e);
        }
    }

    @Override
    public Result login(String name, String pwd) {
        try {
            // 查询管理员
            Admin admin = iAdminDao.selectAdminByName(name);
            if (admin != null) {
                // 验证密码
                if (admin.getPwd().equals(MD5.getMD5(pwd))){
                    return ResponseUtil.success(admin, 200, "登录成功!");
                }
                return ResponseUtil.error(400, "密码错误!");
            }else{
                return ResponseUtil.error(400, "没有该管理员!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "登录失败,异常: " + e);
        }
    }

    @Override
    public Result allow(int id) {
        try {
            // 启用
            if (iAdminDao.updateFlagOpen(id) == 1) {
                return ResponseUtil.success(200, "Allow 成功!");
            }
            return ResponseUtil.error(400, "Allow 失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "Allow 失败,异常: " + e);
        }
    }

    @Override
    public Result ban(int id) {
        try {
            // 禁用
            if (iAdminDao.updateFlagClose(id) == 1) {
                return ResponseUtil.success(200, "Close 成功!");
            }
            return ResponseUtil.error(400, "Close 失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "Close 失败,异常: " + e);
        }
    }

    @Override
    public Result updatePwd(String name, String pwdOld, String pwdNew) {
        try {
            // 验证旧密码
            if(login(name, pwdOld).getCode() == 200){
                // 修改密码
                if (iAdminDao.updatePwd(name, pwdNew) == 1){
                    return ResponseUtil.success(200, "修改密码成功!");
                }
                return ResponseUtil.error(400, "修改密码失败!");
            }
            return ResponseUtil.error(400, "旧密码错误!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "修改密码失败,异常: " + e);
        }
    }

    @Override
    public Result deleteAdminById(int id) {
        try {
            // 根据ID删除
            if (iAdminDao.deleteAdminById(id) == 1) {
                return ResponseUtil.success(200, "根据ID删除管理眼成功!");
            }
            return ResponseUtil.error(400, "根据ID删除管理员失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "根据ID删除管理员失败,异常: " + e);
        }
    }

    @Override
    public Result deleteAdminList(List<Integer> ids) {
        try {
            // 批量删除
            if(iAdminDao.deleteAdminList(ids) == ids.size()){
                return ResponseUtil.success(200, "批量删除成功!");
            }
            return ResponseUtil.error(400, "批量删除失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "批量删除失败,异常: " + e);
        }
    }
}
