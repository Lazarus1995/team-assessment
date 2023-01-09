package com.team.assessment.controller;

import com.team.assessment.SysLawService;
import com.team.assessment.dto.response.BaseResponse;
import com.team.assessment.vo.request.SysLawRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/law")
public class SysLawController {

    @Autowired
    private SysLawService sysLawService;

    @GetMapping("/get/{id}")
    public BaseResponse getLaw(@PathVariable("id") Long id) {
        return BaseResponse.success(sysLawService.getLaw(id));
    }

    @GetMapping("/list")
    public BaseResponse getLawList(@RequestBody @Validated SysLawRequest sysLawRequest) {
        return BaseResponse.success(sysLawService.getLawList(sysLawRequest));
    }

    @PostMapping("/add")
    public BaseResponse addLaw(@RequestBody @Validated SysLawRequest sysLawRequest) {
        sysLawService.addLaw(sysLawRequest);
        return BaseResponse.success();
    }

    @PostMapping("/update")
    public BaseResponse updateLaw(@RequestBody @Validated SysLawRequest sysLawRequest) {

        return BaseResponse.success();
    }

    @PostMapping("/delete")
    public BaseResponse deleteLaw(@RequestBody List<Long> idList) {
        return BaseResponse.success(sysLawService.removeBatchByIds(idList));
    }
}
