package com.hwt.tthappy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 登录传输对象
 * @author hu
 * Create by 2024-04-08 16:27
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String tel;

    private String password;

}
