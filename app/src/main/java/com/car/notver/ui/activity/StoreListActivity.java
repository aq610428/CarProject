package com.car.notver.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.LatLng;
import com.bigkoo.pickerview.TimePickerView;
import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.StoreInfo;
import com.car.notver.bean.UserInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.glide.GlideUtils;
import com.car.notver.util.BigDecimalUtils;
import com.car.notver.util.Constants;
import com.car.notver.util.DateUtils;
import com.car.notver.util.LogUtils;
import com.car.notver.util.Md5Util;
import com.car.notver.util.SaveUtils;
import com.car.notver.util.SystemTools;
import com.car.notver.util.ToastUtil;
import com.car.notver.util.Utility;
import com.car.notver.weight.MediaLoader;
import com.car.notver.weight.PreferenceUtils;
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

import static com.car.notver.util.LocationUtils.getDefaultOption;

/**
 * @author: zt
 * @date: 2020/5/14
 * @name:企业门店资料提交
 */
public class StoreListActivity extends BaseActivity implements NetWorkListener {
    private TimePickerView pvTime1;
    private TextView text_cover, text_start, text_end;
    private TextView title_text_tv, title_left_btn, et_address;
    private Calendar startDate, endDate;
    private Button btn_next;
    private RelativeLayout rl_photo;
    private ImageView iv_photo, iv_address;
    private String result;
    private EditText et_cardNmae, et_contacts, et_phone, et_brand, et_work, et_mobile;
    private RelativeLayout rl_tabs;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_store);
        BaseApplication.activityTaskManager.putActivity("StoreListActivity", this);
    }

    @Override
    protected void initView() {
        rl_tabs = getView(R.id.rl_tabs);
        iv_address = getView(R.id.iv_address);
        et_work = getView(R.id.et_work);
        et_mobile = getView(R.id.et_mobile);
        et_brand = getView(R.id.et_brand);
        et_contacts = getView(R.id.et_contacts);
        et_phone = getView(R.id.et_phone);
        et_cardNmae = getView(R.id.et_cardNmae);
        text_start = getView(R.id.text_start);
        text_end = getView(R.id.text_end);
        text_cover = getView(R.id.text_cover);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        rl_photo = getView(R.id.rl_photo);
        iv_photo = getView(R.id.iv_photo);
        et_address = getView(R.id.et_address);
        btn_next = getView(R.id.btn_next);
        text_start.setOnClickListener(this);
        text_end.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        rl_photo.setOnClickListener(this);
        title_text_tv.setText("添加门店");
        title_left_btn.setOnClickListener(this);
        rl_tabs.setOnClickListener(this);
    }


    @Override
    protected void initData() {
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


        initLocation();
    }


    private int type = 0;

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.text_start:
                type = 1;
                pvTime1.show();
                break;
            case R.id.text_end:
                type = 2;
                pvTime1.show();
                break;
            case R.id.rl_photo:
                requestPermission(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
                break;
            case R.id.btn_next:
                checkData();
                break;
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.rl_tabs:
                Intent intent = new Intent(this, LocationActivity.class);
                startActivityForResult(intent, 100);
                break;

        }
    }

    String city, area, province;
    double lat, lon;

    @Override
    protected void onResume() {
        super.onResume();
        String name = PreferenceUtils.getPrefString(this, "name", "");
        String lat1 = PreferenceUtils.getPrefString(this, "lat", "");
        String lon1 = PreferenceUtils.getPrefString(this, "lon", "");
        if (!Utility.isEmpty(PreferenceUtils.getPrefString(this, "provider", ""))) {
            city = PreferenceUtils.getPrefString(this, "city", "");
            area = PreferenceUtils.getPrefString(this, "district", "");
            province = PreferenceUtils.getPrefString(this, "provider", "");
        }
        if (!Utility.isEmpty(name) && !Utility.isEmpty(lat1)) {
            et_address.setText(name + "");
            LatLng latLng = SystemTools.getLatLng(Double.parseDouble(lat1), Double.parseDouble(lon1), this);
            lat = BigDecimalUtils.subLastBit(latLng.latitude, 6).doubleValue();
            lon = BigDecimalUtils.subLastBit(latLng.longitude, 6).doubleValue();
            PreferenceUtils.setPrefString(this, "name", "");
            PreferenceUtils.setPrefString(this, "lat", "");
            PreferenceUtils.setPrefString(this, "lon", "");
        }
    }

    private void checkData() {
        String name = et_cardNmae.getText().toString();
        String contacts = et_contacts.getText().toString();
        String phone = et_phone.getText().toString();
        String start = text_start.getText().toString();
        String end = text_end.getText().toString().trim();
        String address = et_address.getText().toString().trim();

        String brand = et_brand.getText().toString();
        String work = et_work.getText().toString();
        String mobile = et_mobile.getText().toString();

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
        } else if (Utility.isEmpty(mobile)) {
            ToastUtil.showToast("救援电话不能为空");
        } else if (Utility.isEmpty(result)) {
            ToastUtil.showToast("企业门头照不能为空");
        } else if (Utility.isEmpty(province)) {
            ToastUtil.showToast("省市区不能为空");
        } else {
            String sign = "address=" + address;
            if (!Utility.isEmpty(area)) {
                sign = sign + "&area=" + area;
            }
            sign = sign + "&brandName=" + brand + "&businessScope=" + work + "&city=" + city + "&contactPerson=" + contacts
                    + "&lat=" + lat + "&lng=" + lon + "&logo=" + result + "&memberId=" + SaveUtils.getSaveInfo().getId() + "&name=" + name + "&operTime=" + start + "-" + end
                    + "&partnerid=" + Constants.PARTNERID + "&phone=" + phone + "&province=" + province + "&rescuePhone=" + mobile
                    + Constants.SECREKEY;
            showProgressDialog(this, false);
            Map<String, String> params = okHttpModel.getParams();
            params.put("apptype", Constants.TYPE);
            if (!Utility.isEmpty(area)){
                params.put("area", area + "");
            }

            params.put("address", address + "");
            params.put("brandName", brand);
            params.put("businessScope", work);
            params.put("city", city + "");
            params.put("contactPerson", contacts);

            params.put("lat", lat + "");
            params.put("lng", lon + "");
            params.put("logo", result + "");
            params.put("memberId", SaveUtils.getSaveInfo().getId() + "");
            params.put("name", name);
            params.put("operTime", start + "-" + end);
            params.put("partnerid", Constants.PARTNERID);
            params.put("phone", phone);
            params.put("province", province + "");
            params.put("rescuePhone", mobile);

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
                        String name = et_cardNmae.getText().toString();
                        String contacts = et_contacts.getText().toString();
                        String phone = et_phone.getText().toString();
                        String storeId = object.optString("result");
                        StoreInfo storeInfo = new StoreInfo();
                        storeInfo.setId(storeId);
                        storeInfo.setPhone(phone);
                        storeInfo.setName(name);
                        storeInfo.setContactPerson(contacts);
                        Intent intent = new Intent(StoreListActivity.this, LegalActivity.class);
                        intent.putExtra("info", storeInfo);
                        startActivity(intent);
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
                        result = jsonObject.optString("result");
                        iv_photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        GlideUtils.CreateImageRound(result, iv_photo, 5);
                        text_cover.setVisibility(View.GONE);
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
                ToastUtil.showToast(response.getException().getMessage() + "");
            }
        });
    }


    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    private void initLocation() {
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = getDefaultOption();
        locationClient.setLocationListener(locationListener);
        locationClient.startLocation();
    }


    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location && !Utility.isEmpty(location.getProvince())) {
                province = location.getProvince();
                city = location.getCity();
                area = location.getDistrict();
                lat = location.getLatitude();
                lon = location.getLongitude();
                locationClient.stopLocation();
            }
        }
    };


    @Override
    protected void onPause() {
        super.onPause();
        PreferenceUtils.setPrefString(this, "name", "");
        PreferenceUtils.setPrefString(this, "lat", "");
        PreferenceUtils.setPrefString(this, "lon", "");
        PreferenceUtils.setPrefString(this, "city", "");
        PreferenceUtils.setPrefString(this, "district", "");
        PreferenceUtils.setPrefString(this, "provider", "");
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
