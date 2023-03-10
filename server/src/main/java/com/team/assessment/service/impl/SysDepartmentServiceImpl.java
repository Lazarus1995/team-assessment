package com.team.assessment.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.assessment.dao.SysDepartmentMapper;
import com.team.assessment.model.entry.SysDepartment;
import com.team.assessment.model.vo.request.SysDepartmentRequest;
import com.team.assessment.model.vo.response.SysDepartmentResponse;
import com.team.assessment.service.SysDepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author qu
 * @description 针对表【sys_department】的数据库操作Service实现
 * @createDate 2023-01-07 12:21:27
 */
@Service
public class SysDepartmentServiceImpl extends ServiceImpl<SysDepartmentMapper, SysDepartment>
        implements SysDepartmentService {

    @Autowired
    public SysDepartmentMapper sysDepartmentMapper;

    @Override
    public SysDepartmentResponse getDepartment(Long departmentId) {
        SysDepartment sysDepartment = sysDepartmentMapper.selectById(departmentId);
        SysDepartmentResponse sysDepartmentResponse = new SysDepartmentResponse();
        BeanUtils.copyProperties(sysDepartment, sysDepartmentResponse);
        return sysDepartmentResponse;
    }

    @Override
    public List<SysDepartmentResponse> getDepartmentList(SysDepartmentRequest sysDepartmentRequest) {
        List<SysDepartment> sysDepartmentList = sysDepartmentMapper.selectList(
                Wrappers.lambdaQuery(SysDepartment.class)
                        .eq(StringUtils.isNotEmpty(sysDepartmentRequest.getDepartmentName()),
                                SysDepartment::getDepartmentName, sysDepartmentRequest.getDepartmentName())
                        .eq(StringUtils.isNotEmpty(sysDepartmentRequest.getParentId().toString()),
                                SysDepartment::getParentId, sysDepartmentRequest.getParentId()));

        List<SysDepartmentResponse> sysDepartmentResponseList = sysDepartmentList.stream().map(sysDepartment -> {
            SysDepartmentResponse sysDepartmentResponse = new SysDepartmentResponse();
            BeanUtils.copyProperties(sysDepartment, sysDepartmentResponse);
            return sysDepartmentResponse;
        }).collect(Collectors.toList());
        return sysDepartmentResponseList;
    }

    @Override
    public List<SysDepartmentResponse> getDepartmentListChildren(Long departmentId) {
        //查出所有部门
        List<SysDepartment> sysDepartmentList = sysDepartmentMapper.selectList(null);
        //递归查出所有子部门
        List<SysDepartmentResponse> sysDepartmentListChildren = getChildren(sysDepartmentList, departmentId);
        return sysDepartmentListChildren;
    }

    private List<SysDepartmentResponse> getChildren(List<SysDepartment> sysDepartmentList, Long departmentId) {
        List<SysDepartmentResponse> result = sysDepartmentList.stream()
                .filter(sysDepartment -> sysDepartment.getParentId().equals(departmentId)).map(sysDepartment -> {
                    SysDepartmentResponse sysDepartmentResponse = SysDepartmentResponse.convert(sysDepartment);
                    return sysDepartmentResponse;
                }).collect(Collectors.toList());
        for (SysDepartmentResponse sysDepartmentResponse : result) {
            List<SysDepartmentResponse> children = getChildren(sysDepartmentList, sysDepartmentResponse.getId());
            if(Objects.nonNull(children)){
                sysDepartmentResponse.setChildren(children);
                sysDepartmentResponse.setLeaf(false);
            }else{
                sysDepartmentResponse.setChildren(null);
                sysDepartmentResponse.setLeaf(true);
            }
        }
        if (result.size() == 0) {
            return null;
        }
        return result;
    }

//     public String getParentDepartment(StringBuilder stringBuilder,List<SysDepartment> sysDepartmentList,Long departmentId){
//        //获取当前部门 ID 的 parentID
//        Long parentId = sysDepartmentList
//                .stream().filter(sysDepartment -> sysDepartment.getId().equals(departmentId)).findFirst().get().getParentId();
//
//        List<SysDepartment> result = sysDepartmentList.stream()
//                .filter(sysDepartment -> sysDepartment.getId().equals(departmentId)).collect(Collectors.toList());
//        for(SysDepartment sysDepartment:result){
//            stringBuilder.append(sysDepartment.getDepartmentName());
//            if(sysDepartment.getParentId()!=0){
//                getParentDepartment(stringBuilder,sysDepartmentList,sysDepartment.getParentId());
//            }
//        }
//        return stringBuilder.toString();
//     }

}




