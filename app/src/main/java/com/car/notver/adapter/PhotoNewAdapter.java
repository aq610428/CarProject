package com.car.notver.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.car.notver.R;
import com.car.notver.bean.FileInfo;
import com.car.notver.glide.GlideUtils;
import com.car.notver.ui.activity.PhotoActivity;
import com.car.notver.util.MeasureWidthUtils;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/29
 * @name:PhotoNewAdapter
 */
public class PhotoNewAdapter extends AutoRVAdapter {
    private List<FileInfo> infos;
    private PhotoActivity activity;

    public PhotoNewAdapter(PhotoActivity activity, List<FileInfo> list) {
        super(activity, list);
        this.infos = list;
        this.activity = activity;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_pic;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        ImageView imageView = vh.getImageView(R.id.iv_photo);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        int width= MeasureWidthUtils.getScreenWidth(activity);
        layoutParams.width = (width-60)/2;
        layoutParams.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        imageView.setLayoutParams(layoutParams);
        GlideUtils.CreateImageRound(infos.get(position).getPhotoFile(), imageView,8);
        vh.getTextView(R.id.text_jcompany).setText(infos.get(position).getTitle());
        vh.getImageView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.quryDel(infos.get(position).getId());
            }
        });
    }
}
