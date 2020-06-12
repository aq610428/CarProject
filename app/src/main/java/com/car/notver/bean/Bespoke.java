package com.car.notver.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/5/29
 * @name:Bespoke
 */
public class Bespoke implements Serializable {


    /**
     * id : b07f362c2dde4684b73c356345a1c5a8
     * storeId : a3e0fd89e44a40dc95839f1c15139c28
     * storeName : 深圳西丽汽车维修中心
     * orderTime : 2020-05-30 10:00
     * project : 保养
     * carcard : 粤S1Q0P1
     * mobile : 15919931559
     * status : 1
     * memberId : e1fbf8a12b5344cba4c03e9164c3c59d
     * createTime : 2020-05-29T09:31:35.000Z
     * updateTime : 2020-05-29T10:40:08.000Z
     * storeMemberId : e573c2c0ed4d40aebee62750b507194b
     * descriptionToString : {"orderTime":"预约时间","createTime":"创建时间","carcard":"车牌","mobile":"联系手机","project":"预约项目","storeName":"门店名称","updateTime":"更新时间","id":"id","storeId":"门店","status":"预约状态 0=取消预约,1=预约申请中,2=接受预约,3=预约完成","memberId":"用户id"}
     * stringCreateTime : 2020-05-29 17:31:35
     * stringUpdateTime : 2020-05-29 18:40:08
     */

    private String id;
    private String storeId;
    private String storeName;
    private String orderTime;
    private String project;
    private String carcard;
    private String mobile;
    private int status;//0=取消预约,1=预约申请中,2=接受预约,3=预约完成
    private String memberId;
    private String createTime;
    private String updateTime;
    private String storeMemberId;
    private String descriptionToString;
    private String stringCreateTime;
    private String stringUpdateTime;
    private String billId;

    public int getIntegralFlag() {
        return integralFlag;
    }

    public void setIntegralFlag(int integralFlag) {
        this.integralFlag = integralFlag;
    }

    private int integralFlag;


    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCarcard() {
        return carcard;
    }

    public void setCarcard(String carcard) {
        this.carcard = carcard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public String getStoreMemberId() {
        return storeMemberId;
    }

    public void setStoreMemberId(String storeMemberId) {
        this.storeMemberId = storeMemberId;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
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
