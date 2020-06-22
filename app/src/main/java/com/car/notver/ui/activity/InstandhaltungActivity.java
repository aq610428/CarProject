package com.car.notver.ui.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bigkoo.pickerview.TimePickerView;
import com.car.notver.R;
import com.car.notver.adapter.OrderListAdapter;
import com.car.notver.base.BaseActivity;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.StoreInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.util.Constants;
import com.car.notver.util.DateUtils;
import com.car.notver.util.JsonParse;
import com.car.notver.util.Md5Util;
import com.car.notver.util.SaveUtils;
import com.car.notver.util.Utility;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/6/22
 * @name:设备维修
 */
public class InstandhaltungActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_date, text_store;
    private TimePickerView pvTime1;
    private Calendar startDate, endDate;
    private List<StoreInfo> infos = new ArrayList<>();
    private Button btn_next;
    private String storeId;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_instandhaltung);
        qury();
    }

    @Override
    protected void initView() {
        btn_next = getView(R.id.btn_next);
        text_store = getView(R.id.text_store);
        text_date = getView(R.id.text_date);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("设备维修");
        text_store.setOnClickListener(this);
        text_date.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Calendar selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);
        endDate = Calendar.getInstance();
        endDate.set(2033, 11, 28);
        pvTime1 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                text_date.setText(DateUtils.getTyTimes(date) + "");
            }
        })
                .setType(new boolean[]{true, true, true, true, true, false})
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
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_store:
                showDialog();
                break;
            case R.id.text_date:
                pvTime1.show();
                break;
            case R.id.btn_next:

                break;
        }
    }

    /*******门店列表******/
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
                    case Api.GET_INFOO_ID:
                        infos = JsonParse.getStoreJson(object);
                        if (infos != null && infos.size() > 0) {
                            if (infos.size() == 1) {
                                storeId = infos.get(0).getId();
                                text_store.setText(infos.get(0).getName());
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


    public void showDialog() {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout_list, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        OrderListAdapter adapter = new OrderListAdapter(this, infos);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                storeId = infos.get(position).getId();
                text_store.setText(infos.get(position).getName());
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
