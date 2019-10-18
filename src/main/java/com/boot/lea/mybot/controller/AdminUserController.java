package com.boot.lea.mybot.controller;

/**
 * @Title: AdminUserController.java
 * @Package com.boot.lea.mybot.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/18 10:58
 * @version v.3.0
 * @ClassName: AdminUserController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/10/18 10:58
 * @ClassName: AdminUserController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/10/18 10:58
 */

/**
 * @ClassName: AdminUserController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/10/18 10:58 
 *
 */

import com.boot.lea.mybot.dto.RspDTO;
import com.boot.lea.mybot.jwt.Audience;
import com.boot.lea.mybot.jwt.JwtIgnore;
import com.boot.lea.mybot.jwt.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@RestController
public class AdminUserController {

    @Autowired
    private Audience audience;

    /**JWT验证主要是通过过滤器验证，
     * 所以我们需要添加一个拦截器来演请求头中是否包含有后台颁发的
     * token，这里请求头的格式：Authorization: Bearer <token>
     * @param response
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    @JwtIgnore
    public RspDTO<String> adminLogin(HttpServletResponse response, String username, String password) {
        // 这里模拟测试, 默认登录成功，返回用户ID和角色信息
        String userId = UUID.randomUUID().toString();
        String role = "admin";

        // 创建token
        String token = JwtTokenUtil.createJWT(userId, username, role, audience);
        log.info("### 登录成功, token={} ###", token);

        // 将token放在响应头
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);

        RspDTO<String> success = RspDTO.success();
        success.setData(token);
        // 将token响应给客户端
        return success;
    }

    @GetMapping("/users")
    public RspDTO userList() {
        log.info("### 查询所有用户列表 ###");
        return RspDTO.success();
    }
}
