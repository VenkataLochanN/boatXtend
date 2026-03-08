package com.ido.life.viewholder.shortcut;

import android.view.View;
import com.boat.Xtend.two.R;
import com.ido.life.adapter.ShortcutAppEditAdapter;
import com.ido.life.bean.ShortcutAppData;
import com.ido.life.viewholder.shortcut.annotation.ShortcutAppBigLayout;
import com.ido.life.viewholder.shortcut.annotation.ShortcutAppUnique;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SleepShortcutAppViewHolder.kt */
/* JADX INFO: loaded from: classes3.dex */
@ShortcutAppUnique({8})
@ShortcutAppBigLayout(layoutId = R.layout.item_shortcut_app_sleep_big)
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/ido/life/viewholder/shortcut/SleepShortcutAppViewHolder;", "Lcom/ido/life/viewholder/shortcut/BaseShortcutAppViewHolder;", "mAdapter", "Lcom/ido/life/adapter/ShortcutAppEditAdapter;", "itemView", "Landroid/view/View;", "(Lcom/ido/life/adapter/ShortcutAppEditAdapter;Landroid/view/View;)V", "onBind", "", "shortcutAppData", "Lcom/ido/life/bean/ShortcutAppData;", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SleepShortcutAppViewHolder extends BaseShortcutAppViewHolder {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SleepShortcutAppViewHolder(ShortcutAppEditAdapter mAdapter, View itemView) {
        super(mAdapter, itemView);
        Intrinsics.checkParameterIsNotNull(mAdapter, "mAdapter");
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
    }

    @Override // com.ido.life.viewholder.shortcut.BaseShortcutAppViewHolder
    public void onBind(ShortcutAppData shortcutAppData) {
        super.onBind(shortcutAppData);
    }
}