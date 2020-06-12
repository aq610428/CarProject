package com.car.notver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.car.notver.R;
import com.car.notver.adapter.RepairAdapter;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.ClientInfo;
import com.car.notver.weight.FinishActivityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/21
 * @name:客户详情
 */
public class RepairActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn, text_repair, text_project,text_commodity;
    private RecyclerView recyclerView;
    private List<ClientInfo> infos = new ArrayList<>();
    private RepairAdapter repairAdapter;
    private RelativeLayout rl_repair;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_repair);
        BaseApplication.activityTaskManager.putActivity("RepairActivity",this);
    }

    @Override
    protected void initView() {
        text_commodity = getView(R.id.text_commodity);
        text_project = getView(R.id.text_project);
        rl_repair = getView(R.id.rl_repair);
        text_repair = getView(R.id.text_repair);
        recyclerView = getView(R.id.recyclerView);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        rl_repair.setOnClickListener(this);
        text_project.setOnClickListener(this);
        text_commodity.setOnClickListener(this);
        title_text_tv.setText("客户详情");
    }

    @Override
    protected void initData() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        infos.add(new ClientInfo("洗车", "B326963", "9"));
        infos.add(new ClientInfo("洗车", "B326963", "9"));
        infos.add(new ClientInfo("洗车", "B326963", "9"));
        infos.add(new ClientInfo("洗车", "B326963", "9"));
        repairAdapter = new RepairAdapter(this, infos);
        recyclerView.setAdapter(repairAdapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_commodity:
                startActivity(new Intent(this, CommodityListActivity.class));
                break;
            case R.id.rl_repair:
                startActivity(new Intent(this, InformationActivity.class));
                break;
            case R.id.text_project:
                startActivity(new Intent(this, ProjectActivity.class));
                break;
        }
    }
}
