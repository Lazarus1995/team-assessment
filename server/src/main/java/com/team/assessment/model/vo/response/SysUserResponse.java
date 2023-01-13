package com.team.assessment.model.vo.response;

import com.team.assessment.config.utils.DateUtils;
import com.team.assessment.model.entry.SysUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    private String token;

    /**
     * 状态
     * @see com.team.assessment.enums.SysUserStatusEnum
     */
    private Integer status;

    /**
     * 最后登录时间
     */
    private String lastLoginTime;

    /**
     * 下属用户
     */
    private List<SysUserResponse> children;


    public static SysUserResponse convert(SysUser sysUser){
        SysUserResponse sysUserResponse = new SysUserResponse();
        sysUserResponse.setUserId(sysUser.getUserId())
                .setUserName(sysUser.getUserName())
                .setPhone(sysUser.getPhone())
                .setAvatarUrl(sysUser.getAvatarUrl())
                //.setDepartmentId(sysUser.getDepartmentId())
                .setStatus(sysUser.getStatus())
                .setLastLoginTime(DateUtils.formatDateTime(sysUser.getLastLoginTime()));
        return sysUserResponse;
    }

}
