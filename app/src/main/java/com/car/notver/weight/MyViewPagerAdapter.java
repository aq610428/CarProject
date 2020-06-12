package com.car.notver.weight;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;

import com.car.notver.bean.FileInfo;
import com.car.notver.glide.GlideUtils;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/29
 * @name:MyViewPagerAdapter
 */
public class MyViewPagerAdapter extends PagerAdapter {
    private List<FileInfo> infoList;
    private Context context;
    public MyViewPagerAdapter(Context context,List<FileInfo> infoList){
        this.infoList = infoList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //返回要显示的条目内容
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(context);
        photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        GlideUtils.setImageUrl(infoList.get(position).getPhotoFile(),photoView);
        container.addView(photoView);
        return photoView;
    }

    //销毁条目
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
