package com.fly.user.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fly.user.manage.pojo.dto.PageRequest;
import com.fly.user.manage.pojo.dto.SysDeptSaveRequest;
import com.fly.user.manage.pojo.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fly.user.manage.pojo.vo.DeptVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门管理 服务类
 * </p>
 *
 * @author free loop
 * @since 2020-07-29
 */
@Service
public interface SysDeptService extends IService<SysDept> {

    /**
     * 添加部门信息
     *
     * @param req 请求信息
     * @return true/false
     */
    boolean add(SysDeptSaveRequest req);

    /**
     * 编辑部门信息
     *
     * @param deptId 部门id
     * @return true/false
     */
    boolean delDept(Long deptId);


    /**
     * 编辑部门信息
     *
     * @param sysDept 部门编辑信息
     * @return true/false
     */
    boolean editDept(SysDept sysDept);


    /**
     * 查询系统菜单信息
     * @param menuId    菜单id
     * @return  SysMenu
     */
    SysDept getSysDept(Long deptId);

    /**
     * 分页查询部门信息
     * @param request   request
     * @return page
     */
    Page<SysDept> page(PageRequest request);

    /**
     * 部门列表
     * @param deptId    部门id
     * @return  list
     */
    List<DeptVO> list(Long deptId);

}
