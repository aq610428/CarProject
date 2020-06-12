package com.car.notver.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/5/30
 * @name:Ordered
 */
public class Ordered implements Serializable {

    /**
     * id : a4c4e38c99a24c03a9c91ed845a3b292
     * storeId : a3e0fd89e44a40dc95839f1c15139c28
     * storeName : 深圳西丽汽车维修中心
     * orderId : b07f362c2dde4684b73c356345a1c5a8
     * amount : 30000.0
     * integral : 20000
     * project : 保养
     * repairPlan : 洗车，保养
     * partsReplace : 更换电瓶
     * partsNum : 2个
     * finishTime : 2020-05-29T16:00:00.000Z
     * memberId : e573c2c0ed4d40aebee62750b507194b
     * createTime : 2020-05-30T07:51:54.000Z
     * updateTime : null
     * integralFlag : 0
     * carcard : 粤S1Q0P1
     * mobile : 15919931559
     * descriptionToString : {"amount":"支付现金","finishTime":"完工时间","orderId":"预约id","partsNum":"配件数量","partsReplace":"更换配件","project":"预约项目","updateTime":"更新时间","storeId":"门店","createTime":"创建时间","integral":"支付积分","storeName":"门店名称","id":"id","repairPlan":"维修方案","memberId":"用户id"}
     * stringCreateTime : 2020-05-30 15:51:54
     * stringUpdateTime :
     * stringFinishTime : 2020-05-30 00:00:00
     */

    private String id;
    private String storeId;
    private String storeName;
    private String orderId;
    private double amount;
    private int integral;
    private String project;
    private String repairPlan;
    private String partsReplace;
    private String partsNum;
    private String finishTime;
    private String memberId;
    private String createTime;
    private Object updateTime;
    private int integralFlag;
    private String carcard;
    private String mobile;
    private String descriptionToString;
    private String stringCreateTime;
    private String stringUpdateTime;
    private String stringFinishTime;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRepairPlan() {
        return repairPlan;
    }

    public void setRepairPlan(String repairPlan) {
        this.repairPlan = repairPlan;
    }

    public String getPartsReplace() {
        return partsReplace;
    }

    public void setPartsReplace(String partsReplace) {
        this.partsReplace = partsReplace;
    }

    public String getPartsNum() {
        return partsNum;
    }

    public void setPartsNum(String partsNum) {
        this.partsNum = partsNum;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
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

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public int getIntegralFlag() {
        return integralFlag;
    }

    public void setIntegralFlag(int integralFlag) {
        this.integralFlag = integralFlag;
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

    public String getStringFinishTime() {
        return stringFinishTime;
    }

    public void setStringFinishTime(String stringFinishTime) {
        this.stringFinishTime = stringFinishTime;
    }
}
