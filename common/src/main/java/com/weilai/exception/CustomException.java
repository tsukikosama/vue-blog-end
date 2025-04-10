package com.weilai.exception;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code = 500;
    private String msg;


    public CustomException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CustomException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
