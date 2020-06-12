package com.car.notver.ocr;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * 绑定了车牌号键盘的输入框(EditText)
 *
 * @author Relish Wang
 * @since 2017/09/21
 */
public class VehicleEditText extends AppCompatEditText {

    public VehicleEditText(Context context) {
        super(context);
        init(context);
    }

    public VehicleEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VehicleEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        VehicleKeyboardHelper.bind(this, new VehicleKeyboardView(getContext()),context);
    }

    OnTouchListener mToucheListener;
    OnFocusChangeListener mFocusChangeListener;
    OnKeyListener mKeyListener;

    public void setOnTouchListener2(OnTouchListener l) {
        mToucheListener = l;
    }

    public void setOnFocusChangeListener2(OnFocusChangeListener l) {
        mFocusChangeListener = l;
    }

    public void setOnKeyListener2(OnKeyListener l) {
        mKeyListener = l;
    }


}
