package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.ig;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import java.io.InputStream;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: Utils.java */
/* JADX INFO: loaded from: classes.dex */
public class jc {
    public static void a(Context context, iv ivVar, String str, int i, int i2, String str2) {
        ivVar.f1409a = hl.c(context, str);
        ivVar.f1412d = i;
        ivVar.f1410b = i2;
        ivVar.f1411c = str2;
    }

    public static iv a(WeakReference<iv> weakReference) {
        if (weakReference == null || weakReference.get() == null) {
            weakReference = new WeakReference<>(new iv());
        }
        return weakReference.get();
    }

    static byte[] a(ig igVar, String str, boolean z) {
        ig.b bVarA;
        byte[] bArr = new byte[0];
        InputStream inputStream = null;
        try {
            bVarA = igVar.a(str);
            if (bVarA == null) {
                if (bVarA != null) {
                    try {
                        bVarA.close();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                return bArr;
            }
            try {
                InputStream inputStreamA = bVarA.a(0);
                if (inputStreamA == null) {
                    if (inputStreamA != null) {
                        try {
                            inputStreamA.close();
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    }
                    if (bVarA != null) {
                        try {
                            bVarA.close();
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                        }
                    }
                    return bArr;
                }
                bArr = new byte[inputStreamA.available()];
                inputStreamA.read(bArr);
                if (z) {
                    igVar.c(str);
                }
                if (inputStreamA != null) {
                    try {
                        inputStreamA.close();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
                if (bVarA != null) {
                    try {
                        bVarA.close();
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                }
                return bArr;
            } catch (Throwable th6) {
                th = th6;
                try {
                    hn.c(th, "sui", "rdS");
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (Throwable th7) {
                            th7.printStackTrace();
                        }
                    }
                    if (bVarA != null) {
                        try {
                            bVarA.close();
                        } catch (Throwable th8) {
                            th8.printStackTrace();
                        }
                    }
                    return bArr;
                } finally {
                }
            }
        } catch (Throwable th9) {
            th = th9;
            bVarA = null;
        }
    }

    public static String a() {
        return gt.a(System.currentTimeMillis());
    }

    public static String a(Context context, gs gsVar) {
        StringBuilder sb = new StringBuilder();
        try {
            String strG = gm.g(context);
            sb.append("\"sim\":\"");
            sb.append(strG);
            sb.append("\",\"sdkversion\":\"");
            sb.append(gsVar.c());
            sb.append("\",\"product\":\"");
            sb.append(gsVar.a());
            sb.append("\",\"ed\":\"");
            sb.append(gsVar.e());
            sb.append("\",\"nt\":\"");
            sb.append(gm.e(context));
            sb.append("\",\"np\":\"");
            sb.append(gm.c(context));
            sb.append("\",\"mnc\":\"");
            sb.append(gm.d(context));
            sb.append("\",\"ant\":\"");
            sb.append(gm.f(context));
            sb.append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    public static String a(String str, String str2, String str3, int i, String str4, String str5) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2);
        stringBuffer.append(AppInfo.DELIM);
        stringBuffer.append("\"timestamp\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str5);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }

    public static String b(String str, String str2, String str3, int i, String str4, String str5) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2);
        stringBuffer.append(AppInfo.DELIM);
        stringBuffer.append("\"timestamp\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str5);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }
}