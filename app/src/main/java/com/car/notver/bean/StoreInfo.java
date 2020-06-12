package com.car.notver.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/5/28
 * @name:StoreInfo
 */
public class StoreInfo implements Serializable {


    /**
     * id : a3e0fd89e44a40dc95839f1c15139c28
     * name : 深圳西丽汽车维修中心
     * phone : 18525246542
     * contactPerson : 李健
     * province : 广东省
     * city : 深圳市
     * area : 在深圳市瑞丰创业产业园附近
     * address : 广东省深圳市南山区珠光路335号3栋靠近深圳市瑞丰创业产业园
     * lng : 113.957265
     * lat : 22.574876
     * logo : http://img.jkabe.com/storelogo/3c1822276d4140179dcf7b572e4b7007.jpg
     * brandName : 奔驰
     * chainName :
     * businessScope : 维修，保养，救援
     * rescuePhone : 18588246540
     * status : 0=待审核,1=审核通过,2=审核不通过
     * reviewContent :
     * memberId : e573c2c0ed4d40aebee62750b507194b
     * createTime : 2020-05-28T10:19:26.000Z
     * updateTime : null
     * operTime : 06:17-18:17
     * descriptionToString : {"area":"区县","chainName":"连锁店品牌","brandName":"主营品牌","address":"详细地址","lng":"经度","city":"城市","contactPerson":"联系人","businessScope":"主营业务","updateTime":"更新时间","province":"省份","phone":"服务电话","rescuePhone":"救援电话","reviewContent":"审核回复内容","createTime":"创建时间","name":"门店名称","logo":"门头","id":"foreignKey","lat":"纬度","status":"审核状态 0=待审核,1=审核通过,2=审核不通过","memberId":"用户id"}
     * stringCreateTime : 2020-05-28 18:19:26
     * stringUpdateTime :
     */

    private String id;
    private String name;
    private String phone;
    private String contactPerson;
    private String province;
    private String city;
    private String area;
    private String address;
    private String lng;
    private String lat;
    private String logo;
    private String brandName;
    private String chainName;
    private String businessScope;
    private String rescuePhone;
    private int status;
    private String reviewContent;
    private String memberId;
    private String createTime;
    private Object updateTime;
    private String operTime;
    private String descriptionToString;
    private String stringCreateTime;
    private String stringUpdateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
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

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getRescuePhone() {
        return rescuePhone;
    }

    public void setRescuePhone(String rescuePhone) {
        this.rescuePhone = rescuePhone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
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

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
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
