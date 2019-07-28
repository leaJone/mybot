package com.boot.lea.mybot.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT 8 FROM  DUAL")
    long countFollowCountByUserId(Long userId);
}
