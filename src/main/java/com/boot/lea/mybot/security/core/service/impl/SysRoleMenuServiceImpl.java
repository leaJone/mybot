package com.boot.lea.mybot.security.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.lea.mybot.security.core.dao.SysRoleMenuDao;
import com.boot.lea.mybot.security.core.entity.SysRoleMenuEntity;
import com.boot.lea.mybot.security.core.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @Description 角色与权限业务实现
 * @Author Sans
 * @CreateTime 2019/9/14 15:57
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

    @Override
    public boolean saveBatch(Collection<SysRoleMenuEntity> entityList) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<SysRoleMenuEntity> entityList) {
        return false;
    }

    @Override
    public boolean update(Wrapper<SysRoleMenuEntity> updateWrapper) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<SysRoleMenuEntity> entityList) {
        return false;
    }

    @Override
    public SysRoleMenuEntity getOne(Wrapper<SysRoleMenuEntity> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<SysRoleMenuEntity> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<SysRoleMenuEntity> list() {
        return null;
    }

    @Override
    public IPage<SysRoleMenuEntity> page(IPage<SysRoleMenuEntity> page) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps() {
        return null;
    }

    @Override
    public List<Object> listObjs() {
        return null;
    }

    @Override
    public <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public List<Object> listObjs(Wrapper<SysRoleMenuEntity> queryWrapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<SysRoleMenuEntity> page) {
        return null;
    }

    @Override
    public QueryChainWrapper<SysRoleMenuEntity> query() {
        return null;
    }

    @Override
    public LambdaQueryChainWrapper<SysRoleMenuEntity> lambdaQuery() {
        return null;
    }

    @Override
    public UpdateChainWrapper<SysRoleMenuEntity> update() {
        return null;
    }

    @Override
    public LambdaUpdateChainWrapper<SysRoleMenuEntity> lambdaUpdate() {
        return null;
    }
}