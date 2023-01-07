package com.team.assessment.controller;

import com.team.assessment.SysDepartmentService;
import com.team.assessment.dto.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class SysDepartmentController {

    @Autowired
    private SysDepartmentService sysDepartmentService;

    @GetMapping("/get")
    public BaseResponse getDepartment(){
        return BaseResponse.success();
    }
    @GetMapping("/list")
    public BaseResponse getDepartmentList(){
        return BaseResponse.success();
    }

    @PostMapping("/add")
    public BaseResponse addDepartment(){
        return BaseResponse.success();
    }

    @PostMapping("/update")
    public BaseResponse updateDepartment(){
        return BaseResponse.success();
    }

    @PostMapping("/delete")
    public BaseResponse deleteDepartment(){
        return BaseResponse.success();
    }
}
