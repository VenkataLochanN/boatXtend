package com.ido.common.utils;

import java.math.BigDecimal;

/* JADX INFO: loaded from: classes2.dex */
public class FileSizeUtil {
    private static final int BASE_B = 1;
    private static final int BASE_GB = 1073741824;
    private static final int BASE_KB = 1024;
    private static final int BASE_MB = 1048576;
    public static final String UNIT_BIT = "B";
    public static final String UNIT_GB = "G";
    public static final String UNIT_KB = "K";
    public static final String UNIT_MB = "M";

    public static String byteConvert(long j) {
        double d2 = j;
        if (d2 / 1.048576E9d >= 1.0d) {
            return new BigDecimal(d2 / 1.073741824E9d).setScale(2, 1).doubleValue() + "GB";
        }
        if (d2 / 1024000.0d >= 1.0d) {
            return new BigDecimal(d2 / 1048576.0d).setScale(1, 1).doubleValue() + "MB";
        }
        if (d2 / 1000.0d >= 1.0d) {
            return new BigDecimal(d2 / 1024.0d).setScale(1, 1).doubleValue() + "KB";
        }
        return j + UNIT_BIT;
    }

    public static String[] convertSpeeds(long j, int i) {
        String[] strArr = new String[2];
        String strConvertFileSize = convertFileSize(j, 0);
        String strSubstring = strConvertFileSize.substring(strConvertFileSize.length() - 1);
        strArr[0] = strConvertFileSize.substring(0, strConvertFileSize.lastIndexOf(strSubstring));
        if (strSubstring.equals(UNIT_BIT)) {
            strArr[1] = strSubstring + "/s";
        } else {
            strArr[1] = strSubstring + "B/s";
        }
        return strArr;
    }

    public static String convertPercent(float f2, int i, String str) {
        float fFloatValue = new BigDecimal(f2 * 100.0f).divide(new BigDecimal(1), i, 4).floatValue();
        return Float.compare(fFloatValue, (float) Math.pow(10.0d, (double) (-i))) < 0 ? str : String.valueOf(fFloatValue);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String convertFileSize(long r11, int r13) {
        /*
            double r0 = (double) r11
            r2 = 0
            r3 = r2
        L3:
            r4 = 1000(0x3e8, double:4.94E-321)
            long r11 = r11 / r4
            r4 = 0
            int r4 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r4 <= 0) goto Lf
            int r3 = r3 + 1
            goto L3
        Lf:
            java.lang.String r11 = "G"
            r12 = 1073741824(0x40000000, float:2.0)
            java.lang.String r4 = "K"
            java.lang.String r5 = "B"
            r6 = 4
            r7 = 2
            java.lang.String r8 = "M"
            r9 = 1
            if (r3 == 0) goto L32
            if (r3 == r9) goto L2e
            if (r3 == r7) goto L2a
            r10 = 3
            if (r3 == r10) goto L34
            if (r3 == r6) goto L34
            r11 = r8
        L28:
            r12 = r9
            goto L34
        L2a:
            r12 = 1048576(0x100000, float:1.469368E-39)
            r11 = r8
            goto L34
        L2e:
            r12 = 1024(0x400, float:1.435E-42)
            r11 = r4
            goto L34
        L32:
            r11 = r5
            goto L28
        L34:
            java.math.BigDecimal r3 = new java.math.BigDecimal
            r3.<init>(r0)
            java.math.BigDecimal r0 = new java.math.BigDecimal
            r0.<init>(r12)
            java.math.BigDecimal r12 = r3.divide(r0, r13, r6)
            double r0 = r12.doubleValue()
            java.lang.String r12 = java.lang.Double.toString(r0)
            r0 = -1
            r1 = 46
            if (r13 != 0) goto L79
            int r13 = r12.indexOf(r1)
            if (r0 != r13) goto L65
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r12)
            r13.append(r11)
            java.lang.String r11 = r13.toString()
            return r11
        L65:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r12 = r12.substring(r2, r13)
            r0.append(r12)
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            return r11
        L79:
            boolean r13 = r11.equals(r5)
            if (r13 == 0) goto L87
            int r13 = r12.indexOf(r1)
            java.lang.String r12 = r12.substring(r2, r13)
        L87:
            boolean r13 = r11.equals(r4)
            if (r13 == 0) goto Laa
            int r13 = r12.indexOf(r1)
            if (r13 == r0) goto L99
            int r13 = r13 + r7
            java.lang.String r12 = r12.substring(r2, r13)
            goto Laa
        L99:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r12)
            java.lang.String r12 = ".0"
            r13.append(r12)
            java.lang.String r12 = r13.toString()
        Laa:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r12)
            r13.append(r11)
            java.lang.String r11 = r13.toString()
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.common.utils.FileSizeUtil.convertFileSize(long, int):java.lang.String");
    }

    public static String getFormatSize(double d2) {
        double d3 = d2 / 1024.0d;
        if (d3 < 1.0d) {
            return d2 + UNIT_BIT;
        }
        double d4 = d3 / 1024.0d;
        if (d4 < 1.0d) {
            return new BigDecimal(Double.toString(d3)).setScale(2, 4).toPlainString() + UNIT_KB;
        }
        double d5 = d4 / 1024.0d;
        if (d5 < 1.0d) {
            return new BigDecimal(Double.toString(d4)).setScale(2, 4).toPlainString() + UNIT_MB;
        }
        double d6 = d5 / 1024.0d;
        if (d6 < 1.0d) {
            return new BigDecimal(Double.toString(d5)).setScale(2, 4).toPlainString() + UNIT_GB;
        }
        return new BigDecimal(d6).setScale(2, 4).toPlainString() + "T";
    }
}