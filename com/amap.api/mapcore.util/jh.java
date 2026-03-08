package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import java.io.ByteArrayOutputStream;

/* JADX INFO: compiled from: StatisticsHeaderDataStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class jh extends jj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f1462a = 13;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f1463b = 6;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Context f1464e;

    public jh(Context context, jj jjVar) {
        super(jjVar);
        this.f1464e = context;
    }

    @Override // com.amap.api.mapcore.util.jj
    protected byte[] a(byte[] bArr) {
        byte[] bArrA = a(this.f1464e);
        byte[] bArr2 = new byte[bArrA.length + bArr.length];
        System.arraycopy(bArrA, 0, bArr2, 0, bArrA.length);
        System.arraycopy(bArr, 0, bArr2, bArrA.length, bArr.length);
        return bArr2;
    }

    private byte[] a(Context context) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] byteArray = new byte[0];
        try {
            try {
                gt.a(byteArrayOutputStream, "1.2." + f1462a + "." + f1463b);
                gt.a(byteArrayOutputStream, "Android");
                gt.a(byteArrayOutputStream, gm.y(context));
                gt.a(byteArrayOutputStream, gm.n(context));
                gt.a(byteArrayOutputStream, gm.i(context));
                gt.a(byteArrayOutputStream, Build.MANUFACTURER);
                gt.a(byteArrayOutputStream, Build.MODEL);
                gt.a(byteArrayOutputStream, Build.DEVICE);
                gt.a(byteArrayOutputStream, gm.A(context));
                gt.a(byteArrayOutputStream, gi.c(context));
                gt.a(byteArrayOutputStream, gi.d(context));
                gt.a(byteArrayOutputStream, gi.f(context));
                byteArrayOutputStream.write(new byte[]{0});
                byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } catch (Throwable th2) {
            try {
                hn.c(th2, "sm", "gh");
                byteArrayOutputStream.close();
            } catch (Throwable th3) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
                throw th3;
            }
        }
        return byteArray;
    }
}