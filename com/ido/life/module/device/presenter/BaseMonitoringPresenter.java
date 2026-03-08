package com.ido.life.module.device.presenter;

import android.os.Handler;
import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.DrinkWaterReminder;
import com.ido.ble.protocol.model.HeartRateMeasureMode;
import com.ido.ble.protocol.model.HeartRateMeasureModeV3;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.PressureParam;
import com.ido.ble.protocol.model.SPO2Param;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.WalkReminder;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.constants.Constants;
import com.ido.life.module.device.view.IBaseMonitoringView;
import com.ido.life.util.RunTimeUtil;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class BaseMonitoringPresenter<T extends IBaseMonitoringView> extends BaseCmdPresenter<T> {
    private boolean isGetModeFromDevice = true;
    private Runnable mGetHeartRateModeTimeout;
    private Handler mHandler;

    protected void getHeartRateModeFailed() {
    }

    protected void onGetHeartRateMode(int i) {
    }

    protected void onGetHeartRateModeV3(int i, int i2) {
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    public SupportFunctionInfo getSupportFunctionInfo() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo == null ? new SupportFunctionInfo() : supportFunctionInfo;
    }

    public boolean isSupportSmartHeartRate() {
        return getSupportFunctionInfo().V3_support_set_smart_heart_rate;
    }

    public void getHeartRateMode(boolean z) {
        HeartRateMeasureModeV3 heartRateModeV3;
        if (isAttachView()) {
            SupportFunctionInfo supportFunctionInfo = getSupportFunctionInfo();
            if (supportFunctionInfo.ex_main4_v3_hr_data) {
                if (this.mHandler == null) {
                    this.mHandler = new Handler();
                }
                if (this.mGetHeartRateModeTimeout == null) {
                    this.mGetHeartRateModeTimeout = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$vn8LJLsGVjGn5kTUthkuh2tEPLk
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.getHeartRateModeFailed();
                        }
                    };
                }
                if (z && (heartRateModeV3 = LocalDataManager.getHeartRateModeV3()) != null) {
                    onGetHeartRateModeV3(heartRateModeV3.mode, heartRateModeV3.measurementInterval);
                }
                this.mHandler.removeCallbacks(this.mGetHeartRateModeTimeout);
                this.mHandler.postDelayed(this.mGetHeartRateModeTimeout, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
                this.isGetModeFromDevice = true;
                HeartRateMeasureModeV3 heartRateMeasureModeV3 = new HeartRateMeasureModeV3();
                heartRateMeasureModeV3.updateTime = 0;
                BLEManager.setHeartRateMeasureModeV3(heartRateMeasureModeV3);
                return;
            }
            if (supportFunctionInfo.heartRateMonitor) {
                this.isGetModeFromDevice = false;
                HeartRateMeasureMode heartRateMode = LocalDataManager.getHeartRateMode();
                if (heartRateMode == null) {
                    heartRateMode = new HeartRateMeasureMode();
                    heartRateMode.mode = 170;
                }
                onGetHeartRateMode(heartRateMode.mode);
            }
        }
    }

    public boolean getPressureSwitch() {
        PressureParam pressureParam = LocalDataManager.getPressureParam();
        if (pressureParam == null) {
            pressureParam = new PressureParam();
            pressureParam.onOff = 85;
        }
        return pressureParam.onOff == 170;
    }

    public boolean getBloodOxyDetectionSwitch() {
        SPO2Param spO2Param = LocalDataManager.getSpO2Param();
        if (spO2Param == null) {
            spO2Param = new SPO2Param();
            spO2Param.onOff = 85;
        }
        return spO2Param.onOff == 170;
    }

    public boolean getWaterClockSwitch() {
        DrinkWaterReminder drinkWaterReminder = LocalDataManager.getDrinkWaterReminder();
        if (drinkWaterReminder == null) {
            drinkWaterReminder = new DrinkWaterReminder();
        }
        return drinkWaterReminder.isOnOff();
    }

    public boolean getWalkReminderSwitch() {
        WalkReminder walkReminder = LocalDataManager.getWalkReminder();
        if (walkReminder == null) {
            walkReminder = new WalkReminder();
            walkReminder.setOnOff(0);
        }
        return walkReminder.getOnOff() == 1;
    }

    public String formatWeekRepeat(boolean[] zArr) {
        int[] iArr = Constants.WEEK_START_MONDAY;
        int weekStart = RunTimeUtil.getInstance().getWeekStart();
        int i = 0;
        String languageText = "";
        int i2 = 0;
        while (true) {
            if (i >= zArr.length) {
                break;
            }
            if (zArr[i]) {
                i2++;
                if (TextUtils.isEmpty(languageText)) {
                    languageText = languageText + LanguageUtil.getLanguageText(iArr[i]);
                } else if (weekStart == 2) {
                    languageText = languageText + "  " + LanguageUtil.getLanguageText(iArr[i]);
                } else if (weekStart == 7) {
                    if (i == zArr.length - 2) {
                        int i3 = i + 1;
                        if (zArr[i3]) {
                            languageText = LanguageUtil.getLanguageText(iArr[i3]) + "  " + languageText;
                            i2++;
                        }
                        languageText = LanguageUtil.getLanguageText(iArr[i]) + "  " + languageText;
                    } else if (i == zArr.length - 1) {
                        languageText = LanguageUtil.getLanguageText(iArr[i]) + "  " + languageText;
                    } else {
                        languageText = languageText + "  " + LanguageUtil.getLanguageText(iArr[i]);
                    }
                } else if (i == zArr.length - 1) {
                    languageText = LanguageUtil.getLanguageText(iArr[i]) + "  " + languageText;
                } else {
                    languageText = languageText + "  " + LanguageUtil.getLanguageText(iArr[i]);
                }
            }
            i++;
        }
        if (TextUtils.isEmpty(languageText)) {
            languageText = LanguageUtil.getLanguageText(R.string.device_without_repetition);
        }
        return i2 == 7 ? LanguageUtil.getLanguageText(R.string.device_everyday) : languageText;
    }

    public boolean getMenstrualCycleSwitch() {
        Menstrual menstrual = LocalDataManager.getMenstrual();
        if (menstrual == null) {
            menstrual = new Menstrual();
            menstrual.on_off = 85;
        }
        return menstrual.on_off == 170;
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        Runnable runnable;
        super.onSetCmdSuccess(settingType, obj);
        if (isAttachView() && settingType == SettingCallBack.SettingType.HEART_RATE_MEASURE_MODE_V3) {
            Handler handler = this.mHandler;
            if (handler != null && (runnable = this.mGetHeartRateModeTimeout) != null) {
                handler.removeCallbacks(runnable);
            }
            HeartRateMeasureModeV3 heartRateMeasureModeV3 = (HeartRateMeasureModeV3) obj;
            if (this.isGetModeFromDevice) {
                onGetHeartRateModeV3(heartRateMeasureModeV3.mode, heartRateMeasureModeV3.measurementInterval);
            }
        }
    }

    public String formInterval(int i, String str) {
        return String.format(Locale.getDefault(), "%1$d%2$s", Integer.valueOf(i), str);
    }

    public String getAlarmNameByType(int i) {
        int i2;
        switch (i) {
            case 0:
                i2 = R.string.alarm_get_up;
                break;
            case 1:
                i2 = R.string.alarm_sleep;
                break;
            case 2:
                i2 = R.string.alarm_exercise;
                break;
            case 3:
                i2 = R.string.alarm_medication;
                break;
            case 4:
                i2 = R.string.alarm_dating;
                break;
            case 5:
                i2 = R.string.alarm_party;
                break;
            case 6:
                i2 = R.string.alarm_meeting;
                break;
            default:
                i2 = 0;
                break;
        }
        return i2 == 0 ? "" : LanguageUtil.getLanguageText(i2);
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
    }
}