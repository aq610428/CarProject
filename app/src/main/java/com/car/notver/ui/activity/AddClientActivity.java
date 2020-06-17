package com.car.notver.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.TimePickerView;
import com.car.notver.R;
import com.car.notver.adapter.OrderListAdapter;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.StoreInfo;
import com.car.notver.bean.UserInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.util.Constants;
import com.car.notver.util.DateUtils;
import com.car.notver.util.JsonParse;
import com.car.notver.util.Md5Util;
import com.car.notver.util.SaveUtils;
import com.car.notver.util.ToastUtil;
import com.car.notver.util.Utility;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/5/19
 * @name:添加客户
 */
public class AddClientActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, title_right_btn, text_mode, text_license;
    private EditText et_name, et_phone, et_discern, et_oss;
    private UserInfo info;
    private List<StoreInfo> infos = new ArrayList<>();

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addclient);
        BaseApplication.activityTaskManager.putActivity("AddClientActivity", this);
    }

    @Override
    protected void initView() {
        et_oss = getView(R.id.et_oss);
        et_name = getView(R.id.et_name);
        et_phone = getView(R.id.et_phone);
        et_discern = getView(R.id.et_discern);
        text_mode = getView(R.id.text_mode);
        text_license = getView(R.id.text_license);
        title_right_btn = getView(R.id.title_right_btn);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_text_tv.setText("添加客户");
        title_left_btn.setOnClickListener(this);
        title_right_btn.setOnClickListener(this);
        text_mode.setOnClickListener(this);
        text_license.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        info = SaveUtils.getSaveInfo();
        initDataTime();
        qury();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = null;
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_mode:
               showDialog();
                break;
            case R.id.text_license:
                pvTime1.show();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

        }
    }

    /*******查询
     * @param ********/
    public void query() {
        String sign = "memberId=" + info.getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberId", info.getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_CAR_SAVE, params, Api.GET_CAR_SAVE_ID, this);
    }

    private void qury() {
        String sign = "memberId=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberId", SaveUtils.getSaveInfo().getId() + "");
        params.put("limit", "10");
        params.put("page", "1");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_INFO, params, Api.GET_INFOO_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_CAR_SAVE_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        finish();
                        break;
                    case Api.GET_INFOO_ID:
                        infos = JsonParse.getStoreJson(object);
                        if (infos != null && infos.size() > 0) {
                            if (infos.size() == 1) {
                                storeId = infos.get(0).getId();
                                text_mode.setText(infos.get(0).getName());
                            }
                        }
                        break;

                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    private Calendar startDate, endDate;
    private TimePickerView pvTime1;
    protected void initDataTime() {
        Calendar selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);
        endDate = Calendar.getInstance();
        endDate.set(2033, 11, 28);
        pvTime1 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                text_license.setText(DateUtils.getTimes(date) + "");
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


    @Override
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
    }

    OrderListAdapter adapter;
    String storeId;

    public void showDialog() {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout_list, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new OrderListAdapter(this, infos);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                storeId = infos.get(position).getId();
                text_mode.setText(infos.get(position).getName());
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
