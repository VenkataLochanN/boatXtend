package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class zzao extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzao> CREATOR = new zzap();
    private final long zzec;
    private final int zzed;
    private final zzcq zzgj;
    private final PendingIntent zzhg;
    private com.google.android.gms.fitness.data.zzt zzhp;
    private final long zzhq;
    private final long zzhr;
    private final List<LocationRequest> zzhs;
    private final long zzht;
    private final List<ClientIdentity> zzhu;
    private DataType zzq;
    private DataSource zzr;

    zzao(DataSource dataSource, DataType dataType, IBinder iBinder, int i, int i2, long j, long j2, PendingIntent pendingIntent, long j3, int i3, List<LocationRequest> list, long j4, IBinder iBinder2) {
        this.zzr = dataSource;
        this.zzq = dataType;
        this.zzhp = iBinder == null ? null : com.google.android.gms.fitness.data.zzu.zza(iBinder);
        this.zzec = j == 0 ? i : j;
        this.zzhr = j3;
        this.zzhq = j2 == 0 ? i2 : j2;
        this.zzhs = list;
        this.zzhg = pendingIntent;
        this.zzed = i3;
        this.zzhu = Collections.emptyList();
        this.zzht = j4;
        this.zzgj = zzcr.zzj(iBinder2);
    }

    private zzao(DataSource dataSource, DataType dataType, com.google.android.gms.fitness.data.zzt zztVar, PendingIntent pendingIntent, long j, long j2, long j3, int i, List<LocationRequest> list, List<ClientIdentity> list2, long j4, zzcq zzcqVar) {
        this.zzr = dataSource;
        this.zzq = dataType;
        this.zzhp = zztVar;
        this.zzhg = pendingIntent;
        this.zzec = j;
        this.zzhr = j2;
        this.zzhq = j3;
        this.zzed = i;
        this.zzhs = null;
        this.zzhu = list2;
        this.zzht = j4;
        this.zzgj = zzcqVar;
    }

    public zzao(SensorRequest sensorRequest, com.google.android.gms.fitness.data.zzt zztVar, PendingIntent pendingIntent, zzcq zzcqVar) {
        this(sensorRequest.getDataSource(), sensorRequest.getDataType(), zztVar, pendingIntent, sensorRequest.getSamplingRate(TimeUnit.MICROSECONDS), sensorRequest.getFastestRate(TimeUnit.MICROSECONDS), sensorRequest.getMaxDeliveryLatency(TimeUnit.MICROSECONDS), sensorRequest.getAccuracyMode(), null, Collections.emptyList(), sensorRequest.zzx(), zzcqVar);
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof zzao) {
                zzao zzaoVar = (zzao) obj;
                if (Objects.equal(this.zzr, zzaoVar.zzr) && Objects.equal(this.zzq, zzaoVar.zzq) && Objects.equal(this.zzhp, zzaoVar.zzhp) && this.zzec == zzaoVar.zzec && this.zzhr == zzaoVar.zzhr && this.zzhq == zzaoVar.zzhq && this.zzed == zzaoVar.zzed && Objects.equal(this.zzhs, zzaoVar.zzhs)) {
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzr, this.zzq, this.zzhp, Long.valueOf(this.zzec), Long.valueOf(this.zzhr), Long.valueOf(this.zzhq), Integer.valueOf(this.zzed), this.zzhs);
    }

    public final String toString() {
        return String.format("SensorRegistrationRequest{type %s source %s interval %s fastest %s latency %s}", this.zzq, this.zzr, Long.valueOf(this.zzec), Long.valueOf(this.zzhr), Long.valueOf(this.zzhq));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzr, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzq, i, false);
        com.google.android.gms.fitness.data.zzt zztVar = this.zzhp;
        SafeParcelWriter.writeIBinder(parcel, 3, zztVar == null ? null : zztVar.asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 4, 0);
        SafeParcelWriter.writeInt(parcel, 5, 0);
        SafeParcelWriter.writeLong(parcel, 6, this.zzec);
        SafeParcelWriter.writeLong(parcel, 7, this.zzhq);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzhg, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.zzhr);
        SafeParcelWriter.writeInt(parcel, 10, this.zzed);
        SafeParcelWriter.writeTypedList(parcel, 11, this.zzhs, false);
        SafeParcelWriter.writeLong(parcel, 12, this.zzht);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 13, zzcqVar != null ? zzcqVar.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}