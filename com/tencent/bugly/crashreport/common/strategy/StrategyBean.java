package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.proguard.ab;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class StrategyBean implements Parcelable {
    public static final Parcelable.Creator<StrategyBean> CREATOR = new Parcelable.Creator<StrategyBean>() { // from class: com.tencent.bugly.crashreport.common.strategy.StrategyBean.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ StrategyBean createFromParcel(Parcel parcel) {
            return new StrategyBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ StrategyBean[] newArray(int i) {
            return new StrategyBean[i];
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f5425a = "https://android.bugly.qq.com/rqd/async";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f5426b = "https://android.bugly.qq.com/rqd/async";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f5427c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f5428d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5429e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5430f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5431g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f5432h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public long n;
    public long o;
    public String p;
    public String q;
    public String r;
    public Map<String, String> s;
    public int t;
    public long u;
    public long v;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public StrategyBean() {
        this.f5427c = -1L;
        this.f5428d = -1L;
        this.f5429e = true;
        this.f5430f = true;
        this.f5431g = true;
        this.f5432h = true;
        this.i = false;
        this.j = true;
        this.k = true;
        this.l = true;
        this.m = true;
        this.o = 30000L;
        this.p = f5425a;
        this.q = f5426b;
        this.t = 10;
        this.u = 300000L;
        this.v = -1L;
        this.f5428d = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("S(@L@L");
        sb.append("@)");
        sb.toString();
        sb.setLength(0);
        sb.append("*^@K#K");
        sb.append("@!");
        this.r = sb.toString();
    }

    public StrategyBean(Parcel parcel) {
        this.f5427c = -1L;
        this.f5428d = -1L;
        boolean z = true;
        this.f5429e = true;
        this.f5430f = true;
        this.f5431g = true;
        this.f5432h = true;
        this.i = false;
        this.j = true;
        this.k = true;
        this.l = true;
        this.m = true;
        this.o = 30000L;
        this.p = f5425a;
        this.q = f5426b;
        this.t = 10;
        this.u = 300000L;
        this.v = -1L;
        try {
            String str = "S(@L@L@)";
            this.f5428d = parcel.readLong();
            this.f5429e = parcel.readByte() == 1;
            this.f5430f = parcel.readByte() == 1;
            this.f5431g = parcel.readByte() == 1;
            this.p = parcel.readString();
            this.q = parcel.readString();
            this.r = parcel.readString();
            this.s = ab.b(parcel);
            this.f5432h = parcel.readByte() == 1;
            this.i = parcel.readByte() == 1;
            this.l = parcel.readByte() == 1;
            this.m = parcel.readByte() == 1;
            this.o = parcel.readLong();
            this.j = parcel.readByte() == 1;
            if (parcel.readByte() != 1) {
                z = false;
            }
            this.k = z;
            this.n = parcel.readLong();
            this.t = parcel.readInt();
            this.u = parcel.readLong();
            this.v = parcel.readLong();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f5428d);
        parcel.writeByte(this.f5429e ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f5430f ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f5431g ? (byte) 1 : (byte) 0);
        parcel.writeString(this.p);
        parcel.writeString(this.q);
        parcel.writeString(this.r);
        ab.b(parcel, this.s);
        parcel.writeByte(this.f5432h ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.i ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.l ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.m ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.o);
        parcel.writeByte(this.j ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.k ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.n);
        parcel.writeInt(this.t);
        parcel.writeLong(this.u);
        parcel.writeLong(this.v);
    }
}