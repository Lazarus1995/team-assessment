package com.team.assessment.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SysLawTypeEnum {

    REJECT(0, "否决"),
    ADD(1, "加分"),
    REDUCE(-1, "减分"),
    ;

    private Integer code;

    private String desc;

    public static SysLawTypeEnum getByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (SysLawTypeEnum ele : values()) {
            if (ele.code.equals(code)) {
                return ele;
            }
        }
        return null;
    }

    /**
     * 使用 code 返回枚举
     */
    public static SysLawTypeEnum getByCode(String code) {
        if (null == code) {
            return null;
        }
        for (SysLawTypeEnum ele : values()) {
            if (ele.code.equals(code)) {
                return ele;
            }
        }
        return null;
    }
}
