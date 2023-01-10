package com.team.assessment.model.vo.response;

import com.team.assessment.entry.BaseEntity;
import com.team.assessment.model.entry.SysLaw;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @TableName sys_law
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysLawResponse extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 小立法 ID
     */
    private Long lawId;

    /**
     * 小立法分数
     */
    private Integer lawScore;

    /**
     * 小立法内容
     */
    private String lawContent;

    /**
     * 所属部门 ID
     */
    private Long departmentId;

    /**
     * 当月出现次数
     */
    private Integer lawMonthCount;


    /**
     * 小立法类型
     */
    private Integer lawType;

    public static SysLawResponse convert(SysLaw sysLaw) {
        SysLawResponse sysLawResponse = new SysLawResponse();
        sysLawResponse.setLawId(sysLaw.getLawId());
        sysLawResponse.setLawScore(sysLaw.getLawScore());
        sysLawResponse.setLawContent(sysLaw.getLawContent());
        sysLawResponse.setDepartmentId(sysLaw.getDepartmentId());
        sysLawResponse.setLawMonthCount(sysLaw.getLawMonthCount());
        sysLawResponse.setLawType(sysLaw.getLawType());
        return sysLawResponse;
    }
}