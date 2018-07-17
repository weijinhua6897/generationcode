package com.wjh.generationcode.bean;


import java.util.List;

/**
 * Created by Mike on 2016/11/18.
 * 定义controller中方法的返回对象
 */
public class ResponseDataListModel<T> {
    private boolean success = true;
    private List<T> list;
    private int count=0;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
