package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.common.IdoApp;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.utils.PermissionUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.ble.BleHelper;
import com.ido.life.boatservice.DeviceAssistService;
import com.ido.life.boatservice.MusicControlService;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.dialog.CommLoadingDialog;
import com.ido.life.module.device.presenter.DeviceSettingPresenter;
import com.ido.life.module.device.view.IDeviceSettingView;
import com.ido.life.util.AppLogUploadManager;
import com.ido.life.util.FunctionHelper;
import com.ido.life.util.SPHelper;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceSettingActivity extends BaseActivity<DeviceSettingPresenter> implements IDeviceSettingView {
    private boolean isVisible;
    private CommBottomConfirmDialog mDeleteDialog;
    private NotDisturbPara mDisturbPara;
    private CommBottomConfirmDialog mGpsOpenDialog;

    @BindView(R.id.item_device_language)
    CustomItemLabelView mItemDeviceLanguage;

    @BindView(R.id.item_device_restart)
    CustomItemLabelView mItemDeviceRestart;

    @BindView(R.id.item_dnd_mode)
    CustomItemLabelView mItemDndMode;

    @BindView(R.id.item_factory_reset)
    CustomItemLabelView mItemFactoryReset;

    @BindView(R.id.item_find_phone)
    CustomItemLabelView mItemFindPhone;

    @BindView(R.id.item_mini_widget)
    CustomItemLabelView mItemMiniWidget;

    @BindView(R.id.item_motion_type)
    CustomItemLabelView mItemMotionType;

    @BindView(R.id.item_msg_reply)
    CustomItemLabelView mItemMsgReply;

    @BindView(R.id.item_multi_motion_mode)
    CustomItemLabelView mItemMultiMotionMode;

    @BindView(R.id.item_music_control)
    CustomItemLabelView mItemMusicControl;

    @BindView(R.id.item_night_light)
    CustomItemLabelView mItemNightLight;

    @BindView(R.id.item_quick_app)
    CustomItemLabelView mItemQuickApp;

    @BindView(R.id.item_screen_luminance)
    CustomItemLabelView mItemScreenLuminance;

    @BindView(R.id.item_sports_data_view)
    CustomItemLabelView mItemSportsDataView;

    @BindView(R.id.item_unbind)
    CustomItemLabelView mItemUnbind;

    @BindView(R.id.item_weather_push)
    CustomItemLabelView mItemWeatherPush;

    @BindView(R.id.item_world_time)
    CustomItemLabelView mItemWorldTime;

    @BindView(R.id.item_wrist_screen)
    CustomItemLabelView mItemWristScreen;
    private CommLoadingDialog mLoadingDialog;
    private CommBottomConfirmDialog mResetDialog;
    private CommBottomConfirmDialog mRestartDialog;
    private ScreenBrightness mScreenBrightness;

    @BindView(R.id.tab1Tv)
    TextView tab1Tv;

    @BindView(R.id.tab2Tv)
    TextView tab2Tv;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_device_setting;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        initFunctionItem();
        initSwitchListener();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mItemMsgReply.setValue(getLanguageText(SPHelper.isQuickMsgReplySwitchOpened() ? R.string.public_opened : R.string.public_closed));
        this.mItemMotionType.setValue(MotionTypeManager.INSTANCE.getInstance().getMotionTypeProportion());
    }

    private void initFunctionItem() {
        SupportFunctionInfo supportFunctionInfo = ((DeviceSettingPresenter) this.mPresenter).getSupportFunctionInfo();
        this.mItemDndMode.setVisibility((supportFunctionInfo.doNotDisturb || supportFunctionInfo.ex_main3_get_do_not_disturb) ? 0 : 8);
        if (supportFunctionInfo.doNotDisturb || supportFunctionInfo.ex_main3_get_do_not_disturb) {
            this.mItemDndMode.setValue(getLanguageText(((DeviceSettingPresenter) this.mPresenter).isDndModeSwitchOn() ? R.string.public_opened : R.string.public_closed));
        }
        this.mItemFindPhone.setVisibility(supportFunctionInfo.findPhone ? 0 : 8);
        this.mItemMusicControl.setVisibility(supportFunctionInfo.bleControlMusic ? 0 : 8);
        this.mItemWristScreen.setVisibility(supportFunctionInfo.upHandGestrue ? 0 : 8);
        this.mItemWeatherPush.setVisibility((supportFunctionInfo.weather || supportFunctionInfo.V3_support_set_v3_weather) ? 0 : 8);
        boolean z = true;
        boolean z2 = supportFunctionInfo.screen_brightness_5_level || supportFunctionInfo.ex_table_main8_screen_brightness_3_level;
        this.mItemScreenLuminance.setVisibility(z2 ? 0 : 8);
        this.mItemNightLight.setVisibility(supportFunctionInfo.night_auto_brightness ? 0 : 8);
        if (z2 || supportFunctionInfo.night_auto_brightness) {
            ((DeviceSettingPresenter) this.mPresenter).getScreenLuminance();
        }
        this.mItemQuickApp.setVisibility((supportFunctionInfo.ex_main7_menu_list || supportFunctionInfo.V3_get_menu_list) ? 0 : 8);
        this.mItemMiniWidget.setVisibility(supportFunctionInfo.V3_set_main_ui_sort ? 0 : 8);
        if (supportFunctionInfo.V3_set_100_sport_sort || (!supportFunctionInfo.ex_table_main7_v3_sports_type && !supportFunctionInfo.sport_mode_sort && !FunctionHelper.isSupportSportMode())) {
            z = false;
        }
        this.mItemMotionType.setVisibility((supportFunctionInfo.V3_set_100_sport_sort || z) ? 0 : 8);
        this.mItemWorldTime.setVisibility(supportFunctionInfo.V3_support_set_v3_world_time ? 0 : 8);
        this.mItemSportsDataView.setVisibility(supportFunctionInfo.V3_set_20_base_sport_param_sort ? 0 : 8);
        this.mItemFactoryReset.setVisibility(supportFunctionInfo.factory_reset ? 0 : 8);
    }

    private void initSwitchListener() {
        this.mItemFindPhone.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceSettingActivity$jSS8PNWehrEA4TCrDjhtFT6Oxc4
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchListener$0$DeviceSettingActivity(view, z);
            }
        });
        this.mItemMusicControl.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceSettingActivity$7HjRsek-oBGEaAmGuWvqPznTu4s
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchListener$1$DeviceSettingActivity(view, z);
            }
        });
        this.mItemWristScreen.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceSettingActivity$BsQGvmpWG1a1gsUnZNyWwP0th6s
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchListener$2$DeviceSettingActivity(view, z);
            }
        });
        this.mItemWeatherPush.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceSettingActivity$9WHxor_KJojgsYMfTeH7bO1U2Dk
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchListener$3$DeviceSettingActivity(view, z);
            }
        });
    }

    public /* synthetic */ void lambda$initSwitchListener$0$DeviceSettingActivity(View view, boolean z) {
        setFindPhoneStatus(z);
    }

    public /* synthetic */ void lambda$initSwitchListener$1$DeviceSettingActivity(View view, boolean z) {
        setMusicControlStatus(z);
    }

    public /* synthetic */ void lambda$initSwitchListener$2$DeviceSettingActivity(View view, boolean z) {
        setWristScreenStatus(z);
    }

    public /* synthetic */ void lambda$initSwitchListener$3$DeviceSettingActivity(View view, boolean z) {
        setWeatherPushStatus(z);
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.isVisible = true;
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.isVisible = false;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getString(R.string.device_set));
        this.mItemDndMode.setTitle(getLanguageText(R.string.device_dnd_mode));
        this.mItemFindPhone.setTitle(getLanguageText(R.string.device_find_phone));
        this.mItemMusicControl.setTitle(getLanguageText(R.string.device_music_control));
        this.mItemWristScreen.setTitle(getLanguageText(R.string.device_wrist_screen));
        this.mItemWeatherPush.setTitle(getLanguageText(R.string.device_weather_push));
        this.mItemMsgReply.setTitle(getLanguageText(R.string.device_quick_msg_reply));
        this.mItemNightLight.setTitle(getLanguageText(R.string.device_night_auto_light));
        this.mItemQuickApp.setTitle(getLanguageText(R.string.device_quick_app));
        this.mItemMultiMotionMode.setTitle(getLanguageText(R.string.device_multi_motion_mode));
        this.mItemDeviceLanguage.setTitle(getLanguageText(R.string.device_language));
        this.mItemDeviceRestart.setTitle(getLanguageText(R.string.device_restart));
        this.mItemFactoryReset.setTitle(getLanguageText(R.string.device_factory_reset));
        this.mItemUnbind.setTitle(getLanguageText(R.string.delete_device));
        this.mItemFindPhone.setSwitchStatus(((DeviceSettingPresenter) this.mPresenter).isFindPhoneSwitchOn());
        if (((DeviceSettingPresenter) this.mPresenter).getSupportFunctionInfo().V3_support_set_v3_music_name) {
            this.mItemMusicControl.setRightToggle(false);
            this.mItemMusicControl.setValue(getLanguageText(((DeviceSettingPresenter) this.mPresenter).isMusicControlSwitchOn() ? R.string.public_opened : R.string.public_closed));
        } else {
            this.mItemMusicControl.setRightToggle(true);
            this.mItemMusicControl.setSwitchStatus(((DeviceSettingPresenter) this.mPresenter).isMusicControlSwitchOn());
        }
        this.mItemWristScreen.setSwitchStatus(((DeviceSettingPresenter) this.mPresenter).isWristScreenSwitchOn());
        this.mItemWeatherPush.setSwitchStatus(((DeviceSettingPresenter) this.mPresenter).isWeatherSwitchOn());
        this.tab1Tv.setText(getLanguageText(R.string.device_function));
        this.tab2Tv.setText(getLanguageText(R.string.nav_device_title));
    }

    private void setFindPhoneStatus(boolean z) {
        if (((DeviceSettingPresenter) this.mPresenter).isConnected()) {
            ((DeviceSettingPresenter) this.mPresenter).setFindPhoneSwitch(z);
        } else {
            setSwitchWithDisconnected(this.mItemFindPhone, z);
        }
    }

    private void setMusicControlStatus(boolean z) {
        if (((DeviceSettingPresenter) this.mPresenter).isConnected()) {
            ((DeviceSettingPresenter) this.mPresenter).setMusicSwitch(z);
        } else {
            setSwitchWithDisconnected(this.mItemMusicControl, z);
        }
    }

    private void setWristScreenStatus(boolean z) {
        if (((DeviceSettingPresenter) this.mPresenter).isConnected()) {
            ((DeviceSettingPresenter) this.mPresenter).setWristScreenSwitch(z);
        } else {
            setSwitchWithDisconnected(this.mItemWristScreen, z);
        }
    }

    private void setWeatherPushStatus(boolean z) {
        if (z && (!checkSelfPermission(PermissionUtil.getLocationPermission()) || !checkGpsSwitch())) {
            if (!checkSelfPermission(PermissionUtil.getLocationPermission())) {
                this.mItemWeatherPush.setSwitchStatus(false);
                requestPermissions(300, PermissionUtil.getLocationPermission());
                return;
            } else {
                if (checkGpsSwitch()) {
                    return;
                }
                this.mItemWeatherPush.setSwitchStatus(false);
                checkGpsSwitch();
                return;
            }
        }
        if (((DeviceSettingPresenter) this.mPresenter).isConnected()) {
            ((DeviceSettingPresenter) this.mPresenter).setWeatherSwitch(z);
        } else {
            setSwitchWithDisconnected(this.mItemWeatherPush, z);
        }
    }

    private boolean checkGpsSwitch() {
        CommBottomConfirmDialog commBottomConfirmDialog = this.mGpsOpenDialog;
        if (commBottomConfirmDialog != null) {
            commBottomConfirmDialog.dismissAllowingStateLoss();
        }
        if (BleHelper.isOpenGPS(IdoApp.getAppContext())) {
            return true;
        }
        if (this.mGpsOpenDialog == null) {
            this.mGpsOpenDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.gps_open_tips), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceSettingActivity$6i9iCVsTVk0td0reVqESdvAr_nA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$checkGpsSwitch$4$DeviceSettingActivity(view);
                }
            });
        }
        this.mGpsOpenDialog.show(getSupportFragmentManager());
        return false;
    }

    public /* synthetic */ void lambda$checkGpsSwitch$4$DeviceSettingActivity(View view) {
        startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }

    private void setSwitchWithDisconnected(CustomItemLabelView customItemLabelView, boolean z) {
        customItemLabelView.setSwitchStatus(!z);
        showToast(getLanguageText(R.string.device_pls_connect_device));
    }

    @OnClick({R.id.item_dnd_mode, R.id.item_msg_reply, R.id.item_night_light, R.id.item_quick_app, R.id.item_multi_motion_mode, R.id.item_device_language, R.id.item_device_restart, R.id.item_factory_reset, R.id.item_screen_luminance, R.id.item_world_time, R.id.item_mini_widget, R.id.item_motion_type, R.id.item_sports_data_view, R.id.item_music_control})
    public void onViewClicked(View view) {
        if (!((DeviceSettingPresenter) this.mPresenter).isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
        }
        switch (view.getId()) {
            case R.id.item_device_language /* 2131362393 */:
                if (((DeviceSettingPresenter) this.mPresenter).getSupportFunctionInfo().downloadLanguage || ((DeviceSettingPresenter) this.mPresenter).getSupportFunctionInfo().ex_table_main10_v3_get_lang_library) {
                    startActivity(new SingleTopIntent(this, (Class<?>) RemoteLanguageActivity.class));
                } else {
                    startActivity(new SingleTopIntent(this, (Class<?>) DeviceLanguageActivity.class));
                }
                break;
            case R.id.item_device_restart /* 2131362394 */:
                showRestartDialog();
                break;
            case R.id.item_dnd_mode /* 2131362401 */:
                SingleTopIntent singleTopIntent = new SingleTopIntent(this, (Class<?>) DNDModeActivity.class);
                singleTopIntent.putExtra(DNDModeActivity.DND_MODE, this.mDisturbPara);
                startActivity(singleTopIntent);
                break;
            case R.id.item_factory_reset /* 2131362407 */:
                showResetDialog();
                break;
            case R.id.item_mini_widget /* 2131362436 */:
                startActivity(new SingleTopIntent(this, (Class<?>) ShortcutEditActivity.class));
                break;
            case R.id.item_motion_type /* 2131362441 */:
                startActivity(new SingleTopIntent(this, (Class<?>) (((DeviceSettingPresenter) this.mPresenter).getSupportFunctionInfo().V3_set_100_sport_sort ? MotionTypesActivity.class : MultiMotionModeActivity.class)));
                break;
            case R.id.item_msg_reply /* 2131362444 */:
                startActivity(new SingleTopIntent(this, (Class<?>) QuickMsgReplyActivity.class));
                break;
            case R.id.item_multi_motion_mode /* 2131362445 */:
                startActivity(new SingleTopIntent(this, (Class<?>) MultiMotionModeActivity.class));
                break;
            case R.id.item_music_control /* 2131362446 */:
                if (((DeviceSettingPresenter) this.mPresenter).getSupportFunctionInfo().V3_support_set_v3_music_name) {
                    startActivity(new SingleTopIntent(this, (Class<?>) MusicControlActivity.class));
                }
                break;
            case R.id.item_night_light /* 2131362448 */:
                SingleTopIntent singleTopIntent2 = new SingleTopIntent(this, (Class<?>) NightLightActivity.class);
                singleTopIntent2.putExtra(NightLightActivity.SCREEN_BRIGHTNESS, this.mScreenBrightness);
                startActivity(singleTopIntent2);
                break;
            case R.id.item_quick_app /* 2131362464 */:
                startActivity(new SingleTopIntent(this, (Class<?>) QuickAppActivity.class));
                break;
            case R.id.item_screen_luminance /* 2131362480 */:
                startActivity(new SingleTopIntent(this, (Class<?>) ScreenLuminanceActivity.class));
                break;
            case R.id.item_sports_data_view /* 2131362485 */:
                startActivity(new SingleTopIntent(this, (Class<?>) SportsDataViewListActivity.class));
                break;
            case R.id.item_world_time /* 2131362514 */:
                startActivity(new SingleTopIntent(this, (Class<?>) WorldTimeListActivity.class));
                break;
        }
    }

    @OnClick({R.id.item_unbind})
    public void onDeleteDeviceClicked() {
        showDeleteDialog();
    }

    private void showRestartDialog() {
        if (this.mRestartDialog == null) {
            this.mRestartDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.device_restart), getLanguageText(R.string.device_restart_tip_android), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceSettingActivity$FJnioBSm5Uq_gjdTavOsBLIE5F0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showRestartDialog$5$DeviceSettingActivity(view);
                }
            });
        }
        this.mRestartDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showRestartDialog$5$DeviceSettingActivity(View view) {
        if (((DeviceSettingPresenter) this.mPresenter).isConnected()) {
            ((DeviceSettingPresenter) this.mPresenter).preRestartDevice();
        } else {
            showToast(getLanguageText(R.string.device_pls_connect_device));
        }
    }

    private void showResetDialog() {
        if (this.mResetDialog == null) {
            this.mResetDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.device_factory_reset_title), getLanguageText(R.string.device_tip_confirm_unbind), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceSettingActivity$s1Z0IJKhugr80cZ0yHIr3gMqbHw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showResetDialog$6$DeviceSettingActivity(view);
                }
            });
        }
        this.mResetDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showResetDialog$6$DeviceSettingActivity(View view) {
        showLoadingDialog(getLanguageText(R.string.device_factory_resetting), getLanguageText(R.string.setting_success), getLanguageText(R.string.setting_failed));
        ((DeviceSettingPresenter) this.mPresenter).deviceFactoryReset();
    }

    private void showDeleteDialog() {
        if (this.mDeleteDialog == null) {
            this.mDeleteDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.delete_device), getLanguageText(R.string.device_tip_confirm_unbind), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceSettingActivity$meTpu1tVUI4cgu_fEShiYOf8mMs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showDeleteDialog$7$DeviceSettingActivity(view);
                }
            });
        }
        this.mDeleteDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showDeleteDialog$7$DeviceSettingActivity(View view) {
        showLoadingDialog(getLanguageText(R.string.device_deleting), getLanguageText(R.string.device_delete_success), getLanguageText(R.string.device_delete_failed));
        ((DeviceSettingPresenter) this.mPresenter).deleteDevice();
    }

    private void showLoadingDialog(String str, String str2, String str3) {
        this.mLoadingDialog = CommLoadingDialog.getInstance(str, str2, str3);
        this.mLoadingDialog.show(getSupportFragmentManager());
    }

    @Override // com.ido.life.module.device.view.IDeviceSettingView
    public void onMusicControlSwitched(boolean z) {
        this.mItemMusicControl.setSwitchStatus(z);
        if (z) {
            startService(new Intent(this, (Class<?>) MusicControlService.class));
        }
    }

    @Override // com.ido.life.module.device.view.IDeviceSettingView
    public void onSetCmdFailed() {
        if (this.isVisible) {
            showCmdResultToast(false);
        }
    }

    @Override // com.ido.life.module.device.view.IDeviceSettingView
    public void onSyncing() {
        showToast(getLanguageText(R.string.syncing));
    }

    @Override // com.ido.life.module.device.view.IDeviceSettingView
    public void onRestartStart() {
        showLoadingDialog(getLanguageText(R.string.device_restarting), getLanguageText(R.string.device_restart_success), getLanguageText(R.string.device_restart_failed));
    }

    @Override // com.ido.life.module.device.view.IDeviceSettingView
    public void onRestartSuccess() {
        CommLoadingDialog commLoadingDialog = this.mLoadingDialog;
        if (commLoadingDialog != null) {
            commLoadingDialog.setComplete(true);
        }
    }

    @Override // com.ido.life.module.device.view.IDeviceSettingView
    public void onRestartFailed() {
        CommLoadingDialog commLoadingDialog = this.mLoadingDialog;
        if (commLoadingDialog != null) {
            commLoadingDialog.setComplete(false);
        }
    }

    @Override // com.ido.life.module.device.view.IDeviceSettingView
    public void onDeleteSuccess() {
        CommLoadingDialog commLoadingDialog = this.mLoadingDialog;
        if (commLoadingDialog != null) {
            commLoadingDialog.setComplete(true);
        }
        setResult(10, new Intent());
        finish();
    }

    @Override // com.ido.life.module.device.view.IDeviceSettingView
    public void onDeleteFailed() {
        CommLoadingDialog commLoadingDialog = this.mLoadingDialog;
        if (commLoadingDialog != null) {
            commLoadingDialog.setComplete(false);
        }
    }

    @Override // com.ido.life.module.device.view.IDeviceSettingView
    public void onResetSuccess() {
        CommLoadingDialog commLoadingDialog = this.mLoadingDialog;
        if (commLoadingDialog != null) {
            commLoadingDialog.setComplete(true);
        }
        setResult(10, new Intent());
        finish();
    }

    @Override // com.ido.life.module.device.view.IDeviceSettingView
    public void onResetFailed() {
        CommLoadingDialog commLoadingDialog = this.mLoadingDialog;
        if (commLoadingDialog != null) {
            commLoadingDialog.setComplete(false);
        }
    }

    @Override // com.ido.life.module.device.view.IDeviceSettingView
    public void onGetDNDMode(NotDisturbPara notDisturbPara) {
        this.mDisturbPara = notDisturbPara;
        this.mItemDndMode.setValue(getLanguageText((notDisturbPara.onOFf == 170 || notDisturbPara.noontimeRestOnOff == 170) ? R.string.public_opened : R.string.public_closed));
    }

    @Override // com.ido.life.module.device.view.IDeviceSettingView
    public void onGetUpHandGesture(UpHandGesture upHandGesture) {
        this.mItemWristScreen.setSwitchStatus(upHandGesture.onOff == 170);
    }

    @Override // com.ido.life.module.device.view.IDeviceSettingView
    public void onGetScreenBrightness(ScreenBrightness screenBrightness) {
        this.mScreenBrightness = screenBrightness;
        if (screenBrightness == null) {
            return;
        }
        this.mItemScreenLuminance.setValue(String.format(getLanguageText(R.string.device_x_level), Integer.valueOf(((DeviceSettingPresenter) this.mPresenter).formatValue2Level(screenBrightness.level))));
        this.mItemNightLight.setValue(getLanguageText(screenBrightness.autoAdjustNight == 3 ? R.string.public_opened : R.string.public_closed));
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.activity.DeviceSettingActivity$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType = new int[SettingCallBack.SettingType.values().length];

        static {
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.NOT_DISTURB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.FIND_PHONE_SWITCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.MUSIC_SWITCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.UP_HAND_GESTURE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.WEATHER_SWITCH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // com.ido.life.module.device.view.IDeviceSettingView
    public void onSetCmdSuccess(SettingCallBack.SettingType settingType) {
        int i = AnonymousClass1.$SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[settingType.ordinal()];
        if (i == 1) {
            this.mItemDndMode.setValue(getLanguageText(((DeviceSettingPresenter) this.mPresenter).isDndModeSwitchOn() ? R.string.public_opened : R.string.public_closed));
            return;
        }
        if (i == 2) {
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_FIND_MOBILE_SUCCESS, "", null);
            if (((DeviceSettingPresenter) this.mPresenter).isFindPhoneSwitchOn()) {
                startService(new Intent(this, (Class<?>) DeviceAssistService.class));
                return;
            }
            return;
        }
        if (i == 3 && ((DeviceSettingPresenter) this.mPresenter).isMusicControlSwitchOn()) {
            startService(new Intent(this, (Class<?>) MusicControlService.class));
        }
    }

    @Override // com.ido.life.module.device.view.IDeviceSettingView
    public void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        int i = AnonymousClass1.$SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[settingType.ordinal()];
        if (i == 2) {
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_FIND_MOBILE_FAILURE, "", null);
            this.mItemFindPhone.setSwitchStatus(!r0.getSwitchStatus());
        } else if (i == 3) {
            this.mItemMusicControl.setSwitchStatus(!r0.getSwitchStatus());
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_MUSIC_FAILURE, "", null);
        } else if (i == 4) {
            this.mItemWristScreen.setSwitchStatus(!r0.getSwitchStatus());
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_BRIGHTEN_THE_SCREEN_FAILURE, "", null);
        } else if (i == 5) {
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_WEATHER_FAILURE, "", null);
            this.mItemWeatherPush.setSwitchStatus(!r0.getSwitchStatus());
        }
        if (!this.isVisible || settingType == SettingCallBack.SettingType.MUSIC_CONTROL_INFO) {
            return;
        }
        showCmdResultToast(false);
    }
}