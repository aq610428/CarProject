package com.car.notver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.bean.ClientVo;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.KeepInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
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
    private TextView title_text_tv, title_left_btn, text_mode;
    private EditText et_license, et_frame, et_engine, et_oss, et_oss_total, et_discern, et_remark;
    private Button btn_next;
    private ClientVo clientVo;
    private KeepInfo keepInfo;

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

    String memberid, storeid;

    @Override
    protected void initData() {
        clientVo = (ClientVo) getIntent().getSerializableExtra("clientVo");
        keepInfo = (KeepInfo) getIntent().getSerializableExtra("keep");
        if (clientVo != null) {
            et_license.setText(clientVo.getCarcard() + "");
            et_frame.setText(clientVo.getVinno() + "");
            et_engine.setText(clientVo.getEngineno() + "");
            et_oss.setText(clientVo.getInitmileage() + "");
            et_oss_total.setText(clientVo.getTotalmileage() + "");
            text_mode.setText(clientVo.getYearmodel() + "");
            et_remark.setText(clientVo.getRemark() + "");
            business = clientVo.getBusiness();
            factory = clientVo.getFactory();
            model = clientVo.getModel();
            yearmodel = clientVo.getYearmodel();
            btn_next.setText("确认修改");
            memberid = clientVo.getMemberid();
            storeid = clientVo.getStoreid();
        } else {
            if (keepInfo != null) {
                memberid = keepInfo.getMemberId();
                storeid = keepInfo.getStoreid();
            }
        }
    }


    private void qury() {
        String carcard = et_license.getText().toString();
        String vinno = et_frame.getText().toString();
        String engineno = et_engine.getText().toString();
        String initmileage = et_oss.getText().toString();
        String totalmileage = et_oss_total.getText().toString();
        String factory = text_mode.getText().toString();
        String remark = et_remark.getText().toString();
        if (Utility.isEmpty(carcard)) {
            ToastUtil.showToast("请输入车牌号码");
            return;
        }
        String sign = "";
        if (!Utility.isEmpty(business)) {
            sign = "business=" + business;
        }
        if (Utility.isEmpty(sign)) {
            sign = "carcard=" + carcard;
        } else {
            sign = sign + "&carcard=" + carcard;
        }
        if (!Utility.isEmpty(engineno)) {
            sign = sign + "&engineno=" + engineno;
        }
        if (!Utility.isEmpty(factory)) {
            sign = sign + "&factory=" + factory;
        }
        if (clientVo != null) {
            sign = sign + "&id=" + clientVo.getId();
        }

        if (!Utility.isEmpty(initmileage)) {
            sign = sign + "&initmileage=" + initmileage;
        }
        sign = sign + "&memberid=" + memberid;
        if (!Utility.isEmpty(model)) {
            sign = sign + "&model=" + model;
        }
        sign = sign + "&partnerid=" + Constants.PARTNERID;
        if (!Utility.isEmpty(remark)) {
            sign = sign + "&remark=" + remark;
        }
        sign = sign + "&storeid=" + storeid + "&storeMemberId=" + SaveUtils.getSaveInfo().getId();

        if (!Utility.isEmpty(totalmileage)) {
            sign = sign + "&totalmileage=" + totalmileage;
        }
        if (!Utility.isEmpty(vinno)) {
            sign = sign + "&vinno=" + vinno;
        }

        if (!Utility.isEmpty(yearmodel)) {
            sign = sign + "&yearmodel=" + yearmodel;
        }

        sign = sign + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        if (!Utility.isEmpty(business)) {
            params.put("business", business);
        }
        params.put("carcard", carcard);
        if (!Utility.isEmpty(engineno)) {
            params.put("engineno", engineno);
        }
        if (!Utility.isEmpty(factory)) {
            params.put("factory", factory);
        }
        if (clientVo != null) {
            params.put("id", clientVo.getId());
        }
        if (!Utility.isEmpty(initmileage)) {
            params.put("initmileage", initmileage);
        }

        params.put("memberid", memberid);
        if (!Utility.isEmpty(model)) {
            params.put("model", model);
        }

        params.put("partnerid", Constants.PARTNERID);
        if (!Utility.isEmpty(remark)) {
            params.put("remark", remark);
        }
        params.put("storeid",storeid+ "");
        params.put("storeMemberId", SaveUtils.getSaveInfo().getId());
        if (!Utility.isEmpty(totalmileage)) {
            params.put("totalmileage", totalmileage);
        }

        if (!Utility.isEmpty(vinno)) {
            params.put("vinno", vinno);
        }
        if (!Utility.isEmpty(yearmodel)) {
            params.put("yearmodel", yearmodel);
        }

        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_MODEL_LISR, params, Api.GET_MODEL_LISR_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_MODEL_LISR_ID:
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
            case R.id.text_mode:
                Intent intent = new Intent(this, BrandActivity.class);
                startActivityForResult(intent, 100);
                break;

        }
    }

    private String business, factory, model, yearmodel;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null) {
            business = data.getStringExtra("business");
            factory = data.getStringExtra("factory");
            model = data.getStringExtra("model");
            yearmodel = data.getStringExtra("yearmodel");
            text_mode.setText(factory + model + yearmodel);
        }
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
