package com.fly.user.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fly.user.common.util.PageUtil;
import com.fly.user.common.util.ReportUtil;
import com.fly.user.manage.mapper.SysDeptMapper;
import com.fly.user.manage.pojo.dto.PageRequest;
import com.fly.user.manage.pojo.dto.SysDeptSaveRequest;
import com.fly.user.manage.pojo.entity.SysDept;
import com.fly.user.manage.pojo.vo.DeptVO;
import com.fly.user.manage.service.SysDeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author free loop
 * @since 2020-07-29
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Override
    public boolean add(SysDeptSaveRequest req) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(req, sysDept);
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>(sysDept);
        SysDept exist = getOne(queryWrapper);
        if (exist != null){
            ReportUtil.throwEx("该部门已经存在");
        }
        return save(sysDept);
    }

    @Override
    public boolean delDept(Long deptId) {
        SysDept sysDept = new SysDept();
        sysDept.setDeptId(deptId);
        sysDept.setDelFlag(true);
        return updateById(sysDept);
    }

    @Override
    public boolean editDept(SysDept sysDept) {
        sysDept.setUpdateTime(LocalDateTime.now());
        return updateById(sysDept);
    }

    @Override
    public SysDept getSysDept(Long deptId) {
        return getById(deptId);
    }

    @Override
    public Page<SysDept> page(PageRequest request) {
        Page<SysDept> page = PageUtil.page(request);
        return page(page);
    }


    @Override
    public List<DeptVO> list(Long deptId) {
        List<SysDept> deps = new ArrayList<>();
        if (deptId == -1) {
            // 查询所有部门
            deps = list();
        } else {
            SysDept sysDept = getById(deptId);
            deps.add(sysDept);
            // 递归查询下所有部门
            List<Long> deptIds = new ArrayList<>();
            deptIds.add(deptId);
            recursion(deps, deptIds);
        }
        return sort(deptId, deps);
    }

    /**
     * 递归查询部门
     *
     * @param list    部门列表
     * @param deptIds 单次查询结果ids
     */
    private void recursion(List<SysDept> list, List<Long> deptIds) {
        if (deptIds != null && deptIds.size() > 0) {
            QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("parent_id", deptIds);
            List<SysDept> result = list(queryWrapper);
            if (result.size() > 0) {
                list.addAll(result);
                deptIds = result.stream().map(SysDept::getDeptId).collect(Collectors.toList());
                recursion(list, deptIds);
            }
        }
    }


    /**
     * 部门排序
     *
     * @param deptId 部门id
     * @param deps   部门列表
     * @return deptVO
     */
    private List<DeptVO> sort(Long deptId, List<SysDept> deps) {

        List<DeptVO> deptVOS = new ArrayList<>();
        for (SysDept sysDept : deps) {
            DeptVO deptVO = new DeptVO();
            BeanUtils.copyProperties(sysDept, deptVO);
            deptVOS.add(deptVO);
        }

        for (DeptVO deptVO : deptVOS) {
            List<DeptVO> children = deptVOS.stream().filter(x -> deptVO.getDeptId().equals(x.getParentId()))
                    .sorted(Comparator.comparingInt(DeptVO::getOrderNum))
                    .collect(Collectors.toList());
            deptVO.setChildren(children);
        }


        List<DeptVO> root = null;
        if (deptId == -1) {
            root = deptVOS.stream().filter(x -> x.getParentId() == -1).collect(Collectors.toList());
        } else {
            root = deptVOS.stream().filter(x -> x.getDeptId().equals(deptId)).collect(Collectors.toList());
        }
        return root;
    }
}
