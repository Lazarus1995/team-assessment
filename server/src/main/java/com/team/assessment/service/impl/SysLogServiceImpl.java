package com.team.assessment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.assessment.common.exception.CustomException;
import com.team.assessment.config.enums.ErrorCode;
import com.team.assessment.config.utils.DateUtils;
import com.team.assessment.dao.SysLogMapper;
import com.team.assessment.model.entry.SysLog;
import com.team.assessment.model.vo.request.SysLogRequest;
import com.team.assessment.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

/**
 * @author qu
 * @description 针对表【sys_log】的数据库操作Service实现
 * @createDate 2023-01-07 12:21:22
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog>
        implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void addLog(SysLogRequest sysLogRequest) {

        MultipartFile file = sysLogRequest.getFile();
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

        SysLog sysLog = new SysLog();
        sysLog.setTheme(sysLogRequest.getTheme());
//        sysLog.setLogType(sysLogRequest.getLogType());
        sysLog.setContent(sysLogRequest.getContent());
        sysLog.setPicUrl(realpath.getPath());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(sysLogRequest.getCreateTime());
        } catch (ParseException e) {
            e.printStackTrace();

        }
        sysLog.setCreateTime(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        sysLog.setCreateUserId(sysLogRequest.getLogUserId());
        sysLogMapper.insert(sysLog);
    }
}




