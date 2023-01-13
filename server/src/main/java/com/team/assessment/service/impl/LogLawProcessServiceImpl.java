package com.team.assessment.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.assessment.common.entry.ExcelExportEntity;
import com.team.assessment.common.entry.ExcelImageEntity;
import com.team.assessment.common.enums.LogRatingStatusEnum;
import com.team.assessment.common.enums.SysLawTypeEnum;
import com.team.assessment.common.exception.CustomException;
import com.team.assessment.config.enums.ErrorCode;
import com.team.assessment.config.utils.DateUtils;
import com.team.assessment.dao.*;
import com.team.assessment.model.entry.*;
import com.team.assessment.model.vo.request.LogLawProcessRequest;
import com.team.assessment.model.vo.response.LogLawProcessResponse;
import com.team.assessment.service.LogLawProcessService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private LogRatingMapper logRatingMapper;

    @Autowired
    private SysUserScoreMapper sysUserScoreMapper;

    @Value("${easyExcel.templatePath}")
    private String ExcelTemplatePath;

    @Value("${easyExcel.demoPath}")
    private String ExcelDemoPath;

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
                saveDir.mkdirs();
            }
            //  新图片相对路径
            String filepath = UUID.randomUUID()
                    + originalFilename.substring(originalFilename.lastIndexOf("."));
            //  新建图片（物理路径+图片名）
            realpath = new File(webroot + filepath);
            try {
                //  将内存中的数据写入磁盘
                //file.transferTo(realpath);

                FileCopyUtils.copy(file.getInputStream(), Files.newOutputStream(realpath.toPath()));
            } catch (Exception e) {
                e.printStackTrace();
                //throw new CustomException(ErrorCode.FILE_UPLOAD_ERROR.getCode(), ErrorCode.FILE_UPLOAD_ERROR.getMessage());
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
        logLawProcess.setCreateTime(LocalDateTime.now());
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

        SysLaw sysLaw = sysLawMapper.selectById(logLawProcessRequest.getLawId());
        sysLawMapper.update(sysLaw, Wrappers.lambdaUpdate(SysLaw.class)
                .set(SysLaw::getLawMonthCount, sysLaw.getLawMonthCount() + 1)
                .eq(SysLaw::getLawId, logLawProcessRequest.getLawId()));

        LogRating logRating = logRatingMapper.selectOne(Wrappers.lambdaQuery(LogRating.class)
                .eq(LogRating::getUserId, logLawProcessRequest.getUserId())
                .orderBy(true, false, LogRating::getCreateTime)
                .last("limit 1"));
        System.out.println(JSON.toJSONString(logRating));
        if (Objects.nonNull(logRating) && logRating.getRatingStatus() == LogRatingStatusEnum.UNCOMPLETE.getCode()) {
            logRatingMapper.update(logRating, Wrappers.lambdaUpdate(LogRating.class)
                    .set(LogRating::getRatingStatus, LogRatingStatusEnum.COMPLETED.getCode())
                    .eq(LogRating::getUserId, logLawProcessRequest.getUserId()));
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

    @Override
    public List<LogLawProcessResponse> getList(Long userId) {
        List<SysUser> userList = sysUserMapper.selectList(null);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<LogLawProcessResponse> result = logLawProcessMapper
                .selectList(Wrappers.lambdaQuery(LogLawProcess.class)
                        .eq(LogLawProcess::getCreateUserId, userId)
                        .orderByDesc(LogLawProcess::getCreateTime)).stream().map(item -> {
                    LogLawProcessResponse logLawProcessResponse = new LogLawProcessResponse();

                    BeanUtils.copyProperties(item, logLawProcessResponse);
                    logLawProcessResponse.setCreateTime(df.format(item.getCreateTime()));
                    return logLawProcessResponse;
                }).collect(Collectors.toList());
        return result;
    }


    /**
     * @param response
     */
    public void download1(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        download2(response);
    }

    public void download2(HttpServletResponse res) throws IOException {
        // 发送给客户端的数据
        // 读取filename
        File file = new File(ExcelDemoPath);
        long length = file.length();
        res.addHeader("Content-Length", String.valueOf(length));
        OutputStream outputStream = res.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        FileInputStream inputStream = new FileInputStream(file);
        bis = new BufferedInputStream(inputStream);
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
        bis.close();
        outputStream.close();
    }

}




