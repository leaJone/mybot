package com.boot.lea.mybot.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LiJing
 * @ClassName: UserVO
 * @Description: 用户视图对象
 * @date 2019/7/30 13:55
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 性别
     */
    private String sex;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;


}