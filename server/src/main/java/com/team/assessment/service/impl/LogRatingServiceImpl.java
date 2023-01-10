package com.team.assessment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.assessment.dao.LogRatingMapper;
import com.team.assessment.model.entry.LogRating;
import com.team.assessment.service.LogRatingService;
import org.springframework.stereotype.Service;

/**
* @author qu
* @description 针对表【log_rating(打分任务日志表(当日一个 userid 只记录一条))】的数据库操作Service实现
* @createDate 2023-01-09 18:02:37
*/
@Service
public class LogRatingServiceImpl extends ServiceImpl<LogRatingMapper, LogRating>
    implements LogRatingService {

}




