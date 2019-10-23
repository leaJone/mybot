package com.boot.lea.mybot.security;

/**
 * @Title: JWTTokenUtil.java
 * @Package com.boot.lea.mybot.security
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/21 15:44
 * @version v.3.0
 */


import com.alibaba.fastjson.JSON;
import com.boot.lea.mybot.security.core.entity.SelfUserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;


/**
 * @ClassName: JWTTokenUtil
 * @Description: JWT工具类
 * @author LiJing
 * @date 2019/10/21 15:44
 *
 */
@Slf4j
public class JWTTokenUtil {

    /**
     * 生成Token
     * @Author Sans
     * @CreateTime 2019/10/2 12:16
     * @Param  selfUserEntity 用户安全实体
     * @Return Token
     */
    public static String createAccessToken(SelfUserEntity selfUserEntity){
        // 登陆成功生成JWT
        String token = Jwts.builder()
                // 放入用户名和用户ID
                .setId(selfUserEntity.getUserId()+"")
                // 主题
                .setSubject(selfUserEntity.getUsername())
                // 签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer("sans")
                // 自定义属性 放入用户拥有权限
                .claim("authorities", JSON.toJSONString(selfUserEntity.getAuthorities()))
                // 失效时间
                .setExpiration(new Date(System.currentTimeMillis() + JWTConfig.expiration))
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, JWTConfig.secret)
                .compact();
        return token;
    }
}
