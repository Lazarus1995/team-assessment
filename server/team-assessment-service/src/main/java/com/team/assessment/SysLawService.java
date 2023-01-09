package com.team.assessment;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team.assessment.entry.SysLaw;
import com.team.assessment.vo.request.SysLawRequest;
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

    List<SysLaw> getLawList(SysLawRequest sysLawRequest);

    void addLaw(SysLawRequest sysLawRequest);
}
