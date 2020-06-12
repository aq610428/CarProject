package com.car.notver.adapter;

import android.content.Context;
import com.car.notver.R;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/26
 * @name:MeAdapter
 */
public class MeAdapter extends AutoRVAdapter {
    private List<String> strings = new ArrayList<>();

    public MeAdapter(Context context, List<String> list) {
        super(context, list);
        this.strings = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_mine;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        vh.getTextView(R.id.text_name).setText(strings.get(position));
    }
}
