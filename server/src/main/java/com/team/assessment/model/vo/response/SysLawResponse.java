package com.team.assessment.model.vo.response;

import com.team.assessment.model.entry.SysLaw;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName sys_law
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLawResponse implements Serializable {

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
     * 所属部门名称
     */
    private String departmentName;

    /**
     * 小立法所属部门完整结构
     */
    private String belong;

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