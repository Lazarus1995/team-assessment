package com.team.assessment;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team.assessment.entry.SysDepartment;
import com.team.assessment.vo.request.SysDepartmentRequest;
import com.team.assessment.vo.response.SysDepartmentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author qu
* @description 针对表【sys_department】的数据库操作Service
* @createDate 2023-01-07 12:21:27
*/
@Service
public interface SysDepartmentService extends IService<SysDepartment> {

    SysDepartmentResponse getDepartment(Long departmentId);

    List<SysDepartmentResponse> getDepartmentList(SysDepartmentRequest sysDepartmentRequest);
}
