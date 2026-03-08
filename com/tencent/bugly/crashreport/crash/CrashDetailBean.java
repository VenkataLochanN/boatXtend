package com.tencent.bugly.crashreport.crash;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.proguard.ab;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class CrashDetailBean implements Parcelable, Comparable<CrashDetailBean> {
    public static final Parcelable.Creator<CrashDetailBean> CREATOR = new Parcelable.Creator<CrashDetailBean>() { // from class: com.tencent.bugly.crashreport.crash.CrashDetailBean.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ CrashDetailBean createFromParcel(Parcel parcel) {
            return new CrashDetailBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ CrashDetailBean[] newArray(int i) {
            return new CrashDetailBean[i];
        }
    };
    public String A;
    public String B;
    public long C;
    public long D;
    public long E;
    public long F;
    public long G;
    public long H;
    public long I;
    public long J;
    public long K;
    public String L;
    public String M;
    public String N;
    public String O;
    public long P;
    public boolean Q;
    public Map<String, String> R;
    public Map<String, String> S;
    public int T;
    public int U;
    public Map<String, String> V;
    public Map<String, String> W;
    public byte[] X;
    public String Y;
    public String Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f5449a;
    private String aa;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5450b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f5451c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5452d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f5453e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f5454f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f5455g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Map<String, PlugInBean> f5456h;
    public Map<String, PlugInBean> i;
    public boolean j;
    public boolean k;
    public int l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public long r;
    public String s;
    public int t;
    public String u;
    public String v;
    public String w;
    public String x;
    public byte[] y;
    public Map<String, String> z;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(CrashDetailBean crashDetailBean) {
        CrashDetailBean crashDetailBean2 = crashDetailBean;
        if (crashDetailBean2 == null) {
            return 1;
        }
        long j = this.r - crashDetailBean2.r;
        if (j <= 0) {
            return j < 0 ? -1 : 0;
        }
        return 1;
    }

    public CrashDetailBean() {
        this.f5449a = -1L;
        this.f5450b = 0;
        this.f5451c = UUID.randomUUID().toString();
        this.f5452d = false;
        this.f5453e = "";
        this.f5454f = "";
        this.f5455g = "";
        this.f5456h = null;
        this.i = null;
        this.j = false;
        this.k = false;
        this.l = 0;
        this.m = "";
        this.n = "";
        this.o = "";
        this.p = "";
        this.q = "";
        this.r = -1L;
        this.s = null;
        this.t = 0;
        this.u = "";
        this.v = "";
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = "";
        this.B = "";
        this.C = -1L;
        this.D = -1L;
        this.E = -1L;
        this.F = -1L;
        this.G = -1L;
        this.H = -1L;
        this.I = -1L;
        this.J = -1L;
        this.K = -1L;
        this.L = "";
        this.aa = "";
        this.M = "";
        this.N = "";
        this.O = "";
        this.P = -1L;
        this.Q = false;
        this.R = null;
        this.S = null;
        this.T = -1;
        this.U = -1;
        this.V = null;
        this.W = null;
        this.X = null;
        this.Y = null;
        this.Z = null;
    }

    public CrashDetailBean(Parcel parcel) {
        this.f5449a = -1L;
        this.f5450b = 0;
        this.f5451c = UUID.randomUUID().toString();
        this.f5452d = false;
        this.f5453e = "";
        this.f5454f = "";
        this.f5455g = "";
        this.f5456h = null;
        this.i = null;
        this.j = false;
        this.k = false;
        this.l = 0;
        this.m = "";
        this.n = "";
        this.o = "";
        this.p = "";
        this.q = "";
        this.r = -1L;
        this.s = null;
        this.t = 0;
        this.u = "";
        this.v = "";
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = "";
        this.B = "";
        this.C = -1L;
        this.D = -1L;
        this.E = -1L;
        this.F = -1L;
        this.G = -1L;
        this.H = -1L;
        this.I = -1L;
        this.J = -1L;
        this.K = -1L;
        this.L = "";
        this.aa = "";
        this.M = "";
        this.N = "";
        this.O = "";
        this.P = -1L;
        this.Q = false;
        this.R = null;
        this.S = null;
        this.T = -1;
        this.U = -1;
        this.V = null;
        this.W = null;
        this.X = null;
        this.Y = null;
        this.Z = null;
        this.f5450b = parcel.readInt();
        this.f5451c = parcel.readString();
        this.f5452d = parcel.readByte() == 1;
        this.f5453e = parcel.readString();
        this.f5454f = parcel.readString();
        this.f5455g = parcel.readString();
        this.j = parcel.readByte() == 1;
        this.k = parcel.readByte() == 1;
        this.l = parcel.readInt();
        this.m = parcel.readString();
        this.n = parcel.readString();
        this.o = parcel.readString();
        this.p = parcel.readString();
        this.q = parcel.readString();
        this.r = parcel.readLong();
        this.s = parcel.readString();
        this.t = parcel.readInt();
        this.u = parcel.readString();
        this.v = parcel.readString();
        this.w = parcel.readString();
        this.z = ab.b(parcel);
        this.A = parcel.readString();
        this.B = parcel.readString();
        this.C = parcel.readLong();
        this.D = parcel.readLong();
        this.E = parcel.readLong();
        this.F = parcel.readLong();
        this.G = parcel.readLong();
        this.H = parcel.readLong();
        this.L = parcel.readString();
        this.aa = parcel.readString();
        this.M = parcel.readString();
        this.N = parcel.readString();
        this.O = parcel.readString();
        this.P = parcel.readLong();
        this.Q = parcel.readByte() == 1;
        this.R = ab.b(parcel);
        this.f5456h = ab.a(parcel);
        this.i = ab.a(parcel);
        this.T = parcel.readInt();
        this.U = parcel.readInt();
        this.V = ab.b(parcel);
        this.W = ab.b(parcel);
        this.X = parcel.createByteArray();
        this.y = parcel.createByteArray();
        this.Y = parcel.readString();
        this.Z = parcel.readString();
        this.x = parcel.readString();
        this.I = parcel.readLong();
        this.J = parcel.readLong();
        this.K = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f5450b);
        parcel.writeString(this.f5451c);
        parcel.writeByte(this.f5452d ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f5453e);
        parcel.writeString(this.f5454f);
        parcel.writeString(this.f5455g);
        parcel.writeByte(this.j ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.k ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.q);
        parcel.writeLong(this.r);
        parcel.writeString(this.s);
        parcel.writeInt(this.t);
        parcel.writeString(this.u);
        parcel.writeString(this.v);
        parcel.writeString(this.w);
        ab.b(parcel, this.z);
        parcel.writeString(this.A);
        parcel.writeString(this.B);
        parcel.writeLong(this.C);
        parcel.writeLong(this.D);
        parcel.writeLong(this.E);
        parcel.writeLong(this.F);
        parcel.writeLong(this.G);
        parcel.writeLong(this.H);
        parcel.writeString(this.L);
        parcel.writeString(this.aa);
        parcel.writeString(this.M);
        parcel.writeString(this.N);
        parcel.writeString(this.O);
        parcel.writeLong(this.P);
        parcel.writeByte(this.Q ? (byte) 1 : (byte) 0);
        ab.b(parcel, this.R);
        ab.a(parcel, this.f5456h);
        ab.a(parcel, this.i);
        parcel.writeInt(this.T);
        parcel.writeInt(this.U);
        ab.b(parcel, this.V);
        ab.b(parcel, this.W);
        parcel.writeByteArray(this.X);
        parcel.writeByteArray(this.y);
        parcel.writeString(this.Y);
        parcel.writeString(this.Z);
        parcel.writeString(this.x);
        parcel.writeLong(this.I);
        parcel.writeLong(this.J);
        parcel.writeLong(this.K);
    }
}