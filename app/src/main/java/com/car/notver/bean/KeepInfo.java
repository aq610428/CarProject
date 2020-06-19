package com.car.notver.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/5/21
 * @name:KeepInfo
 */
public class KeepInfo implements Serializable {


    /**
     * factory : 雪佛兰
     * loginname : 15919936559
     * carcard : 粤AJ44E6
     * imeicode : 201901000487
     * nickname :
     * model : 迈锐宝XL
     * memberId : e1fbf8a12b5344cba4c03e9164c3c59d
     */

    private String factory;
    private String loginname;
    private String carcard;
    private String imeicode;
    private String username;
    private String model;

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    private String memberid;

    private String initmileage;//": 3000.0, 初始里程
    private String totalmileage;//": 3902.95, 累积行驶里程
    private String storeid;//": "a3e0fd89e44a40dc95839f1c15139c28",
    private String lastmaintainmileage;//":上次保养里程
    private String oils;
    private String alarms;
    private String faults;
    private String mileages;
    private String mobile;
    private String birthday;
    private String sex;
    private String id; //": "bf4135aa3ebd4fa4ba69a9faf851b264",

    public String getStoreMemberId() {
        return storeMemberId;
    }

    public void setStoreMemberId(String storeMemberId) {
        this.storeMemberId = storeMemberId;
    }
    private String storeMemberId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String province; //": "广东省",
    private String city; //": "深圳市",
    private String area; //": "福田区",
    private String address; //": "机器猫",
    private String remark; //": "",


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    public String getOils() {
        return oils;
    }

    public void setOils(String oils) {
        this.oils = oils;
    }

    public String getAlarms() {
        return alarms;
    }

    public void setAlarms(String alarms) {
        this.alarms = alarms;
    }

    public String getFaults() {
        return faults;
    }

    public void setFaults(String faults) {
        this.faults = faults;
    }

    public String getMileages() {
        return mileages;
    }

    public void setMileages(String mileages) {
        this.mileages = mileages;
    }



    public String getInitmileage() {
        return initmileage;
    }

    public void setInitmileage(String initmileage) {
        this.initmileage = initmileage;
    }

    public String getTotalmileage() {
        return totalmileage;
    }

    public void setTotalmileage(String totalmileage) {
        this.totalmileage = totalmileage;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getLastmaintainmileage() {
        return lastmaintainmileage;
    }

    public void setLastmaintainmileage(String lastmaintainmileage) {
        this.lastmaintainmileage = lastmaintainmileage;
    }



    public KeepInfo(String factory, String loginname, String imeicode) {
        this.factory = factory;
        this.loginname = loginname;
        this.imeicode = imeicode;

    }

    public boolean isTraveled() {
        return isTraveled;
    }

    public void setTraveled(boolean traveled) {
        isTraveled = traveled;
    }

    private boolean isTraveled;

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getCarcard() {
        return carcard;
    }

    public void setCarcard(String carcard) {
        this.carcard = carcard;
    }

    public String getImeicode() {
        return imeicode;
    }

    public void setImeicode(String imeicode) {
        this.imeicode = imeicode;
    }

    public String getNickname() {
        return username;
    }

    public void setNickname(String nickname) {
        this.username = nickname;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
