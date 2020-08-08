package com.fly.user.manage.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author free loop
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRole对象", description="系统角色表")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID=1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色code")
    private String roleCode;

    @ApiModelProperty(value = "角色描叙")
    private String roleDesc;

    @ApiModelProperty(value = "角色创建时间",hidden = true)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "角色更新时间",hidden = true)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识（0-正常,1-删除）",hidden = true)
    private Boolean delFlag;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
