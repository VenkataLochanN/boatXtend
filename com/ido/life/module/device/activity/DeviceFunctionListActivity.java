package com.ido.life.module.device.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.boatservice.DeviceAssistService;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.data.api.entity.TopDialPlateEntity;
import com.ido.life.module.alexa.AlexaLoginActivity;
import com.ido.life.module.device.presenter.FunctionPresenter;
import com.ido.life.module.device.view.IFunctionListView;
import com.ido.life.util.FunctionHelper;
import com.ido.life.util.SPHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceFunctionListActivity extends BaseActivity<FunctionPresenter> implements IFunctionListView {
    public static final String DEVICE = "device";
    private SupportFunctionInfo mFunctionInfo;

    @BindView(R.id.item_alarm_clock)
    CustomItemLabelView mItemAlarmClock;

    @BindView(R.id.item_amazon_alexa)
    CustomItemLabelView mItemAmazonAlexa;

    @BindView(R.id.item_call_reminder)
    CustomItemLabelView mItemCallReminder;

    @BindView(R.id.item_device_language)
    CustomItemLabelView mItemDeviceLanguage;

    @BindView(R.id.item_device_upgrade)
    CustomItemLabelView mItemDeviceUpgrade;

    @BindView(R.id.item_direction_for_use)
    CustomItemLabelView mItemDirectionForUse;

    @BindView(R.id.item_dnd_daytime)
    CustomItemLabelView mItemDndMode;

    @BindView(R.id.item_find_phone)
    CustomItemLabelView mItemFindPhone;

    @BindView(R.id.item_goal_achievement_reminder)
    CustomItemLabelView mItemGoalAchievementReminder;

    @BindView(R.id.item_menstrual_cycle)
    CustomItemLabelView mItemHealthCare;

    @BindView(R.id.item_heart_rate_detection)
    CustomItemLabelView mItemHeartRateDetection;

    @BindView(R.id.item_motion_recognition)
    CustomItemLabelView mItemMotionRecognition;

    @BindView(R.id.item_motion_type)
    CustomItemLabelView mItemMotionType;

    @BindView(R.id.item_msg_notification)
    CustomItemLabelView mItemMsgNotification;

    @BindView(R.id.item_dial_market)
    CustomItemLabelView mItemMultiDial;

    @BindView(R.id.item_music_control)
    CustomItemLabelView mItemMusicControl;

    @BindView(R.id.item_pressure_detection)
    CustomItemLabelView mItemPressureDetection;

    @BindView(R.id.item_restart)
    CustomItemLabelView mItemRestart;

    @BindView(R.id.item_quick_app)
    CustomItemLabelView mItemShortcut;

    @BindView(R.id.item_time_format)
    CustomItemLabelView mItemTimeFormat;

    @BindView(R.id.item_walk_reminder)
    CustomItemLabelView mItemWalkReminder;

    @BindView(R.id.item_water_clock)
    CustomItemLabelView mItemWaterClock;

    @BindView(R.id.item_weather_forecast)
    CustomItemLabelView mItemWeatherForecast;

    @BindView(R.id.item_wrist_screen)
    CustomItemLabelView mItemWristScreen;

    @BindView(R.id.iv_device)
    ImageView mIvDevice;

    @BindView(R.id.iv_dial_1)
    ImageView mIvDial1;

    @BindView(R.id.iv_dial_2)
    ImageView mIvDial2;

    @BindView(R.id.iv_dial_3)
    ImageView mIvDial3;

    @BindView(R.id.layout_dial)
    LinearLayout mLayoutDial;

    @BindView(R.id.mtv_name)
    MediumTextView mMtvName;
    private CommBottomConfirmDialog mRestartDialog;

    @BindView(R.id.rtv_battery)
    RegularTextView mRtvMac;

    @BindView(R.id.mtv_status)
    MediumTextView mRtvStatus;

    @BindView(R.id.rtv_unbound)
    RegularTextView mRtvUnbound;
    private SwitchStatus mSwitchStatus;
    private CommBottomConfirmDialog mUnbindDialog;

    @BindView(R.id.view_divider)
    View mViewDivider;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_device_function_list;
    }

    @Override // com.ido.life.base.BaseActivity
    protected boolean needEventBus() {
        return true;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        DeviceListEntity.DeviceInfo deviceInfo = (DeviceListEntity.DeviceInfo) getIntent().getSerializableExtra("device");
        if (deviceInfo != null) {
            this.mMtvName.setText(deviceInfo.getDeviceName());
            this.mRtvMac.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            this.mRtvMac.setText(String.format(getLanguageText(R.string.device_mac_ios), deviceInfo.getMac()));
            ImageLoaderUtil.loadImgFillet(this.mIvDevice, deviceInfo.getImageUrl(), 15, R.mipmap.icon_item_device);
        }
        this.mSwitchStatus = ((FunctionPresenter) this.mPresenter).getSwitchStatus();
        updateConnectedStatus();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        initSwitchStatus();
        initFunctionStatus();
        initSwitchEvent();
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mItemWalkReminder.setValue(((FunctionPresenter) this.mPresenter).isWalkReminderOn() ? getLanguageText(R.string.public_opened) : getLanguageText(R.string.public_closed));
        this.mItemWaterClock.setValue(((FunctionPresenter) this.mPresenter).isWaterClockOn() ? getLanguageText(R.string.public_opened) : getLanguageText(R.string.public_closed));
        boolean zIsTimeFollowSys = SPHelper.isTimeFollowSys();
        int i = R.mipmap.icon_time_format_12;
        if (zIsTimeFollowSys) {
            CustomItemLabelView customItemLabelView = this.mItemTimeFormat;
            if (DateFormat.is24HourFormat(this)) {
                i = R.mipmap.icon_time_format_24;
            }
            customItemLabelView.setDrawableLeft(i);
            return;
        }
        CustomItemLabelView customItemLabelView2 = this.mItemTimeFormat;
        if (FunctionHelper.getTimeFormat() != 2) {
            i = R.mipmap.icon_time_format_24;
        }
        customItemLabelView2.setDrawableLeft(i);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mItemMultiDial.setTitle(getLanguageText(R.string.device_multi_dial));
        this.mItemMsgNotification.setTitle(getLanguageText(R.string.device_msg_notification));
        this.mItemCallReminder.setTitle(getLanguageText(R.string.device_call_reminder));
        this.mItemHeartRateDetection.setTitle(getLanguageText(R.string.device_heart_rate_monitoring));
        this.mItemPressureDetection.setTitle(getLanguageText(R.string.device_pressure_monitoring));
        this.mItemWeatherForecast.setTitle(getLanguageText(R.string.device_weather_forecast));
        this.mItemDndMode.setTitle(getLanguageText(R.string.device_dnd_mode));
        this.mItemWalkReminder.setTitle(getLanguageText(R.string.device_walk_reminder));
        this.mItemAlarmClock.setTitle(getLanguageText(R.string.device_alarm_clock));
        this.mItemWristScreen.setTitle(getLanguageText(R.string.device_wrist_screen));
        this.mItemMotionType.setTitle(getLanguageText(R.string.device_motion_type));
        this.mItemGoalAchievementReminder.setTitle(getLanguageText(R.string.device_goal_achievement_reminder));
        this.mItemWaterClock.setTitle(getLanguageText(R.string.device_water_clock));
        this.mItemTimeFormat.setTitle(getLanguageText(R.string.device_time_format));
        this.mItemMotionRecognition.setTitle(getLanguageText(R.string.device_motion_intelligent_recognition));
        this.mItemShortcut.setTitle(getLanguageText(R.string.device_shortcut));
        this.mItemMusicControl.setTitle(getLanguageText(R.string.device_music_control));
        this.mItemFindPhone.setTitle(getLanguageText(R.string.device_find_phone));
        this.mItemHealthCare.setTitle(getLanguageText(R.string.device_menstrual_cycle));
        this.mItemDirectionForUse.setTitle(getLanguageText(R.string.device_direction_for_use));
        this.mItemDeviceUpgrade.setTitle(getLanguageText(R.string.firmware_upgrade));
        this.mItemDeviceLanguage.setTitle(getLanguageText(R.string.device_language));
        this.mItemRestart.setTitle(getLanguageText(R.string.device_restart));
        this.mRtvUnbound.setText(getLanguageText(R.string.device_unbind));
    }

    private void initSwitchStatus() {
        this.mItemCallReminder.setSwitchStatus(this.mSwitchStatus.callReminderSwitched && checkSelfPermission(PermissionUtil.getOnlyPhonePermission()));
        this.mItemWeatherForecast.setSwitchStatus(((FunctionPresenter) this.mPresenter).getWeatherSwitch());
        this.mItemWristScreen.setSwitchStatus(((FunctionPresenter) this.mPresenter).getWristScreenSwitch());
        this.mItemGoalAchievementReminder.setSwitchStatus(((FunctionPresenter) this.mPresenter).getGoalAchievementSwitch());
        this.mItemMusicControl.setSwitchStatus(((FunctionPresenter) this.mPresenter).getMusicControlSwitch());
        this.mItemFindPhone.setSwitchStatus(((FunctionPresenter) this.mPresenter).getFindPhoneSwitch());
    }

    private void initFunctionStatus() {
        this.mFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (this.mFunctionInfo == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        this.mItemMultiDial.setVisibility(this.mFunctionInfo.multiDial ? 0 : 8);
        this.mLayoutDial.setVisibility(this.mFunctionInfo.multiDial ? 0 : 8);
        this.mViewDivider.setVisibility(this.mFunctionInfo.multiDial ? 0 : 8);
        if (this.mFunctionInfo.multiDial) {
            ((FunctionPresenter) this.mPresenter).requestTopDialPlate();
        }
        this.mItemCallReminder.setVisibility(this.mFunctionInfo.calling ? 0 : 8);
        if (this.mFunctionInfo.calling) {
            arrayList.add(this.mItemCallReminder);
        }
        this.mItemHeartRateDetection.setVisibility(this.mFunctionInfo.heartRateMonitor | this.mFunctionInfo.ex_main4_v3_hr_data ? 0 : 8);
        if (this.mFunctionInfo.ex_main4_v3_hr_data | this.mFunctionInfo.ex_main4_v3_hr_data) {
            arrayList.add(this.mItemHeartRateDetection);
        }
        this.mItemPressureDetection.setVisibility(this.mFunctionInfo.ex_main3_v3_pressure ? 0 : 8);
        if (this.mFunctionInfo.ex_main3_v3_pressure) {
            arrayList.add(this.mItemPressureDetection);
        }
        this.mItemWeatherForecast.setVisibility(this.mFunctionInfo.weather ? 0 : 8);
        if (this.mFunctionInfo.weather) {
            arrayList.add(this.mItemWeatherForecast);
        }
        this.mItemDndMode.setVisibility(this.mFunctionInfo.doNotDisturb ? 0 : 8);
        if (this.mFunctionInfo.doNotDisturb) {
            arrayList.add(this.mItemDndMode);
        }
        this.mItemWalkReminder.setVisibility(this.mFunctionInfo.walk_reminder ? 0 : 8);
        if (this.mFunctionInfo.walk_reminder) {
            arrayList.add(this.mItemWalkReminder);
        }
        this.mItemAlarmClock.setVisibility(this.mFunctionInfo.alarmCount > 0 ? 0 : 8);
        if (this.mFunctionInfo.alarmCount > 0) {
            arrayList.add(this.mItemAlarmClock);
        }
        if (this.mFunctionInfo.upHandGestrue) {
            this.mItemWristScreen.setVisibility(0);
            this.mItemWristScreen.setDrawableLeft(((FunctionPresenter) this.mPresenter).getWristScreenSwitch() ? R.mipmap.icon_wrist_screen_on : R.mipmap.icon_wrist_screen_off);
            arrayList.add(this.mItemWristScreen);
        } else {
            this.mItemWristScreen.setVisibility(8);
        }
        cancelLastItemBottomDivider(arrayList);
        this.mItemMotionType.setVisibility(this.mFunctionInfo.sport_mode_sort ? 0 : 8);
        if (this.mFunctionInfo.sport_mode_sort) {
            arrayList.add(this.mItemMotionType);
        }
        this.mItemGoalAchievementReminder.setVisibility(8);
        this.mItemWaterClock.setVisibility(this.mFunctionInfo.weather ? 0 : 8);
        if (this.mFunctionInfo.weather) {
            arrayList.add(this.mItemWaterClock);
        }
        boolean zIsTimeFollowSys = SPHelper.isTimeFollowSys();
        int i = R.mipmap.icon_time_format_12;
        if (zIsTimeFollowSys) {
            CustomItemLabelView customItemLabelView = this.mItemTimeFormat;
            if (DateFormat.is24HourFormat(this)) {
                i = R.mipmap.icon_time_format_24;
            }
            customItemLabelView.setDrawableLeft(i);
        } else {
            CustomItemLabelView customItemLabelView2 = this.mItemTimeFormat;
            if (FunctionHelper.getTimeFormat() != 2) {
                i = R.mipmap.icon_time_format_24;
            }
            customItemLabelView2.setDrawableLeft(i);
        }
        arrayList.add(this.mItemTimeFormat);
        this.mItemMotionRecognition.setVisibility(this.mFunctionInfo.activity_switch ? 0 : 8);
        if (this.mFunctionInfo.activity_switch) {
            arrayList.add(this.mItemMotionRecognition);
        }
        this.mItemShortcut.setVisibility(this.mFunctionInfo.ex_main7_menu_list ? 0 : 8);
        if (this.mFunctionInfo.ex_main7_menu_list) {
            arrayList.add(this.mItemShortcut);
        }
        this.mItemMusicControl.setVisibility(this.mFunctionInfo.bleControlMusic ? 0 : 8);
        if (this.mFunctionInfo.bleControlMusic) {
            arrayList.add(this.mItemMusicControl);
        }
        this.mItemFindPhone.setVisibility(this.mFunctionInfo.findPhone ? 0 : 8);
        if (this.mFunctionInfo.findPhone) {
            arrayList.add(this.mItemFindPhone);
        }
        this.mItemHealthCare.setVisibility(this.mFunctionInfo.ex_main3_menstruation ? 0 : 8);
        if (this.mFunctionInfo.ex_main3_menstruation) {
            arrayList.add(this.mItemHealthCare);
        }
        cancelLastItemBottomDivider(arrayList);
        arrayList.add(this.mItemDirectionForUse);
        this.mItemDeviceUpgrade.setVisibility(this.mFunctionInfo.deviceUpdate ? 0 : 8);
        if (this.mFunctionInfo.deviceUpdate) {
            arrayList.add(this.mItemDeviceUpgrade);
        }
        boolean z = ((FunctionPresenter) this.mPresenter).getSupportLanguageList().size() > 0;
        this.mItemDeviceLanguage.setVisibility(z ? 0 : 8);
        if (z || this.mFunctionInfo.downloadLanguage) {
            arrayList.add(this.mItemDeviceLanguage);
        }
        arrayList.add(this.mItemRestart);
        cancelLastItemBottomDivider(arrayList);
    }

    private void cancelLastItemBottomDivider(List<CustomItemLabelView> list) {
        if (list.size() > 0) {
            list.get(list.size() - 1).setHasBottomDivider(false);
        }
        list.clear();
    }

    private void initSwitchEvent() {
        this.mItemCallReminder.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceFunctionListActivity$HWJ0Krfr2RouOO47bYXsjLWyjmY
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$0$DeviceFunctionListActivity(view, z);
            }
        });
        this.mItemWeatherForecast.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceFunctionListActivity$Ei_Hrkg-8QY_-pjez6IyoXixQPA
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$1$DeviceFunctionListActivity(view, z);
            }
        });
        this.mItemWristScreen.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceFunctionListActivity$exSpIzmDFJGb68bOV6zayy5Z1AY
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$2$DeviceFunctionListActivity(view, z);
            }
        });
        this.mItemGoalAchievementReminder.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceFunctionListActivity$T_uvhvMemdw3XbHGhfnJSXKphNs
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$3$DeviceFunctionListActivity(view, z);
            }
        });
        this.mItemMusicControl.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceFunctionListActivity$a-itAwyKIY0jajFGEjNFbpJob_c
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$4$DeviceFunctionListActivity(view, z);
            }
        });
        this.mItemFindPhone.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceFunctionListActivity$_0E33YfEbnyQHMHJVSYLrbCDRbs
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$5$DeviceFunctionListActivity(view, z);
            }
        });
    }

    public /* synthetic */ void lambda$initSwitchEvent$0$DeviceFunctionListActivity(View view, boolean z) {
        setCallReminderStatus(z);
    }

    public /* synthetic */ void lambda$initSwitchEvent$1$DeviceFunctionListActivity(View view, boolean z) {
        setWeatherForecastStatus(z);
    }

    public /* synthetic */ void lambda$initSwitchEvent$2$DeviceFunctionListActivity(View view, boolean z) {
        setWristScreenStatus(z);
    }

    public /* synthetic */ void lambda$initSwitchEvent$3$DeviceFunctionListActivity(View view, boolean z) {
        setGoalAchievementStatus(z);
    }

    public /* synthetic */ void lambda$initSwitchEvent$4$DeviceFunctionListActivity(View view, boolean z) {
        setMusicControlStatus(z);
    }

    public /* synthetic */ void lambda$initSwitchEvent$5$DeviceFunctionListActivity(View view, boolean z) {
        setFindPhoneStatus(z);
    }

    private void setGoalAchievementStatus(boolean z) {
        if (((FunctionPresenter) this.mPresenter).isConnected()) {
            ((FunctionPresenter) this.mPresenter).setGoalAchievementSwitch(z);
        } else {
            setSwitchWithDisconnected(this.mItemGoalAchievementReminder, z);
        }
    }

    private void setMusicControlStatus(boolean z) {
        if (((FunctionPresenter) this.mPresenter).isConnected()) {
            ((FunctionPresenter) this.mPresenter).setMusicSwitch(z);
        } else {
            setSwitchWithDisconnected(this.mItemMusicControl, z);
        }
    }

    private void setWristScreenStatus(boolean z) {
        if (((FunctionPresenter) this.mPresenter).isConnected()) {
            ((FunctionPresenter) this.mPresenter).setWristScreenSwitch(z);
            this.mItemWristScreen.setDrawableLeft(z ? R.mipmap.icon_wrist_screen_on : R.mipmap.icon_wrist_screen_off);
        } else {
            setSwitchWithDisconnected(this.mItemWristScreen, z);
        }
    }

    private void setWeatherForecastStatus(boolean z) {
        if (z && !checkSelfPermission(PermissionUtil.getLocationPermission())) {
            requestPermissions(300, PermissionUtil.getLocationPermission());
        } else if (((FunctionPresenter) this.mPresenter).isConnected()) {
            ((FunctionPresenter) this.mPresenter).setWeatherSwitch(z);
        } else {
            setSwitchWithDisconnected(this.mItemWeatherForecast, z);
        }
    }

    private void setCallReminderStatus(boolean z) {
        if (z) {
            if (!PermissionUtil.checkSelfPermission(this, PermissionUtil.getPhonePermission())) {
                requestPermissions(502, PermissionUtil.getPhonePermission());
            } else {
                this.mSwitchStatus.callReminderSwitched = true;
            }
        } else {
            this.mSwitchStatus.callReminderSwitched = false;
        }
        ((FunctionPresenter) this.mPresenter).setCallReminderSwitch(this.mSwitchStatus);
    }

    private void setFindPhoneStatus(boolean z) {
        if (((FunctionPresenter) this.mPresenter).isConnected()) {
            ((FunctionPresenter) this.mPresenter).setFindPhoneSwitch(z);
        } else {
            setSwitchWithDisconnected(this.mItemFindPhone, z);
        }
    }

    private void setSwitchWithDisconnected(CustomItemLabelView customItemLabelView, boolean z) {
        customItemLabelView.setSwitchStatus(!z);
        showToast(getLanguageText(R.string.device_pls_connect_device));
    }

    private void updateConnectedStatus() {
        this.mRtvStatus.setText(getLanguageText(((FunctionPresenter) this.mPresenter).isConnected() ? R.string.device_connected : R.string.device_disconnected));
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 300) {
            if (checkSelfPermission(PermissionUtil.getLocationPermission())) {
                setWeatherForecastStatus(true);
                return;
            } else {
                this.mItemWeatherForecast.setSwitchStatus(false);
                return;
            }
        }
        if (i != 502) {
            return;
        }
        if (PermissionUtil.checkSelfPermission(this, PermissionUtil.getOnlyPhonePermission())) {
            this.mSwitchStatus.callReminderSwitched = true;
        } else {
            this.mSwitchStatus.callReminderSwitched = false;
            this.mItemCallReminder.setSwitchStatus(false);
        }
        ((FunctionPresenter) this.mPresenter).setCallReminderSwitch(this.mSwitchStatus);
    }

    @OnClick({R.id.item_dial_market, R.id.item_msg_notification, R.id.item_heart_rate_detection, R.id.item_pressure_detection, R.id.item_dnd_daytime, R.id.item_walk_reminder, R.id.item_alarm_clock, R.id.item_motion_type, R.id.item_water_clock, R.id.item_time_format, R.id.item_motion_recognition, R.id.item_quick_app, R.id.item_amazon_alexa, R.id.item_menstrual_cycle, R.id.item_direction_for_use, R.id.item_device_upgrade, R.id.item_device_language, R.id.item_restart, R.id.layout_dial})
    public void onViewClicked(View view) {
        if (!((FunctionPresenter) this.mPresenter).isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
        }
        switch (view.getId()) {
            case R.id.item_alarm_clock /* 2131362379 */:
                startActivity(new Intent(this, (Class<?>) AlarmClockActivity.class));
                break;
            case R.id.item_amazon_alexa /* 2131362382 */:
                startActivity(new Intent(this, (Class<?>) AlexaLoginActivity.class));
                break;
            case R.id.item_device_language /* 2131362393 */:
                SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
                if (supportFunctionInfo != null && supportFunctionInfo.downloadLanguage) {
                    startActivity(new Intent(this, (Class<?>) RemoteLanguageActivity.class));
                } else {
                    startActivity(new Intent(this, (Class<?>) DeviceLanguageActivity.class));
                }
                break;
            case R.id.item_device_upgrade /* 2131362396 */:
                startActivity(new Intent(this, (Class<?>) DeviceUpgradeNewActivity.class));
                break;
            case R.id.item_dial_market /* 2131362397 */:
            case R.id.layout_dial /* 2131362823 */:
                startActivity(new Intent(this, (Class<?>) DialMarketActivity.class));
                break;
            case R.id.item_direction_for_use /* 2131362398 */:
                startActivity(new Intent(this, (Class<?>) CommonWebViewActivity.class).putExtra("type", 1));
                break;
            case R.id.item_dnd_daytime /* 2131362399 */:
                startActivity(new Intent(this, (Class<?>) DNDModeActivity.class));
                break;
            case R.id.item_heart_rate_detection /* 2131362418 */:
                startActivity(new Intent(this, (Class<?>) HeartRateMonitoringActivity.class));
                break;
            case R.id.item_menstrual_cycle /* 2131362431 */:
                startActivity(new Intent(this, (Class<?>) HealthCareActivity.class));
                break;
            case R.id.item_motion_recognition /* 2131362440 */:
                startActivity(new Intent(this, (Class<?>) MotionRecognitionActivity.class));
                break;
            case R.id.item_motion_type /* 2131362441 */:
                startActivity(new Intent(this, (Class<?>) MultiMotionModeActivity.class));
                break;
            case R.id.item_msg_notification /* 2131362443 */:
                startActivity(new Intent(this, (Class<?>) NotificationActivity.class));
                break;
            case R.id.item_pressure_detection /* 2131362459 */:
                startActivity(new Intent(this, (Class<?>) PressureMonitoringActivity.class));
                break;
            case R.id.item_quick_app /* 2131362464 */:
                startActivity(new Intent(this, (Class<?>) QuickAppActivity.class));
                break;
            case R.id.item_restart /* 2131362476 */:
                showRestartDialog();
                break;
            case R.id.item_time_format /* 2131362492 */:
                startActivity(new Intent(this, (Class<?>) TimeFormatActivity.class));
                break;
            case R.id.item_walk_reminder /* 2131362503 */:
                startActivity(new Intent(this, (Class<?>) WalkReminderActivity.class));
                break;
            case R.id.item_water_clock /* 2131362507 */:
                startActivity(new Intent(this, (Class<?>) WaterClockActivity.class));
                break;
        }
    }

    @OnClick({R.id.rtv_unbound})
    public void onViewClicked() {
        showUnbindDialog();
    }

    private void showUnbindDialog() {
        if (this.mUnbindDialog == null) {
            this.mUnbindDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.device_tip_confirm_unbind), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceFunctionListActivity$dIhhuvv8D5V2XxHe3aKYtiL4jc4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showUnbindDialog$6$DeviceFunctionListActivity(view);
                }
            });
        }
        this.mUnbindDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showUnbindDialog$6$DeviceFunctionListActivity(View view) {
        showLoadingDialog();
        ((FunctionPresenter) this.mPresenter).unbind();
    }

    private void showRestartDialog() {
        if (this.mRestartDialog == null) {
            this.mRestartDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.device_restart_tip_android), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceFunctionListActivity$Jo984uyMrauUQEKa66K9g8jqy44
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showRestartDialog$7$DeviceFunctionListActivity(view);
                }
            });
        }
        this.mRestartDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showRestartDialog$7$DeviceFunctionListActivity(View view) {
        if (((FunctionPresenter) this.mPresenter).isConnected()) {
            ((FunctionPresenter) this.mPresenter).preRestartDevice();
            showLoadingDialog();
        } else {
            showToast(getLanguageText(R.string.device_pls_connect_device));
        }
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        super.handleMessage(baseMessage);
        int type = baseMessage.getType();
        if (type == 101 || type == 102) {
            updateConnectedStatus();
        }
    }

    @Override // com.ido.life.module.device.view.IFunctionListView
    public void onRequestDialSuccess(List<TopDialPlateEntity.DialPlate> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < Math.min(list.size(), 3); i++) {
            TopDialPlateEntity.DialPlate dialPlate = list.get(i);
            if (dialPlate != null) {
                if (i == 0) {
                    ImageLoaderUtil.loadImgFillet(this.mIvDial1, dialPlate.getImageUrl(), (int) getResources().getDimension(R.dimen.sw_dp_9), R.mipmap.ic_default_dial_rectangle);
                } else if (i == 1) {
                    ImageLoaderUtil.loadImgFillet(this.mIvDial2, dialPlate.getImageUrl(), (int) getResources().getDimension(R.dimen.sw_dp_9), R.mipmap.ic_default_dial_rectangle);
                } else {
                    ImageLoaderUtil.loadImgFillet(this.mIvDial3, dialPlate.getImageUrl(), (int) getResources().getDimension(R.dimen.sw_dp_9), R.mipmap.ic_default_dial_rectangle);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.activity.DeviceFunctionListActivity$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType = new int[SettingCallBack.SettingType.values().length];

        static {
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.WEATHER_SWITCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.UP_HAND_GESTURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.MUSIC_SWITCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.FIND_PHONE_SWITCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.ido.life.module.device.view.IFunctionListView
    public void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        int i = AnonymousClass1.$SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[settingType.ordinal()];
        if (i == 1) {
            CustomItemLabelView customItemLabelView = this.mItemWeatherForecast;
            customItemLabelView.setSwitchStatus(true ^ customItemLabelView.getSwitchStatus());
        } else if (i == 2) {
            CustomItemLabelView customItemLabelView2 = this.mItemWristScreen;
            customItemLabelView2.setSwitchStatus(true ^ customItemLabelView2.getSwitchStatus());
            CustomItemLabelView customItemLabelView3 = this.mItemWristScreen;
            customItemLabelView3.setDrawableLeft(customItemLabelView3.getSwitchStatus() ? R.mipmap.icon_wrist_screen_on : R.mipmap.icon_wrist_screen_off);
        } else if (i == 3) {
            CustomItemLabelView customItemLabelView4 = this.mItemMusicControl;
            customItemLabelView4.setSwitchStatus(true ^ customItemLabelView4.getSwitchStatus());
        } else if (i == 4) {
            CustomItemLabelView customItemLabelView5 = this.mItemFindPhone;
            customItemLabelView5.setSwitchStatus(true ^ customItemLabelView5.getSwitchStatus());
        }
        showCmdResultToast(false);
    }

    @Override // com.ido.life.module.device.view.IFunctionListView
    public void onSetCmdSuccess(SettingCallBack.SettingType settingType) {
        if (settingType == SettingCallBack.SettingType.FIND_PHONE_SWITCH && ((FunctionPresenter) this.mPresenter).getFindPhoneSwitch()) {
            startService(new Intent(this, (Class<?>) DeviceAssistService.class));
        }
    }

    @Override // com.ido.life.module.device.view.IFunctionListView
    public void onRestartSuccess() {
        dismissLoadingDialog();
        showToast(R.string.restart_success);
    }

    @Override // com.ido.life.module.device.view.IFunctionListView
    public void onRestartFailed() {
        dismissLoadingDialog();
        showToast(R.string.restart_failed);
    }

    @Override // com.ido.life.module.device.view.IFunctionListView
    public void onUnbindSuccess() {
        dismissLoadingDialog();
        showToast(R.string.unbound_success);
        finish();
    }

    @Override // com.ido.life.module.device.view.IFunctionListView
    public void onUnbindFailed() {
        dismissLoadingDialog();
        showToast(R.string.unbound_failed);
    }
}