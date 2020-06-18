package com.car.notver.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.car.notver.R;
import com.car.notver.bean.Brand;
import com.car.notver.glide.GlideUtils;
import com.car.notver.util.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:清单
 */
public class InventoryAdapter extends BaseAdapter implements SectionIndexer {
    private List<Brand> inventories = new ArrayList<>();
    private Context mContext;

    public InventoryAdapter(Context context, List<Brand> inventories) {
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
            convertView = View.inflate(mContext, R.layout.item_brand, null);
            vh.text_brand = convertView.findViewById(R.id.text_brand);
            vh.iv_logo =  convertView.findViewById(R.id.iv_logo);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Brand inventory = inventories.get(position);
        vh.text_brand.setText(inventory.getModelName());
        GlideUtils.setImageUrl(inventory.getModelImg(), vh.iv_logo);
        return convertView;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = inventories.get(i).getSortLetters();
            if (!Utility.isEmpty(sortStr)){
                char firstChar = sortStr.toUpperCase().charAt(0);
                if (firstChar == section) {
                    return i;
                }
            }

        }

        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return inventories.get(position).getSortLetters().charAt(0);
    }

    class ViewHolder {
        private TextView text_brand;
        private ImageView iv_logo;
    }
}
