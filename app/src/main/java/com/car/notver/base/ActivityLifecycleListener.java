package com.car.notver.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;


/****
 * ActivityLifecycleCallbacks来 监听app 是否运行在后台
 *
 */
public class ActivityLifecycleListener implements Application.ActivityLifecycleCallbacks {
    private int refCount = 0;
    private static final int APP_STATUS_UNKNOWN = -1;
    private static final int APP_STATUS_LIVE = 0;
    private int appStatus = APP_STATUS_UNKNOWN;


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (appStatus == APP_STATUS_UNKNOWN) {
            // 设置状态标志 APP_STATUS_LIVE
            appStatus = APP_STATUS_LIVE;
            // 判断是否跳转闪屏页
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }


    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        refCount--;
        //表示在后台运行

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
