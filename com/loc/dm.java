package com.loc;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: AMapLocationStaticsEntity.java */
/* JADX INFO: loaded from: classes3.dex */
public final class dm implements Parcelable {
    public static final Parcelable.Creator<dm> CREATOR = new Parcelable.Creator<dm>() { // from class: com.loc.dm.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ dm createFromParcel(Parcel parcel) {
            dm dmVar = new dm();
            dmVar.c(parcel.readString());
            dmVar.d(parcel.readString());
            dmVar.e(parcel.readString());
            dmVar.f(parcel.readString());
            dmVar.b(parcel.readString());
            dmVar.c(parcel.readLong());
            dmVar.d(parcel.readLong());
            dmVar.a(parcel.readLong());
            dmVar.b(parcel.readLong());
            dmVar.a(parcel.readString());
            return dmVar;
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ dm[] newArray(int i) {
            return new dm[i];
        }
    };

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5005e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5006f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f5001a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f5002b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f5003c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f5004d = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f5007g = "first";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f5008h = "";
    private String i = "";
    private String j = null;

    public final long a() {
        long j = this.f5004d;
        long j2 = this.f5003c;
        if (j - j2 <= 0) {
            return 0L;
        }
        return j - j2;
    }

    public final void a(long j) {
        this.f5003c = j;
    }

    public final void a(String str) {
        this.i = str;
    }

    public final String b() {
        return this.i;
    }

    public final void b(long j) {
        this.f5004d = j;
    }

    public final void b(String str) {
        this.j = str;
    }

    public final String c() {
        return this.j;
    }

    public final void c(long j) {
        this.f5001a = j;
    }

    public final void c(String str) {
        this.f5005e = str;
    }

    public final String d() {
        return this.f5005e;
    }

    public final void d(long j) {
        this.f5002b = j;
    }

    public final void d(String str) {
        this.f5006f = str;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String e() {
        return this.f5006f;
    }

    public final void e(String str) {
        this.f5007g = str;
    }

    public final String f() {
        return this.f5007g;
    }

    public final void f(String str) {
        this.f5008h = str;
    }

    public final String g() {
        return this.f5008h;
    }

    public final long h() {
        long j = this.f5002b;
        long j2 = this.f5001a;
        if (j <= j2) {
            return 0L;
        }
        return j - j2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        try {
            parcel.writeString(this.f5005e);
            parcel.writeString(this.f5006f);
            parcel.writeString(this.f5007g);
            parcel.writeString(this.f5008h);
            parcel.writeString(this.j);
            parcel.writeLong(this.f5001a);
            parcel.writeLong(this.f5002b);
            parcel.writeLong(this.f5003c);
            parcel.writeLong(this.f5004d);
            parcel.writeString(this.i);
        } catch (Throwable unused) {
        }
    }
}