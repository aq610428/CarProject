package com.car.notver.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:UserInfo
 */
public class UserInfo implements Serializable {

    /**
     * id : e573c2c0ed4d40aebee62750b507194b
     * loginname : 18588246540
     * nickname :
     * realname :
     * sex : 0
     * birthday :
     * mobile : 18588246540
     * province :
     * city :
     * address :
     * company :
     * status : NORMAL
     * rmcode : 939aa3
     * friendcode :
     * createTime :
     * usericon :
     * tgurl : http://kb.jkabe.com/member/invitetoregister?friendcode=939aa3
     * tgcode :
     * descriptionToString : {"birthday":"生日","address":"住址","usericon":"会员图像","loginname":"用户名","city":"城市","sex":"性别  [{'value':'1','name':'男'},{'value':'2','name':'女'}]","mobile":"手机号码","tgcode":"我的推广码","realname":"真实姓名","tgurl":"我的推广链接","province":"省份","createTime":"注册时间","nickname":"昵称","friendcode":"好友邀请码","company":"单位","id":"会员id","status":"状态  [{'value':'NORMAL','name':'正常'},{'value':'LOCKED','name':'锁定'},{'value':'FREEZE','name':'冻结'}]","rmcode":"我的邀请码"}
     */

    private String id;
    private String loginname;
    private String nickname;
    private String realname;
    private int sex;
    private String birthday;
    private String mobile;
    private String province;
    private String city;
    private String address;
    private String company;
    private String status;
    private String rmcode;
    private String friendcode;
    private String createTime;
    private String usericon;
    private String tgurl;
    private String tgcode;
    private String descriptionToString;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRmcode() {
        return rmcode;
    }

    public void setRmcode(String rmcode) {
        this.rmcode = rmcode;
    }

    public String getFriendcode() {
        return friendcode;
    }

    public void setFriendcode(String friendcode) {
        this.friendcode = friendcode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUsericon() {
        return usericon;
    }

    public void setUsericon(String usericon) {
        this.usericon = usericon;
    }

    public String getTgurl() {
        return tgurl;
    }

    public void setTgurl(String tgurl) {
        this.tgurl = tgurl;
    }

    public String getTgcode() {
        return tgcode;
    }

    public void setTgcode(String tgcode) {
        this.tgcode = tgcode;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
    }
}
