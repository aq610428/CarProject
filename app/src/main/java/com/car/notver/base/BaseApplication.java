package com.car.notver.base;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import androidx.multidex.MultiDex;

import com.car.notver.util.CardUtils;
import com.car.notver.weight.ActivityTaskManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.https.HttpsUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

/**
 * Created by will wu on 2016/10/8.
 */
public class BaseApplication extends Application {
    public static Context myContext;
    public static BaseApplication baseApplicition;
    public static ActivityTaskManager activityTaskManager;

    @Override
    public void onCreate() {
        super.onCreate();
        myContext = getApplicationContext();
        activityTaskManager = ActivityTaskManager.getInstance();//初始化Activity管理器
        initOkHttpClient();
        registerActivityLifecycleCallbacks(new ActivityLifecycleListener());
        CardUtils.init();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }


    /****网络框架初始化*******/
    public static void initOkHttpClient() {
        OkGo.getInstance().init(BaseApplication.getInstance());
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //全局的读取超时时间
        builder.readTimeout(30000, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(30000, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(30000, TimeUnit.MILLISECONDS);
        builder.retryOnConnectionFailure(false);
        //方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        OkGo.getInstance().init(BaseApplication.getInstance())                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(24 * 60 * 60)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3); //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0

//                .addCommonHeaders(headers);
    }


    /**
     * 返回全局context对象cx
     */
    public static Context getContext() {
        return myContext;
    }

    public static BaseApplication getInstance() {
        if (baseApplicition == null) {
            baseApplicition = new BaseApplication();
        }
        return baseApplicition;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}