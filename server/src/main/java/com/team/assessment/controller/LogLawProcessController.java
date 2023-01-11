package com.team.assessment.controller;

import com.team.assessment.common.response.BaseResponse;
import com.team.assessment.model.vo.request.LogLawProcessRequest;
import com.team.assessment.service.LogLawProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/logLawProcess")
public class LogLawProcessController {

    @Autowired
    private LogLawProcessService logLawProcessService;


    @PostMapping("/addPorcess")
    public BaseResponse addLawLog(LogLawProcessRequest logLawProcessRequest) {
        //todo 待联调
        logLawProcessService.addPorcess(logLawProcessRequest);
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
