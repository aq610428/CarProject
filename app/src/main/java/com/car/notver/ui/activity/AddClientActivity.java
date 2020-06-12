package com.car.notver.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bigkoo.pickerview.TimePickerView;
import com.car.notver.R;
import com.car.notver.adapter.ClientAdapter;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.util.DateUtils;
import com.car.notver.weight.FinishActivityManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/19
 * @name:添加客户
 */
public class AddClientActivity extends BaseActivity {
    private RecyclerView recyclerView, recyclerView1;
    private ClientAdapter clientAdapter,clientAdapter1;
    private List<String> data = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private Calendar startDate, endDate;
    private TimePickerView pvTime;
    private TextView text_keep_nor, text_time_next, text_day_nomor, text_year, text_forcing_nomor, text_business_nor;
    private TextView title_text_tv, title_left_btn, title_right_btn,text_mechanic;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addclient);
        BaseApplication.activityTaskManager.putActivity("AddClientActivity",this);
        initTime();
    }

    @Override
    protected void initView() {
        text_mechanic = getView(R.id.text_mechanic);
        title_right_btn = getView(R.id.title_right_btn);
        text_time_next = getView(R.id.text_time_next);
        text_day_nomor = getView(R.id.text_day_nomor);
        text_year = getView(R.id.text_year);
        text_forcing_nomor = getView(R.id.text_forcing_nomor);
        text_business_nor = getView(R.id.text_business_nor);
        text_keep_nor = getView(R.id.text_keep_nor);
        recyclerView = getView(R.id.recyclerView);
        recyclerView1 = getView(R.id.recyclerView1);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_right_btn.setText("提交");
        title_text_tv.setText("添加客户");

        text_forcing_nomor.setOnClickListener(this);
        text_year.setOnClickListener(this);
        text_day_nomor.setOnClickListener(this);
        text_keep_nor.setOnClickListener(this);
        text_time_next.setOnClickListener(this);
        text_business_nor.setOnClickListener(this);
        title_left_btn.setOnClickListener(this);
        title_right_btn.setOnClickListener(this);
        text_mechanic.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 3);
        recyclerView1.setLayoutManager(gridLayoutManager1);
        recyclerView1.setHasFixedSize(true);

        data.add("润滑系统");
        data.add("燃油系统");
        data.add("冷却系统");
        data.add("变速箱系统");
        data.add("燃油系统");
        data.add("冷却系统");
        clientAdapter = new ClientAdapter(this, data);
        recyclerView.setAdapter(clientAdapter);
        clientAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clientAdapter.setSelectedItem(position);
                clientAdapter.notifyDataSetChanged();
            }
        });

        list.add("雨刷");
        list.add("大灯");
        list.add("轮胎");
        list.add("美容");
        list.add("蓄电池");
        list.add("四轮定位");
        clientAdapter1 = new ClientAdapter(this, list);
        recyclerView1.setAdapter(clientAdapter1);
        clientAdapter1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clientAdapter1.setSelectedItem(position);
                clientAdapter1.notifyDataSetChanged();
            }
        });
    }


    private int isKeep;

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_mechanic:
                break;
            case R.id.text_keep_nor:
                isKeep = 1;
                pvTime.show();
                break;
            case R.id.text_time_next:
                isKeep = 2;
                pvTime.show();
                break;
            case R.id.text_day_nomor:
                isKeep = 3;
                pvTime.show();
                break;
            case R.id.text_year:
                isKeep = 4;
                pvTime.show();
                break;
            case R.id.text_forcing_nomor:
                isKeep = 5;
                pvTime.show();
                break;
            case R.id.text_business_nor:
                isKeep = 6;
                pvTime.show();
                break;


        }
    }

    public void initTime() {
        Calendar selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);
        endDate = Calendar.getInstance();
        endDate.set(2033, 11, 28);
        pvTime = new TimePickerView.Builder(this, (date, v) -> {//选中事件回调
            switch (isKeep) {
                case 1:
                    text_keep_nor.setText(DateUtils.getTimes(date));
                    break;
                case 2:
                    text_time_next.setText(DateUtils.getTimes(date));
                    break;
                case 3:
                    text_day_nomor.setText(DateUtils.getTimes(date));
                    break;
                case 4:
                    text_year.setText(DateUtils.getTimes(date));
                    break;
                case 5:
                    text_forcing_nomor.setText(DateUtils.getTimes(date));
                    break;
                case 6:
                    text_business_nor.setText(DateUtils.getTimes(date));
                    break;

            }

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
