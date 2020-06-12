package com.car.notver.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.TimePickerView;
import com.car.notver.R;
import com.car.notver.adapter.FrontAdapter;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.ClientInfo;
import com.car.notver.util.DateUtils;
import com.car.notver.weight.FinishActivityManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/21
 * @name:前置仓
 */
public class FrontActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn, text_goods,text_add;
    private Button btn_next;
    private Calendar startDate, endDate;
    private TimePickerView pvTime;
    private RecyclerView recyclerView;
    private FrontAdapter frontAdapter;
    private List<ClientInfo> infos = new ArrayList<>();


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_front);
        BaseApplication.activityTaskManager.putActivity("FrontActivity",this);
    }

    @Override
    protected void initView() {
        text_add = getView(R.id.text_add);
        recyclerView = getView(R.id.recyclerView);
        text_goods = getView(R.id.text_goods);
        btn_next = getView(R.id.btn_next);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_goods.setOnClickListener(this);
        text_add.setOnClickListener(this);
        title_text_tv.setText("前置仓");
        init();
    }

    @Override
    protected void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        infos.add(new ClientInfo("李总","18588246540","18903"));
        infos.add(new ClientInfo("李总","18588246540","18903"));
        infos.add(new ClientInfo("李总","18588246540","18903"));
        infos.add(new ClientInfo("李总","18588246540","18903"));
        infos.add(new ClientInfo("李总","18588246540","18903"));
        frontAdapter=new FrontAdapter(this,infos);
        recyclerView.setAdapter(frontAdapter);

        frontAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(FrontActivity.this,VendorActivity.class));
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
            case R.id.text_goods:
                pvTime.show();
                break;
            case R.id.text_add:
               startActivity(new Intent(this,AddSupplierActivity.class));
                break;

        }
    }

    public void init() {
        Calendar selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);
        endDate = Calendar.getInstance();
        endDate.set(2033, 11, 28);
        pvTime = new TimePickerView.Builder(this, (date, v) -> {//选中事件回调
            text_goods.setText(DateUtils.getTimes(date) + "");
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.BLUE)
                .setContentSize(18)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();
    }


}
