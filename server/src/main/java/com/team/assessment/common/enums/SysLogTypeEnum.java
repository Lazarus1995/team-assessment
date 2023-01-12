package com.team.assessment.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SysLogTypeEnum {
    SCORE(0, "打分日志"),
    EXPORT(1, "导出日志"),
    ;

    private Integer code;

    private String desc;

    public static SysLogTypeEnum getByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (SysLogTypeEnum ele : values()) {
            if (ele.code.equals(code)) {
                return ele;
            }
        }
        return null;
    }
}
