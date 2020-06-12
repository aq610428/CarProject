package com.car.notver.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.car.notver.base.BaseApplication;
import com.google.android.material.snackbar.Snackbar;


/**
 * Toast工具类
 *
 * @author 续写经典
 * @version 1.0 2015/11/3
 */
public final class ToastUtil {
    private ToastUtil() {
    }

    public static Toast toast;

    public static Toast getInstance() {
        if (toast == null) {
            toast = new Toast(BaseApplication.getContext());
        }
        return toast;
    }


    public static void showToast(String text) {
        getInstance().makeText(BaseApplication.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void showSnackbar(View view,String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }



    public static void showToasts(String msg) {
        Toast toast = Toast.makeText(BaseApplication.getContext(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    public static void showToast(Context context, int resId) {
        getInstance().makeText(context, context.getResources().getText(resId),
                Toast.LENGTH_SHORT).show();
    }


    public static void showLongToast(String text) {
        getInstance().makeText(BaseApplication.getContext(), text, Toast.LENGTH_LONG).show();
    }


    public static void showLongToast(Context context, int resId) {
        getInstance().makeText(context, context.getResources().getText(resId),
                Toast.LENGTH_LONG).show();
    }

    public static void cancelToast() {
        Toast toast = getInstance();
        toast.cancel();
        toast = null;
    }

}
