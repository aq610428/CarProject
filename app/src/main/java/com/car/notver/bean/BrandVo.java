package com.car.notver.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/6/19
 * @name:BrandVo
 */
public class BrandVo implements Serializable {

    /**
     * business : {"id":"219","modelName":"一汽奥迪","fullName":"一汽奥迪","modelImg":null,"firstChar":"A","parentId":"1","modelMark":2,"remark":null,"salestate":"","price":"","yeartype":"","productionstate":"","sizetype":"","iscommonuse":0,"brandid":"1","businessid":"219","descriptionToString":"{\"firstChar\":\"搜索首字母\",\"modelImg\":\"车型logo\",\"fullName\":\"车型全称\",\"remark\":\"备注\",\"productionstate\":\"生产状态\",\"parentId\":\"父类id\",\"modelName\":\"车型名称\",\"yeartype\":\"年款\",\"sizetype\":\"尺寸类型\",\"modelMark\":\"层级标识 [1 车系 2 产商 3 车型 4 年款]\",\"price\":\"官方指导价\",\"id\":\"车型id\",\"salestate\":\"销售状态\"}"}
     * items : [{"id":"220","modelName":"A3","fullName":"奥迪A3","modelImg":"http://pic1.jisuapi.cn/car/static/images/logo/300/220.jpg","firstChar":"A","parentId":"219","modelMark":3,"remark":null,"salestate":"在销","price":"","yeartype":"","productionstate":"","sizetype":"","iscommonuse":0,"brandid":"1","businessid":"219","descriptionToString":"{\"firstChar\":\"搜索首字母\",\"modelImg\":\"车型logo\",\"fullName\":\"车型全称\",\"remark\":\"备注\",\"productionstate\":\"生产状态\",\"parentId\":\"父类id\",\"modelName\":\"车型名称\",\"yeartype\":\"年款\",\"sizetype\":\"尺寸类型\",\"modelMark\":\"层级标识 [1 车系 2 产商 3 车型 4 年款]\",\"price\":\"官方指导价\",\"id\":\"车型id\",\"salestate\":\"销售状态\"}"},{"id":"221","modelName":"A4L","fullName":"奥迪A4L","modelImg":"http://pic1.jisuapi.cn/car/static/images/logo/300/221.jpg","firstChar":"A","parentId":"219","modelMark":3,"remark":null,"salestate":"在销","price":"","yeartype":"","productionstate":"","sizetype":"","iscommonuse":0,"brandid":"1","businessid":"219","descriptionToString":"{\"firstChar\":\"搜索首字母\",\"modelImg\":\"车型logo\",\"fullName\":\"车型全称\",\"remark\":\"备注\",\"productionstate\":\"生产状态\",\"parentId\":\"父类id\",\"modelName\":\"车型名称\",\"yeartype\":\"年款\",\"sizetype\":\"尺寸类型\",\"modelMark\":\"层级标识 [1 车系 2 产商 3 车型 4 年款]\",\"price\":\"官方指导价\",\"id\":\"车型id\",\"salestate\":\"销售状态\"}"}]
     */

    private BusinessBean business;
    private List<ItemsBean> items;

    public BusinessBean getBusiness() {
        return business;
    }

    public void setBusiness(BusinessBean business) {
        this.business = business;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class BusinessBean {
        /**
         * id : 219
         * modelName : 一汽奥迪
         * fullName : 一汽奥迪
         * modelImg : null
         * firstChar : A
         * parentId : 1
         * modelMark : 2
         * remark : null
         * salestate :
         * price :
         * yeartype :
         * productionstate :
         * sizetype :
         * iscommonuse : 0
         * brandid : 1
         * businessid : 219
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

    public static class ItemsBean {
        /**
         * id : 220
         * modelName : A3
         * fullName : 奥迪A3
         * modelImg : http://pic1.jisuapi.cn/car/static/images/logo/300/220.jpg
         * firstChar : A
         * parentId : 219
         * modelMark : 3
         * remark : null
         * salestate : 在销
         * price :
         * yeartype :
         * productionstate :
         * sizetype :
         * iscommonuse : 0
         * brandid : 1
         * businessid : 219
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
