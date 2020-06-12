package com.car.notver.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/5/21
 * @name:CommodityInfo
 */
public class CommodityInfo implements Serializable {
    private String name;
    private String stock;
    private String mechanical;
    private String originalPrice;

    public CommodityInfo(String name, String stock, String mechanical, String originalPrice) {
        this.name=name;
        this.stock=stock;
        this.mechanical=mechanical;  this.originalPrice=originalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getMechanical() {
        return mechanical;
    }

    public void setMechanical(String mechanical) {
        this.mechanical = mechanical;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }



}
