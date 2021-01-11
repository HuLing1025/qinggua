package com.yznu.qinggua.service;

import com.yznu.qinggua.pojo.Admin;
import com.yznu.qinggua.utils.Result;

import java.util.List;

public interface IAdminService {
    /**
     * 获取管理员列表
     * @return
     * */
    Result getAdminList();

    /**
     * 根据用户名搜索列表
     * @param search
     * @return
     * */
    Result getAdminListByName(String search);

    /**
     * 注册
     * @param admin
     * @return
     * */
    Result logon(Admin admin);

    /**
     * 登录
     * @param name
     * @param pwd
     * @return
     * */
    Result login(String name, String pwd);

    /**
     * 启用账户
     * @param id
     * @return
     * */
    Result allow(int id);

    /**
     * 禁用账户
     * @param id
     * @return
     * */
    Result ban(int id);

    /**
     * 修改密码
     * @param name
     * @param pwdOld
     * @param pwdNew
     * @return
     * */
    Result updatePwd(String name, String pwdOld, String pwdNew);

    /**
     * 根据ID删除管理员
     * @param id
     * @return
     * */
    Result deleteAdminById(int id);

    /**
     * 删除管理员列表
     * @param ids
     * @return
     * */
    Result deleteAdminList(List<Integer> ids);

}
