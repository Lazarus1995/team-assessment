package com.team.assessment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team.assessment.model.entry.SysLog;
import com.team.assessment.model.vo.request.SysLogRequest;

/**
* @author qu
* @description 针对表【sys_log】的数据库操作Service
* @createDate 2023-01-07 12:21:22
*/
public interface SysLogService extends IService<SysLog> {


    void addLog(SysLogRequest sysLogRequest);

}
