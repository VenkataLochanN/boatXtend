package com.baidu.mapsdkplatform.comapi.synchronization.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class SyncResponseResult implements Parcelable {
    public static final Parcelable.Creator<SyncResponseResult> CREATOR = new j();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private RouteLineInfo f3712a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TrafficInfo f3713b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DriverPosition f3714c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f3715d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f3716e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f3717f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long f3718g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f3719h;
    private String i;
    private String j;

    public SyncResponseResult() {
        this.f3712a = new RouteLineInfo();
        this.f3713b = new TrafficInfo();
        this.f3714c = new DriverPosition();
        this.f3715d = 0.0f;
        this.f3716e = 0L;
        this.f3717f = 0.0f;
        this.f3718g = 0L;
        this.f3719h = 0;
        this.i = null;
        this.j = null;
    }

    protected SyncResponseResult(Parcel parcel) {
        this.f3712a = (RouteLineInfo) parcel.readParcelable(RouteLineInfo.class.getClassLoader());
        this.f3713b = (TrafficInfo) parcel.readParcelable(TrafficInfo.class.getClassLoader());
        this.f3714c = (DriverPosition) parcel.readParcelable(DriverPosition.class.getClassLoader());
        this.f3715d = parcel.readLong();
        this.f3716e = parcel.readLong();
        this.f3717f = parcel.readLong();
        this.f3718g = parcel.readLong();
        this.f3719h = parcel.readInt();
        this.i = parcel.readString();
        this.j = parcel.readString();
    }

    public RouteLineInfo a() {
        return this.f3712a;
    }

    public void a(float f2) {
        this.f3715d = f2;
    }

    public void a(int i) {
        this.f3719h = i;
    }

    public void a(long j) {
        this.f3716e = j;
    }

    public void a(String str) {
        this.i = str;
    }

    public TrafficInfo b() {
        return this.f3713b;
    }

    public void b(float f2) {
        this.f3717f = f2;
    }

    public void b(long j) {
        this.f3718g = j;
    }

    public void b(String str) {
        this.j = str;
    }

    public DriverPosition c() {
        return this.f3714c;
    }

    public float d() {
        return this.f3717f;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long e() {
        return this.f3718g;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3712a, 1);
        parcel.writeParcelable(this.f3713b, 1);
        parcel.writeParcelable(this.f3714c, 1);
        parcel.writeFloat(this.f3715d);
        parcel.writeLong(this.f3716e);
        parcel.writeFloat(this.f3717f);
        parcel.writeLong(this.f3718g);
        parcel.writeInt(this.f3719h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
    }
}