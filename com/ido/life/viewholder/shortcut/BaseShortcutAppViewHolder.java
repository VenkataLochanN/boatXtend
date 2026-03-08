package com.ido.life.viewholder.shortcut;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.adapter.ShortcutAppEditAdapter;
import com.ido.life.bean.ShortcutAppData;
import com.ido.life.data.cache.ShortcutAppManager;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseShortcutAppViewHolder.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006$"}, d2 = {"Lcom/ido/life/viewholder/shortcut/BaseShortcutAppViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "mAdapter", "Lcom/ido/life/adapter/ShortcutAppEditAdapter;", "itemView", "Landroid/view/View;", "(Lcom/ido/life/adapter/ShortcutAppEditAdapter;Landroid/view/View;)V", "ivAppIcon", "Landroid/widget/ImageView;", "getIvAppIcon", "()Landroid/widget/ImageView;", "setIvAppIcon", "(Landroid/widget/ImageView;)V", "ivDeleteShortcutApp", "getIvDeleteShortcutApp", "setIvDeleteShortcutApp", "llContainer", "Landroid/widget/LinearLayout;", "getLlContainer", "()Landroid/widget/LinearLayout;", "setLlContainer", "(Landroid/widget/LinearLayout;)V", "getMAdapter", "()Lcom/ido/life/adapter/ShortcutAppEditAdapter;", "setMAdapter", "(Lcom/ido/life/adapter/ShortcutAppEditAdapter;)V", "tvAppName", "Landroid/widget/TextView;", "getTvAppName", "()Landroid/widget/TextView;", "setTvAppName", "(Landroid/widget/TextView;)V", "onBind", "", "shortcutAppData", "Lcom/ido/life/bean/ShortcutAppData;", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class BaseShortcutAppViewHolder extends RecyclerView.ViewHolder {
    private ImageView ivAppIcon;
    private ImageView ivDeleteShortcutApp;
    private LinearLayout llContainer;
    private ShortcutAppEditAdapter mAdapter;
    private TextView tvAppName;

    public final ShortcutAppEditAdapter getMAdapter() {
        return this.mAdapter;
    }

    public final void setMAdapter(ShortcutAppEditAdapter shortcutAppEditAdapter) {
        Intrinsics.checkParameterIsNotNull(shortcutAppEditAdapter, "<set-?>");
        this.mAdapter = shortcutAppEditAdapter;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseShortcutAppViewHolder(ShortcutAppEditAdapter mAdapter, View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(mAdapter, "mAdapter");
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        this.mAdapter = mAdapter;
        this.ivAppIcon = (ImageView) itemView.findViewById(R.id.ivShortcutAppIcon);
        this.tvAppName = (TextView) itemView.findViewById(R.id.tvShortcutAppName);
        this.llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
        this.ivDeleteShortcutApp = (ImageView) itemView.findViewById(R.id.ivDeleteShortcutApp);
        ImageView imageView = this.ivDeleteShortcutApp;
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.viewholder.shortcut.BaseShortcutAppViewHolder.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ShortcutAppEditAdapter.OnItemDeleteClickListener onItemDeleteClickListener = BaseShortcutAppViewHolder.this.getMAdapter().getOnItemDeleteClickListener();
                    if (onItemDeleteClickListener != null) {
                        onItemDeleteClickListener.onItemDeleteClick(BaseShortcutAppViewHolder.this.getAdapterPosition());
                    }
                }
            });
        }
        itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.ido.life.viewholder.shortcut.BaseShortcutAppViewHolder.2
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                if (BaseShortcutAppViewHolder.this.getMAdapter().getIsEdit()) {
                    return false;
                }
                ShortcutAppEditAdapter.OnItemLongClickListener onItemLongClickListener = BaseShortcutAppViewHolder.this.getMAdapter().getOnItemLongClickListener();
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClick(BaseShortcutAppViewHolder.this.getAdapterPosition());
                }
                return true;
            }
        });
    }

    public final ImageView getIvAppIcon() {
        return this.ivAppIcon;
    }

    public final void setIvAppIcon(ImageView imageView) {
        this.ivAppIcon = imageView;
    }

    public final TextView getTvAppName() {
        return this.tvAppName;
    }

    public final void setTvAppName(TextView textView) {
        this.tvAppName = textView;
    }

    public final LinearLayout getLlContainer() {
        return this.llContainer;
    }

    public final void setLlContainer(LinearLayout linearLayout) {
        this.llContainer = linearLayout;
    }

    public final ImageView getIvDeleteShortcutApp() {
        return this.ivDeleteShortcutApp;
    }

    public final void setIvDeleteShortcutApp(ImageView imageView) {
        this.ivDeleteShortcutApp = imageView;
    }

    public void onBind(ShortcutAppData shortcutAppData) {
        ViewGroup.LayoutParams layoutParams;
        if (shortcutAppData != null) {
            ImageView imageView = this.ivAppIcon;
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            TextView textView = this.tvAppName;
            if (textView != null) {
                textView.setVisibility(0);
            }
            Pair<Integer, Integer> shortcutAppSampleInfo = ShortcutAppManager.INSTANCE.getInstance().getShortcutAppSampleInfo(shortcutAppData.getWidgets_type());
            ImageView imageView2 = this.ivAppIcon;
            if (imageView2 != null) {
                imageView2.setImageResource(shortcutAppSampleInfo.getSecond().intValue());
            }
            TextView textView2 = this.tvAppName;
            if (textView2 != null) {
                textView2.setText(LanguageUtil.getLanguageText(shortcutAppSampleInfo.getFirst().intValue()));
            }
        } else {
            ImageView imageView3 = this.ivAppIcon;
            if (imageView3 != null) {
                imageView3.setVisibility(4);
            }
            TextView textView3 = this.tvAppName;
            if (textView3 != null) {
                textView3.setVisibility(4);
            }
        }
        LinearLayout linearLayout = this.llContainer;
        if (linearLayout == null || (layoutParams = linearLayout.getLayoutParams()) == null) {
            return;
        }
        layoutParams.height = this.mAdapter.getMItemHeight();
    }
}