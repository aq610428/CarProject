package com.car.notver.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.util.ImageFactory;
import com.car.notver.util.LogUtils;
import com.car.notver.weight.FinishActivityManager;
import com.car.notver.weight.MediaLoader;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import java.util.ArrayList;

/**
 * @author: zt
 * @date: 2020/5/15
 * @name:账户信息
 */
public class AccountActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn;
    private RelativeLayout rl_photo;
    private ImageView iv_photo;
    private Button btn_next;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_account);
        BaseApplication.activityTaskManager.putActivity("AccountActivity",this);
    }

    @Override
    protected void initView() {
        btn_next = getView(R.id.btn_next);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        rl_photo = getView(R.id.rl_photo);
        iv_photo = getView(R.id.iv_photo);
        title_left_btn.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        rl_photo.setOnClickListener(this);
        title_text_tv.setText("账户信息");
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.rl_photo:
                requestPermission(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
                break;
            case R.id.btn_next:
                startActivity(new Intent(this, IndustryActivity.class));
                break;
        }
    }

    @Override
    public void permissinSucceed(int code) {
        super.permissinSucceed(code);
        selectPhoto();
    }


    private Bitmap bitmap;
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
                        bitmap = ImageFactory.getBitmap(path);
                        if (bitmap != null) {
                            iv_photo.setImageBitmap(bitmap);
                        }
                    }
                })
                .onCancel(result -> LogUtils.e("已"))
                .start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
    }
}
