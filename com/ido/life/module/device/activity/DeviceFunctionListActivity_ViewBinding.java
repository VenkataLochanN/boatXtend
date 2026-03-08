package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceFunctionListActivity_ViewBinding implements Unbinder {
    private DeviceFunctionListActivity target;
    private View view7f0a024b;
    private View view7f0a024e;
    private View view7f0a0259;
    private View view7f0a025c;
    private View view7f0a025d;
    private View view7f0a025e;
    private View view7f0a025f;
    private View view7f0a0272;
    private View view7f0a027f;
    private View view7f0a0288;
    private View view7f0a0289;
    private View view7f0a028b;
    private View view7f0a029b;
    private View view7f0a02a0;
    private View view7f0a02ac;
    private View view7f0a02bc;
    private View view7f0a02c7;
    private View view7f0a02cb;
    private View view7f0a0407;
    private View view7f0a06a0;

    public DeviceFunctionListActivity_ViewBinding(DeviceFunctionListActivity deviceFunctionListActivity) {
        this(deviceFunctionListActivity, deviceFunctionListActivity.getWindow().getDecorView());
    }

    public DeviceFunctionListActivity_ViewBinding(final DeviceFunctionListActivity deviceFunctionListActivity, View view) {
        this.target = deviceFunctionListActivity;
        deviceFunctionListActivity.mIvDevice = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_device, "field 'mIvDevice'", ImageView.class);
        deviceFunctionListActivity.mMtvName = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_name, "field 'mMtvName'", MediumTextView.class);
        deviceFunctionListActivity.mRtvStatus = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_status, "field 'mRtvStatus'", MediumTextView.class);
        deviceFunctionListActivity.mRtvMac = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_battery, "field 'mRtvMac'", RegularTextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_dial_market, "field 'mItemMultiDial' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemMultiDial = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_dial_market, "field 'mItemMultiDial'", CustomItemLabelView.class);
        this.view7f0a025d = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.layout_dial, "field 'mLayoutDial' and method 'onViewClicked'");
        deviceFunctionListActivity.mLayoutDial = (LinearLayout) Utils.castView(viewFindRequiredView2, R.id.layout_dial, "field 'mLayoutDial'", LinearLayout.class);
        this.view7f0a0407 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_msg_notification, "field 'mItemMsgNotification' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemMsgNotification = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_msg_notification, "field 'mItemMsgNotification'", CustomItemLabelView.class);
        this.view7f0a028b = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        deviceFunctionListActivity.mItemCallReminder = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_call_reminder, "field 'mItemCallReminder'", CustomItemLabelView.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.item_heart_rate_detection, "field 'mItemHeartRateDetection' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemHeartRateDetection = (CustomItemLabelView) Utils.castView(viewFindRequiredView4, R.id.item_heart_rate_detection, "field 'mItemHeartRateDetection'", CustomItemLabelView.class);
        this.view7f0a0272 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.item_pressure_detection, "field 'mItemPressureDetection' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemPressureDetection = (CustomItemLabelView) Utils.castView(viewFindRequiredView5, R.id.item_pressure_detection, "field 'mItemPressureDetection'", CustomItemLabelView.class);
        this.view7f0a029b = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        deviceFunctionListActivity.mItemWeatherForecast = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_weather_forecast, "field 'mItemWeatherForecast'", CustomItemLabelView.class);
        View viewFindRequiredView6 = Utils.findRequiredView(view, R.id.item_dnd_daytime, "field 'mItemDndMode' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemDndMode = (CustomItemLabelView) Utils.castView(viewFindRequiredView6, R.id.item_dnd_daytime, "field 'mItemDndMode'", CustomItemLabelView.class);
        this.view7f0a025f = viewFindRequiredView6;
        viewFindRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView7 = Utils.findRequiredView(view, R.id.item_walk_reminder, "field 'mItemWalkReminder' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemWalkReminder = (CustomItemLabelView) Utils.castView(viewFindRequiredView7, R.id.item_walk_reminder, "field 'mItemWalkReminder'", CustomItemLabelView.class);
        this.view7f0a02c7 = viewFindRequiredView7;
        viewFindRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView8 = Utils.findRequiredView(view, R.id.item_alarm_clock, "field 'mItemAlarmClock' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemAlarmClock = (CustomItemLabelView) Utils.castView(viewFindRequiredView8, R.id.item_alarm_clock, "field 'mItemAlarmClock'", CustomItemLabelView.class);
        this.view7f0a024b = viewFindRequiredView8;
        viewFindRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        deviceFunctionListActivity.mItemWristScreen = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_wrist_screen, "field 'mItemWristScreen'", CustomItemLabelView.class);
        View viewFindRequiredView9 = Utils.findRequiredView(view, R.id.item_motion_type, "field 'mItemMotionType' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemMotionType = (CustomItemLabelView) Utils.castView(viewFindRequiredView9, R.id.item_motion_type, "field 'mItemMotionType'", CustomItemLabelView.class);
        this.view7f0a0289 = viewFindRequiredView9;
        viewFindRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        deviceFunctionListActivity.mItemGoalAchievementReminder = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_goal_achievement_reminder, "field 'mItemGoalAchievementReminder'", CustomItemLabelView.class);
        View viewFindRequiredView10 = Utils.findRequiredView(view, R.id.item_water_clock, "field 'mItemWaterClock' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemWaterClock = (CustomItemLabelView) Utils.castView(viewFindRequiredView10, R.id.item_water_clock, "field 'mItemWaterClock'", CustomItemLabelView.class);
        this.view7f0a02cb = viewFindRequiredView10;
        viewFindRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView11 = Utils.findRequiredView(view, R.id.item_time_format, "field 'mItemTimeFormat' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemTimeFormat = (CustomItemLabelView) Utils.castView(viewFindRequiredView11, R.id.item_time_format, "field 'mItemTimeFormat'", CustomItemLabelView.class);
        this.view7f0a02bc = viewFindRequiredView11;
        viewFindRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView12 = Utils.findRequiredView(view, R.id.item_motion_recognition, "field 'mItemMotionRecognition' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemMotionRecognition = (CustomItemLabelView) Utils.castView(viewFindRequiredView12, R.id.item_motion_recognition, "field 'mItemMotionRecognition'", CustomItemLabelView.class);
        this.view7f0a0288 = viewFindRequiredView12;
        viewFindRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView13 = Utils.findRequiredView(view, R.id.item_quick_app, "field 'mItemShortcut' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemShortcut = (CustomItemLabelView) Utils.castView(viewFindRequiredView13, R.id.item_quick_app, "field 'mItemShortcut'", CustomItemLabelView.class);
        this.view7f0a02a0 = viewFindRequiredView13;
        viewFindRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        deviceFunctionListActivity.mItemMusicControl = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_music_control, "field 'mItemMusicControl'", CustomItemLabelView.class);
        deviceFunctionListActivity.mItemFindPhone = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_find_phone, "field 'mItemFindPhone'", CustomItemLabelView.class);
        View viewFindRequiredView14 = Utils.findRequiredView(view, R.id.item_amazon_alexa, "field 'mItemAmazonAlexa' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemAmazonAlexa = (CustomItemLabelView) Utils.castView(viewFindRequiredView14, R.id.item_amazon_alexa, "field 'mItemAmazonAlexa'", CustomItemLabelView.class);
        this.view7f0a024e = viewFindRequiredView14;
        viewFindRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView15 = Utils.findRequiredView(view, R.id.item_menstrual_cycle, "field 'mItemHealthCare' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemHealthCare = (CustomItemLabelView) Utils.castView(viewFindRequiredView15, R.id.item_menstrual_cycle, "field 'mItemHealthCare'", CustomItemLabelView.class);
        this.view7f0a027f = viewFindRequiredView15;
        viewFindRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView16 = Utils.findRequiredView(view, R.id.item_direction_for_use, "field 'mItemDirectionForUse' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemDirectionForUse = (CustomItemLabelView) Utils.castView(viewFindRequiredView16, R.id.item_direction_for_use, "field 'mItemDirectionForUse'", CustomItemLabelView.class);
        this.view7f0a025e = viewFindRequiredView16;
        viewFindRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView17 = Utils.findRequiredView(view, R.id.item_device_upgrade, "field 'mItemDeviceUpgrade' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemDeviceUpgrade = (CustomItemLabelView) Utils.castView(viewFindRequiredView17, R.id.item_device_upgrade, "field 'mItemDeviceUpgrade'", CustomItemLabelView.class);
        this.view7f0a025c = viewFindRequiredView17;
        viewFindRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView18 = Utils.findRequiredView(view, R.id.item_device_language, "field 'mItemDeviceLanguage' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemDeviceLanguage = (CustomItemLabelView) Utils.castView(viewFindRequiredView18, R.id.item_device_language, "field 'mItemDeviceLanguage'", CustomItemLabelView.class);
        this.view7f0a0259 = viewFindRequiredView18;
        viewFindRequiredView18.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.18
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView19 = Utils.findRequiredView(view, R.id.item_restart, "field 'mItemRestart' and method 'onViewClicked'");
        deviceFunctionListActivity.mItemRestart = (CustomItemLabelView) Utils.castView(viewFindRequiredView19, R.id.item_restart, "field 'mItemRestart'", CustomItemLabelView.class);
        this.view7f0a02ac = viewFindRequiredView19;
        viewFindRequiredView19.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.19
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView20 = Utils.findRequiredView(view, R.id.rtv_unbound, "field 'mRtvUnbound' and method 'onViewClicked'");
        deviceFunctionListActivity.mRtvUnbound = (RegularTextView) Utils.castView(viewFindRequiredView20, R.id.rtv_unbound, "field 'mRtvUnbound'", RegularTextView.class);
        this.view7f0a06a0 = viewFindRequiredView20;
        viewFindRequiredView20.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceFunctionListActivity_ViewBinding.20
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFunctionListActivity.onViewClicked();
            }
        });
        deviceFunctionListActivity.mIvDial1 = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_dial_1, "field 'mIvDial1'", ImageView.class);
        deviceFunctionListActivity.mIvDial2 = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_dial_2, "field 'mIvDial2'", ImageView.class);
        deviceFunctionListActivity.mIvDial3 = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_dial_3, "field 'mIvDial3'", ImageView.class);
        deviceFunctionListActivity.mViewDivider = Utils.findRequiredView(view, R.id.view_divider, "field 'mViewDivider'");
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DeviceFunctionListActivity deviceFunctionListActivity = this.target;
        if (deviceFunctionListActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        deviceFunctionListActivity.mIvDevice = null;
        deviceFunctionListActivity.mMtvName = null;
        deviceFunctionListActivity.mRtvStatus = null;
        deviceFunctionListActivity.mRtvMac = null;
        deviceFunctionListActivity.mItemMultiDial = null;
        deviceFunctionListActivity.mLayoutDial = null;
        deviceFunctionListActivity.mItemMsgNotification = null;
        deviceFunctionListActivity.mItemCallReminder = null;
        deviceFunctionListActivity.mItemHeartRateDetection = null;
        deviceFunctionListActivity.mItemPressureDetection = null;
        deviceFunctionListActivity.mItemWeatherForecast = null;
        deviceFunctionListActivity.mItemDndMode = null;
        deviceFunctionListActivity.mItemWalkReminder = null;
        deviceFunctionListActivity.mItemAlarmClock = null;
        deviceFunctionListActivity.mItemWristScreen = null;
        deviceFunctionListActivity.mItemMotionType = null;
        deviceFunctionListActivity.mItemGoalAchievementReminder = null;
        deviceFunctionListActivity.mItemWaterClock = null;
        deviceFunctionListActivity.mItemTimeFormat = null;
        deviceFunctionListActivity.mItemMotionRecognition = null;
        deviceFunctionListActivity.mItemShortcut = null;
        deviceFunctionListActivity.mItemMusicControl = null;
        deviceFunctionListActivity.mItemFindPhone = null;
        deviceFunctionListActivity.mItemAmazonAlexa = null;
        deviceFunctionListActivity.mItemHealthCare = null;
        deviceFunctionListActivity.mItemDirectionForUse = null;
        deviceFunctionListActivity.mItemDeviceUpgrade = null;
        deviceFunctionListActivity.mItemDeviceLanguage = null;
        deviceFunctionListActivity.mItemRestart = null;
        deviceFunctionListActivity.mRtvUnbound = null;
        deviceFunctionListActivity.mIvDial1 = null;
        deviceFunctionListActivity.mIvDial2 = null;
        deviceFunctionListActivity.mIvDial3 = null;
        deviceFunctionListActivity.mViewDivider = null;
        this.view7f0a025d.setOnClickListener(null);
        this.view7f0a025d = null;
        this.view7f0a0407.setOnClickListener(null);
        this.view7f0a0407 = null;
        this.view7f0a028b.setOnClickListener(null);
        this.view7f0a028b = null;
        this.view7f0a0272.setOnClickListener(null);
        this.view7f0a0272 = null;
        this.view7f0a029b.setOnClickListener(null);
        this.view7f0a029b = null;
        this.view7f0a025f.setOnClickListener(null);
        this.view7f0a025f = null;
        this.view7f0a02c7.setOnClickListener(null);
        this.view7f0a02c7 = null;
        this.view7f0a024b.setOnClickListener(null);
        this.view7f0a024b = null;
        this.view7f0a0289.setOnClickListener(null);
        this.view7f0a0289 = null;
        this.view7f0a02cb.setOnClickListener(null);
        this.view7f0a02cb = null;
        this.view7f0a02bc.setOnClickListener(null);
        this.view7f0a02bc = null;
        this.view7f0a0288.setOnClickListener(null);
        this.view7f0a0288 = null;
        this.view7f0a02a0.setOnClickListener(null);
        this.view7f0a02a0 = null;
        this.view7f0a024e.setOnClickListener(null);
        this.view7f0a024e = null;
        this.view7f0a027f.setOnClickListener(null);
        this.view7f0a027f = null;
        this.view7f0a025e.setOnClickListener(null);
        this.view7f0a025e = null;
        this.view7f0a025c.setOnClickListener(null);
        this.view7f0a025c = null;
        this.view7f0a0259.setOnClickListener(null);
        this.view7f0a0259 = null;
        this.view7f0a02ac.setOnClickListener(null);
        this.view7f0a02ac = null;
        this.view7f0a06a0.setOnClickListener(null);
        this.view7f0a06a0 = null;
    }
}