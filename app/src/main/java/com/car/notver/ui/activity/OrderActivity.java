package com.car.notver.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.TimePickerView;
import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.Bespoke;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.Ordered;
import com.car.notver.bean.UserInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.util.Constants;
import com.car.notver.util.DateUtils;
import com.car.notver.util.JsonParse;
import com.car.notver.util.Md5Util;
import com.car.notver.util.QRCodeUtil;
import com.car.notver.util.SaveUtils;
import com.car.notver.util.ToastUtil;
import com.car.notver.util.Utility;
import com.google.zxing.qrcode.encoder.QRCode;

import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author: zt
 * @date: 2020/5/21
 * @name:开单详情
 */
public class OrderActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, text_repair, text_project, text_commodity;
    private RelativeLayout rl_repair, rl_code;
    private Bespoke bespoke;
    private TextView text_license, text_date;
    private EditText et_cash, et_integral, et_parts, et_accessory, et_number;
    private Button btn_billing, btn_code;
    private UserInfo info;
    private Ordered ordered;
    private ImageView iv_code;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_order);
        info = SaveUtils.getSaveInfo();
        BaseApplication.activityTaskManager.putActivity("OrderActivity", this);
    }

    @Override
    protected void initView() {
        rl_code = getView(R.id.rl_code);
        iv_code = getView(R.id.iv_code);
        btn_code = getView(R.id.btn_code);
        et_cash = getView(R.id.et_cash);
        et_integral = getView(R.id.et_integral);
        et_parts = getView(R.id.et_parts);
        et_accessory = getView(R.id.et_accessory);
        et_number = getView(R.id.et_number);

        btn_billing = getView(R.id.btn_billing);
        text_date = getView(R.id.text_date);
        text_license = getView(R.id.text_license);
        text_commodity = getView(R.id.text_commodity);
        text_project = getView(R.id.text_project);
        rl_repair = getView(R.id.rl_repair);
        text_repair = getView(R.id.text_repair);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        rl_repair.setOnClickListener(this);
        text_project.setOnClickListener(this);
        text_commodity.setOnClickListener(this);
        text_date.setOnClickListener(this);
        title_text_tv.setText("开单详情");
        btn_billing.setOnClickListener(this);
        btn_code.setOnClickListener(this);
        rl_code.setOnClickListener(this);
        initDataTime();
    }

    @Override
    protected void initData() {
        bespoke = (Bespoke) getIntent().getSerializableExtra("bespoke");
        if (bespoke != null) {
            text_license.setText(bespoke.getCarcard());
            text_repair.setText(bespoke.getMobile());
            if (bespoke.getStatus() == 4 && !Utility.isEmpty(bespoke.getBillId())) {//已开单，不可编辑
                text_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                text_date.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                et_cash.setFocusable(false);
                et_integral.setFocusable(false);
                et_parts.setFocusable(false);
                et_accessory.setFocusable(false);
                et_number.setFocusable(false);
                btn_billing.setVisibility(View.GONE);
                query();
            }

        } else {
            String id = getIntent().getStringExtra("id");
            query1(id);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_code:
                rl_code.setVisibility(View.GONE);
                break;
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_date:
                pvTime1.show();
                break;
            case R.id.btn_billing:
                checkData();
                break;
            case R.id.btn_code:
                if (ordered != null) {
                    createView();
                }
                break;
        }
    }

    private void createView() {
        rl_code.setVisibility(View.VISIBLE);
        if (ordered != null && !Utility.isEmpty(ordered.getId())) {
            Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(ordered.getId() + "," + ordered.getIntegral(), 480, 480);
            iv_code.setImageBitmap(mBitmap);
            iv_code.setOnClickListener(this);
        }
    }


    /*******查询预约详情********/
    public void query1(String id) {
        String sign = "id=" + id + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("id", id + "");
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_ADVANCE_BILL, params, Api.GET_ADVANCE_BILL_ID, this);
    }


    /*******查询预约详情********/
    public void query() {
        String sign = "id=" + bespoke.getBillId() + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("id", bespoke.getBillId() + "");
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_ADVANCE_BILL, params, Api.GET_ADVANCE_BILL_ID, this);
    }

    private void checkData() {
        String finishTime = text_date.getText().toString();//完工时间
        String cash = et_cash.getText().toString();
        String integral = et_integral.getText().toString();
        String parts = et_parts.getText().toString();
        String accessory = et_accessory.getText().toString();
        String number = et_number.getText().toString();
        String project = bespoke.getProject();

        try {
            parts = URLDecoder.decode(parts, "UTF-8");
            accessory = URLDecoder.decode(accessory, "UTF-8");
            number = URLDecoder.decode(number, "UTF-8");
            project = URLDecoder.decode(project, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if ("维修开单".equals(project) || "保养开单".equals(project)) {
            if (Utility.isEmpty(finishTime)) {
                ToastUtil.showToast("完工时间不能为空");
            } else if (Utility.isEmpty(cash)) {
                ToastUtil.showToast("请输入维修价格");
            } else if (Utility.isEmpty(integral)) {
                ToastUtil.showToast("请输入需要抵扣积分");
            } else if (Utility.isEmpty(parts)) {
                ToastUtil.showToast("请输入您的维修方案");
            } else if (Utility.isEmpty(accessory)) {
                ToastUtil.showToast("请输入更换配件的名称");
            } else if (Utility.isEmpty(number)) {
                ToastUtil.showToast("请输入更换配件的数量");
            } else {
                sumber();
            }
        } else {
            if (Utility.isEmpty(finishTime)) {
                ToastUtil.showToast("完工时间不能为空");
            } else if (Utility.isEmpty(cash)) {
                ToastUtil.showToast("请输入维修价格");
            } else if (Utility.isEmpty(integral)) {
                ToastUtil.showToast("请输入需要抵扣积分");
            } else {
                sumber1();
            }
        }


    }

    public void sumber() {
        String finishTime = text_date.getText().toString();//完工时间
        String cash = et_cash.getText().toString();
        String integral = et_integral.getText().toString();
        String parts = et_parts.getText().toString();
        String accessory = et_accessory.getText().toString();
        String number = et_number.getText().toString();
        String project = bespoke.getProject();
        String sign = "amount=" + cash + "&finishTime=" + finishTime + "&integral=" + integral + "&memberId=" + info.getId()
                + "&orderId=" + bespoke.getId() + "&partnerid=" + Constants.PARTNERID + "&partsNum=" + number + "&partsReplace=" + accessory
                + "&project=" + project
                + "&repairPlan=" + parts + "&storeId=" + bespoke.getStoreId() + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("amount", cash + "");
        params.put("finishTime", finishTime + "");
        params.put("integral", integral + "");
        params.put("memberId", info.getId() + "");
        params.put("orderId", bespoke.getId() + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("partsNum", number + "");
        params.put("partsReplace", accessory + "");
        params.put("project", project + "");
        params.put("repairPlan", parts + "");
        params.put("storeId", bespoke.getStoreId() + "");
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_ADVANCE_SAVE, params, Api.GET_ADVANCE_SAVE_ID, this);
    }


    public void sumber1() {
        String finishTime = text_date.getText().toString();//完工时间
        String cash = et_cash.getText().toString();
        String integral = et_integral.getText().toString();

        String parts = et_parts.getText().toString();
        String accessory = et_accessory.getText().toString();
        String number = et_number.getText().toString();

        String project = bespoke.getProject();
        String sign = null;
        sign = "amount=" + cash + "&finishTime=" + finishTime + "&integral=" + integral + "&memberId=" + info.getId()
                + "&orderId=" + bespoke.getId() + "&partnerid=" + Constants.PARTNERID;
        if (!Utility.isEmpty(number)) {
            sign = sign + "&partsNum=" + number;
        }
        if (!Utility.isEmpty(accessory)) {
            sign = sign + "&partsReplace=" + accessory;
        }
        sign = sign + "&project=" + project;
        if (!Utility.isEmpty(parts)) {
            sign = sign + "&repairPlan=" + parts;
        }
        sign = sign + "&storeId=" + bespoke.getStoreId() + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("amount", cash + "");
        params.put("finishTime", finishTime + "");
        params.put("integral", integral + "");
        params.put("memberId", info.getId() + "");
        params.put("orderId", bespoke.getId() + "");
        params.put("partnerid", Constants.PARTNERID);
        if (!Utility.isEmpty(number + "")) {
            params.put("partsNum", number + "");
        }
        if (!Utility.isEmpty(accessory + "")) {
            params.put("partsReplace", accessory + "");
        }
        if (!Utility.isEmpty(parts + "")) {
            params.put("repairPlan", parts + "");
        }
        params.put("project", project + "");
        params.put("storeId", bespoke.getStoreId() + "");
        params.put("apptype", Constants.TYPE);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_ADVANCE_SAVE, params, Api.GET_ADVANCE_SAVE_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_ADVANCE_BILL_ID:
                        ordered = JsonParse.getOrderedJson(object);
                        if (ordered != null) {
                            updateView();
                        }
                        break;
                    case Api.GET_ADVANCE_SAVE_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        finish();
                        break;

                }
            }
        }
        stopProgressDialog();
    }


    private void updateView() {
        text_license.setText(ordered.getCarcard() + "");
        text_repair.setText(ordered.getMobile() + "");
        if (!Utility.isEmpty(ordered.getFinishTime())) {
            String str[] = ordered.getFinishTime().split("T");
            if (str != null && str.length > 0) {
                text_date.setText(str[0] + "");
            }
        }
        et_cash.setText("￥" + ordered.getAmount());
        et_integral.setText(ordered.getIntegral() + "");
        et_parts.setText(ordered.getRepairPlan() + "");
        et_accessory.setText(ordered.getPartsReplace());
        et_number.setText(ordered.getPartsNum() + "");
        btn_billing.setVisibility(View.GONE);

        int integralFlag = ordered.getIntegralFlag();
        if (integralFlag == 1) {//待支付
            btn_code.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFail() {
        stopProgressDialog();
    }

    @Override
    public void onError(Exception e) {
        stopProgressDialog();
    }


    private Calendar startDate, endDate;
    private TimePickerView pvTime1;

    protected void initDataTime() {
        Calendar selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);
        endDate = Calendar.getInstance();
        endDate.set(2033, 11, 28);
        pvTime1 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                text_date.setText(DateUtils.getTimes(date) + "");
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
