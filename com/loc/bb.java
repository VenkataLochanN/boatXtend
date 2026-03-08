package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.AMapException;
import java.io.ByteArrayOutputStream;

/* JADX INFO: compiled from: StatisticsEntity.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f4835a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f4836b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f4837c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f4838d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f4839e;

    public bb(Context context, String str, String str2, String str3) throws j {
        if (TextUtils.isEmpty(str3) || str3.length() > 256) {
            throw new j(AMapException.ERROR_INVALID_PARAMETER);
        }
        this.f4835a = context.getApplicationContext();
        this.f4837c = str;
        this.f4838d = str2;
        this.f4836b = str3;
    }

    public final void a(String str) throws j {
        if (TextUtils.isEmpty(str) || str.length() > 65536) {
            throw new j(AMapException.ERROR_INVALID_PARAMETER);
        }
        this.f4839e = str;
    }

    public final byte[] a() {
        ByteArrayOutputStream byteArrayOutputStream;
        int iCurrentTimeMillis;
        byte[] bArrA;
        byte[] byteArray = new byte[0];
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    u.a(byteArrayOutputStream, this.f4837c);
                    u.a(byteArrayOutputStream, this.f4838d);
                    u.a(byteArrayOutputStream, this.f4836b);
                    u.a(byteArrayOutputStream, String.valueOf(n.q(this.f4835a)));
                    try {
                        iCurrentTimeMillis = (int) (System.currentTimeMillis() / 1000);
                    } catch (Throwable unused) {
                        iCurrentTimeMillis = 0;
                    }
                    byteArrayOutputStream.write(new byte[]{(byte) ((iCurrentTimeMillis >> 24) & 255), (byte) ((iCurrentTimeMillis >> 16) & 255), (byte) ((iCurrentTimeMillis >> 8) & 255), (byte) (iCurrentTimeMillis & 255)});
                    byte[] bArrA2 = (TextUtils.isEmpty(this.f4839e) || (bArrA = u.a(this.f4839e)) == null) ? new byte[]{0, 0} : u.a(bArrA.length);
                    byteArrayOutputStream.write(bArrA2);
                    byteArrayOutputStream.write(u.a(this.f4839e));
                    byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                } catch (Throwable th) {
                    th = th;
                    try {
                        ab.b(th, "se", "tds");
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        return byteArray;
                    } catch (Throwable th2) {
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Throwable th3) {
                                th3.printStackTrace();
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                byteArrayOutputStream = null;
            }
        } catch (Throwable th5) {
            th5.printStackTrace();
        }
        return byteArray;
    }
}