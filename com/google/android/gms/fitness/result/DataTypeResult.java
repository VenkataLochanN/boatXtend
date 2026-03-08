package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataType;

/* JADX INFO: loaded from: classes.dex */
public class DataTypeResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<DataTypeResult> CREATOR = new zze();
    private final Status zzin;
    private final DataType zzq;

    public DataTypeResult(Status status, DataType dataType) {
        this.zzin = status;
        this.zzq = dataType;
    }

    public static DataTypeResult zzc(Status status) {
        return new DataTypeResult(status, null);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof DataTypeResult) {
                DataTypeResult dataTypeResult = (DataTypeResult) obj;
                if (this.zzin.equals(dataTypeResult.zzin) && Objects.equal(this.zzq, dataTypeResult.zzq)) {
                }
            }
            return false;
        }
        return true;
    }

    public DataType getDataType() {
        return this.zzq;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzin;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzin, this.zzq);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zzin).add("dataType", this.zzq).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getDataType(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}