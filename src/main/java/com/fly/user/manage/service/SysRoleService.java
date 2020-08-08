package com.fly.user.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fly.user.manage.pojo.dto.PageRequest;
import com.fly.user.manage.pojo.dto.SysRoleSaveRequest;
import com.fly.user.manage.pojo.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author free loop
 * @since 2020-07-29
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 新增系统角色
     * @param request request
     * @return true/false
     */
    boolean add(SysRoleSaveRequest request);

    /**
     * 删除用户角色
     * @param roleId    角色id
     * @return  true/false
     */
    boolean delSysRole(Long roleId);

    /**
     * 修改用户角色
     * @param sysRole sysRole
     * @return  true/false
     */
    boolean editSysRole(SysRole sysRole);

    /**
     * 分页查询
     * @param params 分页参数
     * @return page
     */
    Page<SysRole> page(PageRequest request);



    /**
     * 通过用户userId查询用户角色
     * @param userId    用户id
     * @return  list
     */
    List<SysRole> getSysAdminRoles(Long userId);

}
