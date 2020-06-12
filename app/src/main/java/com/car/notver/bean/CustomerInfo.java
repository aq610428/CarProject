package com.car.notver.bean;


/**
 * @author: zt
 * @date: 2020/5/28
 * @name:CustomerInfo
 */
public class CustomerInfo {
    public CustomerInfo(String name, int drawable) {
        this.name=name;
        this.drawable=drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    private String name;
    private int drawable;
}
