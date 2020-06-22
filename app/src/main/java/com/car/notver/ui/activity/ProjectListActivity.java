package com.car.notver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.car.notver.R;
import com.car.notver.adapter.LeftAdapter;
import com.car.notver.adapter.ProjectAdapter;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.PhotoInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.util.BigDecimalUtils;
import com.car.notver.util.Constants;
import com.car.notver.util.Md5Util;
import com.car.notver.util.SaveUtils;
import com.car.notver.util.ToastUtil;
import com.car.notver.util.Utility;
import org.json.JSONObject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author: zt
 * @date: 2020/5/21
 * @name:商品管理
 */
public class ProjectListActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_count;
    private RecyclerView rv_left, rv_right;
    private ProjectAdapter adapter;
    private LeftAdapter leftAdapter;
    private List<PhotoInfo> infoList = new ArrayList<>();
    private List<PhotoInfo> infos = new ArrayList<>();
    private Button btn_next;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_projec);
        BaseApplication.activityTaskManager.putActivity("CommodityActivity", this);
    }

    @Override
    protected void initView() {
        btn_next = getView(R.id.btn_next);
        text_count = getView(R.id.text_count);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        rv_left = getView(R.id.rv_left);
        rv_right = getView(R.id.rv_right);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("选择商品");

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv_right.setLayoutManager(manager);


        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        rv_left.setLayoutManager(manager1);

        btn_next.setOnClickListener(this);
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
            case R.id.btn_next:
                sumberOrder();
                break;

        }
    }

    /******提交商品*****/
    private double total;
    public void sumberOrder() {
        total = 0;
        if (adapter != null && adapter.map != null && adapter.map.size() > 0) {
            for (Map.Entry<Integer, PhotoInfo> entry : adapter.map.entrySet()) {
                PhotoInfo info = entry.getValue();
                total = BigDecimalUtils.add(new BigDecimal(total), new BigDecimal(info.getPic())).doubleValue();
            }
            text_count.setText("合计：￥" + total);
        }else{
            text_count.setText("合计：￥0" );
        }
    }


    /*******商品列表********/
    public void query() {
        String sign = "memberId=" + SaveUtils.getSaveInfo().getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("memberId", SaveUtils.getSaveInfo().getId() + "");
        params.put("limit", "10");
        params.put("page", "1");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_INFO, params, Api.GET_INFOO_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_USER_VERSION_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        finish();
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    @Override
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
    }
}
