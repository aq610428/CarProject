package com.car.notver.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/5/21
 * @name:ClientInfo
 */
public class ClientInfo implements Serializable {
    private String name;
    private String phone;
    private String license ;
    private String make;

    public boolean isMake() {
        return isMake;
    }

    public void setMake(boolean make) {
        isMake = make;
    }

    private boolean isMake;

    public ClientInfo(String name, String phone, String license, String make) {
        this.name=name;
        this.phone=phone;
        this.license=license;
        this.make=make;
    }

    public ClientInfo(String name, String phone, String license) {
        this.name=name;
        this.phone=phone;
        this.license=license;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }



}
