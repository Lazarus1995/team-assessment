package com.team.assessment.controller;

import com.team.assessment.common.response.BaseResponse;
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


    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    @GetMapping("/get/{userId}")
    public BaseResponse getUser(@PathVariable Long userId) {
        return BaseResponse.success(sysUserService.getUser(userId));
    }

    @GetMapping("/list")
    public BaseResponse getUserList(@Validated @RequestBody SysUserRequest sysUserRequest) {
        return BaseResponse.success(sysUserService.getUserList(sysUserRequest));
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody SysUserRequest sysUserRequest) throws Exception {

        return BaseResponse.success(sysUserService.login(sysUserRequest));
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
        //JSONObject jsonObject = WechatUtil.getSessionKeyOrOpenId(code);


        //jsonObject.put("token", TokenUtils.createToken(jsonObject.get("session_key").toString(), jsonObject.get("openid").toString(), "wxac25927737cf3994"));
        //return BaseResponse.success(jsonObject);
        return null;
    }

}
