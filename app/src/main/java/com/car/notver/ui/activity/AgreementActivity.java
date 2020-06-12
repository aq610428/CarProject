package com.car.notver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.weight.FinishActivityManager;

/**
 * @author: zt
 * @date: 2020/6/4
 * @name:AgreementActivity
 */
public class AgreementActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn,text_doc;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_agreement);
        BaseApplication.activityTaskManager.putActivity("AgreementActivity",this);
    }

    @Override
    protected void initView() {
        text_doc = getView(R.id.text_doc);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("用户协议");
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
