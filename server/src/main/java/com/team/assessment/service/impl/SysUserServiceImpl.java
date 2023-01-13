package com.team.assessment.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.assessment.common.exception.CustomException;
import com.team.assessment.config.enums.ErrorCode;
import com.team.assessment.config.utils.AESUtil;
import com.team.assessment.config.utils.TokenUtils;
import com.team.assessment.dao.SysDepartmentMapper;
import com.team.assessment.dao.SysDepartmentUserMapper;
import com.team.assessment.dao.SysUserMapper;
import com.team.assessment.model.entry.SysDepartment;
import com.team.assessment.model.entry.SysDepartmentUser;
import com.team.assessment.model.entry.SysUser;
import com.team.assessment.model.vo.request.SysUserRequest;
import com.team.assessment.model.vo.response.SysUserResponse;
import com.team.assessment.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public SysUserResponse login(SysUserRequest sysUserRequest) throws Exception {
        String password = AESUtil.encrypt(sysUserRequest.getPassword());
        SysUser sysUser = sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery()
                .eq(SysUser::getPhone, sysUserRequest.getPhone())
                .eq(SysUser::getPassword, password));

        if (Objects.nonNull(sysUser)) {
            SysDepartmentUser sysDepartmentUser = sysDepartmentUserMapper.selectOne(Wrappers.<SysDepartmentUser>lambdaQuery()
                    .eq(SysDepartmentUser::getUserId, sysUser.getUserId()));
            String token = TokenUtils.createToken(sysUserRequest.getPhone());
            return SysUserResponse.builder()
                    .userId(sysUser.getUserId())
                    .userName(sysUser.getUserName())
                    .phone(sysUser.getPhone())
                    .avatarUrl(sysUser.getAvatarUrl())
                    .departmentId(sysDepartmentUser.getDepartmentId())
                    .token(token)
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public SysUserResponse getUser(Long userId) {
        //获取用户基础信息
        SysUser sysUser = sysUserMapper.selectById(userId);
        if (sysUser == null) {
            throw new CustomException(ErrorCode.SYS_USER_NOT_FOUND.getCode(), ErrorCode.SYS_USER_NOT_FOUND.getMessage());
        }
        //获取用户对应部门信息
        SysDepartmentUser sysDepartmentUser = sysDepartmentUserMapper.selectOne(Wrappers.<SysDepartmentUser>lambdaQuery()
                .eq(SysDepartmentUser::getUserId, userId));
        if (sysDepartmentUser == null) {
            throw new CustomException(ErrorCode.SYS_USER_DEPARTMENT_NOT_FOUND.getCode(), ErrorCode.SYS_USER_DEPARTMENT_NOT_FOUND.getMessage());
        }

        SysUserResponse sysUserResponse = SysUserResponse.convert(sysUser);

        //获取部门信息
        List<SysDepartment> tempDepartmentList = sysDepartmentMapper.selectList(null);

        //完整部门信息
        StringBuilder stringBuilder = new StringBuilder();
        String departmentStr = getParentDepartment(stringBuilder, tempDepartmentList, sysDepartmentUser.getDepartmentId());
        sysUserResponse.setDepartmentName(departmentStr);
        return sysUserResponse;
    }

    /**
     * 获取上级部门信息
     *
     * @param sysDepartmentList
     * @param departmentId
     * @return
     */
    private String getParentDepartment(StringBuilder stringBuilder, List<SysDepartment> sysDepartmentList, Long departmentId) {
        //获取当前部门 ID 的 parentID
        SysDepartment sysDepartmentTemp = sysDepartmentList
                .stream().filter(sysDepartment -> sysDepartment.getId().equals(departmentId)).findFirst().get();

        List<SysDepartment> result = sysDepartmentList.stream()
                .filter(sysDepartment -> sysDepartment.getId().equals(sysDepartmentTemp.getParentId())).collect(Collectors.toList());
        for (SysDepartment sysDepartment : result) {
            //添加叶子部门
            if (stringBuilder.length() == 0) {
                stringBuilder.append(sysDepartmentTemp.getDepartmentName());
            }
            stringBuilder.insert(0, sysDepartment.getDepartmentName() + "/");
            if (sysDepartment.getParentId() != 0) {
                getParentDepartment(stringBuilder, sysDepartmentList, sysDepartment.getParentId());
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public List<SysUserResponse> getUserList(SysUserRequest sysUserRequest) {
//        List<SysDepartment> sysDepartmentList = sysDepartmentMapper.selectList(null);
//        List<SysUser> userList = sysUserMapper.selectList(Wrappers.lambdaQuery(SysUser.class)
//                .eq(SysUser::getDepartmentId, sysUserRequest.getDepartmentId()));
//        List<SysUserResponse> result = userList.stream().map(item -> {
//            SysUserResponse sysUserResponse = SysUserResponse.convert(item);
//            sysUserResponse.setDepartmentName(sysDepartmentList.stream()
//                    .filter(m -> item.getDepartmentId().equals(m.getId())).findFirst().orElse(null).getDepartmentName());
//            return sysUserResponse;
//        }).collect(Collectors.toList());
//        return result;
        return null;
    }

    @Override
    public List<SysUserResponse> getUserListChildren(Long userId) {
        // Long departmentId = sysUserMapper.selectById(userId).getDepartmentId();

        List<SysDepartment> sysDepartmentList = sysDepartmentMapper.selectList(null);
        //List<SysUserResponse> sysUserResponseChildrenList = getChildren(sysDepartmentList, departmentId);

        // return sysUserResponseChildrenList;
        return null;
    }

    @Override
    public JSONObject WXLogin(JSONObject jsonObject) {
        String openid = jsonObject.getString("openid");
        String phone = jsonObject.getString("phone");
        return null;
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
        for (SysUserResponse sysUserResponse : sysUserResponseChildrenList) {
            sysUserResponse.setChildren(getChildren(sysDepartmentList, sysUserResponse.getDepartmentId()));
        }
        return sysUserResponseChildrenList;
    }


}




