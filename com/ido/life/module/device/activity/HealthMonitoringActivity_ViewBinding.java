package com.ido.life.module.device.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class HealthMonitoringActivity_ViewBinding implements Unbinder {
    private HealthMonitoringActivity target;
    private View view7f0a0132;
    private View view7f0a0272;
    private View view7f0a027f;
    private View view7f0a029b;
    private View view7f0a02ae;
    private View view7f0a02af;
    private View view7f0a02c7;
    private View view7f0a02cb;

    public HealthMonitoringActivity_ViewBinding(HealthMonitoringActivity healthMonitoringActivity) {
        this(healthMonitoringActivity, healthMonitoringActivity.getWindow().getDecorView());
    }

    public HealthMonitoringActivity_ViewBinding(final HealthMonitoringActivity healthMonitoringActivity, View view) {
        this.target = healthMonitoringActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_heart_rate_detection, "field 'mItemHeartRateDetection' and method 'onViewClicked'");
        healthMonitoringActivity.mItemHeartRateDetection = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_heart_rate_detection, "field 'mItemHeartRateDetection'", CustomItemLabelView.class);
        this.view7f0a0272 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.HealthMonitoringActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                healthMonitoringActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_pressure_detection, "field 'mItemPressureDetection' and method 'onViewClicked'");
        healthMonitoringActivity.mItemPressureDetection = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_pressure_detection, "field 'mItemPressureDetection'", CustomItemLabelView.class);
        this.view7f0a029b = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.HealthMonitoringActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                healthMonitoringActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_water_clock, "field 'mItemWaterClock' and method 'onViewClicked'");
        healthMonitoringActivity.mItemWaterClock = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_water_clock, "field 'mItemWaterClock'", CustomItemLabelView.class);
        this.view7f0a02cb = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.HealthMonitoringActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                healthMonitoringActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.item_walk_reminder, "field 'mItemWalkReminder' and method 'onViewClicked'");
        healthMonitoringActivity.mItemWalkReminder = (CustomItemLabelView) Utils.castView(viewFindRequiredView4, R.id.item_walk_reminder, "field 'mItemWalkReminder'", CustomItemLabelView.class);
        this.view7f0a02c7 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.HealthMonitoringActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                healthMonitoringActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.item_science_volume, "field 'mItemVolumeReminder' and method 'onViewClicked'");
        healthMonitoringActivity.mItemVolumeReminder = (CustomItemLabelView) Utils.castView(viewFindRequiredView5, R.id.item_science_volume, "field 'mItemVolumeReminder'", CustomItemLabelView.class);
        this.view7f0a02af = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.HealthMonitoringActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                healthMonitoringActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView6 = Utils.findRequiredView(view, R.id.item_menstrual_cycle, "field 'mItemMenstrualCycle' and method 'onViewClicked'");
        healthMonitoringActivity.mItemMenstrualCycle = (CustomItemLabelView) Utils.castView(viewFindRequiredView6, R.id.item_menstrual_cycle, "field 'mItemMenstrualCycle'", CustomItemLabelView.class);
        this.view7f0a027f = viewFindRequiredView6;
        viewFindRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.HealthMonitoringActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                healthMonitoringActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView7 = Utils.findRequiredView(view, R.id.device_blood_detection, "field 'mItemBloodDetection' and method 'onViewClicked'");
        healthMonitoringActivity.mItemBloodDetection = (CustomItemLabelView) Utils.castView(viewFindRequiredView7, R.id.device_blood_detection, "field 'mItemBloodDetection'", CustomItemLabelView.class);
        this.view7f0a0132 = viewFindRequiredView7;
        viewFindRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.HealthMonitoringActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                healthMonitoringActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView8 = Utils.findRequiredView(view, R.id.item_science_sleep, "field 'mItemScienceSleep' and method 'onViewClicked'");
        healthMonitoringActivity.mItemScienceSleep = (CustomItemLabelView) Utils.castView(viewFindRequiredView8, R.id.item_science_sleep, "field 'mItemScienceSleep'", CustomItemLabelView.class);
        this.view7f0a02ae = viewFindRequiredView8;
        viewFindRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.HealthMonitoringActivity_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                healthMonitoringActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        HealthMonitoringActivity healthMonitoringActivity = this.target;
        if (healthMonitoringActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        healthMonitoringActivity.mItemHeartRateDetection = null;
        healthMonitoringActivity.mItemPressureDetection = null;
        healthMonitoringActivity.mItemWaterClock = null;
        healthMonitoringActivity.mItemWalkReminder = null;
        healthMonitoringActivity.mItemVolumeReminder = null;
        healthMonitoringActivity.mItemMenstrualCycle = null;
        healthMonitoringActivity.mItemBloodDetection = null;
        healthMonitoringActivity.mItemScienceSleep = null;
        this.view7f0a0272.setOnClickListener(null);
        this.view7f0a0272 = null;
        this.view7f0a029b.setOnClickListener(null);
        this.view7f0a029b = null;
        this.view7f0a02cb.setOnClickListener(null);
        this.view7f0a02cb = null;
        this.view7f0a02c7.setOnClickListener(null);
        this.view7f0a02c7 = null;
        this.view7f0a02af.setOnClickListener(null);
        this.view7f0a02af = null;
        this.view7f0a027f.setOnClickListener(null);
        this.view7f0a027f = null;
        this.view7f0a0132.setOnClickListener(null);
        this.view7f0a0132 = null;
        this.view7f0a02ae.setOnClickListener(null);
        this.view7f0a02ae = null;
    }
}