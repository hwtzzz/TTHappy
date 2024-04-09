package com.hwt.tthappy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hwt.tthappy.config.AppConsts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户信息
 *
 * @author hu
 * Create by 2024-04-08 16:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 电话
     */
    private String tel;
    /**
     * 密码
     */
    private String password;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 最后登录时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConsts.dateTimeFormatPattern, timezone = "GMT+8")
    @DateTimeFormat(pattern = AppConsts.dateTimeFormatPattern)
    private Date lastLoginTime;
    /**
     * 个人介绍
     */
    private String introduce;
    /**
     * 生日
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConsts.dateFormatPattern, timezone = "GMT+8")
    @DateTimeFormat(pattern = AppConsts.dateFormatPattern)
    private Date birthday;
    /**
     * 性别 1男 2女
     */
    private Integer sex;

}
