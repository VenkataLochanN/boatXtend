package com.ido.ble.bluetooth.d;

import android.util.Log;
import com.ido.ble.logs.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4115a = "DFUServiceParser";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f4116b = 6;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f4117c = 2;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f4118d = "2148";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f4119e = "-289";

    public static boolean a(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        try {
            int length = bArr.length;
            int i = 0;
            while (i < length) {
                byte b2 = bArr[i];
                if (b2 == 0) {
                    return false;
                }
                int i2 = i + 1;
                byte b3 = bArr[i2];
                if (b3 == 6) {
                    return a(bArr, i2 + 1, b2 - 1);
                }
                if (b3 == 2) {
                    return b(bArr, i2 + 1, b2 - 1);
                }
                i = i2 + (b2 - 1) + 1;
            }
        } catch (Exception e2) {
            LogTool.b(f4115a, e2.getMessage());
        }
        return false;
    }

    private static boolean a(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        Log.d(f4115a, "StartPosition: " + i + " Data length: " + i2);
        if (bArr == null || bArr.length == 0 || (i3 = i + i2) < 4 || i3 - 4 > bArr.length) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Byte.toString(bArr[i3 - 3]));
        sb.append(Byte.toString(bArr[i4]));
        return sb.toString().equals(f4118d);
    }

    private static boolean b(byte[] bArr, int i, int i2) {
        Log.d(f4115a, "StartPosition: " + i + " Data length: " + i2);
        if (bArr == null || bArr.length == 0 || i2 < 2) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Byte.toString(bArr[i + 1]));
        sb.append(Byte.toString(bArr[i]));
        return sb.toString().equals(f4119e);
    }
}