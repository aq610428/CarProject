package com.car.notver.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/6/4
 * @name:Massage
 */
public class Massage implements Serializable {

    /**
     * id : 31b804f15695425abd6c179b60059b1b
     * memberid : e573c2c0ed4d40aebee62750b507194b
     * title : 系统通知
     * content : 系统通知
     * status : 0
     * type : 0
     * createTime : 2020-06-04T08:00:22.000Z
     * updateTime : 2020-06-04T08:00:27.000Z
     * lng :
     * lat :
     * carcard :
     * address :
     * gpstime : null
     * descriptionToString : {"address":"定位地址","lng":"经度","carcard":"车牌","updateTime":"更新时间","title":"标题","type":"消息类型 0=系统消息,1=报警消息","content":"内容","createTime":"创建时间","id":"主键","gpstime":"定位时间","lat":"纬度","memberid":"用户id","status":"读取状态 0=未读,1=已读"}
     * stringCreateTime : 2020-06-04 16:00:22
     * stringUpdateTime : 2020-06-04 16:00:27
     * stringgpstime :
     */

    private String id;
    private String memberid;
    private String title;
    private String content;
    private int status;
    private int type;
    private String createTime;
    private String updateTime;
    private String lng;
    private String lat;
    private String carcard;
    private String address;
    private Object gpstime;
    private String descriptionToString;
    private Object stringCreateTime;
    private Object stringUpdateTime;
    private String stringgpstime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getCarcard() {
        return carcard;
    }

    public void setCarcard(String carcard) {
        this.carcard = carcard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getGpstime() {
        return gpstime;
    }

    public void setGpstime(Object gpstime) {
        this.gpstime = gpstime;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
    }

    public Object getStringCreateTime() {
        return stringCreateTime;
    }

    public void setStringCreateTime(Object stringCreateTime) {
        this.stringCreateTime = stringCreateTime;
    }

    public Object getStringUpdateTime() {
        return stringUpdateTime;
    }

    public void setStringUpdateTime(Object stringUpdateTime) {
        this.stringUpdateTime = stringUpdateTime;
    }

    public String getStringgpstime() {
        return stringgpstime;
    }

    public void setStringgpstime(String stringgpstime) {
        this.stringgpstime = stringgpstime;
    }
}
