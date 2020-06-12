package com.car.notver.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import com.car.notver.R;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/19
 * @name:ClientAdapter
 */
public class ClientAdapter extends AutoRVAdapter {
    private List<String> data;
    private SparseBooleanArray selected;
    private boolean isSingle = true;
    private int old = 0;

    public ClientAdapter(Context context, List<String> list) {
        super(context, list);
        this.data = list;
        selected = new SparseBooleanArray();
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_client;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        vh.getTextView(R.id.text_take_min).setText(data.get(position));
        if (selected.get(position)) {
            vh.getLinearLayout(R.id.ll_bg).setBackgroundResource(R.drawable.next_bg);
        } else {
            vh.getLinearLayout(R.id.ll_bg).setBackgroundResource(R.drawable.next_bg_nor);
        }
    }

    public void setSelectedItem(int selected) {
        if (isSingle = true && old != -1) {
            this.selected.put(old, false);
        }
        this.selected.put(selected, true);
        old = selected;
    }
}
