package com.car.notver.adapter;

import android.content.Context;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ImageView;

import com.car.notver.R;
import com.car.notver.bean.ClientInfo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/5/19
 * @name:ClientAdapter
 */
public class RepairAdapter extends AutoRVAdapter {
    private List<ClientInfo> data;
    private Map<Integer, ClientInfo> map = new LinkedHashMap<>();


    public RepairAdapter(Context context, List<ClientInfo> list) {
        super(context, list);
        this.data = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_repair;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        ClientInfo clientInfo = data.get(position);
        vh.getTextView(R.id.text_name).setText(clientInfo.getName());
        vh.getTextView(R.id.text_code).setText("编码:" + clientInfo.getPhone());
        vh.getTextView(R.id.text_num).setText("可用次数:" + clientInfo.getLicense());
        ImageView imageView = vh.getImageView(R.id.iv_check);
        if (clientInfo.isMake()) {
            imageView.setImageResource(R.mipmap.ic_check_order);
        } else {
            imageView.setImageResource(R.mipmap.icon_pitch_nor);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clientInfo.isMake()){
                    map.remove(position);
                    clientInfo.setMake(false);
                    imageView.setImageResource(R.mipmap.icon_pitch_nor);
                }else{
                    clientInfo.setMake(true);
                    imageView.setImageResource(R.mipmap.ic_check_order);
                    map.put(position, clientInfo);
                }
            }
        });
    }

}
