package com.car.notver.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.car.notver.R;
import com.car.notver.bean.Inventory;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class InventoryAdapter extends BaseAdapter {
    private List<Inventory> inventories = new ArrayList<>();
    private Context mContext;

    public InventoryAdapter(Context context, List<Inventory> inventories) {
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
            convertView = View.inflate(mContext, R.layout.item_inventory, null);
            vh.text_initiation = (TextView) convertView.findViewById(R.id.text_initiation);
            vh.text_flameout = (TextView) convertView.findViewById(R.id.text_flameout);
            vh.text_kilometre = (TextView) convertView.findViewById(R.id.text_kilometre);
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
        Inventory inventory = inventories.get(position);
        vh.text_initiation.setText(inventory.getInitiation());
        vh.text_flameout.setText(inventory.getFlameout());
        vh.text_kilometre.setText(inventory.getKilometre());
        return convertView;
    }

    class ViewHolder {
        private TextView text_initiation, text_flameout, text_kilometre;
        private LinearLayout ll_tab, ll_tab1;
    }
}
