package com.fly.user.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fly.user.common.util.FormatParam;
import com.fly.user.common.util.PageUtil;
import com.fly.user.common.util.Query;
import com.fly.user.common.util.ReportUtil;
import com.fly.user.manage.mapper.SysRoleMapper;
import com.fly.user.manage.pojo.dto.PageRequest;
import com.fly.user.manage.pojo.dto.SysRoleSaveRequest;
import com.fly.user.manage.pojo.entity.SysRole;
import com.fly.user.manage.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2020-07-29
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {


    @Override
    public boolean add(SysRoleSaveRequest request) {

        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_code", request.getRoleCode());
        SysRole sysRole = getOne(queryWrapper);
        if (sysRole != null) {
            ReportUtil.throwEx("角色code意见存在!");
        }

        queryWrapper.clear();
        queryWrapper.eq("role_name", request.getRoleName());
        sysRole = getOne(queryWrapper);
        if (sysRole != null) {
            ReportUtil.throwEx("该角色名称意见存在!");
        }
        sysRole = new SysRole();
        BeanUtils.copyProperties(request, sysRole);
        return save(sysRole);
    }

    @Override
    public boolean delSysRole(Long roleId) {
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(roleId);
        sysRole.setDelFlag(true);
        return updateById(sysRole);
    }

    @Override
    public boolean editSysRole(SysRole sysRole) {
        return updateById(sysRole);
    }

    @Override
    public Page<SysRole> page(PageRequest request) {
        Page<SysRole> page = PageUtil.page(request);
        return page(page);
    }

    @Override
    public List<SysRole> getSysAdminRoles(Long userId) {
        return getBaseMapper().getSysAdminRoles(userId);
    }
}
