package com.fly.user.manage.pojo.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户部门管联表
 * </p>
 *
 * @author free loop
 * @since 2020-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysAdminDept对象", description="用户部门管联表")
public class SysAdminDept extends Model<SysAdminDept> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "部门id")
    private Long deptId;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
