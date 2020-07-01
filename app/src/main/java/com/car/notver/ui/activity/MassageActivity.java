package com.car.notver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.car.notver.R;
import com.car.notver.adapter.MsgAdapter;
import com.car.notver.base.BaseActivity;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.Massage;
import com.car.notver.bean.UserInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.util.Constants;
import com.car.notver.util.JsonParse;
import com.car.notver.util.Md5Util;
import com.car.notver.util.SaveUtils;
import com.car.notver.util.Utility;
import com.car.notver.weight.NoDataView;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/6/4
 * @name:消息中心
 */
public class MassageActivity extends BaseActivity implements OnLoadMoreListener, OnRefreshListener, NetWorkListener {
    private RecyclerView swipe_target;
    private int limit = 10;
    private int page = 1;
    private boolean isRefresh;
    private SwipeToLoadLayout swipeToLoadLayout;
    private UserInfo info;
    private NoDataView mNoDataView;
    private List<Massage> list = new ArrayList<>();
    private MsgAdapter adapter;
    private TextView title_text_tv, title_left_btn;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_msg);
    }

    @Override
    protected void initView() {
        mNoDataView = getView(R.id.mNoDataView);
        swipe_target = getView(R.id.swipe_target);
        swipeToLoadLayout = getView(R.id.swipeToLoadLayout);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("消息中心");
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        mNoDataView.textView.setText("暂无消息");

    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        swipe_target.setLayoutManager(layoutManager);
        info = SaveUtils.getSaveInfo();
        if (info != null) {
            query();
        }
    }


    public void query() {
        String sign = "memberid=" + info.getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberid", info.getId());
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_MSG_VERSION, params, Api.GET_MSG_VERSION_ID, this);
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
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_MSG_VERSION_ID:
                        List<Massage> infos = JsonParse.getMassageJson(object);
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
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }


    private void setAdapter(List<Massage> voList) {
        swipe_target.setVisibility(View.GONE);
        mNoDataView.setVisibility(View.GONE);
        if (!isRefresh) {
            list.clear();
            list.addAll(voList);
            adapter = new MsgAdapter(this, list);
            swipe_target.setAdapter(adapter);
        } else {
            list.addAll(voList);
            adapter.setData(list);
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


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
        }
    }
}
