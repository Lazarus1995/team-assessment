package com.team.assessment;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team.assessment.entry.SysUser;
import com.team.assessment.vo.request.SysUserRequest;
import com.team.assessment.vo.response.SysUserResponse;

import java.util.List;

/**
* @author qu
* @description 针对表【sys_user(系统用户表)】的数据库操作Service
* @createDate 2023-01-07 12:20:13
*/
public interface SysUserService extends IService<SysUser> {

    SysUserResponse getUser(Long userId);

    List<SysUserResponse> getUserList(SysUserRequest sysUserRequest);

    List<SysUserResponse> getUserListChildren(Long userId);

}
