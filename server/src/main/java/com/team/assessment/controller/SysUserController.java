package com.team.assessment.controller;

import com.alibaba.fastjson2.JSONObject;
import com.team.assessment.common.response.BaseResponse;
import com.team.assessment.config.utils.WechatUtil;
import com.team.assessment.model.vo.request.SysUserRequest;
import com.team.assessment.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    @GetMapping("/get/{userId}")
    public BaseResponse getUser(@PathVariable Long userId) {
        return BaseResponse.success(sysUserService.getUser(userId));
    }

    @GetMapping("/list")
    public BaseResponse getUserList(@Validated @RequestBody SysUserRequest sysUserRequest) {
        return BaseResponse.success(sysUserService.getUserList(sysUserRequest));
    }

    @GetMapping("/listChildren/{userId}")
    public BaseResponse getUserListChildren(@PathVariable Long userId) {
        //todo 还有 bug
        return BaseResponse.success(sysUserService.getUserListChildren(userId));
    }

    @PostMapping("add")
    public BaseResponse addUser() {
        return BaseResponse.success();
    }

    @PostMapping("update")
    public BaseResponse updateUser() {
        return BaseResponse.success();
    }

    @PostMapping("delete")
    public BaseResponse deleteUser() {
        return BaseResponse.success();
    }

    @GetMapping("/WXLogin")
    public BaseResponse WXLogin(String code) throws IOException {
        System.out.println(code);
        JSONObject jsonObject = WechatUtil.getSessionKeyOrOpenId(code);
        jsonObject.put("appid", "wxac25927737cf3994");
        jsonObject.put("token", "ttookkeenn");

        //jsonObject.put("token", TokenUtils.createToken(jsonObject.get("session_key").toString(), jsonObject.get("openid").toString(), "wxac25927737cf3994"));
        return BaseResponse.success(jsonObject);
    }
}
