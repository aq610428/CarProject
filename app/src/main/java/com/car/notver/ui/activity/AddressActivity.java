package com.car.notver.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.car.notver.R;
import com.car.notver.adapter.OrderListAdapter;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.ClientVo;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.StoreInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.util.Constants;
import com.car.notver.util.JsonParse;
import com.car.notver.util.Md5Util;
import com.car.notver.util.SaveUtils;
import com.car.notver.util.ToastUtil;
import com.car.notver.util.Utility;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/5/27
 * @name:新增项目
 */
public class AddressActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_mode;
    private ClientVo clientVo;
    private EditText et_name, et_phone, et_discern, et_remarks;
    private Button btn_next;
    private OrderListAdapter adapter;
    private List<StoreInfo> infos = new ArrayList<>();
    private String storeId;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_address);
        BaseApplication.activityTaskManager.putActivity("AddressActivity", this);
    }

    @Override
    protected void initView() {
        btn_next = getView(R.id.btn_next);
        text_mode = getView(R.id.text_mode);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        et_name = getView(R.id.et_name);
        et_phone = getView(R.id.et_phone);
        et_discern = getView(R.id.et_discern);
        et_remarks = getView(R.id.et_remarks);
        text_mode.setOnClickListener(this);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("新增项目");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_mode:
                if (infos != null && infos.size() > 1) {
                    showDialog();
                }
                break;
        }
    }


    @Override
    protected void initData() {
        clientVo = (ClientVo) getIntent().getSerializableExtra("clientVo");
        if (clientVo != null) {
            title_text_tv.setText("项目修改");
            btn_next.setText("确认修改");
            text_mode.setText(clientVo.getStoreName() + "");
            et_name.setText(clientVo.getUsername());
            et_phone.setText("￥" + clientVo.getTotalmileage());
            et_discern.setText(clientVo.getInitmileage() + "积分");
        }
        qury();
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
                    case Api.GET_COINS_DAILY_BILL_ID:
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
