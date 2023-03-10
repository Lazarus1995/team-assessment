package com.team.assessment.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogRatingStatusEnum {
    UNCOMPLETE(0, "未完成"),
    COMPLETED(1, "已完成"),
    TIMEOUT(-1, "超时"),
    ;
    private Integer code;

    private String desc;

    public static LogRatingStatusEnum getByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (LogRatingStatusEnum ele : values()) {
            if (ele.code.equals(code)) {
                return ele;
            }
        }
        return null;
    }
}
