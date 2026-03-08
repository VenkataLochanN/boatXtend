package com.ido.life.util;

import android.content.Context;
import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.google.android.gms.common.GoogleApiAvailability;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.constants.Constants;
import com.ido.life.database.model.MapEngine;
import com.ido.life.database.model.PrivateSafeSetting;
import com.ido.life.database.model.TempUnitSetting;
import com.ido.life.database.model.TimeSet;
import com.ido.life.database.model.UnitSetting;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.WeekStartSetting;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class RunTimeUtil {
    public static final int USERID_DEFAULT = -1;
    private static RunTimeUtil mInstance = new RunTimeUtil();
    private long mUserId = -1;

    public static int getDefaultMapEngine() {
        return 2;
    }

    public int getMapEngine(Context context) {
        return 1;
    }

    private RunTimeUtil() {
    }

    public static RunTimeUtil getInstance() {
        return mInstance;
    }

    public long getUserId() {
        return this.mUserId;
    }

    public void setUserId(long j) {
        this.mUserId = j;
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), RunTimeUtil.class.getSimpleName(), "用户ID变更为" + this.mUserId);
    }

    public boolean hasLogin() {
        return this.mUserId > -1;
    }

    public boolean enableUploadPrivateData() {
        if (!hasLogin()) {
            return false;
        }
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(getInstance().getUserId());
        if (privateSafeSettingQueryPrivateSafeSetting != null) {
            return privateSafeSettingQueryPrivateSafeSetting.getSavePrivateData();
        }
        return true;
    }

    public boolean enableUploadSportData() {
        if (!hasLogin()) {
            return false;
        }
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(getInstance().getUserId());
        if (privateSafeSettingQueryPrivateSafeSetting != null) {
            return privateSafeSettingQueryPrivateSafeSetting.getSaveSportData();
        }
        return true;
    }

    public boolean enableUploadHealthData() {
        if (!hasLogin()) {
            return false;
        }
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(getInstance().getUserId());
        if (privateSafeSettingQueryPrivateSafeSetting != null) {
            return privateSafeSettingQueryPrivateSafeSetting.getSaveHealthData();
        }
        return true;
    }

    public int getWeekStart() {
        WeekStartSetting weekStartSettingQueryWeekStart = GreenDaoUtil.queryWeekStart(this.mUserId);
        if (weekStartSettingQueryWeekStart == null) {
            weekStartSettingQueryWeekStart = generateDefaultWeekSetting(getInstance().getUserId());
            GreenDaoUtil.addWeekStart(weekStartSettingQueryWeekStart);
        }
        return weekStartSettingQueryWeekStart.getWeekStart();
    }

    public static boolean supportGoogleService(Context context) {
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0;
    }

    public int getStepTarget() {
        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(this.mUserId);
        if (userTargetNewQueryUserLatestTarget != null) {
            return userTargetNewQueryUserLatestTarget.getStep();
        }
        return 10000;
    }

    public int getWeightTarget() {
        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(this.mUserId);
        if (userTargetNewQueryUserLatestTarget != null) {
            return userTargetNewQueryUserLatestTarget.getWeightInt();
        }
        return 60;
    }

    public int getTimeFormat() {
        TimeSet timeSetQueryTimeSet = GreenDaoUtil.queryTimeSet(this.mUserId);
        if (timeSetQueryTimeSet == null) {
            timeSetQueryTimeSet = generateDefaultTimeSet(this.mUserId);
            GreenDaoUtil.addTimeSet(timeSetQueryTimeSet);
        }
        return timeSetQueryTimeSet.getTimeFormat();
    }

    public int getUnitSet() {
        UnitSetting unitSettingQueryUnitSetting = GreenDaoUtil.queryUnitSetting(this.mUserId);
        if (unitSettingQueryUnitSetting == null) {
            unitSettingQueryUnitSetting = generateDefaultUnitSetting(getInstance().getUserId());
            GreenDaoUtil.addUnitSetting(unitSettingQueryUnitSetting);
        }
        return unitSettingQueryUnitSetting.getUnit();
    }

    public static TimeSet generateDefaultTimeSet(long j) {
        TimeSet timeSet = new TimeSet();
        timeSet.setHasSyncDeviceSuccess(false);
        timeSet.setHasUpload(false);
        timeSet.setTimeFormat(0);
        SPHelper.setTimeFormat(0);
        timeSet.setUserId(j);
        return timeSet;
    }

    public static UnitSetting generateDefaultUnitSetting(long j) {
        UnitSetting unitSetting = new UnitSetting();
        unitSetting.setUnit(1);
        unitSetting.setHasSyncDeviceSuccess(false);
        unitSetting.setHasUpload(false);
        unitSetting.setUserId(j);
        return unitSetting;
    }

    public static WeekStartSetting generateDefaultWeekSetting(long j) {
        WeekStartSetting weekStartSetting = new WeekStartSetting();
        weekStartSetting.setHasSyncDeviceSuccess(false);
        weekStartSetting.setHasUpload(false);
        weekStartSetting.setUserId(j);
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(j);
        weekStartSetting.setWeekStart((userInfoQueryUserInfo == null || !TextUtils.equals(userInfoQueryUserInfo.getAreaCode(), "86")) ? 1 : 2);
        return weekStartSetting;
    }

    public static MapEngine generateDefaultMapEngine(long j, int i) {
        MapEngine mapEngine = new MapEngine();
        mapEngine.setHasUpload(false);
        mapEngine.setMapEngine(i);
        mapEngine.setUserId(j);
        return mapEngine;
    }

    public static TempUnitSetting generateDefaultTempUnitSetting(long j) {
        TempUnitSetting tempUnitSetting = new TempUnitSetting();
        tempUnitSetting.setUserId(j);
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(j);
        int i = 2;
        if (userInfoQueryUserInfo != null) {
            String areaCode = userInfoQueryUserInfo.getAreaCode();
            if (!TextUtils.isEmpty(areaCode)) {
                if (getTempFCountryCode().indexOf(areaCode.trim()) <= -1) {
                    i = 1;
                }
            }
        }
        tempUnitSetting.setTemp(i);
        return tempUnitSetting;
    }

    private static List<String> getTempFCountryCode() {
        return new ArrayList<String>() { // from class: com.ido.life.util.RunTimeUtil.1
            {
                add("1242");
                add("501");
                add("1671");
                add("680");
                add("1787");
                add(Constants.USA_CODE);
                add(Constants.USA_VIRGIN_ISLANDS_CODE);
                add(Constants.USA_SAMOA_CODE);
            }
        };
    }

    public PrivateSafeSetting generateDefaultPrivateSafeSetting() {
        PrivateSafeSetting privateSafeSetting = new PrivateSafeSetting();
        privateSafeSetting.setUserId(this.mUserId);
        privateSafeSetting.setSavePrivateData(true);
        privateSafeSetting.setSaveHealthData(true);
        privateSafeSetting.setSaveSportData(true);
        privateSafeSetting.setSaveToGoogleFit(false);
        privateSafeSetting.setSaveToStrava(true);
        return privateSafeSetting;
    }

    public static String getCountryCodeByCountryName(String str) {
        String str2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        String[] stringArray = ResourceUtil.getStringArray(R.array.country_short_cn);
        int length = stringArray.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String str3 = stringArray[i];
            String[] strArrSplit = str3.split("_");
            if (strArrSplit[0].contentEquals(str)) {
                str2 = strArrSplit[1];
                break;
            }
            if (str3.contains(str)) {
                arrayList.add(str3);
            }
            i++;
        }
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        if (arrayList.size() <= 0) {
            return "";
        }
        int size = arrayList.size();
        String str4 = "";
        int length2 = 0;
        for (int i2 = 0; i2 < size; i2++) {
            String[] strArrSplit2 = ((String) arrayList.get(i2)).split("_");
            if (length2 == 0) {
                length2 = strArrSplit2[0].length();
                str4 = strArrSplit2[1];
            } else if (length2 > strArrSplit2[0].length()) {
                str4 = strArrSplit2[1];
                length2 = strArrSplit2[0].length();
            }
        }
        return str4;
    }

    public static String getCalorieUnit() {
        return LanguageUtil.getLanguageText(R.string.public_heat_calorie);
    }

    public static UserTargetNew generateDefaultUserTargetNew(long j) {
        UserTargetNew userTargetNew = new UserTargetNew();
        userTargetNew.setUserId(j);
        userTargetNew.setDate(DateUtil.format(Calendar.getInstance(Locale.getDefault()), DateUtil.DATE_FORMAT_YMD));
        userTargetNew.setStep(10000);
        userTargetNew.setDistance(5000);
        userTargetNew.setCalories(500);
        userTargetNew.setWeight(50.0f);
        userTargetNew.setWeightUnit(1);
        userTargetNew.setActivityTime(1800);
        userTargetNew.setWalk(43200);
        userTargetNew.setSportStep(100);
        return userTargetNew;
    }
}