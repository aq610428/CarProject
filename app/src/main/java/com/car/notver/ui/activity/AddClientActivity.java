package com.car.notver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.UserInfo;
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
 * @date: 2020/5/19
 * @name:添加客户
 */
public class AddClientActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, title_right_btn, text_mode;
    private EditText et_license, et_name, et_phone, et_discern, et_oss;
    private UserInfo info;

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
        et_license = getView(R.id.et_license);
        title_right_btn = getView(R.id.title_right_btn);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_text_tv.setText("添加客户");
        title_left_btn.setOnClickListener(this);
        title_right_btn.setOnClickListener(this);
        VehicleKeyboardHelper1.bind(et_license, this);
        text_mode.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        info = SaveUtils.getSaveInfo();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_mode:

                break;
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


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_CAR_SAVE_ID:
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
}
