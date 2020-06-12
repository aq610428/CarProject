package com.car.notver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.car.notver.MainActivity;
import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.UserInfo;
import com.car.notver.util.SaveUtils;
import com.car.notver.weight.FinishActivityManager;

/***
 *
 * 启动页面
 *
 *
 */
public class StartActivity extends BaseActivity {
    private UserInfo info;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_start);
        BaseApplication.activityTaskManager.putActivity("StartActivity",this);
    }

    @Override
    protected void initView() {
        info = SaveUtils.getSaveInfo();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (info!=null){
                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                }else{
                    startActivity(new Intent(StartActivity.this, LoginActivity.class));
                }

                finish();
            }
        }, 2000);
    }

    @Override
    protected void initData() {

    }
}
