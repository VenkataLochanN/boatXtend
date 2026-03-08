package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: compiled from: ClientInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class gl {
    public static String a(Context context, String str, String str2) {
        try {
            return gq.b(gi.e(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            hk.a(th, "CI", "Sco");
            return null;
        }
    }

    public static String a() {
        try {
            String strValueOf = String.valueOf(System.currentTimeMillis());
            String str = gi.a() ? "1" : AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
            int length = strValueOf.length();
            return strValueOf.substring(0, length - 2) + str + strValueOf.substring(length - 1);
        } catch (Throwable th) {
            hk.a(th, "CI", "TS");
            return null;
        }
    }

    public static String a(Context context) {
        try {
            a aVar = new a();
            aVar.f1100d = gi.c(context);
            aVar.i = gi.d(context);
            return a(context, aVar);
        } catch (Throwable th) {
            hk.a(th, "CI", "IX");
            return null;
        }
    }

    public static byte[] a(Context context, byte[] bArr) throws BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException, NullPointerException {
        return gn.a(bArr);
    }

    public static byte[] a(Context context, boolean z, boolean z2) {
        try {
            return b(context, b(context, z, z2));
        } catch (Throwable th) {
            hk.a(th, "CI", "gz");
            return null;
        }
    }

    public static String b(Context context) {
        return a(context, false);
    }

    public static String a(Context context, boolean z) {
        try {
            return a(context, b(context, false, z));
        } catch (Throwable th) {
            hk.a(th, "CI", "gCXi");
            return null;
        }
    }

    public static byte[] b(Context context, byte[] bArr) throws BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException, NullPointerException {
        PublicKey publicKeyD = gt.d();
        if (bArr.length > 117) {
            byte[] bArr2 = new byte[117];
            System.arraycopy(bArr, 0, bArr2, 0, 117);
            byte[] bArrA = gn.a(bArr2, publicKeyD);
            byte[] bArr3 = new byte[(bArr.length + 128) - 117];
            System.arraycopy(bArrA, 0, bArr3, 0, 128);
            System.arraycopy(bArr, 117, bArr3, 128, bArr.length - 117);
            return bArr3;
        }
        return gn.a(bArr, publicKeyD);
    }

    private static String a(Context context, a aVar) {
        return gn.b(b(context, aVar));
    }

    private static byte[] b(Context context, a aVar) {
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
            byteArrayOutputStream = null;
        }
        try {
            a(byteArrayOutputStream, aVar.f1097a);
            a(byteArrayOutputStream, aVar.f1098b);
            a(byteArrayOutputStream, aVar.f1099c);
            a(byteArrayOutputStream, aVar.f1100d);
            a(byteArrayOutputStream, aVar.f1101e);
            a(byteArrayOutputStream, aVar.f1102f);
            a(byteArrayOutputStream, aVar.f1103g);
            a(byteArrayOutputStream, aVar.f1104h);
            a(byteArrayOutputStream, aVar.i);
            a(byteArrayOutputStream, aVar.j);
            a(byteArrayOutputStream, aVar.k);
            a(byteArrayOutputStream, aVar.l);
            a(byteArrayOutputStream, aVar.m);
            a(byteArrayOutputStream, aVar.n);
            a(byteArrayOutputStream, aVar.o);
            a(byteArrayOutputStream, aVar.p);
            a(byteArrayOutputStream, aVar.q);
            a(byteArrayOutputStream, aVar.r);
            a(byteArrayOutputStream, aVar.s);
            a(byteArrayOutputStream, aVar.t);
            a(byteArrayOutputStream, aVar.u);
            a(byteArrayOutputStream, aVar.v);
            a(byteArrayOutputStream, aVar.w);
            a(byteArrayOutputStream, aVar.x);
            a(byteArrayOutputStream, aVar.y);
            byte[] bArrA = a(context, byteArrayOutputStream);
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            return bArrA;
        } catch (Throwable th3) {
            th = th3;
            try {
                hk.a(th, "CI", "gzx");
                return null;
            } finally {
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
            }
        }
    }

    private static byte[] a(Context context, ByteArrayOutputStream byteArrayOutputStream) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException {
        return b(context, gt.b(byteArrayOutputStream.toByteArray()));
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (!TextUtils.isEmpty(str)) {
            gt.a(byteArrayOutputStream, str.getBytes().length > 255 ? (byte) -1 : (byte) str.getBytes().length, gt.a(str));
        } else {
            gt.a(byteArrayOutputStream, (byte) 0, new byte[0]);
        }
    }

    private static a b(Context context, boolean z, boolean z2) {
        a aVar = new a();
        aVar.f1097a = gm.y(context);
        aVar.f1098b = gm.n(context);
        String strI = gm.i(context);
        if (strI == null) {
            strI = "";
        }
        aVar.f1099c = strI;
        aVar.f1100d = gi.c(context);
        aVar.f1101e = Build.MODEL;
        aVar.f1102f = Build.MANUFACTURER;
        aVar.f1103g = Build.DEVICE;
        aVar.f1104h = gi.b(context);
        aVar.i = gi.d(context);
        aVar.j = String.valueOf(Build.VERSION.SDK_INT);
        aVar.k = gm.A(context);
        aVar.l = gm.u(context);
        aVar.m = gm.r(context) + "";
        aVar.n = gm.q(context) + "";
        aVar.o = gm.C(context);
        aVar.p = gm.p(context);
        if (z) {
            aVar.q = "";
        } else {
            aVar.q = gm.m(context);
        }
        if (z) {
            aVar.r = "";
        } else {
            aVar.r = gm.l(context);
        }
        if (z) {
            aVar.s = "";
            aVar.t = "";
        } else {
            String[] strArrO = gm.o(context);
            aVar.s = strArrO[0];
            aVar.t = strArrO[1];
        }
        aVar.w = gm.a();
        String strB = gm.b(context);
        if (!TextUtils.isEmpty(strB)) {
            aVar.x = strB;
        } else {
            aVar.x = "";
        }
        aVar.y = "aid=" + gm.k(context);
        if ((z2 && hj.f1233d) || hj.f1234e) {
            String strH = gm.h(context);
            if (!TextUtils.isEmpty(strH)) {
                aVar.y += "|oaid=" + strH;
            }
        }
        String strA = gm.a(context, AppInfo.DELIM, true);
        if (!TextUtils.isEmpty(strA)) {
            aVar.y += "|multiImeis=" + strA;
        }
        String strZ = gm.z(context);
        if (!TextUtils.isEmpty(strZ)) {
            aVar.y += "|meid=" + strZ;
        }
        aVar.y += "|serial=" + gm.j(context);
        String strA2 = gm.a(context);
        if (!TextUtils.isEmpty(strA2)) {
            aVar.y += "|adiuExtras=" + strA2;
        }
        aVar.y += "|storage=" + gm.c() + "|ram=" + gm.B(context) + "|arch=" + gm.d();
        return aVar;
    }

    /* JADX INFO: compiled from: ClientInfo.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f1097a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f1098b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String f1099c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        String f1100d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        String f1101e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        String f1102f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        String f1103g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        String f1104h;
        String i;
        String j;
        String k;
        String l;
        String m;
        String n;
        String o;
        String p;
        String q;
        String r;
        String s;
        String t;
        String u;
        String v;
        String w;
        String x;
        String y;

        private a() {
        }
    }
}