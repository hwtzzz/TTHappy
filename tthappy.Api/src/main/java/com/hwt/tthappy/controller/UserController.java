package com.hwt.tthappy.controller;

import com.hwt.tthappy.dto.LoginDto;
import com.hwt.tthappy.entity.User;
import com.hwt.tthappy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 *
 * @author hu
 * Create by 2024-04-08 16:20
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param loginDto 登录信息接收对象
     * @return 返回登录用户信息
     */
    @PostMapping("/login")
    public User getUserByTel(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

}
