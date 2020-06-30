package com.car.notver.adapter;

import android.view.View;

import com.car.notver.R;
import com.car.notver.bean.ClientVo;
import com.car.notver.bean.KeepInfo;
import com.car.notver.ui.activity.AccountActivity;
import com.car.notver.ui.activity.DepositoryActivity1;
import com.car.notver.util.Utility;

import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/21
 * @name:DepositoryAdapter
 */
public class GiveAdapter extends AutoRVAdapter {
    private List<ClientVo> infos;
    private AccountActivity activity1;

    public GiveAdapter(AccountActivity activity1, List<ClientVo> list) {
        super(activity1, list);
        this.infos = list;
        this.activity1=activity1;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.item_give;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        ClientVo info = infos.get(position);
        vh.getTextView(R.id.text_store).setText(info.getStoreName() + "");
        vh.getTextView(R.id.text_name).setText(info.getUsername());
        vh.getTextView(R.id.text_phone).setText("￥"+info.getTotalmileage()+" + "+info.getInitmileage()+"积分");

        vh.getImageView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity1.showDelete(info.getId());
            }
        });
    }


}
