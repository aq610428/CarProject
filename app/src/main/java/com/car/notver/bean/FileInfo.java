package com.car.notver.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/5/29
 * @name:FileInfo
 */
public class FileInfo implements Serializable {

    /**
     * id : 861dc03168ec49439fe249cba5f6ef31
     * storeId : a3e0fd89e44a40dc95839f1c15139c28
     * storeName : 深圳西丽汽车维修中心
     * photoFile : http://img.jkabe.com/storelogo/8ffa539bb2b84e5abed7192c3badcb2f.jpg
     * title : 门牌照
     * sort : 1
     * status : 0
     * createTime : 2020-05-29T04:31:55.000Z
     * updateTime : null
     * descriptionToString : {"photoFile":"门店照片","createTime":"创建时间","storeName":"门店名称","updateTime":"更新时间","id":"foreignKey","sort":"排序","storeId":"门店","title":"标题","status":"启用状态 1=未启用,2=已启用,3=已停用"}
     * stringCreateTime : 2020-05-29 12:31:55
     * stringUpdateTime :
     */

    private String id;
    private String storeId;
    private String storeName;
    private String photoFile;
    private String title;
    private int sort;
    private int status;
    private String createTime;
    private Object updateTime;
    private String descriptionToString;
    private String stringCreateTime;
    private String stringUpdateTime;

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

    public String getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(String photoFile) {
        this.photoFile = photoFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
