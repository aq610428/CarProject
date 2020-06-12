package com.car.notver.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.car.notver.R;
import com.car.notver.adapter.PhotoAdapter;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.bean.PhotoInfo;
import com.car.notver.util.ImageFactory;
import com.car.notver.util.LogUtils;
import com.car.notver.weight.FinishActivityManager;
import com.car.notver.weight.MediaLoader;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zt
 * @date: 2020/5/15
 * @name:企业照片
 */
public class BusinessPhotoActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private PhotoAdapter adapter;
    private List<PhotoInfo> data = new ArrayList<>();
    private TextView title_text_tv, title_left_btn;
    private int position;
    private Button btn_next;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_business);
        BaseApplication.activityTaskManager.putActivity("BusinessPhotoActivity",this);
    }

    @Override
    protected void initView() {
        btn_next = getView(R.id.btn_next);
        recyclerView = getView(R.id.recyclerView);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        title_text_tv.setText("企业形象照预览");
        btn_next.setOnClickListener(this);
    }


    @Override
    protected void initData() {
        data.add(new PhotoInfo("企业门牌照", "清晰醒目的门牌照，车主一定 过目不忘", "0"));
        data.add(new PhotoInfo("企业整体外观照", "着装统一的员工，更加展示企 业形象", "1"));
        data.add(new PhotoInfo("企业内部环境照", "干净整洁的内部环境，为企业 大大加分", "2"));
        data.add(new PhotoInfo("维修工时收费标准", "价格透明合理，车主选择放心", "3"));
        data.add(new PhotoInfo("救援工具照", "现场及时维修救援，值得车主 信赖", "4"));

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adapter = new PhotoAdapter(this, data);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((parent, view, position, id) -> {
            requestPermission(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
            this.position=position;
        });
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
                             data.get(position).setBitmap(bitmap);
                             adapter.notifyItemChanged(position);
                        }
                    }
                })
                .onCancel(result -> LogUtils.e("已"))
                .start();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.btn_next:
               startActivity(new Intent(this,PreviewActivity.class));
                break;
        }
    }
}
