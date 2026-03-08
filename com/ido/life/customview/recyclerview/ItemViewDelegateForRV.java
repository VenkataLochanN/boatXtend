package com.ido.life.customview.recyclerview;

/* JADX INFO: loaded from: classes2.dex */
public interface ItemViewDelegateForRV<T> {
    void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, T t, int i);

    int getItemViewLayoutId();

    int getViewType(T t, int i);
}