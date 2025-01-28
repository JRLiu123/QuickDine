package com.sky.mapper;


import com.sky.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * search user with openid
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    /**
     * add new user
     * @param user
     */
    void insert(User user);
    @Select("select * from user where id = #{id}")
    User getById(Long userId);
}
