package com.hwt.tthappy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/*
说明:
使用@Configuration和@Component注解都是可以的
 */
@Configuration
public class AppConfiguration {

    /**
     * 获取文件保存地址
     */
    public static String fileSavePath;


    @Value("${fileSavePath}")
    private String _fileSavePath;

    @PostConstruct
    public void setFileSavePath() {
        fileSavePath = this._fileSavePath;
    }

}