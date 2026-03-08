package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

/* JADX INFO: loaded from: classes.dex */
public class DailyTotalResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<DailyTotalResult> CREATOR = new zzb();
    private final DataSet zzeb;
    private final Status zzin;

    DailyTotalResult(Status status, DataSet dataSet) {
        this.zzin = status;
        this.zzeb = dataSet;
    }

    private DailyTotalResult(DataSet dataSet, Status status) {
        this.zzin = status;
        this.zzeb = dataSet;
    }

    public static DailyTotalResult zza(Status status, DataType dataType) {
        return new DailyTotalResult(DataSet.create(new DataSource.Builder().setDataType(dataType).setType(1).build()), status);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof DailyTotalResult) {
                DailyTotalResult dailyTotalResult = (DailyTotalResult) obj;
                if (this.zzin.equals(dailyTotalResult.zzin) && Objects.equal(this.zzeb, dailyTotalResult.zzeb)) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzin;
    }

    public DataSet getTotal() {
        return this.zzeb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzin, this.zzeb);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zzin).add("dataPoint", this.zzeb).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getTotal(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}