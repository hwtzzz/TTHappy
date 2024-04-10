package com.hwt.tthappy.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hwt.tthappy.dao.UserDao;
import com.hwt.tthappy.dto.LoginDto;
import com.hwt.tthappy.dto.RegisterDto;
import com.hwt.tthappy.entity.User;
import com.hwt.tthappy.utils.ImageUtils;
import com.hwt.tthappy.utils.UserFriendlyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public void register(RegisterDto registerDto) {
        User u = userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getTel, registerDto.getTel()));
        UserFriendlyException.throwException(u != null , "手机号已注册!");
        User user = new User();
        //判断是否为默认头像
        if (registerDto.getAvatar().equals("https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0")){
            user.setAvatar(registerDto.getAvatar());
        }else {
            String imgData = registerDto.getAvatar();
            String avatar = ImageUtils.GenerateImage(imgData);
            user.setAvatar(avatar);
        }
        user.setTel(registerDto.getTel());
        user.setNickname(registerDto.getNickname());
        user.setPassword(registerDto.getPassword());
        userDao.insert(user);
    }
}
