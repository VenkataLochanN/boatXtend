package com.baidu.location.indoor;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f2516a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static d f2517b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f2518c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private BluetoothAdapter f2521f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f2522g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private b f2523h;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2519d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2520e = false;
    private boolean i = false;
    private String j = null;
    private long k = -1;
    private HashMap<String, ScanResult> l = new HashMap<>();
    private Handler m = new Handler();
    private Runnable n = new e(this);
    private Object o = null;

    public static class a implements Comparable<a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2524a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2525b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f2526c;

        public a(String str, int i, long j) {
            this.f2524a = str;
            this.f2525b = i;
            this.f2526c = j / 1000000;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(a aVar) {
            return Math.abs(this.f2525b) > Math.abs(aVar.f2525b) ? 1 : 0;
        }

        public String toString() {
            return this.f2524a.toUpperCase() + ";" + this.f2525b + ";" + this.f2526c;
        }
    }

    public interface b {
        void a(boolean z, String str, String str2, String str3);
    }

    private d(Context context) {
        this.f2522g = false;
        this.f2518c = context;
        if (this.f2521f == null) {
            try {
                if (Build.VERSION.SDK_INT > 18) {
                    this.f2521f = ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter();
                    this.f2522g = this.f2518c.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
                } else {
                    this.f2521f = BluetoothAdapter.getDefaultAdapter();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static d a(Context context) {
        if (f2517b == null) {
            f2517b = new d(context);
        }
        return f2517b;
    }

    private String a(List<a> list, int i) {
        StringBuilder sb = new StringBuilder();
        Collections.sort(list);
        sb.append(list.get(0).toString());
        for (int i2 = 1; i2 < list.size() && i2 < i; i2++) {
            sb.append("|");
            sb.append(list.get(i2).toString());
        }
        return sb.toString();
    }

    public static String a(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & UByte.MAX_VALUE;
            int i3 = i * 2;
            char[] cArr2 = f2516a;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HashMap<String, ScanResult> map) {
        ArrayList<ScanResult> arrayList = new ArrayList(map.values());
        ArrayList arrayList2 = new ArrayList();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        HashMap map4 = new HashMap();
        ArrayList arrayList3 = new ArrayList();
        for (ScanResult scanResult : arrayList) {
            arrayList3.add(new a(scanResult.getDevice().getAddress().replaceAll(":", ""), scanResult.getRssi(), scanResult.getTimestampNanos()));
            if (this.f2519d) {
                scanResult.getScanRecord().getAdvertiseFlags();
                byte[] bytes = scanResult.getScanRecord().getBytes();
                if (bytes.length >= 26) {
                    String strA = a(Arrays.copyOfRange(bytes, 9, 25));
                    arrayList2.add(strA);
                    map2.put(strA, scanResult.getDevice().getName());
                    map3.put(strA, a(Arrays.copyOfRange(bytes, 0, 9)));
                    if (map4.get(strA) == null) {
                        map4.put(strA, 0);
                    }
                    map4.put(strA, Integer.valueOf(((Integer) map4.get(strA)).intValue() + 1));
                }
            }
        }
        String str = null;
        int iIntValue = 0;
        for (String str2 : map4.keySet()) {
            if (((Integer) map4.get(str2)).intValue() > iIntValue) {
                iIntValue = ((Integer) map4.get(str2)).intValue();
                str = str2;
            }
        }
        boolean z = iIntValue > 3;
        b bVar = this.f2523h;
        if (bVar != null && this.f2519d) {
            bVar.a(z, str, (String) map2.get(str), (String) map3.get(str));
            this.f2519d = false;
        }
        if (arrayList3.size() > 3) {
            this.j = a(arrayList3, 32);
            this.k = System.currentTimeMillis();
        }
        if (this.i) {
            a(true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0069 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean h() {
        /*
            r7 = this;
            java.lang.String r0 = ""
            java.io.File r1 = new java.io.File
            android.content.Context r2 = r7.f2518c
            java.io.File r2 = r2.getCacheDir()
            java.lang.String r3 = "ibct"
            r1.<init>(r2, r3)
            boolean r2 = r1.exists()
            r3 = 0
            if (r2 != 0) goto L17
            return r3
        L17:
            r2 = 0
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L41
            java.io.FileReader r5 = new java.io.FileReader     // Catch: java.lang.Exception -> L41
            r5.<init>(r1)     // Catch: java.lang.Exception -> L41
            r4.<init>(r5)     // Catch: java.lang.Exception -> L41
            r1 = r2
            r2 = r0
        L24:
            java.lang.String r1 = r4.readLine()     // Catch: java.lang.Exception -> L3f
            if (r1 == 0) goto L3a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L3f
            r5.<init>()     // Catch: java.lang.Exception -> L3f
            r5.append(r2)     // Catch: java.lang.Exception -> L3f
            r5.append(r1)     // Catch: java.lang.Exception -> L3f
            java.lang.String r2 = r5.toString()     // Catch: java.lang.Exception -> L3f
            goto L24
        L3a:
            r4.close()     // Catch: java.lang.Exception -> L41
            r1 = r2
            goto L48
        L3f:
            r2 = move-exception
            goto L45
        L41:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L45:
            r2.printStackTrace()
        L48:
            if (r1 == 0) goto L6b
            java.lang.String r2 = r1.trim()
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L55
            goto L6b
        L55:
            java.lang.Long r0 = java.lang.Long.valueOf(r1)     // Catch: java.lang.Exception -> L6b
            long r0 = r0.longValue()     // Catch: java.lang.Exception -> L6b
            long r4 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L6b
            long r4 = r4 - r0
            r0 = 259200(0x3f480, double:1.28062E-318)
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 >= 0) goto L6b
            r0 = 1
            return r0
        L6b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.indoor.d.h():boolean");
    }

    private void i() {
        try {
            FileWriter fileWriter = new FileWriter(new File(this.f2518c.getCacheDir(), "ibct"));
            fileWriter.write(System.currentTimeMillis() + "");
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception unused) {
        }
    }

    public void a(boolean z) {
        boolean z2;
        if (this.f2521f == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                if (z) {
                    this.o = new f(this);
                    this.f2521f.getBluetoothLeScanner().startScan((ScanCallback) this.o);
                    this.m.postDelayed(this.n, 3000L);
                    z2 = true;
                } else {
                    if (this.f2523h != null) {
                        this.f2521f.getBluetoothLeScanner().stopScan((ScanCallback) this.o);
                    }
                    z2 = false;
                }
                this.f2519d = z2;
            }
        } catch (Exception unused) {
        }
    }

    public boolean a() {
        BluetoothAdapter bluetoothAdapter = this.f2521f;
        if (bluetoothAdapter != null && this.f2522g) {
            try {
                return bluetoothAdapter.isEnabled();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public boolean a(b bVar) {
        if (this.f2519d || this.f2520e) {
            return false;
        }
        this.f2520e = true;
        if (!a() || h()) {
            return false;
        }
        i();
        this.f2523h = bVar;
        a(true);
        return true;
    }

    public boolean b() {
        if (!a()) {
            return false;
        }
        a(true);
        this.i = true;
        return true;
    }

    public void c() {
        this.f2520e = false;
        this.f2519d = false;
    }

    public void d() {
        this.i = false;
    }

    public String e() {
        return this.j;
    }

    public long f() {
        return this.k;
    }

    public boolean g() {
        return System.currentTimeMillis() - this.k <= 20000;
    }
}