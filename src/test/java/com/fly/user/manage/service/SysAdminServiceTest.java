package com.fly.user.manage.service;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fly.user.manage.pojo.dto.PageRequest;
import com.fly.user.manage.pojo.dto.SysAdminSaveRequest;
import com.fly.user.manage.pojo.dto.SysAdminUpdateRequest;
import com.fly.user.manage.pojo.entity.SysAdmin;
import com.fly.user.manage.pojo.vo.SysAdminVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/7 16:50
 */
@SpringBootTest
public class SysAdminServiceTest {

    @Autowired
    private SysAdminService sysAdminService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void sysAdminAdd() {
        SysAdminSaveRequest request = new SysAdminSaveRequest();
        request.setAccount("free loop");
        request.setPhone("18229196961");
        request.setEmail("306934150@qq.com");
        request.setPassword("123456");
        request.setNickName("hello world");
        request.setAvatar("avatar");
        request.setUserName("xiao kun");
        request.setSex(1);
        request.setAppId("appId");
        Assertions.assertTrue(sysAdminService.sysAdminAdd(request));
    }

    @Test
    public void comparePwd(){
        String source = "123456";
        SysAdmin sysAdmin = sysAdminService.getAdminByPhone("18229196961");
        Assertions.assertTrue(bCryptPasswordEncoder.matches(source,sysAdmin.getPassword()));

    }

    @Test
    public void lockSysAdmin() {
        SysAdminVO sysAdmin = sysAdminService.getAdminByUserId(1293069215946903552L);
        Assertions.assertNotEquals(sysAdmin,null);
        Assertions.assertTrue(sysAdminService.lockSysAdmin(sysAdmin.getSysAdmin().getUserId()));
    }

    @Test
    public void editSysAdmin() {
        SysAdminVO sysAdmin = sysAdminService.getAdminByUserId(1L);
        SysAdminUpdateRequest request = new SysAdminUpdateRequest();
        request.setUserId(1L);
//        BeanUtils.copyProperties(sysAdmin,request);
        request.setAppId("helloworld");
        Assertions.assertTrue(sysAdminService.editSysAdmin(request));
    }

    @Test
    public void page() {
        PageRequest request = new PageRequest();
        request.setPage(1L);
        request.setLimit(10L);
        Page<SysAdmin> page = sysAdminService.page(request);
        System.out.println("====================");
        System.out.println(JSON.toJSONString(page));
    }

    @Test
    public void getAdminByUserId() {
        Assertions.assertNotEquals(sysAdminService.getAdminByUserId(1L),null);
    }

    @Test
    public void getAdminByPhone() {
        Assertions.assertNotEquals(sysAdminService.getAdminByPhone("18229196961"),null);
    }
}