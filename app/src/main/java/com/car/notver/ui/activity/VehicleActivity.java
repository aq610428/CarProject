package com.car.notver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.KeepInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.ocr.VehicleKeyboardHelper;
import com.car.notver.ocr.VehicleKeyboardHelper1;
import com.car.notver.util.Constants;
import com.car.notver.util.Md5Util;
import com.car.notver.util.SaveUtils;
import com.car.notver.util.ToastUtil;
import com.car.notver.util.Utility;
import org.json.JSONObject;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/6/17
 * @name:添加车辆
 */
public class VehicleActivity extends BaseActivity implements NetWorkListener {
    private KeepInfo keepInfo;
    private TextView title_text_tv, title_left_btn, text_mode;
    private EditText et_license, et_frame, et_engine, et_oss, et_oss_total, et_discern, et_remark;
    private Button btn_next;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_vehicle);
    }

    @Override
    protected void initView() {
        btn_next = getView(R.id.btn_next);
        et_license = getView(R.id.et_license);
        et_frame = getView(R.id.et_frame);
        et_engine = getView(R.id.et_engine);
        et_oss = getView(R.id.et_oss);
        et_oss_total = getView(R.id.et_oss_total);
        et_discern = getView(R.id.et_discern);
        et_remark = getView(R.id.et_remark);
        text_mode = getView(R.id.text_mode);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("添加车辆");
        text_mode.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        VehicleKeyboardHelper1.bind(et_license, this);
    }

    @Override
    protected void initData() {
        keepInfo = (KeepInfo) getIntent().getSerializableExtra("keep");
        if (keepInfo != null) {

        }
    }


    private void qury() {
        String carcard = et_license.getText().toString();
        String vinno=et_frame.getText().toString();
        String engineno=et_engine.getText().toString();
        String initmileage=et_oss.getText().toString();
        String totalmileage=et_oss_total.getText().toString();
        String factory=text_mode.getText().toString();
        String remark=et_remark.getText().toString();
        String business="1";
        factory="2";
        String model="3";
        String yearmodel="4";


        if (Utility.isEmpty(carcard)) {
            ToastUtil.showToast("请输入车牌号码");
            return;
        }
        String sign = "partnerid=" + Constants.PARTNERID + "&storeId=" + SaveUtils.getSaveInfo().getId() + Constants.SECREKEY;




        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("business", business);
        params.put("carcard", carcard);
        params.put("engineno", engineno);
        params.put("factory", factory);
        params.put("memberid", keepInfo.getId());
        params.put("model", model);
        params.put("initmileage", initmileage);
        params.put("partnerid", Constants.PARTNERID);
        params.put("remark", remark);
        params.put("totalmileage", totalmileage);
        params.put("storeid", keepInfo.getStoreid() + "");
        params.put("storeMemberId", SaveUtils.getSaveInfo().getId());
        params.put("yearmodel", yearmodel);
        params.put("vinno", vinno);
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_IMAGE, params, Api.GET_IMAGE_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_IMAGEINFO_ID:
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
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.btn_next:
                qury();
                break;

        }
    }

    @Override
    public void onFail() {

    }

    @Override
    public void onError(Exception e) {

    }
}
