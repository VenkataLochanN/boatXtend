package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class DataUpdateNotification extends AbstractSafeParcelable {
    public static final String ACTION = "com.google.android.gms.fitness.DATA_UPDATE_NOTIFICATION";
    public static final Parcelable.Creator<DataUpdateNotification> CREATOR = new zzn();
    public static final String EXTRA_DATA_UPDATE_NOTIFICATION = "vnd.google.fitness.data_udpate_notification";
    public static final int OPERATION_DELETE = 2;
    public static final int OPERATION_INSERT = 1;
    public static final int OPERATION_UPDATE = 3;
    private final long zzcb;
    private final long zzcc;
    private final int zzcd;
    private final DataType zzq;
    private final DataSource zzr;

    public DataUpdateNotification(long j, long j2, int i, DataSource dataSource, DataType dataType) {
        this.zzcb = j;
        this.zzcc = j2;
        this.zzcd = i;
        this.zzr = dataSource;
        this.zzq = dataType;
    }

    public static DataUpdateNotification getDataUpdateNotification(Intent intent) {
        return (DataUpdateNotification) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_DATA_UPDATE_NOTIFICATION, CREATOR);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataUpdateNotification)) {
            return false;
        }
        DataUpdateNotification dataUpdateNotification = (DataUpdateNotification) obj;
        return this.zzcb == dataUpdateNotification.zzcb && this.zzcc == dataUpdateNotification.zzcc && this.zzcd == dataUpdateNotification.zzcd && Objects.equal(this.zzr, dataUpdateNotification.zzr) && Objects.equal(this.zzq, dataUpdateNotification.zzq);
    }

    public DataSource getDataSource() {
        return this.zzr;
    }

    public DataType getDataType() {
        return this.zzq;
    }

    public int getOperationType() {
        return this.zzcd;
    }

    public long getUpdateEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzcc, TimeUnit.NANOSECONDS);
    }

    public long getUpdateStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzcb, TimeUnit.NANOSECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzcb), Long.valueOf(this.zzcc), Integer.valueOf(this.zzcd), this.zzr, this.zzq);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("updateStartTimeNanos", Long.valueOf(this.zzcb)).add("updateEndTimeNanos", Long.valueOf(this.zzcc)).add("operationType", Integer.valueOf(this.zzcd)).add("dataSource", this.zzr).add("dataType", this.zzq).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzcb);
        SafeParcelWriter.writeLong(parcel, 2, this.zzcc);
        SafeParcelWriter.writeInt(parcel, 3, getOperationType());
        SafeParcelWriter.writeParcelable(parcel, 4, getDataSource(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, getDataType(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}