package com.ido.life.module.sport.setting.rate;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.HeartRateInterval;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.sport.setting.SportSetting;
import com.ido.life.module.sport.setting.SportSettingPreference;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes2.dex */
public class SportRatePresenter extends BasePresenter<ISportRateView> {
    private static final String TAG = "SportRatePresenter";
    private boolean mIsOpen;
    private int mMaxHeart;
    private final SettingCallBack.ICallBack mSettingCallback = new SettingCallBack.ICallBack() { // from class: com.ido.life.module.sport.setting.rate.SportRatePresenter.1
        @Override // com.ido.ble.callback.SettingCallBack.ICallBack
        public void onSuccess(SettingCallBack.SettingType settingType, Object obj) {
            if (settingType == SettingCallBack.SettingType.HEART_RATE_INTERVAL) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBugLogPath(), "心率区间设置成功==");
            }
            if (SportRatePresenter.this.getView() == null) {
                return;
            }
            ((ISportRateView) SportRatePresenter.this.getView()).setRateLayoutBg(SportRatePresenter.this.mIsOpen);
            ((ISportRateView) SportRatePresenter.this.getView()).setRateLimitEnable(SportRatePresenter.this.mIsOpen);
            CommonLogUtil.d(SportRatePresenter.TAG, "onSuccess: " + settingType + AppInfo.DELIM + SportRatePresenter.this.mIsOpen);
            SportRatePresenter sportRatePresenter = SportRatePresenter.this;
            sportRatePresenter.setRateDataToLocal(sportRatePresenter.mIsOpen, -1);
        }

        @Override // com.ido.ble.callback.SettingCallBack.ICallBack
        public void onFailed(SettingCallBack.SettingType settingType) {
            CommonLogUtil.d(SportRatePresenter.TAG, "onFailed: " + settingType);
        }
    };

    public void chooseHeartMax(int i) {
    }

    public void openOrCloseRateLimit(boolean z) {
        this.mIsOpen = z;
        setHeartRateRange(getHeartRateInterval(z), z);
    }

    public static HeartRateInterval getHeartRateInterval(boolean z) {
        int ageByBirthday;
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        int rateWarning = SportSettingPreference.getSportSetting().getRateWarning();
        HeartRateInterval heartRateInterval = LocalDataManager.getHeartRateInterval();
        if (heartRateInterval == null) {
            heartRateInterval = new HeartRateInterval();
        }
        if (userInfoQueryUserInfo != null) {
            ageByBirthday = DateUtil.getAgeByBirthday(userInfoQueryUserInfo.getBirthday(), DateUtil.DATE_FORMAT_YMD);
            if (rateWarning == 0) {
                rateWarning = 220 - ageByBirthday;
            }
        } else {
            ageByBirthday = 0;
        }
        int i = 220 - ageByBirthday;
        if (z) {
            heartRateInterval.setMaxHrRemind(1);
        } else {
            heartRateInterval.setMaxHrRemind(0);
        }
        setHeartRateValue(heartRateInterval, i, rateWarning);
        return heartRateInterval;
    }

    public void setHeartRateRange(HeartRateInterval heartRateInterval, boolean z) {
        this.mIsOpen = z;
        BLEManager.unregisterSettingCallBack(this.mSettingCallback);
        BLEManager.registerSettingCallBack(this.mSettingCallback);
        BLEManager.setHeartRateInterval(heartRateInterval);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBugLogPath(), "设置心率区间==" + heartRateInterval);
    }

    public static void setHeartRateValue(HeartRateInterval heartRateInterval, int i, int i2) {
        heartRateInterval.setUserMaxHR(i2);
        double d2 = i;
        heartRateInterval.setWarmUpThreshold((int) (0.5d * d2));
        heartRateInterval.setBurnFatThreshold((int) (0.6d * d2));
        heartRateInterval.setAerobicThreshold((int) (0.7d * d2));
        heartRateInterval.setAnaerobicThreshold((int) (0.8d * d2));
        heartRateInterval.setLimintThreshold((int) (d2 * 0.9d));
    }

    public void getMaxHeart() {
        if (getView() == null) {
            return;
        }
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        int ageByBirthday = 0;
        if (userInfoQueryUserInfo != null) {
            String birthday = userInfoQueryUserInfo.getBirthday();
            if (userInfoQueryUserInfo.getBirthday().contains("/")) {
                birthday = userInfoQueryUserInfo.getBirthday().replaceAll("/", "-");
            }
            ageByBirthday = DateUtil.getAgeByBirthday(birthday, DateUtil.DATE_FORMAT_YMD);
        }
        int i = 220 - ageByBirthday;
        getView().setRateMax(String.valueOf(i));
        getView().setRateMax(i);
        getView().setRateLimit(Math.round(i * 0.9f) + "-" + i + LanguageUtil.getLanguageText(R.string.public_heartbeat_unit));
        ISportRateView view = getView();
        StringBuilder sb = new StringBuilder();
        double d2 = (double) i;
        double d3 = 0.8d * d2;
        sb.append(Math.round(d3));
        sb.append("-");
        sb.append(Math.round((0.9d * d2) - 1.0d));
        sb.append(LanguageUtil.getLanguageText(R.string.public_heartbeat_unit));
        view.setRateAnaerobicEndurance(sb.toString());
        ISportRateView view2 = getView();
        StringBuilder sb2 = new StringBuilder();
        double d4 = 0.7d * d2;
        sb2.append(Math.round(d4));
        sb2.append("-");
        sb2.append(Math.round(d3 - 1.0d));
        sb2.append(LanguageUtil.getLanguageText(R.string.public_heartbeat_unit));
        view2.setRateAerobicEndurance(sb2.toString());
        ISportRateView view3 = getView();
        StringBuilder sb3 = new StringBuilder();
        double d5 = 0.6d * d2;
        sb3.append(Math.round(d5));
        sb3.append("-");
        sb3.append(Math.round(d4) - 1);
        sb3.append(LanguageUtil.getLanguageText(R.string.public_heartbeat_unit));
        view3.setRateBurningGrease(sb3.toString());
        getView().setRateWarmUp(Math.round(d2 * 0.5d) + "-" + Math.round(d5 - 1.0d) + LanguageUtil.getLanguageText(R.string.public_heartbeat_unit));
        this.mMaxHeart = i;
    }

    public void getHeartLimit() {
        if (getView() == null) {
            return;
        }
        SportSetting sportSetting = SportSettingPreference.getSportSetting();
        this.mIsOpen = sportSetting.isRateWarning();
        getView().setRateWarnIsOpen(sportSetting.isRateWarning());
        if (sportSetting.getRateWarning() == 0) {
            getView().setRateUpperLimit(ResourceUtil.getResources().getString(R.string.sport_setting_rate_explain_rate_up_value, 220));
            getView().setHeartLimit(220);
        } else {
            getView().setRateUpperLimit(ResourceUtil.getResources().getString(R.string.sport_setting_rate_explain_rate_up_value, Integer.valueOf(sportSetting.getRateWarning())));
            getView().setHeartLimit(sportSetting.getRateWarning());
        }
    }

    public void chooseHeartLimit(int i) {
        if (getView() == null) {
            return;
        }
        getView().setRateUpperLimit(ResourceUtil.getResources().getString(R.string.sport_setting_rate_explain_rate_up_value, Integer.valueOf(i)));
        getView().setHeartLimit(i);
        setRateDataToLocal(true, i);
        setHeartRateRange(getHeartRateInterval(this.mIsOpen), this.mIsOpen);
    }

    public void setHeartLimit(int i) {
        SportSetting sportSetting = SportSettingPreference.getSportSetting();
        if (i != -1) {
            sportSetting.setRateWarning(i);
        }
        CommonLogUtil.d(TAG, "setRateDataToLocal: " + sportSetting.toString());
        SportSettingPreference.saveSportSetting(sportSetting);
    }

    public void getSupportFunction() {
        SupportFunctionInfo supportFunctionInfo;
        if (getView() == null || (supportFunctionInfo = LocalDataManager.getSupportFunctionInfo()) == null) {
            return;
        }
        if (!supportFunctionInfo.ex_table_main11_not_support_heart_rate_high_alarm) {
            getView().showRateUpper(true);
            getView().showOptSportHeartRate(true);
        } else {
            getView().showRateUpper(false);
            getView().showOptSportHeartRate(false);
        }
    }

    public void unregisterSettingCallBack() {
        BLEManager.unregisterSettingCallBack(this.mSettingCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRateDataToLocal(boolean z, int i) {
        SportSetting sportSetting = SportSettingPreference.getSportSetting();
        sportSetting.setRateWarning(z);
        if (i != -1) {
            sportSetting.setRateWarning(i);
        }
        CommonLogUtil.d(TAG, "setRateDataToLocal: " + sportSetting.toString());
        SportSettingPreference.saveSportSetting(sportSetting);
    }
}