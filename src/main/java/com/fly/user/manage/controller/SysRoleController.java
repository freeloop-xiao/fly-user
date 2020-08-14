package com.fly.user.manage.controller;

import com.fly.user.common.controller.BaseController;
import com.fly.user.common.util.RoleUtil;
import com.fly.user.common.vo.R;
import com.fly.user.manage.pojo.dto.PageRequest;
import com.fly.user.manage.pojo.dto.SysRoleSaveRequest;
import com.fly.user.manage.pojo.entity.SysRole;
import com.fly.user.manage.pojo.vo.MenuVO;
import com.fly.user.manage.service.SysRoleMenuService;
import com.fly.user.manage.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author free loop
 * @since 2020-07-29
 */
@Api(tags = "系统角色接口(web)")
@RestController
@RequestMapping("/sys-role")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 添加
     *
     * @param request 实体
     * @return success/false
     */
    @ApiOperation(value = "添加角色", notes = "添加角色")
    @PostMapping
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<Boolean> add(@Valid @RequestBody SysRoleSaveRequest request) {
        return new R<>(sysRoleService.add(request));
    }

    /**
     * 删除
     *
     * @param roleId ID
     * @return success/false
     */
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @DeleteMapping("/{roleId}")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<Boolean> delete(@PathVariable Long roleId) {
        return new R<>(sysRoleService.delSysRole(roleId));
    }

    /**
     * 编辑
     *
     * @param sysRole 实体
     * @return success/false
     */
    @ApiOperation(value = "修改角色", notes = "修改角色")
    @PutMapping
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<Boolean> edit(@RequestBody SysRole sysRole) {
        sysRole.setUpdateTime(LocalDateTime.now());
        return new R<>(sysRoleService.editSysRole(sysRole));
    }

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysRole
     */
    @ApiOperation(value = "查询角色信息", notes = "查询角色信息")
    @GetMapping("/{roleId}")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<SysRole> get(@PathVariable Long roleId) {
        return new R<>(sysRoleService.getById(roleId));
    }


    /**
     * 分页查询信息
     *
     * @param params 分页对象
     * @return 分页对象
     */
    @ApiOperation(value = "分页获取", notes = "分页获取")
    @PostMapping("/page")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<?> page(@RequestBody PageRequest request) {
        return new R<>((sysRoleService.page(request)));
    }


    @ApiOperation(value = "角色查询菜单树", notes = "角色查询菜单树")
    @PostMapping("/menus/{roleId}")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<List<MenuVO>> roleOfMenus(@PathVariable Long roleId) {
        return new R<>(sysRoleMenuService.getSysRoleOfMenus(roleId));
    }


    @ApiOperation(value = "角色添加菜单", notes = "角色添加菜单")
    @PostMapping("/role-map-menu")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<Boolean> roleMapMenu(@RequestParam Long roleId, @RequestParam Long menuId) {
        return new R<>(sysRoleMenuService.roleMapMenu(roleId, menuId));
    }

    @ApiOperation(value = "角色取消菜单", notes = "角色取消菜单")
    @PostMapping("/role-cancel-menu")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<Boolean> roleDelMenu(@RequestParam Long roleId, @RequestParam Long menuId) {
        return new R<>(sysRoleMenuService.roleCancelMenu(roleId, menuId));
    }


}
