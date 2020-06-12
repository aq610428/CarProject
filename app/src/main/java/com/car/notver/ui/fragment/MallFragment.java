package com.car.notver.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.car.notver.R;
import com.car.notver.adapter.ClientMallAdapter;
import com.car.notver.adapter.InventoryMallAdapter;
import com.car.notver.adapter.MailAdapter;
import com.car.notver.adapter.OpenAdapter;
import com.car.notver.adapter.OwnerAdapter1;
import com.car.notver.adapter.TableAdapter;
import com.car.notver.base.BaseFragment;
import com.car.notver.bean.Bespoke;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.CustomerInfo;
import com.car.notver.bean.KeepInfo;
import com.car.notver.bean.Ordered;
import com.car.notver.bean.UserInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.util.Constants;
import com.car.notver.util.JsonParse;
import com.car.notver.util.Md5Util;
import com.car.notver.util.SaveUtils;
import com.car.notver.util.SystemTools;
import com.car.notver.util.ToastUtil;
import com.car.notver.util.Utility;
import com.car.notver.weight.EmptyDataView;
import com.car.notver.weight.NoDataView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import crossoverone.statuslib.StatusUtil;

/****
 *
 * 清单
 *
 */
public class MallFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener, NetWorkListener {
    private View rootView;
    private boolean isPrepared;
    private TextView text_top_name, text_name;
    private ListView swipe_target;
    private SwipeToLoadLayout swipeToLoadLayout;
    private InventoryMallAdapter adapter;
    private ClientMallAdapter clientMallAdapter;
    private RecyclerView recyclerView;
    private List<CustomerInfo> infos = new ArrayList<>();
    private TableAdapter tableAdapter;
    private OwnerAdapter1 ownerAdapter;
    private List<KeepInfo> keepInfos = new ArrayList<>();
    private List<Bespoke> bespokes = new ArrayList<>();
    private List<Ordered> ordereds = new ArrayList<>();
    private int limit = 10;
    private int page = 1;
    private boolean isRefresh;
    private UserInfo info;
    private OpenAdapter openAdapter;
    private MailAdapter mailAdapter;
    private EmptyDataView mNoDataView;
    private int type;
    private LinearLayout ll_empty;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_mall, container, false);
            initView();
            isPrepared = true;
            lazyLoad();
        }
        return rootView;
    }

    private void initView() {
        ll_empty= getView(rootView, R.id.ll_empty);
        mNoDataView = getView(rootView, R.id.mNoDataView);
        mNoDataView.textView.setText("暂无数据");
        swipe_target = getView(rootView, R.id.swipe_target);
        text_top_name = getView(rootView, R.id.text_top_name);

        swipeToLoadLayout = getView(rootView, R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        text_top_name.setText("清单");

        View vHead = View.inflate(getContext(), R.layout.header_view, null);
        recyclerView = getView(vHead, R.id.recyclerView);
        text_name = getView(vHead, R.id.text_name);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        infos = SystemTools.getListCustomerMe();
        tableAdapter = new TableAdapter(getContext(), infos);
        recyclerView.setAdapter(tableAdapter);
        text_name.setText(infos.get(0).getName());
        tableAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = infos.get(position).getName();
                text_name.setText(name);
                page = 1;
                isRefresh = false;
                if ("故障信息".equals(name)) {
                    type = 1;
                    query1();
                } else if ("保养到期".equals(name)) {
                    type = 2;
                    query();
                } else if ("消费记录".equals(name)) {
                    type = 3;
                    queryopen();
                } else if ("预约记录".equals(name)) {
                    type = 4;
                    queryOrder();
                } else if ("保险数据".equals(name)) {
                    type = 6;
                    keepInfos.clear();
                    adapter = new InventoryMallAdapter(getContext(), keepInfos);
                    swipe_target.setAdapter(adapter);
                    ll_empty.setVisibility(View.VISIBLE);
                } else {
                    type = 5;
                    queryDily();
                }
            }
        });

        swipe_target.addHeaderView(vHead);
        info = SaveUtils.getSaveInfo();
        if (info != null) {
            queryopen();
        }
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusUtil.setUseStatusBarColor(getActivity(), Color.parseColor("#FFFFFF"));
    }

    @Override
    public void onLoadMore() {
        isRefresh = true;
        ++page;
        isRefresh();
    }

    @Override
    public void onRefresh() {
        isRefresh = false;
        page = 1;
        isRefresh();
    }

    public void isRefresh() {
        if (type == 1) {
            query1();
        } else if (type == 2) {
            query();
        } else if (type == 3) {
            type = 3;
            queryopen();
        } else if (type == 4) {
            type = 4;
            queryOrder();
        } else if (type == 5) {
            queryDily();
        } else {
            stopProgressDialog();
            swipeToLoadLayout.setRefreshing(false);
            swipeToLoadLayout.setLoadingMore(false);
        }
    }


    /*******查询保养到期
     * @param ********/
    public void query() {
        String sign = "memberId=" + info.getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(getActivity(), false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("memberId", info.getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_CAR_DATA, params, Api.GET_CAR_DATA_ID, this);
    }

    /*******故障信息
     * @param ********/
    public void query1() {
        String sign = "memberId=" + info.getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(getActivity(), false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("memberId", info.getId());
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_CAR_SAVE, params, Api.GET_CAR_SAVE_ID, this);
    }

    /*******预约记录********/
    public void queryOrder() {
        String sign = "memberId=" + info.getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(getActivity(), false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("memberId", info.getId() + "");
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_ADVNCE, params, Api.GET_ADVNCE_ID, this);
    }

    /*******查询预约列表********/
    public void queryopen() {
        String sign = "memberId=" + info.getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(getActivity(), false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("memberId", info.getId() + "");
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_COINS_INTERBILL, params, Api.GET_COINS_INTERBILL_ID, this);
    }


    /*******查询预约列表********/
    public void queryDily() {
        String sign = "memberId=" + info.getId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(getActivity(), false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("memberId", info.getId() + "");
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_COINS_DAILY, params, Api.GET_COINS_DAILY_ID, this);
    }


    /*******接受，拒绝预约列表
     * @param status********/
    public void queryOrder(int status, String id) {
        String sign = "id=" + id + "&partnerid=" + Constants.PARTNERID + "&status=" + status + Constants.SECREKEY;
        showProgressDialog(getActivity(), false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("id", id + "");
        params.put("status", status + "");
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_ADVANCE_STORE, params, Api.GET_ADVANCE_STORE_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_ADVANCE_STORE_ID:
                        isRefresh();
                        break;

                    case Api.GET_CAR_DATA_ID:
                        List<KeepInfo> infos = JsonParse.getKeepInfo(object);
                        if (infos != null && infos.size() > 0) {
                            setAdapter(infos);
                            ll_empty.setVisibility(View.GONE);
                        } else {
                            if (page == 1 && !isRefresh) {
                                ll_empty.setVisibility(View.VISIBLE);
                                keepInfos = new ArrayList<>();
                                adapter = new InventoryMallAdapter(getContext(), keepInfos);
                                swipe_target.setAdapter(adapter);
                            } else {
                                ToastUtil.showToast("无更多数据");
                            }

                        }
                        break;
                    case Api.GET_CAR_SAVE_ID:
                        List<KeepInfo> keepInfo = JsonParse.getKeepInfo(object);
                        if (keepInfo != null && keepInfo.size() > 0) {
                            ll_empty.setVisibility(View.GONE);
                            setAdapter1(keepInfo);
                        } else {
                            if (page == 1 && !isRefresh) {
                                ll_empty.setVisibility(View.VISIBLE);
                                keepInfos = new ArrayList<>();
                                clientMallAdapter = new ClientMallAdapter(getContext(), keepInfos);
                                swipe_target.setAdapter(clientMallAdapter);
                            } else {
                                ToastUtil.showToast("无更多数据");
                            }

                        }
                        break;
                    case Api.GET_ADVNCE_ID:
                        List<Bespoke> bespokes = JsonParse.getBespokeJson(object);
                        if (bespokes != null && bespokes.size() > 0) {
                            ll_empty.setVisibility(View.GONE);
                            setOrderAdapter(bespokes);
                        } else {
                            if (page == 1 && !isRefresh) {
                                bespokes = new ArrayList<>();
                                ownerAdapter = new OwnerAdapter1(this, bespokes);
                                swipe_target.setAdapter(ownerAdapter);
                                ll_empty.setVisibility(View.VISIBLE);
                            } else {
                                ToastUtil.showToast("无更多数据");
                            }
                        }
                        break;
                    case Api.GET_COINS_INTERBILL_ID:
                        List<Ordered> ordereds = JsonParse.getopenJson(object);
                        if (ordereds != null && ordereds.size() > 0) {
                            ll_empty.setVisibility(View.GONE);
                            setOrdereds(ordereds);
                        } else {
                            if (page == 1 && !isRefresh) {
                                ll_empty.setVisibility(View.VISIBLE);
                                ordereds = new ArrayList<>();
                                openAdapter = new OpenAdapter(this, ordereds);
                                swipe_target.setAdapter(openAdapter);
                            } else {
                                ToastUtil.showToast("无更多数据");
                            }
                        }
                        break;
                    case Api.GET_COINS_DAILY_ID:
                        List<KeepInfo> list = JsonParse.getopenKeepInfoJson(object);
                        if (list != null && list.size() > 0) {
                            ll_empty.setVisibility(View.GONE);
                            setAdapter1List(list);
                        } else {
                            if (page == 1 && !isRefresh) {
                                ll_empty.setVisibility(View.VISIBLE);
                                keepInfos = new ArrayList<>();
                                mailAdapter = new MailAdapter(this, keepInfos);
                                swipe_target.setAdapter(mailAdapter);
                            } else {
                                ToastUtil.showToast("无更多数据");
                            }

                        }
                        break;

                }
            }
        }
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }

    /****消费记录***/
    private void setOrdereds(List<Ordered> list) {
        if (!isRefresh) {
            ordereds.clear();
            ordereds.addAll(list);
            openAdapter = new OpenAdapter(this, ordereds);
            swipe_target.setAdapter(openAdapter);
        } else {
            ordereds.addAll(list);
            openAdapter.setData(ordereds);
            openAdapter.notifyDataSetChanged();
        }
    }

    /****预约记录***/
    private void setOrderAdapter(List<Bespoke> list) {
        if (!isRefresh) {
            bespokes.clear();
            bespokes.addAll(list);
            ownerAdapter = new OwnerAdapter1(this, bespokes);
            swipe_target.setAdapter(ownerAdapter);
        } else {
            bespokes.addAll(list);
            ownerAdapter.setData(bespokes);
            ownerAdapter.notifyDataSetChanged();
        }
    }

    /****保养到期***/
    private void setAdapter(List<KeepInfo> voList) {
        if (!isRefresh) {
            keepInfos.clear();
            keepInfos.addAll(voList);
            adapter = new InventoryMallAdapter(getContext(), keepInfos);
            swipe_target.setAdapter(adapter);
        } else {
            keepInfos.addAll(voList);
            adapter.setData(keepInfos);
            adapter.notifyDataSetChanged();
        }
    }

    /****故障信息***/
    private void setAdapter1(List<KeepInfo> list) {
        if (!isRefresh) {
            keepInfos.clear();
            keepInfos.addAll(list);
            clientMallAdapter = new ClientMallAdapter(getContext(), keepInfos);
            swipe_target.setAdapter(clientMallAdapter);
        } else {
            keepInfos.addAll(list);
            clientMallAdapter.setData(keepInfos);
            clientMallAdapter.notifyDataSetChanged();
        }
    }

    /****行驶排行***/
    private void setAdapter1List(List<KeepInfo> list) {
        if (!isRefresh) {
            keepInfos.clear();
            keepInfos.addAll(list);
            mailAdapter = new MailAdapter(this, keepInfos);
            swipe_target.setAdapter(mailAdapter);
        } else {
            keepInfos.addAll(list);
            adapter.setData(keepInfos);
            mailAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onFail() {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }
}
