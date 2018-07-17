package com.wjh.generationcode.bean;

public class FileUrl {
    private String controllerurl;

    private String serviceurl;
    private String serviceImplurl;
    private String daourl;
    private String beanurl;

    public String getBeanurl() {
        return beanurl;
    }

    public void setBeanurl(String beanurl) {
        this.beanurl = beanurl;
    }

    public String getControllerurl() {
        return controllerurl;
    }

    public void setControllerurl(String controllerurl) {
        this.controllerurl = controllerurl;
    }

    public String getServiceurl() {
        return serviceurl;
    }

    public void setServiceurl(String serviceurl) {
        this.serviceurl = serviceurl;
    }

    public String getServiceImplurl() {
        return serviceImplurl;
    }

    public void setServiceImplurl(String serviceImplurl) {
        this.serviceImplurl = serviceImplurl;
    }

    public String getDaourl() {
        return daourl;
    }

    public void setDaourl(String daourl) {
        this.daourl = daourl;
    }
}
