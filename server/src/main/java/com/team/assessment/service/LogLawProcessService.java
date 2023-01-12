package com.team.assessment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team.assessment.model.entry.LogLawProcess;
import com.team.assessment.model.vo.request.LogLawProcessRequest;

/**
* @author qu
* @description 针对表【log_law_process(小立法评分日志表)】的数据库操作Service
* @createDate 2023-01-09 18:02:37
*/
public interface LogLawProcessService extends IService<LogLawProcess> {
    /**
     * 小立法评分日志
     */
    void addPorcess(LogLawProcessRequest logLawProcessRequest);

    void download(Long userId);
}
