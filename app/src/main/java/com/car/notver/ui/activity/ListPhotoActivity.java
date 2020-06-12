package com.car.notver.ui.activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.car.notver.R;
import com.car.notver.base.BaseActivity1;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.FileInfo;
import com.car.notver.weight.MyViewPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/29
 * @name:ListPhotoActivity
 */
public class ListPhotoActivity extends BaseActivity1 {
    private ViewPager viewPager;
    private MyViewPagerAdapter pagerAdapter;
    private List<FileInfo> infoList = new ArrayList<>();
    private TextView title_text_tv;
    private int position;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_list_photo);
        BaseApplication.activityTaskManager.putActivity("ListPhotoActivity", this);
    }

    @Override
    protected void initView() {
        title_text_tv = findViewById(R.id.title_text_tv);
        viewPager = findViewById(R.id.viewpager);
        infoList = (List<FileInfo>) getIntent().getSerializableExtra("infoList");
        if (infoList != null && infoList.size() > 0) {
            pagerAdapter = new MyViewPagerAdapter(this, infoList);
            viewPager.setAdapter(pagerAdapter);
            position=getIntent().getIntExtra("position",0);
            viewPager.setCurrentItem(position);
            title_text_tv.setText(infoList.get(position).getTitle());
        }
    }


    @Override
    protected void initData() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                title_text_tv.setText(infoList.get(i).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
