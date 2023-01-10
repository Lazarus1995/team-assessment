package com.team.assessment.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.assessment.common.entry.ExcelExportEntity;
import com.team.assessment.common.entry.ExcelImageEntity;
import com.team.assessment.common.enums.SysLawTypeEnum;
import com.team.assessment.config.utils.DateUtils;
import com.team.assessment.dao.LogLawProcessMapper;
import com.team.assessment.dao.SysLawMapper;
import com.team.assessment.dao.SysUserMapper;
import com.team.assessment.model.entry.LogLawProcess;
import com.team.assessment.model.entry.SysLaw;
import com.team.assessment.model.entry.SysUser;
import com.team.assessment.service.LogLawProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private SysLawMapper sysLawMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Value("${easyExcel.templatePath}")
    private String ExcelTemplatePath;

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
    public void download(Long userId) {
        //需要填充的时间，月初和当前
        String monthTime = DateUtils.formatDate(DateUtils.getFirstDay());
        String nowTime = DateUtils.formatDate(new Date());
        //判分人名称
        SysUser sysUser = sysUserMapper.selectById(userId);
        String createUserName = sysUser.getUserName();

        List<LogLawProcess> logLawProcessesList = logLawProcessMapper.selectList(Wrappers.lambdaQuery(LogLawProcess.class)
                .eq(LogLawProcess::getCreateUserId, userId)
                .between(LogLawProcess::getCreateTime, monthTime, nowTime));

        List<ExcelExportEntity> excelExportEntities = logLawProcessesList.stream().map(logLawProcess -> {
            ExcelExportEntity excelExportEntity = new ExcelExportEntity();

            SysLaw sysLaw = sysLawMapper.selectById(logLawProcess.getLawId());
            excelExportEntity.setLawContent(sysLaw.getLawContent());
            excelExportEntity.setLawType(SysLawTypeEnum.getByCode(logLawProcess.getLawType()).getDesc());
            excelExportEntity.setCreateUserName(createUserName);
            //添加图片
            try {
                ExcelImageEntity excelImageEntity = new ExcelImageEntity();
                excelImageEntity.setUrl(new URL(logLawProcess.getPicUrl()));

            } catch (MalformedURLException e) {
                excelExportEntity.setPic(null);
            }
            //todo shit
            //excelExportEntity.setCreataTime(DateUtils.formatDate(logLawProcess.getCreateTime()));
            return excelExportEntity;
        }).collect(Collectors.toList());

        try{
            //导出文件名
            String fileName = URLEncoder.encode("评分日志", "UTF-8").replaceAll("\\+", "%20")
                    + DateUtils.formatDate(new Date()) + ".xlsx";
            EasyExcel.write(fileName).withTemplate(ExcelTemplatePath).sheet().doFill(excelExportEntities);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}




