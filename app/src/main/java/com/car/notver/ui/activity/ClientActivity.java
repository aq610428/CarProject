package com.car.notver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.car.notver.R;
import com.car.notver.adapter.ClientNewAdapter;
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
import com.car.notver.util.Utility;
import com.car.notver.weight.FinishActivityManager;
import com.car.notver.weight.NoDataView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/6/1
 * @name:门店绑定车辆
 */
public class ClientActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener, NetWorkListener {
    private TextView title_text_tv, title_left_btn;
    private ListView swipe_target;
    private SwipeToLoadLayout swipeToLoadLayout;
    private List<KeepInfo> infos = new ArrayList<>();
    private ClientNewAdapter keepAdapter;
    private UserInfo info;
    private int limit = 10;
    private int page = 1;
    private boolean isRefresh;
    private NoDataView mNoDataView;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_client);
        BaseApplication.activityTaskManager.putActivity("ClientActivity", this);
        info = SaveUtils.getSaveInfo();
        if (info != null) {
            query();
        }
    }

    @Override
    protected void initView() {
        mNoDataView = getView(R.id.mNoDataView);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        swipe_target = getView(R.id.swipe_target);
        swipeToLoadLayout = getView(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("我绑定的车辆");
        mNoDataView.textView.setText("门店暂无绑定车辆");
    }

    @Override
    protected void initData() {


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
        }
    }

    /*******查询首页数据
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
        okHttpModel.get(Api.GET_USER_CAR, params, Api.GET_USER_CAR_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_USER_CAR_ID:
                        List<KeepInfo> infos = JsonParse.getKeepInfo(object);
                        if (infos != null && infos.size() > 0) {
                            setAdapter(infos);
                        } else {
                            if (page == 1 && !isRefresh) {
                                mNoDataView.setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                }
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }


    private void setAdapter(List<KeepInfo> voList) {
        mNoDataView.setVisibility(View.GONE);
        if (!isRefresh) {
            infos.clear();
            infos.addAll(voList);
            keepAdapter = new ClientNewAdapter(this, infos);
            swipe_target.setAdapter(keepAdapter);
        } else {
            infos.addAll(voList);
            keepAdapter.setData(infos);
        }
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
}
