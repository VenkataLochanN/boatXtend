package com.ido.life.module.device.activity;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.boatservice.MusicControlService;
import com.ido.life.constants.Constants;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.MusicControlPresenter;
import com.ido.life.module.device.view.IMusicControlView;
import com.ido.life.util.AppLogUploadManager;
import com.ido.life.util.MsgNotificationHelper;
import com.ido.life.util.eventbus.EventBusHelper;

/* JADX INFO: loaded from: classes2.dex */
public class MusicControlActivity extends BaseActivity<MusicControlPresenter> implements IMusicControlView, CustomToggleButton.OnSwitchListener {
    private boolean isToOpenSwitch = false;
    private boolean isVisible;

    @BindView(R.id.item_music_control)
    CustomItemLabelView mItemMusicControl;

    @BindView(R.id.item_music_show_name)
    CustomItemLabelView mItemMusicNameControl;

    @BindView(R.id.tv_tip)
    RegularTextView mTvTip;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_music_control;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        initFunctionItem();
        initSwitchListener();
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

    private void initTipText() {
        isNotificationEnabled();
        String string = getString(R.string.device_music_permissions);
        String string2 = getString(R.string.sport_setting_manager_to_set);
        String strConcat = string.concat("\t\t").concat(string2);
        int iIndexOf = strConcat.indexOf(string2);
        SpannableString spannableString = new SpannableString(strConcat);
        spannableString.setSpan(new ClickableSpan() { // from class: com.ido.life.module.device.activity.MusicControlActivity.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                MsgNotificationHelper.saveLog("click top tips");
                MusicControlActivity.this.jump2SettingActivity();
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
                textPaint.setColor(MusicControlActivity.this.getColor(R.color.color_red));
            }
        }, iIndexOf, string2.length() + iIndexOf, 33);
        this.mTvTip.setText(spannableString);
        this.mTvTip.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        initTipText();
        this.mTitleBar.setTitle(getString(R.string.device_music_control));
        this.mItemMusicControl.setTitle(getLanguageText(R.string.device_music_control));
        this.mItemMusicNameControl.setTitle(getLanguageText(R.string.device_music_name));
        this.mItemMusicControl.setSwitchStatus(((MusicControlPresenter) this.mPresenter).isMusicControlSwitchOn());
        this.mItemMusicNameControl.setSwitchStatus(canMusicNameSwitchOn());
    }

    private boolean canMusicNameSwitchOn() {
        return ((MusicControlPresenter) this.mPresenter).isMusicControlSwitchOn() && isNotificationEnabled() && ((MusicControlPresenter) this.mPresenter).getMusicNameSwitch();
    }

    private void initFunctionItem() {
        SupportFunctionInfo supportFunctionInfo = ((MusicControlPresenter) this.mPresenter).getSupportFunctionInfo();
        this.mItemMusicControl.setVisibility(supportFunctionInfo.bleControlMusic ? 0 : 8);
        this.mItemMusicNameControl.setVisibility(supportFunctionInfo.V3_support_set_v3_music_name ? 0 : 8);
    }

    private void initSwitchListener() {
        this.mItemMusicControl.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$MusicControlActivity$xXJDGnrGE8BZR5P76XYJuZTPr9Q
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchListener$0$MusicControlActivity(view, z);
            }
        });
        this.mItemMusicNameControl.setOnSwitchListener(this);
        if (this.mItemMusicControl.getVisibility() == 0) {
            this.mItemMusicControl.setSwitchStatus(((MusicControlPresenter) this.mPresenter).isMusicControlSwitchOn());
        }
    }

    public /* synthetic */ void lambda$initSwitchListener$0$MusicControlActivity(View view, boolean z) {
        setMusicControlStatus(z);
    }

    private void setMusicControlStatus(boolean z) {
        MsgNotificationHelper.saveLog("setMusicControlStatus " + z);
        if (!z) {
            this.mItemMusicNameControl.setSwitchStatus(false);
        }
        this.mItemMusicNameControl.setSwitchEnable(z);
        if (((MusicControlPresenter) this.mPresenter).isConnected()) {
            showLoadingDialog();
            ((MusicControlPresenter) this.mPresenter).setMusicSwitch(z);
        } else {
            setSwitchWithDisconnected(this.mItemMusicControl, z);
        }
    }

    private void setMusicControlNameStatus(boolean z) {
        MsgNotificationHelper.saveLog("setMusicControlNameStatus " + z);
        if (((MusicControlPresenter) this.mPresenter).isConnected()) {
            ((MusicControlPresenter) this.mPresenter).setMusicNameSwitch(z);
        } else {
            setSwitchWithDisconnected(this.mItemMusicControl, z);
        }
    }

    private void setSwitchWithDisconnected(CustomItemLabelView customItemLabelView, boolean z) {
        customItemLabelView.setSwitchStatus(!z);
        showToast(getLanguageText(R.string.device_pls_connect_device));
    }

    @Override // com.ido.life.module.device.view.IMusicControlView
    public void onMusicControlSwitched(boolean z) {
        this.mItemMusicControl.setSwitchStatus(z);
        if (z) {
            startService(new Intent(this, (Class<?>) MusicControlService.class));
        }
    }

    public void settingDialog(final boolean z) {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.device_music_control), getLanguageText(R.string.device_music_permissions), ResourceUtil.getString(R.string.sport_setting_manager_to_set), ResourceUtil.getString(R.string.public_tip_cancel), true);
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.MusicControlActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MusicControlActivity.this.mItemMusicNameControl.setSwitchStatus(false);
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.MusicControlActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MsgNotificationHelper.saveLog("click music control dialog tips");
                if (z) {
                    MusicControlActivity.this.isToOpenSwitch = true;
                }
                MusicControlActivity.this.jump2SettingActivity();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jump2SettingActivity() {
        MsgNotificationHelper.saveLog("NotificationActivity，jump2SettingActivity");
        try {
            startActivityForResult(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"), 500);
        } catch (ActivityNotFoundException e2) {
            MsgNotificationHelper.saveLog("ActivityNotFoundException ：" + e2.toString());
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$NotificationAccessSettingsActivity"));
                intent.putExtra(":settings:show_fragment", "NotificationAccessSettings");
                startActivityForResult(intent, 500);
            } catch (Exception e3) {
                MsgNotificationHelper.saveLog("Exception ：" + e3.toString());
                e3.printStackTrace();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getNotificationLogPath(), "--------------对不起，您的手机暂不支持------------->>");
            }
            e2.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.activity.MusicControlActivity$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType = new int[SettingCallBack.SettingType.values().length];

        static {
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.MUSIC_SWITCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @Override // com.ido.life.module.device.view.IMusicControlView
    public void onSetCmdSuccess(SettingCallBack.SettingType settingType) {
        if (AnonymousClass4.$SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[settingType.ordinal()] != 1) {
            return;
        }
        dismissLoadingDialog();
        boolean zIsMusicControlSwitchOn = ((MusicControlPresenter) this.mPresenter).isMusicControlSwitchOn();
        if (zIsMusicControlSwitchOn) {
            startService(new Intent(this, (Class<?>) MusicControlService.class));
        } else {
            ((MusicControlPresenter) this.mPresenter).setMusicNameSwitch(false);
        }
        EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_MUSIC_CONTROL_CHANGED, Boolean.valueOf(zIsMusicControlSwitchOn)));
    }

    @Override // com.ido.life.module.device.view.IMusicControlView
    public void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        if (AnonymousClass4.$SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[settingType.ordinal()] != 1) {
            return;
        }
        dismissLoadingDialog();
        CustomItemLabelView customItemLabelView = this.mItemMusicControl;
        customItemLabelView.setSwitchStatus(true ^ customItemLabelView.getSwitchStatus());
        this.mItemMusicNameControl.setSwitchStatus(canMusicNameSwitchOn());
        AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_MUSIC_FAILURE, "", null);
        if (this.isVisible) {
            showCmdResultToast(false);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 500) {
            boolean zIsNotificationEnabled = isNotificationEnabled();
            if (this.isToOpenSwitch && zIsNotificationEnabled) {
                this.mItemMusicNameControl.setSwitchStatus(true);
                setMusicControlNameStatus(true);
            }
            if (zIsNotificationEnabled) {
                EventBusHelper.postSticky(Constants.EventConstants.EVENT_NOTIFICATION_PERMISSION_CHANGED);
            }
        }
    }

    @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
    public void onSwitched(View view, boolean z) {
        MsgNotificationHelper.saveLog("onSwitched " + z);
        if (!z) {
            setMusicControlNameStatus(this.mItemMusicNameControl.getSwitchStatus());
        } else if (isNotificationEnabled()) {
            setMusicControlNameStatus(this.mItemMusicNameControl.getSwitchStatus());
        } else {
            settingDialog(true);
        }
    }
}