package com.car.notver.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bigkoo.pickerview.TimePickerView;
import com.car.notver.R;
import com.car.notver.base.BaseActivity1;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.StoreInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.glide.GlideUtils;
import com.car.notver.util.Constants;
import com.car.notver.util.DateUtils;
import com.car.notver.util.Md5Util;
import com.car.notver.util.SaveUtils;
import com.car.notver.util.ToastUtil;
import com.car.notver.util.Utility;
import org.json.JSONObject;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/5/28
 * @name:店铺详情
 */
public class StoreDeilActivity extends BaseActivity1 implements NetWorkListener {
    private StoreInfo info;
    private TextView title_text_tv, title_left_btn;
    private EditText text_name, text_contacts, text_phone, text_adress, text_brand, text_business, text_rescue;
    private ImageView lv_logo;
    private TextView text_album, text_natural, text_start, text_end;
    private int type = 0;
    private Calendar startDate, endDate;
    private Button btn_next;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_storedeil);
        BaseApplication.activityTaskManager.putActivity("StoreDeilActivity", this);
    }

    @Override
    protected void initView() {
        btn_next = getView(R.id.btn_next);
        text_end = getView(R.id.text_end);
        text_start = getView(R.id.text_start);
        text_album = getView(R.id.text_album);
        text_natural = getView(R.id.text_natural);
        lv_logo = getView(R.id.lv_logo);
        text_name = getView(R.id.text_name);
        text_contacts = getView(R.id.text_contacts);
        text_phone = getView(R.id.text_phone);
        text_adress = getView(R.id.text_adress);
        text_brand = getView(R.id.text_brand);
        text_business = getView(R.id.text_business);
        text_rescue = getView(R.id.text_rescue);

        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_album.setOnClickListener(this);
        text_natural.setOnClickListener(this);
        title_text_tv.setText("店铺详情");
        text_start.setOnClickListener(this);
        text_end.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        info = (StoreInfo) getIntent().getSerializableExtra("info");
        if (info != null) {
            text_name.setText(info.getName());
            text_contacts.setText(info.getContactPerson());
            text_phone.setText(info.getPhone());
            text_adress.setText(info.getAddress());
            text_brand.setText(info.getBrandName());
            text_business.setText(info.getBusinessScope());
            text_rescue.setText(info.getRescuePhone());
            GlideUtils.setImageUrl(info.getLogo(), lv_logo);

            if (!Utility.isEmpty(info.getOperTime())) {
                String[] open = info.getOperTime().split("-");
                if (open != null && open.length > 0) {
                    text_start.setText(open[0] + "");
                    text_end.setText(open[1] + "");
                }
            }
        }

        Calendar selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);
        endDate = Calendar.getInstance();
        endDate.set(2033, 11, 28);
        pvTime1 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (type == 1) {
                    text_start.setText(DateUtils.getTime1(date) + "");
                } else {
                    text_end.setText(DateUtils.getTime1(date) + "");
                }
            }
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
    }


    private void checkData() {
        String name = text_name.getText().toString();
        String contacts = text_contacts.getText().toString();
        String phone = text_phone.getText().toString();
        String start = text_start.getText().toString();
        String end = text_end.getText().toString();
        String address = text_adress.getText().toString();

        String brand = text_brand.getText().toString();
        String work = text_business.getText().toString();
        String mobile = text_rescue.getText().toString();

        if (Utility.isEmpty(name)) {
            ToastUtil.showToast("企业名称不能为空");
        } else if (Utility.isEmpty(contacts)) {
            ToastUtil.showToast("联系人不能为空");
        } else if (Utility.isEmpty(phone)) {
            ToastUtil.showToast("联系电话不能为空");
        } else if (Utility.isEmpty(start)) {
            ToastUtil.showToast("营业时间不能为空");
        } else if (Utility.isEmpty(end)) {
            ToastUtil.showToast("营业时间不能为空");
        } else if (Utility.isEmpty(address)) {
            ToastUtil.showToast("地址不能为空");
        } else if (Utility.isEmpty(brand)) {
            ToastUtil.showToast("主营品牌不能为空");
        } else if (Utility.isEmpty(work)) {
            ToastUtil.showToast("主营业务不能为空");
        } else {
            String sign=null;
             sign = "address=" + address + "&area=" + info.getArea() + "&brandName=" + brand + "&businessScope=" + work + "&city=" + info.getCity() + "&contactPerson=" + contacts
                    + "&id=" + info.getId() + "&lat=" + info.getLat() + "&lng=" + info.getLng() + "&logo=" + info.getLogo() + "&memberId=" + SaveUtils.getSaveInfo().getId() + "&name=" + name + "&operTime=" + start + "-" + end
                    + "&partnerid=" + Constants.PARTNERID + "&phone=" + phone + "&province=" + info.getProvince() ;
             if (!Utility.isEmpty(mobile)){
                 sign=sign+ "&rescuePhone=" + mobile;
             }
            sign=sign+ Constants.SECREKEY;;
            showProgressDialog(this, false);
            Map<String, String> params = okHttpModel.getParams();
            params.put("apptype", Constants.TYPE);
            params.put("area", info.getArea() + "");
            params.put("address", address + "");
            params.put("brandName", brand);
            params.put("businessScope", work);
            params.put("city", info.getCity() + "");
            params.put("contactPerson", contacts);
            params.put("id", info.getId() + "");
            params.put("lat", info.getLat() + "");
            params.put("lng", info.getLng() + "");
            params.put("logo", info.getLogo() + "");
            params.put("memberId", SaveUtils.getSaveInfo().getId() + "");
            params.put("name", name);
            params.put("operTime", start + "-" + end);
            params.put("partnerid", Constants.PARTNERID);
            params.put("phone", phone);
            params.put("province", info.getProvince() + "");
            if (!Utility.isEmpty(mobile)){
                params.put("rescuePhone", mobile);
            }
            params.put("sign", Md5Util.encode(sign));
            okHttpModel.get(Api.GET_STOREINFO, params, Api.GET_STOREINFO_ID, this);
        }
    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_STOREINFO_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        finish();
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
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

    private TimePickerView pvTime1;

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_next:
                checkData();
                break;
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_start:
                type = 1;
                pvTime1.show();
                break;
            case R.id.text_end:
                type = 2;
                pvTime1.show();
                break;
            case R.id.text_album:
                intent = new Intent(StoreDeilActivity.this, PhotoActivity.class);
                intent.putExtra("info", info);
                startActivity(intent);
                break;
            case R.id.text_natural:
                intent = new Intent(StoreDeilActivity.this, LegalActivity.class);
                intent.putExtra("info", info);
                startActivity(intent);
                break;
        }
    }
}
