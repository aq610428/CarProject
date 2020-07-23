package com.car.notver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amap.api.services.core.PoiItem;
import com.car.notver.R;
import com.car.notver.adapter.PoiSearchAdapter;
import com.car.notver.base.BaseActivity;
import com.car.notver.util.LogUtils;
import com.car.notver.weight.PreferenceUtils;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/6/8
 * @name:PoiSearchActivity
 */
public class PoiSearchActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private TextView title_text_tv, title_left_btn;
    private List<PoiItem> poiItems;
    private PoiSearchAdapter poiSearchAdapter;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_poisearch);
    }

    @Override
    protected void initView() {
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("店铺地址");
        recyclerView = getView(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
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


    @Override
    protected void initData() {
        poiItems = (List<PoiItem>) getIntent().getSerializableExtra("poiItems");
        if (poiItems != null && poiItems.size() > 0) {
            poiSearchAdapter = new PoiSearchAdapter(this, poiItems);
            recyclerView.setAdapter(poiSearchAdapter);
            poiSearchAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String provider=poiItems.get(position).getProvinceName();
                    String city=poiItems.get(position).getCityName();
                    String district=poiItems.get(position).getDirection();
                    String name=poiItems.get(position).getTitle();
                    double lat=poiItems.get(position).getLatLonPoint().getLatitude();
                    double lon=poiItems.get(position).getLatLonPoint().getLongitude();
                    PreferenceUtils.setPrefString(PoiSearchActivity.this, "provider",provider);
                    PreferenceUtils.setPrefString(PoiSearchActivity.this, "city", city);
                    PreferenceUtils.setPrefString(PoiSearchActivity.this, "district", district);
                    PreferenceUtils.setPrefString(PoiSearchActivity.this, "name",name );
                    PreferenceUtils.setPrefString(PoiSearchActivity.this, "lat",  lat+ "");
                    PreferenceUtils.setPrefString(PoiSearchActivity.this, "lon", lon + "");
                    LogUtils.e("provider="+provider+"city="+city+"district="+district+name+lat+lon);
                    finish();
                }
            });
        }
    }
}
