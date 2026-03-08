package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzbq;
import com.google.android.gms.internal.fitness.zzbr;
import com.google.android.gms.internal.fitness.zzfa;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GoalsReadRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GoalsReadRequest> CREATOR = new zzad();
    private final List<DataType> zzah;
    private final List<Integer> zzdl;
    private final zzbq zzhh;
    private final List<Integer> zzhi;

    public static class Builder {
        private final List<DataType> zzah = new ArrayList();
        private final List<Integer> zzhi = new ArrayList();
        private final List<Integer> zzdl = new ArrayList();

        public Builder addActivity(String str) {
            int iZzl = zzfa.zzl(str);
            Preconditions.checkState(iZzl != 4, "Attempting to add an unknown activity");
            com.google.android.gms.internal.fitness.zzf.zza(Integer.valueOf(iZzl), this.zzdl);
            return this;
        }

        public Builder addDataType(DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            if (!this.zzah.contains(dataType)) {
                this.zzah.add(dataType);
            }
            return this;
        }

        public Builder addObjectiveType(int i) {
            boolean z = true;
            if (i != 1 && i != 2 && i != 3) {
                z = false;
            }
            Preconditions.checkState(z, "Attempting to add an invalid objective type");
            if (!this.zzhi.contains(Integer.valueOf(i))) {
                this.zzhi.add(Integer.valueOf(i));
            }
            return this;
        }

        public GoalsReadRequest build() {
            Preconditions.checkState(!this.zzah.isEmpty(), "At least one data type should be specified.");
            return new GoalsReadRequest(this);
        }
    }

    GoalsReadRequest(IBinder iBinder, List<DataType> list, List<Integer> list2, List<Integer> list3) {
        this.zzhh = iBinder == null ? null : zzbr.zzf(iBinder);
        this.zzah = list;
        this.zzhi = list2;
        this.zzdl = list3;
    }

    private GoalsReadRequest(Builder builder) {
        this((zzbq) null, (List<DataType>) builder.zzah, (List<Integer>) builder.zzhi, (List<Integer>) builder.zzdl);
    }

    public GoalsReadRequest(GoalsReadRequest goalsReadRequest, zzbq zzbqVar) {
        this(zzbqVar, goalsReadRequest.getDataTypes(), goalsReadRequest.zzhi, goalsReadRequest.zzdl);
    }

    private GoalsReadRequest(zzbq zzbqVar, List<DataType> list, List<Integer> list2, List<Integer> list3) {
        this(zzbqVar == null ? null : zzbqVar.asBinder(), list, list2, list3);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof GoalsReadRequest) {
                GoalsReadRequest goalsReadRequest = (GoalsReadRequest) obj;
                if (Objects.equal(this.zzah, goalsReadRequest.zzah) && Objects.equal(this.zzhi, goalsReadRequest.zzhi) && Objects.equal(this.zzdl, goalsReadRequest.zzdl)) {
                }
            }
            return false;
        }
        return true;
    }

    public List<String> getActivityNames() {
        if (this.zzdl.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = this.zzdl.iterator();
        while (it.hasNext()) {
            arrayList.add(zzfa.getName(it.next().intValue()));
        }
        return arrayList;
    }

    public List<DataType> getDataTypes() {
        return this.zzah;
    }

    public List<Integer> getObjectiveTypes() {
        if (this.zzhi.isEmpty()) {
            return null;
        }
        return this.zzhi;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzah, this.zzhi, getActivityNames());
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.zzah).add("objectiveTypes", this.zzhi).add("activities", getActivityNames()).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzhh.asBinder(), false);
        SafeParcelWriter.writeList(parcel, 2, getDataTypes(), false);
        SafeParcelWriter.writeList(parcel, 3, this.zzhi, false);
        SafeParcelWriter.writeList(parcel, 4, this.zzdl, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}