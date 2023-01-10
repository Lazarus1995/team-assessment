package com.team.assessment.common.response;



import com.team.assessment.enums.ErrorCode;

import java.io.Serializable;

/**
 * 返回基础类
 *
 * @author herryzhang
 * @date 2018-7-24 14:59:54
 */
public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 返回结果
     */
    private Object result;

    private BaseResponse() {

    }

    private BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private BaseResponse(Integer code, Object result) {
        this.code = code;
        this.result = result;
    }

    private BaseResponse(Integer code) {
        this.code = code;
    }

    public BaseResponse(Object result) {
        this.result = result;
    }

    public static BaseResponse error() {
        return error(ErrorCode.SC_INTERNAL_SERVER_ERROR.getCode(), ErrorCode.SC_INTERNAL_SERVER_ERROR.getMessage());
    }

    public static BaseResponse error(String message) {
        return error(ErrorCode.SC_INTERNAL_SERVER_ERROR.getCode(), message);
    }

    public static BaseResponse error(Integer code, String message) {
        return new BaseResponse(code, message);
    }

    public static BaseResponse success(String message) {
        return new BaseResponse(ErrorCode.OK.getCode(), message);
    }

    public static BaseResponse success(Object result) {
        return new BaseResponse(ErrorCode.OK.getCode(), result);
    }

    public static BaseResponse success() {
        return new BaseResponse(ErrorCode.OK.getCode());
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
