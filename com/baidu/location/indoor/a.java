package com.baidu.location.indoor;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.location.a.v;
import com.bumptech.glide.load.Key;
import com.ido.life.util.DateUtil;
import io.reactivex.annotations.SchedulerSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
public class a extends com.baidu.location.g.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static HashMap<String, Long> f2508a = new HashMap<>();
    private static Object v = new Object();
    private static a w = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f2511d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f2513f;
    private InterfaceC0023a r;
    private Handler t;
    private Runnable u;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2509b = "http://loc.map.baidu.com/indoorlocbuildinginfo.php";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final SimpleDateFormat f2510c = new SimpleDateFormat("yyyyMM");
    private String s = null;
    private HashSet<String> q = new HashSet<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2512e = false;

    /* JADX INFO: renamed from: com.baidu.location.indoor.a$a, reason: collision with other inner class name */
    public interface InterfaceC0023a {
        void a(boolean z);
    }

    public a(Context context) {
        this.f2511d = context;
        this.k = new HashMap();
        this.t = new Handler();
        this.u = new b(this);
    }

    private String a(Date date) {
        BufferedReader bufferedReader;
        String str;
        File file = new File(this.f2511d.getCacheDir(), com.baidu.android.bbalbs.common.a.c.a((this.f2513f + this.f2510c.format(date)).getBytes(), false));
        if (!file.isFile()) {
            return null;
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            str = "";
        } catch (Exception unused) {
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            str = str + line + IOUtils.LINE_SEPARATOR_UNIX;
            return null;
        }
        bufferedReader.close();
        if (!str.equals("")) {
            return new String(com.baidu.android.bbalbs.common.a.b.a(str.getBytes()));
        }
        return null;
    }

    private Date d() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, -1);
        return calendar.getTime();
    }

    private void d(String str) {
        for (String str2 : str.split(AppInfo.DELIM)) {
            this.q.add(str2.toLowerCase());
        }
    }

    private void e() {
        try {
            File file = new File(this.f2511d.getCacheDir(), com.baidu.android.bbalbs.common.a.c.a((this.f2513f + this.f2510c.format(d())).getBytes(), false));
            if (file.isFile()) {
                file.delete();
            }
        } catch (Exception unused) {
        }
    }

    private void e(String str) {
        try {
            FileWriter fileWriter = new FileWriter(new File(this.f2511d.getCacheDir(), com.baidu.android.bbalbs.common.a.c.a((this.f2513f + this.f2510c.format(new Date())).getBytes(), false)));
            fileWriter.write(com.baidu.android.bbalbs.common.a.b.a(str.getBytes(), Key.STRING_CHARSET_NAME));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException unused) {
        }
    }

    private void f(String str) {
        try {
            FileWriter fileWriter = new FileWriter(new File(this.f2511d.getCacheDir(), "buildings"), true);
            fileWriter.write(str + IOUtils.LINE_SEPARATOR_UNIX);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.baidu.location.g.e
    public void a() {
        this.f2489h = this.f2509b;
        this.k.clear();
        this.k.put("bid", SchedulerSupport.NONE);
        this.k.put("bldg", this.f2513f);
        this.k.put("mb", Build.MODEL);
        this.k.put("msdk", "2.0");
        this.k.put("cuid", com.baidu.location.g.b.a().f2466c);
        this.k.put("anchors", "v1");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0046  */
    @Override // com.baidu.location.g.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(boolean r6) {
        /*
            r5 = this;
            java.lang.String r0 = "anchorinfo"
            r1 = 1
            r2 = 0
            if (r6 == 0) goto L46
            java.lang.String r6 = r5.j
            if (r6 == 0) goto L46
            java.lang.String r6 = r5.j     // Catch: java.lang.Exception -> L46
            java.lang.String r3 = new java.lang.String     // Catch: java.lang.Exception -> L46
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Exception -> L46
            byte[] r6 = r6.getBytes()     // Catch: java.lang.Exception -> L46
            byte[] r6 = com.baidu.android.bbalbs.common.a.b.a(r6)     // Catch: java.lang.Exception -> L46
            r3.<init>(r6)     // Catch: java.lang.Exception -> L46
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: java.lang.Exception -> L46
            r6.<init>(r3)     // Catch: java.lang.Exception -> L46
            boolean r3 = r6.has(r0)     // Catch: java.lang.Exception -> L46
            if (r3 == 0) goto L46
            java.lang.String r6 = r6.optString(r0)     // Catch: java.lang.Exception -> L46
            if (r6 == 0) goto L46
            java.lang.String r0 = ""
            boolean r0 = r6.equals(r0)     // Catch: java.lang.Exception -> L46
            if (r0 != 0) goto L46
            java.util.HashSet<java.lang.String> r0 = r5.q     // Catch: java.lang.Exception -> L46
            r0.clear()     // Catch: java.lang.Exception -> L46
            r5.d(r6)     // Catch: java.lang.Exception -> L46
            r5.e(r6)     // Catch: java.lang.Exception -> L46
            r5.e()     // Catch: java.lang.Exception -> L44
        L44:
            r6 = r1
            goto L47
        L46:
            r6 = r2
        L47:
            if (r6 != 0) goto L5c
            java.lang.String r0 = r5.s
            if (r0 != 0) goto L5c
            java.lang.String r0 = r5.f2513f
            r5.s = r0
            android.os.Handler r0 = r5.t
            java.lang.Runnable r1 = r5.u
            r3 = 60000(0xea60, double:2.9644E-319)
            r0.postDelayed(r1, r3)
            goto L7d
        L5c:
            r0 = 0
            if (r6 == 0) goto L62
            r5.s = r0
            goto L7d
        L62:
            java.lang.String r3 = r5.s
            r5.f(r3)
            r5.s = r0
            java.util.Date r0 = r5.d()
            java.lang.String r0 = r5.a(r0)
            if (r0 == 0) goto L7d
            r5.d(r0)
            com.baidu.location.indoor.a$a r0 = r5.r
            if (r0 == 0) goto L7d
            r0.a(r1)
        L7d:
            r5.f2512e = r2
            com.baidu.location.indoor.a$a r0 = r5.r
            if (r0 == 0) goto L86
            r0.a(r6)
        L86:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.indoor.a.a(boolean):void");
    }

    public boolean a(String str) {
        String str2 = this.f2513f;
        return (str2 == null || !str2.equalsIgnoreCase(str) || this.q.isEmpty()) ? false : true;
    }

    public boolean a(String str, InterfaceC0023a interfaceC0023a) {
        if (!this.f2512e) {
            this.r = interfaceC0023a;
            this.f2512e = true;
            this.f2513f = str;
            try {
                String strA = a(new Date());
                if (strA == null) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (f2508a.get(str) == null || jCurrentTimeMillis - f2508a.get(str).longValue() > DateUtil.DAY) {
                        f2508a.put(str, Long.valueOf(jCurrentTimeMillis));
                        b(v.a().c());
                    }
                } else {
                    d(strA);
                    if (this.r != null) {
                        this.r.a(true);
                    }
                    this.f2512e = false;
                }
            } catch (Exception unused) {
                this.f2512e = false;
            }
        }
        return false;
    }

    public boolean b() {
        HashSet<String> hashSet = this.q;
        return (hashSet == null || hashSet.isEmpty()) ? false : true;
    }

    public boolean b(String str) {
        HashSet<String> hashSet;
        return (this.f2513f == null || (hashSet = this.q) == null || hashSet.isEmpty() || !this.q.contains(str)) ? false : true;
    }

    public void c() {
        this.f2513f = null;
        this.q.clear();
    }
}