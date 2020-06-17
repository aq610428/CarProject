package com.car.notver.ui.activity;

import android.os.Bundle;
import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.bean.KeepInfo;

/**
 * @author: zt
 * @date: 2020/6/17
 * @name:添加车辆
 */
public class VehicleActivity extends BaseActivity {
    private KeepInfo keepInfo;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_vehicle);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        keepInfo = (KeepInfo) getIntent().getSerializableExtra("keep");
        if (keepInfo != null) {

        }
    }
}
