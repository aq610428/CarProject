package com.car.notver.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者：Administrator on 2017/2/17.
 */

public class RVHolder extends RecyclerView.ViewHolder {


    private ViewHolder vh;

    public RVHolder(View itemView) {
        super(itemView);
        vh = ViewHolder.getViewHolder(itemView);
    }


    public ViewHolder getViewHolder() {
        return vh;
    }
}
