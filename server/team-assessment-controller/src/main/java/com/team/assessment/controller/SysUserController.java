package com.team.assessment.controller;

import com.team.assessment.SysUserService;
import com.team.assessment.dto.response.BaseResponse;
import com.team.assessment.vo.request.SysUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    @GetMapping("/get/{id}")
    public BaseResponse getUser(@PathVariable Long id){
        return BaseResponse.success(sysUserService.getUser(id));
    }

    @GetMapping("/list")
    public BaseResponse getUserList(@Validated @RequestBody SysUserRequest sysUserRequest){
        return BaseResponse.success(sysUserService.getUserList(sysUserRequest));
    }

    @PostMapping("add")
    public BaseResponse addUser(){
        return BaseResponse.success();
    }

    @PostMapping("update")
    public BaseResponse updateUser(){
        return BaseResponse.success();
    }

    @PostMapping("delete")
    public BaseResponse deleteUser(){
        return BaseResponse.success();
    }
}
