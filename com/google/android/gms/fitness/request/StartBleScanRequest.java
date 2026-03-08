package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StartBleScanRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<StartBleScanRequest> CREATOR = new zzbg();
    private final List<DataType> zzah;
    private final zzcq zzgj;
    private final zzae zzii;
    private final int zzij;

    public static class Builder {
        private zzae zzii;
        private DataType[] zzhd = new DataType[0];
        private int zzij = 10;

        public StartBleScanRequest build() {
            Preconditions.checkState(this.zzii != null, "Must set BleScanCallback");
            return new StartBleScanRequest(this);
        }

        public Builder setBleScanCallback(BleScanCallback bleScanCallback) {
            this.zzii = zzd.zzt().zza(bleScanCallback);
            return this;
        }

        public Builder setDataTypes(DataType... dataTypeArr) {
            this.zzhd = dataTypeArr;
            return this;
        }

        public Builder setTimeoutSecs(int i) {
            Preconditions.checkArgument(i > 0, "Stop time must be greater than zero");
            Preconditions.checkArgument(i <= 60, "Stop time must be less than 1 minute");
            this.zzij = i;
            return this;
        }
    }

    private StartBleScanRequest(Builder builder) {
        this(ArrayUtils.toArrayList(builder.zzhd), builder.zzii, builder.zzij, (zzcq) null);
    }

    public StartBleScanRequest(StartBleScanRequest startBleScanRequest, zzcq zzcqVar) {
        this(startBleScanRequest.zzah, startBleScanRequest.zzii, startBleScanRequest.zzij, zzcqVar);
    }

    StartBleScanRequest(List<DataType> list, IBinder iBinder, int i, IBinder iBinder2) {
        zzae zzagVar;
        this.zzah = list;
        if (iBinder == null) {
            zzagVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.request.IBleScanCallback");
            zzagVar = iInterfaceQueryLocalInterface instanceof zzae ? (zzae) iInterfaceQueryLocalInterface : new zzag(iBinder);
        }
        this.zzii = zzagVar;
        this.zzij = i;
        this.zzgj = zzcr.zzj(iBinder2);
    }

    public StartBleScanRequest(List<DataType> list, zzae zzaeVar, int i, zzcq zzcqVar) {
        this.zzah = list;
        this.zzii = zzaeVar;
        this.zzij = i;
        this.zzgj = zzcqVar;
    }

    public List<DataType> getDataTypes() {
        return Collections.unmodifiableList(this.zzah);
    }

    public int getTimeoutSecs() {
        return this.zzij;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.zzah).add("timeoutSecs", Integer.valueOf(this.zzij)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzii.asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 3, getTimeoutSecs());
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcqVar == null ? null : zzcqVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}