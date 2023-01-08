package com.team.assessment.vo.response;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysUserResponse {
    /**
     * 用户 ID
     */
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
     * 从属部门名称
     */
    private String departmentName;

    /**
     * 状态：-1 删除，0 禁用，1 在岗
     */
    private Integer status;

    /**
     * 最后登录时间
     */
    private String lastLoginTime;

}
