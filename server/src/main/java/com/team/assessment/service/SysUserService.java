package com.team.assessment.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team.assessment.model.entry.SysUser;
import com.team.assessment.model.vo.request.SysUserRequest;
import com.team.assessment.model.vo.response.SysUserResponse;
import org.springframework.stereotype.Service;


import java.util.List;

/**
* @author qu
* @description 针对表【sys_user(系统用户表)】的数据库操作Service
* @createDate 2023-01-07 12:20:13
*/
@Service
public interface SysUserService extends IService<SysUser> {

    SysUserResponse login(SysUserRequest sysUserRequest) throws Exception;

    SysUserResponse getUser(Long userId);

    List<SysUserResponse> getUserList(SysUserRequest sysUserRequest);

    List<SysUserResponse> getUserListChildren(Long userId);

    JSONObject WXLogin(JSONObject jsonObject);
}
