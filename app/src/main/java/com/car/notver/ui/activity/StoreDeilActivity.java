package com.car.notver.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.car.notver.R;
import com.car.notver.base.BaseActivity1;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.StoreInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.glide.GlideUtils;
import com.car.notver.util.Constants;
import com.car.notver.util.DateUtils;
import com.car.notver.util.LogUtils;
import com.car.notver.util.Md5Util;
import com.car.notver.util.SaveUtils;
import com.car.notver.util.ToastUtil;
import com.car.notver.util.Utility;
import com.car.notver.weight.MediaLoader;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * @author: zt
 * @date: 2020/5/28
 * @name:店铺详情
 */
public class StoreDeilActivity extends BaseActivity1 implements NetWorkListener {
    private StoreInfo info;
    private TextView title_text_tv, title_left_btn;
    private EditText text_name, text_contacts, text_phone, text_adress, text_brand, text_business, text_rescue;
    private ImageView lv_logo;
    private TextView text_album, text_natural, text_start, text_end;
    private int type = 0;
    private Calendar startDate, endDate;
    private Button btn_next;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_storedeil);
        BaseApplication.activityTaskManager.putActivity("StoreDeilActivity", this);
    }

    @Override
    protected void initView() {
        btn_next = getView(R.id.btn_next);
        text_end = getView(R.id.text_end);
        text_start = getView(R.id.text_start);
        text_album = getView(R.id.text_album);
        text_natural = getView(R.id.text_natural);
        lv_logo = getView(R.id.lv_logo);
        text_name = getView(R.id.text_name);
        text_contacts = getView(R.id.text_contacts);
        text_phone = getView(R.id.text_phone);
        text_adress = getView(R.id.text_adress);
        text_brand = getView(R.id.text_brand);
        text_business = getView(R.id.text_business);
        text_rescue = getView(R.id.text_rescue);

        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_album.setOnClickListener(this);
        text_natural.setOnClickListener(this);
        title_text_tv.setText("店铺详情");
        text_start.setOnClickListener(this);
        text_end.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        lv_logo.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        info = (StoreInfo) getIntent().getSerializableExtra("info");
        if (info != null) {
            text_name.setText(info.getName());
            text_contacts.setText(info.getContactPerson());
            text_phone.setText(info.getPhone());
            text_adress.setText(info.getAddress());
            text_brand.setText(info.getBrandName());
            text_business.setText(info.getBusinessScope());
            text_rescue.setText(info.getRescuePhone());
            GlideUtils.setImageUrl(info.getLogo(), lv_logo);

            if (!Utility.isEmpty(info.getOperTime())) {
                String[] open = info.getOperTime().split("-");
                if (open != null && open.length > 0) {
                    text_start.setText(open[0] + "");
                    text_end.setText(open[1] + "");
                }
            }
        }

        Calendar selectedDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);
        endDate = Calendar.getInstance();
        endDate.set(2033, 11, 28);
        pvTime1 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (type == 1) {
                    text_start.setText(DateUtils.getTime1(date) + "");
                } else {
                    text_end.setText(DateUtils.getTime1(date) + "");
                }
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{false, false, false, true, true, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.BLUE)
                .setContentSize(18)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();
    }


    private void checkData() {
        String name = text_name.getText().toString();
        String contacts = text_contacts.getText().toString();
        String phone = text_phone.getText().toString();
        String start = text_start.getText().toString();
        String end = text_end.getText().toString();
        String address = text_adress.getText().toString();

        String brand = text_brand.getText().toString();
        String work = text_business.getText().toString();
        String mobile = text_rescue.getText().toString();

        if (Utility.isEmpty(name)) {
            ToastUtil.showToast("企业名称不能为空");
        } else if (Utility.isEmpty(contacts)) {
            ToastUtil.showToast("联系人不能为空");
        } else if (Utility.isEmpty(phone)) {
            ToastUtil.showToast("联系电话不能为空");
        } else if (Utility.isEmpty(start)) {
            ToastUtil.showToast("营业时间不能为空");
        } else if (Utility.isEmpty(end)) {
            ToastUtil.showToast("营业时间不能为空");
        } else if (Utility.isEmpty(address)) {
            ToastUtil.showToast("地址不能为空");
        } else if (Utility.isEmpty(brand)) {
            ToastUtil.showToast("主营品牌不能为空");
        } else if (Utility.isEmpty(work)) {
            ToastUtil.showToast("主营业务不能为空");
        } else {
            String sign = null;
            sign = "address=" + address + "&area=" + info.getArea() + "&brandName=" + brand + "&businessScope=" + work + "&city=" + info.getCity() + "&contactPerson=" + contacts
                    + "&id=" + info.getId() + "&lat=" + info.getLat() + "&lng=" + info.getLng() + "&logo=" + info.getLogo() + "&memberId=" + SaveUtils.getSaveInfo().getId() + "&name=" + name + "&operTime=" + start + "-" + end
                    + "&partnerid=" + Constants.PARTNERID + "&phone=" + phone + "&province=" + info.getProvince();
            if (!Utility.isEmpty(mobile)) {
                sign = sign + "&rescuePhone=" + mobile;
            }
            sign = sign + Constants.SECREKEY;
            ;
            showProgressDialog(this, false);
            Map<String, String> params = okHttpModel.getParams();
            params.put("apptype", Constants.TYPE);
            params.put("area", info.getArea() + "");
            params.put("address", address + "");
            params.put("brandName", brand);
            params.put("businessScope", work);
            params.put("city", info.getCity() + "");
            params.put("contactPerson", contacts);
            params.put("id", info.getId() + "");
            params.put("lat", info.getLat() + "");
            params.put("lng", info.getLng() + "");
            params.put("logo", info.getLogo() + "");
            params.put("memberId", SaveUtils.getSaveInfo().getId() + "");
            params.put("name", name);
            params.put("operTime", start + "-" + end);
            params.put("partnerid", Constants.PARTNERID);
            params.put("phone", phone);
            params.put("province", info.getProvince() + "");
            if (!Utility.isEmpty(mobile)) {
                params.put("rescuePhone", mobile);
            }
            params.put("sign", Md5Util.encode(sign));
            okHttpModel.get(Api.GET_STOREINFO, params, Api.GET_STOREINFO_ID, this);
        }
    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_STOREINFO_ID:
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

    private TimePickerView pvTime1;

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_next:
                checkData();
                break;
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.lv_logo:
                requestPermission(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
                break;

            case R.id.text_start:
                type = 1;
                pvTime1.show();
                break;
            case R.id.text_end:
                type = 2;
                pvTime1.show();
                break;
            case R.id.text_album:
                intent = new Intent(StoreDeilActivity.this, PhotoActivity.class);
                intent.putExtra("info", info);
                startActivity(intent);
                break;
            case R.id.text_natural:
                intent = new Intent(StoreDeilActivity.this, LegalActivity.class);
                intent.putExtra("info", info);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void permissinSucceed(int code) {
        super.permissinSucceed(code);
        selectPhoto();

    }

    private void selectPhoto() {
        Album.initialize(AlbumConfig.newBuilder(this)
                .setAlbumLoader(new MediaLoader())
                .build());
        ArrayList<AlbumFile> albumFiles = new ArrayList<>();
        Album.image(this) // Image selection.
                .multipleChoice()
                .camera(true)
                .columnCount(3)
                .selectCount(3)
                .checkedList(albumFiles)
                .onResult(result -> {
                    if (result != null && result.size() > 0) {
                        String path = result.get(0).getPath();
                        initLuban(path);
                    }
                })
                .onCancel(result -> LogUtils.e("已"))
                .start();
    }


    /*****图片压缩*****/
    private void initLuban(String path) {
        Luban.with(this).load(new File(path)).ignoreBy(100)
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        if (file != null) {
                            upLoad(file);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();
    }


    private void upLoad(File file) {
        String sign = "partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        OkGo.<String>post(Api.GET_UPDATELOAD).isMultipart(true).tag(BaseApplication.getContext()).params(params).params("file", file).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.body() != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        String result = jsonObject.optString("result");
                        info.setLogo(result);
                        GlideUtils.setImageUrl(info.getLogo(), lv_logo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                stopProgressDialog();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                stopProgressDialog();
            }
        });
    }

}
