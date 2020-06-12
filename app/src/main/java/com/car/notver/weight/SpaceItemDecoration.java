package com.car.notver.weight;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.car.notver.util.DensityUtils;

/**
 * @author: zt
 * @date: 2020/5/20
 * @name:SpaceItemDecoration
 */
public class SpaceItemDecoration  extends RecyclerView.ItemDecoration {

    private final int column;
    private final int space;

    public SpaceItemDecoration(int space, int column) {
        this.space = space;
        this.column = column;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // 第一列左边贴边、后边列项依次移动一个space和前一项移动的距离之和
        int mod = parent.getChildAdapterPosition(view) % column;
        outRect.left = space * mod;
    }


}
