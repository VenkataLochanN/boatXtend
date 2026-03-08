package com.baidu.location.e;

import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import com.baidu.location.g.k;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<ScanResult> f2420a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f2421b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f2422c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2423d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2424e;

    public h(List<ScanResult> list, long j) {
        this.f2420a = null;
        this.f2421b = 0L;
        this.f2422c = 0L;
        this.f2421b = j;
        this.f2420a = list;
        this.f2422c = System.currentTimeMillis();
        try {
            o();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("wpa|wep", 2).matcher(str).find();
    }

    private String b(String str) {
        return str != null ? (str.contains("&") || str.contains(";")) ? str.replace("&", "_").replace(";", "_") : str : str;
    }

    private int n() {
        List<ScanResult> list = this.f2420a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    private void o() {
        /*
            r7 = this;
            int r0 = r7.n()
            r1 = 1
            if (r0 >= r1) goto L8
            return
        L8:
            java.util.List<android.net.wifi.ScanResult> r0 = r7.f2420a
            int r0 = r0.size()
            int r0 = r0 - r1
            r2 = r1
        L10:
            if (r0 < r1) goto L5e
            if (r2 == 0) goto L5e
            r2 = 0
            r3 = r2
        L16:
            if (r2 >= r0) goto L5a
            java.util.List<android.net.wifi.ScanResult> r4 = r7.f2420a
            java.lang.Object r4 = r4.get(r2)
            if (r4 == 0) goto L57
            java.util.List<android.net.wifi.ScanResult> r4 = r7.f2420a
            int r5 = r2 + 1
            java.lang.Object r4 = r4.get(r5)
            if (r4 == 0) goto L57
            java.util.List<android.net.wifi.ScanResult> r4 = r7.f2420a
            java.lang.Object r4 = r4.get(r2)
            android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
            int r4 = r4.level
            java.util.List<android.net.wifi.ScanResult> r6 = r7.f2420a
            java.lang.Object r6 = r6.get(r5)
            android.net.wifi.ScanResult r6 = (android.net.wifi.ScanResult) r6
            int r6 = r6.level
            if (r4 >= r6) goto L57
            java.util.List<android.net.wifi.ScanResult> r3 = r7.f2420a
            java.lang.Object r3 = r3.get(r5)
            android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
            java.util.List<android.net.wifi.ScanResult> r4 = r7.f2420a
            java.lang.Object r6 = r4.get(r2)
            r4.set(r5, r6)
            java.util.List<android.net.wifi.ScanResult> r4 = r7.f2420a
            r4.set(r2, r3)
            r3 = r1
        L57:
            int r2 = r2 + 1
            goto L16
        L5a:
            int r0 = r0 + (-1)
            r2 = r3
            goto L10
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.h.o():void");
    }

    public int a() {
        List<ScanResult> list = this.f2420a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public String a(int i) {
        return a(i, false, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0225 A[Catch: Exception -> 0x005c, Error -> 0x031b, TryCatch #5 {Exception -> 0x005c, blocks: (B:17:0x0053, B:32:0x0077, B:40:0x0092, B:105:0x0215, B:50:0x00c4, B:56:0x00d8, B:58:0x00df, B:64:0x0117, B:66:0x0123, B:69:0x0137, B:71:0x0156, B:73:0x015c, B:101:0x01f5, B:60:0x00fc, B:63:0x0103, B:108:0x0225, B:112:0x023e, B:115:0x0258, B:117:0x025e, B:119:0x026f, B:120:0x0287, B:122:0x028d, B:124:0x0295, B:128:0x02c1, B:125:0x02a0, B:127:0x02af, B:129:0x02c5, B:131:0x02ce, B:133:0x02ee, B:137:0x02fc, B:139:0x0301, B:141:0x030d, B:142:0x0314), top: B:157:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0319 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00d8 A[Catch: Exception -> 0x005c, Error -> 0x031b, TryCatch #5 {Exception -> 0x005c, blocks: (B:17:0x0053, B:32:0x0077, B:40:0x0092, B:105:0x0215, B:50:0x00c4, B:56:0x00d8, B:58:0x00df, B:64:0x0117, B:66:0x0123, B:69:0x0137, B:71:0x0156, B:73:0x015c, B:101:0x01f5, B:60:0x00fc, B:63:0x0103, B:108:0x0225, B:112:0x023e, B:115:0x0258, B:117:0x025e, B:119:0x026f, B:120:0x0287, B:122:0x028d, B:124:0x0295, B:128:0x02c1, B:125:0x02a0, B:127:0x02af, B:129:0x02c5, B:131:0x02ce, B:133:0x02ee, B:137:0x02fc, B:139:0x0301, B:141:0x030d, B:142:0x0314), top: B:157:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00fc A[Catch: Exception -> 0x005c, Error -> 0x031b, TryCatch #5 {Exception -> 0x005c, blocks: (B:17:0x0053, B:32:0x0077, B:40:0x0092, B:105:0x0215, B:50:0x00c4, B:56:0x00d8, B:58:0x00df, B:64:0x0117, B:66:0x0123, B:69:0x0137, B:71:0x0156, B:73:0x015c, B:101:0x01f5, B:60:0x00fc, B:63:0x0103, B:108:0x0225, B:112:0x023e, B:115:0x0258, B:117:0x025e, B:119:0x026f, B:120:0x0287, B:122:0x028d, B:124:0x0295, B:128:0x02c1, B:125:0x02a0, B:127:0x02af, B:129:0x02c5, B:131:0x02ce, B:133:0x02ee, B:137:0x02fc, B:139:0x0301, B:141:0x030d, B:142:0x0314), top: B:157:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0123 A[Catch: Exception -> 0x005c, Error -> 0x031b, TryCatch #5 {Exception -> 0x005c, blocks: (B:17:0x0053, B:32:0x0077, B:40:0x0092, B:105:0x0215, B:50:0x00c4, B:56:0x00d8, B:58:0x00df, B:64:0x0117, B:66:0x0123, B:69:0x0137, B:71:0x0156, B:73:0x015c, B:101:0x01f5, B:60:0x00fc, B:63:0x0103, B:108:0x0225, B:112:0x023e, B:115:0x0258, B:117:0x025e, B:119:0x026f, B:120:0x0287, B:122:0x028d, B:124:0x0295, B:128:0x02c1, B:125:0x02a0, B:127:0x02af, B:129:0x02c5, B:131:0x02ce, B:133:0x02ee, B:137:0x02fc, B:139:0x0301, B:141:0x030d, B:142:0x0314), top: B:157:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(int r27, boolean r28, boolean r29) {
        /*
            Method dump skipped, instruction units count: 801
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.h.a(int, boolean, boolean):java.lang.String");
    }

    public boolean a(long j) {
        long jElapsedRealtimeNanos;
        boolean z;
        List<ScanResult> list;
        long j2;
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos() / 1000;
            } catch (Error | Exception unused) {
                jElapsedRealtimeNanos = 0;
            }
            z = jElapsedRealtimeNanos > 0;
        } else {
            z = false;
            jElapsedRealtimeNanos = 0;
        }
        if (!z || (list = this.f2420a) == null || list.size() == 0) {
            return false;
        }
        int size = this.f2420a.size();
        if (size > 16) {
            size = 16;
        }
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < size; i++) {
            if (this.f2420a.get(i).level != 0 && z) {
                try {
                    j2 = (jElapsedRealtimeNanos - this.f2420a.get(i).timestamp) / 1000000;
                } catch (Error | Exception unused2) {
                    j2 = 0;
                }
                j3 += j2;
                if (j2 > j4) {
                    j4 = j2;
                }
            }
        }
        return j4 * 1000 > j || (j3 / ((long) size)) * 1000 > j;
    }

    public boolean a(h hVar) {
        List<ScanResult> list = this.f2420a;
        if (list == null || hVar == null || hVar.f2420a == null) {
            return false;
        }
        int size = (list.size() < hVar.f2420a.size() ? this.f2420a : hVar.f2420a).size();
        for (int i = 0; i < size; i++) {
            if (!this.f2420a.get(i).BSSID.equals(hVar.f2420a.get(i).BSSID)) {
                return false;
            }
        }
        return true;
    }

    public int b(int i) {
        if (i <= 2400 || i >= 2500) {
            return (i <= 4900 || i >= 5900) ? 0 : 5;
        }
        return 2;
    }

    public String b() {
        try {
            return a(k.O, true, true);
        } catch (Exception unused) {
            return null;
        }
    }

    public boolean b(h hVar) {
        List<ScanResult> list = this.f2420a;
        if (list == null || hVar == null || hVar.f2420a == null) {
            return false;
        }
        int size = (list.size() < hVar.f2420a.size() ? this.f2420a : hVar.f2420a).size();
        for (int i = 0; i < size; i++) {
            String str = this.f2420a.get(i).BSSID;
            int i2 = this.f2420a.get(i).level;
            String str2 = hVar.f2420a.get(i).BSSID;
            int i3 = hVar.f2420a.get(i).level;
            if (!str.equals(str2) || i2 != i3) {
                return false;
            }
        }
        return true;
    }

    public String c() {
        try {
            return a(k.O, true, false);
        } catch (Exception unused) {
            return null;
        }
    }

    public String c(int i) {
        if (a() < 1) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(512);
        int size = this.f2420a.size();
        if (size <= i) {
            i = size;
        }
        boolean z = true;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.f2420a.get(i2).level != 0 && this.f2420a.get(i2).BSSID != null) {
                if (z) {
                    z = false;
                } else {
                    stringBuffer.append("|");
                }
                stringBuffer.append(this.f2420a.get(i2).BSSID.replace(":", ""));
                int i3 = this.f2420a.get(i2).level;
                if (i3 < 0) {
                    i3 = -i3;
                }
                stringBuffer.append(String.format(Locale.CHINA, ";%d;", Integer.valueOf(i3)));
            }
        }
        if (z) {
            return null;
        }
        return stringBuffer.toString();
    }

    public boolean c(h hVar) {
        return i.a(hVar, this);
    }

    public String d() {
        try {
            return a(15);
        } catch (Exception unused) {
            return null;
        }
    }

    public String d(int i) {
        if (i == 0) {
            return null;
        }
        int i2 = 1;
        if (a() < 1) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(256);
        int size = this.f2420a.size();
        if (size > k.O) {
            size = k.O;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            if ((i2 & i) != 0 && this.f2420a.get(i4).BSSID != null) {
                stringBuffer.append(i3 == 0 ? "&ssid=" : "|");
                stringBuffer.append(this.f2420a.get(i4).BSSID.replace(":", ""));
                stringBuffer.append(";");
                stringBuffer.append(b(this.f2420a.get(i4).SSID));
                i3++;
            }
            i2 <<= 1;
        }
        return stringBuffer.toString();
    }

    public boolean e() {
        return a(k.ag);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long f() {
        /*
            r13 = this;
            java.util.List<android.net.wifi.ScanResult> r0 = r13.f2420a
            r1 = 0
            if (r0 == 0) goto L6c
            int r0 = r0.size()
            if (r0 != 0) goto Le
            goto L6c
        Le:
            r3 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r0 = android.os.Build.VERSION.SDK_INT
            r5 = 17
            r6 = 0
            if (r0 < r5) goto L27
            long r7 = android.os.SystemClock.elapsedRealtimeNanos()     // Catch: java.lang.Throwable -> L20
            r9 = 1000(0x3e8, double:4.94E-321)
            long r7 = r7 / r9
            goto L21
        L20:
            r7 = r1
        L21:
            int r0 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r0 <= 0) goto L28
            r0 = 1
            goto L29
        L27:
            r7 = r1
        L28:
            r0 = r6
        L29:
            if (r0 != 0) goto L2c
            return r1
        L2c:
            java.util.List<android.net.wifi.ScanResult> r5 = r13.f2420a
            int r5 = r5.size()
            r9 = 16
            if (r5 <= r9) goto L37
            r5 = r9
        L37:
            if (r6 >= r5) goto L62
            java.util.List<android.net.wifi.ScanResult> r9 = r13.f2420a
            java.lang.Object r9 = r9.get(r6)
            android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
            int r9 = r9.level
            if (r9 != 0) goto L46
            goto L5f
        L46:
            if (r0 == 0) goto L5f
            java.util.List<android.net.wifi.ScanResult> r9 = r13.f2420a     // Catch: java.lang.Throwable -> L59
            java.lang.Object r9 = r9.get(r6)     // Catch: java.lang.Throwable -> L59
            android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9     // Catch: java.lang.Throwable -> L59
            long r9 = r9.timestamp     // Catch: java.lang.Throwable -> L59
            long r9 = r7 - r9
            r11 = 1000000(0xf4240, double:4.940656E-318)
            long r9 = r9 / r11
            goto L5a
        L59:
            r9 = r1
        L5a:
            int r11 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r11 >= 0) goto L5f
            r3 = r9
        L5f:
            int r6 = r6 + 1
            goto L37
        L62:
            if (r0 == 0) goto L65
            goto L66
        L65:
            r3 = r1
        L66:
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 >= 0) goto L6b
            goto L6c
        L6b:
            r1 = r3
        L6c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.h.f():long");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0027 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long g() {
        /*
            r13 = this;
            java.util.List<android.net.wifi.ScanResult> r0 = r13.f2420a
            r1 = 0
            if (r0 == 0) goto L60
            int r0 = r0.size()
            if (r0 != 0) goto Ld
            goto L60
        Ld:
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 17
            r4 = 0
            if (r0 < r3) goto L23
            long r5 = android.os.SystemClock.elapsedRealtimeNanos()     // Catch: java.lang.Throwable -> L1c
            r7 = 1000(0x3e8, double:4.94E-321)
            long r5 = r5 / r7
            goto L1d
        L1c:
            r5 = r1
        L1d:
            int r0 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r0 <= 0) goto L24
            r0 = 1
            goto L25
        L23:
            r5 = r1
        L24:
            r0 = r4
        L25:
            if (r0 != 0) goto L28
            return r1
        L28:
            java.util.List<android.net.wifi.ScanResult> r3 = r13.f2420a
            int r3 = r3.size()
            r7 = 16
            if (r3 <= r7) goto L33
            r3 = r7
        L33:
            r7 = r1
        L34:
            if (r4 >= r3) goto L5f
            java.util.List<android.net.wifi.ScanResult> r9 = r13.f2420a
            java.lang.Object r9 = r9.get(r4)
            android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
            int r9 = r9.level
            if (r9 != 0) goto L43
            goto L5c
        L43:
            if (r0 == 0) goto L5c
            java.util.List<android.net.wifi.ScanResult> r9 = r13.f2420a     // Catch: java.lang.Throwable -> L56
            java.lang.Object r9 = r9.get(r4)     // Catch: java.lang.Throwable -> L56
            android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9     // Catch: java.lang.Throwable -> L56
            long r9 = r9.timestamp     // Catch: java.lang.Throwable -> L56
            long r9 = r5 - r9
            r11 = 1000000(0xf4240, double:4.940656E-318)
            long r9 = r9 / r11
            goto L57
        L56:
            r9 = r1
        L57:
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 <= 0) goto L5c
            r7 = r9
        L5c:
            int r4 = r4 + 1
            goto L34
        L5f:
            return r7
        L60:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.h.g():long");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long h() {
        /*
            r18 = this;
            r0 = r18
            java.util.List<android.net.wifi.ScanResult> r1 = r0.f2420a
            r2 = 0
            if (r1 == 0) goto L75
            int r1 = r1.size()
            if (r1 != 0) goto L10
            goto L75
        L10:
            int r1 = android.os.Build.VERSION.SDK_INT
            r4 = 17
            r5 = 0
            if (r1 < r4) goto L26
            long r6 = android.os.SystemClock.elapsedRealtimeNanos()     // Catch: java.lang.Throwable -> L1f
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 / r8
            goto L20
        L1f:
            r6 = r2
        L20:
            int r1 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r1 <= 0) goto L27
            r1 = 1
            goto L28
        L26:
            r6 = r2
        L27:
            r1 = r5
        L28:
            if (r1 != 0) goto L2b
            return r2
        L2b:
            java.util.List<android.net.wifi.ScanResult> r4 = r0.f2420a
            int r4 = r4.size()
            r8 = 16
            if (r4 <= r8) goto L36
            r4 = r8
        L36:
            r8 = r2
            r10 = r8
            r12 = r10
        L39:
            r14 = 1
            if (r5 >= r4) goto L6c
            java.util.List<android.net.wifi.ScanResult> r2 = r0.f2420a
            java.lang.Object r2 = r2.get(r5)
            android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2
            int r2 = r2.level
            if (r2 != 0) goto L4a
            goto L67
        L4a:
            if (r1 == 0) goto L67
            java.util.List<android.net.wifi.ScanResult> r2 = r0.f2420a     // Catch: java.lang.Throwable -> L5e
            java.lang.Object r2 = r2.get(r5)     // Catch: java.lang.Throwable -> L5e
            android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2     // Catch: java.lang.Throwable -> L5e
            long r2 = r2.timestamp     // Catch: java.lang.Throwable -> L5e
            long r2 = r6 - r2
            r16 = 1000000(0xf4240, double:4.940656E-318)
            long r2 = r2 / r16
            goto L60
        L5e:
            r2 = 0
        L60:
            long r12 = r12 + r2
            long r8 = r8 + r14
            int r14 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r14 <= 0) goto L67
            r10 = r2
        L67:
            int r5 = r5 + 1
            r2 = 0
            goto L39
        L6c:
            int r1 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r1 <= 0) goto L74
            long r12 = r12 - r10
            long r8 = r8 - r14
            long r10 = r12 / r8
        L74:
            return r10
        L75:
            r1 = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.h.h():long");
    }

    public int i() {
        for (int i = 0; i < a(); i++) {
            int i2 = -this.f2420a.get(i).level;
            if (i2 > 0) {
                return i2;
            }
        }
        return 0;
    }

    public boolean j() {
        return this.f2423d;
    }

    public boolean k() {
        return System.currentTimeMillis() - this.f2422c > 0 && System.currentTimeMillis() - this.f2422c < BootloaderScanner.TIMEOUT;
    }

    public boolean l() {
        return System.currentTimeMillis() - this.f2422c > 0 && System.currentTimeMillis() - this.f2422c < BootloaderScanner.TIMEOUT;
    }

    public boolean m() {
        return System.currentTimeMillis() - this.f2422c > 0 && System.currentTimeMillis() - this.f2421b < BootloaderScanner.TIMEOUT;
    }
}