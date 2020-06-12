package com.car.notver.adapter;

import android.content.Context;
import com.car.notver.R;
import com.car.notver.bean.PhotoInfo;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/18
 * @name:PhotoActivity
 */
public class PhotoAdapter extends AutoRVAdapter {
    private List<PhotoInfo> data ;

    public PhotoAdapter(Context context, List<PhotoInfo> list) {
        super(context, list);
        this.data = list;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_photo;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        PhotoInfo photoInfo = data.get(position);
        vh.getTextView(R.id.text_jcompany).setText(photoInfo.getTitle());
        vh.getTextView(R.id.text_explain).setText(photoInfo.getExplain());
        if (photoInfo.getBitmap()!=null){
            vh.getImageView(R.id.iv_photo).setImageBitmap(photoInfo.getBitmap());
        }
    }
}
