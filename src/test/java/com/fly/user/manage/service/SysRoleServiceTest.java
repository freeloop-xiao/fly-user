package com.fly.user.manage.service;

import com.alibaba.fastjson.JSON;
import com.fly.user.manage.pojo.dto.SysRoleSaveRequest;
import com.fly.user.manage.pojo.entity.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/5 11:27
 */
@SpringBootTest
public class SysRoleServiceTest {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void add() {
        SysRoleSaveRequest request = new SysRoleSaveRequest();
        request.setRoleCode("ROLE_ADMIN");
        request.setRoleName("系统管理员");
        request.setRoleDesc("管理用户系统");
        sysRoleService.add(request);
    }

    @Test
    public void delSysRole() {
        SysRole sysRole = sysRoleService.list().get(0);
        sysRoleService.delSysRole(sysRole.getRoleId());
    }

    @Test
    public void editSysRole() {
        SysRole sysRole = sysRoleService.list().get(0);
        sysRole.setRoleDesc("测试修改管理用户系统");
        sysRoleService.editSysRole(sysRole);
    }

    @Test
    public void getSysAdminRoles(){
        List<SysRole> list = sysRoleService.getSysAdminRoles(3L);
        System.out.println(JSON.toJSONString(list));
    }
}