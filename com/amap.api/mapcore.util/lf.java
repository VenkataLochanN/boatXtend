package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: AmapWifi.java */
/* JADX INFO: loaded from: classes.dex */
public final class lf {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f1651a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f1652b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1654d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f1655e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public short f1657g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1658h;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1653c = -113;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f1656f = 0;

    public lf(boolean z) {
        this.f1658h = z;
    }

    public static long a(String str) {
        long j;
        if (str == null || str.length() == 0) {
            return 0L;
        }
        int i = 0;
        long j2 = 0;
        for (int length = str.length() - 1; length >= 0; length--) {
            long jCharAt = str.charAt(length);
            if (jCharAt < 48 || jCharAt > 57) {
                long j3 = 97;
                if (jCharAt < 97 || jCharAt > 102) {
                    j3 = 65;
                    if (jCharAt < 65 || jCharAt > 70) {
                        if (jCharAt != 58 && jCharAt != 124) {
                            return 0L;
                        }
                    }
                }
                j = (jCharAt - j3) + 10;
            } else {
                j = jCharAt - 48;
            }
            j2 += j << i;
            i += 4;
        }
        if (i != 48) {
            return 0L;
        }
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public lf clone() {
        lf lfVar = new lf(this.f1658h);
        lfVar.f1651a = this.f1651a;
        lfVar.f1652b = this.f1652b;
        lfVar.f1653c = this.f1653c;
        lfVar.f1654d = this.f1654d;
        lfVar.f1655e = this.f1655e;
        lfVar.f1656f = this.f1656f;
        lfVar.f1657g = this.f1657g;
        lfVar.f1658h = this.f1658h;
        return lfVar;
    }

    public final String toString() {
        return "AmapWifi{mac=" + this.f1651a + ", ssid='" + this.f1652b + "', rssi=" + this.f1653c + ", frequency=" + this.f1654d + ", timestamp=" + this.f1655e + ", lastUpdateUtcMills=" + this.f1656f + ", freshness=" + ((int) this.f1657g) + ", connected=" + this.f1658h + '}';
    }
}