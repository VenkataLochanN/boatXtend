package com.ido.life.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.bean.MyDialProxy;
import com.ido.life.customview.OnItemClickListener;
import com.ido.life.customview.OnItemMoveListener;
import com.ido.life.customview.recyclerview.ItemTouchHelperAdapter;
import com.ido.life.customview.recyclerview.OnStartDragListener;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.module.device.activity.MyDialEditActivity;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MydialEditListAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u00012\u00020\u0003:\u0001TB3\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000eJ\u0016\u0010@\u001a\u00020A2\u000e\u0010B\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007J\u000e\u0010C\u001a\u00020\b2\u0006\u0010D\u001a\u00020$J\b\u0010E\u001a\u00020$H\u0016J\u001c\u0010F\u001a\u00020A2\n\u0010G\u001a\u00060\u0002R\u00020\u00002\u0006\u0010D\u001a\u00020$H\u0016J\u001c\u0010H\u001a\u00060\u0002R\u00020\u00002\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020$H\u0016J\u0010\u0010L\u001a\u00020A2\u0006\u0010D\u001a\u00020$H\u0016J\u0018\u0010M\u001a\u00020\f2\u0006\u0010N\u001a\u00020$2\u0006\u0010O\u001a\u00020$H\u0016J\u000e\u0010P\u001a\u00020A2\u0006\u0010Q\u001a\u00020\fJ\u0010\u0010R\u001a\u00020A2\b\u0010S\u001a\u0004\u0018\u00010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0019\"\u0004\b \u0010\u001bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u001c\u0010+\u001a\u0004\u0018\u00010,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u00101\u001a\u0004\u0018\u00010,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010.\"\u0004\b3\u00100R\u001c\u00104\u001a\u0004\u0018\u000105X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001c\u0010:\u001a\u0004\u0018\u00010;X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?¨\u0006U"}, d2 = {"Lcom/ido/life/adapter/MydialEditListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/adapter/MydialEditListAdapter$ViewHolder;", "Lcom/ido/life/customview/recyclerview/ItemTouchHelperAdapter;", "context", "Lcom/ido/life/module/device/activity/MyDialEditActivity;", "list", "", "Lcom/ido/life/data/api/entity/MyDialListEntity$DialInfo;", "mRatio", "", "mIsBracelet", "", "mIsCircle", "(Lcom/ido/life/module/device/activity/MyDialEditActivity;Ljava/util/List;FZZ)V", "getContext", "()Lcom/ido/life/module/device/activity/MyDialEditActivity;", "currentDialName", "", "getCurrentDialName", "()Ljava/lang/String;", "setCurrentDialName", "(Ljava/lang/String;)V", "value", "isEdit", "()Z", "setEdit", "(Z)V", "getList", "()Ljava/util/List;", "mDeletable", "getMDeletable", "setMDeletable", "getMIsBracelet", "getMIsCircle", "mRadius", "", "getMRadius", "()I", "setMRadius", "(I)V", "getMRatio", "()F", "onDeleteClickListener", "Lcom/ido/life/customview/OnItemClickListener;", "getOnDeleteClickListener", "()Lcom/ido/life/customview/OnItemClickListener;", "setOnDeleteClickListener", "(Lcom/ido/life/customview/OnItemClickListener;)V", "onItemClickListener", "getOnItemClickListener", "setOnItemClickListener", "onItemMoveListener", "Lcom/ido/life/customview/OnItemMoveListener;", "getOnItemMoveListener", "()Lcom/ido/life/customview/OnItemMoveListener;", "setOnItemMoveListener", "(Lcom/ido/life/customview/OnItemMoveListener;)V", "onStartDragListener", "Lcom/ido/life/customview/recyclerview/OnStartDragListener;", "getOnStartDragListener", "()Lcom/ido/life/customview/recyclerview/OnStartDragListener;", "setOnStartDragListener", "(Lcom/ido/life/customview/recyclerview/OnStartDragListener;)V", "addAll", "", "datas", "getItem", "position", "getItemCount", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onItemDismiss", "onItemMove", "fromPosition", "toPosition", "setDeletable", "deletable", "updateCurrentDial", "currentDial", "ViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MydialEditListAdapter extends RecyclerView.Adapter<ViewHolder> implements ItemTouchHelperAdapter {
    private final MyDialEditActivity context;
    private String currentDialName;
    private boolean isEdit;
    private final List<MyDialListEntity.DialInfo> list;
    private boolean mDeletable;
    private final boolean mIsBracelet;
    private final boolean mIsCircle;
    private int mRadius;
    private final float mRatio;
    private OnItemClickListener onDeleteClickListener;
    private OnItemClickListener onItemClickListener;
    private OnItemMoveListener onItemMoveListener;
    private OnStartDragListener onStartDragListener;

    public final MyDialEditActivity getContext() {
        return this.context;
    }

    public final List<MyDialListEntity.DialInfo> getList() {
        return this.list;
    }

    public final float getMRatio() {
        return this.mRatio;
    }

    public final boolean getMIsBracelet() {
        return this.mIsBracelet;
    }

    public final boolean getMIsCircle() {
        return this.mIsCircle;
    }

    public MydialEditListAdapter(MyDialEditActivity context, List<MyDialListEntity.DialInfo> list, float f2, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(list, "list");
        this.context = context;
        this.list = list;
        this.mRatio = f2;
        this.mIsBracelet = z;
        this.mIsCircle = z2;
        this.currentDialName = "";
        this.mRadius = ResourceUtil.getDimens(this.mIsBracelet ? R.dimen.sw_dp_16 : R.dimen.sw_dp_18);
        this.isEdit = true;
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

    public final String getCurrentDialName() {
        return this.currentDialName;
    }

    public final void setCurrentDialName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.currentDialName = str;
    }

    public final int getMRadius() {
        return this.mRadius;
    }

    public final void setMRadius(int i) {
        this.mRadius = i;
    }

    public final boolean getMDeletable() {
        return this.mDeletable;
    }

    public final void setMDeletable(boolean z) {
        this.mDeletable = z;
    }

    public final void setDeletable(boolean deletable) {
        this.mDeletable = deletable;
        notifyDataSetChanged();
    }

    public final void addAll(List<MyDialListEntity.DialInfo> datas) {
        List<MyDialListEntity.DialInfo> list;
        List<MyDialListEntity.DialInfo> list2 = this.list;
        if (list2 != null) {
            list2.clear();
        }
        if (datas != null && (list = this.list) != null) {
            list.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public final void updateCurrentDial(String currentDial) {
        if (currentDial != null) {
            this.currentDialName = currentDial;
        }
        notifyDataSetChanged();
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
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.item_mydial_edit_view_list, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…view_list, parent, false)");
        return new ViewHolder(this, viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String otaFaceName;
        String otaFaceName2;
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        MyDialListEntity.DialInfo item = getItem(position);
        TextView tvDialName = holder.getTvDialName();
        if (tvDialName != null) {
            if (item == null || (otaFaceName2 = item.getOtaFaceName()) == null) {
                otaFaceName2 = "";
            }
            tvDialName.setText(otaFaceName2);
        }
        RelativeLayout rtDial = holder.getRtDial();
        ViewGroup.LayoutParams layoutParams = rtDial != null ? rtDial.getLayoutParams() : null;
        if (layoutParams != null) {
            layoutParams.width = (int) (DipPixelUtil.dip2px(94.0f) / this.mRatio);
        }
        if (layoutParams != null) {
            layoutParams.height = DipPixelUtil.dip2px(94.0f);
        }
        RelativeLayout rtDial2 = holder.getRtDial();
        if (rtDial2 != null) {
            rtDial2.setLayoutParams(layoutParams);
        }
        RelativeLayout rtDial3 = holder.getRtDial();
        if (rtDial3 != null) {
            rtDial3.setBackgroundResource(this.mIsCircle ? R.drawable.dial_frame_circle_bg : this.mIsBracelet ? R.drawable.dial_frame_bracelet_bg : R.drawable.dial_frame_watch_bg);
        }
        int i = this.mIsCircle ? R.mipmap.ic_default_dial_oval : this.mIsBracelet ? R.mipmap.ic_default_dial_bracelet : this.mRatio > ((float) 1) ? R.mipmap.ic_default_dial_rectangle : R.mipmap.ic_default_dial_square;
        MyDialProxy myDialProxy = new MyDialProxy(item);
        ImageLoaderUtil.loadImgFillet(holder.getIvDialIcon(), myDialProxy.getImageUrl(), myDialProxy.hasRefresh(), this.mRadius, i);
        if (this.isEdit) {
            ImageView ivOptIcon = holder.getIvOptIcon();
            if (ivOptIcon != null) {
                ivOptIcon.setOnTouchListener(new View.OnTouchListener() { // from class: com.ido.life.adapter.MydialEditListAdapter.onBindViewHolder.1
                    @Override // android.view.View.OnTouchListener
                    public final boolean onTouch(View view, MotionEvent event) {
                        OnStartDragListener onStartDragListener;
                        Intrinsics.checkParameterIsNotNull(event, "event");
                        if (event.getAction() != 0 || MydialEditListAdapter.this.getOnStartDragListener() == null || (onStartDragListener = MydialEditListAdapter.this.getOnStartDragListener()) == null) {
                            return false;
                        }
                        onStartDragListener.onStartDrag(holder);
                        return false;
                    }
                });
            }
        } else {
            ImageView ivOptIcon2 = holder.getIvOptIcon();
            if (ivOptIcon2 != null) {
                ivOptIcon2.setVisibility(8);
            }
        }
        if (!TextUtils.isEmpty(this.currentDialName) && item != null && (otaFaceName = item.getOtaFaceName()) != null && StringsKt.contains$default((CharSequence) otaFaceName, (CharSequence) StringsKt.replace$default(this.currentDialName, ".iwf", "", false, 4, (Object) null), false, 2, (Object) null)) {
            TextView tvCurrentDial = holder.getTvCurrentDial();
            if (tvCurrentDial != null) {
                tvCurrentDial.setVisibility(0);
            }
            item.isCurrentDial = true;
        } else {
            item.isCurrentDial = false;
            TextView tvCurrentDial2 = holder.getTvCurrentDial();
            if (tvCurrentDial2 != null) {
                tvCurrentDial2.setVisibility(8);
            }
        }
        ImageView ivDialDelete = holder.getIvDialDelete();
        if (ivDialDelete != null) {
            ivDialDelete.setEnabled(this.mDeletable);
        }
        if (item.isLocal() || !this.mDeletable) {
            ImageView ivDialDelete2 = holder.getIvDialDelete();
            if (ivDialDelete2 != null) {
                ivDialDelete2.setBackgroundResource(R.mipmap.icon_motion_type_delete_disable);
                return;
            }
            return;
        }
        ImageView ivDialDelete3 = holder.getIvDialDelete();
        if (ivDialDelete3 != null) {
            ivDialDelete3.setBackgroundResource(R.mipmap.icon_motion_type_delete);
        }
        ImageView ivDialDelete4 = holder.getIvDialDelete();
        if (ivDialDelete4 != null) {
            ivDialDelete4.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.adapter.MydialEditListAdapter.onBindViewHolder.2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OnItemClickListener onDeleteClickListener = MydialEditListAdapter.this.getOnDeleteClickListener();
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.onItemClick(holder.getAdapterPosition());
                    }
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MyDialListEntity.DialInfo> list = this.list;
        return (list != null ? Integer.valueOf(list.size()) : null).intValue();
    }

    public final MyDialListEntity.DialInfo getItem(int position) {
        List<MyDialListEntity.DialInfo> list = this.list;
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    /* JADX INFO: compiled from: MydialEditListAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001c¨\u0006 "}, d2 = {"Lcom/ido/life/adapter/MydialEditListAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/ido/life/adapter/MydialEditListAdapter;Landroid/view/View;)V", "ivDialDelete", "Landroid/widget/ImageView;", "getIvDialDelete", "()Landroid/widget/ImageView;", "setIvDialDelete", "(Landroid/widget/ImageView;)V", "ivDialIcon", "getIvDialIcon", "setIvDialIcon", "ivOptIcon", "getIvOptIcon", "setIvOptIcon", "rtDial", "Landroid/widget/RelativeLayout;", "getRtDial", "()Landroid/widget/RelativeLayout;", "setRtDial", "(Landroid/widget/RelativeLayout;)V", "tvCurrentDial", "Landroid/widget/TextView;", "getTvCurrentDial", "()Landroid/widget/TextView;", "setTvCurrentDial", "(Landroid/widget/TextView;)V", "tvDialName", "getTvDialName", "setTvDialName", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivDialDelete;
        private ImageView ivDialIcon;
        private ImageView ivOptIcon;
        private RelativeLayout rtDial;
        final /* synthetic */ MydialEditListAdapter this$0;
        private TextView tvCurrentDial;
        private TextView tvDialName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(MydialEditListAdapter mydialEditListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            this.this$0 = mydialEditListAdapter;
            this.ivDialDelete = (ImageView) itemView.findViewById(R.id.ivDialDelete);
            this.ivDialIcon = (ImageView) itemView.findViewById(R.id.ivDialIcon);
            this.tvDialName = (TextView) itemView.findViewById(R.id.tvDialName);
            this.ivOptIcon = (ImageView) itemView.findViewById(R.id.ivOptIcon);
            this.tvCurrentDial = (TextView) itemView.findViewById(R.id.tvCurrentDial);
            this.rtDial = (RelativeLayout) itemView.findViewById(R.id.rldial);
            itemView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.adapter.MydialEditListAdapter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OnItemClickListener onItemClickListener = ViewHolder.this.this$0.getOnItemClickListener();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(ViewHolder.this.getAdapterPosition());
                    }
                }
            });
        }

        public final ImageView getIvDialDelete() {
            return this.ivDialDelete;
        }

        public final void setIvDialDelete(ImageView imageView) {
            this.ivDialDelete = imageView;
        }

        public final ImageView getIvDialIcon() {
            return this.ivDialIcon;
        }

        public final void setIvDialIcon(ImageView imageView) {
            this.ivDialIcon = imageView;
        }

        public final TextView getTvDialName() {
            return this.tvDialName;
        }

        public final void setTvDialName(TextView textView) {
            this.tvDialName = textView;
        }

        public final ImageView getIvOptIcon() {
            return this.ivOptIcon;
        }

        public final void setIvOptIcon(ImageView imageView) {
            this.ivOptIcon = imageView;
        }

        public final TextView getTvCurrentDial() {
            return this.tvCurrentDial;
        }

        public final void setTvCurrentDial(TextView textView) {
            this.tvCurrentDial = textView;
        }

        public final RelativeLayout getRtDial() {
            return this.rtDial;
        }

        public final void setRtDial(RelativeLayout relativeLayout) {
            this.rtDial = relativeLayout;
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

    @Override // com.ido.life.customview.recyclerview.ItemTouchHelperAdapter
    public void onItemDismiss(int position) {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }
}