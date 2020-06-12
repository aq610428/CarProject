package com.car.notver.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:Inventory
 */
public class Inventory implements Serializable {
    private String initiation;

    private String flameout;
    private String kilometre;

    public Inventory(String initiation, String flameout, String kilometre) {
        this.initiation = initiation;
        this.flameout = flameout;
        this.kilometre = kilometre;
    }

    public String getInitiation() {
        return initiation;
    }

    public void setInitiation(String initiation) {
        this.initiation = initiation;
    }

    public String getFlameout() {
        return flameout;
    }

    public void setFlameout(String flameout) {
        this.flameout = flameout;
    }

    public String getKilometre() {
        return kilometre;
    }

    public void setKilometre(String kilometre) {
        this.kilometre = kilometre;
    }

}
