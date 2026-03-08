package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* JADX INFO: loaded from: classes.dex */
public class Subscription extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Subscription> CREATOR = new zzah();
    private final long zzec;
    private final int zzed;
    private final DataType zzq;
    private final DataSource zzr;

    public static class zza {
        private long zzec = -1;
        private int zzed = 2;
        private DataType zzq;
        private DataSource zzr;

        public final zza zza(DataSource dataSource) {
            this.zzr = dataSource;
            return this;
        }

        public final zza zza(DataType dataType) {
            this.zzq = dataType;
            return this;
        }

        public final Subscription zzr() {
            DataSource dataSource;
            Preconditions.checkState((this.zzr == null && this.zzq == null) ? false : true, "Must call setDataSource() or setDataType()");
            DataType dataType = this.zzq;
            Preconditions.checkState(dataType == null || (dataSource = this.zzr) == null || dataType.equals(dataSource.getDataType()), "Specified data type is incompatible with specified data source");
            return new Subscription(this);
        }
    }

    Subscription(DataSource dataSource, DataType dataType, long j, int i) {
        this.zzr = dataSource;
        this.zzq = dataType;
        this.zzec = j;
        this.zzed = i;
    }

    private Subscription(zza zzaVar) {
        this.zzq = zzaVar.zzq;
        this.zzr = zzaVar.zzr;
        this.zzec = zzaVar.zzec;
        this.zzed = zzaVar.zzed;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Subscription)) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        return Objects.equal(this.zzr, subscription.zzr) && Objects.equal(this.zzq, subscription.zzq) && this.zzec == subscription.zzec && this.zzed == subscription.zzed;
    }

    public DataSource getDataSource() {
        return this.zzr;
    }

    public DataType getDataType() {
        return this.zzq;
    }

    public int hashCode() {
        DataSource dataSource = this.zzr;
        return Objects.hashCode(dataSource, dataSource, Long.valueOf(this.zzec), Integer.valueOf(this.zzed));
    }

    public String toDebugString() {
        Object[] objArr = new Object[1];
        DataSource dataSource = this.zzr;
        objArr[0] = dataSource == null ? this.zzq.getName() : dataSource.toDebugString();
        return String.format("Subscription{%s}", objArr);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataSource", this.zzr).add("dataType", this.zzq).add("samplingIntervalMicros", Long.valueOf(this.zzec)).add("accuracyMode", Integer.valueOf(this.zzed)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getDataType(), i, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzec);
        SafeParcelWriter.writeInt(parcel, 4, this.zzed);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final DataType zzq() {
        DataType dataType = this.zzq;
        return dataType == null ? this.zzr.getDataType() : dataType;
    }
}