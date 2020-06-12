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
 * @date: 2020/5/22
 * @name:洗车开单
 */
public class WashActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private List<ClientInfo> infos = new ArrayList<>();
    private RepairAdapter repairAdapter;
    private TextView title_text_tv, title_left_btn, text_project;
    private RelativeLayout rl_repair;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_wash);
        BaseApplication.activityTaskManager.putActivity("WashActivity",this);
    }

    @Override
    protected void initView() {
        rl_repair = getView(R.id.rl_repair);
        recyclerView = getView(R.id.recyclerView);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        text_project = getView(R.id.text_project);
        title_left_btn.setOnClickListener(this);
        rl_repair.setOnClickListener(this);
        text_project.setOnClickListener(this);
        title_text_tv.setText("洗车开单");
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
            case R.id.rl_repair:
                startActivity(new Intent(this, InformationActivity.class));
                break;
            case R.id.text_project:
                startActivity(new Intent(this, ProjectActivity.class));
                break;
        }
    }
}
