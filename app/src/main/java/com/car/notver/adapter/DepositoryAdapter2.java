package com.car.notver.adapter;

import android.content.Context;

import com.car.notver.R;
import com.car.notver.bean.ClientVo;
import com.car.notver.bean.KeepInfo;
import com.car.notver.util.Utility;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/21
 * @name:DepositoryAdapter
 */
public class DepositoryAdapter2 extends AutoRVAdapter {
    private List<ClientVo> infos;

    public DepositoryAdapter2(Context context, List<ClientVo> list) {
        super(context, list);
        this.infos = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_depository;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        ClientVo info = infos.get(position);
        if (!Utility.isEmpty(info.getUsername())) {
            vh.getTextView(R.id.text_name).setText(info.getUsername() + "");
        } else {
            vh.getTextView(R.id.text_name).setText("匿名");
        }
         vh.getTextView(R.id.text_store).setText(info.getStoreName());
        vh.getTextView(R.id.text_phone).setText(info.getMobile());
        vh.getTextView(R.id.text_license).setText(info.getCarcard());
        vh.getTextView(R.id.text_make).setText(info.getFactory());
    }

    public void setData(List<ClientVo> keepInfos) {
        this.infos = keepInfos;
    }
}
