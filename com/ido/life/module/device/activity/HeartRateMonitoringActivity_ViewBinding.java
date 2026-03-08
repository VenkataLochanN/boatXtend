package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseHealthMonitoringActivity_ViewBinding;
import com.ido.life.customview.ReminderSelectView;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class HeartRateMonitoringActivity_ViewBinding extends BaseHealthMonitoringActivity_ViewBinding {
    private HeartRateMonitoringActivity target;
    private View view7f0a0265;
    private View view7f0a02b6;
    private View view7f0a04e2;
    private View view7f0a04e3;

    public HeartRateMonitoringActivity_ViewBinding(HeartRateMonitoringActivity heartRateMonitoringActivity) {
        this(heartRateMonitoringActivity, heartRateMonitoringActivity.getWindow().getDecorView());
    }

    public HeartRateMonitoringActivity_ViewBinding(final HeartRateMonitoringActivity heartRateMonitoringActivity, View view) {
        super(heartRateMonitoringActivity, view);
        this.target = heartRateMonitoringActivity;
        heartRateMonitoringActivity.mItemHeartRateSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_heart_rate_switch, "field 'mItemHeartRateSwitch'", CustomItemLabelView.class);
        heartRateMonitoringActivity.mRtvRemindTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_remind_tip, "field 'mRtvRemindTip'", RegularTextView.class);
        heartRateMonitoringActivity.mTipsTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tipsTv, "field 'mTipsTv'", TextView.class);
        heartRateMonitoringActivity.mTvMeasuringFrequency = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.tv_measuring_frequency, "field 'mTvMeasuringFrequency'", MediumTextView.class);
        heartRateMonitoringActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        heartRateMonitoringActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
        heartRateMonitoringActivity.mLayoutContent = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.layout_content, "field 'mLayoutContent'", NestedScrollView.class);
        heartRateMonitoringActivity.mLayoutLoadFailed = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_load_failed, "field 'mLayoutLoadFailed'", LinearLayout.class);
        heartRateMonitoringActivity.mLayoutMeasuringTime = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.device_measuring_time_layout, "field 'mLayoutMeasuringTime'", LinearLayout.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_start_time, "field 'mItemStartTime' and method 'onViewClick'");
        heartRateMonitoringActivity.mItemStartTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_start_time, "field 'mItemStartTime'", CustomItemLabelView.class);
        this.view7f0a02b6 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.HeartRateMonitoringActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                heartRateMonitoringActivity.onViewClick(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_end_time, "field 'mItemEndTime' and method 'onViewClick'");
        heartRateMonitoringActivity.mItemEndTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_end_time, "field 'mItemEndTime'", CustomItemLabelView.class);
        this.view7f0a0265 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.HeartRateMonitoringActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                heartRateMonitoringActivity.onViewClick(view2);
            }
        });
        heartRateMonitoringActivity.mLayoutRemindTime = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.device_measuring_remind_layout, "field 'mLayoutRemindTime'", LinearLayout.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.measuring_detection_high_remind, "field 'mItemHighRemind' and method 'onViewClick'");
        heartRateMonitoringActivity.mItemHighRemind = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.measuring_detection_high_remind, "field 'mItemHighRemind'", CustomItemLabelView.class);
        this.view7f0a04e2 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.HeartRateMonitoringActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                heartRateMonitoringActivity.onViewClick(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.measuring_detection_low_remind, "field 'mItemLowRemind' and method 'onViewClick'");
        heartRateMonitoringActivity.mItemLowRemind = (CustomItemLabelView) Utils.castView(viewFindRequiredView4, R.id.measuring_detection_low_remind, "field 'mItemLowRemind'", CustomItemLabelView.class);
        this.view7f0a04e3 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.HeartRateMonitoringActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                heartRateMonitoringActivity.onViewClick(view2);
            }
        });
        heartRateMonitoringActivity.llHeartModel = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.llHeartModel, "field 'llHeartModel'", LinearLayout.class);
        heartRateMonitoringActivity.vReminder = (ReminderSelectView) Utils.findRequiredViewAsType(view, R.id.vReminder, "field 'vReminder'", ReminderSelectView.class);
        heartRateMonitoringActivity.tvTip1 = (TextView) Utils.findRequiredViewAsType(view, R.id.tvTip1, "field 'tvTip1'", TextView.class);
    }

    @Override // com.ido.life.base.BaseHealthMonitoringActivity_ViewBinding, butterknife.Unbinder
    public void unbind() {
        HeartRateMonitoringActivity heartRateMonitoringActivity = this.target;
        if (heartRateMonitoringActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        heartRateMonitoringActivity.mItemHeartRateSwitch = null;
        heartRateMonitoringActivity.mRtvRemindTip = null;
        heartRateMonitoringActivity.mTipsTv = null;
        heartRateMonitoringActivity.mTvMeasuringFrequency = null;
        heartRateMonitoringActivity.mRecyclerView = null;
        heartRateMonitoringActivity.mCommLoadingView = null;
        heartRateMonitoringActivity.mLayoutContent = null;
        heartRateMonitoringActivity.mLayoutLoadFailed = null;
        heartRateMonitoringActivity.mLayoutMeasuringTime = null;
        heartRateMonitoringActivity.mItemStartTime = null;
        heartRateMonitoringActivity.mItemEndTime = null;
        heartRateMonitoringActivity.mLayoutRemindTime = null;
        heartRateMonitoringActivity.mItemHighRemind = null;
        heartRateMonitoringActivity.mItemLowRemind = null;
        heartRateMonitoringActivity.llHeartModel = null;
        heartRateMonitoringActivity.vReminder = null;
        heartRateMonitoringActivity.tvTip1 = null;
        this.view7f0a02b6.setOnClickListener(null);
        this.view7f0a02b6 = null;
        this.view7f0a0265.setOnClickListener(null);
        this.view7f0a0265 = null;
        this.view7f0a04e2.setOnClickListener(null);
        this.view7f0a04e2 = null;
        this.view7f0a04e3.setOnClickListener(null);
        this.view7f0a04e3 = null;
        super.unbind();
    }
}