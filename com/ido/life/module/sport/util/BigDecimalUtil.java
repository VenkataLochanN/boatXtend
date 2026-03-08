package com.ido.life.module.sport.util;

import java.math.BigDecimal;

/* JADX INFO: loaded from: classes2.dex */
public class BigDecimalUtil {
    private static final int DEF_DIV_SCALE = 10;

    public static double add(double d2, double d3) {
        return BigDecimal.valueOf(d2).add(BigDecimal.valueOf(d3)).doubleValue();
    }

    public static double sub(double d2, double d3) {
        return BigDecimal.valueOf(d2).subtract(BigDecimal.valueOf(d3)).doubleValue();
    }

    public static double mul(double d2, double d3) {
        return BigDecimal.valueOf(d2).multiply(BigDecimal.valueOf(d3)).doubleValue();
    }

    public static double div(double d2, double d3) {
        return div(d2, d3, 10);
    }

    public static double div(double d2, double d3, int i) {
        if (i < 0) {
            return 0.0d;
        }
        try {
            return BigDecimal.valueOf(d2).divide(BigDecimal.valueOf(d3), i, 4).doubleValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0.0d;
        }
    }

    public static double round(double d2, int i) throws IllegalAccessException {
        return div(d2, 1.0d, i);
    }

    public static boolean equalTo(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        return (bigDecimal == null || bigDecimal2 == null || bigDecimal.compareTo(bigDecimal2) != 0) ? false : true;
    }
}