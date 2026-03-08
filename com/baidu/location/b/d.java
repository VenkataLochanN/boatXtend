package com.baidu.location.b;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static d f2226d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f2227a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2228b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f2229c = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2230e = -1;

    public class a extends BroadcastReceiver {
        public a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x005d  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x006d A[Catch: Exception -> 0x0075, TRY_LEAVE, TryCatch #0 {Exception -> 0x0075, blocks: (B:3:0x0005, B:5:0x000d, B:8:0x0030, B:16:0x0047, B:24:0x0060, B:25:0x0067, B:26:0x006d, B:17:0x004d, B:18:0x0051, B:19:0x0055, B:9:0x0039), top: B:30:0x0005 }] */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReceive(android.content.Context r6, android.content.Intent r7) {
            /*
                r5 = this;
                java.lang.String r6 = r7.getAction()
                r0 = 0
                java.lang.String r1 = "android.intent.action.BATTERY_CHANGED"
                boolean r6 = r6.equals(r1)     // Catch: java.lang.Exception -> L75
                if (r6 == 0) goto L7a
                com.baidu.location.b.d r6 = com.baidu.location.b.d.this     // Catch: java.lang.Exception -> L75
                r1 = 0
                com.baidu.location.b.d.a(r6, r1)     // Catch: java.lang.Exception -> L75
                java.lang.String r6 = "status"
                int r6 = r7.getIntExtra(r6, r1)     // Catch: java.lang.Exception -> L75
                java.lang.String r2 = "plugged"
                int r1 = r7.getIntExtra(r2, r1)     // Catch: java.lang.Exception -> L75
                java.lang.String r2 = "level"
                r3 = -1
                int r2 = r7.getIntExtra(r2, r3)     // Catch: java.lang.Exception -> L75
                java.lang.String r4 = "scale"
                int r7 = r7.getIntExtra(r4, r3)     // Catch: java.lang.Exception -> L75
                if (r2 <= 0) goto L39
                if (r7 <= 0) goto L39
                com.baidu.location.b.d r3 = com.baidu.location.b.d.this     // Catch: java.lang.Exception -> L75
                int r2 = r2 * 100
                int r2 = r2 / r7
                com.baidu.location.b.d.a(r3, r2)     // Catch: java.lang.Exception -> L75
                goto L3e
            L39:
                com.baidu.location.b.d r7 = com.baidu.location.b.d.this     // Catch: java.lang.Exception -> L75
                com.baidu.location.b.d.a(r7, r3)     // Catch: java.lang.Exception -> L75
            L3e:
                r7 = 2
                if (r6 == r7) goto L55
                r2 = 3
                if (r6 == r2) goto L4d
                r2 = 4
                if (r6 == r2) goto L4d
                com.baidu.location.b.d r6 = com.baidu.location.b.d.this     // Catch: java.lang.Exception -> L75
                com.baidu.location.b.d.a(r6, r0)     // Catch: java.lang.Exception -> L75
                goto L5a
            L4d:
                com.baidu.location.b.d r6 = com.baidu.location.b.d.this     // Catch: java.lang.Exception -> L75
                java.lang.String r2 = "3"
            L51:
                com.baidu.location.b.d.a(r6, r2)     // Catch: java.lang.Exception -> L75
                goto L5a
            L55:
                com.baidu.location.b.d r6 = com.baidu.location.b.d.this     // Catch: java.lang.Exception -> L75
                java.lang.String r2 = "4"
                goto L51
            L5a:
                r6 = 1
                if (r1 == r6) goto L6d
                if (r1 == r7) goto L60
                goto L7a
            L60:
                com.baidu.location.b.d r7 = com.baidu.location.b.d.this     // Catch: java.lang.Exception -> L75
                java.lang.String r1 = "5"
                com.baidu.location.b.d.a(r7, r1)     // Catch: java.lang.Exception -> L75
            L67:
                com.baidu.location.b.d r7 = com.baidu.location.b.d.this     // Catch: java.lang.Exception -> L75
                com.baidu.location.b.d.a(r7, r6)     // Catch: java.lang.Exception -> L75
                goto L7a
            L6d:
                com.baidu.location.b.d r7 = com.baidu.location.b.d.this     // Catch: java.lang.Exception -> L75
                java.lang.String r1 = "6"
                com.baidu.location.b.d.a(r7, r1)     // Catch: java.lang.Exception -> L75
                goto L67
            L75:
                com.baidu.location.b.d r6 = com.baidu.location.b.d.this
                com.baidu.location.b.d.a(r6, r0)
            L7a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.d.a.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    private d() {
    }

    public static synchronized d a() {
        if (f2226d == null) {
            f2226d = new d();
        }
        return f2226d;
    }

    public void b() {
        this.f2229c = new a();
        try {
            com.baidu.location.f.getServiceContext().registerReceiver(this.f2229c, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        } catch (Exception unused) {
        }
    }

    public void c() {
        if (this.f2229c != null) {
            try {
                com.baidu.location.f.getServiceContext().unregisterReceiver(this.f2229c);
            } catch (Exception unused) {
            }
        }
        this.f2229c = null;
    }

    public String d() {
        return this.f2228b;
    }

    public boolean e() {
        return this.f2227a;
    }

    public int f() {
        return this.f2230e;
    }
}