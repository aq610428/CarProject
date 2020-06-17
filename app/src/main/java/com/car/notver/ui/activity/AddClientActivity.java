package com.car.notver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.ocr.VehicleKeyboardHelper1;

/**
 * @author: zt
 * @date: 2020/5/19
 * @name:添加客户
 */
public class AddClientActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn, title_right_btn;
    private EditText et_license,et_name,et_phone,et_discern,et_model,et_oss;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addclient);
        BaseApplication.activityTaskManager.putActivity("AddClientActivity",this);
    }

    @Override
    protected void initView() {
        et_oss= getView(R.id.et_oss);
        et_name= getView(R.id.et_name);
        et_phone= getView(R.id.et_phone);
        et_discern= getView(R.id.et_discern);
        et_model= getView(R.id.et_model);
        et_license = getView(R.id.et_license);
        title_right_btn = getView(R.id.title_right_btn);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_text_tv.setText("添加客户");
        title_left_btn.setOnClickListener(this);
        title_right_btn.setOnClickListener(this);
        VehicleKeyboardHelper1.bind(et_license, this);
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
