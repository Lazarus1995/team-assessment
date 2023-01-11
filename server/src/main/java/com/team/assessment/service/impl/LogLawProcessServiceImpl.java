package com.team.assessment.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.assessment.common.entry.ExcelExportEntity;
import com.team.assessment.common.entry.ExcelImageEntity;
import com.team.assessment.common.enums.SysLawTypeEnum;
import com.team.assessment.common.exception.CustomException;
import com.team.assessment.config.enums.ErrorCode;
import com.team.assessment.config.utils.DateUtils;
import com.team.assessment.dao.LogLawProcessMapper;
import com.team.assessment.dao.SysLawMapper;
import com.team.assessment.dao.SysUserMapper;
import com.team.assessment.dao.SysUserScoreMapper;
import com.team.assessment.model.entry.LogLawProcess;
import com.team.assessment.model.entry.SysLaw;
import com.team.assessment.model.entry.SysUser;
import com.team.assessment.model.entry.SysUserScore;
import com.team.assessment.model.vo.request.LogLawProcessRequest;
import com.team.assessment.service.LogLawProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
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

    @Autowired
    private SysUserScoreMapper sysUserScoreMapper;

    @Value("${easyExcel.templatePath}")
    private String ExcelTemplatePath;

    /**
     * 小立法评分日志
     */
    @Transactional
    @Override
    public void addPorcess(LogLawProcessRequest logLawProcessRequest) {

        MultipartFile file = logLawProcessRequest.getFile();
        File realpath = null;
        if (file != null && !file.isEmpty()) {
            //  原始名称
            String originalFilename = file.getOriginalFilename();
            //  图片存放的webroot物理路径
            String webroot = System.getProperty("user.home") + File.separatorChar + "upload" + File.separatorChar + DateUtils.formatDate(new Date()) + File.separatorChar;
            System.out.println(webroot);
            File saveDir = new File(webroot);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            //  新图片相对路径
            String filepath = UUID.randomUUID()
                    + originalFilename.substring(originalFilename.lastIndexOf("."));
            //  新建图片（物理路径+图片名）
            realpath = new File(webroot + filepath);
            try {
                //  将内存中的数据写入磁盘
                file.transferTo(realpath);
            } catch (Exception e) {
                new CustomException(ErrorCode.FILE_UPLOAD_ERROR.getCode(), ErrorCode.FILE_UPLOAD_ERROR.getMessage());
            }
        } else {
            throw new CustomException(ErrorCode.FILE_NULL.getCode(), ErrorCode.FILE_NULL.getMessage());
        }

        LogLawProcess logLawProcess = new LogLawProcess();
        logLawProcess.setLawId(logLawProcessRequest.getLawId());
        logLawProcess.setUserId(logLawProcessRequest.getUserId());
        logLawProcess.setLawType(logLawProcessRequest.getLawType());
        logLawProcess.setContent(logLawProcessRequest.getContent());
        logLawProcess.setPicUrl(realpath.getPath());
        logLawProcess.setCreateUserId(logLawProcessRequest.getCreateUserId());
        logLawProcessMapper.insert(logLawProcess);

        SysLawTypeEnum sysLawTypeEnum = SysLawTypeEnum.getByCode(logLawProcessRequest.getLawType());
        String year = DateUtils.getNowYear(), month = DateUtils.getNowMonth();

        SysUserScore sysUserScore = sysUserScoreMapper.selectOne(Wrappers.lambdaQuery(SysUserScore.class)
                .eq(SysUserScore::getUserId, logLawProcessRequest.getUserId())
                .eq(SysUserScore::getYear, year)
                .eq(SysUserScore::getMonth, month));
        if (!Objects.isNull(sysUserScore)) {
            switch (sysLawTypeEnum) {
                case REJECT:
                    sysUserScoreMapper.update(sysUserScore, Wrappers.lambdaUpdate(SysUserScore.class)
                            .set(SysUserScore::getScore, 0)
                            .eq(SysUserScore::getUserId, logLawProcessRequest.getUserId())
                            .eq(SysUserScore::getYear, year)
                            .eq(SysUserScore::getMonth, month));
                    break;
                case ADD:
                    sysUserScoreMapper.update(sysUserScore, Wrappers.lambdaUpdate(SysUserScore.class)
                            .set(SysUserScore::getScore, sysUserScore.getScore() + logLawProcessRequest.getLawScore())
                            .eq(SysUserScore::getUserId, logLawProcessRequest.getUserId())
                            .eq(SysUserScore::getYear, year)
                            .eq(SysUserScore::getMonth, month));
                    break;
                case REDUCE:
                    sysUserScoreMapper.update(sysUserScore, Wrappers.lambdaUpdate(SysUserScore.class)
                            .set(SysUserScore::getScore, sysUserScore.getScore() - logLawProcessRequest.getLawScore())
                            .eq(SysUserScore::getUserId, logLawProcessRequest.getUserId())
                            .eq(SysUserScore::getYear, year)
                            .eq(SysUserScore::getMonth, month));
                    break;
                default:
                    throw new CustomException(ErrorCode.SC_INTERNAL_SERVER_ERROR.getCode(), ErrorCode.SC_INTERNAL_SERVER_ERROR.getMessage());
            }
        }

    }

    /**
     * 进行数据下载
     *
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

        try {
            //导出文件名
            String fileName = URLEncoder.encode("评分日志", "UTF-8").replaceAll("\\+", "%20")
                    + DateUtils.formatDate(new Date()) + ".xlsx";
            EasyExcel.write(fileName).withTemplate(ExcelTemplatePath).sheet().doFill(excelExportEntities);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}




