package com.car.notver.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/6/3
 * @name:Verison
 */
public class Verison implements Serializable {

    /**
     * id : d4edf04606b4481eb416db84fa98a8c2
     * versionNo : 1.0.0
     * versionIndex : 1
     * versionContent : 惠保养初始版本
     * filepath : http://img.jkabe.com/apkfile/13d5d70f23074bdcb12f11d6a579648c.apk
     * versionTime : 2020-06-03
     * isForceUpdate : 1
     * createTime : 2020-06-03 17:30:17
     * descriptionToString : {"versionContent":"版本内容","versionIndex":"序列码","filepath":"文件地址","createTime":"发布时间","versionTime":"版本时间","versionNo":"版本号","id":"版本id","isForceUpdate":"强制升级 1=否,2=是"}
     */

    private String id;
    private String versionNo;
    private String versionIndex;
    private String versionContent;
    private String filepath;
    private String versionTime;
    private int isForceUpdate;
    private String createTime;
    private String descriptionToString;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getVersionIndex() {
        return versionIndex;
    }

    public void setVersionIndex(String versionIndex) {
        this.versionIndex = versionIndex;
    }

    public String getVersionContent() {
        return versionContent;
    }

    public void setVersionContent(String versionContent) {
        this.versionContent = versionContent;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getVersionTime() {
        return versionTime;
    }

    public void setVersionTime(String versionTime) {
        this.versionTime = versionTime;
    }

    public int getIsForceUpdate() {
        return isForceUpdate;
    }

    public void setIsForceUpdate(int isForceUpdate) {
        this.isForceUpdate = isForceUpdate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescriptionToString() {
        return descriptionToString;
    }

    public void setDescriptionToString(String descriptionToString) {
        this.descriptionToString = descriptionToString;
    }
}
