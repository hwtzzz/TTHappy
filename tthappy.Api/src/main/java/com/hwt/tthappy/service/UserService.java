package com.hwt.tthappy.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hwt.tthappy.dao.UserDao;
import com.hwt.tthappy.dto.LoginDto;
import com.hwt.tthappy.entity.User;
import com.hwt.tthappy.utils.UserFriendlyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author hu
 * Create by 2024-04-08 16:19
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User login(LoginDto loginDto) {
        User user = userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getTel, loginDto.getTel()));
        UserFriendlyException.throwException(user == null, "用户名或密码错误!");
        UserFriendlyException.throwException(!user.getPassword().equals(loginDto.getPassword()), "用户名或密码错误!");
        user.setLastLoginTime(new Date());
        userDao.updateById(user);
        return user;
    }
}
