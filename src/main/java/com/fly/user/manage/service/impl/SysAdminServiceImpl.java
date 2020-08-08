package com.fly.user.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fly.user.common.util.PageUtil;
import com.fly.user.common.util.ReportUtil;
import com.fly.user.manage.entity.SysAdminDept;
import com.fly.user.manage.pojo.dto.PageRequest;
import com.fly.user.manage.pojo.dto.SysAdminSaveRequest;
import com.fly.user.manage.pojo.dto.SysAdminUpdateRequest;
import com.fly.user.manage.pojo.entity.SysAdmin;
import com.fly.user.manage.mapper.SysAdminMapper;
import com.fly.user.manage.pojo.entity.SysDept;
import com.fly.user.manage.pojo.entity.SysMenu;
import com.fly.user.manage.pojo.entity.SysRole;
import com.fly.user.manage.pojo.vo.MenuVO;
import com.fly.user.manage.pojo.vo.SysAdminVO;
import com.fly.user.manage.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统管理员表 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2020-07-29
 */
@Service
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean sysAdminAdd(SysAdminSaveRequest request) {

        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setPhone(request.getPhone());
        QueryWrapper<SysAdmin> queryWrapper = new QueryWrapper<>(sysAdmin);
        SysAdmin exists = getOne(queryWrapper);
        if (exists != null) {
            ReportUtil.throwEx("该手机号用户已经存在");
        }
        BeanUtils.copyProperties(request, sysAdmin);
        // todo
        sysAdmin.setUserId(1L);
        String password = bCryptPasswordEncoder.encode(request.getPassword());
        sysAdmin.setPassword(password);
        // 设置密码，保存用户信息
        return save(sysAdmin);
    }

    @Override
    public boolean lockSysAdmin(Long userId) {
        SysAdmin sysAdmin = getById(userId);
        if (sysAdmin == null) {
            ReportUtil.throwEx("该管理员不存在");
        }
        sysAdmin.setIsLocked(true);
        return updateById(sysAdmin);
    }

    @Override
    public boolean editSysAdmin(SysAdminUpdateRequest request) {
        SysAdmin sysAdmin = new SysAdmin();
        BeanUtils.copyProperties(request, sysAdmin);
        return updateById(sysAdmin);
    }

    @Override
    public Page<SysAdmin> page(PageRequest request) {
        Page<SysAdmin> page = PageUtil.page(request);
        Page<SysAdmin> result = page(page);
        result.getRecords().forEach(x -> x.setPassword(null));
        return result;
    }

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysAdminDeptService sysAdminDeptService;

    @Override
    public SysAdminVO getAdminByUserId(Long userId) {

        SysAdminVO sysAdminVO = new SysAdminVO();

        // 查询用户信息
        SysAdmin sysAdmin = getById(userId);
        sysAdmin.setPassword(null);
        sysAdminVO.setSysAdmin(sysAdmin);

        // 查询用户角色
        List<SysRole> roles = sysRoleService.getSysAdminRoles(userId);
        sysAdminVO.setRoles(roles);

        //  查询用户部门信息
        SysAdminDept sysAdminDept = sysAdminDeptService.getById(userId);
        if (sysAdminDept != null) {
            SysDept sysDept = sysDeptService.getSysDept(sysAdminDept.getDeptId());
            if (sysDept != null) {
                sysAdminVO.setDept(sysDept.getName());
            }
        }

        return sysAdminVO;
    }

    @Override
    public SysAdmin getAdminByPhone(String phone) {
        QueryWrapper<SysAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        return getOne(queryWrapper);
    }

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public SysAdminVO getMyAdminByUserId(Long userId) {

        SysAdminVO sysAdminVO = getAdminByUserId(userId);
        List<SysMenu> sysMenus = new ArrayList<>();
        for (SysRole role : sysAdminVO.getRoles()) {
            List<SysMenu> result = sysMenuService.getSysMenuByRoleId(role.getRoleId());
            sysMenus.addAll(result);
        }

        // 去重
        Map<Long, SysMenu> map = new HashMap<>();
        for (SysMenu sysMenu : sysMenus) {
            if (!map.containsKey(sysMenu.getMenuId())) {
                map.put(sysMenu.getMenuId(), sysMenu);
            }
        }
        sysMenus = new ArrayList<>(map.values());
        List<MenuVO> menuVOS = sysMenuService.sort(-1L, sysMenus);
        sysAdminVO.setMenus(menuVOS);

        return sysAdminVO;
    }
}
