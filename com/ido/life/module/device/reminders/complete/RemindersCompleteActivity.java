package com.ido.life.module.device.reminders.complete;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.ScheduleReminderV3;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.CustomItemDecoration;
import com.ido.life.customview.LoadingLayout;
import com.ido.life.customview.recyclerview.SwipeItemLayout;
import com.ido.life.module.device.reminders.add.RemindAddActivity;
import com.ido.life.module.device.reminders.complete.RemindersCompleteActivity;
import com.ido.life.util.TimeUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RemindersCompleteActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0014J\"\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\b\u0010\u0015\u001a\u00020\u000eH\u0016J\u0012\u0010\u0016\u001a\u00020\u000e2\b\u0010\u0017\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\u0018\u001a\u00020\u000eH\u0016J\b\u0010\u0019\u001a\u00020\u000eH\u0002J\u0016\u0010\u001a\u001a\u00020\u000e2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u001cH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/ido/life/module/device/reminders/complete/RemindersCompleteActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/reminders/complete/RemindsCompletePresenter;", "Lcom/ido/life/module/device/reminders/complete/IRemindCompleteView;", "()V", "mRemindersAdapter", "Lcom/ido/life/module/device/reminders/complete/RemindersCompleteActivity$RemindersAdapter;", "mScheduleReminderV3", "Lcom/ido/ble/protocol/model/ScheduleReminderV3;", "mScheduleReminderV3List", "", "getLayoutResId", "", "initData", "", "onActivityResult", "requestCode", "resultCode", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Landroid/content/Intent;", "onBackPressed", "onDeleteEventReminderFailed", "onDeleteEventReminderSuccess", "scheduleReminderV3", "onQueryEventReminderFailed", "onUpdateRemindersCompleteSuccess", "setDeviceReminderList", "scheduleReminderV3List", "", "RemindersAdapter", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RemindersCompleteActivity extends BaseActivity<RemindsCompletePresenter> implements IRemindCompleteView {
    private HashMap _$_findViewCache;
    private RemindersAdapter mRemindersAdapter;
    private ScheduleReminderV3 mScheduleReminderV3;
    private List<ScheduleReminderV3> mScheduleReminderV3List = new ArrayList();

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_reminders_complete;
    }

    public static final /* synthetic */ RemindsCompletePresenter access$getMPresenter$p(RemindersCompleteActivity remindersCompleteActivity) {
        return (RemindsCompletePresenter) remindersCompleteActivity.mPresenter;
    }

    /* JADX INFO: compiled from: RemindersCompleteActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u001b\u001cB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\b\u0010\u0010\u001a\u00020\nH\u0016J\u001c\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0014\u001a\u00020\nH\u0016J\u001c\u0010\u0015\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\nH\u0016J\u000e\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\bR\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/ido/life/module/device/reminders/complete/RemindersCompleteActivity$RemindersAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/module/device/reminders/complete/RemindersCompleteActivity$RemindersAdapter$ViewHolder;", "reminderList", "", "Lcom/ido/ble/protocol/model/ScheduleReminderV3;", "(Ljava/util/List;)V", "mOnItemListener", "Lcom/ido/life/module/device/reminders/complete/RemindersCompleteActivity$RemindersAdapter$OnItemListener;", "mSelectedPos", "", "getReminderList", "()Ljava/util/List;", "deleteRemind", "", "scheduleReminderV3", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setOnItemListener", "onItemListener", "OnItemListener", "ViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class RemindersAdapter extends RecyclerView.Adapter<ViewHolder> {
        private OnItemListener mOnItemListener;
        private int mSelectedPos;
        private final List<ScheduleReminderV3> reminderList;

        /* JADX INFO: compiled from: RemindersCompleteActivity.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/ido/life/module/device/reminders/complete/RemindersCompleteActivity$RemindersAdapter$OnItemListener;", "", "onClickListener", "", "reminderMode", "Lcom/ido/ble/protocol/model/ScheduleReminderV3;", "onLongClickListener", "scheduleReminderV3", "app_release"}, k = 1, mv = {1, 1, 16})
        public interface OnItemListener {
            void onClickListener(ScheduleReminderV3 reminderMode);

            void onLongClickListener(ScheduleReminderV3 scheduleReminderV3);
        }

        public static final /* synthetic */ OnItemListener access$getMOnItemListener$p(RemindersAdapter remindersAdapter) {
            OnItemListener onItemListener = remindersAdapter.mOnItemListener;
            if (onItemListener == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mOnItemListener");
            }
            return onItemListener;
        }

        public final List<ScheduleReminderV3> getReminderList() {
            return this.reminderList;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public RemindersAdapter(List<? extends ScheduleReminderV3> reminderList) {
            Intrinsics.checkParameterIsNotNull(reminderList, "reminderList");
            this.reminderList = reminderList;
            this.mSelectedPos = -1;
        }

        public final void setOnItemListener(OnItemListener onItemListener) {
            Intrinsics.checkParameterIsNotNull(onItemListener, "onItemListener");
            this.mOnItemListener = onItemListener;
        }

        /* JADX INFO: compiled from: RemindersCompleteActivity.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/ido/life/module/device/reminders/complete/RemindersCompleteActivity$RemindersAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/ido/life/module/device/reminders/complete/RemindersCompleteActivity$RemindersAdapter;Landroid/view/View;)V", "ivSlideDelete", "Landroid/widget/ImageView;", "getIvSlideDelete", "()Landroid/widget/ImageView;", "remindCheck", "Landroid/widget/CheckBox;", "getRemindCheck", "()Landroid/widget/CheckBox;", "tvDateTime", "Landroid/widget/TextView;", "getTvDateTime", "()Landroid/widget/TextView;", "tvDesc", "getTvDesc", "app_release"}, k = 1, mv = {1, 1, 16})
        public final class ViewHolder extends RecyclerView.ViewHolder {
            private final ImageView ivSlideDelete;
            private final CheckBox remindCheck;
            final /* synthetic */ RemindersAdapter this$0;
            private final TextView tvDateTime;
            private final TextView tvDesc;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ViewHolder(RemindersAdapter remindersAdapter, View view) {
                super(view);
                Intrinsics.checkParameterIsNotNull(view, "view");
                this.this$0 = remindersAdapter;
                View viewFindViewById = view.findViewById(R.id.tv_remind_desc);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "view.findViewById(R.id.tv_remind_desc)");
                this.tvDesc = (TextView) viewFindViewById;
                View viewFindViewById2 = view.findViewById(R.id.check_reminder);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "view.findViewById(R.id.check_reminder)");
                this.remindCheck = (CheckBox) viewFindViewById2;
                View viewFindViewById3 = view.findViewById(R.id.tv_time);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "view.findViewById(R.id.tv_time)");
                this.tvDateTime = (TextView) viewFindViewById3;
                View viewFindViewById4 = view.findViewById(R.id.ivSlideDelete);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "view.findViewById(R.id.ivSlideDelete)");
                this.ivSlideDelete = (ImageView) viewFindViewById4;
                this.ivSlideDelete.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.reminders.complete.RemindersCompleteActivity.RemindersAdapter.ViewHolder.1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        ViewHolder.this.this$0.deleteRemind(ViewHolder.this.this$0.getReminderList().get(ViewHolder.this.getAdapterPosition()));
                    }
                });
            }

            public final TextView getTvDesc() {
                return this.tvDesc;
            }

            public final CheckBox getRemindCheck() {
                return this.remindCheck;
            }

            public final TextView getTvDateTime() {
                return this.tvDateTime;
            }

            public final ImageView getIvSlideDelete() {
                return this.ivSlideDelete;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Intrinsics.checkParameterIsNotNull(parent, "parent");
            View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_reminder, parent, false);
            Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(pare…_reminder, parent, false)");
            return new ViewHolder(this, viewInflate);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v2, types: [T, com.ido.ble.protocol.model.ScheduleReminderV3] */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            Intrinsics.checkParameterIsNotNull(holder, "holder");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = this.reminderList.get(position);
            holder.getTvDesc().setText(((ScheduleReminderV3) objectRef.element).getTitle());
            if (!StringsKt.contains$default((CharSequence) String.valueOf(((ScheduleReminderV3) objectRef.element).getYear()), (CharSequence) String.valueOf(TimeUtil.getCurrentYear()), false, 2, (Object) null)) {
                holder.getTvDateTime().setText(String.valueOf(((ScheduleReminderV3) objectRef.element).getYear()) + "/" + String.valueOf(((ScheduleReminderV3) objectRef.element).getMon()) + "/" + String.valueOf(((ScheduleReminderV3) objectRef.element).getDay()));
            } else {
                holder.getTvDateTime().setText(String.valueOf(((ScheduleReminderV3) objectRef.element).getMon()) + "/" + String.valueOf(((ScheduleReminderV3) objectRef.element).getDay()));
            }
            View view = holder.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
            view.setTag(Integer.valueOf(position));
            holder.getRemindCheck().setChecked(this.mSelectedPos != position);
            holder.getTvDateTime().setTextColor(ResourceUtil.getColor(R.color.color_82868F));
            holder.getRemindCheck().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ido.life.module.device.reminders.complete.RemindersCompleteActivity$RemindersAdapter$onBindViewHolder$1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    Intrinsics.checkExpressionValueIsNotNull(compoundButton, "compoundButton");
                    if (compoundButton.isPressed()) {
                        if (holder.getRemindCheck().isChecked()) {
                            this.this$0.mSelectedPos = -1;
                            this.this$0.notifyDataSetChanged();
                        } else {
                            this.this$0.mSelectedPos = position;
                            this.this$0.notifyDataSetChanged();
                        }
                    }
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.reminders.complete.RemindersCompleteActivity$RemindersAdapter$onBindViewHolder$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    if (this.this$0.mSelectedPos == position || RemindersCompleteActivity.RemindersAdapter.access$getMOnItemListener$p(this.this$0) == null) {
                        return;
                    }
                    RemindersCompleteActivity.RemindersAdapter.access$getMOnItemListener$p(this.this$0).onClickListener((ScheduleReminderV3) objectRef.element);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean deleteRemind(ScheduleReminderV3 scheduleReminderV3) {
            OnItemListener onItemListener = this.mOnItemListener;
            if (onItemListener == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mOnItemListener");
            }
            if (onItemListener == null) {
                return true;
            }
            OnItemListener onItemListener2 = this.mOnItemListener;
            if (onItemListener2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mOnItemListener");
            }
            if (onItemListener2 == null) {
                return true;
            }
            onItemListener2.onLongClickListener(scheduleReminderV3);
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.reminderList.size();
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mTitleBar.setTitle(LanguageUtil.getLanguageText(R.string.device_reminders_complete_title));
        this.mTitleBar.getTitleLayoutLeft(this).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.reminders.complete.RemindersCompleteActivity.initData.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RemindersCompleteActivity.this.onUpdateRemindersCompleteSuccess();
            }
        });
        List<ScheduleReminderV3> list = this.mScheduleReminderV3List;
        this.mRemindersAdapter = list != null ? new RemindersAdapter(list) : null;
        RecyclerView recycler_reminds_complete = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_reminds_complete);
        Intrinsics.checkExpressionValueIsNotNull(recycler_reminds_complete, "recycler_reminds_complete");
        recycler_reminds_complete.setAdapter(this.mRemindersAdapter);
        RemindersAdapter remindersAdapter = this.mRemindersAdapter;
        if (remindersAdapter != null) {
            remindersAdapter.setOnItemListener(new RemindersAdapter.OnItemListener() { // from class: com.ido.life.module.device.reminders.complete.RemindersCompleteActivity.initData.3
                @Override // com.ido.life.module.device.reminders.complete.RemindersCompleteActivity.RemindersAdapter.OnItemListener
                public void onClickListener(ScheduleReminderV3 scheduleReminderV3) {
                    Intrinsics.checkParameterIsNotNull(scheduleReminderV3, "scheduleReminderV3");
                }

                @Override // com.ido.life.module.device.reminders.complete.RemindersCompleteActivity.RemindersAdapter.OnItemListener
                public void onLongClickListener(ScheduleReminderV3 scheduleReminderV3) {
                    Intrinsics.checkParameterIsNotNull(scheduleReminderV3, "scheduleReminderV3");
                    RemindersCompleteActivity.this.showLoadingDialog();
                    RemindersCompleteActivity.access$getMPresenter$p(RemindersCompleteActivity.this).deleteScheduleReminder(scheduleReminderV3);
                }
            });
        }
        RemindersCompleteActivity remindersCompleteActivity = this;
        ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_reminds_complete)).addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(remindersCompleteActivity));
        ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_reminds_complete)).addItemDecoration(new CustomItemDecoration().color(ContextCompat.getColor(remindersCompleteActivity, R.color.translate)).height(DipPixelUtil.dip2pxF(12.0f)));
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.loadLayout)).showLoading();
        ((RemindsCompletePresenter) this.mPresenter).queryScheduleReminder();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        onUpdateRemindersCompleteSuccess();
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onUpdateRemindersCompleteSuccess() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        List<ScheduleReminderV3> list = this.mScheduleReminderV3List;
        if (list != null) {
            bundle.putInt("RemindersCompleteNumber", list.size());
        }
        intent.putExtras(bundle);
        setResult(GLMapStaticValue.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER, intent);
        finish();
    }

    @Override // com.ido.life.module.device.reminders.complete.IRemindCompleteView
    public void onQueryEventReminderFailed() {
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.loadLayout)).showContent();
        showToast("查询失败");
    }

    @Override // com.ido.life.module.device.reminders.complete.IRemindCompleteView
    public void setDeviceReminderList(List<? extends ScheduleReminderV3> scheduleReminderV3List) {
        Intrinsics.checkParameterIsNotNull(scheduleReminderV3List, "scheduleReminderV3List");
        ((LoadingLayout) _$_findCachedViewById(com.ido.life.R.id.loadLayout)).showContent();
        List<ScheduleReminderV3> list = this.mScheduleReminderV3List;
        if (list != null) {
            list.addAll(scheduleReminderV3List);
        }
        RemindersAdapter remindersAdapter = this.mRemindersAdapter;
        if (remindersAdapter != null) {
            remindersAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.ido.life.module.device.reminders.complete.IRemindCompleteView
    public void onDeleteEventReminderSuccess(ScheduleReminderV3 scheduleReminderV3) {
        dismissLoadingDialog();
        if (scheduleReminderV3 != null) {
            List<ScheduleReminderV3> list = this.mScheduleReminderV3List;
            if (list != null) {
                list.remove(scheduleReminderV3);
            }
            RemindersAdapter remindersAdapter = this.mRemindersAdapter;
            if (remindersAdapter != null) {
                remindersAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override // com.ido.life.module.device.reminders.complete.IRemindCompleteView
    public void onDeleteEventReminderFailed() {
        dismissLoadingDialog();
        showCmdResultToast(false);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Bundle extras = data != null ? data.getExtras() : null;
            if (extras != null) {
                extras.getSerializable(RemindAddActivity.RESULT_SCHEDULE);
            }
        }
    }
}