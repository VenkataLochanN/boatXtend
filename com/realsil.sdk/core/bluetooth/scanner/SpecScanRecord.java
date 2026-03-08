package com.realsil.sdk.core.bluetooth.scanner;

import android.os.ParcelUuid;
import android.util.SparseArray;
import com.realsil.sdk.core.bluetooth.BluetoothUuid;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class SpecScanRecord {
    public final int ob;
    public final List<ParcelUuid> pb;
    public final SparseArray<byte[]> qb;
    public final Map<ParcelUuid, byte[]> rb;
    public final int sb;
    public final String tb;
    public final byte[] ub;

    public SpecScanRecord(List<ParcelUuid> list, SparseArray<byte[]> sparseArray, Map<ParcelUuid, byte[]> map, int i, int i2, String str, byte[] bArr) {
        this.pb = list;
        this.qb = sparseArray;
        this.rb = map;
        this.tb = str;
        this.ob = i;
        this.sb = i2;
        this.ub = bArr;
    }

    public static int a(byte[] bArr, int i, int i2, int i3, List<ParcelUuid> list) {
        while (i2 > 0) {
            list.add(BluetoothUuid.parseUuidFrom(a(bArr, i, i3)));
            i2 -= i3;
            i += i3;
        }
        return i;
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.realsil.sdk.core.bluetooth.scanner.SpecScanRecord parseFromBytes(byte[] r16) {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.scanner.SpecScanRecord.parseFromBytes(byte[]):com.realsil.sdk.core.bluetooth.scanner.SpecScanRecord");
    }

    public int getAdvertiseFlags() {
        return this.ob;
    }

    public byte[] getBytes() {
        return this.ub;
    }

    public String getDeviceName() {
        return this.tb;
    }

    public SparseArray<byte[]> getManufacturerSpecificData() {
        return this.qb;
    }

    public byte[] getManufacturerSpecificData(int i) {
        SparseArray<byte[]> sparseArray = this.qb;
        if (sparseArray != null) {
            return sparseArray.get(i);
        }
        return null;
    }

    public Map<ParcelUuid, byte[]> getServiceData() {
        return this.rb;
    }

    public byte[] getServiceData(ParcelUuid parcelUuid) {
        if (parcelUuid == null) {
            return null;
        }
        return this.rb.get(parcelUuid);
    }

    public List<ParcelUuid> getServiceUuids() {
        return this.pb;
    }

    public int getTxPowerLevel() {
        return this.sb;
    }

    public String toString() {
        return "ScanRecord [mAdvertiseFlags=" + this.ob + ", mServiceUuids=" + this.pb + "\n, mManufacturerSpecificData=" + SpecBluetoothLeUtils.toString(this.qb) + ", mServiceData=" + SpecBluetoothLeUtils.toString(this.rb) + ", mTxPowerLevel=" + this.sb + ", mDeviceName=" + this.tb + "]";
    }
}