package com.team.assessment.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.assessment.SysUserService;
import com.team.assessment.entry.SysDepartment;
import com.team.assessment.entry.SysDepartmentUser;
import com.team.assessment.entry.SysUser;
import com.team.assessment.enums.ErrorCode;
import com.team.assessment.exception.CustomException;
import com.team.assessment.mapper.SysDepartmentMapper;
import com.team.assessment.mapper.SysDepartmentUserMapper;
import com.team.assessment.mapper.SysUserMapper;
import com.team.assessment.vo.request.SysUserRequest;
import com.team.assessment.vo.response.SysUserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @Autowired
    private SysDepartmentUserMapper sysDepartmentUserMapper;


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
            SysUserResponse sysUserResponse = SysUserResponse.convert(item);
            sysUserResponse.setDepartmentName(sysDepartmentList.stream()
                    .filter(m -> item.getDepartmentId().equals(m.getId())).findFirst().orElse(null).getDepartmentName());
            return sysUserResponse;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<SysUserResponse> getUserListChildren(Long userId) {
        Long departmentId = sysUserMapper.selectById(userId).getDepartmentId();

        List<SysDepartment> sysDepartmentList = sysDepartmentMapper.selectList(null);
        List<SysUserResponse> sysUserResponseChildrenList = getChildren(sysDepartmentList, departmentId);

        return sysUserResponseChildrenList;
    }

    private List<SysUserResponse> getChildren(List<SysDepartment> sysDepartmentList, Long departmentId) {
        List<SysUserResponse> sysUserResponseChildrenList = sysDepartmentList.stream()
                .filter(item -> item.getParentId().equals(departmentId)).map(item -> {
                    SysDepartmentUser sysDepartmentUser = sysDepartmentUserMapper
                            .selectOne(Wrappers.lambdaQuery(SysDepartmentUser.class)
                                    .eq(SysDepartmentUser::getDepartmentId, item.getId()));
                    SysUserResponse sysUserResponse = new SysUserResponse();
                    if (Objects.nonNull(sysDepartmentUser)) {
                        SysUser sysUser = sysUserMapper.selectById(sysDepartmentUser.getUserId());
                        if (Objects.nonNull(sysUser)) {
                            sysUserResponse = SysUserResponse.convert(sysUser);
                            sysUserResponse.setDepartmentName(item.getDepartmentName());
                            sysUserResponse.setDepartmentId(item.getId());
                        }
                    }
                    return sysUserResponse;
                }).collect(Collectors.toList());
        for(SysUserResponse sysUserResponse : sysUserResponseChildrenList){
            sysUserResponse.setChildren(getChildren(sysDepartmentList, sysUserResponse.getDepartmentId()));
        }
        return sysUserResponseChildrenList;
    }


}




