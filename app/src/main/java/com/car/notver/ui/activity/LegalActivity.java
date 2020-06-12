package com.car.notver.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.StoreInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.glide.GlideUtils;
import com.car.notver.util.Constants;
import com.car.notver.util.LogUtils;
import com.car.notver.util.Md5Util;
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
import java.util.Map;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * @author: zt
 * @date: 2020/5/15
 * @name:法人身份信息
 */
public class LegalActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn;
    private RelativeLayout rl_tab1, rl_tab2, rl_tab3;
    private ImageView iv_card, iv_photo, iv_license;
    private Button btn_next;
    private TextView tv_tab1, tv_tab2, tv_tab3;
    private StoreInfo info;
    private String photoId;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_legal);
        BaseApplication.activityTaskManager.putActivity("LegalActivity", this);
    }

    @Override
    protected void initView() {
        tv_tab1 = getView(R.id.tv_tab1);
        tv_tab2 = getView(R.id.tv_tab2);
        tv_tab3 = getView(R.id.tv_tab3);
        iv_license = getView(R.id.iv_license);
        btn_next = getView(R.id.btn_next);
        iv_photo = getView(R.id.iv_photo);
        iv_card = getView(R.id.iv_card);
        rl_tab2 = getView(R.id.rl_tab2);
        rl_tab1 = getView(R.id.rl_tab1);
        rl_tab3 = getView(R.id.rl_tab3);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        rl_tab1.setOnClickListener(this);
        rl_tab2.setOnClickListener(this);
        rl_tab3.setOnClickListener(this);
        title_text_tv.setText("门店资质");
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.btn_next:
                checkData();
                break;
            case R.id.rl_tab1:
                requestPermission(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                break;
            case R.id.rl_tab2:
                requestPermission(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
                break;
            case R.id.rl_tab3:
                requestPermission(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 4);
                break;
        }
    }


    @Override
    public void permissinSucceed(int code) {
        super.permissinSucceed(code);
        selectPhoto(code);
    }

    private void selectPhoto(int code) {
        Album.initialize(AlbumConfig.newBuilder(this)
                .setAlbumLoader(new MediaLoader())
                .build());
        ArrayList<AlbumFile> albumFiles = new ArrayList<>();
        Album.image(this) // Image selection.
                .multipleChoice()
                .camera(true)
                .columnCount(4)
                .selectCount(1)
                .checkedList(albumFiles)
                .onResult(result -> {
                    if (result != null && result.size() > 0) {
                        String path = result.get(0).getPath();
                        initLuban(path, code);
                    }
                })
                .onCancel(result -> LogUtils.e("已"))
                .start();
    }


    /*****图片压缩*****/
    private void initLuban(String path, int code) {
        Luban.with(this).load(new File(path)).ignoreBy(100)
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        if (file != null) {
                            upLoad(file, code);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();
    }


    @Override
    protected void initData() {
        info = (StoreInfo) getIntent().getSerializableExtra("info");
        if (info != null) {
            if (info.getStatus() == 3 || info.getStatus() == 2) {
                btn_next.setVisibility(View.GONE);
            } else {
                btn_next.setVisibility(View.VISIBLE);
            }
            queryList();
        }
    }

    String cardPic, reversePic, businessPic;

    private void checkData() {
        if (Utility.isEmpty(cardPic)) {
            ToastUtil.showToast("法人身份证不能为空");
        } else if (Utility.isEmpty(reversePic)) {
            ToastUtil.showToast("法人身份证不能为空");
        } else if (Utility.isEmpty(businessPic)) {
            ToastUtil.showToast("营业执照不能为空");
        } else {
            query();
        }
    }

    public void queryList() {
        String sign = "partnerid=" + Constants.PARTNERID + "&storeId=" + info.getId() + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        params.put("storeId", info.getId() + "");
        okHttpModel.get(Api.GET_REMOVE_STORE, params, Api.GET_REMOVE_STORE_ID, this);
    }


    public void query() {
        String sign = "enterpriseName=" + info.getName();
        if (!Utility.isEmpty(photoId)) {
            sign =sign+ "&id=" + photoId;
        }
        sign = sign+ "&idcardInfofile=" + cardPic + "&idcardMainfile=" + reversePic+ "&legalMobile=" + info.getPhone() + "&legalPerson=" + info.getContactPerson() + "&licenseFile=" + businessPic + "&partnerid=" + Constants.PARTNERID + "&storeId=" + info.getId() + Constants.SECREKEY;
        LogUtils.e("sign="+sign);
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        params.put("enterpriseName", info.getName() + "");
        if (!Utility.isEmpty(photoId)) {
            params.put("id", photoId);
        }
        params.put("idcardInfofile", cardPic);
        params.put("idcardMainfile", reversePic);
        params.put("legalMobile", info.getPhone() + "");
        params.put("legalPerson", info.getContactPerson() + "");
        params.put("licenseFile", businessPic);
        params.put("storeId", info.getId() + "");
        okHttpModel.get(Api.GET_PROPER, params, Api.GET_PROPER_ID, this);
    }

    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_PROPER_ID:
                        ToastUtil.showToast("修改成功");
                        BaseApplication.activityTaskManager.removeActivity("StoreDeilActivity");
                        finish();
                        break;
                    case Api.GET_REMOVE_STORE_ID:
                        JSONObject jsonObject = object.optJSONObject("result");
                        photoId = jsonObject.optString("id");
                        cardPic = jsonObject.optString("idcardInfofile");
                        reversePic = jsonObject.optString("idcardMainfile");
                        businessPic = jsonObject.optString("licenseFile");
                        if (!Utility.isEmpty(cardPic)) {
                            iv_card.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            tv_tab1.setVisibility(View.GONE);
                            GlideUtils.CreateImageRound(cardPic, iv_card, 5);
                            if (info.getStatus() == 2 || info.getStatus() == 3) {
                                rl_tab1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                            }
                        }
                        if (!Utility.isEmpty(reversePic)) {
                            tv_tab2.setVisibility(View.GONE);
                            iv_photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            GlideUtils.CreateImageRound(reversePic, iv_photo, 5);
                            if (info.getStatus() == 2 || info.getStatus() == 3) {
                                rl_tab2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                            }
                        }

                        if (!Utility.isEmpty(businessPic)) {
                            tv_tab3.setVisibility(View.GONE);
                            iv_license.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            GlideUtils.CreateImageRound(businessPic, iv_license, 5);
                            if (info.getStatus() == 2 || info.getStatus() == 3) {
                                rl_tab3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                            }

                        }
                        break;
                }
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


    private void upLoad(File file, int code) {
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
                        if (code == 2) {
                            cardPic = jsonObject.optString("result");
                            iv_card.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            GlideUtils.CreateImageRound(cardPic, iv_card, 5);
                            tv_tab1.setVisibility(View.GONE);
                        } else if (code == 3) {
                            tv_tab2.setVisibility(View.GONE);
                            reversePic = jsonObject.optString("result");
                            iv_photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            GlideUtils.CreateImageRound(reversePic, iv_photo, 5);
                        } else {
                            tv_tab3.setVisibility(View.GONE);
                            businessPic = jsonObject.optString("result");
                            iv_license.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            GlideUtils.CreateImageRound(businessPic, iv_license, 5);
                        }
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
