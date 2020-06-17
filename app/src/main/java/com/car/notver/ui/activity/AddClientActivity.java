package com.car.notver.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
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
import com.car.notver.ocr.lljjcoder.Interface.OnCityItemClickListener;
import com.car.notver.ocr.lljjcoder.bean.CityBean;
import com.car.notver.ocr.lljjcoder.bean.DistrictBean;
import com.car.notver.ocr.lljjcoder.bean.ProvinceBean;
import com.car.notver.ocr.lljjcoder.citywheel.CityConfig;
import com.car.notver.ocr.lljjcoder.style.citypickerview.CityPickerView;
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
    private TextView title_text_tv, title_left_btn, title_right_btn, text_mode, text_license, text_city;
    private EditText et_name, et_phone, et_discern, et_oss;
    private UserInfo info;
    private List<StoreInfo> infos = new ArrayList<>();
    private CityPickerView mCityPickerView = new CityPickerView();
    private Button btn_next;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addclient);
        BaseApplication.activityTaskManager.putActivity("AddClientActivity", this);
        mCityPickerView.init(this);
    }

    @Override
    protected void initView() {
        btn_next = getView(R.id.btn_next);
        text_city = getView(R.id.text_city);
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
        text_city.setOnClickListener(this);
        btn_next.setOnClickListener(this);
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
                pvTime.show();
                break;
            case R.id.text_city:
                showAddress();
                break;
            case R.id.btn_next:
                qury();
                break;
        }
    }


    /*******门店列表
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
        String username = et_name.getText().toString();
        String mobile = et_phone.getText().toString();
        String birthday = text_license.getText().toString();
        String city = text_city.getText().toString();
        String address = et_oss.getText().toString();
        String remark = et_discern.getText().toString();
        String mode = text_mode.getText().toString();
        String sex="1";

        if (!Utility.isEmpty(username)) {
            ToastUtil.showToast("客户姓名不能为空");
            return;
        } else if (!Utility.isEmpty(mobile)) {
            ToastUtil.showToast("手机号码不能为空");
            return;
        } else if (!Utility.isEmpty(mode)) {
            ToastUtil.showToast("门店不能为空");
            return;
        }
        String sign = null;
        if (!Utility.isEmpty(area)) {
            sign = "area=" + area;
        }
        if (!Utility.isEmpty(address)) {
            sign = sign + "&address=" + address;
        }
        if (!Utility.isEmpty(birthday)) {
            sign = sign + "&birthday=" + birthday;
        }

        if (!Utility.isEmpty(city)) {
            sign = sign + "&city=" + city;
        }
        sign = sign + "&mobile=" + mobile+"&partnerid="+ Constants.PARTNERID ;

        if (!Utility.isEmpty(province)) {
            sign = sign + "&province=" + province;
        }


        if (!Utility.isEmpty(remark)) {
            sign = sign + "&remark=" + remark;
        }

        if (!Utility.isEmpty(sex)) {
            sign = sign + "&sex=" + sex;
        }

        if (!Utility.isEmpty(sex)) {
            sign = sign + "&sex=" + sex;
        }
        sign = sign + "&storeid=" + storeId+"&storeMemberId="+info.getId()+"&username="+username+Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("area", area);
        params.put("address", address);
        params.put("birthday", birthday);
        params.put("city", city);
        params.put("mobile", mobile);
        params.put("province", province);
        params.put("partnerid", Constants.PARTNERID);
        params.put("remark", remark);
        params.put("sex", "1");

        params.put("storeid", storeId);
        params.put("storeMemberId", info.getId());
        params.put("username", username);
        params.put("apptype", Constants.TYPE);

        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_USER_VERSION, params, Api.GET_USER_VERSION_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_USER_VERSION_ID:
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


    private String province, city, area = "";

    private void showAddress() {
        CityConfig cityConfig = new CityConfig.Builder()
                .title("选择城市")
                .visibleItemsCount(5)
                .province("广东省")
                .city("深圳市")
                .district("南山区")
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)
                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.item_city_name_tv)
                .setShowGAT(true)
                .build();
        mCityPickerView.setConfig(cityConfig);
        mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean provinceBean, CityBean cityBean, DistrictBean districtBean) {
                province = provinceBean.getName();
                city = cityBean.getName();
                area = districtBean.getName();
                text_city.setText(province + city + area + "");
            }

            @Override
            public void onCancel() {
                ToastUtil.showToast("已取消");
            }
        });
        mCityPickerView.showCityPicker();
    }


    private TimePickerView pvTime;
    private Calendar startDate, endDate;

    protected void initDataTime() {
        Calendar selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);
        endDate = Calendar.getInstance();
        endDate.set(2033, 11, 28);
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
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
