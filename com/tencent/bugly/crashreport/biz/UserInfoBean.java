package com.tencent.bugly.crashreport.biz;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.proguard.ab;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class UserInfoBean implements Parcelable {
    public static final Parcelable.Creator<UserInfoBean> CREATOR = new Parcelable.Creator<UserInfoBean>() { // from class: com.tencent.bugly.crashreport.biz.UserInfoBean.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ UserInfoBean createFromParcel(Parcel parcel) {
            return new UserInfoBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ UserInfoBean[] newArray(int i) {
            return new UserInfoBean[i];
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f5379a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5380b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f5381c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f5382d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f5383e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f5384f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f5385g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f5386h;
    public long i;
    public String j;
    public long k;
    public boolean l;
    public String m;
    public String n;
    public int o;
    public int p;
    public int q;
    public Map<String, String> r;
    public Map<String, String> s;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UserInfoBean() {
        this.k = 0L;
        this.l = false;
        this.m = "unknown";
        this.p = -1;
        this.q = -1;
        this.r = null;
        this.s = null;
    }

    public UserInfoBean(Parcel parcel) {
        this.k = 0L;
        this.l = false;
        this.m = "unknown";
        this.p = -1;
        this.q = -1;
        this.r = null;
        this.s = null;
        this.f5380b = parcel.readInt();
        this.f5381c = parcel.readString();
        this.f5382d = parcel.readString();
        this.f5383e = parcel.readLong();
        this.f5384f = parcel.readLong();
        this.f5385g = parcel.readLong();
        this.f5386h = parcel.readLong();
        this.i = parcel.readLong();
        this.j = parcel.readString();
        this.k = parcel.readLong();
        this.l = parcel.readByte() == 1;
        this.m = parcel.readString();
        this.p = parcel.readInt();
        this.q = parcel.readInt();
        this.r = ab.b(parcel);
        this.s = ab.b(parcel);
        this.n = parcel.readString();
        this.o = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f5380b);
        parcel.writeString(this.f5381c);
        parcel.writeString(this.f5382d);
        parcel.writeLong(this.f5383e);
        parcel.writeLong(this.f5384f);
        parcel.writeLong(this.f5385g);
        parcel.writeLong(this.f5386h);
        parcel.writeLong(this.i);
        parcel.writeString(this.j);
        parcel.writeLong(this.k);
        parcel.writeByte(this.l ? (byte) 1 : (byte) 0);
        parcel.writeString(this.m);
        parcel.writeInt(this.p);
        parcel.writeInt(this.q);
        ab.b(parcel, this.r);
        ab.b(parcel, this.s);
        parcel.writeString(this.n);
        parcel.writeInt(this.o);
    }
}