package com.boot.lea.mybot.security;

/**
 * @Title: UserLoginFailureHandler.java
 * @Package com.boot.lea.mybot.security
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/21 15:47
 * @version v.3.0
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiJing
 * @ClassName: UserLoginFailureHandler
 * @Description: 登录失败处理类
 * @date 2019/10/21 15:47
 */
@Slf4j
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {
    /**
     * 登录失败返回结果
     *
     * @Author Sans
     * @CreateTime 2019/10/3 9:12
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        // 这些对于操作的处理类可以根据不同异常进行不同处理
        if (exception instanceof UsernameNotFoundException) {
            log.info("【登录失败】" + exception.getMessage());
            ResultUtil.responseJson(response, ResultUtil.resultCode(500, "用户名不存在"));
        }
        if (exception instanceof LockedException) {
            log.info("【登录失败】" + exception.getMessage());
            ResultUtil.responseJson(response, ResultUtil.resultCode(500, "用户被冻结"));
        }
        if (exception instanceof BadCredentialsException) {
            log.info("【登录失败】" + exception.getMessage());
            ResultUtil.responseJson(response, ResultUtil.resultCode(500, "用户名密码不正确"));
        }
        ResultUtil.responseJson(response, ResultUtil.resultCode(500, "登录失败"));
    }
}
