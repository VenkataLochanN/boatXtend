package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceSettingActivity_ViewBinding implements Unbinder {
    private DeviceSettingActivity target;
    private View view7f0a0259;
    private View view7f0a025a;
    private View view7f0a0261;
    private View view7f0a0267;
    private View view7f0a0284;
    private View view7f0a0289;
    private View view7f0a028c;
    private View view7f0a028d;
    private View view7f0a028e;
    private View view7f0a0290;
    private View view7f0a02a0;
    private View view7f0a02b0;
    private View view7f0a02b5;
    private View view7f0a02c2;
    private View view7f0a02d2;

    public DeviceSettingActivity_ViewBinding(DeviceSettingActivity deviceSettingActivity) {
        this(deviceSettingActivity, deviceSettingActivity.getWindow().getDecorView());
    }

    public DeviceSettingActivity_ViewBinding(final DeviceSettingActivity deviceSettingActivity, View view) {
        this.target = deviceSettingActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_mini_widget, "field 'mItemMiniWidget' and method 'onViewClicked'");
        deviceSettingActivity.mItemMiniWidget = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_mini_widget, "field 'mItemMiniWidget'", CustomItemLabelView.class);
        this.view7f0a0284 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceSettingActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_quick_app, "field 'mItemQuickApp' and method 'onViewClicked'");
        deviceSettingActivity.mItemQuickApp = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_quick_app, "field 'mItemQuickApp'", CustomItemLabelView.class);
        this.view7f0a02a0 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceSettingActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_multi_motion_mode, "field 'mItemMultiMotionMode' and method 'onViewClicked'");
        deviceSettingActivity.mItemMultiMotionMode = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_multi_motion_mode, "field 'mItemMultiMotionMode'", CustomItemLabelView.class);
        this.view7f0a028d = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceSettingActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.item_device_language, "field 'mItemDeviceLanguage' and method 'onViewClicked'");
        deviceSettingActivity.mItemDeviceLanguage = (CustomItemLabelView) Utils.castView(viewFindRequiredView4, R.id.item_device_language, "field 'mItemDeviceLanguage'", CustomItemLabelView.class);
        this.view7f0a0259 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceSettingActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.item_device_restart, "field 'mItemDeviceRestart' and method 'onViewClicked'");
        deviceSettingActivity.mItemDeviceRestart = (CustomItemLabelView) Utils.castView(viewFindRequiredView5, R.id.item_device_restart, "field 'mItemDeviceRestart'", CustomItemLabelView.class);
        this.view7f0a025a = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceSettingActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView6 = Utils.findRequiredView(view, R.id.item_factory_reset, "field 'mItemFactoryReset' and method 'onViewClicked'");
        deviceSettingActivity.mItemFactoryReset = (CustomItemLabelView) Utils.castView(viewFindRequiredView6, R.id.item_factory_reset, "field 'mItemFactoryReset'", CustomItemLabelView.class);
        this.view7f0a0267 = viewFindRequiredView6;
        viewFindRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceSettingActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView7 = Utils.findRequiredView(view, R.id.item_unbind, "field 'mItemUnbind' and method 'onDeleteDeviceClicked'");
        deviceSettingActivity.mItemUnbind = (CustomItemLabelView) Utils.castView(viewFindRequiredView7, R.id.item_unbind, "field 'mItemUnbind'", CustomItemLabelView.class);
        this.view7f0a02c2 = viewFindRequiredView7;
        viewFindRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceSettingActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceSettingActivity.onDeleteDeviceClicked();
            }
        });
        View viewFindRequiredView8 = Utils.findRequiredView(view, R.id.item_dnd_mode, "field 'mItemDndMode' and method 'onViewClicked'");
        deviceSettingActivity.mItemDndMode = (CustomItemLabelView) Utils.castView(viewFindRequiredView8, R.id.item_dnd_mode, "field 'mItemDndMode'", CustomItemLabelView.class);
        this.view7f0a0261 = viewFindRequiredView8;
        viewFindRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceSettingActivity_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceSettingActivity.onViewClicked(view2);
            }
        });
        deviceSettingActivity.mItemFindPhone = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_find_phone, "field 'mItemFindPhone'", CustomItemLabelView.class);
        View viewFindRequiredView9 = Utils.findRequiredView(view, R.id.item_music_control, "field 'mItemMusicControl' and method 'onViewClicked'");
        deviceSettingActivity.mItemMusicControl = (CustomItemLabelView) Utils.castView(viewFindRequiredView9, R.id.item_music_control, "field 'mItemMusicControl'", CustomItemLabelView.class);
        this.view7f0a028e = viewFindRequiredView9;
        viewFindRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceSettingActivity_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceSettingActivity.onViewClicked(view2);
            }
        });
        deviceSettingActivity.mItemWristScreen = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_wrist_screen, "field 'mItemWristScreen'", CustomItemLabelView.class);
        deviceSettingActivity.mItemWeatherPush = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_weather_push, "field 'mItemWeatherPush'", CustomItemLabelView.class);
        View viewFindRequiredView10 = Utils.findRequiredView(view, R.id.item_msg_reply, "field 'mItemMsgReply' and method 'onViewClicked'");
        deviceSettingActivity.mItemMsgReply = (CustomItemLabelView) Utils.castView(viewFindRequiredView10, R.id.item_msg_reply, "field 'mItemMsgReply'", CustomItemLabelView.class);
        this.view7f0a028c = viewFindRequiredView10;
        viewFindRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceSettingActivity_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView11 = Utils.findRequiredView(view, R.id.item_night_light, "field 'mItemNightLight' and method 'onViewClicked'");
        deviceSettingActivity.mItemNightLight = (CustomItemLabelView) Utils.castView(viewFindRequiredView11, R.id.item_night_light, "field 'mItemNightLight'", CustomItemLabelView.class);
        this.view7f0a0290 = viewFindRequiredView11;
        viewFindRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceSettingActivity_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView12 = Utils.findRequiredView(view, R.id.item_screen_luminance, "field 'mItemScreenLuminance' and method 'onViewClicked'");
        deviceSettingActivity.mItemScreenLuminance = (CustomItemLabelView) Utils.castView(viewFindRequiredView12, R.id.item_screen_luminance, "field 'mItemScreenLuminance'", CustomItemLabelView.class);
        this.view7f0a02b0 = viewFindRequiredView12;
        viewFindRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceSettingActivity_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView13 = Utils.findRequiredView(view, R.id.item_world_time, "field 'mItemWorldTime' and method 'onViewClicked'");
        deviceSettingActivity.mItemWorldTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView13, R.id.item_world_time, "field 'mItemWorldTime'", CustomItemLabelView.class);
        this.view7f0a02d2 = viewFindRequiredView13;
        viewFindRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceSettingActivity_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView14 = Utils.findRequiredView(view, R.id.item_sports_data_view, "field 'mItemSportsDataView' and method 'onViewClicked'");
        deviceSettingActivity.mItemSportsDataView = (CustomItemLabelView) Utils.castView(viewFindRequiredView14, R.id.item_sports_data_view, "field 'mItemSportsDataView'", CustomItemLabelView.class);
        this.view7f0a02b5 = viewFindRequiredView14;
        viewFindRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceSettingActivity_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView15 = Utils.findRequiredView(view, R.id.item_motion_type, "field 'mItemMotionType' and method 'onViewClicked'");
        deviceSettingActivity.mItemMotionType = (CustomItemLabelView) Utils.castView(viewFindRequiredView15, R.id.item_motion_type, "field 'mItemMotionType'", CustomItemLabelView.class);
        this.view7f0a0289 = viewFindRequiredView15;
        viewFindRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceSettingActivity_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceSettingActivity.onViewClicked(view2);
            }
        });
        deviceSettingActivity.tab1Tv = (TextView) Utils.findRequiredViewAsType(view, R.id.tab1Tv, "field 'tab1Tv'", TextView.class);
        deviceSettingActivity.tab2Tv = (TextView) Utils.findRequiredViewAsType(view, R.id.tab2Tv, "field 'tab2Tv'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DeviceSettingActivity deviceSettingActivity = this.target;
        if (deviceSettingActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        deviceSettingActivity.mItemMiniWidget = null;
        deviceSettingActivity.mItemQuickApp = null;
        deviceSettingActivity.mItemMultiMotionMode = null;
        deviceSettingActivity.mItemDeviceLanguage = null;
        deviceSettingActivity.mItemDeviceRestart = null;
        deviceSettingActivity.mItemFactoryReset = null;
        deviceSettingActivity.mItemUnbind = null;
        deviceSettingActivity.mItemDndMode = null;
        deviceSettingActivity.mItemFindPhone = null;
        deviceSettingActivity.mItemMusicControl = null;
        deviceSettingActivity.mItemWristScreen = null;
        deviceSettingActivity.mItemWeatherPush = null;
        deviceSettingActivity.mItemMsgReply = null;
        deviceSettingActivity.mItemNightLight = null;
        deviceSettingActivity.mItemScreenLuminance = null;
        deviceSettingActivity.mItemWorldTime = null;
        deviceSettingActivity.mItemSportsDataView = null;
        deviceSettingActivity.mItemMotionType = null;
        deviceSettingActivity.tab1Tv = null;
        deviceSettingActivity.tab2Tv = null;
        this.view7f0a0284.setOnClickListener(null);
        this.view7f0a0284 = null;
        this.view7f0a02a0.setOnClickListener(null);
        this.view7f0a02a0 = null;
        this.view7f0a028d.setOnClickListener(null);
        this.view7f0a028d = null;
        this.view7f0a0259.setOnClickListener(null);
        this.view7f0a0259 = null;
        this.view7f0a025a.setOnClickListener(null);
        this.view7f0a025a = null;
        this.view7f0a0267.setOnClickListener(null);
        this.view7f0a0267 = null;
        this.view7f0a02c2.setOnClickListener(null);
        this.view7f0a02c2 = null;
        this.view7f0a0261.setOnClickListener(null);
        this.view7f0a0261 = null;
        this.view7f0a028e.setOnClickListener(null);
        this.view7f0a028e = null;
        this.view7f0a028c.setOnClickListener(null);
        this.view7f0a028c = null;
        this.view7f0a0290.setOnClickListener(null);
        this.view7f0a0290 = null;
        this.view7f0a02b0.setOnClickListener(null);
        this.view7f0a02b0 = null;
        this.view7f0a02d2.setOnClickListener(null);
        this.view7f0a02d2 = null;
        this.view7f0a02b5.setOnClickListener(null);
        this.view7f0a02b5 = null;
        this.view7f0a0289.setOnClickListener(null);
        this.view7f0a0289 = null;
    }
}