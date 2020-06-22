package com.car.notver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.car.notver.R;
import com.car.notver.adapter.ProjectAdapter;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.KeepInfo;
import com.car.notver.weight.FinishActivityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/22
 * @name:选择项目
 */
public class ProjectActivity extends BaseActivity {
    private RecyclerView swipe_target;
    private List<KeepInfo> keepInfos = new ArrayList<>();
    private ProjectAdapter projectAdapter;
    private TextView title_text_tv, title_left_btn;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_project);
        BaseApplication.activityTaskManager.putActivity("ProjectActivity",this);
    }

    @Override
    protected void initView() {
        swipe_target = getView(R.id.swipe_target);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);

        title_text_tv.setText("选择项目");
    }

    @Override
    protected void initData() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        swipe_target.setLayoutManager(manager);

        keepInfos.add(new KeepInfo("米其林轮胎", "GHLT", "366"));
        keepInfos.add(new KeepInfo("米其林轮胎", "GHLT", "366"));
        keepInfos.add(new KeepInfo("米其林轮胎", "GHLT", "366"));
        keepInfos.add(new KeepInfo("米其林轮胎", "GHLT", "366"));
        keepInfos.add(new KeepInfo("米其林轮胎", "GHLT", "366"));
        keepInfos.add(new KeepInfo("米其林轮胎", "GHLT", "366"));
        keepInfos.add(new KeepInfo("米其林轮胎", "GHLT", "366"));
        keepInfos.add(new KeepInfo("米其林轮胎", "GHLT", "366"));
        keepInfos.add(new KeepInfo("米其林轮胎", "GHLT", "366"));
        keepInfos.add(new KeepInfo("米其林轮胎", "GHLT", "366"));
        keepInfos.add(new KeepInfo("米其林轮胎", "GHLT", "366"));
        keepInfos.add(new KeepInfo("米其林轮胎", "GHLT", "366"));
//        projectAdapter = new ProjectAdapter(this, keepInfos);
//        swipe_target.setAdapter(projectAdapter);

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
