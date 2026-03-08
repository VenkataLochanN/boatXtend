package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.common.IdoApp;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.utils.PermissionUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.ble.BleHelper;
import com.ido.life.boatservice.DeviceAssistService;
import com.ido.life.boatservice.MusicControlService;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.alexa.AlexaLoginActivity;
import com.ido.life.module.device.presenter.MorePresenter;
import com.ido.life.module.device.view.IMoreView;

/* JADX INFO: loaded from: classes2.dex */
public class MoreActivity extends BaseActivity<MorePresenter> implements IMoreView {
    private boolean isVisible;
    private NotDisturbPara mDisturbPara;
    private CommBottomConfirmDialog mGpsOpenDialog;

    @BindView(R.id.item_dnd_mode)
    CustomItemLabelView mItemDndMode;

    @BindView(R.id.item_find_phone)
    CustomItemLabelView mItemFindPhone;

    @BindView(R.id.item_msg_reply)
    CustomItemLabelView mItemMsgReply;

    @BindView(R.id.item_music_control)
    CustomItemLabelView mItemMusicControl;

    @BindView(R.id.item_night_light)
    CustomItemLabelView mItemNightLight;

    @BindView(R.id.item_weather_push)
    CustomItemLabelView mItemWeatherPush;

    @BindView(R.id.item_wrist_screen)
    CustomItemLabelView mItemWristScreen;
    private ScreenBrightness mScreenBrightness;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_more;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        CustomItemLabelView customItemLabelView = this.mItemDndMode;
        boolean zIsDndModeSwitchOn = ((MorePresenter) this.mPresenter).isDndModeSwitchOn();
        int i = R.string.public_opened;
        customItemLabelView.setValue(getLanguageText(zIsDndModeSwitchOn ? R.string.public_opened : R.string.public_closed));
        this.mItemFindPhone.setSwitchStatus(((MorePresenter) this.mPresenter).isFindPhoneSwitchOn());
        this.mItemMusicControl.setSwitchStatus(((MorePresenter) this.mPresenter).isMusicControlSwitchOn());
        this.mItemWristScreen.setSwitchStatus(((MorePresenter) this.mPresenter).isWristScreenSwitchOn());
        this.mItemWeatherPush.setSwitchStatus(((MorePresenter) this.mPresenter).isWeatherSwitchOn());
        CustomItemLabelView customItemLabelView2 = this.mItemNightLight;
        if (!((MorePresenter) this.mPresenter).isNightLightSwitchOn()) {
            i = R.string.public_closed;
        }
        customItemLabelView2.setValue(getLanguageText(i));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        initSwitchEvent();
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

    private void initSwitchEvent() {
        this.mItemFindPhone.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$MoreActivity$q-ujFPRHI985iQ1K5HyzmzaSItQ
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$0$MoreActivity(view, z);
            }
        });
        this.mItemMusicControl.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$MoreActivity$ulUBvzcu6DGbJqKpeMAg7hq3T1c
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$1$MoreActivity(view, z);
            }
        });
        this.mItemWristScreen.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$MoreActivity$mRr5dr31rZCA5H3SYqbRI8SjJgM
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$2$MoreActivity(view, z);
            }
        });
        this.mItemWeatherPush.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$MoreActivity$M17aHrOFSpN0IHf-_ZDL6gBGbpw
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$3$MoreActivity(view, z);
            }
        });
    }

    public /* synthetic */ void lambda$initSwitchEvent$0$MoreActivity(View view, boolean z) {
        setFindPhoneStatus(z);
    }

    public /* synthetic */ void lambda$initSwitchEvent$1$MoreActivity(View view, boolean z) {
        setMusicControlStatus(z);
    }

    public /* synthetic */ void lambda$initSwitchEvent$2$MoreActivity(View view, boolean z) {
        setWristScreenStatus(z);
    }

    public /* synthetic */ void lambda$initSwitchEvent$3$MoreActivity(View view, boolean z) {
        setWeatherPushStatus(z);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_more));
        this.mItemDndMode.setTitle(getLanguageText(R.string.device_dnd_mode));
        this.mItemFindPhone.setTitle(getLanguageText(R.string.device_find_phone));
        this.mItemMusicControl.setTitle(getLanguageText(R.string.device_music_control));
        this.mItemWristScreen.setTitle(getLanguageText(R.string.device_wrist_screen));
        this.mItemWeatherPush.setTitle(getLanguageText(R.string.device_weather_push));
        this.mItemMsgReply.setTitle(getLanguageText(R.string.device_quick_msg_reply));
        this.mItemNightLight.setTitle(getLanguageText(R.string.device_night_auto_light));
    }

    private void setFindPhoneStatus(boolean z) {
        if (((MorePresenter) this.mPresenter).isConnected()) {
            ((MorePresenter) this.mPresenter).setFindPhoneSwitch(z);
        } else {
            setSwitchWithDisconnected(this.mItemFindPhone, z);
        }
    }

    private void setMusicControlStatus(boolean z) {
        if (((MorePresenter) this.mPresenter).isConnected()) {
            ((MorePresenter) this.mPresenter).setMusicSwitch(z);
        } else {
            setSwitchWithDisconnected(this.mItemMusicControl, z);
        }
    }

    private void setWristScreenStatus(boolean z) {
        if (((MorePresenter) this.mPresenter).isConnected()) {
            ((MorePresenter) this.mPresenter).setWristScreenSwitch(z);
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
        if (((MorePresenter) this.mPresenter).isConnected()) {
            ((MorePresenter) this.mPresenter).setWeatherSwitch(z);
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
            this.mGpsOpenDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.gps_open_tips), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$MoreActivity$ronbJMQ_yjd_KkN692c562IMqWQ
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$checkGpsSwitch$4$MoreActivity(view);
                }
            });
        }
        this.mGpsOpenDialog.show(getSupportFragmentManager());
        return false;
    }

    public /* synthetic */ void lambda$checkGpsSwitch$4$MoreActivity(View view) {
        startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }

    private void setSwitchWithDisconnected(CustomItemLabelView customItemLabelView, boolean z) {
        customItemLabelView.setSwitchStatus(!z);
        showToast(getLanguageText(R.string.device_pls_connect_device));
    }

    @OnClick({R.id.item_dnd_mode, R.id.item_msg_reply, R.id.item_night_light, R.id.item_amazon_alexa})
    public void onViewClicked(View view) {
        if (!((MorePresenter) this.mPresenter).isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
        }
        switch (view.getId()) {
            case R.id.item_amazon_alexa /* 2131362382 */:
                startActivity(new Intent(this, (Class<?>) AlexaLoginActivity.class));
                break;
            case R.id.item_dnd_mode /* 2131362401 */:
                Intent intent = new Intent(this, (Class<?>) DNDModeActivity.class);
                intent.putExtra(DNDModeActivity.DND_MODE, this.mDisturbPara);
                startActivity(intent);
                break;
            case R.id.item_msg_reply /* 2131362444 */:
                startActivity(new Intent(this, (Class<?>) QuickMsgReplyActivity.class));
                break;
            case R.id.item_night_light /* 2131362448 */:
                Intent intent2 = new Intent(this, (Class<?>) NightLightActivity.class);
                intent2.putExtra(NightLightActivity.SCREEN_BRIGHTNESS, this.mScreenBrightness);
                startActivity(intent2);
                break;
        }
    }

    @Override // com.ido.life.module.device.view.IMoreView
    public void onGetDNDMode(NotDisturbPara notDisturbPara) {
        this.mDisturbPara = notDisturbPara;
        this.mItemDndMode.setValue(getLanguageText((notDisturbPara.onOFf == 170 || notDisturbPara.noontimeRestOnOff == 170) ? R.string.public_opened : R.string.public_closed));
    }

    @Override // com.ido.life.module.device.view.IMoreView
    public void onGetUpHandGesture(UpHandGesture upHandGesture) {
        this.mItemWristScreen.setSwitchStatus(upHandGesture.onOff == 170);
    }

    @Override // com.ido.life.module.device.view.IMoreView
    public void onGetScreenBrightness(ScreenBrightness screenBrightness) {
        this.mScreenBrightness = screenBrightness;
        this.mItemNightLight.setValue(getLanguageText(screenBrightness.autoAdjustNight == 3 ? R.string.public_opened : R.string.public_closed));
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.activity.MoreActivity$1, reason: invalid class name */
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
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.SCREEN_BRIGHTNESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.MUSIC_SWITCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.UP_HAND_GESTURE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.WEATHER_SWITCH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Override // com.ido.life.module.device.view.IMoreView
    public void onSetCmdSuccess(SettingCallBack.SettingType settingType) {
        int i = AnonymousClass1.$SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[settingType.ordinal()];
        int i2 = R.string.public_opened;
        if (i == 1) {
            CustomItemLabelView customItemLabelView = this.mItemDndMode;
            if (!((MorePresenter) this.mPresenter).isDndModeSwitchOn()) {
                i2 = R.string.public_closed;
            }
            customItemLabelView.setValue(getLanguageText(i2));
            return;
        }
        if (i == 2) {
            if (((MorePresenter) this.mPresenter).isFindPhoneSwitchOn()) {
                startService(new Intent(this, (Class<?>) DeviceAssistService.class));
            }
        } else {
            if (i == 3) {
                CustomItemLabelView customItemLabelView2 = this.mItemNightLight;
                if (!((MorePresenter) this.mPresenter).isNightLightSwitchOn()) {
                    i2 = R.string.public_closed;
                }
                customItemLabelView2.setValue(getLanguageText(i2));
                return;
            }
            if (i == 4 && ((MorePresenter) this.mPresenter).isMusicControlSwitchOn()) {
                startService(new Intent(this, (Class<?>) MusicControlService.class));
            }
        }
    }

    @Override // com.ido.life.module.device.view.IMoreView
    public void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        int i = AnonymousClass1.$SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[settingType.ordinal()];
        if (i == 2) {
            this.mItemFindPhone.setSwitchStatus(!r2.getSwitchStatus());
        } else if (i == 4) {
            this.mItemMusicControl.setSwitchStatus(!r2.getSwitchStatus());
        } else if (i == 5) {
            this.mItemWristScreen.setSwitchStatus(!r2.getSwitchStatus());
        } else if (i == 6) {
            this.mItemWeatherPush.setSwitchStatus(!r2.getSwitchStatus());
        }
        if (this.isVisible) {
            showCmdResultToast(false);
        }
    }
}