package com.team.assessment;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team.assessment.entry.LogRating;

/**
* @author qu
* @description 针对表【log_rating(打分任务日志表(当日一个 userid 只记录一条))】的数据库操作Service
* @createDate 2023-01-09 18:02:37
*/
public interface LogRatingService extends IService<LogRating> {

}
