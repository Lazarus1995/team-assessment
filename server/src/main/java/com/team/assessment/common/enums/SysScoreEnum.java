package com.team.assessment.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SysScoreEnum {
    TRUE(true, "是"),
    FALSE(false, "否");

    private Boolean code;

    private String desc;

    public static SysScoreEnum getByCode(Boolean code) {
        if (null == code) {
            return null;
        }
        for (SysScoreEnum ele : values()) {
            if (ele.code.equals(code)) {
                return ele;
            }
        }
        return null;
    }
}
