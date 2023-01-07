package com.ta.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ta.SysLogService;
import com.ta.entry.SysLog;
import com.ta.mapper.SysLogMapper;
import org.springframework.stereotype.Service;

/**
* @author qu
* @description 针对表【sys_log】的数据库操作Service实现
* @createDate 2023-01-07 12:21:22
*/
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog>
    implements SysLogService {

}




