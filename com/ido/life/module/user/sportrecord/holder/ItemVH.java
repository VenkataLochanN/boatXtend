package com.ido.life.module.user.sportrecord.holder;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ItemVH extends RecyclerView.ViewHolder {
    public abstract int type();

    public ItemVH(View view) {
        super(view);
    }
}