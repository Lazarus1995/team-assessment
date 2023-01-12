package com.team.assessment.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SysUserStatusEnum {
    DELETE(-1, "删除"),
    DISABLE(0, "禁用"),
    NORMAL(1, "在岗"),
    ;

    private Integer code;

    private String desc;

    public static SysUserStatusEnum getByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (SysUserStatusEnum ele : values()) {
            if (ele.code.equals(code)) {
                return ele;
            }
        }
        return null;
    }
}
