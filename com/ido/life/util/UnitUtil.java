package com.ido.life.util;

import android.app.Activity;
import android.content.res.Resources;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.utils.NumUtil;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes3.dex */
public class UnitUtil {
    static int BRITISH = 2;
    public static final float KG_UNIT = 0.45359236f;
    static int METRIC = 1;
    public static final float POUND_UNIT = 2.2046225f;
    private static final String TAG = "UnitUtil";

    public static float cm2Inch(int i) {
        return (i * 1.0f) / 30.48f;
    }

    public static int ft2in(int i) {
        return i * 12;
    }

    public static float getKg2Pound(float f2) {
        return f2 * 2.2046225f;
    }

    public static float getKg2St(float f2) {
        return f2 * 0.157f;
    }

    public static int getMode() {
        return 0;
    }

    public static int getPaceInt(int i, int i2) {
        return i2;
    }

    public static float getPound2Kg(float f2) {
        return f2 * 0.45359236f;
    }

    public static float getPound2St(float f2) {
        return f2 * 0.07f;
    }

    public static float getSt2Kg(float f2) {
        return f2 * 6.35f;
    }

    public static float getSt2Lb(float f2) {
        return f2 * 14.0f;
    }

    public static String getUnitByType(Activity activity) {
        return "";
    }

    public static float inch2CmF(float f2) {
        return f2 * 30.48f;
    }

    public static float km2mile(float f2) {
        return f2 * 0.6213712f;
    }

    public static float mile2km(float f2) {
        return f2 * 1.609344f;
    }

    public static int st2lb(int i) {
        return i * 14;
    }

    public static int newInch2cm(int[] iArr) {
        return inch2cm(iArr);
    }

    public static int newCm2inch(int[] iArr) {
        return Math.round((iArr[0] + iArr[1]) / 3.0172415f);
    }

    public static int inch2cm(int[] iArr) {
        iArr[1] = (iArr[0] * 12) + iArr[1];
        return Math.round(iArr[1] * 2.54f);
    }

    public static int inch2cm(float f2) {
        return Math.round(f2 * 2.54f);
    }

    public static int inch2inch(int[] iArr) {
        return (iArr[0] * 12) + iArr[1];
    }

    public static float inch2foot(int[] iArr) {
        return iArr[0] + (((iArr[1] * 100.0f) / 12.0f) / 100.0f);
    }

    public static int[] inch2inch(int i) {
        return new int[]{i / 12, i % 12};
    }

    public static int inchs2cm(float f2) {
        return Math.round(f2 * 2.54f);
    }

    public static int[] cm2inch(int i) {
        int[] iArr = new int[2];
        iArr[1] = cm2inchs(i);
        iArr[0] = iArr[1] / 12;
        iArr[1] = iArr[1] % 12;
        return iArr;
    }

    public static int cm2inchs(int i) {
        return Math.round(i / 2.54f);
    }

    public static float kg2lb(float f2) {
        return Math.round(f2 * 2.2046225f);
    }

    public static float lb2kg(float f2) {
        return Math.round(f2 * 0.4535924f);
    }

    public static int kg2st(int i) {
        return (int) Math.round(((double) i) * 0.157d);
    }

    public static int lb2st(int i) {
        return (int) Math.round(((double) i) * 0.071d);
    }

    public static int st2kg(int i) {
        return (int) Math.round(((double) i) * 6.35d);
    }

    public static String getUnitByType(Activity activity, int i) {
        return getUnitByType(activity);
    }

    public static String getUnitByType() {
        return getUnitByType(null);
    }

    public static String getUnit(Resources resources, int i) {
        return getUnitByType(null);
    }

    public static float getKm2mile(float f2) {
        return km2mile(f2);
    }

    public static float getmile2Km(float f2) {
        return mile2km(f2);
    }

    public static float getWeight(String str) {
        return NumUtil.parseFloat(String.valueOf(new BigDecimal(str).setScale(0, 4)));
    }

    public static String getUnitStr(int i, int i2) {
        if (i2 == METRIC) {
            return String.valueOf(i);
        }
        int[] iArrInch2inch = inch2inch(i);
        return iArrInch2inch[0] + "'" + iArrInch2inch[1] + "\"";
    }

    public static String getPaceStr(int i, int i2) {
        return DateUtil.computeTimeMS(getPaceInt(i, i2));
    }

    public static String getUnitStr() {
        return IdoApp.getAppContext().getResources().getString(R.string.me_all_ke_me_ios);
    }

    public static int inch2Cm(float f2) {
        return Math.round(f2 * 30.48f);
    }
}