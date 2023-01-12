package com.team.assessment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team.assessment.model.entry.SysUserScore;
import com.team.assessment.model.vo.response.SysScoreResponse;

import java.util.List;

/**
* @author qu
* @description 针对表【sys_user_score】的数据库操作Service
* @createDate 2023-01-11 01:27:37
*/
public interface SysUserScoreService extends IService<SysUserScore> {

    List<SysScoreResponse> getScoreList(Long departmentId,Long totalSalary);

}
