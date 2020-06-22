package com.car.notver.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.car.notver.R;


public class NoView extends RelativeLayout {
    public TextView textView,text_refresh;


    public NoView(Context context) {
        super(context);
        init();
    }

    public NoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.no_view, this);
        textView = view.findViewById(R.id.text_name);
    }

    public void setData(String data) {
        textView.setText(data + "");
    }
}
