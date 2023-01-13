package com.team.assessment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team.assessment.model.entry.LogRating;
import org.springframework.stereotype.Repository;

/**
* @author qu
* @description 针对表【log_rating(打分任务日志表(当日一个 userid 只记录一条))】的数据库操作Mapper
* @createDate 2023-01-09 18:02:37
* @Entity generator.domain.LogRating
*/
@Repository
public interface LogRatingMapper extends BaseMapper<LogRating> {

}




