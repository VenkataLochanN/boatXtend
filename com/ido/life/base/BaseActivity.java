package com.ido.life.base;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.AppUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.common.utils.StatusBarsUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.base.BasePresenter;
import com.ido.life.constants.Constants;
import com.ido.life.constants.LanguageCodeHelper;
import com.ido.life.customview.CommonTitleBarHelper;
import com.ido.life.data.api.entity.OtaEntity;
import com.ido.life.data.api.entity.RemoteLanguage;
import com.ido.life.database.model.RemoteLanguageMsg;
import com.ido.life.dialog.RemoteLanguageDialogFragment;
import com.ido.life.module.device.activity.DeviceUpgradeNewActivity;
import com.ido.life.module.main.MainActivity;
import com.ido.life.util.ConnectLogHelper;
import com.ido.life.util.DateUtil;
import com.ido.life.util.DialogHelper;
import com.ido.life.util.ObjectUtil;
import com.ido.life.util.RemoteLanguageHelper;
import com.ido.life.util.SPHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import com.ido.life.util.eventbus.EventBusWrapper;
import com.ido.life.util.eventbus.IHandlerEventBus;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseActivity<P extends BasePresenter> extends BaseCoreActivity implements IBaseView, IHandlerEventBus, RemoteLanguageHelper.OnRemoteLanguageListener {
    private Unbinder mBind;
    private CommBottomConfirmDialog mConfirmDialog;
    private long mDialogShowTime;
    EventBusWrapper mEventBus;
    private boolean mInBackGround;
    private CommBottomConfirmDialog mOtaDialog;
    protected P mPresenter;
    private RemoteLanguageDialogFragment mRemoteLanguageDialog;
    protected CommonTitleBarHelper mTitleBar;
    private WaitingDialog mWaitingDialog;
    CommBottomConfirmDialog rebootBluetoothDialog;

    protected void initLabelLanguage() {
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public void initViews() {
    }

    protected boolean isFunctionActivity() {
        return false;
    }

    protected boolean needEventBus() {
        return true;
    }

    protected boolean needPortrait() {
        return true;
    }

    protected void sendCmd() {
    }

    protected boolean useButterKnife() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showSettingLoading(boolean z) {
        showLoadingDialog(z);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public void setContentView(View view) {
        super.setContentView(view);
        viewCreate();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public void setContentView(int i) {
        super.setContentView(i);
        CommonLogUtil.d("******************" + toString());
        viewCreate();
    }

    protected void viewCreate() {
        if (useButterKnife()) {
            this.mBind = ButterKnife.bind(this);
        }
        this.mTitleBar = new CommonTitleBarHelper();
        this.mTitleBar.init(this);
        if (isFunctionActivity()) {
            this.mTitleBar.setBarBackground(R.color.color_1E1E1E);
            setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        } else if (isStatusBgWhite()) {
            setStatusBarColor(-1);
        } else {
            setStatusBarColor(getColor(R.color.color_1E1E1E));
        }
        StatusBarsUtil.setStatusTextColor(this, isStatusBgWhite());
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (bundle == null || (this instanceof MainActivity)) {
            return;
        }
        finish();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public void actionBeforeSetContentView() {
        if (needPortrait() && getResources().getConfiguration().orientation != 1) {
            setRequestedOrientation(1);
        }
        requestWindowFeature(1);
        if (needEventBus()) {
            this.mEventBus = new EventBusWrapper();
            this.mEventBus.register(this);
        }
        initPresenter();
        ScreenUtil.setImmersiveStatusBar(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        initLabelLanguage();
        if (this.mInBackGround) {
            fromBackToForeground();
        }
        this.mInBackGround = false;
        AppUtil.appIsRunning(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        getWindow().getDecorView().postDelayed(new Runnable() { // from class: com.ido.life.base.-$$Lambda$BaseActivity$ur2hCg4VdCdyAqjlZh9pmGYDAAQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onStop$0$BaseActivity();
            }
        }, 500L);
    }

    public /* synthetic */ void lambda$onStop$0$BaseActivity() {
        if (AppUtil.appIsRunning(this)) {
            return;
        }
        this.mInBackGround = true;
        fromForegroundToBack();
    }

    public boolean inBackGround() {
        return this.mInBackGround;
    }

    @Override // android.app.Activity
    public void setTitle(int i) {
        this.mTitleBar.setTitle(i);
    }

    @Override // android.app.Activity
    public void setTitle(CharSequence charSequence) {
        this.mTitleBar.setTitle((String) charSequence);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getLanguageText(int i) {
        return LanguageUtil.getLanguageText(i);
    }

    protected void setNavigationBar() {
        ScreenUtil.setNavigationBar(this, getResources().getDimension(R.dimen.common_tittle_height));
    }

    @Override // com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        if (baseMessage == null) {
            return;
        }
        int type = baseMessage.getType();
        if (type == 501) {
            CommonLogUtil.d(getClass().getSimpleName(), "finish");
            finish();
            return;
        }
        if (type == 828) {
            if (DialogHelper.appUpdateDialogShowing()) {
                CommonLogUtil.printAndSave("已经显示了APP强制升级弹框，不再弹出蓝牙重启弹框。");
                return;
            } else {
                showRebootBluetoothDialog();
                return;
            }
        }
        if (type == 801 || type == 802) {
            if (DialogHelper.appUpdateDialogShowing()) {
                CommonLogUtil.printAndSave("已经显示了APP强制升级弹框，不再弹出语言升级弹框。");
                return;
            } else {
                showRemoteLanguageDialog(baseMessage);
                return;
            }
        }
        if (type == 824) {
            CommBottomConfirmDialog commBottomConfirmDialog = this.mOtaDialog;
            if (commBottomConfirmDialog != null) {
                commBottomConfirmDialog.dismissAllowingStateLoss();
                this.mOtaDialog = null;
                return;
            }
            return;
        }
        if (type == 825) {
            if (DialogHelper.appUpdateDialogShowing()) {
                CommonLogUtil.printAndSave("已经显示了APP强制升级弹框，不再弹出固件升级弹框。");
                return;
            } else {
                showNewFirmwareDialog(1, (OtaEntity.OtaInfo) baseMessage.getData());
                return;
            }
        }
        if (type == 830) {
            if (DialogHelper.appUpdateDialogShowing()) {
                CommonLogUtil.printAndSave("已经显示了APP强制升级弹框，不再弹出字库升级弹框。");
                return;
            } else {
                showNewFirmwareDialog(2, (OtaEntity.OtaInfo) baseMessage.getData());
                return;
            }
        }
        if (type != 831) {
            return;
        }
        if (DialogHelper.appUpdateDialogShowing()) {
            CommonLogUtil.printAndSave("已经显示了APP强制升级弹框，不再弹出系统组件升级弹框。");
        } else {
            showNewFirmwareDialog(4, (OtaEntity.OtaInfo) baseMessage.getData());
        }
    }

    protected void showNewFirmwareDialog(final int i, OtaEntity.OtaInfo otaInfo) {
        if (otaInfo == null) {
            return;
        }
        if (!BLEManager.isConnected() || !BLEManager.isBind()) {
            ConnectLogHelper.saveLog("BaseActivity", "提示用户升级：过滤 connect:" + BLEManager.isConnected());
            return;
        }
        if (isForeground()) {
            CommBottomConfirmDialog commBottomConfirmDialog = this.mOtaDialog;
            if (commBottomConfirmDialog == null || !commBottomConfirmDialog.isDialogShowing()) {
                RemoteLanguageDialogFragment remoteLanguageDialogFragment = this.mRemoteLanguageDialog;
                if (remoteLanguageDialogFragment == null || !remoteLanguageDialogFragment.isDialogShowing()) {
                    try {
                        if (i == 4) {
                            otaInfo.getFirmwareVersion();
                        } else {
                            otaInfo.getVersion();
                        }
                        ConnectLogHelper.saveLog("BaseActivity", "提示用户升级：" + i);
                        boolean z = true;
                        String languageText = getLanguageText(i == 1 ? R.string.firmware_upgrade : i == 2 ? R.string.resource_upgrade : R.string.system_component_upgrade);
                        String languageText2 = getLanguageText(R.string.device_checked_new_firmware);
                        String languageText3 = getLanguageText(R.string.device_to_upgrade);
                        String languageText4 = getLanguageText(R.string.public_tip_cancel);
                        if (otaInfo.isForced()) {
                            z = false;
                        }
                        this.mOtaDialog = CommBottomConfirmDialog.newInstance(languageText, languageText2, languageText3, languageText4, z, false, false).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.base.-$$Lambda$BaseActivity$655_Pt9D-t_XnsN1Sj5rB-cFPBI
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                this.f$0.lambda$showNewFirmwareDialog$1$BaseActivity(i, view);
                            }
                        }).setCancelListener(new View.OnClickListener() { // from class: com.ido.life.base.-$$Lambda$BaseActivity$dZGI83fygJ8Qi9BOATvn8VFiTNs
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                this.f$0.lambda$showNewFirmwareDialog$2$BaseActivity(i, view);
                            }
                        });
                        this.mOtaDialog.show(getSupportFragmentManager());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    public /* synthetic */ void lambda$showNewFirmwareDialog$1$BaseActivity(int i, View view) {
        startActivity(new Intent(this, (Class<?>) DeviceUpgradeNewActivity.class));
        saveOtaReminderDate(i);
    }

    public /* synthetic */ void lambda$showNewFirmwareDialog$2$BaseActivity(int i, View view) {
        this.mOtaDialog = null;
        saveOtaReminderDate(i);
    }

    private void saveOtaReminderDate(int i) {
        if (i == 1) {
            SPHelper.saveLastOtaReminderDate(DateUtil.format(DateUtil.getTodayDate(), DateUtil.DATE_FORMAT_YMD));
        } else if (i == 2) {
            SPHelper.saveLastFlashReminderDate(DateUtil.format(DateUtil.getTodayDate(), DateUtil.DATE_FORMAT_YMD));
        } else {
            SPHelper.saveLastSysReminderDate(DateUtil.format(DateUtil.getTodayDate(), DateUtil.DATE_FORMAT_YMD));
        }
    }

    protected void initPresenter() {
        this.mPresenter = (P) autoCreatePresenter();
        P p = this.mPresenter;
        if (p != null) {
            try {
                p.attachView(this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public P autoCreatePresenter() {
        return (P) ObjectUtil.getParameterizedType(getClass());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Unbinder unbinder = this.mBind;
        if (unbinder != null) {
            unbinder.unbind();
        }
        EventBusWrapper eventBusWrapper = this.mEventBus;
        if (eventBusWrapper != null) {
            eventBusWrapper.unregister();
        }
        P p = this.mPresenter;
        if (p != null) {
            p.detachView();
        }
        dismissLoadingDialog(true);
    }

    public void showLoadingDialog(String str) {
        showLoadingDialog(str, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showLoadingDialog() {
        showLoadingDialog((String) null);
    }

    protected void showLoadingDialog(boolean z) {
        showLoadingDialog(null, z);
    }

    protected void showLoadingDialog(String str, boolean z) {
        WaitingDialog waitingDialog = this.mWaitingDialog;
        if (waitingDialog == null || !waitingDialog.isDialogShowing()) {
            this.mDialogShowTime = System.currentTimeMillis();
            this.mWaitingDialog = WaitingDialog.newInstance(str, z);
            this.mWaitingDialog.show(getSupportFragmentManager());
        }
    }

    protected void showLoadingDialog(String str, boolean z, Drawable drawable) {
        WaitingDialog waitingDialog = this.mWaitingDialog;
        if (waitingDialog == null || !waitingDialog.isDialogShowing()) {
            this.mDialogShowTime = System.currentTimeMillis();
            this.mWaitingDialog = WaitingDialog.newInstance(str, z);
            this.mWaitingDialog.show(getSupportFragmentManager());
            this.mWaitingDialog.setBackgroundDrawable(drawable);
        }
    }

    public void dismissLoadingDialog() {
        dismissLoadingDialog(false);
    }

    protected void dismissLoadingDialog(boolean z) {
        WaitingDialog waitingDialog = this.mWaitingDialog;
        if (waitingDialog == null || !waitingDialog.isAdded()) {
            return;
        }
        try {
            if (z) {
                this.mWaitingDialog.dismissAllowingStateLoss();
                this.mWaitingDialog = null;
            } else {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - this.mDialogShowTime < 1000 && jCurrentTimeMillis - this.mDialogShowTime > 100) {
                    getWindow().getDecorView().postDelayed(new Runnable() { // from class: com.ido.life.base.-$$Lambda$BaseActivity$1uWrjp2Ue26itzYjp40fAmQR6Y8
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$dismissLoadingDialog$3$BaseActivity();
                        }
                    }, 500L);
                } else if (this.mWaitingDialog != null) {
                    this.mWaitingDialog.dismissAllowingStateLoss();
                    this.mWaitingDialog = null;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            this.mWaitingDialog = null;
        }
    }

    public /* synthetic */ void lambda$dismissLoadingDialog$3$BaseActivity() {
        WaitingDialog waitingDialog = this.mWaitingDialog;
        if (waitingDialog != null) {
            waitingDialog.dismissAllowingStateLoss();
            this.mWaitingDialog = null;
        }
    }

    protected void showDataChangedDialog() {
        if (this.mConfirmDialog == null) {
            this.mConfirmDialog = CommBottomConfirmDialog.newInstance(getString(R.string.device_tip_save_ornot), getString(R.string.save), getString(R.string.device_abandon), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.base.-$$Lambda$BaseActivity$7PJC-ciBk-MDhhJf3p2FsziS6ek
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showDataChangedDialog$4$BaseActivity(view);
                }
            }).setCancelListener(new View.OnClickListener() { // from class: com.ido.life.base.-$$Lambda$BaseActivity$H_luP_EtxnQzEHtv1KTHoJZJKvc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showDataChangedDialog$5$BaseActivity(view);
                }
            });
        }
        this.mConfirmDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showDataChangedDialog$4$BaseActivity(View view) {
        if (!BLEManager.isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
        } else {
            saveData();
        }
    }

    public /* synthetic */ void lambda$showDataChangedDialog$5$BaseActivity(View view) {
        supportFinishAfterTransition();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void saveData() {
        if (!BLEManager.isConnected()) {
            showCmdResultToast(false);
            finish();
        } else {
            sendCmd();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showCmdResultToast(boolean z) {
        String languageText;
        if (z) {
            languageText = getLanguageText(R.string.logn_all_set_success_ios);
        } else {
            languageText = getLanguageText(R.string.logn_all_set_failed_ios);
        }
        showToast(languageText);
    }

    protected void exitApp() {
        VeryFitApp.getApp().exitApp();
    }

    public boolean isNotificationEnabled() {
        String string = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return string.contains(getPackageName());
    }

    private void showRebootBluetoothDialog() {
        if (isForeground()) {
            CommBottomConfirmDialog commBottomConfirmDialog = this.rebootBluetoothDialog;
            if (commBottomConfirmDialog != null) {
                commBottomConfirmDialog.dismissAllowingStateLoss();
                this.rebootBluetoothDialog = null;
            }
            this.rebootBluetoothDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.reboot_bluetooth_tip), getLanguageText(R.string.sync_ok), getLanguageText(R.string.public_tip_cancel), false).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.base.-$$Lambda$BaseActivity$6OU-7kto43KYM9GtpeTLNav6Z6s
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showRebootBluetoothDialog$6$BaseActivity(view);
                }
            });
            this.rebootBluetoothDialog.show(getSupportFragmentManager());
        }
    }

    public /* synthetic */ void lambda$showRebootBluetoothDialog$6$BaseActivity(View view) {
        this.rebootBluetoothDialog.dismissAllowingStateLoss();
        this.rebootBluetoothDialog = null;
    }

    private void showRemoteLanguageDialog(BaseMessage baseMessage) {
        RemoteLanguageMsg remoteLanguageMsg;
        if (isForeground() && BLEManager.isConnected()) {
            CommBottomConfirmDialog commBottomConfirmDialog = this.mOtaDialog;
            if (commBottomConfirmDialog == null || !commBottomConfirmDialog.isDialogShowing()) {
                RemoteLanguageDialogFragment remoteLanguageDialogFragment = this.mRemoteLanguageDialog;
                if ((remoteLanguageDialogFragment == null || !remoteLanguageDialogFragment.isDialogShowing()) && (remoteLanguageMsg = (RemoteLanguageMsg) baseMessage) != null) {
                    final RemoteLanguage.LanguageInfo data = remoteLanguageMsg.getData();
                    View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.ido.life.base.-$$Lambda$BaseActivity$0GLDKmPTOX15baLEWI8ZTHPsIdI
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            this.f$0.lambda$showRemoteLanguageDialog$7$BaseActivity(data, view);
                        }
                    };
                    this.mRemoteLanguageDialog = RemoteLanguageDialogFragment.getInstance(baseMessage.getType() == 801 ? R.string.device_language_different_tip : R.string.device_language_update_tip, LanguageCodeHelper.formatLanguageCode2Name(data.getCodeId())).setOnConfirmListener(onClickListener).setOnRetryListener(onClickListener).setOnCancelListener(new View.OnClickListener() { // from class: com.ido.life.base.-$$Lambda$BaseActivity$S1JRJ9_aYZEsAVr8r3N0txsoJrw
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            this.f$0.lambda$showRemoteLanguageDialog$8$BaseActivity(view);
                        }
                    });
                    this.mRemoteLanguageDialog.show(getSupportFragmentManager());
                }
            }
        }
    }

    public /* synthetic */ void lambda$showRemoteLanguageDialog$7$BaseActivity(RemoteLanguage.LanguageInfo languageInfo, View view) {
        RemoteLanguageHelper.downloadLanguageFile(languageInfo, this);
    }

    public /* synthetic */ void lambda$showRemoteLanguageDialog$8$BaseActivity(View view) {
        this.mRemoteLanguageDialog = null;
    }

    private void clickedISee(RemoteLanguage.LanguageInfo languageInfo) {
        SPHelper.saveLastRemindLanguageInfo(languageInfo);
    }

    @Override // com.ido.life.util.RemoteLanguageHelper.OnRemoteLanguageListener
    public void onRemoteLanguageProgress(final int i, final int i2) {
        runOnUiThread(new Runnable() { // from class: com.ido.life.base.-$$Lambda$BaseActivity$eYop6_pifStdOtTMFaf8wnr84S8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onRemoteLanguageProgress$9$BaseActivity(i, i2);
            }
        });
    }

    public /* synthetic */ void lambda$onRemoteLanguageProgress$9$BaseActivity(int i, int i2) {
        RemoteLanguageDialogFragment remoteLanguageDialogFragment = this.mRemoteLanguageDialog;
        if (remoteLanguageDialogFragment == null) {
            return;
        }
        remoteLanguageDialogFragment.setProgress(i, i2);
    }

    @Override // com.ido.life.util.RemoteLanguageHelper.OnRemoteLanguageListener
    public void onRemoteLanguageComplete(final int i, final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.ido.life.base.-$$Lambda$BaseActivity$ZiCHpARXu-itnGtGwEt7lPjM7oQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onRemoteLanguageComplete$10$BaseActivity(z, i);
            }
        });
    }

    public /* synthetic */ void lambda$onRemoteLanguageComplete$10$BaseActivity(boolean z, int i) {
        RemoteLanguageDialogFragment remoteLanguageDialogFragment = this.mRemoteLanguageDialog;
        if (remoteLanguageDialogFragment == null) {
            return;
        }
        if (z) {
            remoteLanguageDialogFragment.setSuccess(i);
        } else {
            remoteLanguageDialogFragment.setFailed(i);
        }
    }

    protected void fromForegroundToBack() {
        this.mInBackGround = true;
        EventBusHelper.post(Constants.EventConstants.EVENT_ENTER_BACK);
    }

    protected void fromBackToForeground() {
        EventBusHelper.post(Constants.EventConstants.EVENT_BACK_FROM_BACK);
        if (isNotificationEnabled()) {
            DialogHelper.dismissDialogFragment(DialogHelper.TYPE_NOTIFICATION_PERMISSION());
        }
    }

    protected boolean isForeground() {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) getSystemService("activity")).getRunningTasks(1);
        if (runningTasks != null && runningTasks.size() > 0) {
            if (getClass().getName().equals(runningTasks.get(0).topActivity.getClassName())) {
                return true;
            }
        }
        return false;
    }
}