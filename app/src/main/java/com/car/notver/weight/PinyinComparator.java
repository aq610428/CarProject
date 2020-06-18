package com.car.notver.weight;

import com.car.notver.bean.Brand;

import java.util.Comparator;

/**
 * @author: zt
 * @date: 2020/6/18
 * @name:PinyinComparator
 */
public class PinyinComparator implements Comparator<Brand> {
    public int compare(Brand o1, Brand o2) {
        //这里主要是用来对ListView里面的数据根据ABCDEFG...来排序
        if (o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }
}
