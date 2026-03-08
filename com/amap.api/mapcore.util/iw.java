package com.amap.api.mapcore.util;

import com.amap.api.mapcore.util.ig;
import java.io.File;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: LogEngine.java */
/* JADX INFO: loaded from: classes.dex */
public class iw {
    public static void a(String str, byte[] bArr, iv ivVar) throws Throwable {
        ig igVarA;
        OutputStream outputStreamA = null;
        try {
            if (a(ivVar.f1409a, str)) {
                return;
            }
            File file = new File(ivVar.f1409a);
            if (!file.exists()) {
                file.mkdirs();
            }
            igVarA = ig.a(file, 1, 1, ivVar.f1410b);
            try {
                igVarA.a(ivVar.f1412d);
                byte[] bArrB = ivVar.f1413e.b(bArr);
                ig.a aVarB = igVarA.b(str);
                outputStreamA = aVarB.a(0);
                outputStreamA.write(bArrB);
                aVarB.a();
                igVarA.e();
                if (outputStreamA != null) {
                    try {
                        outputStreamA.close();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                if (igVarA != null) {
                    try {
                        igVarA.close();
                        return;
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                        return;
                    }
                }
                return;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            igVarA = null;
        }
        if (outputStreamA != null) {
            try {
                outputStreamA.close();
            } catch (Throwable th5) {
                th5.printStackTrace();
            }
        }
        if (igVarA == null) {
            throw th;
        }
        try {
            igVarA.close();
            throw th;
        } catch (Throwable th6) {
            th6.printStackTrace();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0098 A[Catch: all -> 0x0088, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x0088, blocks: (B:43:0x0098, B:35:0x0084), top: B:56:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(com.amap.api.mapcore.util.iv r10) {
        /*
            java.lang.String r0 = "code"
            r1 = -1
            r2 = 0
            com.amap.api.mapcore.util.jp r3 = r10.f1414f     // Catch: java.lang.Throwable -> L8d
            boolean r3 = r3.d()     // Catch: java.lang.Throwable -> L8d
            if (r3 == 0) goto L82
            com.amap.api.mapcore.util.jp r3 = r10.f1414f     // Catch: java.lang.Throwable -> L8d
            r4 = 1
            r3.a_(r4)     // Catch: java.lang.Throwable -> L8d
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L8d
            java.lang.String r5 = r10.f1409a     // Catch: java.lang.Throwable -> L8d
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L8d
            long r5 = r10.f1410b     // Catch: java.lang.Throwable -> L8d
            com.amap.api.mapcore.util.ig r3 = com.amap.api.mapcore.util.ig.a(r3, r4, r4, r5)     // Catch: java.lang.Throwable -> L8d
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L80
            r5.<init>()     // Catch: java.lang.Throwable -> L80
            byte[] r6 = a(r3, r10, r5)     // Catch: java.lang.Throwable -> L80
            if (r6 == 0) goto L75
            int r7 = r6.length     // Catch: java.lang.Throwable -> L80
            if (r7 != 0) goto L2e
            goto L75
        L2e:
            com.amap.api.mapcore.util.hm r7 = new com.amap.api.mapcore.util.hm     // Catch: java.lang.Throwable -> L80
            java.lang.String r8 = r10.f1411c     // Catch: java.lang.Throwable -> L80
            r7.<init>(r6, r8)     // Catch: java.lang.Throwable -> L80
            com.amap.api.mapcore.util.ij r8 = com.amap.api.mapcore.util.ij.a()     // Catch: java.lang.Throwable -> L80
            byte[] r7 = r8.b(r7)     // Catch: java.lang.Throwable -> L80
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L80
            java.lang.String r9 = new java.lang.String     // Catch: java.lang.Throwable -> L80
            r9.<init>(r7)     // Catch: java.lang.Throwable -> L80
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L80
            boolean r7 = r8.has(r0)     // Catch: java.lang.Throwable -> L80
            if (r7 == 0) goto L73
            int r0 = r8.getInt(r0)     // Catch: java.lang.Throwable -> L80
            if (r0 != r4) goto L73
            com.amap.api.mapcore.util.jp r0 = r10.f1414f     // Catch: java.lang.Throwable -> L80
            if (r0 == 0) goto L5f
            if (r6 == 0) goto L5f
            com.amap.api.mapcore.util.jp r0 = r10.f1414f     // Catch: java.lang.Throwable -> L80
            int r4 = r6.length     // Catch: java.lang.Throwable -> L80
            r0.a_(r4)     // Catch: java.lang.Throwable -> L80
        L5f:
            com.amap.api.mapcore.util.jp r10 = r10.f1414f     // Catch: java.lang.Throwable -> L80
            int r10 = r10.a()     // Catch: java.lang.Throwable -> L80
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r10 >= r0) goto L6e
            a(r3, r5)     // Catch: java.lang.Throwable -> L80
            goto L71
        L6e:
            a(r3)     // Catch: java.lang.Throwable -> L80
        L71:
            int r10 = r6.length     // Catch: java.lang.Throwable -> L8d
            return r10
        L73:
            r2 = r3
            goto L82
        L75:
            if (r3 == 0) goto L7f
            r3.close()     // Catch: java.lang.Throwable -> L7b
            goto L7f
        L7b:
            r10 = move-exception
            r10.printStackTrace()
        L7f:
            return r1
        L80:
            r10 = move-exception
            goto L8f
        L82:
            if (r2 == 0) goto L9b
            r2.close()     // Catch: java.lang.Throwable -> L88
            goto L9b
        L88:
            r10 = move-exception
            r10.printStackTrace()
            goto L9b
        L8d:
            r10 = move-exception
            r3 = r2
        L8f:
            java.lang.String r0 = "leg"
            java.lang.String r2 = "uts"
            com.amap.api.mapcore.util.hn.c(r10, r0, r2)     // Catch: java.lang.Throwable -> L9c
            if (r3 == 0) goto L9b
            r3.close()     // Catch: java.lang.Throwable -> L88
        L9b:
            return r1
        L9c:
            r10 = move-exception
            if (r3 == 0) goto La7
            r3.close()     // Catch: java.lang.Throwable -> La3
            goto La7
        La3:
            r0 = move-exception
            r0.printStackTrace()
        La7:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.iw.a(com.amap.api.mapcore.util.iv):int");
    }

    private static byte[] a(ig igVar, iv ivVar, List<String> list) {
        try {
            File fileC = igVar.c();
            if (fileC != null && fileC.exists()) {
                int length = 0;
                for (String str : fileC.list()) {
                    if (str.contains(".0")) {
                        String str2 = str.split("\\.")[0];
                        byte[] bArrA = jc.a(igVar, str2, false);
                        length += bArrA.length;
                        list.add(str2);
                        if (length > ivVar.f1414f.a()) {
                            break;
                        }
                        ivVar.f1415g.b(bArrA);
                    }
                }
                if (length <= 0) {
                    return null;
                }
                return ivVar.f1415g.a();
            }
        } catch (Throwable th) {
            hn.c(th, "leg", "gCo");
        }
        return new byte[0];
    }

    private static void a(ig igVar) {
        if (igVar != null) {
            try {
                igVar.f();
            } catch (Throwable th) {
                hn.c(th, "ofm", "dlo");
            }
        }
    }

    private static void a(ig igVar, List<String> list) {
        if (igVar != null) {
            try {
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    igVar.c(it.next());
                }
                igVar.close();
            } catch (Throwable th) {
                hn.c(th, "ofm", "dlo");
            }
        }
    }

    private static boolean a(String str, String str2) {
        try {
            return new File(str, str2 + ".0").exists();
        } catch (Throwable th) {
            hn.c(th, "leg", "fet");
            return false;
        }
    }
}