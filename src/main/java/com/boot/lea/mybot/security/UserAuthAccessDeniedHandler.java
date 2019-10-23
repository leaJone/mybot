package com.boot.lea.mybot.security;

/**
 * @Title: UserAuthAccessDeniedHandler.java
 * @Package com.boot.lea.mybot.security
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/21 15:46
 * @version v.3.0
 */



import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @ClassName: UserAuthAccessDeniedHandler
 * @Description: 暂无权限处理类
 * @author LiJing
 * @date 2019/10/21 15:46
 *
 */
@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * 暂无权限返回结果
     * @Author Sans
     * @CreateTime 2019/10/3 8:41
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception){
        ResultUtil.responseJson(response,ResultUtil.resultCode(403,"未授权"));
    }
}
