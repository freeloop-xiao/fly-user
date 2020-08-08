package com.fly.user.manage.controller;

import com.fly.user.common.controller.BaseController;
import com.fly.user.common.util.RoleUtil;
import com.fly.user.common.vo.R;
import com.fly.user.manage.pojo.dto.PageRequest;
import com.fly.user.manage.pojo.dto.SysAdminSaveRequest;
import com.fly.user.manage.pojo.dto.SysAdminUpdateRequest;
import com.fly.user.manage.pojo.entity.SysAdmin;
import com.fly.user.manage.pojo.vo.SysAdminVO;
import com.fly.user.manage.service.SysAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统管理员表 前端控制器
 * </p>
 *
 * @author free loop
 * @since 2020-07-29
 */
@Api(tags = "系统管理员接口(web)")
@RestController
@RequestMapping("/sys-admin")
public class SysAdminController extends BaseController {

    @Autowired
    private SysAdminService sysAdminService;

    /**
     * 添加
     *
     * @param request 实体
     * @return success/false
     */
    @ApiOperation(value = "添加管理员", notes = "添加管理员")
    @PostMapping
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<Boolean> add(@Valid @RequestBody SysAdminSaveRequest request) {
        return new R<>(sysAdminService.sysAdminAdd(request));
    }


    /**
     * 删除
     *
     * @param userId userId
     * @return success/false
     */
    @ApiOperation(value = "删除管理员", notes = "删除管理员")
    @DeleteMapping("/{userId}")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<Boolean> delete(@PathVariable Long userId) {
        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setUserId(userId);
        sysAdmin.setUpdateTime(LocalDateTime.now());
        sysAdmin.setDelFlag(true);
        return new R<>(sysAdminService.updateById(sysAdmin));
    }


    /**
     * 编辑
     *
     * @param request 实体
     * @return success/false
     */
    @ApiOperation(value = "修改管理员信息", notes = "修改管理员信息")
    @PutMapping
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<?> edit(@RequestBody SysAdminUpdateRequest request) {
        return new R<>(sysAdminService.editSysAdmin(request));
    }

    /**
     * 锁定管理员
     *
     * @param userId userId
     * @return success/false
     */
    @ApiOperation(value = "锁定管理员", notes = "锁定管理员")
    @PutMapping("/lock/{userId}")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<?> lock(@PathVariable Long userId) {
        return new R<>(sysAdminService.lockSysAdmin(userId));
    }


    /**
     * 通过ID查询
     *
     * @param userId userId
     * @return SysAdminVO
     */
    @ApiOperation(value = "获取管理员信息", notes = "获取管理员信息")
    @GetMapping("/{userId}")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<SysAdminVO> get(@PathVariable Long userId) {
        return new R<>(sysAdminService.getAdminByUserId(userId));
    }


    /**
     * 分页查询信息
     *
     * @param params 分页对象
     * @return 分页对象
     */
    @ApiOperation(value = "分页查询管理员", notes = "分页查询管理员")
    @PostMapping("/page")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<?> page(@RequestBody PageRequest request) {
        return new R<>(sysAdminService.page(request));
    }


    /**
     * 通过userId查询
     *
     * @return SysAdminVO
     */
    @ApiOperation(value = "获取登陆管理员本人信息", notes = "获取登陆管理员本人信息")
    @GetMapping("/get-my")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<SysAdminVO> getMy() {
        return new R<>(sysAdminService.getMyAdminByUserId(getUserId()));
    }
}
