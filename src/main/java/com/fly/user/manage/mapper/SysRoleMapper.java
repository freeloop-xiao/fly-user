package com.fly.user.manage.mapper;

import com.fly.user.manage.pojo.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author free loop
 * @since 2020-07-29
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 通过用户userId查询用户角色
     * @param userId    用户id
     * @return  list
     */
    List<SysRole> getSysAdminRoles(Long userId);
}
