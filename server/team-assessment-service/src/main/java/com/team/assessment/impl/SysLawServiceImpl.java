package com.team.assessment.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.assessment.SysLawService;
import com.team.assessment.entry.SysLaw;
import com.team.assessment.mapper.SysLawMapper;
import com.team.assessment.mapper.SysUserMapper;
import com.team.assessment.vo.request.SysLawRequest;
import com.team.assessment.vo.response.SysLawResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qu
 * @description 针对表【sys_law】的数据库操作Service实现
 * @createDate 2023-01-07 12:26:43
 */
@Service
public class SysLawServiceImpl extends ServiceImpl<SysLawMapper, SysLaw>
        implements SysLawService {

    @Autowired
    public SysLawMapper sysLawMapper;

    @Autowired
    public SysUserMapper sysUserMapper;

    @Override
    public SysLaw getLaw(Long id) {
        return sysLawMapper.selectById(id);
    }

    @Override
    public List<SysLawResponse> getLawList(SysLawRequest sysLawRequest) {
        return sysLawMapper.selectList(Wrappers.lambdaQuery(SysLaw.class)
                        .eq(SysLaw::getDepartmentId, sysLawRequest.getDepartmentId())
                        .orderBy(sysLawRequest.getOrderByFrequency(), sysLawRequest.getAesOrder(), SysLaw::getLawMonthCount))
                .stream().map(SysLawResponse::convert).collect(Collectors.toList());
    }

    @Override
    public void addLaw(SysLawRequest sysLawRequest) {
        //todo 进行角色权限校验
        SysLaw sysLaw = new SysLaw();
        sysLaw.setLawContent(sysLawRequest.getLawContent());
        sysLaw.setLawScore(sysLawRequest.getLawScore());
        sysLaw.setDepartmentId(sysLawRequest.getDepartmentId());
        sysLaw.setCreateUserId(sysLawRequest.getUserId());
        sysLawMapper.insert(sysLaw);
    }
}




