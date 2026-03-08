package com.amap.api.mapcore.util;

import com.realsil.sdk.bbpro.core.protocol.params.Mmi;
import com.realsil.sdk.bbpro.core.protocol.params.Parameters;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: Encrypt.java */
/* JADX INFO: loaded from: classes.dex */
public final class kb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f1513a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final byte[] f1514b = {61, 61, 81, 65, 65, 69, 119, 65, 67, Mmi.AU_MMI_VOL_UP, 74, 80, 115, Mmi.AU_MMI_RWS_SWITCH_CHANNEL, 54, 75, 104, 76, 122, 97, 88, 99, 53, 71, Mmi.AU_MMI_VOL_DOWN, 122, 68, 70, 79, 104, 113, 113, 65, 97, 76, 54, 65, 66, 87, 53, 103, 85, 84, 113, 71, 68, 69, 76, 80, 82, 106, Parameters.RWS_CHANNEL_2, 66, 75, 75, 69, 98, 55, 84, 108, 115, 122, Parameters.RWS_CHANNEL_2, 106, 76, 55, 88, 122, 70, 121, 73, 75, 52, 50, 43, Mmi.AU_MMI_AUDIO_PASS_THROUGH, 70, 121, 56, 105, 115, 105, 89, 120, 117, 112, 53, Mmi.AU_MMI_VOL_UP, 76, 81, 70, 86, 108, 110, 73, 65, 66, 74, 65, 83, 119, 65, 119, 83, 68, 65, 81, 66, 66, 69, 81, 65, 78, 99, 118, 104, 73, 90, 111, 75, 74, 89, 81, 68, 119, 119, 70, 77};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final byte[] f1515c = {0, 1, 1, 2, 3, 5, 8, 13, 8, 7, 6, 5, 4, 3, 2, 1};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final IvParameterSpec f1516d = new IvParameterSpec(f1515c);

    public static byte[] a(byte[] bArr) {
        try {
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[bArr.length - 16];
            System.arraycopy(bArr, 0, bArr2, 0, 16);
            System.arraycopy(bArr, 16, bArr3, 0, bArr.length - 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, new IvParameterSpec(gt.c()));
            return cipher.doFinal(bArr3);
        } catch (Throwable th) {
            kg.a(th, "Encrypt", "decryptRsponse length = " + (bArr != null ? bArr.length : 0));
            return null;
        }
    }
}