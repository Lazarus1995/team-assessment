package com.team.assessment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team.assessment.model.entry.LogRating;

import java.util.List;


/**
* @author qu
* @description 针对表【log_rating(打分任务日志表(当日一个 userid 只记录一条))】的数据库操作Service
* @createDate 2023-01-09 18:02:37
*/
public interface LogRatingService extends IService<LogRating> {


    List<LogRating> getTodo(Long userId,Integer type);
}
