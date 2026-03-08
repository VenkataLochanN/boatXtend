package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class WaterClockActivity_ViewBinding implements Unbinder {
    private WaterClockActivity target;
    private View view7f0a0265;
    private View view7f0a02a6;
    private View view7f0a02ab;
    private View view7f0a02b6;

    public WaterClockActivity_ViewBinding(WaterClockActivity waterClockActivity) {
        this(waterClockActivity, waterClockActivity.getWindow().getDecorView());
    }

    public WaterClockActivity_ViewBinding(final WaterClockActivity waterClockActivity, View view) {
        this.target = waterClockActivity;
        waterClockActivity.mLayoutContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_content, "field 'mLayoutContent'", LinearLayout.class);
        waterClockActivity.mItemWaterClockSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_water_clock_switch, "field 'mItemWaterClockSwitch'", CustomItemLabelView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_start_time, "field 'mItemStartTime' and method 'onViewClicked'");
        waterClockActivity.mItemStartTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_start_time, "field 'mItemStartTime'", CustomItemLabelView.class);
        this.view7f0a02b6 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WaterClockActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                waterClockActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_end_time, "field 'mItemEndTime' and method 'onViewClicked'");
        waterClockActivity.mItemEndTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_end_time, "field 'mItemEndTime'", CustomItemLabelView.class);
        this.view7f0a0265 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WaterClockActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                waterClockActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_reminder_interval, "field 'mItemReminderInterval' and method 'onViewClicked'");
        waterClockActivity.mItemReminderInterval = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_reminder_interval, "field 'mItemReminderInterval'", CustomItemLabelView.class);
        this.view7f0a02a6 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WaterClockActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                waterClockActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.item_repetition_period, "field 'mItemRepetitionPeriod' and method 'onViewClicked'");
        waterClockActivity.mItemRepetitionPeriod = (CustomItemLabelView) Utils.castView(viewFindRequiredView4, R.id.item_repetition_period, "field 'mItemRepetitionPeriod'", CustomItemLabelView.class);
        this.view7f0a02ab = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WaterClockActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                waterClockActivity.onViewClicked(view2);
            }
        });
        waterClockActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        WaterClockActivity waterClockActivity = this.target;
        if (waterClockActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        waterClockActivity.mLayoutContent = null;
        waterClockActivity.mItemWaterClockSwitch = null;
        waterClockActivity.mItemStartTime = null;
        waterClockActivity.mItemEndTime = null;
        waterClockActivity.mItemReminderInterval = null;
        waterClockActivity.mItemRepetitionPeriod = null;
        waterClockActivity.mCommLoadingView = null;
        this.view7f0a02b6.setOnClickListener(null);
        this.view7f0a02b6 = null;
        this.view7f0a0265.setOnClickListener(null);
        this.view7f0a0265 = null;
        this.view7f0a02a6.setOnClickListener(null);
        this.view7f0a02a6 = null;
        this.view7f0a02ab.setOnClickListener(null);
        this.view7f0a02ab = null;
    }
}