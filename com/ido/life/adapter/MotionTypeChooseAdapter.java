package com.ido.life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.common.utils.DipPixelUtil;
import com.ido.life.bean.MotionTypeBean;
import com.ido.life.customview.OnItemClickListener;
import com.ido.life.data.cache.MotionTypeManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MotionTypeChooseAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001BB5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00070\tj\b\u0012\u0004\u0012\u00020\u0007`\n¢\u0006\u0002\u0010\u000bJ\b\u0010/\u001a\u00020\u0015H\u0002J\b\u00100\u001a\u00020\u0015H\u0002J\u0012\u00101\u001a\u0004\u0018\u00010\u00072\u0006\u00102\u001a\u00020!H\u0002J\b\u00103\u001a\u00020!H\u0016J\u0016\u00104\u001a\u0012\u0012\u0004\u0012\u00020\u00070\tj\b\u0012\u0004\u0012\u00020\u0007`\nJ\u0010\u00105\u001a\u00020\u00152\u0006\u00102\u001a\u00020!H\u0002J\u0010\u00106\u001a\u00020\u00152\u0006\u00102\u001a\u00020!H\u0002J\u0012\u00107\u001a\u00020\u00152\b\u00108\u001a\u0004\u0018\u00010\u0007H\u0002J\u001c\u00109\u001a\u00020:2\n\u0010;\u001a\u00060\u0002R\u00020\u00002\u0006\u00102\u001a\u00020!H\u0016J\u001c\u0010<\u001a\u00060\u0002R\u00020\u00002\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020!H\u0016J\u001c\u0010@\u001a\u00020:2\n\u0010;\u001a\u00060\u0002R\u00020\u00002\u0006\u00102\u001a\u00020!H\u0002J\u0006\u0010A\u001a\u00020:R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R$\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0015@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00070\tj\b\u0012\u0004\u0012\u00020\u0007`\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010&\u001a\u0004\u0018\u00010'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001c\u0010,\u001a\u0004\u0018\u00010'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010)\"\u0004\b.\u0010+¨\u0006C"}, d2 = {"Lcom/ido/life/adapter/MotionTypeChooseAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/adapter/MotionTypeChooseAdapter$ViewHolder;", "context", "Landroid/content/Context;", "datas", "", "Lcom/ido/life/bean/MotionTypeBean;", "mSelectedDatas", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(Landroid/content/Context;Ljava/util/List;Ljava/util/ArrayList;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getDatas", "()Ljava/util/List;", "setDatas", "(Ljava/util/List;)V", "enableAdd", "", "getEnableAdd", "()Z", "setEnableAdd", "(Z)V", "enableDelete", "getEnableDelete", "setEnableDelete", "value", "isEdit", "setEdit", "mBottomMargin", "", "getMSelectedDatas", "()Ljava/util/ArrayList;", "setMSelectedDatas", "(Ljava/util/ArrayList;)V", "onAddClickListener", "Lcom/ido/life/customview/OnItemClickListener;", "getOnAddClickListener", "()Lcom/ido/life/customview/OnItemClickListener;", "setOnAddClickListener", "(Lcom/ido/life/customview/OnItemClickListener;)V", "onDeleteClickListener", "getOnDeleteClickListener", "setOnDeleteClickListener", "checkIfCanAdd", "checkIfCanDelete", "getItem", "position", "getItemCount", "getSelectedDatas", "isFirstInGroup", "isLastInGroup", "isSelected", AeUtil.ROOT_DATA_PATH_OLD_NAME, "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setOnClickListener", "update", "ViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MotionTypeChooseAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<MotionTypeBean> datas;
    private boolean enableAdd;
    private boolean enableDelete;
    private boolean isEdit;
    private int mBottomMargin;
    private ArrayList<MotionTypeBean> mSelectedDatas;
    private OnItemClickListener onAddClickListener;
    private OnItemClickListener onDeleteClickListener;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    public final List<MotionTypeBean> getDatas() {
        return this.datas;
    }

    public final void setDatas(List<MotionTypeBean> list) {
        this.datas = list;
    }

    public final ArrayList<MotionTypeBean> getMSelectedDatas() {
        return this.mSelectedDatas;
    }

    public final void setMSelectedDatas(ArrayList<MotionTypeBean> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.mSelectedDatas = arrayList;
    }

    public MotionTypeChooseAdapter(Context context, List<MotionTypeBean> list, ArrayList<MotionTypeBean> mSelectedDatas) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(mSelectedDatas, "mSelectedDatas");
        this.context = context;
        this.datas = list;
        this.mSelectedDatas = mSelectedDatas;
        this.enableDelete = true;
        this.enableAdd = true;
        this.mBottomMargin = DipPixelUtil.dip2px(20.0f);
    }

    public final OnItemClickListener getOnDeleteClickListener() {
        return this.onDeleteClickListener;
    }

    public final void setOnDeleteClickListener(OnItemClickListener onItemClickListener) {
        this.onDeleteClickListener = onItemClickListener;
    }

    public final OnItemClickListener getOnAddClickListener() {
        return this.onAddClickListener;
    }

    public final void setOnAddClickListener(OnItemClickListener onItemClickListener) {
        this.onAddClickListener = onItemClickListener;
    }

    public final boolean getEnableDelete() {
        return this.enableDelete;
    }

    public final void setEnableDelete(boolean z) {
        this.enableDelete = z;
    }

    public final boolean getEnableAdd() {
        return this.enableAdd;
    }

    public final void setEnableAdd(boolean z) {
        this.enableAdd = z;
    }

    /* JADX INFO: renamed from: isEdit, reason: from getter */
    public final boolean getIsEdit() {
        return this.isEdit;
    }

    public final void setEdit(boolean z) {
        this.isEdit = z;
        notifyDataSetChanged();
    }

    public final void update() {
        this.enableDelete = checkIfCanDelete();
        this.enableAdd = checkIfCanAdd();
        notifyDataSetChanged();
    }

    public final ArrayList<MotionTypeBean> getSelectedDatas() {
        return this.mSelectedDatas;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.item_motion_type_choose, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…pe_choose, parent, false)");
        return new ViewHolder(this, viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        MotionTypeBean item = getItem(position);
        if (item != null) {
            TextView tvMotionName = holder.getTvMotionName();
            if (tvMotionName != null) {
                tvMotionName.setText(item.getName());
            }
            ImageView ivMotionIcon = holder.getIvMotionIcon();
            if (ivMotionIcon != null) {
                ivMotionIcon.setImageResource(item.getIconResId() > 0 ? item.getIconResId() : R.mipmap.motion_4);
            }
            boolean zIsFirstInGroup = isFirstInGroup(position);
            boolean zIsLastInGroup = isLastInGroup(position);
            boolean z = true;
            boolean z2 = (zIsFirstInGroup && zIsLastInGroup) || zIsFirstInGroup;
            int i = (zIsFirstInGroup && zIsLastInGroup) ? R.drawable.bg_motion_type_choose_list_only_one : zIsFirstInGroup ? R.drawable.bg_motion_type_choose_list_first : zIsLastInGroup ? R.drawable.bg_motion_type_choose_list_last : R.drawable.bg_motion_type_choose_list_mid;
            RelativeLayout rlContent = holder.getRlContent();
            if (rlContent != null) {
                rlContent.setBackground(ContextCompat.getDrawable(this.context, i));
            }
            TextView tvGroupTitle = holder.getTvGroupTitle();
            if (tvGroupTitle != null) {
                tvGroupTitle.setVisibility(z2 ? 0 : 8);
            }
            TextView tvGroupTitle2 = holder.getTvGroupTitle();
            if (tvGroupTitle2 != null) {
                tvGroupTitle2.setText(z2 ? MotionTypeManager.INSTANCE.getGroupName(item.getGroupType()) : "");
            }
            View vDivider = holder.getVDivider();
            if (vDivider != null) {
                vDivider.setVisibility(zIsLastInGroup ? 8 : 0);
            }
            holder.itemView.setPadding(0, 0, 0, zIsLastInGroup ? this.mBottomMargin : 0);
            boolean zIsSelected = isSelected(item);
            ImageView ivOptIcon = holder.getIvOptIcon();
            if (ivOptIcon != null) {
                ivOptIcon.setImageDrawable(ContextCompat.getDrawable(this.context, zIsSelected ? R.drawable.selector_motion_type_delete : R.drawable.selector_motion_type_add));
            }
            ImageView ivMotionIcon2 = holder.getIvMotionIcon();
            if (ivMotionIcon2 != null) {
                ivMotionIcon2.setAlpha(zIsSelected ? 1.0f : 0.4f);
            }
            TextView tvMotionName2 = holder.getTvMotionName();
            if (tvMotionName2 != null) {
                tvMotionName2.setEnabled(!zIsSelected);
            }
            ImageView ivOptIcon2 = holder.getIvOptIcon();
            if (ivOptIcon2 != null) {
                ivOptIcon2.setVisibility(this.isEdit ? 0 : 8);
            }
            ImageView ivOptIcon3 = holder.getIvOptIcon();
            if (ivOptIcon3 != null) {
                if ((!zIsSelected || !this.enableDelete) && (zIsSelected || !this.enableAdd)) {
                    z = false;
                }
                ivOptIcon3.setEnabled(z);
            }
            setOnClickListener(holder, position);
        }
    }

    private final boolean isLastInGroup(int position) {
        MotionTypeBean item = getItem(position);
        if (item == null) {
            return false;
        }
        List<MotionTypeBean> list = this.datas;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        if (position == list.size() - 1) {
            return true;
        }
        int groupType = item.getGroupType();
        List<MotionTypeBean> list2 = this.datas;
        if (list2 == null) {
            Intrinsics.throwNpe();
        }
        return groupType != list2.get(position + 1).getGroupType();
    }

    private final boolean isFirstInGroup(int position) {
        MotionTypeBean item = getItem(position);
        if (position == 0) {
            return true;
        }
        List<MotionTypeBean> list = this.datas;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        return item == null || list.get(position - 1).getGroupType() != item.getGroupType();
    }

    private final void setOnClickListener(ViewHolder holder, final int position) {
        ImageView ivOptIcon = holder.getIvOptIcon();
        if (ivOptIcon != null) {
            ivOptIcon.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.adapter.MotionTypeChooseAdapter.setOnClickListener.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MotionTypeBean item = MotionTypeChooseAdapter.this.getItem(position);
                    if (item != null) {
                        if (MotionTypeChooseAdapter.this.isSelected(item)) {
                            MotionTypeChooseAdapter.this.getMSelectedDatas().remove(item);
                            OnItemClickListener onDeleteClickListener = MotionTypeChooseAdapter.this.getOnDeleteClickListener();
                            if (onDeleteClickListener != null) {
                                onDeleteClickListener.onItemClick(position);
                            }
                        } else {
                            MotionTypeChooseAdapter.this.getMSelectedDatas().add(item);
                            OnItemClickListener onAddClickListener = MotionTypeChooseAdapter.this.getOnAddClickListener();
                            if (onAddClickListener != null) {
                                onAddClickListener.onItemClick(position);
                            }
                        }
                        boolean zCheckIfCanDelete = MotionTypeChooseAdapter.this.checkIfCanDelete();
                        boolean zCheckIfCanAdd = MotionTypeChooseAdapter.this.checkIfCanAdd();
                        if (zCheckIfCanAdd != MotionTypeChooseAdapter.this.getEnableAdd() || zCheckIfCanDelete != MotionTypeChooseAdapter.this.getEnableDelete()) {
                            MotionTypeChooseAdapter.this.setEnableAdd(zCheckIfCanAdd);
                            MotionTypeChooseAdapter.this.setEnableDelete(zCheckIfCanDelete);
                            MotionTypeChooseAdapter.this.notifyDataSetChanged();
                            return;
                        }
                        MotionTypeChooseAdapter.this.notifyItemChanged(position);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkIfCanAdd() {
        return this.mSelectedDatas.size() < MotionTypeManager.INSTANCE.getMAX_MOTION_TYPE();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkIfCanDelete() {
        return this.mSelectedDatas.size() > MotionTypeManager.INSTANCE.getMIN_MOTION_TYPE();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MotionTypeBean> list = this.datas;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MotionTypeBean getItem(int position) {
        List<MotionTypeBean> list = this.datas;
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isSelected(MotionTypeBean data) {
        return CollectionsKt.contains(this.mSelectedDatas, data);
    }

    /* JADX INFO: compiled from: MotionTypeChooseAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006\""}, d2 = {"Lcom/ido/life/adapter/MotionTypeChooseAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/ido/life/adapter/MotionTypeChooseAdapter;Landroid/view/View;)V", "ivMotionIcon", "Landroid/widget/ImageView;", "getIvMotionIcon", "()Landroid/widget/ImageView;", "setIvMotionIcon", "(Landroid/widget/ImageView;)V", "ivOptIcon", "getIvOptIcon", "setIvOptIcon", "rlContent", "Landroid/widget/RelativeLayout;", "getRlContent", "()Landroid/widget/RelativeLayout;", "setRlContent", "(Landroid/widget/RelativeLayout;)V", "tvGroupTitle", "Landroid/widget/TextView;", "getTvGroupTitle", "()Landroid/widget/TextView;", "setTvGroupTitle", "(Landroid/widget/TextView;)V", "tvMotionName", "getTvMotionName", "setTvMotionName", "vDivider", "getVDivider", "()Landroid/view/View;", "setVDivider", "(Landroid/view/View;)V", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivMotionIcon;
        private ImageView ivOptIcon;
        private RelativeLayout rlContent;
        final /* synthetic */ MotionTypeChooseAdapter this$0;
        private TextView tvGroupTitle;
        private TextView tvMotionName;
        private View vDivider;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(MotionTypeChooseAdapter motionTypeChooseAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            this.this$0 = motionTypeChooseAdapter;
            this.ivMotionIcon = (ImageView) itemView.findViewById(R.id.ivMotionIcon);
            this.ivOptIcon = (ImageView) itemView.findViewById(R.id.ivOptIcon);
            this.tvMotionName = (TextView) itemView.findViewById(R.id.tvMotionName);
            this.tvGroupTitle = (TextView) itemView.findViewById(R.id.tvGroupTitle);
            this.rlContent = (RelativeLayout) itemView.findViewById(R.id.rlContent);
            this.vDivider = itemView.findViewById(R.id.vDivider);
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

        public final TextView getTvGroupTitle() {
            return this.tvGroupTitle;
        }

        public final void setTvGroupTitle(TextView textView) {
            this.tvGroupTitle = textView;
        }

        public final RelativeLayout getRlContent() {
            return this.rlContent;
        }

        public final void setRlContent(RelativeLayout relativeLayout) {
            this.rlContent = relativeLayout;
        }

        public final View getVDivider() {
            return this.vDivider;
        }

        public final void setVDivider(View view) {
            this.vDivider = view;
        }
    }
}