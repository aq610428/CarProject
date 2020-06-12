package com.car.notver.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.car.notver.R;
import com.car.notver.bean.KeepInfo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/5/19
 * @name:ClientAdapter
 */
public class ProjectAdapter extends AutoRVAdapter {
    private List<KeepInfo> data;
    private Map<Integer, KeepInfo> map = new LinkedHashMap<>();

    public ProjectAdapter(Context context, List<KeepInfo> list) {
        super(context, list);
        this.data = list;
        this.data = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.iem_project;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        TextView textView = vh.getTextView(R.id.text_check);
        KeepInfo info = data.get(position);
        if (info.isTraveled()) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_check_order, 0);
        }else{
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.icon_pitch_nor, 0);
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.isTraveled()){
                    textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.icon_pitch_nor, 0);
                    map.remove(position);
                    info.setTraveled(false);
                }else{
                    info.setTraveled(true);
                    textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_check_order, 0);
                    map.put(position, info);
                }
                }

        });
    }

}
