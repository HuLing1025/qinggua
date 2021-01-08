package com.yznu.qinggua.service;

import com.yznu.qinggua.pojo.User;
import com.yznu.qinggua.utils.Result;

public interface IUserService {
    /**
     * 注册
     * @param user
     * @return
     * */
   Result logon(User user);

   /**
    * 登录
    * @param name
    * @param pwd
    * @return
    * */
   Result login(String name, String pwd);

   /**
    * 获取用户列表
    * @return
    * */
   Result getUserList();

   /**
    * 根据用户名查询用户列表
    * @param name
    * @return
    * */
   Result searchUsersByName(String name);

   /**
    * 修改基本信息
    * @param user
    * @return
    * */
   Result updateBaseInfo(User user);

   /**
    * 修改密码
    * @param id
    * @param pwdOld
    * @param pwdNew
    * @return
    * */
   Result updatePwd(int id, String pwdOld,String pwdNew);

   /**
    * 账户充值
    * @param id
    * @param price
    * @return
    * */
   Result recharge(int id, float price);

   /**
    * 账户消费
    * @param id
    * @param price
    * @return
    * */
    Result consumption(int id, float price);

    /**
     * 注销账户
     * @param id
     * @return
     * */
    Result logout(int id);

}
