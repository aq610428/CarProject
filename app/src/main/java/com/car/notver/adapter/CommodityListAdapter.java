package com.car.notver.adapter;

import android.content.Context;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/22
 * @name:选择商品
 */
public class CommodityListAdapter extends AutoRVAdapter {
    public CommodityListAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }
}
