package com.car.notver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.ClientVo;
import com.car.notver.weight.FinishActivityManager;

/**
 * @author: zt
 * @date: 2020/5/27
 * @name:关于我们
 */
public class AddressActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn, text_mode;
    private ClientVo clientVo;
    private EditText et_name, et_phone, et_discern, et_remarks;
    private Button btn_next;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_address);
        BaseApplication.activityTaskManager.putActivity("AddressActivity", this);
    }

    @Override
    protected void initView() {
        btn_next = getView(R.id.btn_next);
        text_mode = getView(R.id.text_mode);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        et_name = getView(R.id.et_name);
        et_phone = getView(R.id.et_phone);
        et_discern = getView(R.id.et_discern);
        et_remarks = getView(R.id.et_remarks);

        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("新增项目");
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
        clientVo = (ClientVo) getIntent().getSerializableExtra("clientVo");
        if (clientVo != null) {
            title_text_tv.setText("项目修改");
            btn_next.setText("确认修改");
            text_mode.setText(clientVo.getStoreName() + "");
            et_name.setText(clientVo.getUsername());
            et_phone.setText("￥" + clientVo.getTotalmileage());
            et_discern.setText(clientVo.getInitmileage() + "积分");
        }
    }
}
