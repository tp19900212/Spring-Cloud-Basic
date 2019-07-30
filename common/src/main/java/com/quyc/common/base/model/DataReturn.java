
package com.quyc.common.base.model;


public class DataReturn {

    /**
     * 成功
     */
    public static int SUCCESS = 0;
    /**
     * 失败
     */
    public static int FAILURE = 1;
    private int code = FAILURE;
    private String message = "";
    private Object data = null;


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DataReturn{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
