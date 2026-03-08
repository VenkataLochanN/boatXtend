package com.lzy.imagepicker.util;

import androidx.recyclerview.widget.DiffUtil;
import com.lzy.imagepicker.bean.ImageItem;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class DiffCallback extends DiffUtil.Callback {
    private final List<ImageItem> mNewList;
    private final List<ImageItem> mOldList;

    public DiffCallback(List<ImageItem> list, List<ImageItem> list2) {
        this.mOldList = list;
        this.mNewList = list2;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public int getOldListSize() {
        List<ImageItem> list = this.mOldList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public int getNewListSize() {
        List<ImageItem> list = this.mNewList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areItemsTheSame(int i, int i2) {
        List<ImageItem> list;
        List<ImageItem> list2 = this.mOldList;
        if (list2 == null || list2.isEmpty() || (list = this.mNewList) == null || list.isEmpty()) {
            return false;
        }
        return this.mOldList.get(i).equals(this.mNewList.get(i2));
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areContentsTheSame(int i, int i2) {
        List<ImageItem> list;
        List<ImageItem> list2 = this.mOldList;
        if (list2 == null || list2.isEmpty() || (list = this.mNewList) == null || list.isEmpty()) {
            return false;
        }
        return this.mOldList.get(i).equals(this.mNewList.get(i2));
    }
}