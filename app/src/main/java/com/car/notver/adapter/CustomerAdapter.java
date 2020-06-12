package com.car.notver.adapter;

import android.Manifest;
import android.view.View;
import android.widget.TextView;
import com.car.notver.R;
import com.car.notver.ui.fragment.CustomerFragment;
import com.car.notver.util.SystemTools;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/15
 * @name:CustomerAdapter
 */
public class CustomerAdapter extends AutoRVAdapter {
    private List<String> list;
    private CustomerFragment fragment;
    public String phone = "";


    public CustomerAdapter(CustomerFragment fragment, List<String> data) {
        super(fragment.getContext(), data);
        this.list = data;
        this.fragment = fragment;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_customer;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        TextView textView = vh.getTextView(R.id.tv_marke);
        TextView tv_phone = vh.getTextView(R.id.tv_phone);
        TextView tv_address = vh.getTextView(R.id.tv_address);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = textView.getText().toString().trim();
                fragment.requestPermission(new String[]{Manifest.permission.CALL_PHONE}, 3);
            }
        });
        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = tv_phone.getText().toString().trim();
                fragment.requestPermission(new String[]{Manifest.permission.CALL_PHONE}, 3);
            }
        });

        tv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemTools.openMap(tv_address.getText().toString(),fragment.getContext());
            }
        });
    }

}
