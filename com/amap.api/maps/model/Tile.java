package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.autonavi.ae.gmap.maploader.Pools;

/* JADX INFO: loaded from: classes.dex */
public final class Tile implements Parcelable {
    public static final b CREATOR = new b();
    private static final Pools.SynchronizedPool<Tile> M_POOL = new Pools.SynchronizedPool<>(18);
    public final byte[] data;
    public final int height;
    private final int mVersionCode;
    public final int width;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static Tile obtain(int i, int i2, byte[] bArr) {
        Tile tileAcquire = M_POOL.acquire();
        return tileAcquire != null ? tileAcquire : new Tile(i, i2, bArr);
    }

    public void recycle() {
        M_POOL.release(this);
    }

    Tile(int i, int i2, int i3, byte[] bArr) {
        this.mVersionCode = i;
        this.width = i2;
        this.height = i3;
        this.data = bArr;
    }

    public Tile(int i, int i2, byte[] bArr) {
        this(1, i, i2, bArr);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mVersionCode);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeByteArray(this.data);
    }
}