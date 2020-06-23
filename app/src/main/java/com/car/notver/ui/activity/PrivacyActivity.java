package com.car.notver.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.car.notver.R;
import com.car.notver.base.BaseActivity;

/**
 * @author: zt
 * @date: 2020/6/23
 * @name:PrivacyActivity
 */
public class PrivacyActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_privacy);
    }

    @Override
    protected void initView() {
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("用户隐私");
    }

    @Override
    protected void initData() {

    }
}
