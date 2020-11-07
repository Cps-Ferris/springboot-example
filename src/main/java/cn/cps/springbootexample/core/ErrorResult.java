package cn.cps.springbootexample.core;

import java.io.Serializable;

/**
 * @Author: Cai Peishen
 * @Date: 2020/6/29 11:02
 * @Description: 使用ResponseResult返回错误状态信息
 */
public class ErrorResult implements Serializable {

    private int errorCode;
    private String errorMessage;

    public int getErrorCode() {
        return errorCode;
    }

    public ErrorResult setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ErrorResult setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}