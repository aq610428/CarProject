package com.car.notver.adapter;

import android.content.Context;
import com.car.notver.R;
import com.car.notver.bean.Money;
import com.car.notver.glide.GlideUtils;
import com.car.notver.util.BigDecimalUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class CarAdapter extends AutoRVAdapter {
    private List<Money> inventories = new ArrayList<>();
    private Context mContext;

    public CarAdapter(Context context, List<Money> inventories) {
        super(context,inventories);
        this.mContext = context;
        this.inventories = inventories;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_keep_car;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Money inventory = inventories.get(position);
        vh.getTextView(R.id.text_name).setText(inventory.getSymbol() + "");
        vh.getTextView(R.id.text_price).setText(BigDecimalUtils.subLastBit(inventory.getPrice_usd(),4) + "");
        if (inventory.getPercent_change_24h()>0){
            vh.getTextView(R.id.text_Increase).setBackgroundResource(R.drawable.shape_login);
        }else{
            vh.getTextView(R.id.text_Increase).setBackgroundResource(R.drawable.shape_login_red);
        }
        vh.getTextView(R.id.text_Increase).setText(inventory.getPercent_change_24h() + "%");
        GlideUtils.setImageUrl(inventory.getLogo_png(), vh.getImageView(R.id.iv_logo));
    }


}
