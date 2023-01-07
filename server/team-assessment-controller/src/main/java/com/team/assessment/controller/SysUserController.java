package com.team.assessment.controller;

import com.team.assessment.SysUserService;
import com.team.assessment.dto.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    @GetMapping("/get")
    public BaseResponse getUser(){
        return BaseResponse.success();
    }

    @GetMapping("list")
    public BaseResponse getUserList(){
        return BaseResponse.success();
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
