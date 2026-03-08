package com.ido.life.viewholder.shortcut;

import android.view.View;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.life.adapter.ShortcutAppEditAdapter;
import com.ido.life.bean.ShortcutAppData;
import com.ido.life.data.cache.ShortcutAppManager;
import com.ido.life.viewholder.shortcut.annotation.ShortcutAppSmallLayout;
import com.ido.life.viewholder.shortcut.annotation.ShortcutAppUnique;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NormalSmallShortcutAppViewHolder.kt */
/* JADX INFO: loaded from: classes3.dex */
@ShortcutAppUnique({2, 15, 10, 11, 12, 16, 13, 17, 9, 18})
@ShortcutAppSmallLayout(layoutId = R.layout.item_shortcut_app_normal_small)
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\f¨\u0006\u0014"}, d2 = {"Lcom/ido/life/viewholder/shortcut/NormalSmallShortcutAppViewHolder;", "Lcom/ido/life/viewholder/shortcut/BaseShortcutAppViewHolder;", "mAdapter", "Lcom/ido/life/adapter/ShortcutAppEditAdapter;", "itemView", "Landroid/view/View;", "(Lcom/ido/life/adapter/ShortcutAppEditAdapter;Landroid/view/View;)V", "tvContent", "Landroid/widget/TextView;", "getTvContent", "()Landroid/widget/TextView;", "setTvContent", "(Landroid/widget/TextView;)V", "tvDesc", "getTvDesc", "setTvDesc", "onBind", "", "shortcutAppData", "Lcom/ido/life/bean/ShortcutAppData;", "app_release"}, k = 1, mv = {1, 1, 16})
public final class NormalSmallShortcutAppViewHolder extends BaseShortcutAppViewHolder {
    private TextView tvContent;
    private TextView tvDesc;

    public final TextView getTvContent() {
        return this.tvContent;
    }

    public final void setTvContent(TextView textView) {
        this.tvContent = textView;
    }

    public final TextView getTvDesc() {
        return this.tvDesc;
    }

    public final void setTvDesc(TextView textView) {
        this.tvDesc = textView;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NormalSmallShortcutAppViewHolder(ShortcutAppEditAdapter mAdapter, View itemView) {
        super(mAdapter, itemView);
        Intrinsics.checkParameterIsNotNull(mAdapter, "mAdapter");
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        this.tvContent = (TextView) itemView.findViewById(R.id.tvContent);
        this.tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
    }

    @Override // com.ido.life.viewholder.shortcut.BaseShortcutAppViewHolder
    public void onBind(ShortcutAppData shortcutAppData) {
        super.onBind(shortcutAppData);
        if (shortcutAppData != null) {
            Pair<String, String> shortcutAppContent = ShortcutAppManager.INSTANCE.getInstance().getShortcutAppContent(shortcutAppData.getWidgets_type());
            TextView textView = this.tvContent;
            if (textView != null) {
                textView.setText(shortcutAppContent.getFirst());
            }
            TextView textView2 = this.tvDesc;
            if (textView2 != null) {
                textView2.setText(shortcutAppContent.getSecond());
                return;
            }
            return;
        }
        TextView textView3 = this.tvContent;
        if (textView3 != null) {
            textView3.setText("");
        }
        TextView textView4 = this.tvDesc;
        if (textView4 != null) {
            textView4.setText("");
        }
    }
}