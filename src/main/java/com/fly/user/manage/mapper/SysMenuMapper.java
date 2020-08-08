package com.fly.user.manage.mapper;

import com.fly.user.manage.pojo.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author free loop
 * @since 2020-07-30
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 查询角色菜单
     * @param roleId    角色id
     * @return
     */
    List<SysMenu> getSysMenuByRoleId(Long roleId);

}
