package com.loc;

/* JADX INFO: compiled from: AmapWifi.java */
/* JADX INFO: loaded from: classes3.dex */
public final class dd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f4968a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f4969b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4971d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f4972e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public short f4974g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4975h;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4970c = -113;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f4973f = 0;

    public dd(boolean z) {
        this.f4975h = z;
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

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        dd ddVar = new dd(this.f4975h);
        ddVar.f4968a = this.f4968a;
        ddVar.f4969b = this.f4969b;
        ddVar.f4970c = this.f4970c;
        ddVar.f4971d = this.f4971d;
        ddVar.f4972e = this.f4972e;
        ddVar.f4973f = this.f4973f;
        ddVar.f4974g = this.f4974g;
        ddVar.f4975h = this.f4975h;
        return ddVar;
    }

    public final String toString() {
        return "AmapWifi{mac=" + this.f4968a + ", ssid='" + this.f4969b + "', rssi=" + this.f4970c + ", frequency=" + this.f4971d + ", timestamp=" + this.f4972e + ", lastUpdateUtcMills=" + this.f4973f + ", freshness=" + ((int) this.f4974g) + ", connected=" + this.f4975h + '}';
    }
}