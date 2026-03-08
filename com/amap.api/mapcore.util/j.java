package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.Hashtable;
import java.util.Map;

/* JADX INFO: compiled from: AuthRequest.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends ga<String, a> {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f1432h;
    private int[] i;

    /* JADX INFO: compiled from: AuthRequest.java */
    public static class a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f1434b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f1435c;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1433a = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1436d = false;
    }

    @Override // com.amap.api.mapcore.util.ga
    protected String a() {
        return null;
    }

    @Override // com.amap.api.mapcore.util.iq
    public boolean isSupportIPV6() {
        return true;
    }

    public j(Context context, String str) {
        super(context, str);
        this.f1432h = true;
        this.i = new int[]{10000, 0, 10018, 10019, 10020, 10021, 10022, 10023};
        this.f1009g = "/feedback";
        this.isPostFlag = false;
        this.f1432h = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0039, code lost:
    
        r1.f1436d = true;
     */
    @Override // com.amap.api.mapcore.util.ga
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amap.api.mapcore.util.j.a b(java.lang.String r6) throws com.amap.api.mapcore.util.fz {
        /*
            r5 = this;
            java.lang.String r0 = "errcode"
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L41
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L41
            r6 = -1
            boolean r2 = r1.has(r0)     // Catch: java.lang.Throwable -> L41
            java.lang.String r3 = ""
            if (r2 == 0) goto L21
            int r6 = r1.optInt(r0)     // Catch: java.lang.Throwable -> L41
            java.lang.String r0 = "errmsg"
            java.lang.String r3 = r1.optString(r0)     // Catch: java.lang.Throwable -> L41
            java.lang.String r0 = "errdetail"
            java.lang.String r0 = r1.optString(r0)     // Catch: java.lang.Throwable -> L41
            goto L22
        L21:
            r0 = r3
        L22:
            com.amap.api.mapcore.util.j$a r1 = new com.amap.api.mapcore.util.j$a     // Catch: java.lang.Throwable -> L41
            r1.<init>()     // Catch: java.lang.Throwable -> L41
            r1.f1433a = r6     // Catch: java.lang.Throwable -> L41
            r1.f1434b = r3     // Catch: java.lang.Throwable -> L41
            r1.f1435c = r0     // Catch: java.lang.Throwable -> L41
            r0 = 0
            r1.f1436d = r0     // Catch: java.lang.Throwable -> L41
            int[] r2 = r5.i     // Catch: java.lang.Throwable -> L41
            int r3 = r2.length     // Catch: java.lang.Throwable -> L41
        L33:
            if (r0 >= r3) goto L40
            r4 = r2[r0]     // Catch: java.lang.Throwable -> L41
            if (r4 != r6) goto L3d
            r6 = 1
            r1.f1436d = r6     // Catch: java.lang.Throwable -> L41
            goto L40
        L3d:
            int r0 = r0 + 1
            goto L33
        L40:
            return r1
        L41:
            r6 = move-exception
            r6.printStackTrace()
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.j.b(java.lang.String):com.amap.api.mapcore.util.j$a");
    }

    @Override // com.amap.api.mapcore.util.dp, com.amap.api.mapcore.util.iq
    public Map<String, String> getParams() {
        Hashtable hashtable = new Hashtable(16);
        hashtable.put("key", gi.f(this.f1008f));
        if (this.f1432h) {
            hashtable.put("pname", "3dmap");
        }
        String strA = gl.a();
        String strA2 = gl.a(this.f1008f, strA, gt.c(hashtable));
        hashtable.put("ts", strA);
        hashtable.put("scode", strA2);
        return hashtable;
    }

    @Override // com.amap.api.mapcore.util.iq
    public String getURL() {
        return "http://restsdk.amap.com/v4" + this.f1009g;
    }

    @Override // com.amap.api.mapcore.util.iq
    public String getIPV6URL() {
        return er.a(getURL());
    }
}