package com.car.notver.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/16.
 * 集合类
 */
public class CommonalityModel implements Serializable {
    private String statusCode;//接口是否请求成功
    private String errorDesc;//错误信息
    private String errorCode;//错误代码

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
