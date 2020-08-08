package com.fly.user.manage.service;

import com.fly.user.manage.pojo.dto.SysMenuSaveRequest;
import com.fly.user.manage.pojo.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fly.user.manage.pojo.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author free loop
 * @since 2020-07-30
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 添加菜单
     * @param menuDTO 菜单dto
     * @return true/false
     */
    boolean addSysMenu(SysMenuSaveRequest request);

    /**
     * 删除菜单
     * @param menuId 菜单id
     * @return  true/false
     */
    boolean delSysMenu(Long menuId);

    /**
     * 编辑菜单
     * @param sysMenu 系统菜单
     */
    boolean editSysMenu(SysMenu sysMenu);

    /**
     * 通过角色查询菜单
     * @param roleId    角色id
     * @return
     */
    List<SysMenu> getSysMenuByRoleId(Long roleId);

    /**
     * 获取菜单列表
     * @param menuId   菜单id
     * @return
     */
    List<MenuVO> list(Long menuId);

    /**
     * 菜单排序生成树
     * @param menuId 菜单root id
     * @param menus 菜单列表
     * @return root
     */
    List<MenuVO> sort(Long menuId, List<SysMenu> menus);
}
