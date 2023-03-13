package com.demo.entity;

public enum ResultEnum {

    UNKNOWN_ERROR(-100,"未知错误"),
    SUCCESS(1,"成功"),
    FAIL(-1,"失败");


    private Integer statusCode;
    private String msg;

    ResultEnum(Integer statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getMsg() {
        return msg;
    }
}