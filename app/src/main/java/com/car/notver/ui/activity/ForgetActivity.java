package com.car.notver.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.util.Constants;
import com.car.notver.util.Md5Util;
import com.car.notver.util.SystemTools;
import com.car.notver.util.ToastUtil;
import com.car.notver.util.Utility;
import com.car.notver.weight.FinishActivityManager;

import org.json.JSONObject;

import java.util.Map;

/**
 * @author: zt
 * @date: 2020/5/20
 * @name:注册
 */
public class ForgetActivity extends BaseActivity implements NetWorkListener {
    private EditText username, et_code, e_password,e_password_again;
    private Button btn_next;
    private TextView btn_code;
    private TextView title_text_tv, title_left_btn;
    private View view1,view2,view3,view4;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_forget);
        BaseApplication.activityTaskManager.putActivity("ForgetActivity", this);
    }

    @Override
    protected void initView() {
        e_password_again= getView(R.id.e_password_again);
        view1= getView(R.id.view1);
        view2= getView(R.id.view2);
        view3= getView(R.id.view3);
        view4= getView(R.id.view4);
        btn_code = getView(R.id.btn_code);
        btn_next = getView(R.id.btn_next);
        username = getView(R.id.username);
        et_code = getView(R.id.et_code);
        e_password = getView(R.id.e_password);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        btn_code.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        title_left_btn.setOnClickListener(this);
        String name=getIntent().getStringExtra("name");
        if (!Utility.isEmpty(name)){
            title_text_tv.setText(name+"");
        }else{
            title_text_tv.setText("忘记密码");
        }
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Utility.isEmpty(username.getText().toString())){
                    view1.setBackgroundColor(Color.parseColor("#EEEEEE"));
                }else{
                    view1.setBackgroundColor(Color.parseColor("#3F80F4"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        e_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Utility.isEmpty(e_password.getText().toString())){
                    view2.setBackgroundColor(Color.parseColor("#EEEEEE"));
                }else{
                    view2.setBackgroundColor(Color.parseColor("#3F80F4"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        e_password_again.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Utility.isEmpty(e_password_again.getText().toString())){
                    view3.setBackgroundColor(Color.parseColor("#EEEEEE"));
                }else{
                    view3.setBackgroundColor(Color.parseColor("#3F80F4"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Utility.isEmpty(et_code.getText().toString())){
                    view4.setBackgroundColor(Color.parseColor("#EEEEEE"));
                }else{
                    view4.setBackgroundColor(Color.parseColor("#3F80F4"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_code:
                checkPhone();
                break;
            case R.id.btn_next:
                query();
                break;
            case R.id.title_left_btn:
                finish();
                break;
        }
    }

    private void checkPhone() {
        String phone = username.getText().toString().trim();
        if (Utility.isEmpty(phone)) {
            ToastUtil.showToast("手机号不能为空");
        } else if (!SystemTools.isMobileNumber(phone)) {
            ToastUtil.showToast("手机号码不合法，请重新输入");
        } else {
            queryCode();
        }
    }

    /**
     * 倒计时显示
     */
    CountDownTimer timer;

    private void countDown() {
        timer = new CountDownTimer(90000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btn_code.setEnabled(false);
                btn_code.setText("已发送(" + millisUntilFinished / 1000 + ")");
            }

            @Override
            public void onFinish() {
                btn_code.setEnabled(true);
                btn_code.setText("重新获取验证码");

            }
        }.start();

    }

    public void queryCode() {
        String mobile = username.getText().toString();
        String sign = "mobile=" + mobile + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("mobile", mobile);
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_MOBILCODE, params, Api.GET_MOBILCODE_ID, this);
    }


    public void query() {
        String mobile = username.getText().toString();
        String password = e_password.getText().toString();
        String code = et_code.getText().toString();
        if (Utility.isEmpty(mobile)) {
            ToastUtil.showToast("手机号不能为空");
        } else if (!SystemTools.isMobileNumber(mobile)) {
            ToastUtil.showToast("手机号码不合法，请重新输入");
        } else if (Utility.isEmpty(mobile)) {
            ToastUtil.showToast("验证码不能为空");
        } else if (Utility.isEmpty(code)) {
            ToastUtil.showToast("验证码不能为空");
        } else if (Utility.isEmpty(password)) {
            ToastUtil.showToast("密码不能为空");
        } else {
            String sign = "loginname=" + mobile + "&partnerid=" + Constants.PARTNERID + "&password=" + Md5Util.encode(password) + "&vcode=" + code + Constants.SECREKEY;
            showProgressDialog(this, false);
            Map<String, String> params = okHttpModel.getParams();
            params.put("loginname", mobile);
            params.put("apptype", Constants.TYPE);
            params.put("partnerid", Constants.PARTNERID);
            params.put("sign", Md5Util.encode(sign));
            params.put("vcode", code);
            params.put("password", Md5Util.encode(password));
            okHttpModel.get(Api.GET_UPDATE, params, Api.GET_UPDATE_ID, this);
        }
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_MOBILCODE_ID:
                        ToastUtil.showToast("验证码已发送");
                        countDown();
                        break;
                    case Api.GET_UPDATE_ID:
                        ToastUtil.showToast("修改成功");
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }

    }
}
