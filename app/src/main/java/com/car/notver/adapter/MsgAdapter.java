package com.car.notver.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;

import com.car.notver.R;
import com.car.notver.bean.Bespoke;
import com.car.notver.bean.Massage;
import com.car.notver.util.Utility;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/19
 * @name:ClientAdapter
 */
public class MsgAdapter extends AutoRVAdapter {
    private List<Massage> data;

    public MsgAdapter(Context context, List<Massage> list) {
        super(context, list);
        this.data = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_msg;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Massage massage = data.get(position);
        vh.getTextView(R.id.text_name).setText(massage.getTitle());
        String createTime = massage.getStringCreateTime().toString();
        if (!Utility.isEmpty(createTime) && createTime.length() > 8) {
            String end = createTime.substring(0, createTime.length() - 8);
            String start = createTime.substring(createTime.length() -8, createTime.length());
            vh.getTextView(R.id.text_date).setText(end + "  " + start);
        }
        vh.getTextView(R.id.text_verify).setText(massage.getContent());
    }

    public void setData(List<Massage> bespokes) {
        this.data = bespokes;
    }
}
