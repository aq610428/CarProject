package com.car.notver.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.car.notver.R;
import com.car.notver.adapter.DepositoryAdapter1;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.KeepInfo;
import com.car.notver.bean.UserInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.util.Constants;
import com.car.notver.util.JsonParse;
import com.car.notver.util.Md5Util;
import com.car.notver.util.SaveUtils;
import com.car.notver.util.ToastUtil;
import com.car.notver.util.Utility;
import com.car.notver.weight.ClearEditText;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/5/20
 * @name:客户管理
 */
public class DepositoryActivity1 extends BaseActivity implements OnRefreshListener, OnLoadMoreListener, NetWorkListener {
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView;
    private TextView title_text_tv, title_left_btn, title_right_btn, text_msg;
    private List<KeepInfo> keepInfos = new ArrayList<>();
    private DepositoryAdapter1 adapter;
    private String name;
    private UserInfo info;
    private int limit = 10;
    private int page = 1;
    private boolean isRefresh;
    private ClearEditText editText;
    private String cardNum;
    private LinearLayout ll_add;
    private TextView btn_code, text_search;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_depository2);
        info = SaveUtils.getSaveInfo();
        cardNum = getIntent().getStringExtra("name");
        BaseApplication.activityTaskManager.putActivity("DepositoryActivity", this);
    }

    @Override
    protected void initView() {
        text_search = getView(R.id.text_search);
        btn_code = getView(R.id.btn_code);
        text_msg = getView(R.id.text_msg);
        ll_add = getView(R.id.ll_add);
        editText = getView(R.id.et_search);
        title_right_btn = getView(R.id.title_right_btn);
        name = getIntent().getStringExtra("title");
        swipeToLoadLayout = getView(R.id.swipeToLoadLayout);
        recyclerView = getView(R.id.swipe_target);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        if (Utility.isEmpty(name)) {
            title_text_tv.setText("我的客户");
        } else {
            title_text_tv.setText(name + "");
        }

        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        if (!Utility.isEmpty(cardNum)) {
            editText.setText(cardNum);
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Utility.isEmpty(editText.getText().toString())) {
                    query();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        title_right_btn.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_add_store, 0, 0, 0);
        title_right_btn.setOnClickListener(this);
        btn_code.setOnClickListener(this);
        text_search.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!Utility.isEmpty(cardNum)) {
            editText.setText(cardNum);
            text_msg.setText("未搜索到客户信息");
            query1();
        } else {
            text_msg.setText("暂无客户信息");
            query();
        }
    }

    @Override
    public void onLoadMore() {
        isRefresh = true;
        page++;
        query();
    }

    @Override
    public void onRefresh() {
        isRefresh = false;
        page = 1;
        query();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.btn_code:
            case R.id.title_right_btn:
                startActivity(new Intent(this, AddClientActivity.class));
                break;
            case R.id.text_search:
                String name = editText.getText().toString();
                if (!Utility.isEmpty(name)) {
                    query1();
                }
                break;

        }
    }


    /*******查询
     * @param ********/
    public void query1() {
        String sign = "memberId=" + info.getId() + "&partnerid=" + Constants.PARTNERID + "&querys=" + editText.getText().toString() + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("querys", editText.getText().toString() + "");
        params.put("memberId", info.getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_CAR_SAVE, params, Api.GET_COINS_DAILY_BILL_ID, this);
    }


    /*******查询
     * @param ********/
    public void delete(String id) {
        String sign = "id=" + id + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("id", id + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_DELETE, params, Api.GET_DELETE_ID, this);
    }


    /*******查询
     * @param ********/
    public void query() {
        String sign = "memberId=" + info.getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("memberId", info.getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_USER_LIST, params, Api.GET_USER_LIST_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_USER_LIST_ID:
                        List<KeepInfo> infos = JsonParse.getKeepInfo(object);
                        if (infos != null && infos.size() > 0) {
                            setAdapter(infos);
                        } else {
                            if (page == 1 && !isRefresh) {
                                ll_add.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            }
                        }
                        break;
                    case Api.GET_COINS_DAILY_BILL_ID:
                        List<KeepInfo> keepInfo = JsonParse.getKeepInfo(object);
                        if (keepInfo != null && keepInfo.size() > 0) {
                            setAdapter(keepInfo);
                        } else {
                            ll_add.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                        break;
                    case Api.GET_DELETE_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        query();
                        break;
                }
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }

    private void setAdapter(List<KeepInfo> voList) {
        ll_add.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        if (!isRefresh) {
            keepInfos.clear();
            keepInfos.addAll(voList);
            adapter = new DepositoryAdapter1(this, keepInfos);
            recyclerView.setAdapter(adapter);
        } else {
            keepInfos.addAll(voList);
            adapter.setData(keepInfos);
        }

        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                if (!Utility.isEmpty(name) && !"客户管理".equals(name)) {
                    intent = new Intent(DepositoryActivity1.this, OrderAutonomyActivity.class);
                    intent.putExtra("project", name);
                } else {
                    intent = new Intent(DepositoryActivity1.this, ClientDeilActivity.class);
                }
                intent.putExtra("keep", keepInfos.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onFail() {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }

    public void showDelete(String id) {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout1, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        ((TextView) view.findViewById(R.id.title)).setText("温馨提示");
        ((TextView) view.findViewById(R.id.message)).setText("确定是否删除?");
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(id);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
