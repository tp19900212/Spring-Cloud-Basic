package com.quyc.common.base.exception;

public class BusinessException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 4454920264320232255L;
    int code;
    String message;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
