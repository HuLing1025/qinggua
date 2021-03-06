package com.yznu.qinggua.service.serviceImpl;

import com.yznu.qinggua.dao.IUserDao;
import com.yznu.qinggua.pojo.User;
import com.yznu.qinggua.service.IUserService;
import com.yznu.qinggua.utils.JwtUtil;
import com.yznu.qinggua.utils.MD5;
import com.yznu.qinggua.utils.ResponseUtil;
import com.yznu.qinggua.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserDao iUserDao;

    @Override
    public Result logon(User user) {
        try{
            // 若该用户名已经被注册过
            if(iUserDao.selectUserByName(user.getName()).size() != 0){
                return ResponseUtil.error(400,"该用户名已经被注册过了!");
            }
            // 设置注册时间 +8h
            user.setRegtime(new Date());
            // MD5加密
            user.setPwd(MD5.getMD5(user.getPwd()));
            int mount = iUserDao.insertOne(user);
            if(mount == 1){
                return ResponseUtil.success(200,"注册成功!");
            }else{
                return ResponseUtil.error(500, "注册失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "注册失败,异常: " + e);
        }
    }

    @Override
    public Result login(String name, String pwd) {
        try{
            // 查询用户
            List<Map<String, Object>> user = iUserDao.selectUserByName(name);
            // 验证用户名是否存在
            if(user.size() == 0){
                return ResponseUtil.error(400, "用户名不存在!");
            }
            // MD5加密
            pwd = MD5.getMD5(pwd);
            // 验证密码
            if(user.get(0).get("pwd").equals(pwd)){
                Map<String, Object> result = new HashMap<>();
                result.put("user", user.get(0));
                // 获取token
                HashMap<String, String> token = new HashMap<>();
                token.put("username", (String)user.get(0).get("name"));
                result.put("token", JwtUtil.getToken(token));
                return ResponseUtil.success(result, 200, "登录成功!");
            }else{
                return ResponseUtil.error(400, "密码错误!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "登录失败,异常: " + e);
        }
    }

    @Override
    public Result getUserList() {
        try {
            // 查询列表
            List<Map<String, Object>> users = iUserDao.selectUsersList();
            if (users.size() == 0) {
                return ResponseUtil.error(400, "用户列表为空!");
            }
            return ResponseUtil.success(users, 200, "查询用户列表成功!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "查询用户列表失败,异常: " + e);
        }
    }

    @Override
    public Result searchUsersByName(String name) {
        try {
            // 根据用户名查列表
            List<Map<String, Object>> users = iUserDao.searchUsersByName(name);
            if (users.size() == 0) {
                return ResponseUtil.error(400, "用户列表为空!");
            }
            return ResponseUtil.success(users, 200, "根据用户名查询用户列表成功!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "根据用户名查询用户列表失败,异常: " + e);
        }
    }

    @Override
    public Result updateBaseInfo(User user) {
        try{
            if(iUserDao.updateBaseInfo(user) == 1){
                return ResponseUtil.success(200, "修改基本信息成功!");
            }else{
                return ResponseUtil.error(400, "修改基本信息失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "修改基本信息失败,异常: " + e);
        }
    }

    @Override
    public Result updatePwd(int id, String pwdOld,String pwdNew) {
        try{
            // 查找用户
            User user = iUserDao.selectUserById(id);
            if(user == null){
                return ResponseUtil.error(400, "该用户不存在!");
            }
            // 匹配原密码
            if(user.getPwd().equals(pwdOld)){
                // 修改密码
                if(iUserDao.updatePwd(id, pwdNew) == 1){
                    return ResponseUtil.success(200, "修改密码成功!");
                }else{
                    return ResponseUtil.error(400, "修改密码失败!");
                }
            }else{
                return ResponseUtil.error(400, "原密码错误!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "修改密码失败,异常: " + e);
        }
    }

    @Override
    public Result recharge(int id, float price) {
        try{
            User user = iUserDao.selectUserById(id);
            if(user == null){
                return ResponseUtil.error(400, "该用户不存在!");
            }
            // 充值
            if(iUserDao.updateBalance(id, user.getBalance() + price) == 1){
                return ResponseUtil.success(200,"成功充值" + price + "￥!");
            }else{
                return ResponseUtil.error(400, "充值失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "充值失败,异常: " + e);
        }
    }

    @Override
    public Result consumption(int id, float price) {
        try{
            // 查用户
            User user = iUserDao.selectUserById(id);
            if(user == null){
                return ResponseUtil.error(400, "该用户不存在!");
            }
            // 金额不足
            if(user.getBalance() - price < 0){
                return ResponseUtil.error(400, "金额不足!");
            }
            if(iUserDao.updateBalance(id, user.getBalance() - price) == 1){
                return ResponseUtil.success(200, "成功消费" + price + "￥!");
            }else{
                return ResponseUtil.error(400, "消费失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(400, "消费失败,异常: " + e);
        }
    }

    @Override
    public Result logout(int id) {
        try{
            if(iUserDao.deleteUserById(id) == 1){
                return ResponseUtil.success(200, "成功注销账户!");
            }else{
                return ResponseUtil.error(400, "注销账户失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "注销账户失败,异常: " + e);
        }
    }
}
