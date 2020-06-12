package com.car.notver.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.car.notver.R;
import com.car.notver.bean.KeepInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class InventoryMallAdapter extends BaseAdapter {
    private List<KeepInfo> inventories = new ArrayList<>();
    private Context mContext;

    public InventoryMallAdapter(Context context, List<KeepInfo> inventories) {
        this.mContext = context;
        this.inventories=inventories;
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
            convertView = View.inflate(mContext, R.layout.item_inventory_new, null);
            vh.text_license = (TextView) convertView.findViewById(R.id.text_license);
            vh.text_brand = (TextView) convertView.findViewById(R.id.text_brand);
            vh.text_traveled = (TextView) convertView.findViewById(R.id.text_traveled);
            vh.text_last_traveled = (TextView) convertView.findViewById(R.id.text_last_traveled);
            vh.ll_tab = convertView.findViewById(R.id.ll_tab);
            vh.ll_tab1 = convertView.findViewById(R.id.ll_tab1);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if (position == 0) {
            vh.ll_tab.setVisibility(View.VISIBLE);
        } else {
            vh.ll_tab.setVisibility(View.GONE);
        }
        KeepInfo inventory = inventories.get(position);
        vh.text_license.setText(inventory.getCarcard());
        vh.text_brand.setText(inventory.getFactory());
        vh.text_traveled.setText(inventory.getTotalmileage());
        vh.text_last_traveled.setText(inventory.getLastmaintainmileage());
        return convertView;
    }

    public void setData(List<KeepInfo> data) {
        this.inventories = data;
    }



    class ViewHolder {
        private TextView text_license, text_brand, text_traveled,text_last_traveled;
        private LinearLayout ll_tab, ll_tab1;
    }
}
