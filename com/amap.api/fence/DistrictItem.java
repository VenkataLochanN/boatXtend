package com.amap.api.fence;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.location.DPoint;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DistrictItem implements Parcelable {
    public static final Parcelable.Creator<DistrictItem> CREATOR = new Parcelable.Creator<DistrictItem>() { // from class: com.amap.api.fence.DistrictItem.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ DistrictItem createFromParcel(Parcel parcel) {
            return new DistrictItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ DistrictItem[] newArray(int i) {
            return new DistrictItem[i];
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f29a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f30b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f31c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<DPoint> f32d;

    public DistrictItem() {
        this.f29a = "";
        this.f30b = null;
        this.f31c = null;
        this.f32d = null;
    }

    protected DistrictItem(Parcel parcel) {
        this.f29a = "";
        this.f30b = null;
        this.f31c = null;
        this.f32d = null;
        this.f29a = parcel.readString();
        this.f30b = parcel.readString();
        this.f31c = parcel.readString();
        this.f32d = parcel.createTypedArrayList(DPoint.CREATOR);
    }

    public static Parcelable.Creator<DistrictItem> getCreator() {
        return CREATOR;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAdcode() {
        return this.f31c;
    }

    public String getCitycode() {
        return this.f30b;
    }

    public String getDistrictName() {
        return this.f29a;
    }

    public List<DPoint> getPolyline() {
        return this.f32d;
    }

    public void setAdcode(String str) {
        this.f31c = str;
    }

    public void setCitycode(String str) {
        this.f30b = str;
    }

    public void setDistrictName(String str) {
        this.f29a = str;
    }

    public void setPolyline(List<DPoint> list) {
        this.f32d = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f29a);
        parcel.writeString(this.f30b);
        parcel.writeString(this.f31c);
        parcel.writeTypedList(this.f32d);
    }
}