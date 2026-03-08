package com.ido.life.module.sport.setting;

import com.boat.Xtend.two.R;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.module.sport.setting.SportSettingContract;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;

/* JADX INFO: loaded from: classes2.dex */
public class SportSettingPresenter implements SportSettingContract.Presenter {
    public static final int CATEGORY = 2;
    public static final int DISTANCE = 1;
    public static final int NO_TARGET = 0;
    public static final int STEP = 3;
    public static final int TIME = 4;
    private boolean mIsRide;
    private SportSettingContract.View mView;
    public int mStepMin = 5000;
    private int[] distances = new int[102];

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public SportSettingPresenter(SportSettingContract.View view) {
        this.mView = view;
        initDistance();
    }

    private void initDistance() {
        int i = 0;
        while (true) {
            int[] iArr = this.distances;
            if (i >= iArr.length) {
                return;
            }
            iArr[i] = i;
            i++;
        }
    }

    @Override // com.ido.life.module.sport.setting.SportSettingContract.Presenter
    public void getHistoryData() {
        SportSetting sportSetting = SportSettingPreference.getSportSetting();
        getUnitName(0);
        if (sportSetting.isSportTarget()) {
            if (sportSetting.getDistance() > 0) {
                String unitName = getUnitName(1);
                if (RunTimeUtil.getInstance().getUnitSet() == 1) {
                    if (sportSetting.getDistance() == 21092) {
                        this.mView.setSportTarget(LanguageUtil.getLanguageText(R.string.sport_half_horse));
                    } else if (sportSetting.getDistance() == 42195) {
                        this.mView.setSportTarget(LanguageUtil.getLanguageText(R.string.sport_whole_horse));
                    } else {
                        int distance = sportSetting.getDistance() / 1000;
                        this.mView.setSportTarget(distance + unitName);
                    }
                } else if (sportSetting.getDistance() == 21092) {
                    this.mView.setSportTarget(LanguageUtil.getLanguageText(R.string.sport_half_horse));
                } else if (sportSetting.getDistance() == 42195) {
                    this.mView.setSportTarget(LanguageUtil.getLanguageText(R.string.sport_whole_horse));
                } else {
                    int iRound = Math.round(UnitUtil.km2mile(sportSetting.getDistance()) / 1000.0f);
                    this.mView.setSportTarget(iRound + unitName);
                }
            } else if (sportSetting.getCategory() > 0) {
                String unitName2 = getUnitName(2);
                String str = sportSetting.getCategory() + "";
                this.mView.setSportTarget(str + unitName2);
            } else if (sportSetting.getTime() > 0) {
                String unitName3 = getUnitName(4);
                String strValueOf = String.valueOf(sportSetting.getTime() / 60);
                this.mView.setSportTarget(strValueOf + unitName3);
            } else if (this.mIsRide) {
                this.mView.setSportTarget(LanguageUtil.getLanguageText(R.string.sport_setting_no_target));
            } else {
                String unitName4 = getUnitName(3);
                String str2 = sportSetting.getStep() + "";
                this.mView.setSportTarget(str2 + unitName4);
            }
        }
        if (!sportSetting.isDistanceVoice()) {
            this.mView.setSportDistance(LanguageUtil.getLanguageText(R.string.sport_setting_remind_no));
        } else if (RunTimeUtil.getInstance().getUnitSet() == 1) {
            this.mView.setSportDistance(sportSetting.getDistanceInterval() + LanguageUtil.getLanguageText(R.string.sport_run_distance_unit));
        } else {
            this.mView.setSportDistance(sportSetting.getDistanceInterval() + LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi));
        }
        if (!getSupportFunction()) {
            if (!sportSetting.isRateWarning()) {
                this.mView.setRateRange(LanguageUtil.getLanguageText(R.string.sport_setting_rate_close));
                return;
            } else if (sportSetting.getRateWarning() == 0) {
                this.mView.setRateRange(String.format(ResourceUtil.getString(R.string.sport_setting_rate_explain_rate_up_value), 220));
                return;
            } else {
                this.mView.setRateRange(String.format(ResourceUtil.getString(R.string.sport_setting_rate_explain_rate_up_value), Integer.valueOf(sportSetting.getRateWarning())));
                return;
            }
        }
        this.mView.setRateRange("");
    }

    @Override // com.ido.life.module.sport.setting.SportSettingContract.Presenter
    public boolean getSupportFunction() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo != null) {
            return supportFunctionInfo.ex_table_main11_not_support_heart_rate_high_alarm;
        }
        return false;
    }

    @Override // com.ido.life.module.sport.setting.SportSettingContract.Presenter
    public void setTarget(int i, int i2) {
        String step;
        String unitName = getUnitName(i);
        if (i == 0) {
            setNoTarget();
            this.mView.setSportTarget(getUnitName(0));
            return;
        }
        if (i == 1) {
            setDistanceTarget(getTargetDistance(i2));
            return;
        }
        if (i == 2) {
            String category = getCategory(i2);
            setCategoryTarget(category);
            this.mView.setSportTarget(category + unitName);
            return;
        }
        if (i != 3) {
            if (i != 4) {
                return;
            }
            String time = getTime(i2);
            setTimeTarget(time);
            this.mView.setSportTarget(time + unitName);
            return;
        }
        if (this.mIsRide) {
            step = getTime(i2);
            setTimeTarget(step);
        } else {
            step = getStep(i2);
            setStepTarget(step);
        }
        this.mView.setSportTarget(step + unitName);
    }

    @Override // com.ido.life.module.sport.setting.SportSettingContract.Presenter
    public void setIsCycle(boolean z) {
        this.mIsRide = z;
    }

    private String getUnitName(int i) {
        String languageText = LanguageUtil.getLanguageText(R.string.sport_setting_no_target);
        if (i == 0) {
            return LanguageUtil.getLanguageText(R.string.sport_setting_no_target);
        }
        if (i == 1) {
            if (RunTimeUtil.getInstance().getUnitSet() == 1) {
                return LanguageUtil.getLanguageText(R.string.sport_run_distance_unit);
            }
            return LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi);
        }
        if (i == 2) {
            return LanguageUtil.getLanguageText(R.string.public_heat_calorie);
        }
        if (i != 3) {
            return i != 4 ? languageText : LanguageUtil.getLanguageText(R.string.public_time_minute);
        }
        if (this.mIsRide) {
            return LanguageUtil.getLanguageText(R.string.public_time_minute);
        }
        return LanguageUtil.getLanguageText(R.string.public_sport_step);
    }

    private void setDistanceTarget(int i) {
        SportSetting sportSetting = SportSettingPreference.getSportSetting();
        sportSetting.setSportTarget(true);
        try {
            sportSetting.setDistance(i);
        } catch (Exception unused) {
        }
        sportSetting.setCategory(0);
        sportSetting.setStep(0);
        sportSetting.setTime(0);
        sportSetting.setSportRemindOff(true);
        SportSettingPreference.saveSportSetting(sportSetting);
    }

    private void setCategoryTarget(String str) {
        SportSetting sportSetting = SportSettingPreference.getSportSetting();
        sportSetting.setSportTarget(true);
        sportSetting.setSportRemindOff(true);
        sportSetting.setDistance(0);
        sportSetting.setCategory(Integer.parseInt(str));
        sportSetting.setStep(0);
        sportSetting.setTime(0);
        SportSettingPreference.saveSportSetting(sportSetting);
    }

    private void setStepTarget(String str) {
        SportSetting sportSetting = SportSettingPreference.getSportSetting();
        sportSetting.setSportTarget(true);
        sportSetting.setSportRemindOff(true);
        sportSetting.setDistance(0);
        sportSetting.setCategory(0);
        sportSetting.setTime(0);
        sportSetting.setStep(Integer.parseInt(str));
        SportSettingPreference.saveSportSetting(sportSetting);
    }

    private void setTimeTarget(String str) {
        SportSetting sportSetting = SportSettingPreference.getSportSetting();
        sportSetting.setSportTarget(true);
        sportSetting.setSportRemindOff(true);
        sportSetting.setDistance(0);
        sportSetting.setCategory(0);
        sportSetting.setStep(0);
        sportSetting.setTime(Integer.parseInt(str) * 60);
        SportSettingPreference.saveSportSetting(sportSetting);
    }

    private String getStep(int i) {
        for (int i2 = 0; i2 <= 46; i2++) {
            if (i == i2) {
                return String.valueOf(((i2 - 1) * 1000) + this.mStepMin);
            }
        }
        return String.valueOf(this.mStepMin);
    }

    private String getCategory(int i) {
        for (int i2 = 0; i2 <= 20; i2++) {
            if (i == i2) {
                return String.valueOf(i2 * 100);
            }
        }
        return String.valueOf(i * 100);
    }

    private String getTime(int i) {
        for (int i2 = 0; i2 <= 60; i2++) {
            if (i == i2) {
                return String.valueOf(i2 * 5);
            }
        }
        return String.valueOf(i * 5);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x010e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int getTargetDistance(int r8) {
        /*
            Method dump skipped, instruction units count: 279
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.sport.setting.SportSettingPresenter.getTargetDistance(int):int");
    }

    @Override // com.ido.life.module.sport.setting.SportSettingContract.Presenter
    public void clearTarget() {
        SportSettingPreference.clear();
    }

    public void setNoTarget() {
        SportSetting sportSetting = SportSettingPreference.getSportSetting();
        sportSetting.setSportTarget(false);
        SportSettingPreference.saveSportSetting(sportSetting);
    }

    @Override // com.ido.life.module.sport.setting.SportSettingContract.Presenter
    public void setDistanceInterval(int i) {
        SportSetting sportSetting = SportSettingPreference.getSportSetting();
        if (i == 0) {
            sportSetting.setDistanceVoice(false);
            this.mView.setSportDistance(LanguageUtil.getLanguageText(R.string.sport_setting_remind_no));
        } else {
            sportSetting.setDistanceVoice(true);
            sportSetting.setSportRemindOff(true);
            if (RunTimeUtil.getInstance().getUnitSet() == 1) {
                this.mView.setSportDistance(i + LanguageUtil.getLanguageText(R.string.sport_run_distance_unit));
            } else {
                this.mView.setSportDistance(i + LanguageUtil.getLanguageText(R.string.sport_detail_distance_unit_mi));
            }
        }
        sportSetting.setDistanceInterval(i);
        SportSettingPreference.saveSportSetting(sportSetting);
    }
}