package com.team.assessment.controller;

import com.team.assessment.common.response.BaseResponse;
import com.team.assessment.service.LogRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logRating")
public class LogRatingController {

    @Autowired
    private LogRatingService logRatingService;

    @GetMapping("/getTodo")
    public BaseResponse getTodo(Long userId,Integer type) {
        return BaseResponse.success(logRatingService.getTodo(userId,type));
    }
}
