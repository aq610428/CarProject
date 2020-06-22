package com.car.notver.adapter;

import android.content.Context;

import com.car.notver.R;
import com.car.notver.bean.BrandVo;
import com.car.notver.bean.KeepInfo;
import com.car.notver.bean.YearCar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class RightBrandAdapter extends AutoRVAdapter {
    private List<YearCar> inventories = new ArrayList<>();
    private Context mContext;

    public RightBrandAdapter(Context context, List<YearCar> inventories) {
        super(context,inventories);
        this.mContext = context;
        this.inventories = inventories;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    public void setData(List<KeepInfo> infos) {
        this.inventories = inventories;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_right_brand;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        YearCar inventory = inventories.get(position);
        vh.getTextView(R.id.text_name).setText(inventory.getModelName()+ "");
    }


}
