package com.fly.user.manage.controller;

import com.fly.user.common.controller.BaseController;
import com.fly.user.common.util.RoleUtil;
import com.fly.user.common.vo.R;
import com.fly.user.manage.pojo.dto.SysMenuSaveRequest;
import com.fly.user.manage.pojo.entity.SysMenu;
import com.fly.user.manage.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author free loop
 * @since 2020-07-30
 */
@Api(tags = "系统菜单管理接口(web)")
@RestController
@RequestMapping("/sys-menu")
public class SysMenuController extends BaseController{

    @Autowired
    private SysMenuService sysMenuService;


    /**
     * 添加菜单
     *
     * @param  sysMenuSaveRequest  实体
     * @return success/false
     */
    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    @PostMapping
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<Boolean> add(@Valid @RequestBody SysMenuSaveRequest sysMenuSaveRequest) {
        return new R<>(sysMenuService.addSysMenu(sysMenuSaveRequest));
    }


    /**
     * 删除
     *
     * @param menuId 菜单id
     * @return success/false
     */
    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @DeleteMapping("/{menuId}")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<Boolean> delete(@PathVariable Long menuId) {
        return new R<>(sysMenuService.delSysMenu(menuId));
    }

    /**
     * 修改菜单
     *
     * @param  sysMenu  实体
     * @return success/false
     */
    @ApiOperation(value = "修改菜单", notes = "修改菜单")
    @PutMapping
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<Boolean> edit(@RequestBody SysMenu sysMenu) {
        sysMenu.setUpdateTime(LocalDateTime.now());
        return new R<>(sysMenuService.editSysMenu(sysMenu));
    }

    /**
    * 获取菜单详情
    *
    * @param menuId 菜单id
    * @return SysMenu
    */
    @ApiOperation(value = "获取菜单详情", notes = "获取菜单详情")
    @GetMapping("/{menuId}")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<SysMenu> get(@PathVariable Long menuId) {
        return new R<>(sysMenuService.getById(menuId));
    }

    /**
     * 菜单列表树查询
     *
     * @param menuId  菜单id
     * @return  R
     */
    @ApiOperation(value = "菜单列表树查询", notes = "菜单列表树查询")
    @PostMapping("/list/{menuId}")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<?> list(@PathVariable Long menuId) {
        return new R<>(sysMenuService.list(menuId));
    }


}
