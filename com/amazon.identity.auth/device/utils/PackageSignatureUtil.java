package com.amazon.identity.auth.device.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class PackageSignatureUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String LOG_TAG = PackageSignatureUtil.class.getName();

    public static List<String> getAllSignaturesFor(String str, HashAlgorithm hashAlgorithm, Context context) {
        ArrayList arrayList = new ArrayList();
        Signature[] androidSignaturesFor = getAndroidSignaturesFor(str, context);
        if (androidSignaturesFor == null) {
            MAPLog.d(LOG_TAG, " appSignature is null. pkg=" + str);
            return arrayList;
        }
        MAPLog.i(LOG_TAG, "num sigs = " + androidSignaturesFor.length);
        for (Signature signature : androidSignaturesFor) {
            String signatureFingerprint = null;
            try {
                signatureFingerprint = getSignatureFingerprint(signature, hashAlgorithm);
                arrayList.add(signatureFingerprint.toLowerCase(Locale.US));
            } catch (Exception e2) {
                MAPLog.e(LOG_TAG, "Encountered error while finding signatures for " + str, e2);
            }
            MAPLog.pii(LOG_TAG, "Fingerprint checking", "fingerprint = " + signatureFingerprint);
        }
        return arrayList;
    }

    public static String getSignatureFingerprint(Signature signature, HashAlgorithm hashAlgorithm) throws NoSuchAlgorithmException, IOException, CertificateException {
        return MAPUtils.toHexString(getFingerprint(hashAlgorithm, SignatureUtil.getCertificateFromByteArray(signature.toByteArray()).getEncoded()));
    }

    private static Signature[] getAndroidSignaturesFor(String str, Context context) {
        PackageInfo packageInfo;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            MAPLog.d(LOG_TAG, "Can't find app signatures as pkgMgr is null ");
            return null;
        }
        try {
            packageInfo = packageManager.getPackageInfo(str, 64);
        } catch (PackageManager.NameNotFoundException unused) {
            MAPLog.d(LOG_TAG, "packageName not found for package " + str);
            packageInfo = null;
        }
        if (packageInfo == null) {
            MAPLog.d(LOG_TAG, "Can't find app signatures as pkgMgr is null ");
            return null;
        }
        return packageInfo.signatures;
    }

    private static byte[] getFingerprint(HashAlgorithm hashAlgorithm, byte[] bArr) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(hashAlgorithm.getAlgorithmName()).digest(bArr);
    }
}