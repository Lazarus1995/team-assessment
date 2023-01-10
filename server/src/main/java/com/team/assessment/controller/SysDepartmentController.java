package com.team.assessment.controller;

import com.team.assessment.common.response.BaseResponse;
import com.team.assessment.service.SysDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class SysDepartmentController {

    @Autowired
    private SysDepartmentService sysDepartmentService;

    @GetMapping("/get/{departmentId}")
    public BaseResponse getDepartment(@PathVariable Long departmentId){
        return BaseResponse.success();
    }
    @GetMapping("/list")
    public BaseResponse getDepartmentList(){
        return BaseResponse.success();
    }

    @GetMapping("/listChildren/{departmentId}")
    public BaseResponse getDepartmentListChildren(@PathVariable Long departmentId){
        return BaseResponse.success(sysDepartmentService.getDepartmentListChildren(departmentId));
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
