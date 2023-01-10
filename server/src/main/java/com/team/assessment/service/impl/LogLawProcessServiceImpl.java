package com.team.assessment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.team.assessment.common.enums.SysLawTypeEnum;
import com.team.assessment.dao.LogLawProcessMapper;
import com.team.assessment.model.entry.LogLawProcess;
import com.team.assessment.service.LogLawProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author qu
* @description 针对表【log_law_process(小立法评分日志表)】的数据库操作Service实现
* @createDate 2023-01-09 18:02:37
*/
@Service
public class LogLawProcessServiceImpl extends ServiceImpl<LogLawProcessMapper, LogLawProcess>
    implements LogLawProcessService {

    @Autowired
    private LogLawProcessMapper logLawProcessMapper;

    /**
     * 小立法评分日志
     *
     * @param createId
     * @param workers
     * @param lawID
     * @param sysLawTypeEnum
     */
    @Override
    public void addPorcess(Long createId, Long workers, Long lawID, SysLawTypeEnum sysLawTypeEnum,String picUrl) {
        LogLawProcess logLawProcess = new LogLawProcess();
        logLawProcess.setCreateUserId(createId);
        logLawProcess.setLawId(lawID);
        logLawProcess.setLawType(sysLawTypeEnum.getCode());
        logLawProcess.setUserId(workers);
        logLawProcess.setPicUrl(picUrl);
        logLawProcessMapper.insert(logLawProcess);
    }

    /**
     * 进行数据下载
     * @param userId
     * @return
     */
    @Override
    public String download(Long userId) {
        return null;
    }
}




