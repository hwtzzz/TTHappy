package com.hwt.tthappy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwt.tthappy.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hu
 * Create by 2024-04-08 16:13
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}
