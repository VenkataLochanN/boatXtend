package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BleDevicesResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<BleDevicesResult> CREATOR = new zza();
    private final List<BleDevice> zzim;
    private final Status zzin;

    public BleDevicesResult(List<BleDevice> list, Status status) {
        this.zzim = Collections.unmodifiableList(list);
        this.zzin = status;
    }

    public static BleDevicesResult zzb(Status status) {
        return new BleDevicesResult(Collections.emptyList(), status);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BleDevicesResult) {
                BleDevicesResult bleDevicesResult = (BleDevicesResult) obj;
                if (this.zzin.equals(bleDevicesResult.zzin) && Objects.equal(this.zzim, bleDevicesResult.zzim)) {
                }
            }
            return false;
        }
        return true;
    }

    public List<BleDevice> getClaimedBleDevices() {
        return this.zzim;
    }

    public List<BleDevice> getClaimedBleDevices(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (BleDevice bleDevice : this.zzim) {
            if (bleDevice.getDataTypes().contains(dataType)) {
                arrayList.add(bleDevice);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzin;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzin, this.zzim);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zzin).add("bleDevices", this.zzim).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getClaimedBleDevices(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}