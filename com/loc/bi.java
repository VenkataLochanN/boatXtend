package com.loc;

import android.content.Context;
import android.os.Build;
import java.io.ByteArrayOutputStream;

/* JADX INFO: compiled from: StatisticsHeaderDataStrategy.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bi extends bk {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f4860a = 13;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f4861b = 6;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Context f4862e;

    public bi(Context context, bk bkVar) {
        super(bkVar);
        this.f4862e = context;
    }

    private static byte[] a(Context context) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] byteArray = new byte[0];
        try {
            try {
                u.a(byteArrayOutputStream, "1.2." + f4860a + "." + f4861b);
                u.a(byteArrayOutputStream, "Android");
                u.a(byteArrayOutputStream, n.x(context));
                u.a(byteArrayOutputStream, n.m(context));
                u.a(byteArrayOutputStream, n.h(context));
                u.a(byteArrayOutputStream, Build.MANUFACTURER);
                u.a(byteArrayOutputStream, Build.MODEL);
                u.a(byteArrayOutputStream, Build.DEVICE);
                u.a(byteArrayOutputStream, n.A(context));
                u.a(byteArrayOutputStream, k.c(context));
                u.a(byteArrayOutputStream, k.d(context));
                u.a(byteArrayOutputStream, k.f(context));
                byteArrayOutputStream.write(new byte[]{0});
                byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                try {
                    ab.b(th, "sm", "gh");
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                    throw th2;
                }
            }
        } catch (Throwable th4) {
            th4.printStackTrace();
        }
        return byteArray;
    }

    @Override // com.loc.bk
    protected final byte[] a(byte[] bArr) {
        byte[] bArrA = a(this.f4862e);
        byte[] bArr2 = new byte[bArrA.length + bArr.length];
        System.arraycopy(bArrA, 0, bArr2, 0, bArrA.length);
        System.arraycopy(bArr, 0, bArr2, bArrA.length, bArr.length);
        return bArr2;
    }
}