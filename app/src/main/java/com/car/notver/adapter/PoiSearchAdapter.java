package com.car.notver.adapter;

import android.content.Context;
import com.amap.api.services.core.PoiItem;
import com.car.notver.R;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/6/8
 * @name:PoiSearchAdapter
 */
public class PoiSearchAdapter extends AutoRVAdapter {
    List<PoiItem> poiItems;

    public PoiSearchAdapter(Context context, List<PoiItem> list) {
        super(context, list);
        this.poiItems = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_poisearch;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        PoiItem poiItem = poiItems.get(position);
        vh.getTextView(R.id.text_edition).setText(poiItem.getTitle());
    }
}
