package com.car.notver.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.car.notver.R;
import com.car.notver.bean.StoreInfo;
import com.car.notver.glide.GlideUtils;
import com.car.notver.util.Utility;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/28
 * @name:StoreAdapter
 */
public class StoreAdapter extends AutoRVAdapter {
    private List<StoreInfo> infos;

    public StoreAdapter(Context context, List<StoreInfo> list) {
        super(context, list);
        this.infos = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_store;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        StoreInfo info = infos.get(position);
        if (info.getStatus() == 1) {
            vh.getTextView(R.id.text_verify).setText("待完善资料");
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.GONE);
            vh.getTextView(R.id.text_verify).setBackgroundResource(R.drawable.store_shape_item);
        } else if (info.getStatus() == 2) {
            vh.getTextView(R.id.text_verify).setText("审核中");
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.GONE);
            vh.getTextView(R.id.text_verify).setBackgroundResource(R.drawable.store_shape_item);
        } else if (info.getStatus() == 3) {
            vh.getTextView(R.id.text_verify).setText("审核通过");
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.GONE);
            vh.getTextView(R.id.text_verify).setBackgroundResource(R.drawable.store_shape_item_nor);
        } else {
            vh.getTextView(R.id.text_verify).setText("审核不通过");
            vh.getLinearLayout(R.id.ll_tab).setVisibility(View.VISIBLE);
            vh.getTextView(R.id.text_verify).setBackgroundResource(R.drawable.store_shape_item);
            vh.getTextView(R.id.text_reviewContent).setText(info.getReviewContent() + "");
        }
        vh.getTextView(R.id.text_name).setText(infos.get(position).getName());
        GlideUtils.CreateImageRound(infos.get(position).getLogo(), vh.getImageView(R.id.iv_photo), 5);
        String createTime = info.getStringCreateTime();
        if (!Utility.isEmpty(createTime) && createTime.length() > 8) {
            String end = createTime.substring(0, createTime.length() - 8);
            String start = createTime.substring(createTime.length() - 8, createTime.length());
            vh.getTextView(R.id.tv_date).setText(end + "  " + start);
        }

    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void setData(List<StoreInfo> infos) {
        this.infos = infos;
    }
}
