package com.boot.lea.mybot.security;

/**
 * @Title: UserPermissionEvaluator.java
 * @Package com.boot.lea.mybot.security
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/21 15:53
 * @version v.3.0
 */

import com.boot.lea.mybot.security.core.entity.SelfUserEntity;
import com.boot.lea.mybot.security.core.entity.SysMenuEntity;
import com.boot.lea.mybot.security.core.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: UserPermissionEvaluator
 * @Description: 自定义权限注解验证
 * @author LiJing
 * @date 2019/10/21 15:53 
 *
 */
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private SysUserService sysUserService;
    /**
     * hasPermission鉴权方法
     * 这里仅仅判断PreAuthorize注解中的权限表达式
     * 实际中可以根据业务需求设计数据库通过targetUrl和permission做更复杂鉴权
     * @Author Sans
     * @CreateTime 2019/10/6 18:25
     * @Param  authentication  用户身份
     * @Param  targetUrl  请求路径
     * @Param  permission 请求路径权限
     * @Return boolean 是否通过
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        // 获取用户信息
        SelfUserEntity selfUserEntity =(SelfUserEntity) authentication.getPrincipal();
        // 查询用户权限(这里可以将权限放入缓存中提升效率)
        Set<String> permissions = new HashSet<>();
        List<SysMenuEntity> sysMenuEntityList = sysUserService.selectSysMenuByUserId(selfUserEntity.getUserId());
        for (SysMenuEntity sysMenuEntity:sysMenuEntityList) {
            permissions.add(sysMenuEntity.getPermission());
        }
        // 权限对比
        if (permissions.contains(permission.toString())){
            return true;
        }
        return false;
    }
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
