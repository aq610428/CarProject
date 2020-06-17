package com.car.notver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.UserInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
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
 * @name:品牌型号
 */
public class BrandActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn;
    private UserInfo info;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_brand);
    }

    @Override
    protected void initView() {
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_text_tv.setText("品牌型号");
        title_left_btn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        info = SaveUtils.getSaveInfo();
        query();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
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
