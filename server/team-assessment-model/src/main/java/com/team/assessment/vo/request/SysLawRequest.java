package com.team.assessment.vo.request;

import lombok.Data;

@Data
public class SysLawRequest {

    /**
     * 小立法分数
     */
    private Integer lawScore;

    /**
     * 小立法内容
     */
    private String lawContent;



    private Long departmentId;



}
