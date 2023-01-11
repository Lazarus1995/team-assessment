package com.team.assessment.controller;

import com.team.assessment.common.response.BaseResponse;
import com.team.assessment.service.SysUserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userScore")
public class SysUserScoreController {

    @Autowired
    private SysUserScoreService sysUserScoreService;

    @GetMapping("/list")
    public BaseResponse getScoreList( Long departmentId, Long totalSalary) {
        return BaseResponse.success(sysUserScoreService.getScoreList(departmentId,totalSalary));
    }
}
