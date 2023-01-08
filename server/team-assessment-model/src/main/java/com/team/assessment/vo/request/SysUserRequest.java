package com.team.assessment.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysUserRequest {
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
     * 从属部门id
     */
    private Long departmentId;


    /**
     * 状态：-1 删除，0 禁用，1 在岗
     */
    private Integer status;


}
