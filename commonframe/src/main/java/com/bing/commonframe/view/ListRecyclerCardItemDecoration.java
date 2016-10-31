package com.bing.commonframe.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by bingo on 2016/10/26.
 */

public class ListRecyclerCardItemDecoration extends RecyclerView.ItemDecoration {

    public ListRecyclerCardItemDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(10, 10, 10, 10);
    }
}