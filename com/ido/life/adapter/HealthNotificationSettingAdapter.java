package com.ido.life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.bean.HealthMonitoringBean;
import com.ido.life.customview.OnItemClickListener;
import com.ido.life.data.cache.HealthMonitoringManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HealthNotificationSettingAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001eB\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u001c\u0010\u0017\u001a\u00020\u00182\n\u0010\u0019\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001c\u0010\u001a\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0015H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/ido/life/adapter/HealthNotificationSettingAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/adapter/HealthNotificationSettingAdapter$ViewHolder;", "context", "Landroid/content/Context;", "list", "", "Lcom/ido/life/bean/HealthMonitoringBean;", "(Landroid/content/Context;Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "getList", "()Ljava/util/List;", "onItemClickListener", "Lcom/ido/life/customview/OnItemClickListener;", "getOnItemClickListener", "()Lcom/ido/life/customview/OnItemClickListener;", "setOnItemClickListener", "(Lcom/ido/life/customview/OnItemClickListener;)V", "getItem", "position", "", "getItemCount", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class HealthNotificationSettingAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private final List<HealthMonitoringBean> list;
    private OnItemClickListener onItemClickListener;

    public final Context getContext() {
        return this.context;
    }

    public final List<HealthMonitoringBean> getList() {
        return this.list;
    }

    public HealthNotificationSettingAdapter(Context context, List<HealthMonitoringBean> list) {
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
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.item_health_reminder_list, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…nder_list, parent, false)");
        return new ViewHolder(this, viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        HealthMonitoringBean item = getItem(position);
        if (item != null) {
            Pair<Integer, Integer> healthModuleInfo = HealthMonitoringManager.INSTANCE.getHealthModuleInfo(item.getType());
            if (healthModuleInfo != null) {
                ImageView ivFuncIcon = holder.getIvFuncIcon();
                if (ivFuncIcon != null) {
                    ivFuncIcon.setImageResource(healthModuleInfo.getSecond().intValue());
                }
                TextView tvFuncName = holder.getTvFuncName();
                if (tvFuncName != null) {
                    tvFuncName.setText(LanguageUtil.getLanguageText(healthModuleInfo.getFirst().intValue()));
                }
            }
            TextView tvFuncStatus = holder.getTvFuncStatus();
            if (tvFuncStatus != null) {
                tvFuncStatus.setText(LanguageUtil.getLanguageText(item.getStatusValue() == 1 ? R.string.public_permit : item.getStatusValue() == 2 ? R.string.public_silence : R.string.public_close));
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<HealthMonitoringBean> list = this.list;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public final HealthMonitoringBean getItem(int position) {
        List<HealthMonitoringBean> list = this.list;
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    /* JADX INFO: compiled from: HealthNotificationSettingAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006\u0014"}, d2 = {"Lcom/ido/life/adapter/HealthNotificationSettingAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/ido/life/adapter/HealthNotificationSettingAdapter;Landroid/view/View;)V", "ivFuncIcon", "Landroid/widget/ImageView;", "getIvFuncIcon", "()Landroid/widget/ImageView;", "setIvFuncIcon", "(Landroid/widget/ImageView;)V", "tvFuncName", "Landroid/widget/TextView;", "getTvFuncName", "()Landroid/widget/TextView;", "setTvFuncName", "(Landroid/widget/TextView;)V", "tvFuncStatus", "getTvFuncStatus", "setTvFuncStatus", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFuncIcon;
        final /* synthetic */ HealthNotificationSettingAdapter this$0;
        private TextView tvFuncName;
        private TextView tvFuncStatus;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(HealthNotificationSettingAdapter healthNotificationSettingAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            this.this$0 = healthNotificationSettingAdapter;
            this.ivFuncIcon = (ImageView) itemView.findViewById(R.id.ivFuncIcon);
            this.tvFuncName = (TextView) itemView.findViewById(R.id.tvFuncName);
            this.tvFuncStatus = (TextView) itemView.findViewById(R.id.tvFuncStatus);
            itemView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.adapter.HealthNotificationSettingAdapter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OnItemClickListener onItemClickListener = ViewHolder.this.this$0.getOnItemClickListener();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(ViewHolder.this.getAdapterPosition());
                    }
                }
            });
        }

        public final ImageView getIvFuncIcon() {
            return this.ivFuncIcon;
        }

        public final void setIvFuncIcon(ImageView imageView) {
            this.ivFuncIcon = imageView;
        }

        public final TextView getTvFuncName() {
            return this.tvFuncName;
        }

        public final void setTvFuncName(TextView textView) {
            this.tvFuncName = textView;
        }

        public final TextView getTvFuncStatus() {
            return this.tvFuncStatus;
        }

        public final void setTvFuncStatus(TextView textView) {
            this.tvFuncStatus = textView;
        }
    }
}