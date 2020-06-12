package com.car.notver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.car.notver.R;
import com.car.notver.base.BaseActivity;
import com.car.notver.base.BaseApplication;
import com.car.notver.util.ClipboardUtils;
import com.car.notver.util.ToastUtil;
import com.car.notver.util.Utility;
import com.car.notver.weight.FinishActivityManager;

/**
 * @author: zt
 * @date: 2020/5/27
 * @name:社群
 */
public class CommunityActivity extends BaseActivity {
    private TextView title_text_tv, title_left_btn;
    private TextView text_edition, text_qq, text_wechat, text_official;
    private TextView text_group, text_keep, text_ex, text_coffiial;
    private WebView mWebView;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_community);
        BaseApplication.activityTaskManager.putActivity("CommunityActivity",this);
    }

    @Override
    protected void initView() {
        mWebView = getView(R.id.mWebView);
        text_group = getView(R.id.text_group);
        text_keep = getView(R.id.text_keep);
        text_ex = getView(R.id.text_ex);
        text_coffiial = getView(R.id.text_coffiial);


        text_qq = getView(R.id.text_qq);
        text_wechat = getView(R.id.text_wechat);
        text_official = getView(R.id.text_official);
        text_edition = getView(R.id.text_edition);
        title_text_tv = getView(R.id.title_text_tv);
        title_left_btn = getView(R.id.title_left_btn);
        title_left_btn.setOnClickListener(this);
        text_edition.setOnClickListener(this);
        text_qq.setOnClickListener(this);
        text_wechat.setOnClickListener(this);
        text_official.setOnClickListener(this);

        String url = getIntent().getStringExtra("url");
        if (!Utility.isEmpty(url)){
            mWebView.loadUrl(url);
            title_text_tv.setText(getIntent().getStringExtra("name"));
        }else{
            title_text_tv.setText("加入社群");
            mWebView.loadUrl("http://openapi.jkabe.com/golo/about");
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_left_btn:
                finish();
                break;
            case R.id.text_edition:
                ClipboardUtils.copyText(text_group.getText().toString());
                ToastUtil.showToast("已复制成功");
                break;
            case R.id.text_qq:
                ClipboardUtils.copyText(text_keep.getText().toString());
                ToastUtil.showToast("已复制成功");
                break;
            case R.id.text_wechat:
                ClipboardUtils.copyText(text_ex.getText().toString());
                ToastUtil.showToast("已复制成功");
                break;
            case R.id.text_official:
                ClipboardUtils.copyText(text_coffiial.getText().toString());
                ToastUtil.showToast("已复制成功");
                break;
        }
    }


    @Override
    protected void initData() {

    }
}
