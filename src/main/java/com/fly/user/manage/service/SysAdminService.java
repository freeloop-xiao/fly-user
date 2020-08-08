package com.fly.user.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fly.user.manage.pojo.dto.PageRequest;
import com.fly.user.manage.pojo.dto.SysAdminSaveRequest;
import com.fly.user.manage.pojo.dto.SysAdminUpdateRequest;
import com.fly.user.manage.pojo.entity.SysAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fly.user.manage.pojo.entity.SysRole;
import com.fly.user.manage.pojo.vo.SysAdminVO;

import java.util.List;

/**
 * <p>
 * 系统管理员表 服务类
 * </p>
 *
 * @author free loop
 * @since 2020-07-29
 */
public interface SysAdminService extends IService<SysAdmin> {

    /**
     * @param request request
     * @return
     */
    boolean sysAdminAdd(SysAdminSaveRequest request);


    /**
     * 禁用管理员
     * @param userId userId
     * @return
     */
    boolean lockSysAdmin(Long userId);

    /**
     * 修改系统管理员信息
     * @param request request
     * @return
     */
    boolean editSysAdmin(SysAdminUpdateRequest request);

    /**
     * 分页查询管理员信息
     * @param request request
     * @return
     */
    Page<SysAdmin> page(PageRequest request);


    /**
     * 查询管理员用户信息详情
     * @param userId userId
     * @return  SysAdminVO
     */
    SysAdminVO getAdminByUserId(Long userId);

    /**
     * 通过手机号查询用户账户
     * @param phone 手机号
     * @return sysAdmin
     */
    SysAdmin getAdminByPhone(String phone);


    /**
     * 查询管理员用户信息详情
     * @param userId userId
     * @return  SysAdminVO
     */
    SysAdminVO getMyAdminByUserId(Long userId);


}
