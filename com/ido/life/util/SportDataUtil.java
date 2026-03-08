package com.ido.life.util;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.google.android.material.timepicker.TimeModel;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NumUtil;
import com.ido.common.utils.StringUtil;
import com.ido.life.module.sport.bean.TimeLineHeartRateItem;
import com.ido.life.module.user.sportrecord.model.SportChildItem;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SportDataUtil {
    private static final String TAG = "SportDataUtil";

    public static String getSportDistance(int i, boolean z) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float km2mile = UnitUtil.getKm2mile(i / 1000.0f);
        if (z) {
            return decimalFormat.format(Float.parseFloat(NumUtil.float2String(r3, 2)));
        }
        return decimalFormat.format(Float.parseFloat(NumUtil.float2String(km2mile, 2)));
    }

    public static boolean isRide(SportChildItem sportChildItem) {
        return sportChildItem.getType() == 3 || sportChildItem.getType() == 50 || sportChildItem.getType() == 51;
    }

    public static String getAvgSpace(SportChildItem sportChildItem) {
        String str;
        String strComputeTimePace;
        float distance = sportChildItem.getDistance() / 1000.0f;
        float km2mile = UnitUtil.getKm2mile(distance);
        Float fValueOf = Float.valueOf(0.0f);
        String str2 = StringUtil.format("%.2f", fValueOf);
        if (sportChildItem.getDistance() == 0) {
            return str2;
        }
        if (sportChildItem.getAvgPace() != 0) {
            strComputeTimePace = DateUtil.computeTimeMS(sportChildItem.getAvgPace());
            if ((isRide(sportChildItem) && !SportUnitUtil.isRideKm()) || (!isRide(sportChildItem) && !SportUnitUtil.isWalkOrRunKm())) {
                strComputeTimePace = changePeace2mile(sportChildItem.getAvgPace());
            }
        } else {
            if (sportChildItem.getDistance() >= 10) {
                str = StringUtil.format("%.2f", Float.valueOf((sportChildItem.getTotalSeconds() / 60.0f) / distance));
                if ((isRide(sportChildItem) && !SportUnitUtil.isRideKm()) || (!isRide(sportChildItem) && !SportUnitUtil.isWalkOrRunKm())) {
                    str = StringUtil.format("%.2f", Float.valueOf((sportChildItem.getTotalSeconds() / 60.0f) / km2mile));
                }
            } else {
                str = StringUtil.format("%.2f", fValueOf);
            }
            strComputeTimePace = DateUtil.computeTimePace(str);
        }
        if (!strComputeTimePace.contains("'")) {
            return strComputeTimePace;
        }
        try {
            return Integer.parseInt(strComputeTimePace.split("'")[0]) > 99 ? DateUtil.computeTimePace("99.99") : strComputeTimePace;
        } catch (Exception e2) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "getAvgPace: " + e2.toString());
            return strComputeTimePace;
        }
    }

    public static String getSportNameByType(int i) {
        if (i != 1) {
            if (i == 18) {
                return LanguageUtil.getLanguageText(R.string.sport_record_type_yoga);
            }
            if (i == 24) {
                return LanguageUtil.getLanguageText(R.string.sport_record_tennisball);
            }
            if (i == 29) {
                return LanguageUtil.getLanguageText(R.string.sport_record_dancing);
            }
            if (i == 32) {
                return LanguageUtil.getLanguageText(R.string.sport_pilates);
            }
            if (i == 35) {
                return LanguageUtil.getLanguageText(R.string.sport_zumba);
            }
            if (i == 75) {
                return LanguageUtil.getLanguageText(R.string.sport_record_cricket);
            }
            if (i == 104) {
                return LanguageUtil.getLanguageText(R.string.sport_tidy_up_relax);
            }
            if (i == 110) {
                return LanguageUtil.getLanguageText(R.string.sport_traditional_strength_training);
            }
            if (i == 21) {
                return LanguageUtil.getLanguageText(R.string.sport_record_basketball);
            }
            if (i == 22) {
                return LanguageUtil.getLanguageText(R.string.sport_record_socker);
            }
            if (i == 101) {
                return LanguageUtil.getLanguageText(R.string.sport_strength_training);
            }
            if (i != 102) {
                switch (i) {
                    case 4:
                        return LanguageUtil.getLanguageText(R.string.sport_record_walk_onfoot);
                    case 5:
                        return LanguageUtil.getLanguageText(R.string.mine_sport_swim);
                    case 6:
                        return LanguageUtil.getLanguageText(R.string.sport_record_climb);
                    case 7:
                        return LanguageUtil.getLanguageText(R.string.sport_record_badminton);
                    case 8:
                        return LanguageUtil.getLanguageText(R.string.sport_free_exercise);
                    case 9:
                        return LanguageUtil.getLanguageText(R.string.sport_record_fitness);
                    default:
                        switch (i) {
                            case 48:
                                return LanguageUtil.getLanguageText(R.string.sport_record_run_outdoor);
                            case 49:
                                return LanguageUtil.getLanguageText(R.string.sport_record_run_indoor);
                            case 50:
                                return LanguageUtil.getLanguageText(R.string.sport_record_ride_outdoor);
                            case 51:
                                return LanguageUtil.getLanguageText(R.string.sport_record_ride_indoor);
                            case 52:
                                return LanguageUtil.getLanguageText(R.string.sport_record_walk_outside);
                            case 53:
                                break;
                            case 54:
                                return LanguageUtil.getLanguageText(R.string.sport_record_pool_swimming);
                            case 55:
                                return LanguageUtil.getLanguageText(R.string.sport_record_open_swimming);
                            case 56:
                                return LanguageUtil.getLanguageText(R.string.sport_record_elliptical_machine);
                            case 57:
                                return LanguageUtil.getLanguageText(R.string.sport_record_rowing_machine);
                            case 58:
                                return LanguageUtil.getLanguageText(R.string.sport_hiit);
                            default:
                                return "";
                        }
                        break;
                }
            } else {
                return LanguageUtil.getLanguageText(R.string.sport_core_training);
            }
        }
        return LanguageUtil.getLanguageText(R.string.sport_record_walk_home);
    }

    public static List<TimeLineHeartRateItem> resovleHeartRate(String str) {
        int[] iArr = (int[]) GsonUtil.fromJson(str, int[].class);
        ArrayList arrayList = new ArrayList();
        if (iArr != null) {
            int i = 0;
            for (int i2 : iArr) {
                TimeLineHeartRateItem timeLineHeartRateItem = new TimeLineHeartRateItem();
                timeLineHeartRateItem.setHrTime(i);
                timeLineHeartRateItem.setHrValue(i2);
                arrayList.add(timeLineHeartRateItem);
                i += 5;
            }
        }
        return arrayList;
    }

    public static String getAvgSpeed(SportChildItem sportChildItem) {
        if (sportChildItem.getAvgSpeed() != 0) {
            return RunTimeUtil.getInstance().getUnitSet() == 2 ? changeSpeed2mile(sportChildItem.getAvgSpeed()) : formatAvgSpeed(sportChildItem.getAvgSpeed() / 100.0f);
        }
        return computeTimeSpeed(sportChildItem.getDistance(), sportChildItem.getTotalSeconds());
    }

    public static String computeTimeSpeed(int i, int i2) {
        float f2 = i / 1000.0f;
        float km2mile = UnitUtil.getKm2mile(f2);
        if (i < 10) {
            return StringUtil.format("%.1f", Float.valueOf(0.0f));
        }
        float f3 = i2 / 3600.0f;
        return RunTimeUtil.getInstance().getUnitSet() == 2 ? StringUtil.format("%.1f", Float.valueOf(km2mile / f3)) : StringUtil.format("%.1f", Float.valueOf(f2 / f3));
    }

    public static String formatAvgSpeed(float f2) {
        return f2 > 0.0f ? new DecimalFormat("0.00").format(f2) : "0.0";
    }

    public static String changeSpeed2mile(int i) {
        return formatAvgSpeed(((i / 100.0f) * 1000.0f) / 1609.0f);
    }

    public static String changePeace2mile(int i) {
        return DateUtil.computeTimeMS((int) ((i * 1609) / 1000.0f));
    }

    public static String computeTimePace(int i, int i2) {
        String str;
        float f2 = i / 1000.0f;
        float km2mile = UnitUtil.getKm2mile(f2);
        if (i >= 10) {
            StringBuilder sb = new StringBuilder();
            sb.append("computeTimePace: ");
            float f3 = i2 / 60.0f;
            float f4 = f3 / f2;
            sb.append(f4);
            CommonLogUtil.d(TAG, sb.toString());
            str = StringUtil.format("%.2f", Float.valueOf(f4));
            if (RunTimeUtil.getInstance().getUnitSet() == 2) {
                str = StringUtil.format("%.2f", Float.valueOf(f3 / km2mile));
            }
        } else {
            str = StringUtil.format("%.2f", Float.valueOf(0.0f));
        }
        String strComputeTimePace = DateUtil.computeTimePace(str);
        return "00'00''".equals(strComputeTimePace) ? AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE : strComputeTimePace;
    }

    public static String computeTimePace(String str) {
        if (str == null) {
            str = "";
        }
        if (str.contains(".")) {
            String[] strArrSplit = str.split("\\.");
            return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(Integer.parseInt(strArrSplit[0]))) + "'" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf((Integer.parseInt(strArrSplit[1]) * 60) / 100)) + "''";
        }
        if (str.contains(AppInfo.DELIM)) {
            String[] strArrSplit2 = str.split(AppInfo.DELIM);
            return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(Integer.parseInt(strArrSplit2[0]))) + "'" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf((Integer.parseInt(strArrSplit2[1]) * 60) / 100)) + "''";
        }
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, 0) + "'" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, 0) + "''";
    }

    public static String getSpeedByPeace(int i) {
        StringUtil.format("%.1f", Float.valueOf(0.0f));
        float f2 = i;
        return RunTimeUtil.getInstance().getUnitSet() == 2 ? StringUtil.format("%.1f", Float.valueOf((UnitUtil.getKm2mile(1.0f) / f2) * 3600.0f)) : StringUtil.format("%.1f", Float.valueOf((1.0f / f2) * 3600.0f));
    }

    public static String getSpeedMiByPeaceMi(int i) {
        StringUtil.format("%.1f", Float.valueOf(0.0f));
        return StringUtil.format("%.1f", Float.valueOf((1.0f / i) * 3600.0f));
    }

    public static String formatPeace(int i) {
        if (RunTimeUtil.getInstance().getUnitSet() == 2) {
            return changePeace2mile(i);
        }
        return DateUtil.computeTimeMS(i);
    }
}