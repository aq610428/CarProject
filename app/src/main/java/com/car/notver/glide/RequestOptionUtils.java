package com.car.notver.glide;

import android.graphics.Color;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.car.notver.R;
import com.car.notver.base.BaseApplication;

/**
 * Created by Administrator on 2018/6/4.
 * glide:4.4.0
 */

public class RequestOptionUtils {
    /*******普通图片加载******/
    public static RequestOptions getRequestOption(int defaultDrawble) {
        RequestOptions options = new RequestOptions()
                .error(defaultDrawble)
                .placeholder(R.mipmap.mp_chat_goods_card_default_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(defaultDrawble);
        return options;
    }

    /*******普通图片加载******/
    public static RequestOptions getRequestOption() {
        RequestOptions options = new RequestOptions()
                .error(R.mipmap.mp_chat_goods_card_default_img)
                .dontAnimate()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.mp_chat_goods_card_default_img);
        return options;
    }

    /*******普通圆形图片加载******/
    public static RequestOptions getRequestOptionCircle(int defaultDrawble) {
        RequestOptions options = new RequestOptions()
                .circleCrop()
                .placeholder(R.mipmap.mp_chat_goods_card_default_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        return options;
    }


    /*******圆角******/
    public static RequestOptions getCircleTransformRound(int radius) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .fitCenter()
                .placeholder(R.mipmap.mp_chat_goods_card_default_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .transform(new GlideRoundTransform(BaseApplication.getContext(), radius));
        return options;
    }

    /*******高斯模糊******/
    public static RequestOptions getBlurTransformation() {
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transforms(new CircleTransform(BaseApplication.getContext(), 2, Color.DKGRAY))//外圈宽度，外圈颜色
                .error(R.mipmap.mp_chat_goods_card_default_img)
                .placeholder(R.mipmap.mp_chat_goods_card_default_img);
        return options;
    }

    /*******黑白******/
    public static RequestOptions getBlackWhiteTransformation() {
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transforms(new CircleTransform(BaseApplication.getContext(), 2, Color.DKGRAY))//外圈宽度，外圈颜色
                .transforms(new BlackWhiteTransformation())
                .transforms(new BlurTransformation(BaseApplication.getContext(), 25), new CircleTransform(BaseApplication.getContext(), 2, Color.DKGRAY))
                .error(R.mipmap.mp_chat_goods_card_default_img)
                .placeholder(R.mipmap.mp_chat_goods_card_default_img);
        return options;
    }
}
