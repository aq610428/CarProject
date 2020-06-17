package com.car.notver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;

/**
 * @author: zt
 * @date: 2020/5/19
 * @name:添加客户
 */
public class AddClientActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn, title_right_btn;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addclient);
        BaseApplication.activityTaskManager.putActivity("AddClientActivity",this);
    }

    @Override
    protected void initView() {
        title_right_btn = getView(R.id.title_right_btn);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_right_btn.setText("提交");
        title_text_tv.setText("添加客户");
        title_left_btn.setOnClickListener(this);
        title_right_btn.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }



    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
        }
    }


}
