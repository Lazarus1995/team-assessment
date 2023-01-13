package com.team.assessment.model.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysScoreResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userName;

    private Integer currentScore;

    private Double issuedAmount;

    private Long departmentId;

    private String departmentName;

    private Boolean vetoStatus;

}
