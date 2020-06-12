package com.car.notver.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.car.notver.R;
import com.car.notver.bean.KeepInfo;
import com.car.notver.util.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class ClientMallAdapter extends BaseAdapter {
    private List<KeepInfo> inventories = new ArrayList<>();
    private Context mContext;

    public ClientMallAdapter(Context context, List<KeepInfo> inventories) {
        this.mContext = context;
        this.inventories = inventories;
    }


    @Override
    public int getCount() {
        return inventories.size();
    }

    @Override
    public Object getItem(int position) {
        return inventories.get(position);
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
            convertView = View.inflate(mContext, R.layout.item_keep_mall, null);
            vh.text_num = convertView.findViewById(R.id.text_num);
            vh.text_license = convertView.findViewById(R.id.text_license);
            vh.text_brand = convertView.findViewById(R.id.text_brand);
            vh.text_traveled = convertView.findViewById(R.id.text_traveled);
            vh.ll_tab0 = convertView.findViewById(R.id.ll_tab0);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        KeepInfo inventory = inventories.get(position);
        if (position == 0) {
            vh.ll_tab0.setVisibility(View.VISIBLE);
        } else {
            vh.ll_tab0.setVisibility(View.GONE);
        }
        vh.text_num.setText((position + 1) + "");
        vh.text_license.setText(inventory.getCarcard());
        if (Utility.isEmpty(inventory.getNickname())) {
            vh.text_brand.setText("匿名");
        } else {
            vh.text_brand.setText(inventory.getNickname() + "");
        }

        vh.text_traveled.setText(inventory.getLoginname());
        return convertView;
    }

    public void setData(List<KeepInfo> infos) {
        this.inventories = inventories;
    }

    class ViewHolder {
        private TextView text_num, text_license, text_brand, text_traveled;
        private LinearLayout ll_tab0;
    }
}
