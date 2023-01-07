package com.team.assessment.enums;

/**
 * 用户状态
 *
 * @author herry-zhang
 * @date 2018-7-19 10:22:51
 */
public enum SysUserStatusCode {
    DELETE(-1, "账号已被删除"),
    LOCKED(0, "账号已被禁用"),
    NORMAL(1, "在岗");

    private String message;
    private int code;

    SysUserStatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }

}
