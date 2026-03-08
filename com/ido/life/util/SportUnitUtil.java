package com.ido.life.util;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NumUtil;
import com.ido.life.database.model.SportItemPace;
import com.ido.life.log.SportLogHelper;
import java.text.DecimalFormat;

/* JADX INFO: loaded from: classes3.dex */
public class SportUnitUtil {
    private static final String TAG = "SportUnitUtil";

    public static int getCarieValue(int i) {
        return i;
    }

    public static boolean isSwimYard() {
        return false;
    }

    public static int miToYard(int i) {
        return (int) (((double) i) * 1.0936d);
    }

    public static boolean isRideKm() {
        return RunTimeUtil.getInstance().getUnitSet() == 1;
    }

    public static boolean isWalkOrRunKm() {
        return RunTimeUtil.getInstance().getUnitSet() == 1;
    }

    public static String getCalorieUnit() {
        return LanguageUtil.getLanguageText(R.string.public_heat_calorie);
    }

    public static String getCalorieUnitDesc() {
        return LanguageUtil.getLanguageText(R.string.sport_record_total_category);
    }

    public static String getShowDistance(boolean z, int i) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float km2mile = UnitUtil.getKm2mile(i / 1000.0f);
        if (z) {
            return decimalFormat.format(Float.parseFloat(NumUtil.float2String(r4, 2)));
        }
        return decimalFormat.format(Float.parseFloat(NumUtil.float2String(km2mile, 2)));
    }

    public static String getPaceUnit(boolean z) {
        if (z) {
            return LanguageUtil.getLanguageText(R.string.sport_run_distance_unit);
        }
        return LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi);
    }

    public static String getMaxPace(int i, boolean z, int i2, int i3, SportItemPace sportItemPace) {
        String strComputeTimeMS = DateUtil.computeTimeMS(0);
        if (i != 0) {
            strComputeTimeMS = DateUtil.computeTimeMS(i);
            if (!z) {
                strComputeTimeMS = SportDataUtil.changePeace2mile(i);
            }
        } else if (sportItemPace != null) {
            String metricItems = z ? sportItemPace.getMetricItems() : sportItemPace.getBritishItems();
            int[] iArr = TextUtils.isEmpty(metricItems) ? null : (int[]) GsonUtil.fromJson(metricItems, int[].class);
            if (iArr != null && iArr.length > 0) {
                int iMin = 0;
                int iMax = 0;
                for (int i4 = 0; i4 < iArr.length; i4++) {
                    if (i4 == 0) {
                        iMin = iArr[0];
                    }
                    if (iArr[i4] != 0) {
                        iMin = Math.min(iMin, iArr[i4]);
                    }
                    iMax = Math.max(iMax, iArr[i4]);
                }
                strComputeTimeMS = DateUtil.computeTimeMS(iMin);
            }
        }
        if ((TextUtils.isEmpty(strComputeTimeMS) || strComputeTimeMS.equals("0'0''")) && i2 != 0 && i3 != 0) {
            strComputeTimeMS = SportDataUtil.computeTimePace(i2, i3);
        }
        if (!strComputeTimeMS.contains("'")) {
            return strComputeTimeMS;
        }
        try {
            return Integer.parseInt(strComputeTimeMS.split("'")[0]) > 99 ? DateUtil.computeTimePace("99.99") : strComputeTimeMS;
        } catch (Exception e2) {
            SportLogHelper.saveSportLog(TAG, "getMaxPace: " + e2.toString());
            return strComputeTimeMS;
        }
    }

    public static String getAvgPace(int i, int i2, int i3, boolean z) {
        String strComputeTimePace;
        if (i == 0) {
            return DateUtil.computeTimeMS(i);
        }
        if (i3 != 0) {
            strComputeTimePace = DateUtil.computeTimeMS(i3);
            if (!z) {
                strComputeTimePace = SportDataUtil.changePeace2mile(i3);
            }
        } else {
            strComputeTimePace = SportDataUtil.computeTimePace(i, i2);
        }
        if (!strComputeTimePace.contains("'")) {
            return strComputeTimePace;
        }
        try {
            return Integer.parseInt(strComputeTimePace.split("'")[0]) > 99 ? DateUtil.computeTimePace("99.99") : strComputeTimePace;
        } catch (Exception e2) {
            SportLogHelper.saveSportLog(TAG, "getAvgPace: " + e2.toString());
            return strComputeTimePace;
        }
    }

    public static String getSpeedUnit(boolean z) {
        if (z) {
            return LanguageUtil.getLanguageText(R.string.sport_detail_speed_unit);
        }
        return LanguageUtil.getLanguageText(R.string.sport_detail_speed_unit_mi);
    }

    public static String getAvgSpeed(int i, int i2, int i3, boolean z) {
        if (i3 == 0) {
            return SportDataUtil.computeTimeSpeed(i, i2);
        }
        if (!z) {
            return SportDataUtil.changeSpeed2mile(i3);
        }
        return SportDataUtil.formatAvgSpeed(i3 / 100.0f);
    }

    public static String getMaxSpeed(int i, int i2, int i3, boolean z) {
        if (i3 == 0) {
            return SportDataUtil.computeTimeSpeed(i, i2);
        }
        if (!z) {
            return SportDataUtil.changeSpeed2mile(i3);
        }
        return SportDataUtil.formatAvgSpeed(i3 / 100.0f);
    }

    public static String getRangeUnit(boolean z) {
        LanguageUtil.getLanguageText(R.string.public_unit_cm);
        if (z) {
            return LanguageUtil.getLanguageText(R.string.public_unit_cm);
        }
        return LanguageUtil.getLanguageText(R.string.public_unit_inch);
    }
}