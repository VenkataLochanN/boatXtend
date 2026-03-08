package com.ido.life.module.device.reminders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.ScheduleReminderV3;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.CustomItemDecoration;
import com.ido.life.customview.recyclerview.SwipeItemLayout;
import com.ido.life.module.device.reminders.RemindersActivity;
import com.ido.life.module.device.reminders.add.RemindAddActivity;
import com.ido.life.module.device.reminders.complete.RemindersCompleteActivity;
import com.ido.life.util.CalendarUtils;
import com.ido.life.util.DateUtil;
import com.ido.life.util.TimeUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RemindersActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\t\u0018\u0000 /2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0002/0B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0007H\u0014J\b\u0010\u0013\u001a\u00020\u0011H\u0014J\b\u0010\u0014\u001a\u00020\u0011H\u0014J\"\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0012\u0010\u001a\u001a\u00020\u00112\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0011H\u0016J\u0012\u0010\u001e\u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010 \u001a\u00020\u0011H\u0016J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u0007H\u0016J\u0016\u0010&\u001a\u00020\u00112\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000b0(H\u0016J\u0010\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u0007H\u0016J\u0018\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020#2\u0006\u0010-\u001a\u00020#H\u0016J\b\u0010.\u001a\u00020\u0011H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/ido/life/module/device/reminders/RemindersActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/reminders/RemindersPresenter;", "Lcom/ido/life/module/device/reminders/IRemindersView;", "Landroid/view/View$OnClickListener;", "()V", "mCompletedRemind", "", "mId", "mRemindList", "", "Lcom/ido/ble/protocol/model/ScheduleReminderV3;", "mRemindersAdapter", "Lcom/ido/life/module/device/reminders/RemindersActivity$RemindersAdapter;", "onSwipeItemTouchListener", "Lcom/ido/life/customview/recyclerview/SwipeItemLayout$OnSwipeItemTouchListener;", "addRemind", "", "getLayoutResId", "initData", "initEvent", "onActivityResult", "requestCode", "resultCode", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "onDeleteEventReminderFailed", "onDeleteEventReminderSuccess", "scheduleReminderV3", "onQueryEventReminderFailed", "setComplete", "isComplete", "", "setCompletedDeviceReminderList", "int", "setDeviceReminderList", "scheduleReminderV3List", "", "setId", "id", "setNoReminderLayout", "hasNotCompletedData", "hasCompleteData", "sort", "Companion", "RemindersAdapter", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RemindersActivity extends BaseActivity<RemindersPresenter> implements IRemindersView, View.OnClickListener {
    private static final int REMIND_MAX = 30;
    public static final int REQUEST_CODE = 1000;
    private static final String TAG = "RemindersActivity";
    private HashMap _$_findViewCache;
    private int mCompletedRemind;
    private int mId;
    private List<ScheduleReminderV3> mRemindList = new ArrayList();
    private RemindersAdapter mRemindersAdapter;
    private SwipeItemLayout.OnSwipeItemTouchListener onSwipeItemTouchListener;

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
        return R.layout.activity_reminders;
    }

    public static final /* synthetic */ RemindersPresenter access$getMPresenter$p(RemindersActivity remindersActivity) {
        return (RemindersPresenter) remindersActivity.mPresenter;
    }

    /* JADX INFO: compiled from: RemindersActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u001a\u001bB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u000fH\u0016J\u001c\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000fH\u0016J\u000e\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\bR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Lcom/ido/life/module/device/reminders/RemindersActivity$RemindersAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/module/device/reminders/RemindersActivity$RemindersAdapter$ViewHolder;", "reminderList", "", "Lcom/ido/ble/protocol/model/ScheduleReminderV3;", "(Ljava/util/List;)V", "mOnItemListener", "Lcom/ido/life/module/device/reminders/RemindersActivity$RemindersAdapter$OnItemListener;", "getReminderList", "()Ljava/util/List;", "deleteRemind", "", "scheduleReminderV3", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setOnItemListener", "onItemListener", "OnItemListener", "ViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class RemindersAdapter extends RecyclerView.Adapter<ViewHolder> {
        private OnItemListener mOnItemListener;
        private final List<ScheduleReminderV3> reminderList;

        /* JADX INFO: compiled from: RemindersActivity.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/ido/life/module/device/reminders/RemindersActivity$RemindersAdapter$OnItemListener;", "", "onClickListener", "", "scheduleReminderV3", "Lcom/ido/ble/protocol/model/ScheduleReminderV3;", "onLongClickListener", "app_release"}, k = 1, mv = {1, 1, 16})
        public interface OnItemListener {
            void onClickListener(ScheduleReminderV3 scheduleReminderV3);

            void onLongClickListener(ScheduleReminderV3 scheduleReminderV3);
        }

        public final List<ScheduleReminderV3> getReminderList() {
            return this.reminderList;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public RemindersAdapter(List<? extends ScheduleReminderV3> reminderList) {
            Intrinsics.checkParameterIsNotNull(reminderList, "reminderList");
            this.reminderList = reminderList;
        }

        public final void setOnItemListener(OnItemListener onItemListener) {
            Intrinsics.checkParameterIsNotNull(onItemListener, "onItemListener");
            this.mOnItemListener = onItemListener;
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
        public void onBindViewHolder(ViewHolder holder, int position) {
            Intrinsics.checkParameterIsNotNull(holder, "holder");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = this.reminderList.get(position);
            holder.getTvDesc().setText(((ScheduleReminderV3) objectRef.element).getTitle());
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            Calendar compareDay = Calendar.getInstance();
            compareDay.set(1, ((ScheduleReminderV3) objectRef.element).getYear());
            compareDay.set(2, ((ScheduleReminderV3) objectRef.element).getMon() - 1);
            compareDay.set(5, ((ScheduleReminderV3) objectRef.element).getDay());
            CalendarUtils calendarUtils = CalendarUtils.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            Intrinsics.checkExpressionValueIsNotNull(compareDay, "compareDay");
            int typeOfDay = calendarUtils.getTypeOfDay(calendar, compareDay);
            holder.getTvDateTime().setTextColor(ResourceUtil.getColor(R.color.color_82868F));
            if (typeOfDay == 0 || typeOfDay == 1) {
                holder.getTvDateTime().setText(CalendarUtils.INSTANCE.getTypeOfDayStr(typeOfDay));
                if (typeOfDay == 0) {
                    holder.getTvDateTime().setTextColor(ResourceUtil.getColor(R.color.color_FF4826));
                }
            } else if (!StringsKt.contains$default((CharSequence) String.valueOf(((ScheduleReminderV3) objectRef.element).getYear()), (CharSequence) String.valueOf(TimeUtil.getCurrentYear()), false, 2, (Object) null)) {
                holder.getTvDateTime().setText(String.valueOf(((ScheduleReminderV3) objectRef.element).getYear()) + "/" + String.valueOf(((ScheduleReminderV3) objectRef.element).getMon()) + "/" + String.valueOf(((ScheduleReminderV3) objectRef.element).getDay()));
            } else {
                holder.getTvDateTime().setText(String.valueOf(((ScheduleReminderV3) objectRef.element).getMon()) + "/" + String.valueOf(((ScheduleReminderV3) objectRef.element).getDay()));
            }
            View view = holder.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
            view.setTag(Integer.valueOf(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.reminders.RemindersActivity$RemindersAdapter$onBindViewHolder$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    RemindersActivity.RemindersAdapter.OnItemListener onItemListener;
                    if (this.this$0.mOnItemListener == null || (onItemListener = this.this$0.mOnItemListener) == null) {
                        return;
                    }
                    onItemListener.onClickListener((ScheduleReminderV3) objectRef.element);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean deleteRemind(ScheduleReminderV3 scheduleReminderV3) {
            OnItemListener onItemListener = this.mOnItemListener;
            if (onItemListener == null || onItemListener == null) {
                return true;
            }
            onItemListener.onLongClickListener(scheduleReminderV3);
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.reminderList.size();
        }

        /* JADX INFO: compiled from: RemindersActivity.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/ido/life/module/device/reminders/RemindersActivity$RemindersAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/ido/life/module/device/reminders/RemindersActivity$RemindersAdapter;Landroid/view/View;)V", "ivSlideDelete", "Landroid/widget/ImageView;", "getIvSlideDelete", "()Landroid/widget/ImageView;", "remindCheck", "Landroid/widget/CheckBox;", "getRemindCheck", "()Landroid/widget/CheckBox;", "tvDateTime", "Landroid/widget/TextView;", "getTvDateTime", "()Landroid/widget/TextView;", "tvDesc", "getTvDesc", "app_release"}, k = 1, mv = {1, 1, 16})
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
                this.ivSlideDelete.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.reminders.RemindersActivity.RemindersAdapter.ViewHolder.1
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
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mTitleBar.initLayout(1);
        this.mTitleBar.setRightImg(R.mipmap.icon_add_device);
        LinearLayout titleLayoutRight = this.mTitleBar.getTitleLayoutRight(this);
        Intrinsics.checkExpressionValueIsNotNull(titleLayoutRight, "mTitleBar.getTitleLayoutRight(this)");
        titleLayoutRight.setVisibility(4);
        this.mTitleBar.setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.reminders.RemindersActivity.initData.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RemindersActivity.this.addRemind();
            }
        });
        boolean[] zArr = new boolean[7];
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            zArr[i] = false;
        }
        ((RemindersPresenter) this.mPresenter).queryScheduleReminder();
        this.mRemindersAdapter = new RemindersAdapter(this.mRemindList);
        RecyclerView recycler_reminds = (RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_reminds);
        Intrinsics.checkExpressionValueIsNotNull(recycler_reminds, "recycler_reminds");
        recycler_reminds.setAdapter(this.mRemindersAdapter);
        RemindersActivity remindersActivity = this;
        this.onSwipeItemTouchListener = new SwipeItemLayout.OnSwipeItemTouchListener(remindersActivity);
        SwipeItemLayout.OnSwipeItemTouchListener onSwipeItemTouchListener = this.onSwipeItemTouchListener;
        if (onSwipeItemTouchListener != null) {
            ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_reminds)).addOnItemTouchListener(onSwipeItemTouchListener);
        }
        ((RecyclerView) _$_findCachedViewById(com.ido.life.R.id.recycler_reminds)).addItemDecoration(new CustomItemDecoration().color(ContextCompat.getColor(remindersActivity, R.color.translate)).height(DipPixelUtil.dip2pxF(12.0f)));
        RemindersAdapter remindersAdapter = this.mRemindersAdapter;
        if (remindersAdapter != null) {
            remindersAdapter.setOnItemListener(new RemindersAdapter.OnItemListener() { // from class: com.ido.life.module.device.reminders.RemindersActivity.initData.3
                @Override // com.ido.life.module.device.reminders.RemindersActivity.RemindersAdapter.OnItemListener
                public void onClickListener(ScheduleReminderV3 scheduleReminderV3) {
                    Intrinsics.checkParameterIsNotNull(scheduleReminderV3, "scheduleReminderV3");
                    Intent intent = new Intent(RemindersActivity.this, (Class<?>) RemindAddActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(RemindAddActivity.EXTRA_SCHEDULE_REMINDER, scheduleReminderV3);
                    intent.putExtras(bundle);
                    RemindersActivity.this.startActivityForResult(intent, 1000);
                }

                @Override // com.ido.life.module.device.reminders.RemindersActivity.RemindersAdapter.OnItemListener
                public void onLongClickListener(ScheduleReminderV3 scheduleReminderV3) {
                    Intrinsics.checkParameterIsNotNull(scheduleReminderV3, "scheduleReminderV3");
                    RemindersActivity.this.showLoadingDialog(false);
                    RemindersActivity.access$getMPresenter$p(RemindersActivity.this).deleteScheduleReminder(scheduleReminderV3);
                }
            });
        }
        showLoadingDialog();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        RemindersActivity remindersActivity = this;
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete)).setOnClickListener(remindersActivity);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete_new)).setOnClickListener(remindersActivity);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_add)).setOnClickListener(remindersActivity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addRemind() {
        if (this.mCompletedRemind < 30) {
            Bundle bundle = new Bundle();
            bundle.putInt(RemindAddActivity.EXTRA_SCHEDULE_ID, this.mId);
            Intent intent = new Intent(this, (Class<?>) RemindAddActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1000);
            return;
        }
        CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.device_remind_max_count), LanguageUtil.getLanguageText(R.string.me_all_sure_ios), "", false).show(getSupportFragmentManager());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if ((numValueOf != null && numValueOf.intValue() == R.id.tv_complete) || (numValueOf != null && numValueOf.intValue() == R.id.tv_complete_new)) {
            startActivityForResult(new Intent(this, (Class<?>) RemindersCompleteActivity.class), GLMapStaticValue.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER);
        } else if (numValueOf != null && numValueOf.intValue() == R.id.tv_add) {
            addRemind();
        }
    }

    @Override // com.ido.life.module.device.reminders.IRemindersView
    public void onQueryEventReminderFailed() {
        Log.d(TAG, "onQueryEventReminderFailed: ");
        TextView tv_no_remind = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_no_remind);
        Intrinsics.checkExpressionValueIsNotNull(tv_no_remind, "tv_no_remind");
        tv_no_remind.setVisibility(0);
        NestedScrollView lay_content = (NestedScrollView) _$_findCachedViewById(com.ido.life.R.id.lay_content);
        Intrinsics.checkExpressionValueIsNotNull(lay_content, "lay_content");
        lay_content.setVisibility(8);
        TextView tv_complete = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete);
        Intrinsics.checkExpressionValueIsNotNull(tv_complete, "tv_complete");
        tv_complete.setVisibility(8);
        TextView tv_add = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_add);
        Intrinsics.checkExpressionValueIsNotNull(tv_add, "tv_add");
        tv_add.setVisibility(0);
        dismissLoadingDialog();
        showCmdResultToast(false);
    }

    @Override // com.ido.life.module.device.reminders.IRemindersView
    public void onDeleteEventReminderSuccess(ScheduleReminderV3 scheduleReminderV3) {
        Log.d(TAG, "onDeleteEventReminderSuccess: ");
        dismissLoadingDialog();
        if (scheduleReminderV3 != null) {
            this.mRemindList.remove(scheduleReminderV3);
            this.mCompletedRemind--;
            RemindersAdapter remindersAdapter = this.mRemindersAdapter;
            if (remindersAdapter != null) {
                remindersAdapter.notifyDataSetChanged();
            }
        }
        List<ScheduleReminderV3> list = this.mRemindList;
        if (list != null && list.size() > 0) {
            setNoReminderLayout(true, this.mCompletedRemind - this.mRemindList.size() > 0);
        } else {
            setNoReminderLayout(false, this.mCompletedRemind > 0);
        }
    }

    @Override // com.ido.life.module.device.reminders.IRemindersView
    public void onDeleteEventReminderFailed() {
        Log.d(TAG, "onDeleteEventReminderFailed: ");
        dismissLoadingDialog();
        showCmdResultToast(false);
    }

    @Override // com.ido.life.module.device.reminders.IRemindersView
    public void setDeviceReminderList(List<? extends ScheduleReminderV3> scheduleReminderV3List) {
        Intrinsics.checkParameterIsNotNull(scheduleReminderV3List, "scheduleReminderV3List");
        Log.d(TAG, "setDeviceReminderList: ");
        TextView tv_no_remind = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_no_remind);
        Intrinsics.checkExpressionValueIsNotNull(tv_no_remind, "tv_no_remind");
        tv_no_remind.setVisibility(8);
        NestedScrollView lay_content = (NestedScrollView) _$_findCachedViewById(com.ido.life.R.id.lay_content);
        Intrinsics.checkExpressionValueIsNotNull(lay_content, "lay_content");
        lay_content.setVisibility(0);
        TextView tv_complete = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete);
        Intrinsics.checkExpressionValueIsNotNull(tv_complete, "tv_complete");
        tv_complete.setVisibility(8);
        TextView tv_add = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_add);
        Intrinsics.checkExpressionValueIsNotNull(tv_add, "tv_add");
        tv_add.setVisibility(8);
        this.mRemindList.clear();
        this.mRemindList.addAll(scheduleReminderV3List);
        sort();
        RemindersAdapter remindersAdapter = this.mRemindersAdapter;
        if (remindersAdapter != null) {
            remindersAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.ido.life.module.device.reminders.IRemindersView
    public void setCompletedDeviceReminderList(int i) {
        this.mCompletedRemind = i;
    }

    @Override // com.ido.life.module.device.reminders.IRemindersView
    public void setNoReminderLayout(boolean hasNotCompletedData, boolean hasCompleteData) {
        dismissLoadingDialog();
        Log.d(TAG, "setNoReminderLayout:hasNoData= " + hasNotCompletedData + "--hasCompleteData--" + hasCompleteData);
        int i = 0;
        if (hasNotCompletedData) {
            TextView tv_no_remind = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_no_remind);
            Intrinsics.checkExpressionValueIsNotNull(tv_no_remind, "tv_no_remind");
            tv_no_remind.setVisibility(8);
            NestedScrollView lay_content = (NestedScrollView) _$_findCachedViewById(com.ido.life.R.id.lay_content);
            Intrinsics.checkExpressionValueIsNotNull(lay_content, "lay_content");
            lay_content.setVisibility(0);
        } else {
            TextView tv_no_remind2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_no_remind);
            Intrinsics.checkExpressionValueIsNotNull(tv_no_remind2, "tv_no_remind");
            tv_no_remind2.setVisibility(0);
            NestedScrollView lay_content2 = (NestedScrollView) _$_findCachedViewById(com.ido.life.R.id.lay_content);
            Intrinsics.checkExpressionValueIsNotNull(lay_content2, "lay_content");
            lay_content2.setVisibility(8);
        }
        TextView tv_complete = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete);
        Intrinsics.checkExpressionValueIsNotNull(tv_complete, "tv_complete");
        tv_complete.setVisibility(8);
        TextView tv_complete_new = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete_new);
        Intrinsics.checkExpressionValueIsNotNull(tv_complete_new, "tv_complete_new");
        tv_complete_new.setVisibility(8);
        if (hasCompleteData) {
            if (hasNotCompletedData) {
                TextView tv_complete_new2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete_new);
                Intrinsics.checkExpressionValueIsNotNull(tv_complete_new2, "tv_complete_new");
                tv_complete_new2.setVisibility(0);
            } else {
                TextView tv_complete2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete);
                Intrinsics.checkExpressionValueIsNotNull(tv_complete2, "tv_complete");
                tv_complete2.setVisibility(0);
            }
        }
        if (hasCompleteData || hasNotCompletedData) {
            TextView tv_add = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_add);
            Intrinsics.checkExpressionValueIsNotNull(tv_add, "tv_add");
            tv_add.setVisibility(8);
        } else {
            TextView tv_add2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_add);
            Intrinsics.checkExpressionValueIsNotNull(tv_add2, "tv_add");
            tv_add2.setVisibility(0);
        }
        if (!hasNotCompletedData) {
            this.mTitleBar.setTitle(LanguageUtil.getLanguageText(R.string.device_reminders_title));
            TextView tv_complete_number = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete_number);
            Intrinsics.checkExpressionValueIsNotNull(tv_complete_number, "tv_complete_number");
            tv_complete_number.setVisibility(8);
            TextView tv_complete_tip = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete_tip);
            Intrinsics.checkExpressionValueIsNotNull(tv_complete_tip, "tv_complete_tip");
            tv_complete_tip.setVisibility(8);
        } else {
            TextView tv_complete_number2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete_number);
            Intrinsics.checkExpressionValueIsNotNull(tv_complete_number2, "tv_complete_number");
            tv_complete_number2.setVisibility(0);
            TextView tv_complete_tip2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete_tip);
            Intrinsics.checkExpressionValueIsNotNull(tv_complete_tip2, "tv_complete_tip");
            tv_complete_tip2.setVisibility(0);
            ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete_number)).setText("(" + this.mCompletedRemind + "/30)");
            this.mTitleBar.setTitle("");
        }
        LinearLayout titleLayoutRight = this.mTitleBar.getTitleLayoutRight(this);
        Intrinsics.checkExpressionValueIsNotNull(titleLayoutRight, "mTitleBar.getTitleLayoutRight(this)");
        if (!hasNotCompletedData && !hasCompleteData) {
            i = 4;
        }
        titleLayoutRight.setVisibility(i);
        TextView tv_no_remind3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_no_remind);
        Intrinsics.checkExpressionValueIsNotNull(tv_no_remind3, "tv_no_remind");
        tv_no_remind3.setText(getLanguageText(hasCompleteData ? R.string.no_reminder_data : R.string.device_reminders_no));
    }

    @Override // com.ido.life.module.device.reminders.IRemindersView
    public void setId(int id) {
        this.mId = id;
    }

    @Override // com.ido.life.module.device.reminders.IRemindersView
    public void setComplete(boolean isComplete) {
        if (isComplete) {
            TextView tv_complete = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete);
            Intrinsics.checkExpressionValueIsNotNull(tv_complete, "tv_complete");
            tv_complete.setVisibility(0);
        } else {
            TextView tv_complete2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_complete);
            Intrinsics.checkExpressionValueIsNotNull(tv_complete2, "tv_complete");
            tv_complete2.setVisibility(8);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Bundle extras = data != null ? data.getExtras() : null;
            if ((extras != null ? extras.getSerializable(RemindAddActivity.RESULT_SCHEDULE) : null) != null) {
                Serializable serializable = extras != null ? extras.getSerializable(RemindAddActivity.RESULT_SCHEDULE) : null;
                if (serializable == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.ido.ble.protocol.model.ScheduleReminderV3");
                }
                ScheduleReminderV3 scheduleReminderV3 = (ScheduleReminderV3) serializable;
                setNoReminderLayout(false, true);
                if ((extras != null ? Boolean.valueOf(extras.getBoolean("isAdd")) : null).booleanValue()) {
                    this.mId = scheduleReminderV3.getId() + 1;
                    this.mRemindList.add(scheduleReminderV3);
                    this.mCompletedRemind++;
                } else {
                    Iterator<ScheduleReminderV3> it = this.mRemindList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ScheduleReminderV3 next = it.next();
                        if (next.getId() == scheduleReminderV3.getId()) {
                            this.mRemindList.remove(next);
                            this.mRemindList.add(scheduleReminderV3);
                            break;
                        }
                    }
                }
                setNoReminderLayout(true, this.mCompletedRemind - this.mRemindList.size() > 0);
                sort();
                RemindersAdapter remindersAdapter = this.mRemindersAdapter;
                if (remindersAdapter != null) {
                    remindersAdapter.notifyDataSetChanged();
                }
            }
        }
        if (requestCode == 999) {
            Bundle extras2 = data != null ? data.getExtras() : null;
            Integer numValueOf = extras2 != null ? Integer.valueOf(extras2.getInt("RemindersCompleteNumber", 0)) : null;
            Log.d(TAG, "mRemindersCompleteNumber--" + numValueOf + "mRemindList.size--" + this.mRemindList.size());
            int size = this.mRemindList.size();
            if (numValueOf == null) {
                Intrinsics.throwNpe();
            }
            this.mCompletedRemind = size + numValueOf.intValue();
            setNoReminderLayout(this.mRemindList.size() > 0, numValueOf.intValue() > 0);
        }
    }

    private final void sort() {
        List<ScheduleReminderV3> list = this.mRemindList;
        if (list.size() > 1) {
            CollectionsKt.sortWith(list, new Comparator<T>() { // from class: com.ido.life.module.device.reminders.RemindersActivity$sort$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    ScheduleReminderV3 scheduleReminderV3 = (ScheduleReminderV3) t;
                    StringBuilder sb = new StringBuilder();
                    sb.append(scheduleReminderV3.getYear());
                    sb.append(scheduleReminderV3.getMon());
                    sb.append(scheduleReminderV3.getDay());
                    sb.append(' ');
                    sb.append(scheduleReminderV3.getHour());
                    sb.append(':');
                    sb.append(scheduleReminderV3.getMin());
                    sb.append(':');
                    sb.append(scheduleReminderV3.getSec());
                    Date date = DateUtil.parse(sb.toString(), "yyyyMMdd HH:mm:ss");
                    ScheduleReminderV3 scheduleReminderV32 = (ScheduleReminderV3) t2;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(scheduleReminderV32.getYear());
                    sb2.append(scheduleReminderV32.getMon());
                    sb2.append(scheduleReminderV32.getDay());
                    sb2.append(' ');
                    sb2.append(scheduleReminderV32.getHour());
                    sb2.append(':');
                    sb2.append(scheduleReminderV32.getMin());
                    sb2.append(':');
                    sb2.append(scheduleReminderV32.getSec());
                    return ComparisonsKt.compareValues(date, DateUtil.parse(sb2.toString(), "yyyyMMdd HH:mm:ss"));
                }
            });
        }
    }
}