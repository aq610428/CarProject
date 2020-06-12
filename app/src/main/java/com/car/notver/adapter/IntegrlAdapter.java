package com.car.notver.adapter;

import android.content.Context;
import android.graphics.Color;
import com.car.notver.R;
import com.car.notver.bean.Integral;
import com.car.notver.util.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/6/3
 * @name:IntegrlAdapter
 */
public class IntegrlAdapter extends AutoRVAdapter {
    private List<Integral> integrals = new ArrayList<>();

    public IntegrlAdapter(Context context, List<Integral> list) {
        super(context, list);
        this.integrals = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_integrl;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Integral integral = integrals.get(position);
        vh.getTextView(R.id.text_name).setText(integral.getRemark() + "");
        if (integral.getType() == 1 || integral.getType() == 4) {
            vh.getTextView(R.id.text_Mechanical).setText("+" + integral.getIntegral() + "");
            vh.getTextView(R.id.text_Mechanical).setTextColor(Color.parseColor("#4FB053"));
        } else {
            vh.getTextView(R.id.text_Mechanical).setText("-" + integral.getIntegral() + "");
            vh.getTextView(R.id.text_Mechanical).setTextColor(Color.parseColor("#F61B00"));
        }

        String createTime = integral.getCreateTime();
        if (!Utility.isEmpty(createTime) && createTime.length() > 8) {
            String end = createTime.substring(0, createTime.length() - 8);
            String start = createTime.substring(createTime.length() -8, createTime.length());
            vh.getTextView(R.id.text_date).setText(end + "  " + start);
        }

    }

    public void setData(List<Integral> integrals) {
        this.integrals = integrals;
    }
}
