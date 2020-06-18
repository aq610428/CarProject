package com.car.notver.adapter;

import android.content.Context;

import com.car.notver.R;
import com.car.notver.bean.Brand;
import com.car.notver.bean.ClientVo;
import com.car.notver.glide.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class BrandDeilAdapter extends AutoRVAdapter {
    private List<ClientVo> inventories = new ArrayList<>();
    private Context mContext;

    public BrandDeilAdapter(Context context, List<ClientVo> inventories) {
        super(context,inventories);
        this.mContext = context;
        this.inventories = inventories;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public void setData(List<Brand> infos) {
        this.inventories = inventories;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_brand_deile;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        ClientVo inventory = inventories.get(position);
        vh.getTextView(R.id.text_name).setText(inventory.getCarcard());
        vh.getTextView(R.id.text_mobile).setText(inventory.getMobile());
        vh.getTextView(R.id.text_business).setText(inventory.getBusiness());
        vh.getTextView(R.id.text_yearmodel).setText(inventory.getYearmodel());
    }


}
