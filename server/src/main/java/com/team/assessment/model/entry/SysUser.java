package com.team.assessment.model.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户表
 *
 * @TableName sys_user
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_user")
public class SysUser extends BaseEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户 ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户绑定手机号
     */
    private String phone;

    /**
     * 用户头像url
     */
    private String avatarUrl;

    /**
     * 从属部门id
     */
    private Long departmentId;

    /**
     * 状态
     * @see com.team.assessment.enums.SysUserStatusEnum
     */
    private Integer status;

    /**
     * 微信 session id
     */
    private String sessionId;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

}