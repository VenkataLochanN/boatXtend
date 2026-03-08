package com.ido.ble.bluetooth.d;

import com.bumptech.glide.load.Key;
import com.ido.ble.bluetooth.e.f;
import com.ido.ble.common.c;
import com.ido.ble.logs.LogTool;
import java.io.UnsupportedEncodingException;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4120a = "ScannerServiceParser";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f4121b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f4122c = 2;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int f4123d = 3;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int f4124e = 4;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final int f4125f = 5;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int f4126g = 6;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final int f4127h = 7;
    private static final int i = 8;
    private static final int j = 9;
    private static final byte k = 1;
    private static final byte l = 2;

    private static int a(byte[] bArr, int i2) {
        return ((bArr[i2 + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i2] & UByte.MAX_VALUE) << 0);
    }

    public static String a(byte[] bArr) {
        int i2;
        try {
            int length = bArr.length;
            while (i2 < length) {
                byte b2 = bArr[i2];
                if (b2 == 0) {
                    break;
                }
                int i3 = i2 + 1;
                byte b3 = bArr[i3];
                i2 = (b3 == 9 || b3 == 8) ? 0 : i3 + (b2 - 1) + 1;
                return a(bArr, i3 + 1, b2 - 1);
            }
        } catch (Exception e2) {
            LogTool.b(f4120a, e2.getMessage());
        }
        return null;
    }

    public static String a(byte[] bArr, int i2, int i3) {
        try {
            return new String(bArr, i2, i3, Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException | IndexOutOfBoundsException unused) {
            return null;
        }
    }

    private static boolean a(byte[] bArr, String[] strArr) {
        if (bArr == null) {
            return false;
        }
        try {
            int length = bArr.length;
            int i2 = 0;
            boolean z = false;
            boolean z2 = false;
            while (i2 < length) {
                byte b2 = bArr[i2];
                if (b2 == 0) {
                    return z && z2;
                }
                int i3 = i2 + 1;
                byte b3 = bArr[i3];
                if (b3 == 2 || b3 == 3) {
                    for (int i4 = i3 + 1; i4 < (i3 + b2) - 1; i4 += 2) {
                        z2 = z2 || b(strArr, bArr, i4, 2);
                    }
                } else if (b3 == 4 || b3 == 5) {
                    for (int i5 = i3 + 1; i5 < (i3 + b2) - 1; i5 += 4) {
                        z2 = z2 || c(strArr, bArr, i5, 4);
                    }
                } else if (b3 == 6 || b3 == 7) {
                    for (int i6 = i3 + 1; i6 < (i3 + b2) - 1; i6 += 16) {
                        z2 = z2 || a(strArr, bArr, i6, 16);
                    }
                }
                if (b3 == 1) {
                    z = (bArr[i3 + 1] & 3) > 0;
                }
                i2 = i3 + (b2 - 1) + 1;
            }
            return z && z2;
        } catch (Exception e2) {
            LogTool.b(f4120a, e2.getMessage());
            return false;
        }
    }

    private static boolean a(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str.equalsIgnoreCase(str2.substring(4, 8))) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(String[] strArr, byte[] bArr, int i2, int i3) {
        return a(strArr, String.format("%04x", Integer.valueOf(a(bArr, (i2 + i3) - 4))));
    }

    private static boolean b(String[] strArr, byte[] bArr, int i2, int i3) {
        return a(strArr, String.format("%04x", Integer.valueOf(a(bArr, i2))));
    }

    public static byte[] b(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            byte[] bArr3 = new byte[62];
            int length = bArr.length;
            int i2 = 0;
            while (i2 < length) {
                int i3 = bArr[i2];
                if (i3 == 0) {
                    return null;
                }
                int i4 = i2 + 1;
                if (bArr[i4] == -1) {
                    int i5 = i3 - 1;
                    c.a(bArr, bArr3, i4 + 1, i5);
                    bArr2 = new byte[i5];
                    c.a(bArr3, bArr2, 0, i5);
                    return bArr2;
                }
                i2 = i4 + (i3 - 1) + 1;
            }
            return null;
        } catch (Exception e2) {
            LogTool.b(f4120a, e2.getMessage());
            return bArr2;
        }
    }

    public static boolean c(byte[] bArr) {
        return a(bArr, new String[]{f.f4138d.toString(), f.f4139e.toString()});
    }

    private static boolean c(String[] strArr, byte[] bArr, int i2, int i3) {
        return a(strArr, String.format("%04x", Integer.valueOf(a(bArr, (i2 + i3) - 4))));
    }

    public static boolean d(byte[] bArr) {
        return a(bArr, new String[]{f.m.toString(), f.n.toString()});
    }
}