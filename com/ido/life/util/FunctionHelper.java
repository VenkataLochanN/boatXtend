package com.ido.life.util;

import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.boat.Xtend.two.R;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.Units;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.bean.SortBean;
import com.ido.life.constants.Constants;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class FunctionHelper {
    public static final int DEVICE_SHAPE_CIRCLE = 1;
    public static final int DEVICE_SHAPE_OVAL = 3;
    public static final int DEVICE_SHAPE_RECTANGLE = 2;
    public static final int DEV_TYPE_BRACELET = 3;
    public static final int DEV_TYPE_WATCH = 4;
    public static final int DEV_TYPE_WATCH_CIRCLE = 5;

    public static boolean isFactoryReset() {
        return false;
    }

    public static boolean isLanguageDown() {
        return false;
    }

    public static boolean isLanguageIndia() {
        return true;
    }

    public static boolean isSort() {
        return true;
    }

    public static boolean isHr() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo.heartRate || supportFunctionInfo.ex_main4_v3_hr_data;
    }

    public static boolean isSedentariness() {
        return LocalDataManager.getSupportFunctionInfo().sedentariness;
    }

    public static boolean isV3Hr() {
        return LocalDataManager.getSupportFunctionInfo().ex_main4_v3_hr_data;
    }

    public static boolean isV3AutoLight() {
        return LocalDataManager.getSupportFunctionInfo().night_auto_brightness;
    }

    public static boolean isBreatheTrain() {
        return LocalDataManager.getSupportFunctionInfo().breathe_train;
    }

    public static boolean isTimeLine() {
        return LocalDataManager.getSupportFunctionInfo().timeLine;
    }

    public static boolean isSupportSportMode() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null || supportFunctionInfo.multiActivityNoUseApp) {
            return false;
        }
        return supportFunctionInfo.sport_type0_walk || supportFunctionInfo.sport_type0_run || supportFunctionInfo.sport_type0_by_bike || supportFunctionInfo.sport_type0_on_foot || supportFunctionInfo.sport_type0_swim || supportFunctionInfo.sport_type0_mountain_climbing || supportFunctionInfo.sport_type0_badminton || supportFunctionInfo.sport_type0_other || supportFunctionInfo.sport_type1_fitness || supportFunctionInfo.sport_type1_spinning || supportFunctionInfo.sport_type1_ellipsoid || supportFunctionInfo.sport_type1_treadmill || supportFunctionInfo.sport_type1_sit_up || supportFunctionInfo.sport_type1_push_up || supportFunctionInfo.sport_type1_dumbbell || supportFunctionInfo.sport_type1_weightlifting || supportFunctionInfo.sport_type2_bodybuilding_exercise || supportFunctionInfo.sport_type2_yoga || supportFunctionInfo.sport_type2_rope_skipping || supportFunctionInfo.sport_type2_table_tennis || supportFunctionInfo.sport_type2_basketball || supportFunctionInfo.sport_type3_golf || supportFunctionInfo.sport_type3_baseball || supportFunctionInfo.sport_type3_skiing || supportFunctionInfo.sport_type3_roller_skating || supportFunctionInfo.sport_type3_dance || supportFunctionInfo.open_water_swim || supportFunctionInfo.outdoor_walk || supportFunctionInfo.pool_swim || supportFunctionInfo.outdoor_run || supportFunctionInfo.rower || supportFunctionInfo.outdoor_cycle || supportFunctionInfo.indoor_run || supportFunctionInfo.indoor_cycle || supportFunctionInfo.indoor_walk || supportFunctionInfo.elliptical || supportFunctionInfo.HIIT;
    }

    public static boolean isScreenBrightness() {
        return LocalDataManager.getSupportFunctionInfo().ex_screen_brightness;
    }

    public static boolean isFiveHrInterval() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo.FiveHRInterval || supportFunctionInfo.level5_hr_interval;
    }

    public static boolean isOxgen() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo.ex_main3_spo2_data || supportFunctionInfo.ex_main3_v3_spo2_data;
    }

    public static boolean isV3Activity() {
        return LocalDataManager.getSupportFunctionInfo().ex_main4_v3_activity_data;
    }

    public static boolean isOutDoorRun() {
        return LocalDataManager.getSupportFunctionInfo().outdoor_run;
    }

    public static boolean isOutDoorCycle() {
        return LocalDataManager.getSupportFunctionInfo().outdoor_cycle;
    }

    public static boolean isOutDoorWalk() {
        return LocalDataManager.getSupportFunctionInfo().outdoor_walk;
    }

    public static boolean isNewSwim() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo.pool_swim || supportFunctionInfo.open_water_swim;
    }

    public static boolean isGpsSportType() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo.outdoor_cycle && supportFunctionInfo.outdoor_run && supportFunctionInfo.outdoor_walk && supportFunctionInfo.sport_type0_on_foot;
    }

    public static boolean isActivitySwitch() {
        return LocalDataManager.getSupportFunctionInfo().activity_switch;
    }

    public static boolean isScreenBrightness5level() {
        return LocalDataManager.getSupportFunctionInfo().screen_brightness_5_level;
    }

    public static boolean isScreenBrightness3level() {
        LocalDataManager.getSupportFunctionInfo();
        return false;
    }

    public static boolean isId205Dial() {
        LocalDataManager.getSupportFunctionInfo();
        return false;
    }

    public static boolean isId107Dial() {
        return LocalDataManager.getSupportFunctionInfo().ex_id107_l_dial;
    }

    public static boolean isSleepMonitor() {
        LocalDataManager.getSupportFunctionInfo();
        return false;
    }

    public static boolean isLanguageHindi() {
        return LocalDataManager.getSupportFunctionInfo().ex_lang2_hindi;
    }

    public static boolean isLanguagePortuguese() {
        return LocalDataManager.getSupportFunctionInfo().ex_lang2_portuguese;
    }

    public static boolean isDialSquare() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo == null) {
            return false;
        }
        return basicInfo.deivceId == 233 || basicInfo.deivceId == 235;
    }

    public static boolean isDialDown() {
        if (LocalDataManager.getBasicInfo() == null) {
        }
        return false;
    }

    public static boolean is205SId() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo == null) {
            return false;
        }
        return basicInfo.deivceId == 233 || basicInfo.deivceId == 6802;
    }

    public static boolean isSupportHeartMonitoring() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        return basicInfo != null && basicInfo.deivceId == 7264;
    }

    public static boolean isSupportOver14chars() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo == null) {
            return false;
        }
        if (basicInfo.deivceId != 7264) {
            return true;
        }
        if (basicInfo.deivceId == 7264) {
            int iIntValue = ((Integer) SPUtils.get(Constants.PERIPHERAL_VERSION, 0)).intValue();
            CommonLogUtil.printAndSave("isSupportOver14chars-->peripheral的值是" + iIntValue);
            if (basicInfo.firmwareVersion >= 11 || (basicInfo.firmwareVersion == 10 && iIntValue == 1)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLimitHr() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo == null) {
            return false;
        }
        return basicInfo.deivceId == 1111 || basicInfo.deivceId == 233;
    }

    public static boolean isBleControlMusic() {
        return LocalDataManager.getSupportFunctionInfo().bleControlMusic;
    }

    public static boolean is302Or303() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        return basicInfo != null && (basicInfo.deivceId == 302 || basicInfo.deivceId == 303);
    }

    public static boolean isExGgps() {
        return LocalDataManager.getSupportFunctionInfo().ex_gps;
    }

    public static boolean isId205L() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        return basicInfo != null && (basicInfo.deivceId == 310 || basicInfo.deivceId == 228 || basicInfo.deivceId == 307);
    }

    public static int getTimeFormat() {
        Units units = LocalDataManager.getUnits();
        if (units == null) {
            return 0;
        }
        return units.timeMode;
    }

    public static List<SortBean> getSupportMotionTypeList() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        ArrayList arrayList = new ArrayList();
        if (supportFunctionInfo == null) {
            return arrayList;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDeviceLogPath(), "获取支持的运动类型列表===" + supportFunctionInfo.toString());
        if (supportFunctionInfo.outdoor_run) {
            arrayList.add(new SortBean(48, R.string.sport_run_outdoor, false));
        }
        if (supportFunctionInfo.indoor_run) {
            arrayList.add(new SortBean(49, R.string.sport_run_indoor, false));
        }
        if (supportFunctionInfo.sport_type0_run) {
            arrayList.add(new SortBean(2, R.string.sport_tab_run, false));
        }
        if (supportFunctionInfo.outdoor_walk) {
            arrayList.add(new SortBean(52, R.string.sport_walk_outdoor, false));
        }
        if (supportFunctionInfo.sport_type0_walk) {
            arrayList.add(new SortBean(1, R.string.sport_tab_walk, false));
        }
        if (supportFunctionInfo.indoor_walk) {
            arrayList.add(new SortBean(53, R.string.sport_walk_indoor, false));
        }
        if (supportFunctionInfo.sport_type0_on_foot) {
            arrayList.add(new SortBean(4, R.string.sport_walk_onfoot, false));
        }
        if (supportFunctionInfo.outdoor_cycle) {
            arrayList.add(new SortBean(50, R.string.sport_type_ride_outdoor, false));
        }
        if (supportFunctionInfo.sport_type0_by_bike) {
            arrayList.add(new SortBean(3, R.string.sport_tab_ride, false));
        }
        if (supportFunctionInfo.cricket) {
            arrayList.add(new SortBean(75, R.string.sport_type_cricket, false));
        }
        if (supportFunctionInfo.sport_type2_yoga) {
            arrayList.add(new SortBean(18, R.string.sport_type_yoga, false));
        }
        if (supportFunctionInfo.indoor_cycle) {
            arrayList.add(new SortBean(51, R.string.sport_indoor_cycle_android, false));
        }
        if (supportFunctionInfo.sport_type1_fitness) {
            arrayList.add(new SortBean(9, R.string.sport_fitness_android, false));
        }
        if (supportFunctionInfo.sport_type0_swim) {
            arrayList.add(new SortBean(5, R.string.mine_sport_swim, false));
        }
        if (supportFunctionInfo.pool_swim) {
            arrayList.add(new SortBean(54, R.string.sport_type_pool_swimming, false));
        }
        if (supportFunctionInfo.open_water_swim) {
            arrayList.add(new SortBean(55, R.string.sport_type_open_swimming, false));
        }
        if (supportFunctionInfo.rower) {
            arrayList.add(new SortBean(57, R.string.sport_type_rowing_machine, false));
        }
        if (supportFunctionInfo.elliptical) {
            arrayList.add(new SortBean(56, R.string.sport_type_elliptical_machine, false));
        }
        if (supportFunctionInfo.sport_type0_mountain_climbing) {
            arrayList.add(new SortBean(6, R.string.sport_record_climb, false));
        }
        if (supportFunctionInfo.sport_type0_badminton) {
            arrayList.add(new SortBean(7, R.string.sport_type_badminton, false));
        }
        if (supportFunctionInfo.sport_type1_spinning) {
            arrayList.add(new SortBean(10, R.string.sport_type_dynamic_bicycle, false));
        }
        if (supportFunctionInfo.sport_type1_treadmill) {
            arrayList.add(new SortBean(12, R.string.sport_treadmill_android, false));
        }
        if (supportFunctionInfo.sport_type2_basketball) {
            arrayList.add(new SortBean(21, R.string.sport_type_basketball, false));
        }
        if (supportFunctionInfo.sport_type2_footballl) {
            arrayList.add(new SortBean(22, R.string.sport_football, false));
        }
        if (supportFunctionInfo.sport_type2_tennis) {
            arrayList.add(new SortBean(24, R.string.sport_tennis, false));
        }
        if (supportFunctionInfo.sport_type3_dance) {
            arrayList.add(new SortBean(29, R.string.sport_dance, false));
        }
        if (supportFunctionInfo.sport_type1_ellipsoid) {
            arrayList.add(new SortBean(11, R.string.sport_ellipsoid, false));
        }
        if (supportFunctionInfo.sport_type1_sit_up) {
            arrayList.add(new SortBean(13, R.string.sport_sit_up, false));
        }
        if (supportFunctionInfo.sport_type1_push_up) {
            arrayList.add(new SortBean(14, R.string.sport_push_up, false));
        }
        if (supportFunctionInfo.sport_type1_dumbbell) {
            arrayList.add(new SortBean(15, R.string.sport_dumbbell, false));
        }
        if (supportFunctionInfo.sport_type1_weightlifting) {
            arrayList.add(new SortBean(16, R.string.sport_weightlifting, false));
        }
        if (supportFunctionInfo.sport_type2_bodybuilding_exercise) {
            arrayList.add(new SortBean(17, R.string.sport_bodybuilding_exercise, false));
        }
        if (supportFunctionInfo.sport_type2_rope_skipping) {
            arrayList.add(new SortBean(19, R.string.sport_rope_skipping, false));
        }
        if (supportFunctionInfo.sport_type2_volleyball) {
            arrayList.add(new SortBean(23, R.string.sport_volleyball, false));
        }
        if (supportFunctionInfo.sport_type2_table_tennis) {
            arrayList.add(new SortBean(20, R.string.sport_ping_pong, false));
        }
        if (supportFunctionInfo.sport_type3_golf) {
            arrayList.add(new SortBean(25, R.string.sport_golf, false));
        }
        if (supportFunctionInfo.sport_type3_baseball) {
            arrayList.add(new SortBean(26, R.string.sport_baseball, false));
        }
        if (supportFunctionInfo.sport_type3_skiing) {
            arrayList.add(new SortBean(27, R.string.sport_skiing, false));
        }
        if (supportFunctionInfo.sport_type3_roller_skating) {
            arrayList.add(new SortBean(28, R.string.sport_roller_skating, false));
        }
        if (supportFunctionInfo.pilates) {
            arrayList.add(new SortBean(32, R.string.sport_pilates, false));
        }
        if (supportFunctionInfo.zumba) {
            arrayList.add(new SortBean(35, R.string.sport_zumba, false));
        }
        if (supportFunctionInfo.sport_type3_core_training) {
            arrayList.add(new SortBean(102, R.string.sport_core_training, false));
        }
        if (supportFunctionInfo.sport_type_traditional_strength_training) {
            arrayList.add(new SortBean(110, R.string.sport_traditional_strength_training, false));
        }
        if (supportFunctionInfo.sport_type3_strength_training) {
            arrayList.add(new SortBean(101, R.string.sport_strength_training, false));
        }
        if (supportFunctionInfo.HIIT) {
            arrayList.add(new SortBean(58, R.string.sport_hiit, false));
        }
        if (supportFunctionInfo.sport_type3_tidy_up_relax) {
            arrayList.add(new SortBean(104, R.string.sport_tidy_up_relax, false));
        }
        if (supportFunctionInfo.sport_type0_other) {
            arrayList.add(new SortBean(8, R.string.sport_free_exercise, false));
        }
        return arrayList;
    }

    public static List<Integer> getAllQuickAppList() {
        ArrayList arrayList = new ArrayList();
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (supportFunctionInfo != null && basicInfo != null) {
            arrayList.add(17);
            if (supportFunctionInfo.heartRate || supportFunctionInfo.ex_main4_v3_hr_data) {
                arrayList.add(2);
            }
            if (supportFunctionInfo.ex_main3_pressure || supportFunctionInfo.ex_main3_v3_pressure) {
                arrayList.add(13);
            }
            arrayList.add(16);
            if (isSupportSpO2()) {
                arrayList.add(18);
            }
            if (supportFunctionInfo.ex_table_main7_voice_transmission) {
                arrayList.add(20);
            }
            if (supportFunctionInfo.weather || supportFunctionInfo.V3_support_set_v3_weather) {
                arrayList.add(10);
            }
            if (supportFunctionInfo.bleControlMusic) {
                arrayList.add(6);
            }
        }
        return arrayList;
    }

    public static boolean isSupportWallpaper() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null && (basicInfo.deivceId == 335 || basicInfo.deivceId == 7035)) {
            return true;
        }
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return (supportFunctionInfo == null || supportFunctionInfo.V3_Veryfit_not_support_photo_wallpaper) ? false : true;
    }

    public static List<Integer> searchDevice() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(7264);
        arrayList.add(Integer.valueOf(GlMapUtil.DEVICE_DISPLAY_DPI_XHIGH));
        arrayList.add(7526);
        arrayList.add(Integer.valueOf(Constants.BindErrorCode.BIND_FAILED_CANCELLED));
        arrayList.add(481);
        arrayList.add(485);
        arrayList.add(7537);
        arrayList.add(7543);
        arrayList.add(7545);
        arrayList.add(7546);
        arrayList.add(7547);
        return arrayList;
    }

    public static List<Integer> isSupportBloodOxygen() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(7526);
        return arrayList;
    }

    public static boolean isSupportSpO2() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        return basicInfo == null || !isSupportBloodOxygen().contains(Integer.valueOf(basicInfo.deivceId));
    }
}