package com.car.notver;

import android.Manifest;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import androidx.fragment.app.FragmentTabHost;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.UserInfo;
import com.car.notver.bean.Verison;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.ui.fragment.CarFragment;
import com.car.notver.ui.fragment.CustomerFragment;
import com.car.notver.ui.fragment.MallFragment;
import com.car.notver.ui.fragment.MeFragment;
import com.car.notver.util.Constants;
import com.car.notver.util.JsonParse;
import com.car.notver.util.Md5Util;
import com.car.notver.util.SaveUtils;
import com.car.notver.util.SystemTools;
import com.car.notver.util.Utility;
import com.car.notver.weight.UpdateManager;
import org.json.JSONObject;
import java.util.Map;
import cn.jpush.android.api.JPushInterface;

/*****
 *
 *  惠养车
 *
 */
public class MainActivity extends BaseActivity implements NetWorkListener {
    private Class fragments[] = {CustomerFragment.class, CarFragment.class, MallFragment.class, MeFragment.class};
    private int drawables[] = {R.drawable.shelf_drawable, R.drawable.chosen_drawable1, R.drawable.chosen_drawable, R.drawable.me_drawable};
    private String textviewArray[] = {"首页", "资产", "工单", "我的"};
    public FragmentTabHost mTabHost;
    private Verison verison;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        BaseApplication.activityTaskManager.putActivity("MainActivity", this);
    }


    @Override
    protected void initView() {
        mTabHost = getView(R.id.mTabHost);
        queryPush();
        query();
    }


    @Override
    protected void initData() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        for (int i = 0; i < fragments.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(textviewArray[i]).setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, fragments[i], null);
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.colorCCC);
        }
        TabHost.OnTabChangeListener l = tabId -> {
            try {
                if (tabId.equals(textviewArray[0])) {
                    clearViewColor();
                    TextView tv_home = mTabHost.getTabWidget().getChildAt(0).findViewById(R.id.textview);
                    tv_home.setTextColor(Color.parseColor("#3E80F3"));
                }
                if (tabId.equals(textviewArray[1])) {
                    clearViewColor();
                    TextView tv_order = mTabHost.getTabWidget().getChildAt(1).findViewById(R.id.textview);
                    tv_order.setTextColor(Color.parseColor("#3E80F3"));
                }

                if (tabId.equals(textviewArray[2])) {
                    clearViewColor();
                    TextView tv_mine = mTabHost.getTabWidget().getChildAt(2).findViewById(R.id.textview);
                    tv_mine.setTextColor(Color.parseColor("#3E80F3"));
                }

                if (tabId.equals(textviewArray[3])) {
                    clearViewColor();
                    TextView tv_mine = mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.textview);
                    tv_mine.setTextColor(Color.parseColor("#3E80F3"));
                }

            } catch (Exception e) {
            }
        };
        setCurrentTab(0);
        mTabHost.setOnTabChangedListener(l);
        mTabHost.getTabWidget().setDividerDrawable(null);
    }


    /******
     * 清除所有的颜色
     */
    public void clearViewColor() {
        TextView tv_home = mTabHost.getTabWidget().getChildAt(0).findViewById(R.id.textview);
        tv_home.setTextColor(Color.parseColor("#666666"));
        TextView tv_order = mTabHost.getTabWidget().getChildAt(1).findViewById(R.id.textview);
        tv_order.setTextColor(Color.parseColor("#666666"));
        TextView tv_mine2 = mTabHost.getTabWidget().getChildAt(2).findViewById(R.id.textview);
        tv_mine2.setTextColor(Color.parseColor("#666666"));

        TextView tv_mine3 = mTabHost.getTabWidget().getChildAt(3).findViewById(R.id.textview);
        tv_mine3.setTextColor(Color.parseColor("#666666"));
    }

    /*****检测是否具有读写权限******/
    public void applyPermission() {
        if (Build.VERSION.SDK_INT >= 19) {
            if (checkPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})) {
                UpdateVerison();
            } else {
                requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
            }
        } else {
            UpdateVerison();
        }
    }

    /*****检测是否具有安装未知来源的权限******/
    public void UpdateVerison() {
        new UpdateManager(this).checkForceUpdate(verison);
    }

    public void permissinSucceed(int code) {
        switch (code) {
            case 3:
                UpdateVerison();
                break;
        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item_view, null);
        ImageView imageView = view.findViewById(R.id.imageview);
        imageView.setImageResource(drawables[index]);
        TextView textView = view.findViewById(R.id.textview);
        textView.setText(textviewArray[index]);
        return view;
    }


    public void setCurrentTab(int index) {
        mTabHost.setCurrentTab(index);
        clearViewColor();
        TextView tv_order = mTabHost.getTabWidget().getChildAt(index).findViewById(R.id.textview);
        tv_order.setTextColor(Color.parseColor("#3E80F3"));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            showDialog();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        showDialog();
    }


    public void showDialog() {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout1, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        ((TextView) view.findViewById(R.id.title)).setText("温馨提示");
        ((TextView) view.findViewById(R.id.message)).setText("确定退出惠保养?");
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnEventExit();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    /**
     * 退出应用
     */
    public void OnEventExit() {
        try {
            BaseApplication.activityTaskManager.closeAllActivity();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
            moveTaskToBack(false);
        } catch (Exception e) {
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }


    /*******查询首页数据
     * @param ********/
    public void query() {
        String sign = "partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_INTERGRAL_VERSION, params, Api.GET_INTERGRAL_VERSION_ID, this);
    }


    /*******绑定Jpush
     * @param ********/
    public void queryPush() {
        String registrationID = JPushInterface.getRegistrationID(this);
        String sign = "memberid=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + "&registerid=" + registrationID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("memberid",  SaveUtils.getSaveInfo().getId() + "");
        params.put("registerid", registrationID + "");
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_PUSH_VERSION, params, Api.GET_PUSH_VERSION_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_INTERGRAL_VERSION_ID:
                        verison = JsonParse.getVerisonUserInfo(object);
                        if (verison != null) {
                            int code = SystemTools.getAppVersionCode(this);
                            if (!Utility.isEmpty(verison.getVersionIndex())) {
                                int versionCode = Integer.parseInt(verison.getVersionIndex());
                                if (versionCode > code) {
                                    applyPermission();
                                }
                            }

                        }
                        break;
                }
            }
        }
        stopProgressDialog();
    }

    @Override
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
    }
}

