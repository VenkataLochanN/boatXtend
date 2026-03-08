package com.ido.life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.life.bean.SportsDataViewDetailBean;
import com.ido.life.customview.OnItemClickListener;
import com.ido.life.customview.OnItemMoveListener;
import com.ido.life.customview.recyclerview.ItemTouchHelperAdapter;
import com.ido.life.customview.recyclerview.ItemTouchHelperViewHolder;
import com.ido.life.customview.recyclerview.OnStartDragListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SportsDataViewDetailListAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u00012\u00020\u0003:\u0002;<B%\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010-\u001a\u00020\nH\u0002J\u0010\u0010.\u001a\u0004\u0018\u00010\b2\u0006\u0010/\u001a\u00020\nJ\b\u00100\u001a\u00020\nH\u0016J\u001c\u00101\u001a\u0002022\n\u00103\u001a\u00060\u0002R\u00020\u00002\u0006\u0010/\u001a\u00020\nH\u0016J\u001c\u00104\u001a\u00060\u0002R\u00020\u00002\u0006\u00105\u001a\u0002062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u00107\u001a\u0002022\u0006\u0010/\u001a\u00020\nH\u0016J\u0018\u00108\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\n2\u0006\u0010:\u001a\u00020\nH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,¨\u0006="}, d2 = {"Lcom/ido/life/adapter/SportsDataViewDetailListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/adapter/SportsDataViewDetailListAdapter$ViewHolder;", "Lcom/ido/life/customview/recyclerview/ItemTouchHelperAdapter;", "context", "Landroid/content/Context;", "list", "", "Lcom/ido/life/bean/SportsDataViewDetailBean;", "viewType", "", "(Landroid/content/Context;Ljava/util/List;I)V", "getContext", "()Landroid/content/Context;", "enable", "", "getEnable", "()Z", "setEnable", "(Z)V", "value", "isEdit", "setEdit", "getList", "()Ljava/util/List;", "onItemClickListener", "Lcom/ido/life/customview/OnItemClickListener;", "getOnItemClickListener", "()Lcom/ido/life/customview/OnItemClickListener;", "setOnItemClickListener", "(Lcom/ido/life/customview/OnItemClickListener;)V", "onItemMoveListener", "Lcom/ido/life/customview/OnItemMoveListener;", "getOnItemMoveListener", "()Lcom/ido/life/customview/OnItemMoveListener;", "setOnItemMoveListener", "(Lcom/ido/life/customview/OnItemMoveListener;)V", "onStartDragListener", "Lcom/ido/life/customview/recyclerview/OnStartDragListener;", "getOnStartDragListener", "()Lcom/ido/life/customview/recyclerview/OnStartDragListener;", "setOnStartDragListener", "(Lcom/ido/life/customview/recyclerview/OnStartDragListener;)V", "getViewType", "()I", "getDeleteOrAddIcon", "getItem", "position", "getItemCount", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onItemDismiss", "onItemMove", "fromPosition", "toPosition", "ViewHolder", "ViewType", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SportsDataViewDetailListAdapter extends RecyclerView.Adapter<ViewHolder> implements ItemTouchHelperAdapter {
    private final Context context;
    private boolean enable;
    private boolean isEdit;
    private final List<SportsDataViewDetailBean> list;
    private OnItemClickListener onItemClickListener;
    private OnItemMoveListener onItemMoveListener;
    private OnStartDragListener onStartDragListener;
    private final int viewType;

    @Override // com.ido.life.customview.recyclerview.ItemTouchHelperAdapter
    public void onItemDismiss(int position) {
    }

    public final Context getContext() {
        return this.context;
    }

    public final List<SportsDataViewDetailBean> getList() {
        return this.list;
    }

    public final int getViewType() {
        return this.viewType;
    }

    public SportsDataViewDetailListAdapter(Context context, List<SportsDataViewDetailBean> list, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.list = list;
        this.viewType = i;
        this.enable = true;
    }

    public final OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public final void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public final OnItemMoveListener getOnItemMoveListener() {
        return this.onItemMoveListener;
    }

    public final void setOnItemMoveListener(OnItemMoveListener onItemMoveListener) {
        this.onItemMoveListener = onItemMoveListener;
    }

    public final OnStartDragListener getOnStartDragListener() {
        return this.onStartDragListener;
    }

    public final void setOnStartDragListener(OnStartDragListener onStartDragListener) {
        this.onStartDragListener = onStartDragListener;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    public final void setEnable(boolean z) {
        this.enable = z;
    }

    /* JADX INFO: renamed from: isEdit, reason: from getter */
    public final boolean getIsEdit() {
        return this.isEdit;
    }

    public final void setEdit(boolean z) {
        this.isEdit = z;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.item_sports_data_view_detail_list, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…tail_list, parent, false)");
        return new ViewHolder(this, viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TextView tvMotionName;
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        SportsDataViewDetailBean item = getItem(position);
        if (item != null && (tvMotionName = holder.getTvMotionName()) != null) {
            tvMotionName.setText(item.getName());
        }
        ImageView ivAddOrDelete = holder.getIvAddOrDelete();
        if (ivAddOrDelete != null) {
            ivAddOrDelete.setImageResource(getDeleteOrAddIcon());
        }
        ImageView ivAddOrDelete2 = holder.getIvAddOrDelete();
        if (ivAddOrDelete2 != null) {
            ivAddOrDelete2.setEnabled(this.enable);
        }
        ImageView ivOptIcon = holder.getIvOptIcon();
        if (ivOptIcon != null) {
            ivOptIcon.setVisibility((this.isEdit && this.viewType == 1) ? 0 : 8);
        }
        ImageView ivAddOrDelete3 = holder.getIvAddOrDelete();
        if (ivAddOrDelete3 != null) {
            ivAddOrDelete3.setVisibility(this.isEdit ? 0 : 8);
        }
        if (this.isEdit) {
            ImageView ivOptIcon2 = holder.getIvOptIcon();
            if (ivOptIcon2 != null) {
                ivOptIcon2.setOnTouchListener(new View.OnTouchListener() { // from class: com.ido.life.adapter.SportsDataViewDetailListAdapter.onBindViewHolder.2
                    @Override // android.view.View.OnTouchListener
                    public final boolean onTouch(View view, MotionEvent event) {
                        OnStartDragListener onStartDragListener;
                        Intrinsics.checkParameterIsNotNull(event, "event");
                        if (event.getAction() != 0 || SportsDataViewDetailListAdapter.this.getOnStartDragListener() == null || (onStartDragListener = SportsDataViewDetailListAdapter.this.getOnStartDragListener()) == null) {
                            return false;
                        }
                        onStartDragListener.onStartDrag(holder);
                        return false;
                    }
                });
                return;
            }
            return;
        }
        ImageView ivOptIcon3 = holder.getIvOptIcon();
        if (ivOptIcon3 != null) {
            ivOptIcon3.setOnTouchListener(null);
        }
    }

    private final int getDeleteOrAddIcon() {
        return this.viewType == 1 ? this.enable ? R.mipmap.icon_motion_type_delete : R.mipmap.icon_motion_type_delete_disable : this.enable ? R.mipmap.icon_motion_type_add : R.mipmap.icon_motion_type_add_disable;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SportsDataViewDetailBean> list = this.list;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public final SportsDataViewDetailBean getItem(int position) {
        List<SportsDataViewDetailBean> list = this.list;
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    /* JADX INFO: compiled from: SportsDataViewDetailListAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0018"}, d2 = {"Lcom/ido/life/adapter/SportsDataViewDetailListAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/ido/life/customview/recyclerview/ItemTouchHelperViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/ido/life/adapter/SportsDataViewDetailListAdapter;Landroid/view/View;)V", "ivAddOrDelete", "Landroid/widget/ImageView;", "getIvAddOrDelete", "()Landroid/widget/ImageView;", "setIvAddOrDelete", "(Landroid/widget/ImageView;)V", "ivOptIcon", "getIvOptIcon", "setIvOptIcon", "tvMotionName", "Landroid/widget/TextView;", "getTvMotionName", "()Landroid/widget/TextView;", "setTvMotionName", "(Landroid/widget/TextView;)V", "onItemClear", "", "onItemSelected", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        private ImageView ivAddOrDelete;
        private ImageView ivOptIcon;
        final /* synthetic */ SportsDataViewDetailListAdapter this$0;
        private TextView tvMotionName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(SportsDataViewDetailListAdapter sportsDataViewDetailListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            this.this$0 = sportsDataViewDetailListAdapter;
            this.ivAddOrDelete = (ImageView) itemView.findViewById(R.id.ivAddOrDelete);
            this.ivOptIcon = (ImageView) itemView.findViewById(R.id.ivOptIcon);
            this.tvMotionName = (TextView) itemView.findViewById(R.id.tvMotionName);
            itemView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.adapter.SportsDataViewDetailListAdapter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OnItemClickListener onItemClickListener;
                    if (!ViewHolder.this.this$0.getEnable() || (onItemClickListener = ViewHolder.this.this$0.getOnItemClickListener()) == null) {
                        return;
                    }
                    onItemClickListener.onItemClick(ViewHolder.this.getAdapterPosition());
                }
            });
        }

        public final ImageView getIvOptIcon() {
            return this.ivOptIcon;
        }

        public final void setIvOptIcon(ImageView imageView) {
            this.ivOptIcon = imageView;
        }

        public final ImageView getIvAddOrDelete() {
            return this.ivAddOrDelete;
        }

        public final void setIvAddOrDelete(ImageView imageView) {
            this.ivAddOrDelete = imageView;
        }

        public final TextView getTvMotionName() {
            return this.tvMotionName;
        }

        public final void setTvMotionName(TextView textView) {
            this.tvMotionName = textView;
        }

        @Override // com.ido.life.customview.recyclerview.ItemTouchHelperViewHolder
        public void onItemSelected() {
            this.itemView.setBackgroundResource(R.color.color_66979797);
        }

        @Override // com.ido.life.customview.recyclerview.ItemTouchHelperViewHolder
        public void onItemClear() {
            this.itemView.setBackgroundColor(0);
        }
    }

    @Override // com.ido.life.customview.recyclerview.ItemTouchHelperAdapter
    public boolean onItemMove(int fromPosition, int toPosition) {
        OnItemMoveListener onItemMoveListener = this.onItemMoveListener;
        if (onItemMoveListener == null) {
            return true;
        }
        onItemMoveListener.onItemMove(fromPosition, toPosition);
        return true;
    }

    /* JADX INFO: compiled from: SportsDataViewDetailListAdapter.kt */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0087\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/ido/life/adapter/SportsDataViewDetailListAdapter$ViewType;", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
    @kotlin.annotation.Target(allowedTargets = {AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION})
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    public @interface ViewType {
        public static final int ADDED = 1;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;
        public static final int NOT_ADDED = 2;

        /* JADX INFO: compiled from: SportsDataViewDetailListAdapter.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/ido/life/adapter/SportsDataViewDetailListAdapter$ViewType$Companion;", "", "()V", "ADDED", "", "NOT_ADDED", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            public static final int ADDED = 1;
            public static final int NOT_ADDED = 2;

            private Companion() {
            }
        }
    }
}