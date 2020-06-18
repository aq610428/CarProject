package com.car.notver.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/6/18
 * @name:BrandInfo
 */
public class BrandInfo implements Serializable {
    /**
     * business : {"id":"259","modelName":"进口阿斯顿·马丁","fullName":"进口阿斯顿·马丁","modelImg":null,"firstChar":"A","parentId":"2","modelMark":2,"remark":null,"salestate":"","price":"","yeartype":"","productionstate":"","sizetype":"","iscommonuse":0,"brandid":"2","businessid":"259","descriptionToString":"{\"firstChar\":\"搜索首字母\",\"modelImg\":\"车型logo\",\"fullName\":\"车型全称\",\"remark\":\"备注\",\"productionstate\":\"生产状态\",\"parentId\":\"父类id\",\"modelName\":\"车型名称\",\"yeartype\":\"年款\",\"sizetype\":\"尺寸类型\",\"modelMark\":\"层级标识 [1 车系 2 产商 3 车型 4 年款]\",\"price\":\"官方指导价\",\"id\":\"车型id\",\"salestate\":\"销售状态\"}"}
     * items : [{"id":"260","modelName":"DB9","fullName":"阿斯顿·马丁DB9","modelImg":"http://pic1.jisuapi.cn/car/static/images/logo/300/260.JPG","firstChar":"A","parentId":"259","modelMark":3,"remark":null,"salestate":"停销","price":"","yeartype":"","productionstate":"","sizetype":"","iscommonuse":0,"brandid":"2","businessid":"259","descriptionToString":"{\"firstChar\":\"搜索首字母\",\"modelImg\":\"车型logo\",\"fullName\":\"车型全称\",\"remark\":\"备注\",\"productionstate\":\"生产状态\",\"parentId\":\"父类id\",\"modelName\":\"车型名称\",\"yeartype\":\"年款\",\"sizetype\":\"尺寸类型\",\"modelMark\":\"层级标识 [1 车系 2 产商 3 车型 4 年款]\",\"price\":\"官方指导价\",\"id\":\"车型id\",\"salestate\":\"销售状态\"}"},{"id":"261","modelName":"DBS","fullName":"阿斯顿·马丁DBS","modelImg":"http://pic1.jisuapi.cn/car/static/images/logo/300/261.jpg","firstChar":"A","parentId":"259","modelMark":3,"remark":null,"salestate":"在销","price":"","yeartype":"","productionstate":"","sizetype":"","iscommonuse":0,"brandid":"2","businessid":"259","descriptionToString":"{\"firstChar\":\"搜索首字母\",\"modelImg\":\"车型logo\",\"fullName\":\"车型全称\",\"remark\":\"备注\",\"productionstate\":\"生产状态\",\"parentId\":\"父类id\",\"modelName\":\"车型名称\",\"yeartype\":\"年款\",\"sizetype\":\"尺寸类型\",\"modelMark\":\"层级标识 [1 车系 2 产商 3 车型 4 年款]\",\"price\":\"官方指导价\",\"id\":\"车型id\",\"salestate\":\"销售状态\"}"},{"id":"262","modelName":"Lagonda","fullName":"阿斯顿·马丁Lagonda","modelImg":"http://pic1.jisuapi.cn/car/static/images/logo/300/262.jpg","firstChar":"A","parentId":"259","modelMark":3,"remark":null,"salestate":"停销","price":"","yeartype":"","productionstate":"","sizetype":"","iscommonuse":0,"brandid":"2","businessid":"259","descriptionToString":"{\"firstChar\":\"搜索首字母\",\"modelImg\":\"车型logo\",\"fullName\":\"车型全称\",\"remark\":\"备注\",\"productionstate\":\"生产状态\",\"parentId\":\"父类id\",\"modelName\":\"车型名称\",\"yeartype\":\"年款\",\"sizetype\":\"尺寸类型\",\"modelMark\":\"层级标识 [1 车系 2 产商 3 车型 4 年款]\",\"price\":\"官方指导价\",\"id\":\"车型id\",\"salestate\":\"销售状态\"}"},{"id":"263","modelName":"Rapide","fullName":"阿斯顿·马丁Rapide","modelImg":"http://pic1.jisuapi.cn/car/static/images/logo/300/263.JPG","firstChar":"A","parentId":"259","modelMark":3,"remark":null,"salestate":"在销","price":"","yeartype":"","productionstate":"","sizetype":"","iscommonuse":0,"brandid":"2","businessid":"259","descriptionToString":"{\"firstChar\":\"搜索首字母\",\"modelImg\":\"车型logo\",\"fullName\":\"车型全称\",\"remark\":\"备注\",\"productionstate\":\"生产状态\",\"parentId\":\"父类id\",\"modelName\":\"车型名称\",\"yeartype\":\"年款\",\"sizetype\":\"尺寸类型\",\"modelMark\":\"层级标识 [1 车系 2 产商 3 车型 4 年款]\",\"price\":\"官方指导价\",\"id\":\"车型id\",\"salestate\":\"销售状态\"}"}]
     */

    @SerializedName("business")
    private BusinessBean businessX;
    @SerializedName("items")
    private List<ItemsBean> itemsX;




    public BusinessBean getBusinessX() {
        return businessX;
    }

    public void setBusinessX(BusinessBean businessX) {
        this.businessX = businessX;
    }

    public List<ItemsBean> getItemsX() {
        return itemsX;
    }

    public void setItemsX(List<ItemsBean> itemsX) {
        this.itemsX = itemsX;
    }

    public static class BusinessBean implements Serializable{
        /**
         * id : 259
         * modelName : 进口阿斯顿·马丁
         * fullName : 进口阿斯顿·马丁
         * modelImg : null
         * firstChar : A
         * parentId : 2
         * modelMark : 2
         * remark : null
         * salestate :
         * price :
         * yeartype :
         * productionstate :
         * sizetype :
         * iscommonuse : 0
         * brandid : 2
         * businessid : 259
         * descriptionToString : {"firstChar":"搜索首字母","modelImg":"车型logo","fullName":"车型全称","remark":"备注","productionstate":"生产状态","parentId":"父类id","modelName":"车型名称","yeartype":"年款","sizetype":"尺寸类型","modelMark":"层级标识 [1 车系 2 产商 3 车型 4 年款]","price":"官方指导价","id":"车型id","salestate":"销售状态"}
         */

        private String id;
        private String modelName;
        private String fullName;
        private Object modelImg;
        private String firstChar;
        private String parentId;
        private int modelMark;
        private Object remark;
        private String salestate;
        private String price;
        private String yeartype;
        private String productionstate;
        private String sizetype;
        private int iscommonuse;
        private String brandid;
        private String businessid;
        private String descriptionToString;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public Object getModelImg() {
            return modelImg;
        }

        public void setModelImg(Object modelImg) {
            this.modelImg = modelImg;
        }

        public String getFirstChar() {
            return firstChar;
        }

        public void setFirstChar(String firstChar) {
            this.firstChar = firstChar;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public int getModelMark() {
            return modelMark;
        }

        public void setModelMark(int modelMark) {
            this.modelMark = modelMark;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getSalestate() {
            return salestate;
        }

        public void setSalestate(String salestate) {
            this.salestate = salestate;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getYeartype() {
            return yeartype;
        }

        public void setYeartype(String yeartype) {
            this.yeartype = yeartype;
        }

        public String getProductionstate() {
            return productionstate;
        }

        public void setProductionstate(String productionstate) {
            this.productionstate = productionstate;
        }

        public String getSizetype() {
            return sizetype;
        }

        public void setSizetype(String sizetype) {
            this.sizetype = sizetype;
        }

        public int getIscommonuse() {
            return iscommonuse;
        }

        public void setIscommonuse(int iscommonuse) {
            this.iscommonuse = iscommonuse;
        }

        public String getBrandid() {
            return brandid;
        }

        public void setBrandid(String brandid) {
            this.brandid = brandid;
        }

        public String getBusinessid() {
            return businessid;
        }

        public void setBusinessid(String businessid) {
            this.businessid = businessid;
        }

        public String getDescriptionToString() {
            return descriptionToString;
        }

        public void setDescriptionToString(String descriptionToString) {
            this.descriptionToString = descriptionToString;
        }
    }

    public static class ItemsBean implements Serializable{
        /**
         * id : 260
         * modelName : DB9
         * fullName : 阿斯顿·马丁DB9
         * modelImg : http://pic1.jisuapi.cn/car/static/images/logo/300/260.JPG
         * firstChar : A
         * parentId : 259
         * modelMark : 3
         * remark : null
         * salestate : 停销
         * price :
         * yeartype :
         * productionstate :
         * sizetype :
         * iscommonuse : 0
         * brandid : 2
         * businessid : 259
         * descriptionToString : {"firstChar":"搜索首字母","modelImg":"车型logo","fullName":"车型全称","remark":"备注","productionstate":"生产状态","parentId":"父类id","modelName":"车型名称","yeartype":"年款","sizetype":"尺寸类型","modelMark":"层级标识 [1 车系 2 产商 3 车型 4 年款]","price":"官方指导价","id":"车型id","salestate":"销售状态"}
         */

        private String id;
        private String modelName;
        private String fullName;
        private String modelImg;
        private String firstChar;
        private String parentId;
        private int modelMark;
        private Object remark;
        private String salestate;
        private String price;
        private String yeartype;
        private String productionstate;
        private String sizetype;
        private int iscommonuse;
        private String brandid;
        private String businessid;
        private String descriptionToString;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getModelImg() {
            return modelImg;
        }

        public void setModelImg(String modelImg) {
            this.modelImg = modelImg;
        }

        public String getFirstChar() {
            return firstChar;
        }

        public void setFirstChar(String firstChar) {
            this.firstChar = firstChar;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public int getModelMark() {
            return modelMark;
        }

        public void setModelMark(int modelMark) {
            this.modelMark = modelMark;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getSalestate() {
            return salestate;
        }

        public void setSalestate(String salestate) {
            this.salestate = salestate;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getYeartype() {
            return yeartype;
        }

        public void setYeartype(String yeartype) {
            this.yeartype = yeartype;
        }

        public String getProductionstate() {
            return productionstate;
        }

        public void setProductionstate(String productionstate) {
            this.productionstate = productionstate;
        }

        public String getSizetype() {
            return sizetype;
        }

        public void setSizetype(String sizetype) {
            this.sizetype = sizetype;
        }

        public int getIscommonuse() {
            return iscommonuse;
        }

        public void setIscommonuse(int iscommonuse) {
            this.iscommonuse = iscommonuse;
        }

        public String getBrandid() {
            return brandid;
        }

        public void setBrandid(String brandid) {
            this.brandid = brandid;
        }

        public String getBusinessid() {
            return businessid;
        }

        public void setBusinessid(String businessid) {
            this.businessid = businessid;
        }

        public String getDescriptionToString() {
            return descriptionToString;
        }

        public void setDescriptionToString(String descriptionToString) {
            this.descriptionToString = descriptionToString;
        }
    }
}
