package com.team.assessment.model.dto;

import lombok.Data;

@Data
public class SysRoleUser {

    public Long userId;

    public String userName;

    private String avatarUrl;

    private Integer status;

    private String roleName;

    private Integer roleId;
}
