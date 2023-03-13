package com.demo.entity;


public class Result<T> {

    private String msg;
    private T data;
    private Integer statusCode;

    public Result() {
    }

    public Result(Integer statusCode, String msg, T data) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success((T)null);
    }

    public static <T> Result<T> success(T data) {
        return new Result(ResultEnum.SUCCESS.getStatusCode(), ResultEnum.SUCCESS.getMsg(), data);
    }

    public static <T> Result<T> fail(T data) {
        return fail(ResultEnum.UNKNOWN_ERROR.getStatusCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
    }

    public static <T>Result<T> fail(){
        return fail(ResultEnum.FAIL.getStatusCode(), ResultEnum.FAIL.getMsg());
    }

    public static <T> Result<T> fail(Integer statusCode, String msg) {
        return new Result(statusCode, msg, (T)null);
    }

    public String toString() {
        return "Result(statusCode=" + this.getStatusCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}

