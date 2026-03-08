package com.realsil.sdk.core.bluetooth.scanner;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class ScannerParams implements Parcelable {
    public static final Parcelable.Creator<ScannerParams> CREATOR = new Parcelable.Creator<ScannerParams>() { // from class: com.realsil.sdk.core.bluetooth.scanner.ScannerParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScannerParams createFromParcel(Parcel parcel) {
            return new ScannerParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScannerParams[] newArray(int i) {
            return new ScannerParams[i];
        }
    };
    public static final int SCAN_MECHANISM_ALL = 0;
    public static final int SCAN_MECHANISM_FILTER_ONE = 1;
    public static final int SCAN_MODE_DUAL = 0;
    public static final int SCAN_MODE_GATT = 17;
    public static final int SCAN_MODE_GATT_STRICT = 18;
    public static final int SCAN_MODE_SPP = 32;
    public static final int SCAN_MODE_SPP_STRICT = 33;
    public int Wa;
    public int Xa;
    public UUID[] Ya;
    public ParcelUuid[] Za;
    public String _a;
    public boolean ab;
    public String bb;
    public int cb;
    public long db;
    public boolean eb;
    public long fb;
    public boolean gb;
    public int hb;

    public ScannerParams() {
        this(0, null);
    }

    public ScannerParams(int i) {
        this(i, null);
    }

    public ScannerParams(int i, ParcelUuid[] parcelUuidArr) {
        this.Wa = 0;
        this.Xa = 0;
        this.ab = true;
        this.cb = -1000;
        this.db = DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT;
        this.fb = 3000L;
        this.gb = true;
        this.hb = 255;
        this.Wa = i;
        this.Za = parcelUuidArr;
        if (parcelUuidArr == null || parcelUuidArr.length <= 0) {
            this.Ya = null;
        } else {
            int length = parcelUuidArr.length;
            this.Ya = new UUID[length];
            for (int i2 = 0; i2 < length; i2++) {
                this.Ya[i2] = parcelUuidArr[i2].getUuid();
            }
        }
        this.eb = false;
        this.Xa = 0;
    }

    public ScannerParams(Parcel parcel) {
        this.Wa = 0;
        this.Xa = 0;
        this.ab = true;
        this.cb = -1000;
        this.db = DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT;
        this.fb = 3000L;
        this.gb = true;
        this.hb = 255;
        this.Wa = parcel.readInt();
        this.Xa = parcel.readInt();
        this.Za = (ParcelUuid[]) parcel.createTypedArray(ParcelUuid.CREATOR);
        this._a = parcel.readString();
        this.ab = parcel.readByte() != 0;
        this.bb = parcel.readString();
        this.cb = parcel.readInt();
        this.db = parcel.readLong();
        this.eb = parcel.readByte() != 0;
        this.fb = parcel.readLong();
        this.gb = parcel.readByte() != 0;
        this.hb = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAddressFilter() {
        return this.bb;
    }

    public long getAutoScanDelay() {
        return this.fb;
    }

    public String getNameFilter() {
        return this._a;
    }

    public int getPhy() {
        return this.hb;
    }

    public int getRssiFilter() {
        return this.cb;
    }

    public int getScanMechanism() {
        return this.Xa;
    }

    public int getScanMode() {
        return this.Wa;
    }

    public long getScanPeriod() {
        return this.db;
    }

    public ParcelUuid[] getServiceParcelUuids() {
        return this.Za;
    }

    public UUID[] getServiceUuids() {
        return this.Ya;
    }

    public boolean isAutoDiscovery() {
        return this.eb;
    }

    public boolean isNameNullable() {
        return this.ab;
    }

    public boolean isReusePaiedDeviceEnabled() {
        return this.gb;
    }

    public void setAddressFilter(String str) {
        this.bb = str;
    }

    public void setAutoDiscovery(boolean z) {
        this.eb = z;
    }

    public void setAutoScanDelay(long j) {
        this.fb = j;
    }

    public void setNameFilter(String str) {
        this._a = str;
    }

    public void setNameNullable(boolean z) {
        this.ab = z;
    }

    public void setPhy(int i) {
        this.hb = i;
    }

    public void setReusePaiedDeviceEnabled(boolean z) {
        this.gb = z;
    }

    public void setRssiFilter(int i) {
        this.cb = i;
    }

    public void setScanMechanism(int i) {
        this.Xa = i;
    }

    public void setScanMode(int i) {
        this.Wa = i;
    }

    public void setScanPeriod(long j) {
        this.db = j;
    }

    public void setServiceParcelUuids(ParcelUuid[] parcelUuidArr) {
        this.Za = parcelUuidArr;
    }

    public void setServiceUuids(UUID[] uuidArr) {
        this.Ya = uuidArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.Wa);
        parcel.writeInt(this.Xa);
        parcel.writeTypedArray(this.Za, i);
        parcel.writeString(this._a);
        parcel.writeByte(this.ab ? (byte) 1 : (byte) 0);
        parcel.writeString(this.bb);
        parcel.writeInt(this.cb);
        parcel.writeLong(this.db);
        parcel.writeByte(this.eb ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.fb);
        parcel.writeByte(this.gb ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.hb);
    }
}