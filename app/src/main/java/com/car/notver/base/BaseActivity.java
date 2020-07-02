/**
 * 项目名称: CoreLibrary
 * 文件名称: BaseActivity.java
 * 创建时间: 2014/3/28
 * Copyright: 2014 www.fantasee.cn Inc. All rights reserved.
 */
package com.car.notver.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.car.notver.R;
import com.car.notver.util.LogUtils;
import com.car.notver.weight.BaseProgressDialog;
import java.util.ArrayList;
import java.util.List;
import crossoverone.statuslib.StatusUtil;


/**
 * 所有页面activity的抽象基类D
 *
 * @author zt </br> Create at 2018年2月19日 下午5:31:35
 * @version 1.0
 */
public abstract class BaseActivity extends AppCompatActivity implements OnClickListener {
    private BaseProgressDialog mProgressDialog = null;
    public String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCreate(savedInstanceState);
        //基类设置默认值,这里是非沉浸,状态栏颜色值#878787,字体颜色为黑色。
        setStatusColor();
        setSystemInvadeBlack();
        initView();
        initData();
    }

    protected void setStatusColor() {
        StatusUtil.setUseStatusBarColor(this, Color.parseColor("#FFFFFF"));
    }

    protected void setSystemInvadeBlack() {
        // 第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtil.setSystemStatus(this, false, true);
    }


    protected abstract void initCreate(Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public void showProgressDialog(Activity activity, BaseProgressDialog.OnCancelListener cancelListener, boolean cancelable, String msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            return;
        }
        mProgressDialog = new BaseProgressDialog(activity, msg);
        if (cancelListener != null) {
            mProgressDialog.setOnCancelListener(cancelListener);
        }
        mProgressDialog.setCancelable(cancelable);
        mProgressDialog.show();
    }

    public void showProgressDialog(Activity activity, boolean cancelable, String msg) {
        showProgressDialog(activity, null, cancelable, msg);
    }

    public void showProgressDialog(Activity activity, boolean cancelable) {
        showProgressDialog(activity, cancelable, "");
    }


    public void stopProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.stop();
        }
        mProgressDialog = null;
    }


    /***
     * 控件获取
     * @param resId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int resId) {
        return (T) this.findViewById(resId);
    }

    /***
     * 控件获取
     * @param resId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(View view, int resId) {
        return (T) view.findViewById(resId);
    }


    public static int REQUEST_CODE = 0;

    public void requestPermission(String[] permissions, int requestCode) {
        REQUEST_CODE = requestCode;
        if (checkPermissions(permissions)) {
            permissinSucceed(REQUEST_CODE);
        } else {
            List<String> needPermissions = getPermissions(permissions);
            ActivityCompat.requestPermissions(this, needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE);
        }
    }

    public List<String> getPermissions(String[] permissions) {
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED || ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                permissionList.add(permission);
            }
        }
        return permissionList;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (verificationPermissions(grantResults)) {
                permissinSucceed(REQUEST_CODE);
            } else {
                permissionFailing(REQUEST_CODE);
            }
        }
    }

    public boolean verificationPermissions(int[] results) {
        for (int result : results) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    /**
     * 检测所有的权限是否都已授权
     *
     * @param permissions
     * @return
     */
    public boolean checkPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(BaseActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    public void showFaiingDialog() {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout_pp, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        ((TextView) view.findViewById(R.id.message)).setText(msg);
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSettings();
                dialog.dismiss();
            }
        });
        dialog.show();
    }




    public void startSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    public void permissionFailing(int code) {
        LogUtils.e("获取权限失败");
    }

    public void permissinSucceed(int code) {

    }

}
