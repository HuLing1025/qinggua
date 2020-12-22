package com.yznu.qinggua.admin.service.impl;

import com.yznu.qinggua.admin.entity.User;
import com.yznu.qinggua.admin.mapper.UserMapper;
import com.yznu.qinggua.admin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huling
 * @since 2020-12-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
