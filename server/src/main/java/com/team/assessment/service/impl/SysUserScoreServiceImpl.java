package com.team.assessment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.assessment.config.utils.DateUtils;
import com.team.assessment.dao.SysDepartmentMapper;
import com.team.assessment.dao.SysUserMapper;
import com.team.assessment.dao.SysUserScoreMapper;
import com.team.assessment.model.entry.SysDepartment;
import com.team.assessment.model.entry.SysUserScore;
import com.team.assessment.model.vo.response.SysDepartmentResponse;
import com.team.assessment.model.vo.response.SysScoreResponse;
import com.team.assessment.service.SysUserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author qu
 * @description 针对表【sys_user_score】的数据库操作Service实现
 * @createDate 2023-01-11 01:27:37
 */
@Service
public class SysUserScoreServiceImpl extends ServiceImpl<SysUserScoreMapper, SysUserScore>
        implements SysUserScoreService {

    @Autowired
    public SysUserScoreMapper sysUserScoreMapper;

    @Autowired
    public SysUserMapper sysUserMapper;

    @Autowired
    public SysDepartmentMapper sysDepartmentMapper;

    @Override
    public List<SysScoreResponse> getScoreList(Long departmentId, Long totalSalary) {

        //获取部门信息
        List<SysDepartment> tempDepartmentList = sysDepartmentMapper.selectList(null);
        //完整部门信息
        List<Long> tempList = getChildren(tempDepartmentList, departmentId, new ArrayList<>());

        Long year = Long.parseLong(DateUtils.getNowYear()), month = Long.parseLong(DateUtils.getNowMonth());
        Integer totleScore = sysUserScoreMapper.getTotleScore(departmentId, year, month);
        Double scoreMoney;
        if (totalSalary != null && totleScore != null) {
            scoreMoney = (double) (totleScore / totalSalary);
        } else {
            scoreMoney = 0.0;
        }

        List<Map<String, Object>> userScoreList = sysUserScoreMapper.getUserScoreList(departmentId, year, month);
        List<SysScoreResponse> result = userScoreList.stream().map(item -> {
            SysScoreResponse sysScoreResponse = new SysScoreResponse();
            sysScoreResponse.setUserId(Long.valueOf(item.get("userId").toString()));
            sysScoreResponse.setUserName(item.get("userName").toString());
            int score = Integer.valueOf(item.get("score").toString());
            sysScoreResponse.setCurrentScore(score);
            sysScoreResponse.setIssuedAmount(totalSalary != null && totleScore != null ? scoreMoney * score : 0.0);
            return sysScoreResponse;
        }).collect(Collectors.toList());

        return result;
    }


    private List<Long> getChildren(List<SysDepartment> sysDepartmentList, Long departmentId, List<Long> tempDepartmentList) {

        List<SysDepartmentResponse> result = sysDepartmentList.stream()
                .filter(sysDepartment -> sysDepartment.getParentId().equals(departmentId)).map(sysDepartment -> {
                    SysDepartmentResponse sysDepartmentResponse = SysDepartmentResponse.convert(sysDepartment);
                    return sysDepartmentResponse;
                }).collect(Collectors.toList());
        for (SysDepartmentResponse sysDepartmentResponse : result) {
            List<Long> children = getChildren(sysDepartmentList, sysDepartmentResponse.getId(), tempDepartmentList);
            if (Objects.isNull(children)) {
                tempDepartmentList.add(sysDepartmentResponse.getId());
            }
        }
        if (result.size() == 0) {
            return null;
        }
        return tempDepartmentList;
    }
}




