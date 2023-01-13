package com.team.assessment.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.team.assessment.common.response.BaseResponse;
import com.team.assessment.model.entry.SysLog;
import com.team.assessment.model.vo.request.SysLogRequest;
import com.team.assessment.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @PostMapping("/add")
    public BaseResponse addLog(SysLogRequest sysLogRequest) {
        sysLogService.addLog(sysLogRequest);
        return BaseResponse.success();
    }

    @GetMapping("/list")
    public BaseResponse logList(Long userId) {
        return BaseResponse.success(sysLogService.list(Wrappers.<SysLog>lambdaQuery().eq(SysLog::getCreateUserId, userId)));

    }
}
