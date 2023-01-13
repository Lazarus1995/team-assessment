package com.team.assessment.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.assessment.dao.LogRatingMapper;
import com.team.assessment.model.entry.LogRating;
import com.team.assessment.service.LogRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qu
 * @description 针对表【log_rating(打分任务日志表(当日一个 userid 只记录一条))】的数据库操作Service实现
 * @createDate 2023-01-09 18:02:37
 */
@Service
public class LogRatingServiceImpl extends ServiceImpl<LogRatingMapper, LogRating>
        implements LogRatingService {

    @Autowired
    private LogRatingMapper logRatingMapper;

    @Override
    public List<LogRating> getTodo(Long userId,Integer type) {
        return logRatingMapper.selectList(Wrappers.<LogRating>lambdaQuery()
                .eq(LogRating::getUserId, userId)
                .eq(LogRating::getRatingStatus,type));
    }
}




