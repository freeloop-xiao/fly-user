package com.fly.user.manage.service;

import com.alibaba.fastjson.JSON;
import com.fly.user.manage.pojo.dto.SysMenuSaveRequest;
import com.fly.user.manage.pojo.entity.SysMenu;
import com.fly.user.manage.pojo.vo.MenuVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/2 22:00
 */
@SpringBootTest
public class SysMenuServiceTest {

    @Autowired
    private SysMenuService sysMenuService;

    @Test
    public void addSysMenu() {
        SysMenuSaveRequest req = new SysMenuSaveRequest();
        req.setName("菜单名称");
        req.setPath("/index.html");
        req.setUrl("http://www.baidu.com");
        req.setIcon("test.png");
        req.setParentId(0);
        Assertions.assertTrue(sysMenuService.addSysMenu(req));
    }

    @Test
    public void delSysMenu() {
        SysMenu sysMenu = sysMenuService.list().get(0);
        sysMenuService.delSysMenu(sysMenu.getMenuId());
    }

    @Test
    public void editSysMenu() {
        SysMenu sysMenu = sysMenuService.list().get(0);
        sysMenu.setName("修改后的值");
        sysMenuService.editSysMenu(sysMenu);
    }

    @Test
    public void list(){
        List<MenuVO> menuVOS = sysMenuService.list(2L);
        System.out.println(JSON.toJSONString(menuVOS));
    }
}