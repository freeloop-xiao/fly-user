package com.fly.user.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fly.user.common.util.ReportUtil;
import com.fly.user.manage.pojo.entity.SysMenu;
import com.fly.user.manage.pojo.entity.SysRole;
import com.fly.user.manage.pojo.entity.SysRoleMenu;
import com.fly.user.manage.mapper.SysRoleMenuMapper;
import com.fly.user.manage.pojo.vo.MenuVO;
import com.fly.user.manage.service.SysMenuService;
import com.fly.user.manage.service.SysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fly.user.manage.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单管联表 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2020-07-29
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public List<MenuVO> getSysRoleOfMenus(Long roleId) {

        SysRole sysRole = sysRoleService.getById(roleId);
        if (sysRole == null) {
            ReportUtil.throwEx("角色不存在");
        }

        // 查询角色菜单
        List<SysMenu> sysMenus = sysMenuService.getSysMenuByRoleId(roleId);

        return listToListTree(sysMenus);
    }


    @Override
    public boolean roleMapMenu(Long roleId, Long menuId) {

        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setRoleId(roleId);
        sysRoleMenu.setMenuId(menuId);
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>(sysRoleMenu);
        SysRoleMenu exist = getOne(queryWrapper);
        if (exist != null) {
            ReportUtil.throwEx("该角色已经拥有该菜单");
        }

        SysMenu sysMenu = sysMenuService.getById(menuId);
        if (sysMenu == null) {
            ReportUtil.throwEx("菜单不存在");
        }

        SysRole sysRole = sysRoleService.getById(roleId);
        if (sysRole == null) {
            ReportUtil.throwEx("角色不存在");
        }

        return save(sysRoleMenu);
    }

    @Override
    public boolean roleCancelMenu(Long roleId, Long menuId) {
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setRoleId(roleId);
        sysRoleMenu.setMenuId(menuId);
        UpdateWrapper<SysRoleMenu> updateWrapper = new UpdateWrapper<>(sysRoleMenu);
        return remove(updateWrapper);
    }


    private List<MenuVO> listToListTree(List<SysMenu> sysMenus) {

        List<MenuVO> menuVOS = sysMenus.stream().map(x -> {
            MenuVO menuVO = new MenuVO();
            BeanUtils.copyProperties(x, menuVO);
            return menuVO;
        }).collect(Collectors.toList());
        for (MenuVO menuVO : menuVOS) {
            List<MenuVO> children = menuVOS.stream().filter(x -> x.getParentId().equals(menuVO.getMenuId())).collect(Collectors.toList());
            menuVO.setChildren(children);
        }
        return menuVOS.stream().filter(x -> x.getParentId() == -1).collect(Collectors.toList());
    }

}
