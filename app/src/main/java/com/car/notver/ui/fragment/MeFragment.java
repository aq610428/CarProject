package com.car.notver.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.car.notver.R;
import com.car.notver.base.BaseApplication;
import com.car.notver.base.BaseFragment;
import com.car.notver.bean.UserInfo;
import com.car.notver.ui.activity.AboutActivity;
import com.car.notver.ui.activity.AddClientActivity;
import com.car.notver.ui.activity.AdministrationActivity;
import com.car.notver.ui.activity.CommunityActivity;
import com.car.notver.ui.activity.ForgetActivity;
import com.car.notver.ui.activity.HelpActivity;
import com.car.notver.ui.activity.LoginActivity;
import com.car.notver.ui.activity.MassageActivity;
import com.car.notver.ui.activity.StoreActivity;
import com.car.notver.util.SaveUtils;
import crossoverone.statuslib.StatusUtil;


/****
 *
 * 我的
 *
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private boolean isPrepared;
    private TextView text_join, text_rescue, text_personne, text_me, text_community, text_help, text_means;
    private Button btn_next;
    private UserInfo info;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_me, container, false);
            initView();
            isPrepared = true;
            lazyLoad();
        }
        return rootView;
    }

    private void initView() {
        text_means = getView(rootView, R.id.text_means);
        text_rescue = getView(rootView, R.id.text_rescue);
        text_help = getView(rootView, R.id.text_help);
        text_community = getView(rootView, R.id.text_community);
        text_me = getView(rootView, R.id.text_me);
        text_personne = rootView.findViewById(R.id.text_personne);
        btn_next = rootView.findViewById(R.id.btn_next);
        text_join = rootView.findViewById(R.id.text_join);
        text_join.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        text_me.setOnClickListener(this);
        text_personne.setOnClickListener(this);
        text_community.setOnClickListener(this);
        text_help.setOnClickListener(this);
        text_rescue.setOnClickListener(this);
        text_means.setOnClickListener(this);
    }


    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusUtil.setUseStatusBarColor(getActivity(), Color.parseColor("#FFFFFF"));
        StatusUtil.setSystemStatus(getActivity(), false, true);
        info = SaveUtils.getSaveInfo();
        if (info != null) {
            btn_next.setText("退出登录");
        } else {
            btn_next.setText("登录");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_means:
                Intent intent = new Intent(getContext(), ForgetActivity.class);
                intent.putExtra("name", "重置密码");
                startActivity(intent);
                break;
            case R.id.text_rescue:
                startActivity(new Intent(getContext(), MassageActivity.class));
                break;

            case R.id.text_join:
                startActivity(new Intent(getContext(), StoreActivity.class));
                break;
            case R.id.btn_next:
                SaveUtils.clealCacheDisk();
                BaseApplication.activityTaskManager.closeAllActivityExceptOne("LoginActivity");
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
                break;
            case R.id.text_add:
                startActivity(new Intent(getContext(), AddClientActivity.class));
                break;
            case R.id.text_personne:
                startActivity(new Intent(getContext(), AdministrationActivity.class));
                break;
            case R.id.text_me:
                startActivity(new Intent(getContext(), AboutActivity.class));
                break;
            case R.id.text_community:
                startActivity(new Intent(getContext(), CommunityActivity.class));
                break;
            case R.id.text_help:
                startActivity(new Intent(getContext(), HelpActivity.class));
                break;


        }
    }
}
