package com.team.assessment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team.assessment.model.entry.SysLaw;
import com.team.assessment.model.vo.request.SysLawRequest;
import com.team.assessment.model.vo.response.SysLawResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author qu
* @description 针对表【sys_law】的数据库操作Service
* @createDate 2023-01-07 12:26:43
*/
@Service
public interface SysLawService extends IService<SysLaw> {

    SysLaw getLaw(Long id);

    List<SysLawResponse> getLawList(Long departmentId,Integer lawType);

    void addLaw(SysLawRequest sysLawRequest);

    void updateLaw(SysLawRequest sysLawRequest);
}
