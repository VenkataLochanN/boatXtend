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
import com.ido.life.bean.NotificationApp;
import com.ido.life.customview.OnItemClickListener;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NotificationSettingAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001'B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001b\u001a\u00020\u001cJ\b\u0010\u001d\u001a\u00020\u001cH\u0016J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001f\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u001c\u0010 \u001a\u00020!2\n\u0010\"\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u001c\u0010#\u001a\u00060\u0002R\u00020\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR$\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006("}, d2 = {"Lcom/ido/life/adapter/NotificationSettingAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/adapter/NotificationSettingAdapter$ViewHolder;", "context", "Landroid/content/Context;", "list", "", "Lcom/ido/life/bean/NotificationApp;", "(Landroid/content/Context;Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "value", "", "enable", "getEnable", "()Z", "setEnable", "(Z)V", "getList", "()Ljava/util/List;", "onItemClickListener", "Lcom/ido/life/customview/OnItemClickListener;", "getOnItemClickListener", "()Lcom/ido/life/customview/OnItemClickListener;", "setOnItemClickListener", "(Lcom/ido/life/customview/OnItemClickListener;)V", "getItem", "position", "", "getItemCount", "isFirstInGroup", "isLastInGroup", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class NotificationSettingAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private boolean enable;
    private final List<NotificationApp> list;
    private OnItemClickListener onItemClickListener;

    public final Context getContext() {
        return this.context;
    }

    public final List<NotificationApp> getList() {
        return this.list;
    }

    public NotificationSettingAdapter(Context context, List<NotificationApp> list) {
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

    public final boolean getEnable() {
        return this.enable;
    }

    public final void setEnable(boolean z) {
        this.enable = z;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.item_notification_setting, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…n_setting, parent, false)");
        return new ViewHolder(this, viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        NotificationApp item = getItem(position);
        if (item != null) {
            ImageView ivAppIcon = holder.getIvAppIcon();
            if (ivAppIcon != null) {
                ivAppIcon.setImageDrawable(item.getIcon());
            }
            TextView tvAppName = holder.getTvAppName();
            if (tvAppName != null) {
                tvAppName.setText(item.getName());
            }
            TextView tvStatus = holder.getTvStatus();
            if (tvStatus != null) {
                tvStatus.setText(LanguageUtil.getLanguageText(item.getStatus() == 1 ? R.string.public_permit : item.getStatus() == 2 ? R.string.public_silence : R.string.public_close));
            }
        }
        boolean zIsFirstInGroup = isFirstInGroup(position);
        boolean zIsLastInGroup = isLastInGroup(position);
        View vLine = holder.getVLine();
        if (vLine != null) {
            vLine.setVisibility(zIsFirstInGroup ? 0 : 8);
        }
        View vDecoration = holder.getVDecoration();
        if (vDecoration != null) {
            vDecoration.setVisibility(zIsLastInGroup ? 8 : 0);
        }
    }

    private final boolean isLastInGroup(int position) {
        NotificationApp notificationApp;
        NotificationApp item = getItem(position);
        if (item == null) {
            return false;
        }
        List<NotificationApp> list = this.list;
        if (position == (list != null ? list.size() : 0) - 1) {
            return true;
        }
        int group = item.getGroup();
        List<NotificationApp> list2 = this.list;
        return list2 == null || (notificationApp = list2.get(position + 1)) == null || group != notificationApp.getGroup();
    }

    private final boolean isFirstInGroup(int position) {
        NotificationApp notificationApp;
        NotificationApp item = getItem(position);
        if (position == 0) {
            return true;
        }
        List<NotificationApp> list = this.list;
        return !Intrinsics.areEqual((list == null || (notificationApp = list.get(position - 1)) == null) ? null : Integer.valueOf(notificationApp.getGroup()), item != null ? Integer.valueOf(item.getGroup()) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<NotificationApp> list = this.list;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public final NotificationApp getItem(int position) {
        List<NotificationApp> list = this.list;
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    /* JADX INFO: compiled from: NotificationSettingAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018¨\u0006\u001c"}, d2 = {"Lcom/ido/life/adapter/NotificationSettingAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/ido/life/adapter/NotificationSettingAdapter;Landroid/view/View;)V", "ivAppIcon", "Landroid/widget/ImageView;", "getIvAppIcon", "()Landroid/widget/ImageView;", "setIvAppIcon", "(Landroid/widget/ImageView;)V", "tvAppName", "Landroid/widget/TextView;", "getTvAppName", "()Landroid/widget/TextView;", "setTvAppName", "(Landroid/widget/TextView;)V", "tvStatus", "getTvStatus", "setTvStatus", "vDecoration", "getVDecoration", "()Landroid/view/View;", "setVDecoration", "(Landroid/view/View;)V", "vLine", "getVLine", "setVLine", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivAppIcon;
        final /* synthetic */ NotificationSettingAdapter this$0;
        private TextView tvAppName;
        private TextView tvStatus;
        private View vDecoration;
        private View vLine;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(NotificationSettingAdapter notificationSettingAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            this.this$0 = notificationSettingAdapter;
            this.vDecoration = itemView.findViewById(R.id.vDecoration);
            this.vLine = itemView.findViewById(R.id.vLine);
            this.ivAppIcon = (ImageView) itemView.findViewById(R.id.ivAppIcon);
            this.tvStatus = (TextView) itemView.findViewById(R.id.tvStatus);
            this.tvAppName = (TextView) itemView.findViewById(R.id.tvAppName);
            itemView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.adapter.NotificationSettingAdapter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OnItemClickListener onItemClickListener = ViewHolder.this.this$0.getOnItemClickListener();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(ViewHolder.this.getAdapterPosition());
                    }
                }
            });
        }

        public final TextView getTvAppName() {
            return this.tvAppName;
        }

        public final void setTvAppName(TextView textView) {
            this.tvAppName = textView;
        }

        public final TextView getTvStatus() {
            return this.tvStatus;
        }

        public final void setTvStatus(TextView textView) {
            this.tvStatus = textView;
        }

        public final ImageView getIvAppIcon() {
            return this.ivAppIcon;
        }

        public final void setIvAppIcon(ImageView imageView) {
            this.ivAppIcon = imageView;
        }

        public final View getVLine() {
            return this.vLine;
        }

        public final void setVLine(View view) {
            this.vLine = view;
        }

        public final View getVDecoration() {
            return this.vDecoration;
        }

        public final void setVDecoration(View view) {
            this.vDecoration = view;
        }
    }
}