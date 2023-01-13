package com.team.assessment.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.assessment.dao.SysDepartmentMapper;
import com.team.assessment.dao.SysLawMapper;
import com.team.assessment.dao.SysUserMapper;
import com.team.assessment.model.entry.SysDepartment;
import com.team.assessment.model.entry.SysLaw;
import com.team.assessment.model.vo.request.SysLawRequest;
import com.team.assessment.model.vo.response.SysDepartmentResponse;
import com.team.assessment.model.vo.response.SysLawResponse;
import com.team.assessment.service.SysLawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @Autowired
    public SysDepartmentMapper sysDepartmentMapper;

    @Override
    public SysLaw getLaw(Long id) {
        return sysLawMapper.selectById(id);
    }

    /**
     * 查询小立法列表
     *
     * @param departmentId
     * @return
     */
    @Override
    public List<SysLawResponse> getLawList(Long departmentId,Integer lawType) {
        SysDepartment sysDepartment = sysDepartmentMapper.selectById(departmentId);

        //获取部门信息
        List<SysDepartment> tempDepartmentList = sysDepartmentMapper.selectList(null);

        //完整部门信息
        List<Long> tempList = getChildren(tempDepartmentList, departmentId, new ArrayList<>());
        List<SysLawResponse> result = new ArrayList<>();
        for (Long tempDepartmentId : tempList) {
            List<SysLaw> tempSysLawTemp = sysLawMapper.selectList(Wrappers.lambdaQuery(SysLaw.class)
                    .eq(SysLaw::getDepartmentId, tempDepartmentId).eq(lawType!=null,SysLaw::getLawType,lawType));
            if (tempSysLawTemp.size() > 0) {
                //当前小立法所属部门的完整结构,取数组第一个
                Long tempId = tempSysLawTemp.get(0).getDepartmentId();
                StringBuilder stringBuilder = new StringBuilder();
                String departmentStr = getParentDepartment(stringBuilder, tempDepartmentList, tempId);
                String departmentName = sysDepartmentMapper.selectById(tempId).getDepartmentName();

                List<SysLawResponse> tempSysLawResponseList = tempSysLawTemp.stream().map(tempSysLaw -> {
                    SysLawResponse sysLawResponse = SysLawResponse.convert(tempSysLaw);
                    sysLawResponse.setDepartmentName(departmentName);
                    sysLawResponse.setBelong(departmentStr);
                    result.add(sysLawResponse);
                    return sysLawResponse;
                }).collect(Collectors.toList());
            }
        }
        return result;
    }


    private List<Long> getChildren(List<SysDepartment> sysDepartmentList, Long departmentId, List<Long> tempDepartmentList) {

        List<SysDepartmentResponse> result = sysDepartmentList.stream()
                .filter(sysDepartment -> sysDepartment.getParentId().equals(departmentId)).map(sysDepartment -> {
                    SysDepartmentResponse sysDepartmentResponse = SysDepartmentResponse.convert(sysDepartment);
                    return sysDepartmentResponse;
                }).collect(Collectors.toList());
        if (result.size() == 0) {
            tempDepartmentList.add(departmentId);
            return tempDepartmentList;
        }
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
        sysLaw.setLawId(Long.parseLong(sysLawRequest.getLawId()));
        sysLaw.setLawScore(sysLawRequest.getLawScore());
        sysLaw.setLawContent(sysLawRequest.getLawContent());
//        sysLaw.setDepartmentId(sysLawRequest.getDepartmentId());
        sysLaw.setUpdateUserId(sysLawRequest.getUserId());
//        sysLaw.setLawType(sysLawRequest.getLawType());
        sysLawMapper.updateById(sysLaw);
    }


    /**
     * 获取上级部门信息
     *
     * @param sysDepartmentList
     * @param departmentId
     * @return
     */
    private String getParentDepartment(StringBuilder stringBuilder, List<SysDepartment> sysDepartmentList, Long departmentId) {
        //获取当前部门 ID 的 parentID
        SysDepartment sysDepartmentTemp = sysDepartmentList
                .stream().filter(sysDepartment -> sysDepartment.getId().equals(departmentId)).findFirst().get();

        List<SysDepartment> result = sysDepartmentList.stream()
                .filter(sysDepartment -> sysDepartment.getId().equals(sysDepartmentTemp.getParentId())).collect(Collectors.toList());
        for (SysDepartment sysDepartment : result) {
            //添加叶子部门
            if (stringBuilder.length() == 0) {
                stringBuilder.append(sysDepartmentTemp.getDepartmentName());
            }
            stringBuilder.insert(0, sysDepartment.getDepartmentName() + "/");
            if (sysDepartment.getParentId() != 0) {
                getParentDepartment(stringBuilder, sysDepartmentList, sysDepartment.getParentId());
            }
        }
        return stringBuilder.toString();
    }
}




