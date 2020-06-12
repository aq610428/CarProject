package com.car.notver.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;

import com.car.notver.R;
import com.car.notver.bean.ClientInfo;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/19
 * @name:ClientAdapter
 */
public class FrontAdapter extends AutoRVAdapter {
    private List<ClientInfo> data;


    public FrontAdapter(Context context, List<ClientInfo> list) {
        super(context, list);
        this.data = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_fount;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        ClientInfo clientInfo = data.get(position);
        vh.getTextView(R.id.text_name).setText(clientInfo.getName());
        vh.getTextView(R.id.text_phone).setText(clientInfo.getPhone());
        vh.getTextView(R.id.text_pay).setText("总计欠款:" + clientInfo.getLicense() + "元");

    }

}
