package com.boot.lea.mybot.mapper;


import com.boot.lea.mybot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author lea
 */
@Mapper
public interface UserMapper {

    @Select("SELECT 8 FROM  DUAL")
    long countFollowCountByUserId(Long userId);

    /**
     * 查询用户
     *
     * @param userId
     * @return
     */
    User selectById(Integer userId);


    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    int updateById(User user);

    int insert(User user);

}
