package com.baidu.mapsdkplatform.comapi.synchronization.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class RouteLineInfo implements Parcelable {
    public static final Parcelable.Creator<RouteLineInfo> CREATOR = new b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f3707a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3708b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private CopyOnWriteArrayList<RouteSectionInfo> f3709c;

    public static final class RouteSectionInfo implements Parcelable {
        public static final Parcelable.Creator<RouteSectionInfo> CREATOR = new c();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private LatLng f3710a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private LatLng f3711b;

        public RouteSectionInfo() {
            this.f3710a = null;
            this.f3711b = null;
            this.f3710a = null;
            this.f3711b = null;
        }

        protected RouteSectionInfo(Parcel parcel) {
            this.f3710a = null;
            this.f3711b = null;
            this.f3710a = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
            this.f3711b = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        }

        public LatLng a() {
            return this.f3710a;
        }

        public void a(LatLng latLng) {
            this.f3710a = latLng;
        }

        public LatLng b() {
            return this.f3711b;
        }

        public void b(LatLng latLng) {
            this.f3711b = latLng;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f3710a, i);
            parcel.writeParcelable(this.f3711b, i);
        }
    }

    public RouteLineInfo() {
        this.f3707a = false;
        this.f3708b = null;
        this.f3709c = new CopyOnWriteArrayList<>();
    }

    protected RouteLineInfo(Parcel parcel) {
        this.f3707a = parcel.readByte() != 0;
        this.f3708b = parcel.readString();
    }

    public String a() {
        return this.f3708b;
    }

    public void a(RouteSectionInfo routeSectionInfo) {
        CopyOnWriteArrayList<RouteSectionInfo> copyOnWriteArrayList = this.f3709c;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.add(routeSectionInfo);
        }
    }

    public void a(String str) {
        this.f3708b = str;
    }

    public void a(boolean z) {
        this.f3707a = z;
    }

    public List<RouteSectionInfo> b() {
        return this.f3709c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.f3707a ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f3708b);
        parcel.writeTypedList(this.f3709c);
    }
}