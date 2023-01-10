package com.team.assessment.controller;

import com.team.assessment.common.enums.SysLawTypeEnum;
import com.team.assessment.common.exception.CustomException;
import com.team.assessment.common.response.BaseResponse;
import com.team.assessment.config.enums.ErrorCode;
import com.team.assessment.config.utils.DateUtils;
import com.team.assessment.model.vo.request.LogLawProcessRequest;
import com.team.assessment.service.LogLawProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/logLawProcess")
public class LogLawProcessController {

    @Autowired
    private LogLawProcessService logLawProcessService;


    @PostMapping("/addPorcess")
    public BaseResponse addLawLog(@RequestParam("file") MultipartFile file, LogLawProcessRequest logLawProcessRequest,
                                  HttpServletRequest request) {
        if (file != null && !file.isEmpty()) {
            //  原始名称
            String originalFilename = file.getOriginalFilename();

            //  图片存放的webroot物理路径
            String webroot = System.getProperty("user.home") + File.separatorChar + "upload" + File.separatorChar + DateUtils.formatDate(new Date()) + File.separatorChar;
            File saveDir = new File(webroot);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            //  新图片相对路径
            String filepath = UUID.randomUUID()
                    + originalFilename.substring(originalFilename.lastIndexOf("."));
            //  新建图片（物理路径+图片名）
            File realpath = new File(webroot + filepath);

            try {
                //  将内存中的数据写入磁盘
                file.transferTo(realpath);
            } catch (Exception e) {
                new CustomException(ErrorCode.FILE_UPLOAD_ERROR.getCode(), ErrorCode.FILE_UPLOAD_ERROR.getMessage());
            }
            //todo 待联调
            logLawProcessService.addPorcess(logLawProcessRequest.getCreateUserId(), logLawProcessRequest.getUserId()
                    , logLawProcessRequest.getLawId(), SysLawTypeEnum.getByCode(logLawProcessRequest.getLawType()), filepath);
        } else {
            throw new CustomException(ErrorCode.FILE_NULL.getCode(), ErrorCode.FILE_NULL.getMessage());
        }
        return BaseResponse.success();
    }

    @PostMapping("/download")
    public void download(@PathVariable Long userId, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        logLawProcessService.download(userId);
    }
}
