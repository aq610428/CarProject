package com.car.notver.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/6/18
 * @name:ClientVo
 */
public class ClientVo implements Serializable {

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * id : 3b3c24b83b9246a18a098cdcf2f7408d
     * imeicode :
     * carcard : 川1QW2333
     * factory : 奥迪A32016款Limousine35TFSI进取型
     * business : 一汽奥迪
     * model : A3
     * yearmodel : 2016款Limousine35TFSI进取型
     * vinno :
     * engineno :
     * initmileage : 0.0
     * totalmileage : 0.0
     * memberid : 00e20e0f6948478fa13d46147ab866bf
     * remark :
     * storeid : a3e0fd89e44a40dc95839f1c15139c28
     * storeMemberId : e573c2c0ed4d40aebee62750b507194b
     * createTime : 2020-06-18T07:05:04.000Z
     * updateTime : null
     * type : 1
     * username : 佚名
     * mobile : 18983166000
     * descriptionToString : {"factory":"汽车品牌","engineno":"发动机号","business":"汽车厂商","yearmodel":"汽车年款","imeicode":"设备号","carcard":"车牌","remark":"车辆备注","updateTime":"更新时间","storeMemberId":"门店会员编号","vinno":"车架号","storeid":"门店id","initmileage":"初始里程","totalmileage":"累计里程","createTime":"创建时间","model":"汽车车型","id":"id","memberid":"车主会员编号"}
     * stringCreateTime : 2020-06-18 15:05:04
     * stringUpdateTime :
     */
    private String storeName;
    private String id;
    private String imeicode;
    private String carcard;
    private String factory;
    private String business;
    private String model;
    private String yearmodel;
    private String vinno;
    private String engineno;
    private double initmileage;
    private double totalmileage;
    private String memberid;
    private String remark;
    private String storeid;
    private String storeMemberId;
    private String createTime;
    private Object updateTime;
    private int type;
    private String username;
    private String mobile;
    private String descriptionToString;
    private String stringCreateTime;
    private String stringUpdateTime;


    public ClientVo(String storeName,String username,double totalmileage,double initmileage){
        this.storeName=storeName;
        this.username=username;
        this.totalmileage=totalmileage;
        this.initmileage=initmileage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImeicode() {
        return imeicode;
    }

    public void setImeicode(String imeicode) {
        this.imeicode = imeicode;
    }

    public String getCarcard() {
        return carcard;
    }

    public void setCarcard(String carcard) {
        this.carcard = carcard;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearmodel() {
        return yearmodel;
    }

    public void setYearmodel(String yearmodel) {
        this.yearmodel = yearmodel;
    }

    public String getVinno() {
        return vinno;
    }

    public void setVinno(String vinno) {
        this.vinno = vinno;
    }

    public String getEngineno() {
        return engineno;
    }

    public void setEngineno(String engineno) {
        this.engineno = engineno;
    }

    public double getInitmileage() {
        return initmileage;
    }

    public void setInitmileage(double initmileage) {
        this.initmileage = initmileage;
    }

    public double getTotalmileage() {
        return totalmileage;
    }

    public void setTotalmileage(double totalmileage) {
        this.totalmileage = totalmileage;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
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

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
