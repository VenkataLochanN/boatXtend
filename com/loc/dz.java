package com.loc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.bumptech.glide.load.Key;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.life.util.DateUtil;
import java.util.ArrayList;
import java.util.Hashtable;
import org.json.JSONObject;

/* JADX INFO: compiled from: Cache.java */
/* JADX INFO: loaded from: classes3.dex */
public final class dz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Hashtable<String, ArrayList<a>> f5091a = new Hashtable<>();
    private long i = 0;
    private boolean j = false;
    private String k = "2.0.201501131131".replace(".", "");
    private String l = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f5092b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    long f5093c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f5094d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    dv f5095e = null;
    private String m = null;
    private long n = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    boolean f5096f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f5097g = true;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    String f5098h = String.valueOf(AMapLocationClientOption.GeoLanguage.DEFAULT);

    /* JADX INFO: compiled from: Cache.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ds f5099a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f5100b = null;

        protected a() {
        }

        public final ds a() {
            return this.f5099a;
        }

        public final void a(ds dsVar) {
            this.f5099a = dsVar;
        }

        public final void a(String str) {
            this.f5100b = TextUtils.isEmpty(str) ? null : str.replace("##", "#");
        }

        public final String b() {
            return this.f5100b;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0102, code lost:
    
        r6 = r5;
        r13 = r12;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01a6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f4 A[Catch: all -> 0x0241, TryCatch #0 {all -> 0x0241, blocks: (B:3:0x0008, B:5:0x0011, B:8:0x001a, B:10:0x0022, B:12:0x002a, B:14:0x0038, B:92:0x01f6, B:94:0x0200, B:96:0x021a, B:98:0x0220, B:99:0x0224, B:101:0x0229, B:103:0x022d, B:105:0x0235, B:16:0x004c, B:18:0x0054, B:21:0x005b, B:24:0x0064, B:26:0x0083, B:28:0x0093, B:30:0x009d, B:33:0x00a4, B:35:0x00aa, B:49:0x00f4, B:90:0x01ea, B:54:0x0109, B:55:0x0122, B:58:0x012a, B:59:0x0135, B:60:0x013d, B:62:0x0143, B:63:0x014d, B:65:0x0165, B:67:0x016b, B:71:0x0182, B:75:0x018d, B:76:0x0193, B:79:0x01a8, B:83:0x01c5, B:88:0x01dd, B:38:0x00b4, B:40:0x00c0, B:42:0x00d0, B:45:0x00d7, B:41:0x00ce), top: B:111:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x012a A[Catch: all -> 0x0241, LOOP:1: B:55:0x0122->B:58:0x012a, LOOP_END, TRY_ENTER, TryCatch #0 {all -> 0x0241, blocks: (B:3:0x0008, B:5:0x0011, B:8:0x001a, B:10:0x0022, B:12:0x002a, B:14:0x0038, B:92:0x01f6, B:94:0x0200, B:96:0x021a, B:98:0x0220, B:99:0x0224, B:101:0x0229, B:103:0x022d, B:105:0x0235, B:16:0x004c, B:18:0x0054, B:21:0x005b, B:24:0x0064, B:26:0x0083, B:28:0x0093, B:30:0x009d, B:33:0x00a4, B:35:0x00aa, B:49:0x00f4, B:90:0x01ea, B:54:0x0109, B:55:0x0122, B:58:0x012a, B:59:0x0135, B:60:0x013d, B:62:0x0143, B:63:0x014d, B:65:0x0165, B:67:0x016b, B:71:0x0182, B:75:0x018d, B:76:0x0193, B:79:0x01a8, B:83:0x01c5, B:88:0x01dd, B:38:0x00b4, B:40:0x00c0, B:42:0x00d0, B:45:0x00d7, B:41:0x00ce), top: B:111:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0143 A[Catch: all -> 0x0241, LOOP:2: B:60:0x013d->B:62:0x0143, LOOP_END, TryCatch #0 {all -> 0x0241, blocks: (B:3:0x0008, B:5:0x0011, B:8:0x001a, B:10:0x0022, B:12:0x002a, B:14:0x0038, B:92:0x01f6, B:94:0x0200, B:96:0x021a, B:98:0x0220, B:99:0x0224, B:101:0x0229, B:103:0x022d, B:105:0x0235, B:16:0x004c, B:18:0x0054, B:21:0x005b, B:24:0x0064, B:26:0x0083, B:28:0x0093, B:30:0x009d, B:33:0x00a4, B:35:0x00aa, B:49:0x00f4, B:90:0x01ea, B:54:0x0109, B:55:0x0122, B:58:0x012a, B:59:0x0135, B:60:0x013d, B:62:0x0143, B:63:0x014d, B:65:0x0165, B:67:0x016b, B:71:0x0182, B:75:0x018d, B:76:0x0193, B:79:0x01a8, B:83:0x01c5, B:88:0x01dd, B:38:0x00b4, B:40:0x00c0, B:42:0x00d0, B:45:0x00d7, B:41:0x00ce), top: B:111:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01a8 A[Catch: all -> 0x0241, TryCatch #0 {all -> 0x0241, blocks: (B:3:0x0008, B:5:0x0011, B:8:0x001a, B:10:0x0022, B:12:0x002a, B:14:0x0038, B:92:0x01f6, B:94:0x0200, B:96:0x021a, B:98:0x0220, B:99:0x0224, B:101:0x0229, B:103:0x022d, B:105:0x0235, B:16:0x004c, B:18:0x0054, B:21:0x005b, B:24:0x0064, B:26:0x0083, B:28:0x0093, B:30:0x009d, B:33:0x00a4, B:35:0x00aa, B:49:0x00f4, B:90:0x01ea, B:54:0x0109, B:55:0x0122, B:58:0x012a, B:59:0x0135, B:60:0x013d, B:62:0x0143, B:63:0x014d, B:65:0x0165, B:67:0x016b, B:71:0x0182, B:75:0x018d, B:76:0x0193, B:79:0x01a8, B:83:0x01c5, B:88:0x01dd, B:38:0x00b4, B:40:0x00c0, B:42:0x00d0, B:45:0x00d7, B:41:0x00ce), top: B:111:0x0008 }] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.loc.ds a(java.lang.String r27, java.lang.StringBuilder r28) {
        /*
            Method dump skipped, instruction units count: 587
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dz.a(java.lang.String, java.lang.StringBuilder):com.loc.ds");
    }

    private String a(String str, StringBuilder sb, Context context) {
        if (context == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.l == null) {
                this.l = dy.a("MD5", k.c(context));
            }
            if (str.contains("&")) {
                str = str.substring(0, str.indexOf("&"));
            }
            String strSubstring = str.substring(str.lastIndexOf("#") + 1);
            if (strSubstring.equals("cgi")) {
                jSONObject.put("cgi", str.substring(0, str.length() - 12));
            } else if (!TextUtils.isEmpty(sb) && sb.indexOf(",access") != -1) {
                jSONObject.put("cgi", str.substring(0, str.length() - (strSubstring.length() + 9)));
                String[] strArrSplit = sb.toString().split(",access");
                jSONObject.put("mmac", strArrSplit[0].contains("#") ? strArrSplit[0].substring(strArrSplit[0].lastIndexOf("#") + 1) : strArrSplit[0]);
            }
            return o.b(dy.a(jSONObject.toString().getBytes(Key.STRING_CHARSET_NAME), this.l));
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x023c A[EDGE_INSN: B:121:0x023c->B:75:0x023c BREAK  A[LOOP:0: B:30:0x00ad->B:77:0x024c], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0107 A[Catch: all -> 0x0265, TryCatch #1 {all -> 0x0265, blocks: (B:24:0x0090, B:26:0x0099, B:28:0x00a7, B:30:0x00ad, B:33:0x00c4, B:35:0x00de, B:36:0x00e2, B:40:0x00f7, B:42:0x0107, B:54:0x0182, B:57:0x0196, B:59:0x019c, B:61:0x01c8, B:63:0x01e3, B:62:0x01d6, B:64:0x01e8, B:66:0x01ee, B:68:0x021a, B:37:0x00e6, B:39:0x00ec, B:43:0x010c, B:45:0x0137, B:46:0x014a, B:50:0x015f, B:52:0x017e, B:47:0x014e, B:49:0x0154), top: B:112:0x0090 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x017e A[Catch: all -> 0x0265, TryCatch #1 {all -> 0x0265, blocks: (B:24:0x0090, B:26:0x0099, B:28:0x00a7, B:30:0x00ad, B:33:0x00c4, B:35:0x00de, B:36:0x00e2, B:40:0x00f7, B:42:0x0107, B:54:0x0182, B:57:0x0196, B:59:0x019c, B:61:0x01c8, B:63:0x01e3, B:62:0x01d6, B:64:0x01e8, B:66:0x01ee, B:68:0x021a, B:37:0x00e6, B:39:0x00ec, B:43:0x010c, B:45:0x0137, B:46:0x014a, B:50:0x015f, B:52:0x017e, B:47:0x014e, B:49:0x0154), top: B:112:0x0090 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x024c A[LOOP:0: B:30:0x00ad->B:77:0x024c, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(android.content.Context r20, java.lang.String r21) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 665
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dz.a(android.content.Context, java.lang.String):void");
    }

    private void a(String str, AMapLocation aMapLocation, StringBuilder sb, Context context) throws Exception {
        if (context == null) {
            return;
        }
        if (this.l == null) {
            this.l = dy.a("MD5", k.c(context));
        }
        String strA = a(str, sb, context);
        StringBuilder sb2 = new StringBuilder();
        SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase = null;
        try {
            sQLiteDatabaseOpenOrCreateDatabase = context.openOrCreateDatabase("hmdb", 0, null);
            sb2.append("CREATE TABLE IF NOT EXISTS hist");
            sb2.append(this.k);
            sb2.append(" (feature VARCHAR PRIMARY KEY, nb VARCHAR, loc VARCHAR, time VARCHAR);");
            sQLiteDatabaseOpenOrCreateDatabase.execSQL(sb2.toString());
            sb2.delete(0, sb2.length());
            sb2.append("REPLACE INTO ");
            sb2.append("hist");
            sb2.append(this.k);
            sb2.append(" VALUES (?, ?, ?, ?)");
            Object[] objArr = new Object[4];
            objArr[0] = strA;
            byte[] bArrA = dy.a(sb.toString().getBytes(Key.STRING_CHARSET_NAME), this.l);
            objArr[1] = bArrA;
            objArr[2] = dy.a(aMapLocation.toStr().getBytes(Key.STRING_CHARSET_NAME), this.l);
            objArr[3] = Long.valueOf(aMapLocation.getTime());
            for (int i = 1; i < 3; i++) {
                objArr[i] = o.b((byte[]) objArr[i]);
            }
            sQLiteDatabaseOpenOrCreateDatabase.execSQL(sb2.toString(), objArr);
            sb2.delete(0, sb2.length());
        } catch (Throwable th) {
            try {
                ej.a(th, "DB", "updateHist");
                sb2.delete(0, sb2.length());
                if (sQLiteDatabaseOpenOrCreateDatabase == null || !sQLiteDatabaseOpenOrCreateDatabase.isOpen()) {
                    return;
                }
                sQLiteDatabaseOpenOrCreateDatabase.close();
            } finally {
                sb2.delete(0, sb2.length());
                if (sQLiteDatabaseOpenOrCreateDatabase != null && sQLiteDatabaseOpenOrCreateDatabase.isOpen()) {
                    sQLiteDatabaseOpenOrCreateDatabase.close();
                }
            }
        }
    }

    private static void a(String str, Hashtable<String, String> hashtable) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        hashtable.clear();
        for (String str2 : str.split("#")) {
            if (!TextUtils.isEmpty(str2) && !str2.contains("|")) {
                hashtable.put(str2, "");
            }
        }
    }

    private static double[] a(double[] dArr, double[] dArr2) {
        double[] dArr3 = new double[3];
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < dArr.length; i3++) {
            d3 += dArr[i3] * dArr[i3];
            d4 += dArr2[i3] * dArr2[i3];
            d2 += dArr[i3] * dArr2[i3];
            if (dArr2[i3] == 1.0d) {
                i2++;
                if (dArr[i3] == 1.0d) {
                    i++;
                }
            }
        }
        dArr3[0] = d2 / (Math.sqrt(d3) * Math.sqrt(d4));
        double d5 = i;
        dArr3[1] = (d5 * 1.0d) / ((double) i2);
        dArr3[2] = d5;
        for (int i4 = 0; i4 < 2; i4++) {
            if (dArr3[i4] > 1.0d) {
                dArr3[i4] = 1.0d;
            }
        }
        return dArr3;
    }

    private boolean b() {
        long jB = ep.b();
        long j = this.i;
        long j2 = jB - j;
        if (j == 0) {
            return false;
        }
        return this.f5091a.size() > 360 || j2 > 36000000;
    }

    private void c() {
        this.i = 0L;
        if (!this.f5091a.isEmpty()) {
            this.f5091a.clear();
        }
        this.j = false;
    }

    public final ds a(Context context, String str, StringBuilder sb, boolean z) {
        if (TextUtils.isEmpty(str) || !ei.e()) {
            return null;
        }
        String str2 = str + "&" + this.f5096f + "&" + this.f5097g + "&" + this.f5098h;
        if (str2.contains("gps") || !ei.e() || sb == null) {
            return null;
        }
        if (b()) {
            c();
            return null;
        }
        if (z && !this.j) {
            try {
                String strA = a(str2, sb, context);
                c();
                a(context, strA);
            } catch (Throwable unused) {
            }
        }
        if (this.f5091a.isEmpty()) {
            return null;
        }
        return a(str2, sb);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ee A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0103  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.loc.ds a(com.loc.dw r17, boolean r18, com.loc.ds r19, com.loc.dx r20, java.lang.StringBuilder r21, java.lang.String r22, android.content.Context r23, boolean r24) {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dz.a(com.loc.dw, boolean, com.loc.ds, com.loc.dx, java.lang.StringBuilder, java.lang.String, android.content.Context, boolean):com.loc.ds");
    }

    public final void a() {
        this.f5093c = 0L;
        this.f5094d = null;
    }

    public final void a(Context context) {
        if (this.j) {
            return;
        }
        try {
            c();
            a(context, (String) null);
        } catch (Throwable th) {
            ej.a(th, "Cache", "loadDB");
        }
        this.j = true;
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        this.f5097g = aMapLocationClientOption.isNeedAddress();
        this.f5096f = aMapLocationClientOption.isOffset();
        this.f5092b = aMapLocationClientOption.isLocationCacheEnable();
        this.f5098h = String.valueOf(aMapLocationClientOption.getGeoLanguage());
    }

    public final void a(dv dvVar) {
        this.f5095e = dvVar;
    }

    public final void a(String str) {
        this.f5094d = str;
    }

    public final void a(String str, StringBuilder sb, ds dsVar, Context context, boolean z) {
        try {
            if (ep.a(dsVar)) {
                String str2 = str + "&" + dsVar.isOffset() + "&" + dsVar.i() + "&" + dsVar.j();
                if (!((TextUtils.isEmpty(str2) || !ep.a(dsVar) || str2.startsWith("#")) ? false : str2.contains("network")) || dsVar.e().equals("mem") || dsVar.e().equals("file") || dsVar.e().equals("wifioff") || "-3".equals(dsVar.d())) {
                    return;
                }
                if (b()) {
                    c();
                }
                JSONObject jSONObjectF = dsVar.f();
                if (ep.a(jSONObjectF, "offpct")) {
                    jSONObjectF.remove("offpct");
                    dsVar.a(jSONObjectF);
                }
                if (str2.contains("wifi")) {
                    if (TextUtils.isEmpty(sb)) {
                        return;
                    }
                    if (dsVar.getAccuracy() >= 300.0f) {
                        int i = 0;
                        for (String str3 : sb.toString().split("#")) {
                            if (str3.contains(AppInfo.DELIM)) {
                                i++;
                            }
                        }
                        if (i >= 8) {
                            return;
                        }
                    } else if (dsVar.getAccuracy() <= 3.0f) {
                        return;
                    }
                    if (str2.contains("cgiwifi") && !TextUtils.isEmpty(dsVar.g())) {
                        String strReplace = str2.replace("cgiwifi", "cgi");
                        ds dsVarH = dsVar.h();
                        if (ep.a(dsVarH)) {
                            a(strReplace, new StringBuilder(), dsVarH, context, true);
                        }
                    }
                } else if (str2.contains("cgi") && ((sb != null && sb.indexOf(AppInfo.DELIM) != -1) || AlexaCustomSkillConstant.EVENT_START_SPORT.equals(dsVar.d()))) {
                    return;
                }
                ds dsVarA = a(str2, sb);
                if (ep.a(dsVarA) && dsVarA.toStr().equals(dsVar.toStr(3))) {
                    return;
                }
                this.i = ep.b();
                a aVar = new a();
                aVar.a(dsVar);
                aVar.a(TextUtils.isEmpty(sb) ? null : sb.toString());
                if (this.f5091a.containsKey(str2)) {
                    this.f5091a.get(str2).add(aVar);
                } else {
                    ArrayList<a> arrayList = new ArrayList<>();
                    arrayList.add(aVar);
                    this.f5091a.put(str2, arrayList);
                }
                if (z) {
                    try {
                        a(str2, dsVar, sb, context);
                    } catch (Throwable th) {
                        ej.a(th, "Cache", "add");
                    }
                }
            }
        } catch (Throwable th2) {
            ej.a(th2, "Cache", "add");
        }
    }

    /* JADX WARN: Finally extract failed */
    public final void b(Context context) {
        SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase;
        try {
            c();
            if (context != null) {
                try {
                    sQLiteDatabaseOpenOrCreateDatabase = context.openOrCreateDatabase("hmdb", 0, null);
                } catch (Throwable th) {
                    th = th;
                    sQLiteDatabaseOpenOrCreateDatabase = null;
                }
                try {
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        ej.a(th, "DB", "clearHist p2");
                        if (sQLiteDatabaseOpenOrCreateDatabase != null && sQLiteDatabaseOpenOrCreateDatabase.isOpen()) {
                            sQLiteDatabaseOpenOrCreateDatabase.close();
                        }
                    } catch (Throwable th3) {
                        if (sQLiteDatabaseOpenOrCreateDatabase != null && sQLiteDatabaseOpenOrCreateDatabase.isOpen()) {
                            sQLiteDatabaseOpenOrCreateDatabase.close();
                        }
                        throw th3;
                    }
                }
                if (ep.a(sQLiteDatabaseOpenOrCreateDatabase, "hist")) {
                    try {
                        sQLiteDatabaseOpenOrCreateDatabase.delete("hist" + this.k, "time<?", new String[]{String.valueOf(ep.a() - DateUtil.DAY)});
                    } catch (Throwable th4) {
                        ej.a(th4, "DB", "clearHist");
                        String message = th4.getMessage();
                        if (!TextUtils.isEmpty(message)) {
                            message.contains("no such table");
                        }
                    }
                    if (sQLiteDatabaseOpenOrCreateDatabase != null && sQLiteDatabaseOpenOrCreateDatabase.isOpen()) {
                        sQLiteDatabaseOpenOrCreateDatabase.close();
                    }
                } else if (sQLiteDatabaseOpenOrCreateDatabase != null && sQLiteDatabaseOpenOrCreateDatabase.isOpen()) {
                    sQLiteDatabaseOpenOrCreateDatabase.close();
                }
            }
            this.j = false;
            this.f5094d = null;
            this.n = 0L;
        } catch (Throwable th5) {
            ej.a(th5, "Cache", "destroy part");
        }
    }
}