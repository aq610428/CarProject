package com.car.notver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;

/**
 * @author: zt
 * @date: 2020/5/27
 * @name:帮助中心
 */
public class HelpActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn;
    private RelativeLayout rl_route,rl_subtract,rl_trajectory,rl_path;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_help);
        BaseApplication.activityTaskManager.putActivity("HelpActivity",this);
    }

    @Override
    protected void initView() {
        rl_path= getView(R.id.rl_path);
        rl_trajectory= getView(R.id.rl_trajectory);
        rl_subtract= getView(R.id.rl_subtract);
        rl_route= getView(R.id.rl_route);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        rl_route.setOnClickListener(this);
        rl_path.setOnClickListener(this);
        rl_trajectory.setOnClickListener(this);
        rl_subtract.setOnClickListener(this);
        title_text_tv.setText("帮助中心");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.rl_route:
                startActivity(new Intent(this,CoreActivity.class));
                break;
            case R.id.rl_subtract:
                startActivity(new Intent(this,BasicActivity.class));
                break;
            case R.id.rl_trajectory:
                startActivity(new Intent(this,BasicTypeActivity.class));
                break;
            case R.id.rl_path:
                startActivity(new Intent(this,BasicOtherActivity.class));
                break;

        }
    }

    @Override
    protected void initData() {

    }
}
