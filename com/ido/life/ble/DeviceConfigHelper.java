package com.ido.life.ble;

import android.text.TextUtils;
import android.text.format.DateFormat;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.DrinkWaterReminder;
import com.ido.ble.protocol.model.Goal;
import com.ido.ble.protocol.model.HeartRateInterval;
import com.ido.ble.protocol.model.HeartRateMeasureMode;
import com.ido.ble.protocol.model.HeartRateMeasureModeV3;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.ble.protocol.model.NoisePara;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.PressureParam;
import com.ido.ble.protocol.model.QuickSportMode;
import com.ido.ble.protocol.model.SPO2Param;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.SleepMonitoringPara;
import com.ido.ble.protocol.model.SportModeSort;
import com.ido.ble.protocol.model.SportModeSortV3;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.Units;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.ble.protocol.model.WalkReminder;
import com.ido.common.IdoApp;
import com.ido.common.constant.LanguageRegion;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.life.bean.QuickApp;
import com.ido.life.bean.SortBean;
import com.ido.life.constants.Constants;
import com.ido.life.database.model.LifeCycleItemBean;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.module.home.fitness.FitnessHelperKt;
import com.ido.life.util.DateUtil;
import com.ido.life.util.FunctionHelper;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RemoteLanguageHelper;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.TimeUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceConfigHelper {
    public static SupportFunctionInfo getSupportFunctionInfo() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo == null ? new SupportFunctionInfo() : supportFunctionInfo;
    }

    public static void setUnitsFlowSystem(boolean z) {
        int i;
        Units units = LocalDataManager.getUnits();
        if (units == null) {
            return;
        }
        if (SPHelper.isLanguageFollowSys()) {
            String language = Locale.getDefault().getLanguage();
            SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
            if (supportFunctionInfo == null) {
                return;
            }
            if (language.equals(LanguageRegion.ZH) && supportFunctionInfo.lang_ch) {
                i = 1;
            } else if (language.equalsIgnoreCase("en")) {
                i = 2;
            } else if (language.equalsIgnoreCase("fr") && supportFunctionInfo.lang_french) {
                i = 3;
            } else if (language.equalsIgnoreCase("de") && supportFunctionInfo.lang_german) {
                i = 4;
            } else if (language.equalsIgnoreCase("it") && supportFunctionInfo.lang_italian) {
                i = 5;
            } else if (language.equalsIgnoreCase("es") && supportFunctionInfo.lang_spanish) {
                i = 6;
            } else if (language.equalsIgnoreCase(LanguageRegion.JA) && supportFunctionInfo.lang_japanese) {
                i = 7;
            } else if (language.equalsIgnoreCase("pl") && supportFunctionInfo.ex_lang_polish) {
                i = 8;
            } else if (language.equalsIgnoreCase("cs") && supportFunctionInfo.lang_czech) {
                i = 9;
            } else if (language.equalsIgnoreCase("ro") && supportFunctionInfo.ex_lang_romanian) {
                i = 10;
            } else if (language.equalsIgnoreCase("lt") && supportFunctionInfo.ex_lang_lithuanian) {
                i = 11;
            } else if (language.equalsIgnoreCase("nl") && supportFunctionInfo.ex_lang_dutch) {
                i = 12;
            } else if (language.equalsIgnoreCase("sl") && supportFunctionInfo.ex_lang_slovenian) {
                i = 13;
            } else if (language.equalsIgnoreCase("hu") && supportFunctionInfo.ex_lang_hungarian) {
                i = 14;
            } else if (language.equalsIgnoreCase(LanguageRegion.RU) && supportFunctionInfo.ex_lang_russian) {
                i = 15;
            } else if (language.equalsIgnoreCase("uk") && supportFunctionInfo.ex_lang_ukrainian) {
                i = 16;
            } else if (language.equalsIgnoreCase("sk") && supportFunctionInfo.ex_lang1_slovak) {
                i = 17;
            } else if (language.equalsIgnoreCase("da") && supportFunctionInfo.ex_lang1_danish) {
                i = 18;
            } else if (language.equalsIgnoreCase("hr") && supportFunctionInfo.ex_lang2_croatian) {
                i = 19;
            } else if (language.equalsIgnoreCase("ko") && supportFunctionInfo.ex_lang2_korean) {
                i = 21;
            } else if (language.equalsIgnoreCase("hi") && supportFunctionInfo.ex_lang2_hindi) {
                i = 22;
            } else if (language.equalsIgnoreCase(Constants.INDIA_SERVICE) && supportFunctionInfo.ex_lang2_indonesian) {
                i = 20;
            } else {
                i = (language.equalsIgnoreCase(LanguageRegion.PT) && supportFunctionInfo.ex_lang2_portuguese) ? 23 : units.language;
            }
            units.language = i;
        }
        int timeFormat = RunTimeUtil.getInstance().getTimeFormat();
        if (timeFormat == 1) {
            units.timeMode = 1;
        } else if (timeFormat == 2) {
            units.timeMode = 2;
        } else {
            units.timeMode = DateFormat.is24HourFormat(IdoApp.getAppContext()) ? 1 : 2;
        }
        int weekStart = RunTimeUtil.getInstance().getWeekStart();
        if (weekStart == 2) {
            units.weekStartDate = 0;
        } else if (weekStart == 7) {
            units.weekStartDate = 3;
        } else {
            units.weekStartDate = 1;
        }
        if (RunTimeUtil.getInstance().getUnitSet() == 2) {
            units.weight = 2;
            units.dist = 2;
        } else {
            units.weight = 1;
            units.dist = 1;
        }
        if (z) {
            BLEManager.setUnit(units);
        } else {
            BLEManager.setUnitPending(units);
        }
    }

    public static int formatLanguageCode(Units units) {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null) {
            supportFunctionInfo = new SupportFunctionInfo();
        }
        String language = Locale.getDefault().getLanguage();
        RemoteLanguageHelper.saveLanguageLog("setUnitsFlowSystem", "DeviceConfigHelper，county ：" + language);
        if (language.equals(LanguageRegion.ZH) && supportFunctionInfo.lang_ch) {
            return 1;
        }
        if (language.equalsIgnoreCase("en")) {
            return 2;
        }
        if (language.equalsIgnoreCase("fr") && supportFunctionInfo.lang_french) {
            return 3;
        }
        if (language.equalsIgnoreCase("de") && supportFunctionInfo.lang_german) {
            return 4;
        }
        if (language.equalsIgnoreCase("it") && supportFunctionInfo.lang_italian) {
            return 5;
        }
        if (language.equalsIgnoreCase("es") && supportFunctionInfo.lang_spanish) {
            return 6;
        }
        if (language.equalsIgnoreCase(LanguageRegion.JA) && supportFunctionInfo.lang_japanese) {
            return 7;
        }
        if (language.equalsIgnoreCase("pl") && supportFunctionInfo.ex_lang_polish) {
            return 8;
        }
        if (language.equalsIgnoreCase("cs") && supportFunctionInfo.lang_czech) {
            return 9;
        }
        if (language.equalsIgnoreCase("ro") && supportFunctionInfo.ex_lang_romanian) {
            return 10;
        }
        if (language.equalsIgnoreCase("lt") && supportFunctionInfo.ex_lang_lithuanian) {
            return 11;
        }
        if (language.equalsIgnoreCase("nl") && supportFunctionInfo.ex_lang_dutch) {
            return 12;
        }
        if (language.equalsIgnoreCase("sl") && supportFunctionInfo.ex_lang_slovenian) {
            return 13;
        }
        if (language.equalsIgnoreCase("hu") && supportFunctionInfo.ex_lang_hungarian) {
            return 14;
        }
        if (language.equalsIgnoreCase(LanguageRegion.RU) && supportFunctionInfo.ex_lang_russian) {
            return 15;
        }
        if (language.equalsIgnoreCase("uk") && supportFunctionInfo.ex_lang_ukrainian) {
            return 16;
        }
        if (language.equalsIgnoreCase("sk") && supportFunctionInfo.ex_lang1_slovak) {
            return 17;
        }
        if (language.equalsIgnoreCase("da") && supportFunctionInfo.ex_lang1_danish) {
            return 18;
        }
        if (language.equalsIgnoreCase("hr") && supportFunctionInfo.ex_lang2_croatian) {
            return 19;
        }
        if (language.equalsIgnoreCase("ko") && supportFunctionInfo.ex_lang2_korean) {
            return 21;
        }
        if (language.equalsIgnoreCase("hi") && supportFunctionInfo.ex_lang2_hindi) {
            return 22;
        }
        if (language.equalsIgnoreCase(Constants.INDIA_SERVICE) && supportFunctionInfo.ex_lang2_indonesian) {
            return 20;
        }
        if (language.equalsIgnoreCase(LanguageRegion.PT) && supportFunctionInfo.ex_lang2_portuguese) {
            return 23;
        }
        if (units.language == 0) {
            return 2;
        }
        return units.language;
    }

    public static void initHeartRateMode() {
        int ageByBirthday;
        SupportFunctionInfo supportFunctionInfo = getSupportFunctionInfo();
        if (supportFunctionInfo.heartRateMonitor) {
            HeartRateMeasureMode heartRateMode = LocalDataManager.getHeartRateMode();
            if (heartRateMode == null) {
                heartRateMode = new HeartRateMeasureMode();
            }
            heartRateMode.startHour = 0;
            heartRateMode.startMinute = 0;
            heartRateMode.endHour = 23;
            heartRateMode.endMinute = 59;
            heartRateMode.mode = 153;
            BLEManager.setHeartRateMeasureModePending(heartRateMode);
        }
        if (supportFunctionInfo.ex_main4_v3_hr_data) {
            HeartRateMeasureModeV3 heartRateModeV3 = LocalDataManager.getHeartRateModeV3();
            if (heartRateModeV3 == null) {
                heartRateModeV3 = new HeartRateMeasureModeV3();
            }
            if (supportFunctionInfo.V3_support_show_detection_time) {
                heartRateModeV3.startHour = 9;
                heartRateModeV3.startMinute = 0;
                heartRateModeV3.endHour = 18;
                heartRateModeV3.endMinute = 0;
            } else {
                heartRateModeV3.startHour = 0;
                heartRateModeV3.startMinute = 0;
                heartRateModeV3.endHour = 23;
                heartRateModeV3.endMinute = 59;
            }
            if (supportFunctionInfo.v3_heart_set_rate_mode_custom) {
                heartRateModeV3.highHeartMode = 85;
                heartRateModeV3.lowHeartMode = 85;
            }
            heartRateModeV3.mode = 187;
            heartRateModeV3.updateTime = (int) TimeUtil.getUTCTime();
            BLEManager.setHeartRateMeasureModeV3Pending(heartRateModeV3);
        }
        SPHelper.saveHeartRateMode(153);
        if (supportFunctionInfo.level5_hr_interval || supportFunctionInfo.FiveHRInterval) {
            HeartRateInterval heartRateInterval = LocalDataManager.getHeartRateInterval();
            if (heartRateInterval == null) {
                heartRateInterval = new HeartRateInterval();
            }
            heartRateInterval.setRemindStartHour(0);
            heartRateInterval.setRemindStartMinute(0);
            heartRateInterval.setRemindStopHour(23);
            heartRateInterval.setRemindStopMinute(59);
            heartRateInterval.setMinHr(20);
            UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
            try {
                ageByBirthday = DateUtil.getAgeByBirthday(userInfoQueryUserInfo != null ? userInfoQueryUserInfo.getBirthday() : "", DateUtil.DATE_FORMAT_YMD);
            } catch (Exception e2) {
                e2.printStackTrace();
                ageByBirthday = 0;
            }
            int i = 220 - ageByBirthday;
            heartRateInterval.setUserMaxHR(i);
            double d2 = i;
            heartRateInterval.setWarmUpThreshold((int) (0.5d * d2));
            heartRateInterval.setBurnFatThreshold((int) (0.6d * d2));
            heartRateInterval.setAerobicThreshold((int) (0.7d * d2));
            heartRateInterval.setAnaerobicThreshold((int) (0.8d * d2));
            heartRateInterval.setLimintThreshold((int) (d2 * 0.9d));
            heartRateInterval.setMaxHrRemind(0);
            heartRateInterval.setMinHrRemind(0);
            BLEManager.setHeartRateIntervalPending(heartRateInterval);
        }
    }

    public static void initPressureMode() {
        SupportFunctionInfo supportFunctionInfo = getSupportFunctionInfo();
        if (supportFunctionInfo.ex_main3_pressure || supportFunctionInfo.ex_main3_v3_pressure) {
            PressureParam pressureParam = LocalDataManager.getPressureParam();
            if (pressureParam == null) {
                pressureParam = new PressureParam();
            }
            pressureParam.onOff = 170;
            if (supportFunctionInfo.ex_table_main11_pressure_high_threshold_reminder) {
                pressureParam.remindOnOff = 85;
                pressureParam.startHour = 9;
                pressureParam.startMinute = 0;
                pressureParam.endHour = 18;
                pressureParam.endMinute = 0;
                pressureParam.highThreshold = 80;
                pressureParam.interval = 60;
                pressureParam.setWeekRepeat(new boolean[]{true, true, true, true, true, false, false});
            }
            BLEManager.setPressureParamPending(pressureParam);
        }
    }

    public static void initDNDMode() {
        SupportFunctionInfo supportFunctionInfo = getSupportFunctionInfo();
        if (supportFunctionInfo.doNotDisturb || supportFunctionInfo.ex_main3_get_do_not_disturb) {
            NotDisturbPara notDisturbPara = LocalDataManager.getNotDisturbPara();
            if (notDisturbPara == null) {
                notDisturbPara = new NotDisturbPara();
            }
            notDisturbPara.onOFf = 85;
            notDisturbPara.startHour = 9;
            notDisturbPara.startMinute = 0;
            notDisturbPara.endHour = 18;
            notDisturbPara.endMinute = 0;
            notDisturbPara.noontimeRestOnOff = 85;
            notDisturbPara.noontimeRestStartHour = 22;
            notDisturbPara.noontimeRestStartMinute = 0;
            notDisturbPara.noontimeRestEndHour = 7;
            notDisturbPara.noontimeRestEndMinute = 0;
            notDisturbPara.setWeeks(new boolean[]{true, true, true, true, true, true, true});
            BLEManager.setNotDisturbParaPending(notDisturbPara);
        }
    }

    public static void initNightLight() {
        SupportFunctionInfo supportFunctionInfo = getSupportFunctionInfo();
        if (supportFunctionInfo.ex_screen_brightness || supportFunctionInfo.screen_brightness_5_level || supportFunctionInfo.ex_table_main8_screen_brightness_3_level || supportFunctionInfo.night_auto_brightness) {
            ScreenBrightness screenBrigthnessConfig = LocalDataManager.getScreenBrigthnessConfig();
            if (screenBrigthnessConfig == null) {
                screenBrigthnessConfig = new ScreenBrightness();
            }
            if (supportFunctionInfo.ex_screen_brightness || supportFunctionInfo.screen_brightness_5_level || supportFunctionInfo.ex_table_main8_screen_brightness_3_level) {
                screenBrigthnessConfig.level = 100;
            }
            if (supportFunctionInfo.night_auto_brightness) {
                screenBrigthnessConfig.autoAdjustNight = 3;
                screenBrigthnessConfig.startHour = 19;
                screenBrigthnessConfig.startMinute = 0;
                screenBrigthnessConfig.endHour = 6;
                screenBrigthnessConfig.endMinute = 0;
            }
            BLEManager.setSCreenBrightnessConfigPending(screenBrigthnessConfig);
        }
    }

    public static void initWalkReminder() {
        if (getSupportFunctionInfo().walk_reminder) {
            WalkReminder walkReminder = LocalDataManager.getWalkReminder();
            if (walkReminder == null) {
                walkReminder = new WalkReminder();
            }
            walkReminder.setOnOff(0);
            walkReminder.setWeeks(new boolean[]{true, true, true, true, true, true, true});
            walkReminder.setStartHour(9);
            walkReminder.setStartMinute(0);
            walkReminder.setEndHour(21);
            walkReminder.setEndMinute(0);
            walkReminder.setGoalStep(100);
            BLEManager.setWalkReminderPending(walkReminder);
            SPHelper.saveWalkReminder(walkReminder);
        }
    }

    public static void initMotionType() {
        int[] iArr;
        SupportFunctionInfo supportFunctionInfo = getSupportFunctionInfo();
        if (supportFunctionInfo.V3_set_100_sport_sort) {
            return;
        }
        int i = supportFunctionInfo.sport_show_num;
        int i2 = 0;
        if (supportFunctionInfo.ex_table_main7_v3_sports_type) {
            List<SortBean> supportMotionTypeList = FunctionHelper.getSupportMotionTypeList();
            SportModeSortV3 sportModeSortV3 = new SportModeSortV3();
            sportModeSortV3.num = supportMotionTypeList.size();
            ArrayList arrayList = new ArrayList();
            while (i2 < supportMotionTypeList.size()) {
                SortBean sortBean = supportMotionTypeList.get(i2);
                SportModeSortV3.SportModeSortItemV3 sportModeSortItemV3 = new SportModeSortV3.SportModeSortItemV3();
                sportModeSortItemV3.type = sortBean.getType();
                i2++;
                sportModeSortItemV3.index = i2;
                arrayList.add(sportModeSortItemV3);
            }
            sportModeSortV3.item = arrayList;
            BLEManager.setSportModeSortInfoV3(sportModeSortV3);
            return;
        }
        char c2 = 1;
        if (supportFunctionInfo.sport_mode_sort) {
            if (i == 3) {
                int[] iArr2 = new int[3];
                iArr2[0] = supportFunctionInfo.outdoor_walk ? '4' : supportFunctionInfo.sport_type0_walk ? (char) 1 : (char) 0;
                iArr2[1] = supportFunctionInfo.outdoor_run ? '0' : supportFunctionInfo.sport_type0_run ? (char) 2 : (char) 0;
                iArr2[2] = supportFunctionInfo.outdoor_cycle ? '2' : supportFunctionInfo.sport_type0_by_bike ? (char) 3 : (char) 0;
                iArr = iArr2;
            } else if (i == 7) {
                int[] iArr3 = new int[7];
                if (supportFunctionInfo.pool_swim) {
                    iArr3[0] = supportFunctionInfo.outdoor_cycle ? '2' : supportFunctionInfo.sport_type0_by_bike ? (char) 3 : (char) 0;
                    iArr3[1] = 54;
                    if (supportFunctionInfo.outdoor_walk) {
                        c2 = '4';
                    } else if (!supportFunctionInfo.sport_type0_walk) {
                        c2 = 0;
                    }
                    iArr3[2] = c2;
                    iArr3[3] = supportFunctionInfo.outdoor_run ? '0' : supportFunctionInfo.sport_type0_run ? (char) 2 : (char) 0;
                    iArr3[4] = supportFunctionInfo.sport_type0_on_foot ? (char) 4 : (char) 0;
                    iArr3[5] = supportFunctionInfo.indoor_run ? '1' : (char) 0;
                    iArr3[6] = supportFunctionInfo.indoor_walk ? '5' : (char) 0;
                    iArr = iArr3;
                } else {
                    iArr3[0] = supportFunctionInfo.outdoor_run ? '0' : supportFunctionInfo.sport_type0_run ? (char) 2 : (char) 0;
                    iArr3[1] = supportFunctionInfo.indoor_run ? '1' : (char) 0;
                    iArr3[2] = supportFunctionInfo.outdoor_cycle ? '2' : supportFunctionInfo.sport_type0_by_bike ? (char) 3 : (char) 0;
                    iArr3[3] = supportFunctionInfo.indoor_cycle ? '3' : (char) 0;
                    if (supportFunctionInfo.outdoor_walk) {
                        c2 = '4';
                    } else if (!supportFunctionInfo.sport_type0_walk) {
                        c2 = 0;
                    }
                    iArr3[4] = c2;
                    iArr3[5] = supportFunctionInfo.sport_type0_on_foot ? (char) 4 : (char) 0;
                    iArr3[6] = supportFunctionInfo.sport_type0_other ? '\b' : (char) 0;
                    iArr = iArr3;
                }
            } else if (i == 6) {
                int[] iArr4 = new int[6];
                iArr4[0] = supportFunctionInfo.outdoor_walk ? '4' : supportFunctionInfo.sport_type0_walk ? (char) 1 : (char) 0;
                iArr4[1] = supportFunctionInfo.outdoor_run ? '0' : supportFunctionInfo.sport_type0_run ? (char) 2 : (char) 0;
                iArr4[2] = supportFunctionInfo.outdoor_cycle ? '2' : supportFunctionInfo.sport_type0_by_bike ? (char) 3 : (char) 0;
                iArr4[3] = supportFunctionInfo.indoor_walk ? '5' : (char) 0;
                iArr4[4] = supportFunctionInfo.pool_swim ? '6' : supportFunctionInfo.sport_type0_swim ? (char) 5 : (char) 0;
                iArr4[5] = supportFunctionInfo.open_water_swim ? '7' : (char) 0;
                iArr = iArr4;
            } else {
                int[] iArr5 = new int[8];
                if (supportFunctionInfo.outdoor_walk) {
                    iArr5[0] = supportFunctionInfo.outdoor_cycle ? '2' : supportFunctionInfo.sport_type0_by_bike ? (char) 3 : (char) 0;
                    iArr5[1] = supportFunctionInfo.pool_swim ? '6' : supportFunctionInfo.sport_type0_swim ? (char) 5 : (char) 0;
                    iArr5[2] = 52;
                    iArr5[3] = supportFunctionInfo.outdoor_run ? '0' : supportFunctionInfo.sport_type0_run ? (char) 2 : (char) 0;
                    iArr5[4] = supportFunctionInfo.sport_type0_on_foot ? (char) 4 : (char) 0;
                    iArr5[5] = supportFunctionInfo.indoor_run ? '1' : (char) 0;
                    iArr5[6] = supportFunctionInfo.indoor_walk ? '5' : (char) 0;
                    iArr5[7] = supportFunctionInfo.HIIT ? ':' : (char) 0;
                    iArr = iArr5;
                } else {
                    iArr5[0] = supportFunctionInfo.sport_type0_run ? (char) 2 : (char) 0;
                    iArr5[1] = supportFunctionInfo.sport_type0_walk ? 1 : 0;
                    iArr5[2] = supportFunctionInfo.sport_type0_by_bike ? (char) 3 : (char) 0;
                    iArr5[3] = supportFunctionInfo.sport_type0_on_foot ? (char) 4 : (char) 0;
                    iArr5[4] = supportFunctionInfo.sport_type0_mountain_climbing ? (char) 6 : (char) 0;
                    iArr5[5] = supportFunctionInfo.sport_type1_treadmill ? '\f' : (char) 0;
                    iArr5[6] = supportFunctionInfo.sport_type1_spinning ? '\n' : (char) 0;
                    iArr5[7] = supportFunctionInfo.sport_type2_yoga ? (char) 18 : (char) 0;
                    iArr = iArr5;
                }
            }
            SportModeSort sportModeSort = new SportModeSort();
            int[] iArr6 = new int[iArr.length];
            int i3 = 0;
            for (int i4 = 0; i4 < iArr.length; i4++) {
                if (iArr[i4] > 0) {
                    iArr6[i3] = iArr[i4];
                    i3++;
                }
            }
            sportModeSort.items = new SportModeSort.SportModeSortItem[i3];
            while (i2 < sportModeSort.items.length) {
                sportModeSort.items[i2] = new SportModeSort.SportModeSortItem();
                int i5 = i2 + 1;
                sportModeSort.items[i2].index = i5;
                sportModeSort.items[i2].type = iArr6[i2];
                i2 = i5;
            }
            BLEManager.setSportModeSortInfoPending(sportModeSort);
            return;
        }
        if (FunctionHelper.isSupportSportMode()) {
            QuickSportMode quickSportMode = new QuickSportMode();
            if (i == 3 || i == 4) {
                quickSportMode.sport_type0_walk = true;
                quickSportMode.sport_type0_run = true;
                quickSportMode.sport_type0_by_bike = true;
                if (i == 4) {
                    quickSportMode.sport_type1_fitness = true;
                    return;
                }
                return;
            }
            if (FunctionHelper.isNewSwim()) {
                quickSportMode.outdoor_walk = true;
                quickSportMode.outdoor_run = true;
                quickSportMode.outdoor_cycle = true;
                quickSportMode.indoor_walk = true;
                quickSportMode.pool_swim = true;
                quickSportMode.open_water_swim = true;
                quickSportMode.HIIT = true;
                return;
            }
            quickSportMode.sport_type0_walk = true;
            quickSportMode.sport_type0_run = true;
            quickSportMode.sport_type0_by_bike = true;
            quickSportMode.sport_type0_on_foot = true;
            quickSportMode.sport_type2_basketball = true;
            quickSportMode.sport_type0_badminton = true;
            quickSportMode.sport_type1_fitness = true;
            quickSportMode.sport_type1_treadmill = true;
        }
    }

    public static void initWaterClock() {
        if (getSupportFunctionInfo().ex_main4_drink_water_reminder) {
            DrinkWaterReminder drinkWaterReminder = LocalDataManager.getDrinkWaterReminder();
            if (drinkWaterReminder == null) {
                drinkWaterReminder = new DrinkWaterReminder();
            }
            drinkWaterReminder.setOnOff(false);
            drinkWaterReminder.setStartHour(9);
            drinkWaterReminder.setStartMinute(0);
            drinkWaterReminder.setEndHour(18);
            drinkWaterReminder.setEndMinute(0);
            drinkWaterReminder.setInterval(30);
            drinkWaterReminder.setWeeks(new boolean[]{true, true, true, true, true, false, false});
            BLEManager.setDrinkWaterReminderPending(drinkWaterReminder);
        }
    }

    public static void initMotionRecognition() {
        if (getSupportFunctionInfo().activity_switch) {
            ActivitySwitch activitySwitch = LocalDataManager.getActivitySwitch();
            if (activitySwitch == null) {
                activitySwitch = new ActivitySwitch();
            }
            activitySwitch.autoIdentifySportRun = 1;
            activitySwitch.autoIdentifySportWalk = 0;
            activitySwitch.autoEndRemindOnOffOnOff = 0;
            activitySwitch.autoIdentifySportBicycle = 0;
            activitySwitch.autoPauseOnOff = 0;
            BLEManager.setActivitySwitchPending(activitySwitch);
        }
    }

    public static void initMusicControl() {
        if (getSupportFunctionInfo().bleControlMusic) {
            CommonLogUtil.printAndSave("setMusicSwitchPending：true");
            BLEManager.setMusicSwitchPending(true);
        }
    }

    public static void initFindPhone() {
        if (getSupportFunctionInfo().findPhone) {
            BLEManager.setFindPhoneSwitchPending(true);
        }
    }

    public static void initMenuLists() {
        SupportFunctionInfo supportFunctionInfo = getSupportFunctionInfo();
        if (!supportFunctionInfo.ex_main7_menu_list || supportFunctionInfo.V3_get_menu_list) {
            return;
        }
        QuickApp quickApp = new QuickApp();
        ArrayList arrayList = new ArrayList();
        arrayList.add(17);
        arrayList.add(2);
        arrayList.add(13);
        arrayList.add(16);
        if (FunctionHelper.isSupportSpO2()) {
            arrayList.add(18);
        }
        quickApp.items = arrayList;
        BLEManager.setMenuListPending(quickApp);
    }

    private static boolean handle7264v10(SupportFunctionInfo supportFunctionInfo) {
        BasicInfo basicInfo;
        if (!supportFunctionInfo.ex_table_main7_voice_transmission || (basicInfo = LocalDataManager.getBasicInfo()) == null || 7264 != basicInfo.deivceId || basicInfo.firmwareVersion < 10) {
            return false;
        }
        QuickApp quickApp = new QuickApp();
        ArrayList arrayList = new ArrayList();
        arrayList.add(17);
        arrayList.add(2);
        arrayList.add(13);
        arrayList.add(18);
        arrayList.add(16);
        arrayList.add(20);
        quickApp.items = arrayList;
        BLEManager.setMenuListPending(quickApp);
        return true;
    }

    public static void initUpHandGesture() {
        if (getSupportFunctionInfo().upHandGestrue) {
            UpHandGesture upHandGesture = LocalDataManager.getUpHandGesture();
            if (upHandGesture == null) {
                upHandGesture = new UpHandGesture();
            }
            upHandGesture.onOff = 170;
            BLEManager.setUpHandGesturePending(upHandGesture);
        }
    }

    public static void initGoalValue() {
        CalorieAndDistanceGoal calorieAndDistanceGoal = new CalorieAndDistanceGoal();
        UserTargetNew userTargetNewQueryUserLastestTarget = GreenDaoUtil.queryUserLastestTarget(RunTimeUtil.getInstance().getUserId());
        if (userTargetNewQueryUserLastestTarget == null) {
            userTargetNewQueryUserLastestTarget = RunTimeUtil.generateDefaultUserTargetNew(RunTimeUtil.getInstance().getUserId());
        }
        int iCaluteCalorieMax = FitnessHelperKt.caluteCalorieMax(FitnessHelperKt.caluteBMR(RunTimeUtil.getInstance().getUserId()));
        int iCaluteCalorieMin = FitnessHelperKt.caluteCalorieMin(FitnessHelperKt.caluteBMR(RunTimeUtil.getInstance().getUserId()));
        if (getSupportFunctionInfo().ex_main3_calorie_goal) {
            calorieAndDistanceGoal.calorie = userTargetNewQueryUserLastestTarget.getCalories() > 0 ? userTargetNewQueryUserLastestTarget.getCalories() : 500;
            calorieAndDistanceGoal.calorie_min = iCaluteCalorieMin;
            calorieAndDistanceGoal.calorie_max = iCaluteCalorieMax;
            calorieAndDistanceGoal.mid_high_time_goal = userTargetNewQueryUserLastestTarget.getActivityTime();
            calorieAndDistanceGoal.walk_goal_time = userTargetNewQueryUserLastestTarget.getWalk() / 3600;
            if (calorieAndDistanceGoal.walk_goal_time == 0) {
                calorieAndDistanceGoal.walk_goal_time = 12;
            }
            CommonLogUtil.printAndSave("setCalorieAndDistanceGoalPending, calorieAndDistanceGoal = " + calorieAndDistanceGoal);
            BLEManager.setCalorieAndDistanceGoalPending(calorieAndDistanceGoal);
            return;
        }
        if (getSupportFunctionInfo().ex_main3_calorie_goal) {
            calorieAndDistanceGoal.calorie = userTargetNewQueryUserLastestTarget.getCalories() > 0 ? userTargetNewQueryUserLastestTarget.getCalories() : 500;
            CommonLogUtil.printAndSave("setCalorieAndDistanceGoalPending, calorieAndDistanceGoal = " + calorieAndDistanceGoal);
            BLEManager.setCalorieAndDistanceGoalPending(calorieAndDistanceGoal);
        }
        if (getSupportFunctionInfo().ex_main3_distance_goal) {
            calorieAndDistanceGoal.distance = userTargetNewQueryUserLastestTarget.getDistance() > 0 ? userTargetNewQueryUserLastestTarget.getDistance() : 5000;
            CommonLogUtil.printAndSave("setCalorieAndDistanceGoalPending, 支持距离目标 calorieAndDistanceGoal = " + calorieAndDistanceGoal);
            BLEManager.setCalorieAndDistanceGoalPending(calorieAndDistanceGoal);
        }
        Goal goal = new Goal();
        goal.sport_step = userTargetNewQueryUserLastestTarget.getStep() > 0 ? userTargetNewQueryUserLastestTarget.getStep() : 10000;
        CommonLogUtil.printAndSave("setGoalPending, goal = " + goal);
        BLEManager.setGoalPending(goal);
    }

    public static void initWeatherForecastSwitch() {
        if (getSupportFunctionInfo().weather || getSupportFunctionInfo().V3_support_set_v3_weather) {
            CommonLogUtil.printAndSave("setWeatherSwitchPending：true");
            BLEManager.setWeatherSwitchPending(true);
        }
    }

    public static void initBloodOxygenDetection() {
        if (getSupportFunctionInfo().V3_support_set_spo2_all_day_on_off) {
            SPO2Param spO2Param = LocalDataManager.getSpO2Param();
            if (spO2Param == null) {
                spO2Param = new SPO2Param();
            }
            spO2Param.startHour = 0;
            spO2Param.startMinute = 0;
            spO2Param.endHour = 23;
            spO2Param.endMinute = 59;
            spO2Param.lowSpo2OnOff = 85;
            spO2Param.onOff = 85;
            spO2Param.lowSpo2OnValue = 85;
            CommonLogUtil.printAndSave("setSPO2ParamPending：" + spO2Param);
            BLEManager.setSPO2ParamPending(spO2Param);
        }
    }

    public static void initMenstrualCycle() {
        LifeCycleItemBean lifeCycleItemBeanQueryLatestLifeCycle;
        if (!getSupportFunctionInfo().ex_main3_menstruation || (lifeCycleItemBeanQueryLatestLifeCycle = HomeHelperKt.queryLatestLifeCycle(RunTimeUtil.getInstance().getUserId())) == null || lifeCycleItemBeanQueryLatestLifeCycle.getItemList() == null || lifeCycleItemBeanQueryLatestLifeCycle.getItemList().size() == 0) {
            return;
        }
        CommonLogUtil.printAndSave("lifeCycleItemBean = " + GsonUtil.toJson(lifeCycleItemBeanQueryLatestLifeCycle));
        Menstrual menstrual = LocalDataManager.getMenstrual();
        if (menstrual == null) {
            menstrual = new Menstrual();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.string2Date(lifeCycleItemBeanQueryLatestLifeCycle.getMonth(), DateUtil.DATE_FORMAT_YM_3));
        calendar.set(5, lifeCycleItemBeanQueryLatestLifeCycle.getItemList().get(lifeCycleItemBeanQueryLatestLifeCycle.getItemList().size() - 1).get(0).intValue());
        menstrual.last_menstrual_year = calendar.get(1);
        menstrual.last_menstrual_month = calendar.get(2);
        menstrual.last_menstrual_day = calendar.get(5);
        menstrual.menstrual_cycle = lifeCycleItemBeanQueryLatestLifeCycle.getMensesCycle();
        menstrual.menstrual_length = lifeCycleItemBeanQueryLatestLifeCycle.getMensesDays();
        MenstrualRemind menstrualRemind = LocalDataManager.getMenstrualRemind();
        if (menstrualRemind == null) {
            menstrualRemind = new MenstrualRemind();
        }
        menstrualRemind.start_day = Math.min(3, Math.max(1, lifeCycleItemBeanQueryLatestLifeCycle.getMensesStartDay()));
        menstrualRemind.ovulation_day = Math.min(3, Math.max(1, lifeCycleItemBeanQueryLatestLifeCycle.getOvulationDay()));
        if (!TextUtils.isEmpty(lifeCycleItemBeanQueryLatestLifeCycle.getReminderTime())) {
            try {
                String[] strArrSplit = lifeCycleItemBeanQueryLatestLifeCycle.getReminderTime().split(":");
                menstrualRemind.hour = Integer.parseInt(strArrSplit[0]);
                menstrualRemind.minute = Integer.parseInt(strArrSplit[1]);
            } catch (Exception e2) {
                e2.printStackTrace();
                menstrualRemind.hour = 20;
                menstrualRemind.minute = 0;
            }
        }
        BLEManager.setMenstrualPending(menstrual);
        BLEManager.setMenstrualRemindPending(menstrualRemind);
    }

    public static void initScienceSleep() {
        if (getSupportFunctionInfo().V3_support_set_scientific_sleep_switch) {
            SleepMonitoringPara sleepMonitoringPara = LocalDataManager.getSleepMonitoringPara();
            if (sleepMonitoringPara == null) {
                sleepMonitoringPara = new SleepMonitoringPara();
                sleepMonitoringPara.mode = 170;
            }
            CommonLogUtil.printAndSave("setSleepMonitoringParaPending：" + sleepMonitoringPara);
            BLEManager.setSleepMonitoringParaPending(sleepMonitoringPara);
        }
    }

    public static void initNoiseMonitoring() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (!getSupportFunctionInfo().V3_health_sync_noise || currentDeviceInfo == null) {
            return;
        }
        NoisePara noiseMode = SPHelper.getNoiseMode(currentDeviceInfo.mDeviceAddress);
        if (noiseMode == null) {
            noiseMode = new NoisePara();
            noiseMode.mode = 85;
        }
        SPHelper.setNoiseMode(currentDeviceInfo.mDeviceAddress, noiseMode);
        CommonLogUtil.printAndSave("setNoisePara：" + noiseMode);
        BLEManager.setNoiseParaPending(noiseMode);
    }
}