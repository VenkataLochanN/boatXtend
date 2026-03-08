package com.ido.life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.DipPixelUtil;
import com.ido.life.bean.MotionTypeBean;
import com.ido.life.customview.OnItemClickListener;
import com.ido.life.customview.OnItemMoveListener;
import com.ido.life.customview.recyclerview.ItemTouchHelperAdapter;
import com.ido.life.customview.recyclerview.ItemTouchHelperViewHolder;
import com.ido.life.customview.recyclerview.OnStartDragListener;
import com.ido.life.customview.recyclerview.SwipeItemLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MotionTypeAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u00012\u00020\u0003:\u0001BB\u001f\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\b\u00105\u001a\u00020\u001cH\u0016J\u001c\u00106\u001a\u0002072\n\u00108\u001a\u00060\u0002R\u00020\u00002\u0006\u00109\u001a\u00020\u001cH\u0016J\u001c\u0010:\u001a\u00060\u0002R\u00020\u00002\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u001cH\u0016J\u0010\u0010>\u001a\u0002072\u0006\u00109\u001a\u00020\u001cH\u0016J\u0018\u0010?\u001a\u00020\u00132\u0006\u0010@\u001a\u00020\u001c2\u0006\u0010A\u001a\u00020\u001cH\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010#\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010 \"\u0004\b%\u0010\"R\u001c\u0010&\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010 \"\u0004\b(\u0010\"R\u001c\u0010)\u001a\u0004\u0018\u00010*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001c\u0010/\u001a\u0004\u0018\u000100X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104¨\u0006C"}, d2 = {"Lcom/ido/life/adapter/MotionTypeAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/adapter/MotionTypeAdapter$ViewHolder;", "Lcom/ido/life/customview/recyclerview/ItemTouchHelperAdapter;", "context", "Landroid/content/Context;", "datas", "", "Lcom/ido/life/bean/MotionTypeBean;", "(Landroid/content/Context;Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getDatas", "()Ljava/util/List;", "setDatas", "(Ljava/util/List;)V", "enableDelete", "", "getEnableDelete", "()Z", "setEnableDelete", "(Z)V", "value", "isEdit", "setEdit", "mBottomMargin", "", "onDeleteClickListener", "Lcom/ido/life/customview/OnItemClickListener;", "getOnDeleteClickListener", "()Lcom/ido/life/customview/OnItemClickListener;", "setOnDeleteClickListener", "(Lcom/ido/life/customview/OnItemClickListener;)V", "onItemClickListener", "getOnItemClickListener", "setOnItemClickListener", "onItemLongClickListener", "getOnItemLongClickListener", "setOnItemLongClickListener", "onItemMoveListener", "Lcom/ido/life/customview/OnItemMoveListener;", "getOnItemMoveListener", "()Lcom/ido/life/customview/OnItemMoveListener;", "setOnItemMoveListener", "(Lcom/ido/life/customview/OnItemMoveListener;)V", "onStartDragListener", "Lcom/ido/life/customview/recyclerview/OnStartDragListener;", "getOnStartDragListener", "()Lcom/ido/life/customview/recyclerview/OnStartDragListener;", "setOnStartDragListener", "(Lcom/ido/life/customview/recyclerview/OnStartDragListener;)V", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onItemDismiss", "onItemMove", "fromPosition", "toPosition", "ViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MotionTypeAdapter extends RecyclerView.Adapter<ViewHolder> implements ItemTouchHelperAdapter {
    private Context context;
    private List<MotionTypeBean> datas;
    private boolean isEdit;
    private OnItemClickListener onDeleteClickListener;
    private OnItemClickListener onItemClickListener;
    private OnItemClickListener onItemLongClickListener;
    private OnItemMoveListener onItemMoveListener;
    private OnStartDragListener onStartDragListener;
    private boolean enableDelete = true;
    private int mBottomMargin = DipPixelUtil.dip2px(150.0f);

    @Override // com.ido.life.customview.recyclerview.ItemTouchHelperAdapter
    public void onItemDismiss(int position) {
    }

    public final Context getContext() {
        return this.context;
    }

    public final List<MotionTypeBean> getDatas() {
        return this.datas;
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    public final void setDatas(List<MotionTypeBean> list) {
        this.datas = list;
    }

    public MotionTypeAdapter(Context context, List<MotionTypeBean> list) {
        this.context = context;
        this.datas = list;
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

    public final OnItemClickListener getOnDeleteClickListener() {
        return this.onDeleteClickListener;
    }

    public final void setOnDeleteClickListener(OnItemClickListener onItemClickListener) {
        this.onDeleteClickListener = onItemClickListener;
    }

    public final OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public final void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public final OnItemClickListener getOnItemLongClickListener() {
        return this.onItemLongClickListener;
    }

    public final void setOnItemLongClickListener(OnItemClickListener onItemClickListener) {
        this.onItemLongClickListener = onItemClickListener;
    }

    public final boolean getEnableDelete() {
        return this.enableDelete;
    }

    public final void setEnableDelete(boolean z) {
        this.enableDelete = z;
    }

    /* JADX INFO: renamed from: isEdit, reason: from getter */
    public final boolean getIsEdit() {
        return this.isEdit;
    }

    public final void setEdit(boolean z) {
        this.isEdit = z;
        notifyDataSetChanged();
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

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.item_motion_type, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…tion_type, parent, false)");
        return new ViewHolder(this, viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int iconResId;
        String name;
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        List<MotionTypeBean> list = this.datas;
        MotionTypeBean motionTypeBean = list != null ? list.get(position) : null;
        TextView tvMotionName = holder.getTvMotionName();
        if (tvMotionName != null) {
            if (motionTypeBean == null || (name = motionTypeBean.getName()) == null) {
                name = "";
            }
            tvMotionName.setText(name);
        }
        ImageView ivMotionIcon = holder.getIvMotionIcon();
        if (ivMotionIcon != null) {
            if ((motionTypeBean != null ? motionTypeBean.getIconResId() : 0) > 0) {
                if (motionTypeBean == null) {
                    Intrinsics.throwNpe();
                }
                iconResId = motionTypeBean.getIconResId();
            } else {
                iconResId = R.mipmap.motion_4;
            }
            ivMotionIcon.setImageResource(iconResId);
        }
        FrameLayout flDelete = holder.getFlDelete();
        if (flDelete != null) {
            flDelete.setEnabled(this.enableDelete);
        }
        ImageView ivOptIcon = holder.getIvOptIcon();
        if (ivOptIcon != null) {
            ivOptIcon.setVisibility(this.isEdit ? 0 : 8);
        }
        if (this.isEdit) {
            ImageView ivOptIcon2 = holder.getIvOptIcon();
            if (ivOptIcon2 != null) {
                ivOptIcon2.setOnTouchListener(new View.OnTouchListener() { // from class: com.ido.life.adapter.MotionTypeAdapter.onBindViewHolder.1
                    @Override // android.view.View.OnTouchListener
                    public final boolean onTouch(View view, MotionEvent event) {
                        OnStartDragListener onStartDragListener;
                        Intrinsics.checkParameterIsNotNull(event, "event");
                        if (event.getAction() != 0 || MotionTypeAdapter.this.getOnStartDragListener() == null || (onStartDragListener = MotionTypeAdapter.this.getOnStartDragListener()) == null) {
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
        holder.itemView.setOnLongClickListener(null);
        ImageView ivOptIcon3 = holder.getIvOptIcon();
        if (ivOptIcon3 != null) {
            ivOptIcon3.setOnTouchListener(null);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MotionTypeBean> list = this.datas;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* JADX INFO: compiled from: MotionTypeAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\"H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006$"}, d2 = {"Lcom/ido/life/adapter/MotionTypeAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/ido/life/customview/recyclerview/ItemTouchHelperViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/ido/life/adapter/MotionTypeAdapter;Landroid/view/View;)V", "flDelete", "Landroid/widget/FrameLayout;", "getFlDelete", "()Landroid/widget/FrameLayout;", "setFlDelete", "(Landroid/widget/FrameLayout;)V", "ivMotionIcon", "Landroid/widget/ImageView;", "getIvMotionIcon", "()Landroid/widget/ImageView;", "setIvMotionIcon", "(Landroid/widget/ImageView;)V", "ivOptIcon", "getIvOptIcon", "setIvOptIcon", "layout", "Lcom/ido/life/customview/recyclerview/SwipeItemLayout;", "getLayout", "()Lcom/ido/life/customview/recyclerview/SwipeItemLayout;", "setLayout", "(Lcom/ido/life/customview/recyclerview/SwipeItemLayout;)V", "tvMotionName", "Landroid/widget/TextView;", "getTvMotionName", "()Landroid/widget/TextView;", "setTvMotionName", "(Landroid/widget/TextView;)V", "onItemClear", "", "onItemSelected", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        private FrameLayout flDelete;
        private ImageView ivMotionIcon;
        private ImageView ivOptIcon;
        private SwipeItemLayout layout;
        final /* synthetic */ MotionTypeAdapter this$0;
        private TextView tvMotionName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(MotionTypeAdapter motionTypeAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            this.this$0 = motionTypeAdapter;
            this.layout = (SwipeItemLayout) itemView.findViewById(R.id.layout);
            this.ivMotionIcon = (ImageView) itemView.findViewById(R.id.ivMotionIcon);
            this.ivOptIcon = (ImageView) itemView.findViewById(R.id.ivOptIcon);
            this.flDelete = (FrameLayout) itemView.findViewById(R.id.flDelete);
            this.tvMotionName = (TextView) itemView.findViewById(R.id.tvMotionName);
            FrameLayout frameLayout = this.flDelete;
            if (frameLayout != null) {
                frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.adapter.MotionTypeAdapter.ViewHolder.1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        OnItemClickListener onDeleteClickListener = ViewHolder.this.this$0.getOnDeleteClickListener();
                        if (onDeleteClickListener != null) {
                            onDeleteClickListener.onItemClick(ViewHolder.this.getAdapterPosition());
                        }
                    }
                });
            }
        }

        public final ImageView getIvOptIcon() {
            return this.ivOptIcon;
        }

        public final void setIvOptIcon(ImageView imageView) {
            this.ivOptIcon = imageView;
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

        public final FrameLayout getFlDelete() {
            return this.flDelete;
        }

        public final void setFlDelete(FrameLayout frameLayout) {
            this.flDelete = frameLayout;
        }

        public final SwipeItemLayout getLayout() {
            return this.layout;
        }

        public final void setLayout(SwipeItemLayout swipeItemLayout) {
            this.layout = swipeItemLayout;
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
}