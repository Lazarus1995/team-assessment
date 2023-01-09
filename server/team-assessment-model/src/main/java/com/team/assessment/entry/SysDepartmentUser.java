package com.team.assessment.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户表
 * @TableName sys_department_user
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_department_user")
public class SysDepartmentUser extends BaseEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键 id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * 部门 ID
     */
    private Long departmentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者 ID
     */
    private Long createUserId;

    /**
     * 更新者 ID
     */
    private Long updateUserId;

    /**
     * 更新时间
     */
    private Date updateTime;
}