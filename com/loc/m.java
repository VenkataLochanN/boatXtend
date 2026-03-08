package com.loc;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.io.ByteArrayOutputStream;
import java.security.PublicKey;

/* JADX INFO: compiled from: ClientInfo.java */
/* JADX INFO: loaded from: classes3.dex */
public final class m {

    /* JADX INFO: compiled from: ClientInfo.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f5283a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f5284b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String f5285c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        String f5286d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        String f5287e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        String f5288f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        String f5289g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        String f5290h;
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

        /* synthetic */ a(byte b2) {
            this();
        }
    }

    public static String a() {
        try {
            String strValueOf = String.valueOf(System.currentTimeMillis());
            String str = k.a() ? "1" : AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
            int length = strValueOf.length();
            return strValueOf.substring(0, length - 2) + str + strValueOf.substring(length - 1);
        } catch (Throwable th) {
            y.a(th, "CI", "TS");
            return null;
        }
    }

    public static String a(Context context, String str, String str2) {
        try {
            return r.a(k.e(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            y.a(th, "CI", "Sco");
            return null;
        }
    }

    private static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            u.a(byteArrayOutputStream, (byte) 0, new byte[0]);
        } else {
            u.a(byteArrayOutputStream, str.getBytes().length > 255 ? (byte) -1 : (byte) str.getBytes().length, u.a(str));
        }
    }

    public static byte[] a(Context context, boolean z, boolean z2) {
        try {
            a aVar = new a((byte) 0);
            aVar.f5283a = n.x(context);
            aVar.f5284b = n.m(context);
            String strH = n.h(context);
            if (strH == null) {
                strH = "";
            }
            aVar.f5285c = strH;
            aVar.f5286d = k.c(context);
            aVar.f5287e = Build.MODEL;
            aVar.f5288f = Build.MANUFACTURER;
            aVar.f5289g = Build.DEVICE;
            aVar.f5290h = k.b(context);
            aVar.i = k.d(context);
            aVar.j = String.valueOf(Build.VERSION.SDK_INT);
            aVar.k = n.A(context);
            aVar.l = n.t(context);
            StringBuilder sb = new StringBuilder();
            sb.append(n.q(context));
            aVar.m = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(n.p(context));
            aVar.n = sb2.toString();
            aVar.o = n.C(context);
            aVar.p = n.o(context);
            if (z) {
                aVar.q = "";
            } else {
                aVar.q = n.l(context);
            }
            if (z) {
                aVar.r = "";
            } else {
                aVar.r = n.k(context);
            }
            if (z) {
                aVar.s = "";
                aVar.t = "";
            } else {
                String[] strArrC = n.c();
                aVar.s = strArrC[0];
                aVar.t = strArrC[1];
            }
            aVar.w = n.a();
            String strA = n.a(context);
            if (TextUtils.isEmpty(strA)) {
                aVar.x = "";
            } else {
                aVar.x = strA;
            }
            aVar.y = "aid=" + n.j(context);
            if ((z2 && v.f5331d) || v.f5332e) {
                String strG = n.g(context);
                if (!TextUtils.isEmpty(strG)) {
                    aVar.y += "|oaid=" + strG;
                }
            }
            String strA2 = n.a(context, AppInfo.DELIM);
            if (!TextUtils.isEmpty(strA2)) {
                aVar.y += "|multiImeis=" + strA2;
            }
            String strZ = n.z(context);
            if (!TextUtils.isEmpty(strZ)) {
                aVar.y += "|meid=" + strZ;
            }
            aVar.y += "|serial=" + n.i(context);
            String strB = n.b();
            if (!TextUtils.isEmpty(strB)) {
                aVar.y += "|adiuExtras=" + strB;
            }
            aVar.y += "|storage=" + n.d() + "|ram=" + n.B(context) + "|arch=" + n.e();
            return a(aVar);
        } catch (Throwable th) {
            y.a(th, "CI", "gz");
            return null;
        }
    }

    private static byte[] a(a aVar) {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArrA;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
            byteArrayOutputStream = null;
        }
        try {
            a(byteArrayOutputStream, aVar.f5283a);
            a(byteArrayOutputStream, aVar.f5284b);
            a(byteArrayOutputStream, aVar.f5285c);
            a(byteArrayOutputStream, aVar.f5286d);
            a(byteArrayOutputStream, aVar.f5287e);
            a(byteArrayOutputStream, aVar.f5288f);
            a(byteArrayOutputStream, aVar.f5289g);
            a(byteArrayOutputStream, aVar.f5290h);
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
            byte[] bArrB = u.b(byteArrayOutputStream.toByteArray());
            PublicKey publicKeyD = u.d();
            if (bArrB.length > 117) {
                byte[] bArr = new byte[117];
                System.arraycopy(bArrB, 0, bArr, 0, 117);
                byte[] bArrA2 = o.a(bArr, publicKeyD);
                bArrA = new byte[(bArrB.length + 128) - 117];
                System.arraycopy(bArrA2, 0, bArrA, 0, 128);
                System.arraycopy(bArrB, 117, bArrA, 128, bArrB.length - 117);
            } else {
                bArrA = o.a(bArrB, publicKeyD);
            }
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            return bArrA;
        } catch (Throwable th3) {
            th = th3;
            try {
                y.a(th, "CI", "gzx");
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
}