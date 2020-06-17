package com.car.notver.adapter;

import android.content.Context;
import com.car.notver.R;
import com.car.notver.bean.StoreInfo;
import com.car.notver.util.Utility;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/6/16
 * @name:AdministrationAdapter
 */
public class AdministrationAdapter extends AutoRVAdapter {
    private List<StoreInfo> list = new ArrayList<>();


    public AdministrationAdapter(Context context, List<StoreInfo> list) {
        super(context, list);
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_administration;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        StoreInfo storeInfo = list.get(position);
        vh.getTextView(R.id.text_name).setText(storeInfo.getName());
        vh.getTextView(R.id.text_contoct).setText(storeInfo.getContactPerson());

        vh.getTextView(R.id.text_phone).setText(storeInfo.getPhone());
        vh.getTextView(R.id.text_open).setText(storeInfo.getOperTime());
        vh.getTextView(R.id.text_address).setText(storeInfo.getAddress());
        vh.getTextView(R.id.text_contoct).setText(storeInfo.getContactPerson());
        vh.getTextView(R.id.text_brand).setText(storeInfo.getBusinessScope());



        String createTime = storeInfo.getStringCreateTime();
        if (!Utility.isEmpty(createTime) && createTime.length() > 8) {
            String end = createTime.substring(0, createTime.length() - 8);
            String start = createTime.substring(createTime.length() -8, createTime.length());
            vh.getTextView(R.id.text_date).setText(end + "  " + start);
        }
    }

    public void setData(List<StoreInfo> infos) {
        this.list=infos;
    }
}
