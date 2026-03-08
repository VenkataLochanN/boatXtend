package com.amap.api.mapcore.util;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* JADX INFO: compiled from: RSAUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class hb {
    public static byte[] a(byte[] bArr, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(gt.c("CUlNBL0VDQi9QS0NTMVBhZGRpbmc"));
            cipher.init(1, publicKey);
            return cipher.doFinal(bArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static PublicKey a(String str) throws Exception {
        try {
            return KeyFactory.getInstance(gt.c("EUlNB")).generatePublic(new X509EncodedKeySpec(gx.a(str)));
        } catch (NullPointerException unused) {
            throw new Exception("公钥数据为空");
        } catch (NoSuchAlgorithmException unused2) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException unused3) {
            throw new Exception("公钥非法");
        }
    }
}