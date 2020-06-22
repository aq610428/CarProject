package com.car.notver.weight;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.car.notver.R;
import com.car.notver.bean.Verison;
import com.car.notver.util.BigDecimalUtils;
import com.car.notver.util.LogUtils;
import com.car.notver.util.SystemTools;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import java.io.File;
import java.math.BigDecimal;


/**
 * Created by zs on 2016/7/7.
 */
public class UpdateManager {
    Activity activity;

    public UpdateManager(Activity activity) {
        this.activity = activity;
    }

    /**
     * 显示更新对话框
     *
     * @param
     */
    public void showNoticeDialog(Verison verison) {
        Dialog dialog = new Dialog(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_generals_up, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.show();
        dialog.getWindow().setContentView(view);
        TextView text__center = view.findViewById(R.id.text__center);
        TextView text_cancel = view.findViewById(R.id.text_cancel);
        TextView text_version = view.findViewById(R.id.text_version);
        TextView dialog_content = view.findViewById(R.id.dialog_content);
        text_version.setText("最新版本：v" + verison.getVersionNo());
        dialog_content.setText(verison.getVersionContent());
        if ("1".equals(verison.getIsForceUpdate()+"")) {
            text_cancel.setVisibility(View.VISIBLE);
        } else {
            text_cancel.setVisibility(View.GONE);
        }

        text__center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
                loadFile(verison.getFilepath());
                dialog.dismiss();
            }
        });

        text_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    ProgressBar mProgressBar;
    private Dialog mDialog;

    public void showDialog() {
        mDialog = new Dialog(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_generals_down, null);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCancelable(false);
        mDialog.cancel();
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.show();
        mDialog.getWindow().setContentView(view);
        mProgressBar = view.findViewById(R.id.mProgressBar);
        mProgressBar.setMax(100);
    }

    public void checkForceUpdate(Verison verison) {
        showNoticeDialog(verison);
    }

    /**
     * 下载文件
     *
     * @param apkUrl
     */
    private void loadFile(String apkUrl) {
        OkGo.<File>get(apkUrl).tag(activity).execute(new FileCallback() {
            @Override
            public void onSuccess(Response<File> response) {
                if (response != null && response.body() != null) {
                    mDialog.dismiss();
                    installProcess(response.body());
                }
            }

            @Override
            public void onError(Response<File> response) {
                super.onError(response);

            }

            @Override
            public void downloadProgress(Progress progress) {
                super.downloadProgress(progress);
                if (progress != null) {
                    BigDecimal current = BigDecimalUtils.mul(BigDecimalUtils.div(new BigDecimal(progress.currentSize), new BigDecimal(progress.totalSize), 2), new BigDecimal(100));
                    mProgressBar.setProgress(current.intValue());
                    LogUtils.e(mProgressBar.getProgress());
                }
            }
        });
    }

    //安装应用的流程
    private void installProcess(File body) {
        boolean haveInstallPermission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//先获取是否有安装未知来源应用的权限
            haveInstallPermission = activity.getPackageManager().canRequestPackageInstalls();
            if (!haveInstallPermission) {
                DialogUtils.showDialog("安装应用需要打开未知来源权限，请去设置中开启权限", activity);
            }
        }
        //有权限，开始安装应用程序
        SystemTools.installApk(activity, body);
    }

}
