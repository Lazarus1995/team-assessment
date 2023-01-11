package com.team.assessment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team.assessment.model.entry.SysUserScore;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author qu
 * @description 针对表【sys_user_score】的数据库操作Mapper
 * @createDate 2023-01-11 01:27:37
 * @Entity generator.domain.SysUserScore
 */
@Repository
public interface SysUserScoreMapper extends BaseMapper<SysUserScore> {

    @MapKey("id")
    List<Map<String, Object>> getUserScoreList(@Param("departmentId") Long departmentId
            , @Param("year") Long year, @Param("month") Long month);

    Integer getTotleScore(@Param("departmentId") Long departmentId
            , @Param("year") Long year, @Param("month") Long month);
}




