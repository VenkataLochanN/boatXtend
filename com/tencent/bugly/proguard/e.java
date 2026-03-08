package com.tencent.bugly.proguard;

import com.realsil.sdk.dfu.DfuConstants;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f5681a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b2 = bArr[i];
            int i2 = i * 2;
            char[] cArr2 = f5681a;
            cArr[i2 + 1] = cArr2[b2 & DfuConstants.BANK_INFO_NOT_SUPPORTED];
            cArr[i2] = cArr2[((byte) (b2 >>> 4)) & DfuConstants.BANK_INFO_NOT_SUPPORTED];
        }
        return new String(cArr);
    }
}