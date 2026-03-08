package com.ido.life.customview.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CommonRecyclerViewAdapter<T> extends MultiItemTypeAdapterForRV<T> {
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected int mLayoutId;

    protected abstract void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, T t, int i);

    public CommonRecyclerViewAdapter(Context context, final int i, List<T> list) {
        super(context, list);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mLayoutId = i;
        this.mDatas = list == null ? new ArrayList<>() : list;
        addItemViewDelegate(0, new ItemViewDelegateForRV<T>() { // from class: com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter.1
            @Override // com.ido.life.customview.recyclerview.ItemViewDelegateForRV
            public int getViewType(T t, int i2) {
                return 0;
            }

            @Override // com.ido.life.customview.recyclerview.ItemViewDelegateForRV
            public int getItemViewLayoutId() {
                return i;
            }

            @Override // com.ido.life.customview.recyclerview.ItemViewDelegateForRV
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, T t, int i2) {
                CommonRecyclerViewAdapter.this.convert(commonRecyclerViewHolder, t, i2);
            }
        });
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV
    public void addAll(List<T> list) {
        this.mDatas.clear();
        if (list != null) {
            this.mDatas.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addMore(List<T> list) {
        this.mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public void remove(int i) {
        if (this.mDatas == null || this.mDatas.isEmpty() || this.mDatas.size() < i) {
            return;
        }
        this.mDatas.remove(i);
        notifyDataSetChanged();
    }

    public void setData(List<T> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.mDatas = list;
        notifyDataSetChanged();
    }
}