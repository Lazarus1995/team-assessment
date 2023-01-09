package com.team.assessment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogComplaintStatusEnum {

    COMPLAINED(0, "已申诉"),
    REJECTION(-1, "已驳回"),
    PASSED(1, "已通过"),
    ;
    private Integer code;

    private String desc;

    public static LogComplaintStatusEnum getByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (LogComplaintStatusEnum ele : values()) {
            if (ele.code.equals(code)) {
                return ele;
            }
        }
        return null;
    }
}
