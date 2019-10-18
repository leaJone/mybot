package com.boot.lea.mybot.jwt;

/**
 * @Title: Audience.java
 * @Package com.boot.lea.mybot.jwt
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/18 10:48
 * @version v.3.0
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Audience
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/10/18 10:48 
 *
 */
@Data
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {

    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;

}
