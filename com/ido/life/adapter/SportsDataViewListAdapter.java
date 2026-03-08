package com.ido.life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.bean.MotionTypeBean;
import com.ido.life.customview.OnItemClickListener;
import com.realsil.sdk.core.utility.DensityUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SportsDataViewListAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001$B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u001c\u0010\u001d\u001a\u00020\u001e2\n\u0010\u001f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001c\u0010 \u001a\u00060\u0002R\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0015H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006%"}, d2 = {"Lcom/ido/life/adapter/SportsDataViewListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/adapter/SportsDataViewListAdapter$ViewHolder;", "context", "Landroid/content/Context;", "list", "", "Lcom/ido/life/bean/MotionTypeBean;", "(Landroid/content/Context;Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "getList", "()Ljava/util/List;", "onItemClickListener", "Lcom/ido/life/customview/OnItemClickListener;", "getOnItemClickListener", "()Lcom/ido/life/customview/OnItemClickListener;", "setOnItemClickListener", "(Lcom/ido/life/customview/OnItemClickListener;)V", "getItem", "position", "", "getItemCount", "getTitle", "", "available", "", "isFirstInGroup", "isLastInGroup", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SportsDataViewListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private final List<MotionTypeBean> list;
    private OnItemClickListener onItemClickListener;

    public final Context getContext() {
        return this.context;
    }

    public final List<MotionTypeBean> getList() {
        return this.list;
    }

    public SportsDataViewListAdapter(Context context, List<MotionTypeBean> list) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.list = list;
    }

    public final OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public final void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.item_sports_data_view_list, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…view_list, parent, false)");
        return new ViewHolder(this, viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView tvTitle;
        TextView tvTitle2;
        int iconResId;
        String name;
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        MotionTypeBean item = getItem(position);
        TextView tvMotionName = holder.getTvMotionName();
        if (tvMotionName != null) {
            if (item == null || (name = item.getName()) == null) {
                name = "";
            }
            tvMotionName.setText(name);
        }
        ImageView ivMotionIcon = holder.getIvMotionIcon();
        boolean z = false;
        if (ivMotionIcon != null) {
            if ((item != null ? item.getIconResId() : 0) > 0) {
                if (item == null) {
                    Intrinsics.throwNpe();
                }
                iconResId = item.getIconResId();
            } else {
                iconResId = R.mipmap.motion_4;
            }
            ivMotionIcon.setImageResource(iconResId);
        }
        boolean zIsFirstInGroup = isFirstInGroup(position);
        boolean zIsLastInGroup = isLastInGroup(position);
        TextView tvTitle3 = holder.getTvTitle();
        if (tvTitle3 != null) {
            tvTitle3.setVisibility(zIsFirstInGroup ? 0 : 8);
        }
        TextView tvTitle4 = holder.getTvTitle();
        if (tvTitle4 != null) {
            tvTitle4.setText(getTitle(item != null && item.getAvailable()));
        }
        if (zIsFirstInGroup && zIsLastInGroup) {
            RelativeLayout lay_content = holder.getLay_content();
            if (lay_content != null) {
                lay_content.setBackground(ResourceUtil.getDrawable(R.drawable.bg_white_12_corner));
            }
            if (position != 0 && (tvTitle2 = holder.getTvTitle()) != null) {
                tvTitle2.setPadding(0, DensityUtils.dip2px(this.context, 32.0f), 0, 0);
            }
        } else if (zIsFirstInGroup) {
            RelativeLayout lay_content2 = holder.getLay_content();
            if (lay_content2 != null) {
                lay_content2.setBackground(ResourceUtil.getDrawable(R.drawable.selector_item_top_corner_bg));
            }
            if (position != 0 && (tvTitle = holder.getTvTitle()) != null) {
                tvTitle.setPadding(0, DensityUtils.dip2px(this.context, 32.0f), 0, 0);
            }
        } else if (zIsLastInGroup) {
            RelativeLayout lay_content3 = holder.getLay_content();
            if (lay_content3 != null) {
                lay_content3.setBackground(ResourceUtil.getDrawable(R.drawable.selector_item_bottom_corner_bg));
            }
        } else {
            RelativeLayout lay_content4 = holder.getLay_content();
            if (lay_content4 != null) {
                lay_content4.setBackground(ResourceUtil.getDrawable(R.drawable.user_center_item_selector));
            }
        }
        RelativeLayout lay_content5 = holder.getLay_content();
        if (lay_content5 != null) {
            lay_content5.setAlpha((item == null || !item.getAvailable()) ? 0.4f : 1.0f);
        }
        RelativeLayout lay_content6 = holder.getLay_content();
        if (lay_content6 != null) {
            if (item != null && item.getAvailable()) {
                z = true;
            }
            lay_content6.setEnabled(z);
        }
    }

    private final boolean isLastInGroup(int position) {
        MotionTypeBean motionTypeBean;
        MotionTypeBean item = getItem(position);
        if (item == null) {
            return false;
        }
        List<MotionTypeBean> list = this.list;
        if (position == (list != null ? list.size() : 0) - 1) {
            return true;
        }
        boolean available = item.getAvailable();
        List<MotionTypeBean> list2 = this.list;
        return list2 == null || (motionTypeBean = list2.get(position + 1)) == null || available != motionTypeBean.getAvailable();
    }

    private final boolean isFirstInGroup(int position) {
        MotionTypeBean motionTypeBean;
        MotionTypeBean item = getItem(position);
        if (position == 0) {
            return true;
        }
        List<MotionTypeBean> list = this.list;
        return !Intrinsics.areEqual((list == null || (motionTypeBean = list.get(position - 1)) == null) ? null : Boolean.valueOf(motionTypeBean.getAvailable()), item != null ? Boolean.valueOf(item.getAvailable()) : null);
    }

    private final String getTitle(boolean available) {
        String languageText = LanguageUtil.getLanguageText(available ? R.string.sports_data_view_tips : R.string.sports_data_view_tips_no_add);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…ts_data_view_tips_no_add)");
        return languageText;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MotionTypeBean> list = this.list;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public final MotionTypeBean getItem(int position) {
        List<MotionTypeBean> list = this.list;
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    /* JADX INFO: compiled from: SportsDataViewListAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016¨\u0006\u001a"}, d2 = {"Lcom/ido/life/adapter/SportsDataViewListAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/ido/life/adapter/SportsDataViewListAdapter;Landroid/view/View;)V", "ivMotionIcon", "Landroid/widget/ImageView;", "getIvMotionIcon", "()Landroid/widget/ImageView;", "setIvMotionIcon", "(Landroid/widget/ImageView;)V", "lay_content", "Landroid/widget/RelativeLayout;", "getLay_content", "()Landroid/widget/RelativeLayout;", "setLay_content", "(Landroid/widget/RelativeLayout;)V", "tvMotionName", "Landroid/widget/TextView;", "getTvMotionName", "()Landroid/widget/TextView;", "setTvMotionName", "(Landroid/widget/TextView;)V", "tvTitle", "getTvTitle", "setTvTitle", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivMotionIcon;
        private RelativeLayout lay_content;
        final /* synthetic */ SportsDataViewListAdapter this$0;
        private TextView tvMotionName;
        private TextView tvTitle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(SportsDataViewListAdapter sportsDataViewListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            this.this$0 = sportsDataViewListAdapter;
            this.ivMotionIcon = (ImageView) itemView.findViewById(R.id.ivMotionIcon);
            this.tvMotionName = (TextView) itemView.findViewById(R.id.tvMotionName);
            this.tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            this.lay_content = (RelativeLayout) itemView.findViewById(R.id.lay_content);
            RelativeLayout relativeLayout = this.lay_content;
            if (relativeLayout != null) {
                relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.adapter.SportsDataViewListAdapter.ViewHolder.1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        OnItemClickListener onItemClickListener = ViewHolder.this.this$0.getOnItemClickListener();
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(ViewHolder.this.getAdapterPosition());
                        }
                    }
                });
            }
        }

        public final ImageView getIvMotionIcon() {
            return this.ivMotionIcon;
        }

        public final void setIvMotionIcon(ImageView imageView) {
            this.ivMotionIcon = imageView;
        }

        public final TextView getTvMotionName() {
            return this.tvMotionName;
        }

        public final void setTvMotionName(TextView textView) {
            this.tvMotionName = textView;
        }

        public final TextView getTvTitle() {
            return this.tvTitle;
        }

        public final void setTvTitle(TextView textView) {
            this.tvTitle = textView;
        }

        public final RelativeLayout getLay_content() {
            return this.lay_content;
        }

        public final void setLay_content(RelativeLayout relativeLayout) {
            this.lay_content = relativeLayout;
        }
    }
}