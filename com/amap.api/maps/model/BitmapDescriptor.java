package com.amap.api.maps.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore.util.er;

/* JADX INFO: loaded from: classes.dex */
public final class BitmapDescriptor implements Parcelable, Cloneable {
    public static final BitmapDescriptorCreator CREATOR = new BitmapDescriptorCreator();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1849a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1850b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Bitmap f1851c;
    private String mId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    BitmapDescriptor(Bitmap bitmap, String str) {
        this.f1849a = 0;
        this.f1850b = 0;
        if (bitmap != null) {
            try {
                this.f1849a = bitmap.getWidth();
                this.f1850b = bitmap.getHeight();
                if (bitmap.getConfig() == null) {
                    this.f1851c = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                } else {
                    this.f1851c = bitmap.copy(bitmap.getConfig(), true);
                }
            } catch (Throwable th) {
                er.a(th);
                return;
            }
        }
        this.mId = str;
    }

    private BitmapDescriptor(Bitmap bitmap, int i, int i2, String str) {
        this.f1849a = 0;
        this.f1850b = 0;
        this.f1849a = i;
        this.f1850b = i2;
        this.f1851c = bitmap;
        this.mId = str;
    }

    public String getId() {
        return this.mId;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BitmapDescriptor m10clone() {
        try {
            return new BitmapDescriptor(this.f1851c.copy(this.f1851c.getConfig(), true), this.f1849a, this.f1850b, this.mId);
        } catch (Throwable th) {
            th.printStackTrace();
            er.a(th);
            return null;
        }
    }

    public Bitmap getBitmap() {
        return this.f1851c;
    }

    public int getWidth() {
        return this.f1849a;
    }

    public int getHeight() {
        return this.f1850b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mId);
        parcel.writeParcelable(this.f1851c, i);
        parcel.writeInt(this.f1849a);
        parcel.writeInt(this.f1850b);
    }

    public void recycle() {
        try {
            er.b(this.f1851c);
        } catch (Throwable th) {
            er.a(th);
        }
    }

    public boolean equals(Object obj) {
        BitmapDescriptor bitmapDescriptor;
        Bitmap bitmap;
        Bitmap bitmap2 = this.f1851c;
        if (bitmap2 == null || bitmap2.isRecycled() || obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() == obj.getClass() && (bitmap = (bitmapDescriptor = (BitmapDescriptor) obj).f1851c) != null && !bitmap.isRecycled() && this.f1849a == bitmapDescriptor.getWidth() && this.f1850b == bitmapDescriptor.getHeight()) {
            try {
                return this.f1851c.sameAs(bitmapDescriptor.f1851c);
            } catch (Throwable th) {
                er.a(th);
            }
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }
}