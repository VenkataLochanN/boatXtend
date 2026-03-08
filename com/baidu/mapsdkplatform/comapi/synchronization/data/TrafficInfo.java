package com.baidu.mapsdkplatform.comapi.synchronization.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TrafficInfo implements Parcelable {
    public static final Parcelable.Creator<TrafficInfo> CREATOR = new n();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f3720a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3721b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ArrayList<Integer> f3722c;

    public TrafficInfo() {
        this.f3720a = false;
        this.f3721b = null;
        this.f3720a = false;
        this.f3721b = null;
        this.f3722c = new ArrayList<>();
    }

    protected TrafficInfo(Parcel parcel) {
        this.f3720a = false;
        this.f3721b = null;
        this.f3720a = parcel.readByte() != 0;
        this.f3721b = parcel.readString();
    }

    public String a() {
        return this.f3721b;
    }

    public void a(String str) {
        this.f3721b = str;
    }

    public void a(ArrayList<Integer> arrayList) {
        this.f3722c = arrayList;
    }

    public void a(boolean z) {
        this.f3720a = z;
    }

    public ArrayList<Integer> b() {
        return this.f3722c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.f3720a ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f3721b);
    }
}