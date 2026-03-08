package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.AMapException;
import java.io.ByteArrayOutputStream;

/* JADX INFO: compiled from: StatisticsEntity.java */
/* JADX INFO: loaded from: classes.dex */
public class ja {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1437a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f1438b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f1439c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f1440d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f1441e;

    public byte[] a(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public ja(Context context, String str, String str2, String str3) throws gh {
        if (TextUtils.isEmpty(str3) || str3.length() > 256) {
            throw new gh(AMapException.ERROR_INVALID_PARAMETER);
        }
        this.f1437a = context.getApplicationContext();
        this.f1439c = str;
        this.f1440d = str2;
        this.f1438b = str3;
    }

    public void a(String str) throws gh {
        if (TextUtils.isEmpty(str) || str.length() > 65536) {
            throw new gh(AMapException.ERROR_INVALID_PARAMETER);
        }
        this.f1441e = str;
    }

    public byte[] b(String str) {
        byte[] bArrA;
        if (!TextUtils.isEmpty(str) && (bArrA = gt.a(this.f1441e)) != null) {
            return gt.a(bArrA.length, 2);
        }
        return new byte[]{0, 0};
    }

    public byte[] a() {
        ByteArrayOutputStream byteArrayOutputStream;
        int iCurrentTimeMillis = 0;
        byte[] byteArray = new byte[0];
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = null;
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        try {
            gt.a(byteArrayOutputStream, this.f1439c);
            gt.a(byteArrayOutputStream, this.f1440d);
            gt.a(byteArrayOutputStream, this.f1438b);
            gt.a(byteArrayOutputStream, String.valueOf(gm.r(this.f1437a)));
            try {
                iCurrentTimeMillis = (int) (System.currentTimeMillis() / 1000);
            } catch (Throwable unused) {
            }
            byteArrayOutputStream.write(a(iCurrentTimeMillis));
            byteArrayOutputStream.write(b(this.f1441e));
            byteArrayOutputStream.write(gt.a(this.f1441e));
            byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (Throwable th3) {
            th = th3;
            try {
                hn.c(th, "se", "tds");
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return byteArray;
            } catch (Throwable th4) {
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                }
                throw th4;
            }
        }
        return byteArray;
    }
}