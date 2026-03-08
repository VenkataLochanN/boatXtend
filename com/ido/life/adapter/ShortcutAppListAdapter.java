package com.ido.life.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.DipPixelUtil;
import com.ido.life.bean.ShortcutAppData;
import com.ido.life.data.cache.ShortcutAppManager;
import com.ido.life.data.cache.ShortcutAppWrapper;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShortcutAppListAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002'(B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012&\u0010\u0005\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0018\u00010\u0006j\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0018\u0001`\t¢\u0006\u0002\u0010\nJ\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u000eH\u0002J\u0016\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0006\u0010\u001b\u001a\u00020\u000eJ\b\u0010\u001c\u001a\u00020\u000eH\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u000eH\u0002J\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u000eH\u0002J\u001c\u0010 \u001a\u00020!2\n\u0010\"\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u000eH\u0016J\u001c\u0010#\u001a\u00060\u0002R\u00020\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u000eH\u0016R1\u0010\u0005\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0018\u00010\u0006j\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0018\u0001`\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006)"}, d2 = {"Lcom/ido/life/adapter/ShortcutAppListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/adapter/ShortcutAppListAdapter$ContentViewHolder;", "mContext", "Landroid/content/Context;", "list", "Ljava/util/ArrayList;", "Lcom/ido/life/data/cache/ShortcutAppWrapper;", "Lcom/ido/life/bean/ShortcutAppData;", "Lkotlin/collections/ArrayList;", "(Landroid/content/Context;Ljava/util/ArrayList;)V", "getList", "()Ljava/util/ArrayList;", "mBottomMargin", "", "getMContext", "()Landroid/content/Context;", "onItemClickListener", "Lcom/ido/life/adapter/ShortcutAppListAdapter$OnItemClickListener;", "getOnItemClickListener", "()Lcom/ido/life/adapter/ShortcutAppListAdapter$OnItemClickListener;", "setOnItemClickListener", "(Lcom/ido/life/adapter/ShortcutAppListAdapter$OnItemClickListener;)V", "getDrawable", "Landroid/graphics/drawable/Drawable;", "resId", "getItem", "position", "getItemCount", "isFirstInGroup", "", "isLastInGroup", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ContentViewHolder", "OnItemClickListener", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ShortcutAppListAdapter extends RecyclerView.Adapter<ContentViewHolder> {
    private final ArrayList<ShortcutAppWrapper<ShortcutAppData>> list;
    private int mBottomMargin;
    private final Context mContext;
    private OnItemClickListener onItemClickListener;

    /* JADX INFO: compiled from: ShortcutAppListAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/ido/life/adapter/ShortcutAppListAdapter$OnItemClickListener;", "", "onItemClick", "", "position", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final ArrayList<ShortcutAppWrapper<ShortcutAppData>> getList() {
        return this.list;
    }

    public ShortcutAppListAdapter(Context mContext, ArrayList<ShortcutAppWrapper<ShortcutAppData>> arrayList) {
        Intrinsics.checkParameterIsNotNull(mContext, "mContext");
        this.mContext = mContext;
        this.list = arrayList;
        this.mBottomMargin = DipPixelUtil.dip2px(50.0f);
    }

    public final OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public final void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shortcut_app_list_content, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(pare…t_content, parent, false)");
        return new ContentViewHolder(this, viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        int i;
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        ShortcutAppWrapper<ShortcutAppData> item = getItem(position);
        if (item != null) {
            Pair<Integer, Integer> shortcutAppInfo = ShortcutAppManager.INSTANCE.getInstance().getShortcutAppInfo(item.getData().getWidgets_type());
            TextView tvShortcutAppName = holder.getTvShortcutAppName();
            if (tvShortcutAppName != null) {
                tvShortcutAppName.setText(shortcutAppInfo.getFirst().intValue());
            }
            ImageView ivShortcutAppIcon = holder.getIvShortcutAppIcon();
            if (ivShortcutAppIcon != null) {
                ivShortcutAppIcon.setImageResource(shortcutAppInfo.getSecond().intValue());
            }
            boolean z = (isFirstInGroup(position) && isLastInGroup(position)) || isFirstInGroup(position);
            if (isFirstInGroup(position) && isLastInGroup(position)) {
                i = R.drawable.bg_shortcut_app_list_one;
            } else if (isFirstInGroup(position)) {
                i = R.drawable.bg_shortcut_app_list_first;
            } else {
                i = isLastInGroup(position) ? R.drawable.bg_shortcut_app_list_last : R.drawable.bg_shortcut_app_list_mid;
            }
            RelativeLayout rlContent = holder.getRlContent();
            if (rlContent != null) {
                rlContent.setBackground(getDrawable(i));
            }
            TextView tvGroupTitle = holder.getTvGroupTitle();
            if (tvGroupTitle != null) {
                tvGroupTitle.setVisibility(z ? 0 : 8);
            }
            TextView tvGroupTitle2 = holder.getTvGroupTitle();
            if (tvGroupTitle2 != null) {
                tvGroupTitle2.setText(z ? ShortcutAppManager.INSTANCE.getInstance().getShortcutAppGroupName(item.getType()) : "");
            }
            View view = holder.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
            }
            RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
            layoutParams2.setMargins(layoutParams2.leftMargin, layoutParams2.topMargin, layoutParams2.rightMargin, position == getItemCount() - 1 ? this.mBottomMargin : 0);
            View view2 = holder.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view2, "holder.itemView");
            view2.setLayoutParams(layoutParams2);
        }
    }

    private final boolean isLastInGroup(int position) {
        ShortcutAppWrapper<ShortcutAppData> shortcutAppWrapper;
        ShortcutAppWrapper<ShortcutAppData> item = getItem(position);
        if (item == null) {
            return false;
        }
        ArrayList<ShortcutAppWrapper<ShortcutAppData>> arrayList = this.list;
        if (position == (arrayList != null ? arrayList.size() : 0) - 1) {
            return true;
        }
        int type = item.getType();
        ArrayList<ShortcutAppWrapper<ShortcutAppData>> arrayList2 = this.list;
        return arrayList2 == null || (shortcutAppWrapper = arrayList2.get(position + 1)) == null || type != shortcutAppWrapper.getType();
    }

    private final boolean isFirstInGroup(int position) {
        ShortcutAppWrapper<ShortcutAppData> shortcutAppWrapper;
        ShortcutAppWrapper<ShortcutAppData> item = getItem(position);
        if (position == 0) {
            return true;
        }
        ArrayList<ShortcutAppWrapper<ShortcutAppData>> arrayList = this.list;
        return !Intrinsics.areEqual((arrayList == null || (shortcutAppWrapper = arrayList.get(position - 1)) == null) ? null : Integer.valueOf(shortcutAppWrapper.getType()), item != null ? Integer.valueOf(item.getType()) : null);
    }

    private final Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(this.mContext, resId);
    }

    public final ShortcutAppWrapper<ShortcutAppData> getItem(int position) {
        ArrayList<ShortcutAppWrapper<ShortcutAppData>> arrayList = this.list;
        if (arrayList != null) {
            return arrayList.get(position);
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<ShortcutAppWrapper<ShortcutAppData>> arrayList = this.list;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    /* JADX INFO: compiled from: ShortcutAppListAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016¨\u0006\u001a"}, d2 = {"Lcom/ido/life/adapter/ShortcutAppListAdapter$ContentViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/ido/life/adapter/ShortcutAppListAdapter;Landroid/view/View;)V", "ivShortcutAppIcon", "Landroid/widget/ImageView;", "getIvShortcutAppIcon", "()Landroid/widget/ImageView;", "setIvShortcutAppIcon", "(Landroid/widget/ImageView;)V", "rlContent", "Landroid/widget/RelativeLayout;", "getRlContent", "()Landroid/widget/RelativeLayout;", "setRlContent", "(Landroid/widget/RelativeLayout;)V", "tvGroupTitle", "Landroid/widget/TextView;", "getTvGroupTitle", "()Landroid/widget/TextView;", "setTvGroupTitle", "(Landroid/widget/TextView;)V", "tvShortcutAppName", "getTvShortcutAppName", "setTvShortcutAppName", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class ContentViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivShortcutAppIcon;
        private RelativeLayout rlContent;
        final /* synthetic */ ShortcutAppListAdapter this$0;
        private TextView tvGroupTitle;
        private TextView tvShortcutAppName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ContentViewHolder(ShortcutAppListAdapter shortcutAppListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            this.this$0 = shortcutAppListAdapter;
            this.rlContent = (RelativeLayout) itemView.findViewById(R.id.rlContent);
            this.tvGroupTitle = (TextView) itemView.findViewById(R.id.tvGroupTitle);
            this.ivShortcutAppIcon = (ImageView) itemView.findViewById(R.id.ivShortcutAppIcon);
            this.tvShortcutAppName = (TextView) itemView.findViewById(R.id.tvShortcutAppName);
            itemView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.adapter.ShortcutAppListAdapter.ContentViewHolder.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OnItemClickListener onItemClickListener = ContentViewHolder.this.this$0.getOnItemClickListener();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(ContentViewHolder.this.getAdapterPosition());
                    }
                }
            });
        }

        public final RelativeLayout getRlContent() {
            return this.rlContent;
        }

        public final void setRlContent(RelativeLayout relativeLayout) {
            this.rlContent = relativeLayout;
        }

        public final TextView getTvGroupTitle() {
            return this.tvGroupTitle;
        }

        public final void setTvGroupTitle(TextView textView) {
            this.tvGroupTitle = textView;
        }

        public final ImageView getIvShortcutAppIcon() {
            return this.ivShortcutAppIcon;
        }

        public final void setIvShortcutAppIcon(ImageView imageView) {
            this.ivShortcutAppIcon = imageView;
        }

        public final TextView getTvShortcutAppName() {
            return this.tvShortcutAppName;
        }

        public final void setTvShortcutAppName(TextView textView) {
            this.tvShortcutAppName = textView;
        }
    }
}