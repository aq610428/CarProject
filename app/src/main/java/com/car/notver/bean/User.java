package com.car.notver.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/6/19
 * @name:User
 */
public class User implements Serializable {

    /**
     * id : 00e20e0f6948478fa13d46147ab866bf
     * username : 机器狗
     * mobile : 18983166000
     * birthday : 2020-06-12T16:00:00.000Z
     * sex : 1
     * province : 重庆市
     * city : 重庆市
     * area : 万州区
     * address :
     * remark :
     * storeid : a3e0fd89e44a40dc95839f1c15139c28
     * storeMemberId : e573c2c0ed4d40aebee62750b507194b
     * createTime : 2020-06-17T02:44:36.000Z
     * updateTime : 2020-06-19T02:36:25.000Z
     * type : 2
     * descriptionToString : {"birthday":"生日","area":"区县","address":"详细地址","city":"城市","sex":"性别 1=男,2=女","mobile":"客户手机","remark":"客户备注","updateTime":"更新时间","storeMemberId":"门店用户id","storeid":"门店id","province":"省份","createTime":"创建时间","id":"foreignKey","username":"客户名称"}
     * stringBirthday : 2020-06-13 00:00:00
     * stringCreateTime : 2020-06-17 10:44:36
     * stringUpdateTime : 2020-06-19 10:36:25
     */

    private String id;
    private String username;
    private String mobile;
    private String birthday;
    private int sex;
    private String province;
    private String city;
    private String area;
    private String address;
    private String remark;
    private String storeid;
    private String storeMemberId;
    private String createTime;
    private String updateTime;
    private int type;
    private String descriptionToString;
    private String stringBirthday;
    private String stringCreateTime;
    private String stringUpdateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getStoreMemberId() {
        return storeMemberId;
    }

    public void setStoreMemberId(String storeMemberId) {
        this.storeMemberId = storeMemberId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
    }

    public String getStringBirthday() {
        return stringBirthday;
    }

    public void setStringBirthday(String stringBirthday) {
        this.stringBirthday = stringBirthday;
    }

    public String getStringCreateTime() {
        return stringCreateTime;
    }

    public void setStringCreateTime(String stringCreateTime) {
        this.stringCreateTime = stringCreateTime;
    }

    public String getStringUpdateTime() {
        return stringUpdateTime;
    }

    public void setStringUpdateTime(String stringUpdateTime) {
        this.stringUpdateTime = stringUpdateTime;
    }
}
