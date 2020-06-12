package com.car.notver.bean;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/6/2
 * @name:Money
 */
public class Money implements Serializable {


    /**
     * price_usd : 0.2106
     * volume_24h_cny : 0
     * symbol : XRP
     * last_updated : 1.591062304142E12
     * total_supply : 0
     * market_cap_cny : 0
     * price_cny : 1.5012
     * logo_png : http://img.jkabe.com/coins/xrp.png
     * price_btc : 0
     * available_supply : 0
     * market_cap_usd : 0
     * percent_change_1h : 0
     * percent_change_24h : 2.98
     * name : xrpusdt
     * volume_24h_usd : 5870982.9159
     * max_supply : 0
     * id : xrp
     * percent_change_7d : 0
     */

    private double price_usd;
    private int volume_24h_cny;
    private String symbol;
    private double last_updated;
    private int total_supply;
    private int market_cap_cny;
    private double price_cny;
    private String logo_png;
    private String price_btc;
    private int available_supply;
    private int market_cap_usd;
    private int percent_change_1h;
    private double percent_change_24h;
    private String name;
    private double volume_24h_usd;
    private int max_supply;
    private String id;
    private int percent_change_7d;

    public double getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(double price_usd) {
        this.price_usd = price_usd;
    }

    public int getVolume_24h_cny() {
        return volume_24h_cny;
    }

    public void setVolume_24h_cny(int volume_24h_cny) {
        this.volume_24h_cny = volume_24h_cny;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(double last_updated) {
        this.last_updated = last_updated;
    }

    public int getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(int total_supply) {
        this.total_supply = total_supply;
    }

    public int getMarket_cap_cny() {
        return market_cap_cny;
    }

    public void setMarket_cap_cny(int market_cap_cny) {
        this.market_cap_cny = market_cap_cny;
    }

    public double getPrice_cny() {
        return price_cny;
    }

    public void setPrice_cny(double price_cny) {
        this.price_cny = price_cny;
    }

    public String getLogo_png() {
        return logo_png;
    }

    public void setLogo_png(String logo_png) {
        this.logo_png = logo_png;
    }

    public String getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(String price_btc) {
        this.price_btc = price_btc;
    }

    public int getAvailable_supply() {
        return available_supply;
    }

    public void setAvailable_supply(int available_supply) {
        this.available_supply = available_supply;
    }

    public int getMarket_cap_usd() {
        return market_cap_usd;
    }

    public void setMarket_cap_usd(int market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }

    public int getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(int percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public double getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(double percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVolume_24h_usd() {
        return volume_24h_usd;
    }

    public void setVolume_24h_usd(double volume_24h_usd) {
        this.volume_24h_usd = volume_24h_usd;
    }

    public int getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(int max_supply) {
        this.max_supply = max_supply;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(int percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }
}
