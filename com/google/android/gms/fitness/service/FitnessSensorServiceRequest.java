package com.google.android.gms.fitness.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.data.zzu;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class FitnessSensorServiceRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<FitnessSensorServiceRequest> CREATOR = new zzb();
    public static final int UNSPECIFIED = -1;
    private final zzt zzhp;
    private final long zziv;
    private final long zziw;
    private final DataSource zzr;

    FitnessSensorServiceRequest(DataSource dataSource, IBinder iBinder, long j, long j2) {
        this.zzr = dataSource;
        this.zzhp = zzu.zza(iBinder);
        this.zziv = j;
        this.zziw = j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FitnessSensorServiceRequest)) {
            return false;
        }
        FitnessSensorServiceRequest fitnessSensorServiceRequest = (FitnessSensorServiceRequest) obj;
        return Objects.equal(this.zzr, fitnessSensorServiceRequest.zzr) && this.zziv == fitnessSensorServiceRequest.zziv && this.zziw == fitnessSensorServiceRequest.zziw;
    }

    public long getBatchInterval(TimeUnit timeUnit) {
        return timeUnit.convert(this.zziw, TimeUnit.MICROSECONDS);
    }

    public DataSource getDataSource() {
        return this.zzr;
    }

    public SensorEventDispatcher getDispatcher() {
        return new zzc(this.zzhp);
    }

    public long getSamplingRate(TimeUnit timeUnit) {
        long j = this.zziv;
        if (j == -1) {
            return -1L;
        }
        return timeUnit.convert(j, TimeUnit.MICROSECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzr, Long.valueOf(this.zziv), Long.valueOf(this.zziw));
    }

    public String toString() {
        return String.format("FitnessSensorServiceRequest{%s}", this.zzr);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzhp.asBinder(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.zziv);
        SafeParcelWriter.writeLong(parcel, 4, this.zziw);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}