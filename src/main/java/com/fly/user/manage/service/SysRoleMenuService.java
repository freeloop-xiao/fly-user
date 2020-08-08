package com.fly.user.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fly.user.manage.pojo.entity.SysRoleMenu;
import com.fly.user.manage.pojo.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 角色菜单管联表 服务类
 * </p>
 *
 * @author free loop
 * @since 2020-07-29
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 查询系统角色菜单列表
     * @param roleId 角色id
     * @return list
     */
    List<MenuVO> getSysRoleOfMenus(Long roleId);

    /**
     * 角色授权菜单
     * @param roleId    角色id
     * @param menuId    菜单id
     * @return  true/false
     */
    boolean roleMapMenu(Long roleId,Long menuId);


    /**
     * 角色取消菜单授权
     * @param roleId    角色id
     * @param menuId    菜单id
     * @return  true/false
     */
    boolean roleCancelMenu(Long roleId,Long menuId);
}
