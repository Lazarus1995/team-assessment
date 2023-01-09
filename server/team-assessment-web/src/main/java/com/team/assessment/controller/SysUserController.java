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


    @GetMapping("/get/{userId}")
    public BaseResponse getUser(@PathVariable Long userId){
        return BaseResponse.success(sysUserService.getUser(userId));
    }

    @GetMapping("/list")
    public BaseResponse getUserList(@Validated @RequestBody SysUserRequest sysUserRequest){
        return BaseResponse.success(sysUserService.getUserList(sysUserRequest));
    }

    @GetMapping("/listChildren/{userId}")
    public BaseResponse getUserListChildren(@PathVariable Long userId){
        //todo 还有 bug
        return BaseResponse.success(sysUserService.getUserListChildren(userId));
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
