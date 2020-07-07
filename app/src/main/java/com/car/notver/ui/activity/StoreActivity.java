package com.car.notver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.car.notver.R;
import com.car.notver.adapter.StoreAdapter;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.StoreInfo;
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
import com.car.notver.weight.NoDataView;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author: zt
 * @date: 2020/5/28
 * @name:门店列表
 */
public class StoreActivity extends BaseActivity implements OnLoadMoreListener, NetWorkListener, OnRefreshListener {
    private TextView title_text_tv, title_left_btn, title_right_btn;
    private RecyclerView swipe_target;
    private SwipeToLoadLayout swipeToLoadLayout;
    private StoreAdapter adapter;
    private List<StoreInfo> infos = new ArrayList<>();
    private int limit = 10;
    private int page = 1;
    private UserInfo info;
    private boolean isRefresh;
    private NoDataView mNoDataView;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_store_list);
        BaseApplication.activityTaskManager.putActivity("StoreActivity", this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        info = SaveUtils.getSaveInfo();
        qury();
    }

    @Override
    protected void initView() {
        mNoDataView = getView(R.id.mNoDataView);
        title_right_btn = getView(R.id.title_right_btn);
        swipeToLoadLayout = getView(R.id.swipeToLoadLayout);
        swipe_target = getView(R.id.swipe_target);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        title_right_btn.setOnClickListener(this);
        title_text_tv.setText("门店列表");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        swipe_target.setLayoutManager(manager);
        mNoDataView.textView.setText("您还没有添加门店");
        title_right_btn.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_add_store,0,0,0);
    }

    @Override
    protected void initData() {


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_right_btn:
                check();
                break;
            case R.id.title_left_btn:
                finish();
                break;
        }
    }

    boolean isCheck;

    private void check() {
        isCheck = false;
        if (infos != null && infos.size() > 0) {
            for (int i = 0; i < infos.size(); i++) {
                if (infos.get(i).getStatus() == 1 || infos.get(i).getStatus() == 2) {
                    isCheck = true;
                    ToastUtil.showToast("“您的门店在审核中，暂时无法添加新的门店");
                    return;
                }
            }
        }
        if (!isCheck) {
            startActivity(new Intent(this, StoreListActivity.class));
        }
    }




    private void qury() {
        String sign = "memberId=" + info.getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberId", info.getId() + "");
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_INFO, params, Api.GET_INFOO_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_INFOO_ID:
                        List<StoreInfo> infos = JsonParse.getStoreJson(object);
                        if (infos != null && infos.size() > 0) {
                            setAdapter(infos);
                        } else {
                            if (page == 1 && !isRefresh) {
                                swipe_target.setVisibility(View.GONE);
                                mNoDataView.setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                }
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
    }

    private void setAdapter(List<StoreInfo> voList) {
        mNoDataView.setVisibility(View.GONE);
        swipe_target.setVisibility(View.VISIBLE);
        if (!isRefresh) {
            infos.clear();
            infos.addAll(voList);
            adapter = new StoreAdapter(this, infos);
            adapter.setHasStableIds(true);
            swipe_target.setAdapter(adapter);
        } else {
            infos.addAll(voList);
            adapter.setData(infos);
        }
        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StoreActivity.this, StoreDeilActivity.class);
                intent.putExtra("info", infos.get(position));
                startActivity(intent);
            }
        });
    }


    @Override
    public void onFail() {
        stopProgressDialog();
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onError(Exception e) {
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
        stopProgressDialog();
    }

    @Override
    public void onRefresh() {
        isRefresh = false;
        page=1;
        qury();
    }

    @Override
    public void onLoadMore() {
        isRefresh = true;
        page++;
        qury();
    }
}
