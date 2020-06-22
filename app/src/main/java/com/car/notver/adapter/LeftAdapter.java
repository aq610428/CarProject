package com.car.notver.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import com.car.notver.R;
import com.car.notver.bean.PhotoInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class LeftAdapter extends AutoRVAdapter {
    private List<PhotoInfo> inventories = new ArrayList<>();
    private Context mContext;
    private SparseBooleanArray selected;
    private boolean isSingle = true;
    private int old = 0;

    public LeftAdapter(Context context, List<PhotoInfo> inventories) {
        super(context, inventories);
        this.mContext = context;
        this.inventories = inventories;
        selected = new SparseBooleanArray();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    public void setData(List<PhotoInfo> infos) {
        this.inventories = inventories;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_left;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        PhotoInfo inventory = inventories.get(position);
        vh.getTextView(R.id.title_text_tv).setText(inventory.getTitle() +"");

        if (selected.get(position)) {
            vh.getTextView(R.id.title_text_tv).setBackgroundColor(Color.parseColor("#FFFFFF"));
            vh.getTextView(R.id.title_text_tv).setTextColor(Color.parseColor("#3F80F4"));
        } else {
            vh.getTextView(R.id.title_text_tv).setBackgroundColor(Color.parseColor("#F8FAFF"));
            vh.getTextView(R.id.title_text_tv).setTextColor(Color.parseColor("#333333"));
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
