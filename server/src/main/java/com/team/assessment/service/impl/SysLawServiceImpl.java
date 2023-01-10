package com.team.assessment.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.assessment.dao.SysLawMapper;
import com.team.assessment.dao.SysUserMapper;
import com.team.assessment.model.entry.SysLaw;
import com.team.assessment.model.vo.request.SysLawRequest;
import com.team.assessment.model.vo.response.SysLawResponse;
import com.team.assessment.service.SysLawService;
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
    public List<SysLawResponse> getLawList(Long departmentId) {
        return sysLawMapper.selectList(Wrappers.lambdaQuery(SysLaw.class)
                        .eq(SysLaw::getDepartmentId, departmentId))
                .stream().map(SysLawResponse::convert).collect(Collectors.toList());
    }

    @Override
    public void addLaw(SysLawRequest sysLawRequest) {
        SysLaw sysLaw = new SysLaw();
        sysLaw.setLawScore(sysLawRequest.getLawScore());
        sysLaw.setLawContent(sysLawRequest.getLawContent());
        sysLaw.setDepartmentId(sysLawRequest.getDepartmentId());
        sysLaw.setLawType(sysLawRequest.getLawType());
        sysLawMapper.insert(sysLaw);
    }

    @Override
    public void updateLaw(SysLawRequest sysLawRequest) {
        SysLaw sysLaw = new SysLaw();
        sysLaw.setLawId(sysLawRequest.getLawId());
        sysLaw.setLawScore(sysLawRequest.getLawScore());
        sysLaw.setLawContent(sysLawRequest.getLawContent());
        sysLaw.setDepartmentId(sysLawRequest.getDepartmentId());
        sysLaw.setLawType(sysLawRequest.getLawType());
        sysLawMapper.updateById(sysLaw);
    }
}




