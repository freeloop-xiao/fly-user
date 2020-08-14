package com.fly.user.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fly.user.common.util.ReportUtil;
import com.fly.user.manage.mapper.SysMenuMapper;
import com.fly.user.manage.pojo.dto.SysMenuSaveRequest;
import com.fly.user.manage.pojo.entity.SysMenu;
import com.fly.user.manage.pojo.vo.DeptVO;
import com.fly.user.manage.pojo.vo.MenuVO;
import com.fly.user.manage.service.SysMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2020-07-30
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public boolean addSysMenu(SysMenuSaveRequest request) {

        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", request.getName());
        queryWrapper.eq("parent_id", request.getParentId());
        queryWrapper.eq("del_flag", false);
        SysMenu sysMenu = getOne(queryWrapper);
        if (sysMenu != null) {
            ReportUtil.throwEx("该菜单名称已经存在!");
        }

        sysMenu = getById(request.getParentId());
        if (sysMenu != null) {
            ReportUtil.throwEx("父菜单不存在！");
        }

        sysMenu = new SysMenu();
        BeanUtils.copyProperties(request, sysMenu);
        return save(sysMenu);
    }

    @Override
    public boolean delSysMenu(Long menuId) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuId(menuId);
        sysMenu.setDelFlag(true);
        return updateById(sysMenu);
    }

    @Override
    public boolean editSysMenu(SysMenu sysMenu) {
        if (sysMenu.getParentId() != -1){
            SysMenu parentMenu = getById(sysMenu.getParentId());
            if (parentMenu == null){
                ReportUtil.throwEx("父菜单不存在");
            }
        }
        return updateById(sysMenu);
    }

    @Override
    public List<SysMenu> getSysMenuByRoleId(Long roleId) {
        return getBaseMapper().getSysMenuByRoleId(roleId);
    }

    @Override
    public List<MenuVO> list(Long menuId) {

        List<SysMenu> menus = new ArrayList<>();
        if (menuId == -1) {
            menus = list();
        } else {
            // 递归查询所有子菜单
            SysMenu root = getById(menuId);
            menus.add(root);
            List<Long> menuIds = new ArrayList<>();
            menuIds.add(menuId);
            recursion(menus, menuIds);
        }
        return sort(menuId, menus);
    }


    private void recursion(List<SysMenu> menus, List<Long> menuIds) {
        if (menuIds != null && menuIds.size() > 0) {
            QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("parent_id", menuIds);
            List<SysMenu> result = list(queryWrapper);
            if (result.size() > 0) {
                menus.addAll(result);
                menuIds = result.stream().map(SysMenu::getMenuId).collect(Collectors.toList());
                recursion(menus, menuIds);
            }
        }
    }


    @Override
    public List<MenuVO> sort(Long menuId, List<SysMenu> menus) {

        List<MenuVO> menuList = new ArrayList<>();
        for (SysMenu menu : menus) {
            MenuVO menuVO = new MenuVO();
            BeanUtils.copyProperties(menu, menuVO);
            menuList.add(menuVO);
        }
        for (MenuVO menuVO : menuList) {
            List<MenuVO> children = menuList.stream().filter(x -> menuVO.getMenuId().equals(x.getParentId()))
                    .sorted(Comparator.comparingInt(MenuVO::getSort))
                    .collect(Collectors.toList());
            menuVO.setChildren(children);
        }


        List<MenuVO> root = null;
        if (menuId == -1) {
            root = menuList.stream().filter(x -> x.getParentId() == -1).collect(Collectors.toList());
        } else {
            root = menuList.stream().filter(x -> x.getMenuId().equals(menuId)).collect(Collectors.toList());
        }
        return root;
    }
}
