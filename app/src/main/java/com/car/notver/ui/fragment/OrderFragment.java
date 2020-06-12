package com.car.notver.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.car.notver.R;
import com.car.notver.base.BaseFragment;

/****
 *
 *
 * 我的订单列表
 *
 */
public class OrderFragment extends BaseFragment {
    private View rootView;
    private boolean isPrepared;
    private TextView text_top_name;

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

    private void initView() {
        text_top_name = rootView.findViewById(R.id.text_top_name);
        text_top_name.setText("开单结算");
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
    }

}
