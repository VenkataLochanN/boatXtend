package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataTypeCreateRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DataTypeCreateRequest> CREATOR = new zzr();
    private final String name;
    private final List<Field> zzbu;
    private final com.google.android.gms.internal.fitness.zzbn zzhf;

    public static class Builder {
        private String name;
        private List<Field> zzbu = new ArrayList();

        public Builder addField(Field field) {
            if (!this.zzbu.contains(field)) {
                this.zzbu.add(field);
            }
            return this;
        }

        public Builder addField(String str, int i) {
            Preconditions.checkArgument((str == null || str.isEmpty()) ? false : true, "Invalid name specified");
            return addField(Field.zza(str, i));
        }

        public DataTypeCreateRequest build() {
            Preconditions.checkState(this.name != null, "Must set the name");
            Preconditions.checkState(!this.zzbu.isEmpty(), "Must specify the data fields");
            return new DataTypeCreateRequest(this);
        }

        public Builder setName(String str) {
            this.name = str;
            return this;
        }
    }

    private DataTypeCreateRequest(Builder builder) {
        this(builder.name, (List<Field>) builder.zzbu, (com.google.android.gms.internal.fitness.zzbn) null);
    }

    public DataTypeCreateRequest(DataTypeCreateRequest dataTypeCreateRequest, com.google.android.gms.internal.fitness.zzbn zzbnVar) {
        this(dataTypeCreateRequest.name, dataTypeCreateRequest.zzbu, zzbnVar);
    }

    DataTypeCreateRequest(String str, List<Field> list, IBinder iBinder) {
        this.name = str;
        this.zzbu = Collections.unmodifiableList(list);
        this.zzhf = com.google.android.gms.internal.fitness.zzbo.zze(iBinder);
    }

    private DataTypeCreateRequest(String str, List<Field> list, com.google.android.gms.internal.fitness.zzbn zzbnVar) {
        this.name = str;
        this.zzbu = Collections.unmodifiableList(list);
        this.zzhf = zzbnVar;
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof DataTypeCreateRequest) {
                DataTypeCreateRequest dataTypeCreateRequest = (DataTypeCreateRequest) obj;
                if (Objects.equal(this.name, dataTypeCreateRequest.name) && Objects.equal(this.zzbu, dataTypeCreateRequest.zzbu)) {
                }
            }
            return false;
        }
        return true;
    }

    public List<Field> getFields() {
        return this.zzbu;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return Objects.hashCode(this.name, this.zzbu);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(AppMeasurementSdk.ConditionalUserProperty.NAME, this.name).add("fields", this.zzbu).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getFields(), false);
        com.google.android.gms.internal.fitness.zzbn zzbnVar = this.zzhf;
        SafeParcelWriter.writeIBinder(parcel, 3, zzbnVar == null ? null : zzbnVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}