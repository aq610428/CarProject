package com.car.notver.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.car.notver.R;
import com.car.notver.adapter.PhotoNewAdapter;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.CommonalityModel;
import com.car.notver.bean.FileInfo;
import com.car.notver.bean.StoreInfo;
import com.car.notver.config.Api;
import com.car.notver.config.NetWorkListener;
import com.car.notver.config.okHttpModel;
import com.car.notver.util.Constants;
import com.car.notver.util.JsonParse;
import com.car.notver.util.LogUtils;
import com.car.notver.util.Md5Util;
import com.car.notver.util.SystemTools;
import com.car.notver.util.ToastUtil;
import com.car.notver.util.Utility;
import com.car.notver.weight.FinishActivityManager;
import com.car.notver.weight.MediaLoader;
import com.car.notver.weight.NoDataView;
import com.car.notver.weight.SpaceItemDecoration;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * @author: zt
 * @date: 2020/5/29
 * @name:门店相册
 */
public class PhotoActivity extends BaseActivity implements NetWorkListener {
    private TextView title_text_tv, title_left_btn, title_right_tv;
    private RecyclerView recyclerView;
    private PhotoNewAdapter adapter;
    private StoreInfo info;
    private List<FileInfo> infoList = new ArrayList<>();
    private int limit = 10;
    private int page = 1;
    private NoDataView mNoDataView;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_photo);
        BaseApplication.activityTaskManager.putActivity("PhotoActivity", this);
    }

    @Override
    protected void initView() {
        mNoDataView = getView(R.id.mNoDataView);
        title_right_tv = getView(R.id.title_right_tv);
        recyclerView = getView(R.id.recyclerView);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_right_tv.setOnClickListener(this);
        title_text_tv.setText("门店相册");
        title_right_tv.setText("新增照片");
        mNoDataView.textView.setText("您还没有添加门店照片~");
    }

    @Override
    protected void initData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        info = (StoreInfo) getIntent().getSerializableExtra("info");
        if (info != null) {
            qury();
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.title_right_tv:
                requestPermission(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
                break;
        }
    }


    private void qury() {
        String sign = "partnerid=" + Constants.PARTNERID + "&storeId=" + info.getId() + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("storeId", info.getId() + "");
        params.put("limit", limit + "");
        params.put("page", page + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_IMAGE, params, Api.GET_IMAGE_ID, this);
    }

    /*****删除门店图片*****/
    public void quryDel(String id) {
        String sign = "id=" + id + "&partnerid=" + Constants.PARTNERID + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("id", id + "");
        params.put("partnerid", Constants.PARTNERID);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_REMOVE, params, Api.GET_REMOVE_ID, this);
    }


    /*****新增门店图片*****/
    private void quryList() {
        String sign = "partnerid=" + Constants.PARTNERID + "&photoFile=" + result + "&sort=" + num + "&status=2&storeId=" + info.getId() + "&title=" + title + Constants.SECREKEY;
        showProgressDialog(this, false);
        Map<String, String> params = okHttpModel.getParams();
        params.put("apptype", Constants.TYPE);
        params.put("partnerid", Constants.PARTNERID);
        params.put("photoFile", result + "");
        params.put("sort", num);
        params.put("status", "2");
        params.put("storeId", info.getId() + "");
        params.put("title", title);
        params.put("sign", Md5Util.encode(sign));
        okHttpModel.get(Api.GET_IMAGEINFO, params, Api.GET_IMAGEINFO_ID, this);
    }


    @Override
    public void onSucceed(JSONObject object, int id, CommonalityModel commonality) {
        if (object != null && commonality != null && !Utility.isEmpty(commonality.getStatusCode())) {
            if (Constants.SUCESSCODE.equals(commonality.getStatusCode())) {
                switch (id) {
                    case Api.GET_IMAGE_ID:
                        infoList = JsonParse.getStoreFileJson(object);
                        if (infoList != null && infoList.size() > 0) {
                            mNoDataView.setVisibility(View.GONE);
                            setAdapter();
                        } else {
                            mNoDataView.setVisibility(View.VISIBLE);
                        }
                        break;
                    case Api.GET_IMAGEINFO_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        qury();
                        break;
                    case Api.GET_REMOVE_ID:
                        ToastUtil.showToast(commonality.getErrorDesc());
                        qury();
                        break;
                }
            } else {
                ToastUtil.showToast(commonality.getErrorDesc());
            }
        }
        stopProgressDialog();
    }

    private void setAdapter() {
        recyclerView.addItemDecoration(new SpaceItemDecoration(20, 2));
        adapter = new PhotoNewAdapter(this, infoList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PhotoActivity.this, ListPhotoActivity.class);
                intent.putExtra("infoList", (Serializable) infoList);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
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
                .columnCount(4)
                .selectCount(1)
                .checkedList(albumFiles)
                .onResult(result -> {
                    if (result != null && result.size() > 0) {
                        String path = result.get(0).getPath();
                        LogUtils.e("path=" + path);
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

    String result;

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
                        showDialog();
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

    private String title, num;

    public void showDialog() {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_photo, null);
        EditText et_name = view.findViewById(R.id.et_name);
        EditText et_num = view.findViewById(R.id.et_num);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = et_name.getText().toString();
                num = et_num.getText().toString();
                if (Utility.isEmpty(title)) {
                    ToastUtil.showToast("请输入照片描述");
                    return;
                }

                if (Utility.isEmpty(num)) {
                    ToastUtil.showToast("请输入照片编号");
                    return;
                }
                quryList();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public void onFail() {

    }

    @Override
    public void onError(Exception e) {

    }
}
