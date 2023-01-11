package com.team.assessment.controller;

import com.team.assessment.common.response.BaseResponse;
import com.team.assessment.service.SysUserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/userScore")
public class SysUserScoreController {

    @Autowired
    private SysUserScoreService sysUserScoreService;

    @GetMapping("/list/{departmentId}/{totalSalary}")
    public BaseResponse getScoreList(@PathVariable Long departmentId,@PathVariable("totalSalary") Long totalSalary) {
        return BaseResponse.success(sysUserScoreService.getScoreList(departmentId,totalSalary));
    }
}
