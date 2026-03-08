package com.ido.life.customview.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public class BaseGridLayoutManager extends GridLayoutManager {
    public BaseGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public BaseGridLayoutManager(Context context, int i) {
        super(context, i);
    }

    public BaseGridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i, i2, z);
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}