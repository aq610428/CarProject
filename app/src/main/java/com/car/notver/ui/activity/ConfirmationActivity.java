package com.car.notver.ui.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.bigkoo.pickerview.TimePickerView;
import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.util.DateUtils;
import com.car.notver.weight.FinishActivityManager;

import java.util.Calendar;

/**
 * @author: zt
 * @date: 2020/5/15
 * @name:确认信息
 */
public class ConfirmationActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn, text_business;
    private Calendar startDate, endDate;
    private TimePickerView pvTime, pvTime1;
    private TextView tv_begin, tv_end, et_transport,btn_confirm,btn_cancel;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_confirmation);
        BaseApplication.activityTaskManager.putActivity("ConfirmationActivity",this);
    }

    @Override
    protected void initView() {
        btn_cancel= getView(R.id.btn_cancel);
        btn_confirm= getView(R.id.btn_confirm);
        et_transport = getView(R.id.et_transport);
        tv_end = getView(R.id.tv_end);
        tv_begin = getView(R.id.tv_begin);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        text_business = getView(R.id.text_business);
        title_left_btn.setOnClickListener(this);
        text_business.setOnClickListener(this);
        tv_end.setOnClickListener(this);
        tv_begin.setOnClickListener(this);
        et_transport.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        title_text_tv.setText("确认信息");
    }

    @Override
    protected void initData() {
        Calendar selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);
        endDate = Calendar.getInstance();
        endDate.set(2033, 11, 28);
        pvTime = new TimePickerView.Builder(this, (date, v) -> {//选中事件回调
            text_business.setText(DateUtils.getTimes(date) + "");
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{false, false, false, true, true, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.BLUE)
                .setContentSize(18)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();

        pvTime1 = new TimePickerView.Builder(this, (date, v) -> {//选中事件回调
            if (isDivider == 1) {
                tv_begin.setText(DateUtils.getTimes(date) + "");
            } else {
                tv_end.setText(DateUtils.getTimes(date) + "");
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.BLUE)
                .setContentSize(18)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();
    }


    private int isDivider;

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_confirm:

                break;
            case R.id.btn_cancel:

                break;

            case R.id.et_transport:
                showIndustry();
                break;

            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_business:
                pvTime.show();
                break;
            case R.id.tv_end:
                isDivider = 2;
                pvTime1.show();
                break;
            case R.id.tv_begin:
                isDivider = 1;
                pvTime1.show();
                break;
        }
    }


    /********行業资质*****/
    public void showIndustry() {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_industry, null);
        TextView text_lab1=getView(view,R.id.text_lab1);
        TextView text_lab2=getView(view,R.id.text_lab2);
        TextView text_lab3=getView(view,R.id.text_lab3);
        TextView text_lab4=getView(view,R.id.text_lab4);
        text_lab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_transport.setText(text_lab1.getText().toString().trim());
                dialog.dismiss();
            }
        });
        text_lab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_transport.setText(text_lab2.getText().toString().trim());
                dialog.dismiss();
            }
        });
        text_lab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_transport.setText(text_lab3.getText().toString().trim());
                dialog.dismiss();
            }
        });
        text_lab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_transport.setText(text_lab4.getText().toString().trim());
                dialog.dismiss();
            }
        });
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.show();
    }
}
