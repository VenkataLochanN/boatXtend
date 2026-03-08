package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.fitness.data.DataType;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataSourcesRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DataSourcesRequest> CREATOR = new zzp();
    private final List<DataType> zzah;
    private final List<Integer> zzha;
    private final boolean zzhb;
    private final com.google.android.gms.internal.fitness.zzbk zzhc;

    public static class Builder {
        private DataType[] zzhd = new DataType[0];
        private int[] zzhe = {0, 1};
        private boolean zzhb = false;

        public DataSourcesRequest build() {
            Preconditions.checkState(this.zzhd.length > 0, "Must add at least one data type");
            Preconditions.checkState(this.zzhe.length > 0, "Must add at least one data source type");
            return new DataSourcesRequest(this);
        }

        public Builder setDataSourceTypes(int... iArr) {
            this.zzhe = iArr;
            return this;
        }

        public Builder setDataTypes(DataType... dataTypeArr) {
            this.zzhd = dataTypeArr;
            return this;
        }
    }

    private DataSourcesRequest(Builder builder) {
        this((List<DataType>) ArrayUtils.toArrayList(builder.zzhd), (List<Integer>) Arrays.asList(ArrayUtils.toWrapperArray(builder.zzhe)), false, (com.google.android.gms.internal.fitness.zzbk) null);
    }

    public DataSourcesRequest(DataSourcesRequest dataSourcesRequest, com.google.android.gms.internal.fitness.zzbk zzbkVar) {
        this(dataSourcesRequest.zzah, dataSourcesRequest.zzha, dataSourcesRequest.zzhb, zzbkVar);
    }

    DataSourcesRequest(List<DataType> list, List<Integer> list2, boolean z, IBinder iBinder) {
        this.zzah = list;
        this.zzha = list2;
        this.zzhb = z;
        this.zzhc = com.google.android.gms.internal.fitness.zzbl.zzd(iBinder);
    }

    private DataSourcesRequest(List<DataType> list, List<Integer> list2, boolean z, com.google.android.gms.internal.fitness.zzbk zzbkVar) {
        this.zzah = list;
        this.zzha = list2;
        this.zzhb = z;
        this.zzhc = zzbkVar;
    }

    public List<DataType> getDataTypes() {
        return this.zzah;
    }

    public String toString() {
        Objects.ToStringHelper toStringHelperAdd = Objects.toStringHelper(this).add("dataTypes", this.zzah).add("sourceTypes", this.zzha);
        if (this.zzhb) {
            toStringHelperAdd.add("includeDbOnlySources", "true");
        }
        return toStringHelperAdd.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        SafeParcelWriter.writeIntegerList(parcel, 2, this.zzha, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzhb);
        com.google.android.gms.internal.fitness.zzbk zzbkVar = this.zzhc;
        SafeParcelWriter.writeIBinder(parcel, 4, zzbkVar == null ? null : zzbkVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}