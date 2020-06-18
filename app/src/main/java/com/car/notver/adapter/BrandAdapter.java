package com.car.notver.adapter;

import android.content.Context;

import com.car.notver.R;
import com.car.notver.bean.Brand;
import com.car.notver.bean.KeepInfo;
import com.car.notver.bean.Money;
import com.car.notver.glide.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class BrandAdapter extends AutoRVAdapter {
    private List<Brand> inventories = new ArrayList<>();
    private Context mContext;

    public BrandAdapter(Context context, List<Brand> inventories) {
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
        return R.layout.item_brand_ifg;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Brand inventory = inventories.get(position);
        vh.getTextView(R.id.text_brand).setText(inventory.getModelName()+ "");
        GlideUtils.setImageUrl(inventory.getModelImg(), vh.getImageView(R.id.iv_logo));
    }


}
