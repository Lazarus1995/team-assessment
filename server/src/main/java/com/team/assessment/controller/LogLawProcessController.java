package com.team.assessment.controller;

import com.team.assessment.common.response.BaseResponse;
import com.team.assessment.model.vo.request.LogLawProcessRequest;
import com.team.assessment.service.LogLawProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/logLawProcess")
public class LogLawProcessController {

    @Autowired
    private LogLawProcessService logLawProcessService;


    @PostMapping("/addProcess")
    public BaseResponse addLawLog(LogLawProcessRequest logLawProcessRequest) {
        //todo 待联调
        logLawProcessService.addPorcess(logLawProcessRequest);
        return BaseResponse.success();
    }

    @GetMapping("/downloadbeta")
    public void download(Long userId, HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("数据导出", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        logLawProcessService.download(response,userId);
    }

//    @GetMapping("/downloadbeta")
//    public ResponseEntity download(HttpServletResponse response) throws IOException {
//
//        String fileName = "2023-01-12(示例文件).xlsx";
//        File file = new File("/root/2023-01-12.xlsx");
//        //设置响应头
//        HttpHeaders headers = new HttpHeaders();
//        //下载显示的文件名，解决中文名称乱码问题
//        String downloadFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
//        //通知浏览器以下载方式(attachment)打开文件
//        headers.setContentDispositionFormData("attachment", downloadFileName);
//        //定义以二进制流数据(最常见的文件下载)的形式下载返回文件数据
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        //使用spring mvc框架的ResponseEntity对象封装返回下载数据
//        return new ResponseEntity(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
//    }

    @GetMapping("/getList/{userId}")
    public BaseResponse getList(@PathVariable Long userId) {
        return BaseResponse.success(logLawProcessService.getList(userId));
    }

}
