package com.realsil.sdk.dfu.utils;

import com.realsil.sdk.core.logger.ZLogger;

/* JADX INFO: loaded from: classes3.dex */
public class AesJni {
    public static final int MODE_AES_256 = 3;

    static {
        try {
            System.loadLibrary("AesJni");
        } catch (Exception e2) {
            e2.printStackTrace();
            ZLogger.e(e2.toString());
        }
    }

    public byte[] aesEncrypt(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        byte[] bArr3 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        try {
            aes_encrypt(bArr2, bArr3);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return bArr3;
    }

    public native boolean aesInit(int i, byte[] bArr);

    public native void aes_encrypt(byte[] bArr, byte[] bArr2);

    public void testJniCallback(int i, int i2) {
    }
}