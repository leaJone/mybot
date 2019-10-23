package com.boot.lea.mybot.security.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.lea.mybot.security.core.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色权限关系DAO
 * @Author Sans
 * @CreateTime 2019/9/14 15:57
 */
@Mapper
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenuEntity> {
	
}
