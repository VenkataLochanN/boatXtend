package com.baidu.location.e;

import com.ido.common.utils.FileSizeUtil;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2385a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2386b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2387c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2388d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2389e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2390f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f2391g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2392h;
    public char i;
    public String j;
    private boolean k;

    public a() {
        this.f2385a = -1;
        this.f2386b = -1;
        this.f2387c = -1;
        this.f2388d = -1;
        this.f2389e = Integer.MAX_VALUE;
        this.f2390f = Integer.MAX_VALUE;
        this.f2391g = 0L;
        this.f2392h = -1;
        this.i = '0';
        this.j = null;
        this.k = false;
        this.f2391g = System.currentTimeMillis();
    }

    public a(int i, int i2, int i3, int i4, int i5, char c2) {
        this.f2385a = -1;
        this.f2386b = -1;
        this.f2387c = -1;
        this.f2388d = -1;
        this.f2389e = Integer.MAX_VALUE;
        this.f2390f = Integer.MAX_VALUE;
        this.f2391g = 0L;
        this.f2392h = -1;
        this.i = '0';
        this.j = null;
        this.k = false;
        this.f2385a = i;
        this.f2386b = i2;
        this.f2387c = i3;
        this.f2388d = i4;
        this.f2392h = i5;
        this.i = c2;
        this.f2391g = System.currentTimeMillis();
    }

    public a(a aVar) {
        this(aVar.f2385a, aVar.f2386b, aVar.f2387c, aVar.f2388d, aVar.f2392h, aVar.i);
        this.f2391g = aVar.f2391g;
    }

    public boolean a() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.f2391g;
        return jCurrentTimeMillis - j > 0 && jCurrentTimeMillis - j < 3000;
    }

    public boolean a(a aVar) {
        return this.f2385a == aVar.f2385a && this.f2386b == aVar.f2386b && this.f2388d == aVar.f2388d && this.f2387c == aVar.f2387c;
    }

    public boolean b() {
        return this.f2385a > -1 && this.f2386b > 0;
    }

    public boolean c() {
        return this.f2385a == -1 && this.f2386b == -1 && this.f2388d == -1 && this.f2387c == -1;
    }

    public boolean d() {
        return this.f2385a > -1 && this.f2386b > -1 && this.f2388d == -1 && this.f2387c == -1;
    }

    public boolean e() {
        return this.f2385a > -1 && this.f2386b > -1 && this.f2388d > -1 && this.f2387c > -1;
    }

    public void f() {
        this.k = true;
    }

    public String g() {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(this.f2386b + 23);
        stringBuffer.append("H");
        stringBuffer.append(this.f2385a + 45);
        stringBuffer.append(FileSizeUtil.UNIT_KB);
        stringBuffer.append(this.f2388d + 54);
        stringBuffer.append("Q");
        stringBuffer.append(this.f2387c + com.veryfit.multi.nativeprotocol.b.H0);
        return stringBuffer.toString();
    }

    public String h() {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&nw=");
        stringBuffer.append(this.i);
        stringBuffer.append(String.format(Locale.CHINA, "&cl=%d|%d|%d|%d&cl_s=%d", Integer.valueOf(this.f2387c), Integer.valueOf(this.f2388d), Integer.valueOf(this.f2385a), Integer.valueOf(this.f2386b), Integer.valueOf(this.f2392h)));
        if (this.k) {
            stringBuffer.append("&newcl=1");
        }
        return stringBuffer.toString();
    }

    public String i() {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&nw2=");
        stringBuffer.append(this.i);
        stringBuffer.append(String.format(Locale.CHINA, "&cl2=%d|%d|%d|%d&cl_s2=%d", Integer.valueOf(this.f2387c), Integer.valueOf(this.f2388d), Integer.valueOf(this.f2385a), Integer.valueOf(this.f2386b), Integer.valueOf(this.f2392h)));
        return stringBuffer.toString();
    }
}