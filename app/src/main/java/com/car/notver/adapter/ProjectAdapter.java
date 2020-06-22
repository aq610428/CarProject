package com.car.notver.adapter;

import android.content.Context;
import android.view.View;
import com.car.notver.R;
import com.car.notver.bean.PhotoInfo;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/5/21
 * @name:CommodityAdapter
 */
public class ProjectAdapter extends AutoRVAdapter {
    public List<PhotoInfo> infos;
    private Map<Integer, PhotoInfo> map;

    public ProjectAdapter(Context context, List<PhotoInfo> list) {
        super(context, list);
        this.infos = list;
        map = new LinkedHashMap<>();
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_right;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        PhotoInfo info = infos.get(position);
        vh.getTextView(R.id.text_name).setText(info.getTitle());
        vh.getTextView(R.id.text_Mechanical).setText("型号：" + info.getId());
        vh.getTextView(R.id.text_stock).setText("库存：" + info.getExplain());
        vh.getTextView(R.id.text_price).setText("价格￥" + info.getPic());

        if (info.isCherk()) {
            vh.getImageView(R.id.iv_select).setImageResource(R.mipmap.icon_pitch);
        } else {
            vh.getImageView(R.id.iv_select).setImageResource(R.mipmap.icon_pitch_nor);
        }

        if (position==infos.size()-1){
            vh.getTextView(R.id.text_empty).setVisibility(View.VISIBLE);
        }else{
            vh.getTextView(R.id.text_empty).setVisibility(View.GONE);
        }

        vh.getImageView(R.id.iv_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.isCherk()) {
                    info.setCherk(false);
                    map.remove(position);
                    vh.getImageView(R.id.iv_select).setImageResource(R.mipmap.icon_pitch_nor);
                } else {
                    info.setCherk(true);
                    map.put(position, info);
                    vh.getImageView(R.id.iv_select).setImageResource(R.mipmap.icon_pitch);
                }
            }
        });
    }
}
