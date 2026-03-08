package com.ido.life.module.device.activity;

import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.FirmwareAndBt3Version;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.ble.BleHelper;
import com.ido.life.constants.Constants;
import com.ido.life.customview.viewgroup.OptionView;
import com.ido.life.data.api.entity.OtaEntity;
import com.ido.life.data.api.entity.RemoteLanguage;
import com.ido.life.dialog.DeviceUpgradeDialogFragment;
import com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter;
import com.ido.life.module.device.view.IDeviceUpgradeNewView;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.module.main.MainActivity;
import com.ido.life.util.DeviceUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import com.ido.smartrefresh.layout.util.SmartUtil;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUpgradeNewActivity extends BaseActivity<DeviceUpgradeNewPresenter> implements IDeviceUpgradeNewView, DeviceUpgradeDialogFragment.ClickListener {
    public static final String DEVICE_INFO = "device_info";
    public static boolean isUpgradeding = false;

    @BindView(R.id.upgrade_content_rl)
    RelativeLayout content_rl;

    @BindView(R.id.lay_fireware)
    OptionView layFireware;

    @BindView(R.id.lay_font)
    OptionView layFone;

    @BindView(R.id.lay_system)
    OptionView laySystemCom;

    @BindView(R.id.upgrade_line)
    View lineView;

    @BindView(R.id.upgrade_system_line)
    View lineViewSystem;
    private BLEDevice mDevice;

    @BindView(R.id.iv_status_right)
    AppCompatImageView mIvStatus;

    @BindView(R.id.iv_top)
    AppCompatImageView mIvTop;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.tv_current_version)
    RegularTextView mTvCurrentVersion;

    @BindView(R.id.tv_retry)
    MediumTextView mTvRetry;

    @BindView(R.id.tv_tip_top)
    MediumTextView mTvTipTop;

    @BindView(R.id.tv_upgrade)
    MediumTextView mTvUpgrade;
    private DeviceUpgradeDialogFragment mUpgradeDialogFragment;

    @BindView(R.id.tip_ll)
    LinearLayout tip_ll;

    @BindView(R.id.upgrade_result_ll)
    LinearLayout upgradeResultll;

    @BindView(R.id.upgrade_stauts_ll)
    LinearLayout upgradeStatusll;
    private final int UPDATE_DATA_INTERVAL = 1000;
    private int time = 0;
    private int currentUpgrade = 0;
    WaitingTimerTask waitingTimerTask = new WaitingTimerTask();
    private Handler mHandler = new Handler();
    private int mode = 1;
    private boolean isFirewareHasNewVersion = false;
    private boolean isFontHasNewVersion = false;
    private boolean isSystemConstituentNewVersion = false;
    private int count = 0;

    private void initClickEvent() {
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_device_upgrade_new;
    }

    @Override // com.ido.life.module.device.view.IDeviceUpgradeNewView
    public void onDetectionLanguageInfo(boolean z, RemoteLanguage.LanguageInfo languageInfo) {
    }

    @Override // com.ido.life.module.device.view.IDeviceUpgradeNewView
    public void onGetCurrentLanguageVersion(String str) {
    }

    static /* synthetic */ int access$108(DeviceUpgradeNewActivity deviceUpgradeNewActivity) {
        int i = deviceUpgradeNewActivity.time;
        deviceUpgradeNewActivity.time = i + 1;
        return i;
    }

    private class WaitingTimerTask extends TimerTask {
        private WaitingTimerTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            DeviceUpgradeNewActivity.access$108(DeviceUpgradeNewActivity.this);
            DeviceUpgradeNewActivity.this.mHandler.postDelayed(this, 1000L);
            if (DeviceUpgradeNewActivity.this.time == 2) {
                if (DeviceUpgradeNewActivity.this.mUpgradeDialogFragment != null) {
                    DeviceUpgradeNewActivity.this.mUpgradeDialogFragment.waitingView();
                }
            } else if (DeviceUpgradeNewActivity.this.time == 300) {
                if (DeviceUpgradeNewActivity.this.mUpgradeDialogFragment != null) {
                    DeviceUpgradeNewActivity.this.mUpgradeDialogFragment.setFontTimeOutView(DeviceUpgradeNewActivity.this.currentUpgrade);
                }
                if (DeviceUpgradeNewActivity.this.mPresenter != null) {
                    ((DeviceUpgradeNewPresenter) DeviceUpgradeNewActivity.this.mPresenter).updateMotaType(DeviceUpgradeNewActivity.this.currentUpgrade);
                }
                DeviceUpgradeNewActivity.this.mHandler.removeCallbacksAndMessages(null);
                return;
            }
            ((DeviceUpgradeNewPresenter) DeviceUpgradeNewActivity.this.mPresenter).saveOtaLog("time:" + DeviceUpgradeNewActivity.this.time);
            if (DeviceUpgradeNewActivity.this.time <= 20 || !((DeviceUpgradeNewPresenter) DeviceUpgradeNewActivity.this.mPresenter).isConnected()) {
                return;
            }
            ((DeviceUpgradeNewPresenter) DeviceUpgradeNewActivity.this.mPresenter).saveOtaLog("----固件升级成功后，判断设备有没有连上，连上了就进入字库升级,停止计时-------");
            ((DeviceUpgradeNewPresenter) DeviceUpgradeNewActivity.this.mPresenter).startUpgrade(DeviceUpgradeNewActivity.this.currentUpgrade, DeviceUpgradeNewActivity.this.mode);
            DeviceUpgradeNewActivity.this.mHandler.removeCallbacksAndMessages(null);
            if (DeviceUpgradeNewActivity.this.mUpgradeDialogFragment != null) {
                ((DeviceUpgradeNewPresenter) DeviceUpgradeNewActivity.this.mPresenter).saveOtaLog("----startUpgradeFont----");
                DeviceUpgradeNewActivity.this.mUpgradeDialogFragment.startUpgradeFont(DeviceUpgradeNewActivity.this.currentUpgrade);
            }
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mDevice = (BLEDevice) getIntent().getSerializableExtra("device_info");
        detectDeviceInfo();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_upgrade));
        initClickEvent();
        initDeviceInfo();
    }

    public void startWaitingTimer() {
        Handler handler = this.mHandler;
        if (handler != null) {
            this.time = 0;
            handler.removeCallbacks(this.waitingTimerTask);
            this.mHandler.post(this.waitingTimerTask);
        }
    }

    private void detectDeviceInfo() {
        this.mProgressBar.setVisibility(0);
        this.mIvStatus.setVisibility(8);
        this.mTvRetry.setVisibility(4);
        this.mTvTipTop.setText(getLanguageText(R.string.device_version_detection));
        ((DeviceUpgradeNewPresenter) this.mPresenter).detectFirmwareInfo(this.mDevice);
    }

    private void initDeviceInfo() {
        ((DeviceUpgradeNewPresenter) this.mPresenter).getFirmwareVersion();
    }

    private boolean needOpenBle() {
        if (BleHelper.isBluetoothOpen()) {
            return false;
        }
        BleHelper.openBLE(this);
        return true;
    }

    private void showUpgradeDialog(int i) {
        DeviceUpgradeDialogFragment deviceUpgradeDialogFragment = this.mUpgradeDialogFragment;
        if (deviceUpgradeDialogFragment == null || !deviceUpgradeDialogFragment.isDialogShowing()) {
            this.mUpgradeDialogFragment = DeviceUpgradeDialogFragment.getInstance(i, this.mode, this.count, this);
            this.mUpgradeDialogFragment.show(getSupportFragmentManager());
        }
    }

    @Override // com.ido.life.dialog.DeviceUpgradeDialogFragment.ClickListener
    public void onRetryClick() {
        if (needOpenBle()) {
            return;
        }
        if (!((DeviceUpgradeNewPresenter) this.mPresenter).isConnected() && (((DeviceUpgradeNewPresenter) this.mPresenter).mOtaType != 1 || ((DeviceUpgradeNewPresenter) this.mPresenter).isApollo())) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
        } else if (!NetworkUtil.isConnected(this)) {
            showToast(getLanguageText(R.string.logn_all_pleaseCheckNetWork_ios));
        } else {
            ((DeviceUpgradeNewPresenter) this.mPresenter).doUpgrade();
        }
    }

    @Override // com.ido.life.dialog.DeviceUpgradeDialogFragment.ClickListener
    public void onCloseClick() {
        if (((DeviceUpgradeNewPresenter) this.mPresenter).isUpgradeSuccess) {
            MainActivity.startActivity(this, 301);
            ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("isUpgradeSuccess: 回到mainactivity");
            finishAfterTransition();
        }
        isUpgradeding = false;
        EventBusHelper.post(Constants.EventConstants.EVENT_FAMILY_DEVICE_END_UPGRADE);
    }

    @OnClick({R.id.tv_retry, R.id.tv_upgrade})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_retry) {
            if (!NetworkUtil.isConnected(this)) {
                showToast(getLanguageText(R.string.logn_all_pleaseCheckNetWork_ios));
                return;
            } else {
                detectDeviceInfo();
                return;
            }
        }
        if (id == R.id.tv_upgrade && !needOpenBle()) {
            if (HomeFragmentPresenter.mIsSyncing) {
                showToast(getLanguageText(R.string.syncing_pls_try_again_later));
                return;
            }
            if (HomeFragmentPresenter.mIsTelephone) {
                showToast(getLanguageText(R.string.sport_start_incalling));
                return;
            }
            this.count = 0;
            if (this.isFirewareHasNewVersion) {
                this.count++;
                ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("固件需要升级count:" + this.count);
            }
            if (this.isFontHasNewVersion) {
                this.count++;
                ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("字库需要升级count:" + this.count);
            }
            if (this.isSystemConstituentNewVersion) {
                this.count++;
                ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("系统组件需要升级count:" + this.count);
            }
            int i = this.count;
            if (i == 0) {
                return;
            }
            if (i == 1) {
                ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("单个升级模式");
                this.mode = 1;
            } else if (i > 1) {
                ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("多个升级模式");
                this.mode = 2;
            }
            if (this.isFirewareHasNewVersion) {
                startUpgradeFireware();
            } else if (this.isFontHasNewVersion) {
                startUpgradeFlashInfo();
            } else if (this.isSystemConstituentNewVersion) {
                startUpgradeSystemConstituent();
            }
        }
    }

    private void startUpgradeFlashInfo() {
        if (needOpenBle()) {
            return;
        }
        if (!((DeviceUpgradeNewPresenter) this.mPresenter).isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
            return;
        }
        this.currentUpgrade = 2;
        ((DeviceUpgradeNewPresenter) this.mPresenter).startUpgrade(2, this.mode);
        isUpgradeding = true;
        EventBusHelper.post(Constants.EventConstants.EVENT_FAMILY_DEVICE_START_UPGRADE);
    }

    private void startUpgradeFireware() {
        if (((DeviceUpgradeNewPresenter) this.mPresenter).isApollo() || !((DeviceUpgradeNewPresenter) this.mPresenter).isInDfuMode) {
            if (((DeviceUpgradeNewPresenter) this.mPresenter).isConnected()) {
                this.currentUpgrade = 1;
                ((DeviceUpgradeNewPresenter) this.mPresenter).startUpgrade(1, this.mode);
                isUpgradeding = true;
                EventBusHelper.post(Constants.EventConstants.EVENT_FAMILY_DEVICE_START_UPGRADE);
                return;
            }
            showToast(getLanguageText(R.string.device_pls_connect_device));
            ((DeviceUpgradeNewPresenter) this.mPresenter).autoConnectDevice();
            return;
        }
        this.currentUpgrade = 1;
        ((DeviceUpgradeNewPresenter) this.mPresenter).startUpgrade(1, this.mode);
        isUpgradeding = true;
        EventBusHelper.post(Constants.EventConstants.EVENT_FAMILY_DEVICE_START_UPGRADE);
    }

    private void startUpgradeSystemConstituent() {
        if (needOpenBle()) {
            return;
        }
        if (!((DeviceUpgradeNewPresenter) this.mPresenter).isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
            return;
        }
        this.currentUpgrade = 4;
        ((DeviceUpgradeNewPresenter) this.mPresenter).startUpgrade(4, this.mode);
        isUpgradeding = true;
    }

    @Override // com.ido.life.module.device.view.IDeviceUpgradeNewView
    public void onGetCurrentFirmwareVersion(String str) {
        this.mTvCurrentVersion.setText(getString(R.string.device_version) + " V" + str);
    }

    @Override // com.ido.life.module.device.view.IDeviceUpgradeNewView
    public void onGetCurrentFlashVersion(String str) {
        if (((DeviceUpgradeNewPresenter) this.mPresenter).mFirmwareInfo == null) {
            return;
        }
        showFlashUpgradeTip(((DeviceUpgradeNewPresenter) this.mPresenter).mFirmwareInfo);
        ((DeviceUpgradeNewPresenter) this.mPresenter).getSystemConstituentInfo();
    }

    @Override // com.ido.life.module.device.view.IDeviceUpgradeNewView
    public void onGetCurrentSystemInfoVersion(FirmwareAndBt3Version firmwareAndBt3Version) {
        if (((DeviceUpgradeNewPresenter) this.mPresenter).mFirmwareInfo == null) {
            return;
        }
        showSystempgradeTip(((DeviceUpgradeNewPresenter) this.mPresenter).mFirmwareInfo);
        detectFinish(true);
    }

    private String formatVersionText(String str) {
        if (str.endsWith("v--")) {
            return str.replace("v--", "--");
        }
        return str.endsWith("V--") ? str.replace("V--", "--") : str;
    }

    @Override // com.ido.life.module.device.view.IDeviceUpgradeNewView
    public void onDetectionFailed() {
        detectFinish(false);
    }

    @Override // com.ido.life.module.device.view.IDeviceUpgradeNewView
    public void onDetectionFirmwareInfo(boolean z, OtaEntity.OtaInfo otaInfo, boolean z2) {
        OtaEntity.OtaInfo.FlashInfo flashInfo;
        this.isFirewareHasNewVersion = z;
        if (z) {
            this.layFireware.setEndText(otaInfo.getVersion());
        }
        if (((DeviceUpgradeNewPresenter) this.mPresenter).isInDfuMode) {
            if (otaInfo != null && BLEManager.isBind() && (flashInfo = otaInfo.getFlashInfo()) != null) {
                try {
                    this.isFontHasNewVersion = true;
                    this.layFone.setEndText("v" + flashInfo.getFontVersion());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            detectFinish(true);
            return;
        }
        ((DeviceUpgradeNewPresenter) this.mPresenter).getFlashInfo();
    }

    private void showFlashUpgradeTip(OtaEntity.OtaInfo otaInfo) {
        OtaEntity.OtaInfo.FlashInfo flashInfo = otaInfo.getFlashInfo();
        if (((DeviceUpgradeNewPresenter) this.mPresenter).mCurrentFlashInfo == null || flashInfo == null) {
            return;
        }
        try {
            if (Integer.parseInt(flashInfo.getFontVersion()) != ((DeviceUpgradeNewPresenter) this.mPresenter).mCurrentFlashInfo.version) {
                this.isFontHasNewVersion = true;
                this.layFone.setEndText("v" + flashInfo.getFontVersion());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void showSystempgradeTip(OtaEntity.OtaInfo otaInfo) {
        OtaEntity.OtaInfo.SystemConstituentInfo systemConstituentInfo;
        if (((DeviceUpgradeNewPresenter) this.mPresenter).mFirmwareInfo == null || (systemConstituentInfo = otaInfo.getSystemConstituentInfo()) == null || ((DeviceUpgradeNewPresenter) this.mPresenter).mCurrentFirmwareAndBt3Version == null) {
            return;
        }
        try {
            ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("onDetectionSystemInfo:mPresenter.mFirmwareInfo != null+moduleVersion:" + systemConstituentInfo.getFirmwareVersion());
            if (systemConstituentInfo.getFirmwareVersion().equals(DeviceUtil.getDeviceBTCurrentVersion(((DeviceUpgradeNewPresenter) this.mPresenter).mCurrentFirmwareAndBt3Version))) {
                return;
            }
            this.isSystemConstituentNewVersion = true;
            this.laySystemCom.setEndText(systemConstituentInfo.getFirmwareVersion());
            ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("onDetectionSystemInfo:NewVersion  true");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.ido.life.module.device.view.IDeviceUpgradeNewView
    public void onDetectionFlashInfo(boolean z, OtaEntity.OtaInfo otaInfo) {
        if (z && ((DeviceUpgradeNewPresenter) this.mPresenter).mCurrentFlashInfo != null && otaInfo != null) {
            try {
                if (Integer.parseInt(otaInfo.getVersion()) > ((DeviceUpgradeNewPresenter) this.mPresenter).mCurrentFlashInfo.version) {
                    this.isFontHasNewVersion = true;
                    this.layFone.setEndText("v" + otaInfo.getVersion());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        ((DeviceUpgradeNewPresenter) this.mPresenter).getSystemConstituentInfo();
    }

    @Override // com.ido.life.module.device.view.IDeviceUpgradeNewView
    public void onDetectionSystemInfo(boolean z, OtaEntity.OtaInfo otaInfo) {
        if (z) {
            this.isSystemConstituentNewVersion = true;
            this.laySystemCom.setEndText(getLanguageText(R.string.device_version) + otaInfo.getFirmwareVersion());
            ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("onDetectionSystemInfo:true");
        }
        detectFinish(true);
    }

    @Override // com.ido.life.module.device.view.IDeviceUpgradeNewView
    public void onUpgradePrepare(int i) {
        showUpgradeDialog(i);
    }

    @Override // com.ido.life.module.device.view.IDeviceUpgradeNewView
    public void onUpgradeStart(int i) {
        DeviceUpgradeDialogFragment deviceUpgradeDialogFragment = this.mUpgradeDialogFragment;
        if (deviceUpgradeDialogFragment != null) {
            deviceUpgradeDialogFragment.startUpgrade(i);
        }
    }

    @Override // com.ido.life.module.device.view.IDeviceUpgradeNewView
    public void onUpgradeProgress(int i, int i2) {
        DeviceUpgradeDialogFragment deviceUpgradeDialogFragment = this.mUpgradeDialogFragment;
        if (deviceUpgradeDialogFragment != null) {
            deviceUpgradeDialogFragment.setProgress(i, i2);
        }
    }

    @Override // com.ido.life.module.device.view.IDeviceUpgradeNewView
    public void onUpgradeSuccess(int i) {
        DeviceUpgradeDialogFragment deviceUpgradeDialogFragment = this.mUpgradeDialogFragment;
        if (deviceUpgradeDialogFragment != null) {
            deviceUpgradeDialogFragment.setUpgradeResult(i, true);
        }
    }

    @Override // com.ido.life.module.device.view.IDeviceUpgradeNewView
    public void onUpgradeFailed(int i) {
        if (i == -1) {
            DeviceUpgradeDialogFragment deviceUpgradeDialogFragment = this.mUpgradeDialogFragment;
            if (deviceUpgradeDialogFragment != null) {
                deviceUpgradeDialogFragment.setUpgradeResult(100, false);
                return;
            }
            return;
        }
        if (i == 100 || i == 200) {
            DeviceUpgradeDialogFragment deviceUpgradeDialogFragment2 = this.mUpgradeDialogFragment;
            if (deviceUpgradeDialogFragment2 != null) {
                deviceUpgradeDialogFragment2.setUpgradeResult(i, false);
                return;
            }
            return;
        }
        if (i == 300 || i == 400) {
            DeviceUpgradeDialogFragment deviceUpgradeDialogFragment3 = this.mUpgradeDialogFragment;
            if (deviceUpgradeDialogFragment3 != null && deviceUpgradeDialogFragment3.isDialogShowing()) {
                this.mUpgradeDialogFragment.dismissAllowingStateLoss();
            }
            CommBottomConfirmDialog.newInstance(getLanguageText(R.string.sport_low_power_title), getLanguageText(R.string.ota_low_power), getLanguageText(R.string.i_see), "", false).show(getSupportFragmentManager());
        }
    }

    @Override // com.ido.life.module.device.view.IDeviceUpgradeNewView
    public void onUpgradeFont() {
        int i = this.currentUpgrade;
        if (i == 1) {
            ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("固件升级完成");
            if (this.isFontHasNewVersion) {
                ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("开始升级字库");
                this.currentUpgrade = 2;
                startWaitingTimer();
                return;
            } else {
                if (this.isSystemConstituentNewVersion) {
                    ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("开始升系统组件");
                    this.currentUpgrade = 4;
                    startWaitingTimer();
                    return;
                }
                return;
            }
        }
        if (i != 2) {
            if (i == 4) {
                ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("升级完成");
                return;
            }
            return;
        }
        ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("字库升级完成");
        if (this.isSystemConstituentNewVersion) {
            ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("开始升系统组件");
            this.currentUpgrade = 4;
            startWaitingTimer();
            return;
        }
        ((DeviceUpgradeNewPresenter) this.mPresenter).saveOtaLog("升级完成");
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        int type;
        if (baseMessage == null || (type = baseMessage.getType()) == 801 || type == 802 || type == 825 || type == 830) {
            return;
        }
        super.handleMessage(baseMessage);
    }

    private void detectFinish(boolean z) {
        this.mProgressBar.setVisibility(8);
        this.mTvTipTop.setText(getLanguageText(z ? R.string.device_tip_version_newest : R.string.device_version_detection_failed));
        if (!z) {
            this.upgradeStatusll.setVisibility(0);
            this.mTvRetry.setVisibility(0);
            this.mIvStatus.setVisibility(0);
            this.mIvStatus.setImageResource(R.mipmap.icon_upgrade_failed);
            return;
        }
        setCompleteView();
    }

    private void setCompleteView() {
        if (this.isFirewareHasNewVersion || this.isFontHasNewVersion || this.isSystemConstituentNewVersion) {
            this.upgradeStatusll.setVisibility(8);
            this.mTvRetry.setVisibility(8);
            this.mTvUpgrade.setVisibility(0);
            this.layFireware.setVisibility(this.isFirewareHasNewVersion ? 0 : 8);
            this.layFone.setVisibility(this.isFontHasNewVersion ? 0 : 8);
            this.laySystemCom.setVisibility(this.isSystemConstituentNewVersion ? 0 : 8);
            if (!this.isFirewareHasNewVersion || !this.isFontHasNewVersion) {
                this.lineView.setVisibility(8);
            }
            if (!this.isSystemConstituentNewVersion) {
                this.lineViewSystem.setVisibility(8);
            }
            startAnimal();
            return;
        }
        this.mIvStatus.setVisibility(0);
        this.mIvStatus.setImageResource(R.mipmap.icon_upgrade_success);
        this.mTvRetry.setVisibility(8);
        this.tip_ll.setVisibility(8);
        HomeFragmentPresenter.hasNewDeviceVersion = false;
    }

    private void startAnimal() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation.setDuration(600L);
        this.upgradeResultll.startAnimation(translateAnimation);
        this.upgradeResultll.setVisibility(0);
        this.mIvTop.animate().scaleX(0.8f).scaleY(0.8f).translationYBy(-SmartUtil.dp2px(36.0f)).setDuration(600L).setInterpolator(new AccelerateInterpolator()).withLayer().start();
        this.content_rl.animate().translationX(0.0f).translationY(-SmartUtil.dp2px(36.0f)).setDuration(600L).setInterpolator(new AccelerateInterpolator()).withLayer().start();
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.waitingTimerTask);
            this.mHandler.removeCallbacksAndMessages(null);
        }
        isUpgradeding = false;
        EventBusHelper.post(Constants.EventConstants.EVENT_FAMILY_DEVICE_END_UPGRADE);
    }
}