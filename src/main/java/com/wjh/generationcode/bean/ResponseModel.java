package com.wjh.generationcode.bean;


/**
 * Created by Mike on 2016/11/18.
 * 定义controller中方法的返回对象
 */

public class ResponseModel {
    private boolean success = true;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
