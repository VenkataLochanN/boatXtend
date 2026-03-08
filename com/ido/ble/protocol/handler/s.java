package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import android.text.format.DateFormat;
import com.ido.ble.LocalDataManager;
import com.ido.ble.gps.model.ConfigGPS;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.ble.protocol.model.AntiLostMode;
import com.ido.ble.protocol.model.BindAuth;
import com.ido.ble.protocol.model.BreatheTrain;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.DialPlate;
import com.ido.ble.protocol.model.DisplayMode;
import com.ido.ble.protocol.model.DrinkWaterReminder;
import com.ido.ble.protocol.model.FitnessGuidance;
import com.ido.ble.protocol.model.Goal;
import com.ido.ble.protocol.model.HeartRateInterval;
import com.ido.ble.protocol.model.HeartRateMeasureMode;
import com.ido.ble.protocol.model.HeartRateMeasureModeV3;
import com.ido.ble.protocol.model.LongSit;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.ble.protocol.model.MenuList;
import com.ido.ble.protocol.model.NightTemperatureMonitoringPara;
import com.ido.ble.protocol.model.NoisePara;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.PressureParam;
import com.ido.ble.protocol.model.QuickSportMode;
import com.ido.ble.protocol.model.SPO2Param;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.ShortCut;
import com.ido.ble.protocol.model.SleepMonitoringPara;
import com.ido.ble.protocol.model.SportModeSort;
import com.ido.ble.protocol.model.SportModeSortV3;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.Units;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.ble.protocol.model.UserInfo;
import com.ido.ble.protocol.model.WalkReminder;
import com.ido.common.constant.LanguageRegion;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
final class s {
    s() {
    }

    private static void A() {
        PressureParam pressureParamL = com.ido.ble.f.a.f.a.c0().L();
        if (pressureParamL == null) {
            pressureParamL = new PressureParam();
            pressureParamL.onOff = 85;
        }
        com.ido.ble.i.a.a.a(pressureParamL);
    }

    private static void B() {
        QuickSportMode quickSportModeM = com.ido.ble.f.a.f.a.c0().M();
        if (quickSportModeM == null) {
            quickSportModeM = b();
        }
        com.ido.ble.i.a.a.a(quickSportModeM);
    }

    private static void C() {
        ScreenBrightness screenBrightnessO = com.ido.ble.f.a.f.a.c0().O();
        if (screenBrightnessO == null) {
            screenBrightnessO = new ScreenBrightness();
            screenBrightnessO.level = 100;
        }
        screenBrightnessO.opera = 0;
        screenBrightnessO.mode = 2;
        com.ido.ble.i.a.a.a(screenBrightnessO);
    }

    private static void D() {
        ShortCut shortCutP = com.ido.ble.f.a.f.a.c0().P();
        if (shortCutP == null) {
            shortCutP = new ShortCut();
        }
        com.ido.ble.i.a.a.a(shortCutP);
    }

    private static void E() {
        SleepMonitoringPara sleepMonitoringParaQ = com.ido.ble.f.a.f.a.c0().Q();
        if (sleepMonitoringParaQ == null) {
            sleepMonitoringParaQ = new SleepMonitoringPara();
        }
        com.ido.ble.i.a.a.a(sleepMonitoringParaQ);
        LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setSleepMonitoringPara:" + sleepMonitoringParaQ);
    }

    private static void F() {
        SPO2Param sPO2ParamR = com.ido.ble.f.a.f.a.c0().R();
        if (sPO2ParamR == null) {
            sPO2ParamR = new SPO2Param();
            sPO2ParamR.onOff = 85;
        }
        com.ido.ble.i.a.a.a(sPO2ParamR);
    }

    private static void G() {
        SportModeSort sportModeSortS = com.ido.ble.f.a.f.a.c0().S();
        if (sportModeSortS == null) {
            sportModeSortS = new SportModeSort();
            sportModeSortS.items = new SportModeSort.SportModeSortItem[1];
            SportModeSort.SportModeSortItem sportModeSortItem = new SportModeSort.SportModeSortItem();
            sportModeSortItem.index = 1;
            sportModeSortItem.type = 1;
            sportModeSortS.items[0] = sportModeSortItem;
        }
        com.ido.ble.i.a.a.a(sportModeSortS);
    }

    private static void H() {
        SportModeSortV3 sportModeSortV3T = com.ido.ble.f.a.f.a.c0().T();
        if (sportModeSortV3T == null) {
            sportModeSortV3T = new SportModeSortV3();
            SportModeSortV3.SportModeSortItemV3 sportModeSortItemV3 = new SportModeSortV3.SportModeSortItemV3();
            sportModeSortV3T.item = new ArrayList();
            sportModeSortV3T.num = 1;
            sportModeSortItemV3.type = 1;
            sportModeSortItemV3.index = 1;
            sportModeSortV3T.item.add(sportModeSortItemV3);
        }
        com.ido.ble.i.a.a.a(sportModeSortV3T);
    }

    private static void I() {
        Units unitsW = com.ido.ble.f.a.f.a.c0().W();
        if (unitsW == null) {
            unitsW = c();
        }
        com.ido.ble.i.a.a.a(unitsW);
    }

    private static void J() {
        UpHandGesture upHandGestureX = com.ido.ble.f.a.f.a.c0().X();
        if (upHandGestureX == null) {
            upHandGestureX = new UpHandGesture();
            upHandGestureX.onOff = 170;
            upHandGestureX.hasTimeRange = 0;
        }
        com.ido.ble.i.a.a.a(upHandGestureX);
    }

    private static void K() {
        WalkReminder walkReminderZ = com.ido.ble.f.a.f.a.c0().Z();
        if (walkReminderZ == null) {
            walkReminderZ = new WalkReminder();
            walkReminderZ.setOnOff(0);
        }
        com.ido.ble.i.a.a.a(walkReminderZ);
    }

    private static int a() {
        String language = Locale.getDefault().getLanguage();
        if (language.equalsIgnoreCase(LanguageRegion.ZH)) {
            return 1;
        }
        if (language.equalsIgnoreCase("en")) {
            return 2;
        }
        if (language.equalsIgnoreCase("fr")) {
            return 3;
        }
        if (language.equalsIgnoreCase("de")) {
            return 4;
        }
        if (language.equalsIgnoreCase("it")) {
            return 5;
        }
        if (language.equalsIgnoreCase("es")) {
            return 6;
        }
        return language.equalsIgnoreCase(LanguageRegion.JA) ? 7 : 2;
    }

    public static void a(int i) {
        String str;
        if (i == 107) {
            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setUserInfo");
            UserInfo userInfoY = com.ido.ble.f.a.f.a.c0().Y();
            if (userInfoY != null) {
                com.ido.ble.i.a.a.a(userInfoY);
                return;
            }
            str = "[SoBaseRequest] setUserInfo failed, userInfo is null, you should call BLEManager.setUserInfo or BLEManager.setUserInfoPending to set it";
        } else {
            if (i == 108) {
                LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setUnits");
                I();
                return;
            }
            if (i == 124) {
                LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setDialPlate");
                l();
                return;
            }
            if (i == 125) {
                LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setShortCut");
                D();
                return;
            }
            if (i == 150) {
                LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setWeatherSwitch");
                com.ido.ble.i.a.a.f(com.ido.ble.f.a.f.a.c0().a0());
                return;
            }
            if (i == 151) {
                LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setQuickSportMode");
                B();
                return;
            }
            if (i == 154) {
                LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setScreenBrightness");
                C();
                return;
            }
            if (i == 155) {
                LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setDefaultGpsPara");
                k();
                return;
            }
            switch (i) {
                case 100:
                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setAlarm");
                    com.ido.ble.i.a.a.g(com.ido.ble.f.a.f.a.c0().e());
                    return;
                case 101:
                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setLongSit");
                    t();
                    return;
                case 102:
                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setAntiLostMode");
                    g();
                    return;
                case 103:
                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setFindPhoneSwitch");
                    com.ido.ble.i.a.a.a(com.ido.ble.f.a.f.a.c0().r());
                    return;
                case 104:
                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setTime");
                    com.ido.ble.i.a.a.n0();
                    return;
                case 105:
                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setGoal");
                    p();
                    return;
                default:
                    switch (i) {
                        case 112:
                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setHeartRateInterval");
                            q();
                            return;
                        case 113:
                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setHeartRateMeasureMode");
                            r();
                            return;
                        case 114:
                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setUpHandGesture");
                            J();
                            return;
                        default:
                            switch (i) {
                                case 116:
                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setNotDisturbPara");
                                    z();
                                    return;
                                case 117:
                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setMusicSwitch");
                                    com.ido.ble.i.a.a.c(com.ido.ble.f.a.f.a.c0().G());
                                    return;
                                case 118:
                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setDisplayMode");
                                    m();
                                    return;
                                case 119:
                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setOneKeySOSSwitch");
                                    com.ido.ble.i.a.a.d(com.ido.ble.f.a.f.a.c0().K());
                                    return;
                                default:
                                    switch (i) {
                                        case 159:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setMenstrual");
                                            u();
                                            return;
                                        case 160:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setMenstrualRemind");
                                            v();
                                            return;
                                        case 161:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setCalorieAndDistanceGoal");
                                            j();
                                            return;
                                        case 162:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setSpO2Param");
                                            F();
                                            return;
                                        case 163:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setPressureParam");
                                            A();
                                            return;
                                        case 164:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setSportModeSort");
                                            G();
                                            return;
                                        case 165:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setWalkReminder");
                                            K();
                                            return;
                                        case 166:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setBreatheTrain");
                                            i();
                                            return;
                                        case 167:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setActivitySwitch");
                                            f();
                                            return;
                                        case 168:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setDrinkWaterReminder");
                                            n();
                                            return;
                                        case 171:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setMenuList");
                                            w();
                                            return;
                                        case com.veryfit.multi.nativeprotocol.b.G0 /* 202 */:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setBindAuth");
                                            h();
                                            return;
                                        case com.veryfit.multi.nativeprotocol.b.V0 /* 311 */:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] getExFunctionTables");
                                            com.ido.ble.i.a.a.B();
                                            return;
                                        case 5010:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setHeartRateMeasureModeV3");
                                            s();
                                            return;
                                        case 5013:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setSportModeSortV3");
                                            H();
                                            return;
                                        case 5018:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] getAlarmV3");
                                            com.ido.ble.i.a.a.n();
                                            return;
                                        case com.veryfit.multi.nativeprotocol.b.F3 /* 5031 */:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] getV3Tables");
                                            d();
                                            return;
                                        default:
                                            switch (i) {
                                                case 183:
                                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setNoisePara");
                                                    y();
                                                    return;
                                                case 184:
                                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setSleepMonitoringPara");
                                                    E();
                                                    return;
                                                case 185:
                                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setNightTemperatureMonitoringPara");
                                                    x();
                                                    return;
                                                case com.veryfit.multi.nativeprotocol.b.A0 /* 186 */:
                                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setFitnessGuidance");
                                                    o();
                                                    return;
                                                default:
                                                    switch (i) {
                                                        case 300:
                                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] getMacAddress");
                                                            com.ido.ble.i.a.a.G();
                                                            return;
                                                        case 301:
                                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] getBasicInfo");
                                                            com.ido.ble.i.a.a.p();
                                                            return;
                                                        case 302:
                                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] getFunctionTables");
                                                            com.ido.ble.i.a.a.R();
                                                            return;
                                                        default:
                                                            str = "[SoBaseRequest] should handle type = " + i;
                                                            break;
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
        LogTool.b(com.ido.ble.logs.a.i, str);
    }

    private static QuickSportMode b() {
        int i;
        QuickSportMode quickSportMode = new QuickSportMode();
        SupportFunctionInfo supportFunctionInfoV = com.ido.ble.f.a.f.a.c0().V();
        boolean z = supportFunctionInfoV != null && ((i = supportFunctionInfoV.sport_show_num) == 3 || i == 4);
        quickSportMode.sport_type0_walk = true;
        quickSportMode.sport_type0_run = true;
        quickSportMode.sport_type0_by_bike = true;
        if (!z) {
            quickSportMode.sport_type2_basketball = true;
            quickSportMode.sport_type0_on_foot = true;
            quickSportMode.sport_type0_badminton = true;
            quickSportMode.sport_type1_fitness = true;
            quickSportMode.sport_type1_treadmill = true;
        } else if (supportFunctionInfoV.sport_show_num == 4) {
            quickSportMode.sport_type1_fitness = true;
        }
        return quickSportMode;
    }

    private static Units c() {
        Units units = new Units();
        units.stride = 75;
        units.language = a();
        if (units.language == 1) {
            units.temp = 1;
            units.dist = 1;
        } else {
            units.temp = 2;
            units.dist = 2;
        }
        units.timeMode = e() ? 1 : 2;
        return units;
    }

    private static void d() {
        u.d(com.veryfit.multi.nativeprotocol.b.F3);
    }

    private static boolean e() {
        return DateFormat.is24HourFormat(com.ido.ble.common.e.a());
    }

    private static void f() {
        ActivitySwitch activitySwitchD = com.ido.ble.f.a.f.a.c0().d();
        if (activitySwitchD == null) {
            activitySwitchD = new ActivitySwitch();
        }
        com.ido.ble.i.a.a.a(activitySwitchD);
    }

    private static void g() {
        AntiLostMode antiLostModeG = com.ido.ble.f.a.f.a.c0().g();
        if (antiLostModeG == null) {
            antiLostModeG = new AntiLostMode();
            antiLostModeG.mode = 0;
        }
        com.ido.ble.i.a.a.a(antiLostModeG);
    }

    private static void h() {
        if (!com.ido.ble.bluetooth.a.g()) {
            LogTool.b(com.ido.ble.logs.a.i, "[SoBaseRequest] not int bind state, ignore set bind auth.");
            return;
        }
        String strC = com.ido.ble.bluetooth.a.c();
        if (TextUtils.isEmpty(strC)) {
            LogTool.b(com.ido.ble.logs.a.i, "[SoBaseRequest] bind auth is null, ignore set bind auth.");
        } else {
            com.ido.ble.i.a.a.a(((BindAuth) com.ido.ble.common.k.c(strC, BindAuth.class)).auth_code);
        }
    }

    private static void i() {
        BreatheTrain breatheTrainK = com.ido.ble.f.a.f.a.c0().k();
        if (breatheTrainK == null) {
            breatheTrainK = new BreatheTrain();
            breatheTrainK.frequency = 60;
        }
        com.ido.ble.i.a.a.a(breatheTrainK);
    }

    private static void j() {
        CalorieAndDistanceGoal calorieAndDistanceGoalM = com.ido.ble.f.a.f.a.c0().m();
        if (calorieAndDistanceGoalM == null) {
            calorieAndDistanceGoalM = new CalorieAndDistanceGoal();
        }
        com.ido.ble.i.a.a.a(calorieAndDistanceGoalM);
    }

    private static void k() {
        ConfigGPS configGPSU = com.ido.ble.f.a.f.a.c0().u();
        if (configGPSU == null) {
            configGPSU = new ConfigGPS();
            configGPSU.startMode = 2;
            configGPSU.operationMode = 1;
            configGPSU.cycleMS = 1000;
            configGPSU.gnsValue = 1;
        }
        if (LocalDataManager.getSupportFunctionInfo().ex_table_main8_ublox_model) {
            configGPSU.gnsValue = 3;
        }
        com.ido.ble.h.a.a(configGPSU);
    }

    private static void l() {
        DialPlate dialPlateO = com.ido.ble.f.a.f.a.c0().o();
        if (dialPlateO == null) {
            dialPlateO = new DialPlate();
            dialPlateO.dial_id = 1;
        }
        com.ido.ble.i.a.a.a(dialPlateO);
    }

    private static void m() {
        DisplayMode displayModeP = com.ido.ble.f.a.f.a.c0().p();
        if (displayModeP == null) {
            displayModeP = new DisplayMode();
            displayModeP.mode = 0;
        }
        com.ido.ble.i.a.a.a(displayModeP);
    }

    private static void n() {
        DrinkWaterReminder drinkWaterReminderQ = com.ido.ble.f.a.f.a.c0().q();
        if (drinkWaterReminderQ == null) {
            drinkWaterReminderQ = new DrinkWaterReminder();
        }
        com.ido.ble.i.a.a.a(drinkWaterReminderQ);
    }

    private static void o() {
        FitnessGuidance fitnessGuidanceS = com.ido.ble.f.a.f.a.c0().s();
        if (fitnessGuidanceS == null) {
            fitnessGuidanceS = new FitnessGuidance();
        }
        com.ido.ble.i.a.a.a(fitnessGuidanceS);
        LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setFitnessGuidance:" + fitnessGuidanceS);
    }

    private static void p() {
        Goal goalT = com.ido.ble.f.a.f.a.c0().t();
        if (goalT == null) {
            goalT = new Goal();
            goalT.sport_step = 10000;
        }
        com.ido.ble.i.a.a.a(goalT);
    }

    private static void q() {
        int i;
        HeartRateInterval heartRateIntervalW = com.ido.ble.f.a.f.a.c0().w();
        SupportFunctionInfo supportFunctionInfoV = com.ido.ble.f.a.f.a.c0().V();
        int i2 = 200;
        if (heartRateIntervalW != null) {
            int userMaxHR = heartRateIntervalW.getUserMaxHR();
            if (userMaxHR == 0) {
                double d2 = 200;
                heartRateIntervalW.setLimintThreshold((int) (0.85d * d2));
                heartRateIntervalW.setAerobicThreshold((int) (d2 * 0.7d));
                heartRateIntervalW.setBurnFatThreshold((int) (d2 * 0.5d));
            } else {
                i2 = userMaxHR;
            }
            if (supportFunctionInfoV != null && (supportFunctionInfoV.level5_hr_interval || supportFunctionInfoV.FiveHRInterval)) {
                double d3 = i2;
                heartRateIntervalW.setLimintThreshold((int) (0.9d * d3));
                heartRateIntervalW.setAnaerobicThreshold((int) (0.8d * d3));
                heartRateIntervalW.setAerobicThreshold((int) (0.7d * d3));
                heartRateIntervalW.setBurnFatThreshold((int) (0.6d * d3));
                i = (int) (d3 * 0.5d);
                heartRateIntervalW.setWarmUpThreshold(i);
            }
        } else {
            heartRateIntervalW = new HeartRateInterval();
            heartRateIntervalW.setUserMaxHR(200);
            heartRateIntervalW.setLimintThreshold(168);
            heartRateIntervalW.setAerobicThreshold(126);
            heartRateIntervalW.setBurnFatThreshold(105);
            if (supportFunctionInfoV != null && (supportFunctionInfoV.level5_hr_interval || supportFunctionInfoV.FiveHRInterval)) {
                heartRateIntervalW.setLimintThreshold(180);
                heartRateIntervalW.setAnaerobicThreshold(160);
                heartRateIntervalW.setAerobicThreshold(140);
                heartRateIntervalW.setBurnFatThreshold(120);
                i = 100;
                heartRateIntervalW.setWarmUpThreshold(i);
            }
        }
        com.ido.ble.i.a.a.a(heartRateIntervalW);
    }

    private static void r() {
        HeartRateMeasureMode heartRateMeasureModeX = com.ido.ble.f.a.f.a.c0().x();
        if (heartRateMeasureModeX == null) {
            heartRateMeasureModeX = new HeartRateMeasureMode();
            heartRateMeasureModeX.mode = 136;
        }
        com.ido.ble.i.a.a.a(heartRateMeasureModeX);
    }

    private static void s() {
        HeartRateMeasureModeV3 heartRateMeasureModeV3Y = com.ido.ble.f.a.f.a.c0().y();
        if (heartRateMeasureModeV3Y == null) {
            heartRateMeasureModeV3Y = new HeartRateMeasureModeV3();
            heartRateMeasureModeV3Y.mode = 136;
        }
        com.ido.ble.i.a.a.a(heartRateMeasureModeV3Y);
    }

    private static void t() {
        LongSit longSitC = com.ido.ble.f.a.f.a.c0().C();
        if (longSitC == null) {
            longSitC = new LongSit();
            longSitC.setStartHour(9);
            longSitC.setEndHour(20);
            longSitC.setInterval(15);
            longSitC.setOnOff(false);
        } else if (longSitC.getInterval() == 0) {
            longSitC.setInterval(15);
        }
        com.ido.ble.i.a.a.a(longSitC);
    }

    private static void u() {
        Menstrual menstrualD = com.ido.ble.f.a.f.a.c0().D();
        if (menstrualD == null) {
            menstrualD = new Menstrual();
            menstrualD.on_off = 85;
        }
        com.ido.ble.i.a.a.a(menstrualD);
    }

    private static void v() {
        MenstrualRemind menstrualRemindE = com.ido.ble.f.a.f.a.c0().E();
        if (menstrualRemindE == null) {
            menstrualRemindE = new MenstrualRemind();
        }
        com.ido.ble.i.a.a.a(menstrualRemindE);
    }

    private static void w() {
        MenuList menuListF = com.ido.ble.f.a.f.a.c0().F();
        if (menuListF == null) {
            menuListF = new MenuList();
            menuListF.items.add(15);
            menuListF.items.add(9);
        }
        com.ido.ble.i.a.a.a(menuListF);
    }

    private static void x() {
        NightTemperatureMonitoringPara nightTemperatureMonitoringParaH = com.ido.ble.f.a.f.a.c0().H();
        if (nightTemperatureMonitoringParaH == null) {
            nightTemperatureMonitoringParaH = new NightTemperatureMonitoringPara();
        }
        com.ido.ble.i.a.a.a(nightTemperatureMonitoringParaH);
        LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setNightTemperatureMonitoringPara:" + nightTemperatureMonitoringParaH);
    }

    private static void y() {
        NoisePara noiseParaI = com.ido.ble.f.a.f.a.c0().I();
        if (noiseParaI == null) {
            noiseParaI = new NoisePara();
        }
        com.ido.ble.i.a.a.a(noiseParaI);
        LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setnoisePara:" + noiseParaI);
    }

    private static void z() {
        NotDisturbPara notDisturbParaJ = com.ido.ble.f.a.f.a.c0().J();
        if (notDisturbParaJ == null) {
            notDisturbParaJ = new NotDisturbPara();
            notDisturbParaJ.onOFf = 85;
        }
        com.ido.ble.i.a.a.a(notDisturbParaJ);
    }
}