package com.car.notver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.car.notver.R;
import com.car.notver.adapter.CommodityAdapter;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.CommodityInfo;
import com.car.notver.weight.FinishActivityManager;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: zt
 * @date: 2020/5/21
 * @name:商品管理
 */
public class CommodityActivity extends BaseActivity implements OnLoadMoreListener, OnRefreshListener {
    private TextView title_text_tv, title_left_btn;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView;
    private CommodityAdapter adapter;
    private List<CommodityInfo> infos = new ArrayList<>();

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_cmmodity);
        BaseApplication.activityTaskManager.putActivity("CommodityActivity",this);
    }

    @Override
    protected void initView() {
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        swipeToLoadLayout = getView(R.id.swipeToLoadLayout);
        recyclerView = getView(R.id.swipe_target);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("商品管理");
    }

    @Override
    protected void initData() {
        infos.add(new CommodityInfo("机油", "36", "A20718000109", "108"));
        infos.add(new CommodityInfo("三滤", "36", "A20718000109",  "101"));
        infos.add(new CommodityInfo("刹车片", "36", "A20718000109", "18"));
        infos.add(new CommodityInfo("汽油格", "36", "A20718000109",  "28"));
        infos.add(new CommodityInfo("发动机皮条","36",  "A20718000109",  "48"));
        infos.add(new CommodityInfo("紧张轮", "36", "A20718000109",  "58"));
        infos.add(new CommodityInfo("正时皮带", "36", "A20718000109",  "8"));
        infos.add(new CommodityInfo("电瓶", "36", "A20718000109",  "98"));
        infos.add(new CommodityInfo("火花塞", "36", "A20718000109",  "38"));
        infos.add(new CommodityInfo("气门杆", "36", "A20718000109",  "48"));
        adapter = new CommodityAdapter(this, infos);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(CommodityActivity.this,CommodityDeilActivity.class));
            }
        });
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setLoadingMore(false);
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
            }
        }, 2000);
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
