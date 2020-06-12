package com.car.notver.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.car.notver.R;
import com.car.notver.bean.Bespoke;
import com.car.notver.ui.activity.OrderActivity;
import com.car.notver.ui.fragment.MallFragment;
import com.car.notver.util.Utility;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/15
 * @name:CustomerAdapter
 */
public class OwnerAdapter1 extends BaseAdapter {
    private List<Bespoke> list;
    private MallFragment fragment;
    public String phone = "";

    public OwnerAdapter1(MallFragment fragment, List<Bespoke> keepInfos) {
        this.fragment = fragment;
        this.list = keepInfos;
    }


    public void setData(List<Bespoke> bespokes) {
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
            convertView = View.inflate(fragment.getContext(), R.layout.item_owner1, null);
            vh.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            vh.tv_make = (TextView) convertView.findViewById(R.id.tv_make);
            vh.tv_license = (TextView) convertView.findViewById(R.id.tv_license);
            vh.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            vh.text_receiving = convertView.findViewById(R.id.text_receiving);
            vh.tv_model = convertView.findViewById(R.id.tv_model);
            vh.tv_date_string = convertView.findViewById(R.id.tv_date_string);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Bespoke bespoke = list.get(position);
        String time = bespoke.getStringCreateTime();
        if (!Utility.isEmpty(time) && time.length() > 7) {
            String end = time.substring(0, 10);
            String start = time.substring(time.length() - 8, time.length());
            vh.tv_date.setText(end + "  " + start);
        }

        String orderTime = bespoke.getOrderTime();
        if (!Utility.isEmpty(orderTime) && orderTime.length() > 5) {
            String end = orderTime.substring(0, orderTime.length() - 5);
            String start = orderTime.substring(orderTime.length() - 5, orderTime.length());
            vh.tv_date_string.setText(end + "  " + start);
        }

        vh.tv_make.setText(bespoke.getProject() + "");
        vh.tv_model.setText(bespoke.getStoreName());
        vh.tv_license.setText(bespoke.getCarcard() + "");
        vh.tv_phone.setText(bespoke.getMobile() + "");
        vh.tv_make.setText(bespoke.getProject() + "");

        if (bespoke.getStatus() == 1) {//未接单
            vh.text_receiving.setText("接受预约");
            ViewHolder finalVh = vh;
            vh.text_receiving.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalVh.text_receiving.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            fragment.queryOrder(2, bespoke.getId());
                        }
                    });
                }
            });
        } else if (bespoke.getStatus() == 3) {//已接单，待完成
            vh.text_receiving.setText("已拒绝预约");
        } else if (bespoke.getStatus() == 2) {//已接单，待完成
            vh.text_receiving.setText("去开单");
            ViewHolder finalVh = vh;
            vh.text_receiving.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalVh.text_receiving.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(fragment.getContext(), OrderActivity.class);
                            intent.putExtra("bespoke", bespoke);
                            fragment.getContext().startActivity(intent);
                        }
                    });
                }
            });
        } else if (bespoke.getStatus() == 4) {//已接单，待完成
            if (bespoke.getIntegralFlag() == 1) {
                vh.text_receiving.setText("收取积分");
            } else {
                vh.text_receiving.setText("已收取积分");
            }

            ViewHolder finalVh = vh;
            vh.text_receiving.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalVh.text_receiving.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(fragment.getContext(), OrderActivity.class);
                            intent.putExtra("bespoke", bespoke);
                            fragment.getContext().startActivity(intent);
                        }
                    });
                }
            });
        }
        return convertView;
    }


    class ViewHolder {
        private TextView tv_date, tv_make, tv_license, tv_phone, text_receiving, tv_model, tv_date_string;
    }
}
