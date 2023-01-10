package com.team.assessment.controller;


import com.team.assessment.common.response.BaseResponse;
import com.team.assessment.model.vo.request.SysLawRequest;
import com.team.assessment.service.SysLawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/law")
public class SysLawController {

    @Autowired
    private SysLawService sysLawService;

    @GetMapping("/get/{id}")
    public BaseResponse getLaw(@PathVariable("id") Long id) {
        return BaseResponse.success(sysLawService.getLaw(id));
    }

    @GetMapping("/list/{departmentId}")
    public BaseResponse getLawList(@PathVariable Long departmentId) {
        return BaseResponse.success(sysLawService.getLawList(departmentId));
    }

    @PostMapping("/add")
    public BaseResponse addLaw(@RequestBody SysLawRequest sysLawRequest) {
        sysLawService.addLaw(sysLawRequest);
        return BaseResponse.success();
    }

    @PostMapping("/update")
    public BaseResponse updateLaw(@RequestBody SysLawRequest sysLawRequest) {
        sysLawService.updateLaw(sysLawRequest);
        return BaseResponse.success();
    }

    @PostMapping("/delete")
    public BaseResponse deleteLaw(@RequestBody SysLawRequest sysLawRequest) {
        return BaseResponse.success(sysLawService.removeById(Long.parseLong(sysLawRequest.getLawId())));
    }
}
