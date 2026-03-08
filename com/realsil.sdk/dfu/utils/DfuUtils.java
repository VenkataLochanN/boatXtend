package com.realsil.sdk.dfu.utils;

import com.ido.alexa.AlexaCustomSkillConstant;
import java.util.Locale;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public class DfuUtils {
    public static int IMAGE_VERSION_FORMAT;
    public static final int IMAGE_VERSION_FORMAT_AUTO = 0;

    public static int binarySearch(int[] iArr, int i) {
        if (iArr == null || iArr.length <= 0) {
            return -1;
        }
        int i2 = 0;
        int length = iArr.length - 1;
        while (i2 <= length) {
            int i3 = (i2 + length) >>> 1;
            int i4 = iArr[i3];
            if (i4 < i) {
                i2 = i3 + 1;
            } else {
                if (i4 <= i) {
                    return i3;
                }
                length = i3 - 1;
            }
        }
        return ~i2;
    }

    public static String convertVersion2Str(int i, int i2) {
        return convertVersion2Str(0, i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x010a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String convertVersion2Str(int r9, int r10, int r11) {
        /*
            Method dump skipped, instruction units count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.utils.DfuUtils.convertVersion2Str(int, int, int):java.lang.String");
    }

    public static String formatBatteryLevel(int i) {
        return String.format(Locale.US, "%d%%", Integer.valueOf(i));
    }

    public static String formatImageVersionWithBinId(int i, int i2, int i3, int i4) {
        return convertVersion2Str(i3, i4, getImageVersionFormatWithBinId(i, i2));
    }

    public static String formatImageVersionWithBitNumber(int i, int i2, int i3, int i4) {
        return convertVersion2Str(i3, i4, getImageVersionFormatWithBitNumber(i, i2));
    }

    public static String formatLinkKey(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append((bArr[i] & UByte.MAX_VALUE) <= 15 ? AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + Integer.toHexString(bArr[i] & UByte.MAX_VALUE).toUpperCase() : Integer.toHexString(bArr[i] & UByte.MAX_VALUE).toUpperCase());
            if (i < length - 1) {
                sb.append(":");
            }
        }
        return sb.toString();
    }

    public static String formatManufacturerAddr(String str) {
        char[] charArray = str.toCharArray();
        char[] cArr = new char[17];
        int i = 0;
        int i2 = 0;
        while (i < 17) {
            int i3 = i + 1;
            if (i3 % 3 == 0) {
                cArr[i] = ':';
            } else {
                cArr[i] = charArray[i2];
                i2++;
            }
            i = i3;
        }
        return String.valueOf(cArr);
    }

    public static int getBatteryLevel(int i) {
        if (i >= 80) {
            return 5;
        }
        if (i >= 60) {
            return 4;
        }
        if (i >= 40) {
            return 3;
        }
        if (i >= 20) {
            return 2;
        }
        return i >= 1 ? 1 : 0;
    }

    public static int getControlSpeed(int i) {
        switch (i) {
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getImageVersionFormatWithBinId(int r8, int r9) {
        /*
            r0 = 2
            r1 = 1
            r2 = 5
            r3 = 3
            if (r8 > r3) goto L8
            r2 = 7
            goto L3c
        L8:
            r4 = 2048(0x800, float:2.87E-42)
            r5 = 768(0x300, float:1.076E-42)
            r6 = 512(0x200, float:7.17E-43)
            if (r8 == r2) goto L2e
            r7 = 9
            if (r8 != r7) goto L15
            goto L2e
        L15:
            if (r9 == r6) goto L3b
            if (r9 == r5) goto L3c
            r2 = 1024(0x400, float:1.435E-42)
            if (r9 == r2) goto L2c
            r2 = 1040(0x410, float:1.457E-42)
            if (r9 == r2) goto L2a
            r2 = 1280(0x500, float:1.794E-42)
            if (r9 == r2) goto L2a
            r2 = 1538(0x602, float:2.155E-42)
            if (r9 == r2) goto L2a
            goto L39
        L2a:
            r2 = 6
            goto L3c
        L2c:
            r2 = r0
            goto L3c
        L2e:
            if (r9 == r6) goto L3b
            if (r9 == r5) goto L3b
            r2 = 1792(0x700, float:2.511E-42)
            if (r9 == r2) goto L3b
            if (r9 == r4) goto L39
            goto L3b
        L39:
            r2 = r1
            goto L3c
        L3b:
            r2 = r3
        L3c:
            java.util.Locale r4 = java.util.Locale.US
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r5 = 0
            r3[r5] = r8
            java.lang.Integer r8 = java.lang.Integer.valueOf(r9)
            r3[r1] = r8
            java.lang.Integer r8 = java.lang.Integer.valueOf(r2)
            r3[r0] = r8
            java.lang.String r8 = "icType=%d, binId=0x%04X, format=%d"
            java.lang.String r8 = java.lang.String.format(r4, r8, r3)
            com.realsil.sdk.core.logger.ZLogger.v(r8)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.utils.DfuUtils.getImageVersionFormatWithBinId(int, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0016  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getImageVersionFormatWithBitNumber(int r6, int r7) {
        /*
            r0 = 1
            r1 = 2
            r2 = 5
            r3 = 3
            if (r6 > r3) goto L8
            r2 = 7
            goto L2a
        L8:
            if (r6 == r2) goto L1a
            r4 = 9
            if (r6 != r4) goto Lf
            goto L1a
        Lf:
            switch(r7) {
                case 0: goto L29;
                case 1: goto L29;
                case 2: goto L18;
                case 3: goto L27;
                case 4: goto L27;
                case 5: goto L2a;
                case 6: goto L16;
                case 7: goto L16;
                case 8: goto L16;
                case 9: goto L18;
                default: goto L12;
            }
        L12:
            switch(r7) {
                case 19: goto L27;
                case 20: goto L27;
                case 21: goto L2a;
                case 22: goto L16;
                case 23: goto L16;
                case 24: goto L16;
                default: goto L15;
            }
        L15:
            goto L18
        L16:
            r2 = 6
            goto L2a
        L18:
            r2 = r0
            goto L2a
        L1a:
            if (r7 == r1) goto L29
            if (r7 == r3) goto L27
            r4 = 4
            if (r7 == r4) goto L27
            if (r7 == r2) goto L27
            r2 = 18
            if (r7 == r2) goto L29
        L27:
            r2 = r3
            goto L2a
        L29:
            r2 = r1
        L2a:
            java.util.Locale r4 = java.util.Locale.US
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r5 = 0
            r3[r5] = r6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)
            r3[r0] = r6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)
            r3[r1] = r6
            java.lang.String r6 = "icType=%d, bitNumber=%d, format=%d"
            java.lang.String r6 = java.lang.String.format(r4, r6, r3)
            com.realsil.sdk.core.logger.ZLogger.v(r6)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.utils.DfuUtils.getImageVersionFormatWithBitNumber(int, int):int");
    }

    public static int getImageVersionFormatWithIc(int i) {
        if (i <= 3) {
            return 7;
        }
        return (i == 5 || i == 9) ? 3 : 5;
    }

    public static int getRecommendBuffercheckLevel(int i) {
        if (i <= 3) {
            return 16;
        }
        if (i == 5 || i == 9) {
            return 1;
        }
        if (i == 4 || i == 6 || i == 7 || i == 8) {
        }
        return 16;
    }
}