package com.car.notver.adapter;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.car.notver.R;
import com.car.notver.bean.KeepInfo;
import com.car.notver.bean.Ordered;
import com.car.notver.ui.fragment.MallFragment;
import com.car.notver.util.BigDecimalUtils;
import com.car.notver.util.Utility;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/15
 * @name:CustomerAdapter
 */
public class MailAdapter extends BaseAdapter {
    private List<KeepInfo> list;
    private MallFragment fragment;

    public MailAdapter(MallFragment fragment, List<KeepInfo> ordereds) {
        this.fragment = fragment;
        this.list = ordereds;
    }


    public void setData(List<KeepInfo> bespokes) {
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
            convertView = View.inflate(fragment.getContext(), R.layout.item_open_mail, null);
            vh.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            vh.tv_make = (TextView) convertView.findViewById(R.id.tv_make);
            vh.tv_license = (TextView) convertView.findViewById(R.id.tv_license);
            vh.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            vh.tv_num = convertView.findViewById(R.id.tv_num);
            vh.tv_accessory = convertView.findViewById(R.id.tv_accessory);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        KeepInfo ordered = list.get(position);
        vh.tv_date.setText(ordered.getLoginname() + "");
        vh.tv_make.setText(ordered.getCarcard() + "");

        if (!Utility.isEmpty(ordered.getMileages())) {
            String mile = BigDecimalUtils.div(new BigDecimal(ordered.getMileages()), new BigDecimal(1000), 0).toPlainString();
            vh.tv_license.setText(mile + "km");
        }

        if (!Utility.isEmpty(ordered.getOils())) {
            String mile = BigDecimalUtils.div(new BigDecimal(ordered.getOils()), new BigDecimal(1000), 0).toPlainString();
            vh.tv_phone.setText(mile + "升");
        }
        vh.tv_num.setText(ordered.getFaults() + "");
        vh.tv_accessory.setText(ordered.getAlarms() + "次");

        return convertView;
    }


    class ViewHolder {
        private TextView tv_date, tv_make, tv_license, tv_phone, tv_num, tv_accessory;
    }
}
