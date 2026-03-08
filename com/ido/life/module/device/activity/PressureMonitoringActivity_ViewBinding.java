package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseHealthMonitoringActivity_ViewBinding;
import com.ido.life.customview.ReminderSelectView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class PressureMonitoringActivity_ViewBinding extends BaseHealthMonitoringActivity_ViewBinding {
    private PressureMonitoringActivity target;
    private View view7f0a0265;
    private View view7f0a02a6;
    private View view7f0a02ab;
    private View view7f0a02b6;

    public PressureMonitoringActivity_ViewBinding(PressureMonitoringActivity pressureMonitoringActivity) {
        this(pressureMonitoringActivity, pressureMonitoringActivity.getWindow().getDecorView());
    }

    public PressureMonitoringActivity_ViewBinding(final PressureMonitoringActivity pressureMonitoringActivity, View view) {
        super(pressureMonitoringActivity, view);
        this.target = pressureMonitoringActivity;
        pressureMonitoringActivity.mItemPressureSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_pressure_switch, "field 'mItemPressureSwitch'", CustomItemLabelView.class);
        pressureMonitoringActivity.mItemOverstressReminderSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_overstress_reminder_switch, "field 'mItemOverstressReminderSwitch'", CustomItemLabelView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_start_time, "field 'mItemStartTime' and method 'onViewClicked'");
        pressureMonitoringActivity.mItemStartTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_start_time, "field 'mItemStartTime'", CustomItemLabelView.class);
        this.view7f0a02b6 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.PressureMonitoringActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                pressureMonitoringActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_end_time, "field 'mItemEndTime' and method 'onViewClicked'");
        pressureMonitoringActivity.mItemEndTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_end_time, "field 'mItemEndTime'", CustomItemLabelView.class);
        this.view7f0a0265 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.PressureMonitoringActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                pressureMonitoringActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_reminder_interval, "field 'mItemReminderInterval' and method 'onViewClicked'");
        pressureMonitoringActivity.mItemReminderInterval = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_reminder_interval, "field 'mItemReminderInterval'", CustomItemLabelView.class);
        this.view7f0a02a6 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.PressureMonitoringActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                pressureMonitoringActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.item_repetition_period, "field 'mItemRepetitionPeriod' and method 'onViewClicked'");
        pressureMonitoringActivity.mItemRepetitionPeriod = (CustomItemLabelView) Utils.castView(viewFindRequiredView4, R.id.item_repetition_period, "field 'mItemRepetitionPeriod'", CustomItemLabelView.class);
        this.view7f0a02ab = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.PressureMonitoringActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                pressureMonitoringActivity.onViewClicked(view2);
            }
        });
        pressureMonitoringActivity.mLayoutOverstressReminder = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_overstress_reminder, "field 'mLayoutOverstressReminder'", LinearLayout.class);
        pressureMonitoringActivity.mLayoutRoot = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_root, "field 'mLayoutRoot'", LinearLayout.class);
        pressureMonitoringActivity.tvOverstressReminderTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_overstress_reminder_tip, "field 'tvOverstressReminderTip'", RegularTextView.class);
        pressureMonitoringActivity.vReminder = (ReminderSelectView) Utils.findRequiredViewAsType(view, R.id.vReminder, "field 'vReminder'", ReminderSelectView.class);
    }

    @Override // com.ido.life.base.BaseHealthMonitoringActivity_ViewBinding, butterknife.Unbinder
    public void unbind() {
        PressureMonitoringActivity pressureMonitoringActivity = this.target;
        if (pressureMonitoringActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        pressureMonitoringActivity.mItemPressureSwitch = null;
        pressureMonitoringActivity.mItemOverstressReminderSwitch = null;
        pressureMonitoringActivity.mItemStartTime = null;
        pressureMonitoringActivity.mItemEndTime = null;
        pressureMonitoringActivity.mItemReminderInterval = null;
        pressureMonitoringActivity.mItemRepetitionPeriod = null;
        pressureMonitoringActivity.mLayoutOverstressReminder = null;
        pressureMonitoringActivity.mLayoutRoot = null;
        pressureMonitoringActivity.tvOverstressReminderTip = null;
        pressureMonitoringActivity.vReminder = null;
        this.view7f0a02b6.setOnClickListener(null);
        this.view7f0a02b6 = null;
        this.view7f0a0265.setOnClickListener(null);
        this.view7f0a0265 = null;
        this.view7f0a02a6.setOnClickListener(null);
        this.view7f0a02a6 = null;
        this.view7f0a02ab.setOnClickListener(null);
        this.view7f0a02ab = null;
        super.unbind();
    }
}