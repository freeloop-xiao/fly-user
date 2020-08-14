package com.fly.user.manage.service;

import com.alibaba.fastjson.JSON;
import com.fly.user.manage.pojo.dto.SysDeptSaveRequest;
import com.fly.user.manage.pojo.entity.SysDept;
import com.fly.user.manage.pojo.vo.DeptVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/4 22:04
 */
@SpringBootTest
public class SysDeptServiceTest {

    @Autowired
    private SysDeptService sysDeptService;


    @Test
    public void add() {
        SysDeptSaveRequest req = new SysDeptSaveRequest();
        req.setName("用户管理");
        req.setOrderNum(1);
        req.setParentId(0);
        Assertions.assertTrue(sysDeptService.add(req));
    }

    @Test
    public void delDept() {;
        SysDept sysDept = sysDeptService.list().get(0);
        Assertions.assertTrue(sysDeptService.delDept(sysDept.getDeptId()));
        sysDept = sysDeptService.getSysDept(sysDept.getDeptId());
        Assertions.assertTrue(sysDept.getDelFlag());
    }

    @Test
    public void editDept() {
        SysDept sysDept = sysDeptService.list().get(0);
        sysDept.setName("测试菜单");
        Assertions.assertTrue(sysDeptService.editDept(sysDept));
        sysDept = sysDeptService.getSysDept(sysDept.getDeptId());
        Assertions.assertEquals("测试菜单",sysDept.getName());
    }

    @Test
    public void getSysDept() {
        SysDept sysDept = sysDeptService.list().get(0);
        Assertions.assertNotEquals(null,sysDept);
    }


    @Test
    public void list(){
        List<DeptVO> deptVOS = sysDeptService.list(2L);
        System.out.println(JSON.toJSONString(deptVOS));
    }
}