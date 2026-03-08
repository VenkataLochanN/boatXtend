package com.ido.life.module.home.chartdetail.vertical;

import android.view.View;
import butterknife.ButterKnife;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseDetailViewHolder {
    private View mItemView;

    public abstract void refreshLanguage();

    public abstract void setDefaultValue();

    public BaseDetailViewHolder(View view) {
        this.mItemView = view;
        ButterKnife.bind(this, view);
    }

    public View getItemView() {
        return this.mItemView;
    }
}