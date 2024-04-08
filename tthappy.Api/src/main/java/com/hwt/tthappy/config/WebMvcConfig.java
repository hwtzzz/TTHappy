package com.hwt.tthappy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private ObjectMapper objectMapper; //忽略红色线

    @Value("${CROS_ALLOWED_ORIGINS}")
    private String[] allowedOrigins;

    //暂时还没使用到枚举
//    @PostConstruct
//    public void myObjectMapper() {
//        // 解决enum不匹配问题 默认值为false
//        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
//    }

//暂时还没添加自己写的拦截器
//    @Bean
//    public AuthenticationInterceptor authenticationInterceptor() {
//        return new AuthenticationInterceptor();// 自己写的拦截器
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") //允许跨域的域名，可以用*表示允许任何域名使用
                .allowedMethods("*") //允许任何方法（post、get等）
                .allowedHeaders("*") //允许任何请求头
                .maxAge(3600L); //maxAge(3600)表明在3600秒内，不需要再发送预检验请求，可以缓存该结果

//        registry.addMapping("/**")
//                //.allowCredentials(true)
//                .allowedHeaders("*")
//                //.allowedMethods("*")
//                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
//                .allowedOriginPatterns(allowedOrigins)
//                .maxAge(3600);
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //System.err.println(filePath);
        //url文件映射到存储目录
//        registry.addResourceHandler("/files/**").addResourceLocations("file:" + AppConfiguration.fileSavePath);
        //注意：配置文件中配置地址时，如果有中文可能会乱码导致文件不能访问，需通过配置utf-8模式才能解决，具体参考：https://blog.csdn.net/qq_40587263/article/details/115294585
        //registry.addResourceHandler("/files/**").addResourceLocations("file:D:/Projects/语音合成/tts/src/main/resources/voiceFiles/");
//    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }
}