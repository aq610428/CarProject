package com.car.notver.adapter;


import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.car.notver.R;
import com.car.notver.bean.Ordered;
import com.car.notver.ui.activity.OrderActivity;
import com.car.notver.ui.fragment.MallFragment;
import com.car.notver.util.Utility;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/15
 * @name:CustomerAdapter
 */
public class OpenAdapter extends BaseAdapter {
    private List<Ordered> list;
    private MallFragment fragment;

    public OpenAdapter(MallFragment fragment, List<Ordered> ordereds) {
        this.fragment = fragment;
        this.list = ordereds;
    }


    public void setData(List<Ordered> bespokes) {
        this.list = bespokes;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = View.inflate(fragment.getContext(), R.layout.item_open, null);
            vh.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            vh.tv_make = (TextView) convertView.findViewById(R.id.tv_make);
            vh.tv_license = (TextView) convertView.findViewById(R.id.tv_license);
            vh.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            vh.tv_billing = convertView.findViewById(R.id.tv_billing);
            vh.tv_cash = convertView.findViewById(R.id.tv_cash);
            vh.tv_finish = convertView.findViewById(R.id.tv_finish);
            vh.tv_num = convertView.findViewById(R.id.tv_num);
            vh.tv_accessory = convertView.findViewById(R.id.tv_accessory);
            vh.tv_parts = convertView.findViewById(R.id.tv_parts);
            vh.btn_code = convertView.findViewById(R.id.btn_code);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Ordered ordered = list.get(position);
        if (!Utility.isEmpty(ordered.getFinishTime())) {
            String str[] = ordered.getFinishTime().split("T");
            if (str != null && str.length > 0) {
                vh.tv_finish.setText(str[0] + " "+str[1].substring(0,str[1].length()-5));
            }
        }
        String createTime = ordered.getStringCreateTime();
        if (!Utility.isEmpty(createTime) && createTime.length() > 8) {
            String end = createTime.substring(0, createTime.length() - 8);
            String start = createTime.substring(createTime.length() -8, createTime.length());
            vh.tv_date.setText(end + "  " + start);
        }
        vh.tv_make.setText(ordered.getProject() + "");

        vh.tv_license.setText(ordered.getCarcard() + "");
        vh.tv_phone.setText(ordered.getMobile() + "");

        vh.tv_billing.setText(ordered.getIntegral() + "");
        vh.tv_cash.setText("￥" + ordered.getAmount() + "");
        vh.tv_num.setText(ordered.getPartsNum() + "");
        vh.tv_accessory.setText(ordered.getPartsReplace() + "");
        vh.tv_parts.setText(ordered.getRepairPlan() + "");
        if (!Utility.isEmpty(ordered.getOrderId())) {
            if (ordered.getIntegralFlag() == 1) {//待支付
                vh.btn_code.setText("收取积分");
            }else{
                vh.btn_code.setText("已收取积分");
            }
        } else {
            vh.btn_code.setText("收取积分");
        }

        vh.btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fragment.getContext(), OrderActivity.class);
                intent.putExtra("id", ordered.getId());
                fragment.getContext().startActivity(intent);
            }
        });
        return convertView;
    }


    class ViewHolder {
        private TextView tv_date, tv_make, tv_license, tv_phone, tv_billing, tv_cash, tv_num, tv_accessory, tv_parts, tv_finish;
        private TextView btn_code;
    }
}
