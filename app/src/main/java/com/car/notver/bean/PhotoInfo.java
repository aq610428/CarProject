package com.car.notver.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * @author: zt
 * @date: 2020/5/18
 * @name:PhotoInfo
 */
public class PhotoInfo implements Serializable {
    private String title;
    private String explain;
    private String id;
    private Bitmap bitmap;
    private String pic;

    public boolean isCherk() {
        return isCherk;
    }

    public void setCherk(boolean cherk) {
        isCherk = cherk;
    }

    private boolean isCherk;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }



    public PhotoInfo(String title, String explain, String id) {
        this.title=title;
        this.explain=explain;
        this.id=id;
    }



    public PhotoInfo(String title, String explain, String id,String pic) {
        this.title=title;
        this.explain=explain;
        this.id=id;
        this.pic=pic;
    }


    public PhotoInfo(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }



}
