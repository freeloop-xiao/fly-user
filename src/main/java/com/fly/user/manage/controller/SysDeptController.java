package com.fly.user.manage.controller;

import com.fly.user.common.controller.BaseController;
import com.fly.user.common.util.RoleUtil;
import com.fly.user.common.vo.R;
import com.fly.user.manage.pojo.dto.SysDeptSaveRequest;
import com.fly.user.manage.pojo.entity.SysDept;
import com.fly.user.manage.pojo.vo.DeptVO;
import com.fly.user.manage.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author free loop
 * @since 2020-07-29
 */
@Api(tags = "系统部门管理接口(web)")
@RestController
@RequestMapping("/sys-dept")
public class SysDeptController extends BaseController {

    @Autowired
    private SysDeptService sysDeptService;


    /**
     * 添加
     *
     * @param req 系统部门
     * @return success/false
     */
    @ApiOperation(value = "添加部门", notes = "添加部门")
    @PostMapping
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<Boolean> add(@Valid @RequestBody SysDeptSaveRequest req) {
        return new R<>(sysDeptService.add(req));
    }


    /**
     * 删除部门
     *
     * @param deptId ID
     * @return success/false
     */
    @ApiOperation(value = "删除部门", notes = "删除部门")
    @DeleteMapping("/{deptId}")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<Boolean> delete(@PathVariable Long deptId) {
        return new R<>(sysDeptService.delDept(deptId));
    }

    /**
     * 修改部门
     *
     * @param sysDept 实体
     * @return success/false
     */
    @ApiOperation(value = "修改部门", notes = "修改部门")
    @PutMapping
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<Boolean> edit(@RequestBody SysDept sysDept) {
        return new R<>(sysDeptService.editDept(sysDept));
    }

    /**
     * 通过ID查询
     *
     * @param deptId ID
     * @return SysDept
     */
    @ApiOperation(value = "获取部门详情", notes = "获取部门详情")
    @GetMapping("/{deptId}")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<SysDept> get(@PathVariable Long deptId) {
        return new R<>(sysDeptService.getSysDept(deptId));
    }


    /**
     * 递归查询部门列表信息
     *
     * @return 分页对象
     */
    @ApiOperation(value = "部门列表树查询", notes = "部门列表树查询")
    @PostMapping("/list/{deptId}")
    @PreAuthorize(RoleUtil.ROLE_SUPER)
    public R<List<DeptVO>> list(@PathVariable Long deptId) {
        return new R<>(sysDeptService.list(deptId));
    }


}
