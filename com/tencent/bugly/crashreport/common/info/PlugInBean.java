package com.tencent.bugly.crashreport.common.info;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class PlugInBean implements Parcelable {
    public static final Parcelable.Creator<PlugInBean> CREATOR = new Parcelable.Creator<PlugInBean>() { // from class: com.tencent.bugly.crashreport.common.info.PlugInBean.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ PlugInBean createFromParcel(Parcel parcel) {
            return new PlugInBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ PlugInBean[] newArray(int i) {
            return new PlugInBean[i];
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f5411a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f5412b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f5413c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlugInBean(String str, String str2, String str3) {
        this.f5411a = str;
        this.f5412b = str2;
        this.f5413c = str3;
    }

    public String toString() {
        return "plid:" + this.f5411a + " plV:" + this.f5412b + " plUUID:" + this.f5413c;
    }

    public PlugInBean(Parcel parcel) {
        this.f5411a = parcel.readString();
        this.f5412b = parcel.readString();
        this.f5413c = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f5411a);
        parcel.writeString(this.f5412b);
        parcel.writeString(this.f5413c);
    }
}