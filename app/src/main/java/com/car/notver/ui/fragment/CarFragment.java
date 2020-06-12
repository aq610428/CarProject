package com.car.notver.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.car.notver.R;
import com.car.notver.adapter.CarAdapter;
import com.car.notver.base.BaseFragment;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.Money;
import com.car.notver.bean.UserInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.ui.activity.CommunityActivity;
import com.car.notver.ui.activity.IntegralActivity;
import com.car.notver.util.Constants;
import com.car.notver.util.JsonParse;
import com.car.notver.util.Md5Util;
import com.car.notver.util.SaveUtils;
import com.car.notver.util.Utility;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import crossoverone.statuslib.StatusUtil;

/****
 *
 *
 * 资产
 *
 */
public class CarFragment extends BaseFragment implements NetWorkListener, OnRefreshListener, View.OnClickListener {
    private View rootView;
    private boolean isPrepared;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView swipe_target;
    private List<Money> monies = new ArrayList<>();
    private CarAdapter carAdapter;
    private Button btn_next;
    private TextView text_integral, text_grand, text_right_name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_order, container, false);
            initView();
            isPrepared = true;
            lazyLoad();
        }
        return rootView;
    }

    UserInfo info;

    private void initView() {
        text_right_name = rootView.findViewById(R.id.text_right_name);
        text_grand = rootView.findViewById(R.id.text_grand);
        text_integral = rootView.findViewById(R.id.text_integral);
        btn_next = rootView.findViewById(R.id.btn_next);
        swipe_target = rootView.findViewById(R.id.mListView);
        swipeToLoadLayout = rootView.findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnRefreshListener(this);
        btn_next.setOnClickListener(this);
        text_right_name.setOnClickListener(this);
        info = SaveUtils.getSaveInfo();
        if (info != null) {
            query();
        }
        queryList();
    }


    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
    }

    /*******虚拟货币列表
     * @param ********/
    public void query() {
        String sign = "partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(getActivity(), false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_COINS_LIST, params, Api.GET_COINS_LIST_ID, this);
    }

    /*******积分
     * @param ********/
    public void queryList() {
        String sign = "memberid=" + info.getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(getActivity(), false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("memberid", info.getId() + "");
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_COINS_INTER, params, Api.GET_COINS_INTER_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_COINS_LIST_ID:
                        monies = JsonParse.getBespokemoniesJson(object);
                        if (monies != null && monies.size() > 0) {
                            setAdapter();
                        }
                        break;
                    case Api.GET_COINS_INTER_ID:
                        JSONObject jsonObject = object.optJSONObject("result");
                        if (jsonObject != null) {
                            String totalintegral = jsonObject.optString("totalintegral");
                            String usablelintegral = jsonObject.optString("usablelintegral");
                            text_grand.setText(totalintegral + "");
                            text_integral.setText(usablelintegral + "");
                        }
                        break;
                }
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }


    @Override
    public void onResume() {
        super.onResume();
        StatusUtil.setUseStatusBarColor(getActivity(), Color.parseColor("#FFFFFF"));
    }


    private void setAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        swipe_target.setLayoutManager(layoutManager);

        carAdapter = new CarAdapter(getContext(), monies);
        swipe_target.setAdapter(carAdapter);
    }

    @Override
    public void onFail() {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        queryList();
        query();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_next:
                intent = new Intent(getContext(), CommunityActivity.class);
                intent.putExtra("url", "http://kb.jkabe.com/app/dlchezhen");
                intent.putExtra("name", "积分兑换");
                startActivity(intent);
                break;
            case R.id.text_right_name:
                intent = new Intent(getContext(), IntegralActivity.class);
                intent.putExtra("url", "http://kb.jkabe.com/app/dlchezhen");
                intent.putExtra("name", "积分兑换");
                startActivity(intent);
                break;
        }
    }
}
