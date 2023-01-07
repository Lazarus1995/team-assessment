package com.team.assessment.controller;

import com.team.assessment.SysLawService;
import com.team.assessment.dto.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/law")
public class SysLawController {

    @Autowired
    private SysLawService sysLawService;

    @GetMapping("/get")
    public BaseResponse getLaw() {

        return BaseResponse.success();
    }

    @GetMapping("/list")
    public BaseResponse getLawList() {

        return BaseResponse.success();
    }

    @PostMapping("/add")
    public BaseResponse addLaw() {

        return BaseResponse.success();
    }

    @PostMapping("/update")
    public BaseResponse updateLaw() {

        return BaseResponse.success();
    }

    @PostMapping("/delete")
    public BaseResponse deleteLaw() {

        return BaseResponse.success();
    }
}
