package com.team.assessment.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.assessment.SysUserService;
import com.team.assessment.entry.SysDepartment;
import com.team.assessment.entry.SysUser;
import com.team.assessment.enums.ErrorCode;
import com.team.assessment.exception.CustomException;
import com.team.assessment.mapper.SysDepartmentMapper;
import com.team.assessment.mapper.SysUserMapper;
import com.team.assessment.vo.request.SysUserRequest;
import com.team.assessment.vo.response.SysUserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qu
 * @description 针对表【sys_user(系统用户表)】的数据库操作Service实现
 * @createDate 2023-01-07 12:20:13
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    @Override
    public SysUserResponse getUser(Long userId) {
        SysUser sysUser = sysUserMapper.selectById(userId);
        if (sysUser == null) {
            throw new CustomException(ErrorCode.SYS_USER_NOT_FOUND.getCode(), ErrorCode.SYS_USER_NOT_FOUND.getMessage());
        }
        SysUserResponse sysUserResponse = new SysUserResponse();
        BeanUtils.copyProperties(sysUser, sysUserResponse);

        List<SysDepartment> sysDepartmentList = sysDepartmentMapper.selectList(null);
        SysDepartment sysDepartment = sysDepartmentList.stream().filter(item -> item.getId().equals(sysUser.getDepartmentId())).findFirst().orElse(null);
        //todo 权限树状图处理
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder = sysDepartmentList.stream().filter(m->sysDepartment.getParentId().equals(m.getId()))
//
//
//        list.stream().filter(m->user.getParent().equals(m.getParentId())
//                .map(m->{
//                    StringBuilder .insert(0,m.getname())
//                }
        sysUserResponse.setDepartmentName("暂时占位");
        return sysUserResponse;
    }

    @Override
    public List<SysUserResponse> getUserList(SysUserRequest sysUserRequest) {
        List<SysDepartment> sysDepartmentList = sysDepartmentMapper.selectList(null);
        List<SysUser> userList = sysUserMapper.selectList(Wrappers.lambdaQuery(SysUser.class)
                .eq(SysUser::getDepartmentId, sysUserRequest.getDepartmentId()));
        List<SysUserResponse> result = userList.stream().map(item -> {
            SysUserResponse sysUserResponse = new SysUserResponse();
            BeanUtils.copyProperties(item, sysUserResponse);
            sysUserResponse.setDepartmentName(sysDepartmentList.stream()
                    .filter(m -> item.getDepartmentId().equals(m.getId())).findFirst().orElse(null).getDepartmentName());
            return sysUserResponse;
        }).collect(Collectors.toList());
        return result;
    }


}




