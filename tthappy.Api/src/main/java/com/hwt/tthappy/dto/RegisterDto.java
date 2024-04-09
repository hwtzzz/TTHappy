package com.hwt.tthappy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author hu
 * Create by 2024-04-09 16:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterDto {

    private String nickname;

    private String tel;

    private String password;

    private String avatar;

}
