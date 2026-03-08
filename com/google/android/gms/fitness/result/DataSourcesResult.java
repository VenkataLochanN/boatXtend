package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataSourcesResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<DataSourcesResult> CREATOR = new zzd();
    private final List<DataSource> zzgm;
    private final Status zzin;

    public DataSourcesResult(List<DataSource> list, Status status) {
        this.zzgm = Collections.unmodifiableList(list);
        this.zzin = status;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof DataSourcesResult) {
                DataSourcesResult dataSourcesResult = (DataSourcesResult) obj;
                if (this.zzin.equals(dataSourcesResult.zzin) && Objects.equal(this.zzgm, dataSourcesResult.zzgm)) {
                }
            }
            return false;
        }
        return true;
    }

    public List<DataSource> getDataSources() {
        return this.zzgm;
    }

    public List<DataSource> getDataSources(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (DataSource dataSource : this.zzgm) {
            if (dataSource.getDataType().equals(dataType)) {
                arrayList.add(dataSource);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzin;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzin, this.zzgm);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zzin).add("dataSources", this.zzgm).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataSources(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}