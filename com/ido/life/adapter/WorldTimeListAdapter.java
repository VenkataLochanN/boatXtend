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
import com.ido.life.bean.WorldTimeCity;
import com.ido.life.customview.OnItemClickListener;
import com.ido.life.customview.OnItemMoveListener;
import com.ido.life.customview.recyclerview.ItemTouchHelperAdapter;
import com.ido.life.customview.recyclerview.ItemTouchHelperViewHolder;
import com.ido.life.customview.recyclerview.OnStartDragListener;
import com.ido.life.util.CalendarUtils;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorldTimeListAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 B2\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u00012\u00020\u0003:\u0002BCB\u001f\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\u0012\u00101\u001a\u0004\u0018\u00010\b2\u0006\u00102\u001a\u000203H\u0002J\b\u00104\u001a\u000203H\u0016J\u001c\u00105\u001a\u0002062\n\u00107\u001a\u00060\u0002R\u00020\u00002\u0006\u00102\u001a\u000203H\u0016J\u001c\u00108\u001a\u00060\u0002R\u00020\u00002\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u000203H\u0016J\u0010\u0010<\u001a\u0002062\u0006\u00102\u001a\u000203H\u0016J\u0018\u0010=\u001a\u00020\u00132\u0006\u0010>\u001a\u0002032\u0006\u0010?\u001a\u000203H\u0016J\u0014\u0010@\u001a\u0002062\n\u00107\u001a\u00060\u0002R\u00020\u0000H\u0002J\u0006\u0010A\u001a\u000206R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R\u001c\u0010$\u001a\u0004\u0018\u00010%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001c\u0010*\u001a\u0004\u0018\u00010+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u000e\u00100\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lcom/ido/life/adapter/WorldTimeListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/adapter/WorldTimeListAdapter$ViewHolder;", "Lcom/ido/life/customview/recyclerview/ItemTouchHelperAdapter;", "context", "Landroid/content/Context;", "datas", "", "Lcom/ido/life/bean/WorldTimeCity;", "(Landroid/content/Context;Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getDatas", "()Ljava/util/List;", "setDatas", "(Ljava/util/List;)V", "enableDelete", "", "getEnableDelete", "()Z", "setEnableDelete", "(Z)V", "value", "isEdit", "setEdit", "onDeleteClickListener", "Lcom/ido/life/customview/OnItemClickListener;", "getOnDeleteClickListener", "()Lcom/ido/life/customview/OnItemClickListener;", "setOnDeleteClickListener", "(Lcom/ido/life/customview/OnItemClickListener;)V", "onItemClickListener", "getOnItemClickListener", "setOnItemClickListener", "onItemMoveListener", "Lcom/ido/life/customview/OnItemMoveListener;", "getOnItemMoveListener", "()Lcom/ido/life/customview/OnItemMoveListener;", "setOnItemMoveListener", "(Lcom/ido/life/customview/OnItemMoveListener;)V", "onStartDragListener", "Lcom/ido/life/customview/recyclerview/OnStartDragListener;", "getOnStartDragListener", "()Lcom/ido/life/customview/recyclerview/OnStartDragListener;", "setOnStartDragListener", "(Lcom/ido/life/customview/recyclerview/OnStartDragListener;)V", "refreshFlag", "getItem", "position", "", "getItemCount", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onItemDismiss", "onItemMove", "fromPosition", "toPosition", "processView", "refreshTimer", "Companion", "ViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WorldTimeListAdapter extends RecyclerView.Adapter<ViewHolder> implements ItemTouchHelperAdapter {
    public static final String TAG = "WorldTimeListAdapter";
    private Context context;
    private List<WorldTimeCity> datas;
    private boolean enableDelete;
    private boolean isEdit;
    private OnItemClickListener onDeleteClickListener;
    private OnItemClickListener onItemClickListener;
    private OnItemMoveListener onItemMoveListener;
    private OnStartDragListener onStartDragListener;
    private boolean refreshFlag;

    @Override // com.ido.life.customview.recyclerview.ItemTouchHelperAdapter
    public void onItemDismiss(int position) {
    }

    public final Context getContext() {
        return this.context;
    }

    public final List<WorldTimeCity> getDatas() {
        return this.datas;
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    public final void setDatas(List<WorldTimeCity> list) {
        this.datas = list;
    }

    public WorldTimeListAdapter(Context context, List<WorldTimeCity> list) {
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

    public final void refreshTimer() {
        if (this.isEdit) {
            return;
        }
        this.refreshFlag = !this.refreshFlag;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.item_world_time_list, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…time_list, parent, false)");
        return new ViewHolder(this, viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        processView(holder);
        List<WorldTimeCity> list = this.datas;
        WorldTimeCity worldTimeCity = list != null ? list.get(position) : null;
        if (worldTimeCity != null) {
            TextView tvCityName = holder.getTvCityName();
            if (tvCityName != null) {
                tvCityName.setText(worldTimeCity.getName());
            }
            TextView tvTimeDiff = holder.getTvTimeDiff();
            if (tvTimeDiff != null) {
                CalendarUtils calendarUtils = CalendarUtils.INSTANCE;
                TimeZone timeZone = TimeZone.getDefault();
                Intrinsics.checkExpressionValueIsNotNull(timeZone, "TimeZone.getDefault()");
                TimeZone timeZone2 = TimeZone.getTimeZone(worldTimeCity.getTimeZoneName());
                Intrinsics.checkExpressionValueIsNotNull(timeZone2, "TimeZone.getTimeZone(it.timeZoneName)");
                tvTimeDiff.setText(calendarUtils.getTimezoneDifferenceStr(timeZone, timeZone2));
            }
            TextView tvTime = holder.getTvTime();
            if (tvTime != null) {
                CalendarUtils calendarUtils2 = CalendarUtils.INSTANCE;
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(worldTimeCity.getTimeZoneName()));
                Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance(\n  …      )\n                )");
                tvTime.setText(calendarUtils2.getTime(calendar));
            }
        }
    }

    private final void processView(final ViewHolder holder) {
        ImageView ivOptIcon = holder.getIvOptIcon();
        if (ivOptIcon != null) {
            ivOptIcon.setImageResource(this.isEdit ? R.mipmap.icon_drag : R.mipmap.icon_world_time_right_arrow);
        }
        TextView tvTime = holder.getTvTime();
        if (tvTime != null) {
            tvTime.setVisibility(this.isEdit ? 8 : 0);
        }
        ImageView ivSlideDelete = holder.getIvSlideDelete();
        if (ivSlideDelete != null) {
            ivSlideDelete.setEnabled(this.enableDelete);
        }
        if (this.isEdit) {
            ImageView ivOptIcon2 = holder.getIvOptIcon();
            if (ivOptIcon2 != null) {
                ivOptIcon2.setOnTouchListener(new View.OnTouchListener() { // from class: com.ido.life.adapter.WorldTimeListAdapter.processView.1
                    @Override // android.view.View.OnTouchListener
                    public final boolean onTouch(View view, MotionEvent event) {
                        OnStartDragListener onStartDragListener;
                        Intrinsics.checkParameterIsNotNull(event, "event");
                        if (event.getAction() != 0 || WorldTimeListAdapter.this.getOnStartDragListener() == null || (onStartDragListener = WorldTimeListAdapter.this.getOnStartDragListener()) == null) {
                            return false;
                        }
                        onStartDragListener.onStartDrag(holder);
                        return false;
                    }
                });
            }
        } else {
            ImageView ivOptIcon3 = holder.getIvOptIcon();
            if (ivOptIcon3 != null) {
                ivOptIcon3.setOnTouchListener(null);
            }
        }
        View vRefresh = holder.getVRefresh();
        if (vRefresh != null) {
            vRefresh.setVisibility(this.refreshFlag ? 0 : 8);
        }
    }

    private final WorldTimeCity getItem(int position) {
        List<WorldTimeCity> list = this.datas;
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<WorldTimeCity> list = this.datas;
        if (list != null) {
            return list.size();
        }
        return 0;
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

    /* JADX INFO: compiled from: WorldTimeListAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020*H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR\u001c\u0010!\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010\u001dR\u001c\u0010$\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u0006,"}, d2 = {"Lcom/ido/life/adapter/WorldTimeListAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/ido/life/customview/recyclerview/ItemTouchHelperViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/ido/life/adapter/WorldTimeListAdapter;Landroid/view/View;)V", "flDelete", "Landroid/widget/FrameLayout;", "getFlDelete", "()Landroid/widget/FrameLayout;", "setFlDelete", "(Landroid/widget/FrameLayout;)V", "ivDelete", "Landroid/widget/ImageView;", "getIvDelete", "()Landroid/widget/ImageView;", "setIvDelete", "(Landroid/widget/ImageView;)V", "ivOptIcon", "getIvOptIcon", "setIvOptIcon", "ivSlideDelete", "getIvSlideDelete", "setIvSlideDelete", "tvCityName", "Landroid/widget/TextView;", "getTvCityName", "()Landroid/widget/TextView;", "setTvCityName", "(Landroid/widget/TextView;)V", "tvTime", "getTvTime", "setTvTime", "tvTimeDiff", "getTvTimeDiff", "setTvTimeDiff", "vRefresh", "getVRefresh", "()Landroid/view/View;", "setVRefresh", "(Landroid/view/View;)V", "onItemClear", "", "onItemSelected", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        private FrameLayout flDelete;
        private ImageView ivDelete;
        private ImageView ivOptIcon;
        private ImageView ivSlideDelete;
        final /* synthetic */ WorldTimeListAdapter this$0;
        private TextView tvCityName;
        private TextView tvTime;
        private TextView tvTimeDiff;
        private View vRefresh;

        @Override // com.ido.life.customview.recyclerview.ItemTouchHelperViewHolder
        public void onItemClear() {
        }

        @Override // com.ido.life.customview.recyclerview.ItemTouchHelperViewHolder
        public void onItemSelected() {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(WorldTimeListAdapter worldTimeListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            this.this$0 = worldTimeListAdapter;
            this.vRefresh = itemView.findViewById(R.id.vRefresh);
            this.flDelete = (FrameLayout) itemView.findViewById(R.id.flDelete);
            this.tvTimeDiff = (TextView) itemView.findViewById(R.id.tvTimeDiff);
            this.tvCityName = (TextView) itemView.findViewById(R.id.tvCityName);
            this.tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            this.ivOptIcon = (ImageView) itemView.findViewById(R.id.ivOptIcon);
            this.ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);
            this.ivSlideDelete = (ImageView) itemView.findViewById(R.id.ivSlideDelete);
            itemView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.adapter.WorldTimeListAdapter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OnItemClickListener onItemClickListener;
                    if (ViewHolder.this.this$0.getIsEdit() || (onItemClickListener = ViewHolder.this.this$0.getOnItemClickListener()) == null) {
                        return;
                    }
                    onItemClickListener.onItemClick(ViewHolder.this.getAdapterPosition());
                }
            });
            ImageView imageView = this.ivSlideDelete;
            if (imageView != null) {
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.adapter.WorldTimeListAdapter.ViewHolder.2
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

        public final ImageView getIvDelete() {
            return this.ivDelete;
        }

        public final void setIvDelete(ImageView imageView) {
            this.ivDelete = imageView;
        }

        public final ImageView getIvSlideDelete() {
            return this.ivSlideDelete;
        }

        public final void setIvSlideDelete(ImageView imageView) {
            this.ivSlideDelete = imageView;
        }

        public final TextView getTvTime() {
            return this.tvTime;
        }

        public final void setTvTime(TextView textView) {
            this.tvTime = textView;
        }

        public final TextView getTvCityName() {
            return this.tvCityName;
        }

        public final void setTvCityName(TextView textView) {
            this.tvCityName = textView;
        }

        public final TextView getTvTimeDiff() {
            return this.tvTimeDiff;
        }

        public final void setTvTimeDiff(TextView textView) {
            this.tvTimeDiff = textView;
        }

        public final FrameLayout getFlDelete() {
            return this.flDelete;
        }

        public final void setFlDelete(FrameLayout frameLayout) {
            this.flDelete = frameLayout;
        }

        public final View getVRefresh() {
            return this.vRefresh;
        }

        public final void setVRefresh(View view) {
            this.vRefresh = view;
        }
    }
}