package com.ido.life.customview.recyclerview;

import androidx.collection.SparseArrayCompat;

/* JADX INFO: loaded from: classes2.dex */
public class ItemViewDelegateManagerForRV<T> {
    SparseArrayCompat<ItemViewDelegateForRV<T>> delegates = new SparseArrayCompat<>();

    public int getItemViewDelegateCount() {
        return this.delegates.size();
    }

    public ItemViewDelegateManagerForRV<T> addDelegate(int i, ItemViewDelegateForRV<T> itemViewDelegateForRV) {
        if (this.delegates.get(i) != null) {
            throw new IllegalArgumentException("An ItemViewDelegateForRV is already registered for the viewType = " + i + ". Already registered ItemViewDelegateForRV is " + this.delegates.get(i));
        }
        this.delegates.put(i, itemViewDelegateForRV);
        return this;
    }

    public ItemViewDelegateManagerForRV<T> removeDelegate(ItemViewDelegateForRV<T> itemViewDelegateForRV) {
        if (itemViewDelegateForRV == null) {
            throw new NullPointerException("ItemViewDelegateForRV is null");
        }
        int iIndexOfValue = this.delegates.indexOfValue(itemViewDelegateForRV);
        if (iIndexOfValue >= 0) {
            this.delegates.removeAt(iIndexOfValue);
        }
        return this;
    }

    public ItemViewDelegateManagerForRV<T> removeDelegate(int i) {
        int iIndexOfKey = this.delegates.indexOfKey(i);
        if (iIndexOfKey >= 0) {
            this.delegates.removeAt(iIndexOfKey);
        }
        return this;
    }

    public int getItemViewType(T t, int i) {
        for (int size = this.delegates.size() - 1; size >= 0; size--) {
            if (this.delegates.valueAt(size).getViewType(t, i) == this.delegates.keyAt(size)) {
                return this.delegates.keyAt(size);
            }
        }
        throw new IllegalArgumentException("No ItemViewDelegateForRV added that matches position=" + i + " in data source");
    }

    public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, T t, int i) {
        int size = this.delegates.size();
        for (int i2 = 0; i2 < size; i2++) {
            ItemViewDelegateForRV<T> itemViewDelegateForRVValueAt = this.delegates.valueAt(i2);
            if (itemViewDelegateForRVValueAt.getViewType(t, i) == this.delegates.keyAt(i2)) {
                itemViewDelegateForRVValueAt.convert(commonRecyclerViewHolder, t, i);
                return;
            }
        }
        throw new IllegalArgumentException("No ItemViewDelegateManagerForRV added that matches position=" + i + " in data source");
    }

    public ItemViewDelegateForRV getItemViewDelegate(int i) {
        return this.delegates.get(i);
    }

    public int getItemViewLayoutId(int i) {
        return getItemViewDelegate(i).getItemViewLayoutId();
    }

    public int getItemViewType(ItemViewDelegateForRV itemViewDelegateForRV) {
        return this.delegates.indexOfValue(itemViewDelegateForRV);
    }
}