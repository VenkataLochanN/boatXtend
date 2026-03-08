package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: compiled from: HeaderAddStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class jf extends jj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1456a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f1457b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private id f1458e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Object[] f1459f;

    public jf(Context context, jj jjVar, id idVar, String str, Object... objArr) {
        super(jjVar);
        this.f1456a = context;
        this.f1457b = str;
        this.f1458e = idVar;
        this.f1459f = objArr;
    }

    @Override // com.amap.api.mapcore.util.jj
    protected byte[] a(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException {
        String strA = gt.a(bArr);
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return gt.a("{\"pinfo\":\"" + b(this.f1456a) + "\",\"els\":[" + strA + "]}");
    }

    private String a(Context context) {
        try {
            return String.format(gt.c(this.f1457b), this.f1459f);
        } catch (Throwable th) {
            th.printStackTrace();
            hn.c(th, "ofm", "gpj");
            return "";
        }
    }

    private String b(Context context) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException {
        return gt.a(this.f1458e.b(gt.a(a(context))));
    }
}