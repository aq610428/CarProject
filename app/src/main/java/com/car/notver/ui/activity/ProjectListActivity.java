package com.car.notver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.car.notver.R;
import com.car.notver.adapter.LeftAdapter;
import com.car.notver.adapter.ProjectAdapter;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.PhotoInfo;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: zt
 * @date: 2020/5/21
 * @name:商品管理
 */
public class ProjectListActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn;
    private RecyclerView rv_left, rv_right;
    private ProjectAdapter adapter;
    private LeftAdapter leftAdapter;
    private List<PhotoInfo> infoList = new ArrayList<>();
    private List<PhotoInfo> infos = new ArrayList<>();

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_projec);
        BaseApplication.activityTaskManager.putActivity("CommodityActivity", this);
    }

    @Override
    protected void initView() {
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        rv_left = getView(R.id.rv_left);
        rv_right = getView(R.id.rv_right);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("商品管理");

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv_right.setLayoutManager(manager);


        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        rv_left.setLayoutManager(manager1);
    }

    @Override
    protected void initData() {
        infoList.add(new PhotoInfo("机油"));
        infoList.add(new PhotoInfo("三滤"));
        infoList.add(new PhotoInfo("刹车片"));
        infoList.add(new PhotoInfo("汽油格"));
        infoList.add(new PhotoInfo("发动机皮条"));
        infoList.add(new PhotoInfo("紧张轮"));
        infoList.add(new PhotoInfo("正时皮带"));
        infoList.add(new PhotoInfo("电瓶"));
        infoList.add(new PhotoInfo("火花塞"));
        infoList.add(new PhotoInfo("气门杆"));
        infoList.add(new PhotoInfo("发动机皮带"));
        infoList.add(new PhotoInfo("火花塞"));
        infoList.add(new PhotoInfo("水箱"));


        infos.add(new PhotoInfo("机油", "36", "A20718000109", "108"));
        infos.add(new PhotoInfo("三滤", "36", "A20718000109", "101"));
        infos.add(new PhotoInfo("刹车片", "36", "A20718000109", "18"));
        infos.add(new PhotoInfo("汽油格", "36", "A20718000109", "28"));
        infos.add(new PhotoInfo("发动机皮条", "36", "A20718000109", "48"));
        infos.add(new PhotoInfo("紧张轮", "36", "A20718000109", "58"));
        infos.add(new PhotoInfo("正时皮带", "36", "A20718000109", "8"));
        infos.add(new PhotoInfo("电瓶", "36", "A20718000109", "98"));
        infos.add(new PhotoInfo("火花塞", "36", "A20718000109", "38"));
        infos.add(new PhotoInfo("气门杆", "36", "A20718000109", "48"));
        adapter = new ProjectAdapter(this, infos);
        rv_right.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ProjectListActivity.this, CommodityDeilActivity.class));
            }
        });

        leftAdapter = new LeftAdapter(this, infoList);
        rv_left.setAdapter(leftAdapter);
        leftAdapter.setSelectedItem(0);
        leftAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                leftAdapter.setSelectedItem(position);
                leftAdapter.notifyDataSetChanged();
            }
        });

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
