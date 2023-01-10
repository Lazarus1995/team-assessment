package com.team.assessment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team.assessment.common.enums.SysLawTypeEnum;
import com.team.assessment.model.entry.LogLawProcess;

/**
* @author qu
* @description 针对表【log_law_process(小立法评分日志表)】的数据库操作Service
* @createDate 2023-01-09 18:02:37
*/
public interface LogLawProcessService extends IService<LogLawProcess> {
    /**
     * 小立法评分日志
     */
    void addPorcess(Long createUserId, Long userId, Long lawID, SysLawTypeEnum sysLawTypeEnum,String picUrl);

    String download(Long userId);
}
