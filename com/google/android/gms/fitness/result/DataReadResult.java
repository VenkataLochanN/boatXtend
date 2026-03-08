package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataReadResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<DataReadResult> CREATOR = new zzc();
    private final List<DataSet> zzaj;
    private final List<DataSource> zzav;
    private final Status zzin;
    private final List<Bucket> zzio;
    private int zzip;

    DataReadResult(List<RawDataSet> list, Status status, List<RawBucket> list2, int i, List<DataSource> list3) {
        this.zzin = status;
        this.zzip = i;
        this.zzav = list3;
        this.zzaj = new ArrayList(list.size());
        Iterator<RawDataSet> it = list.iterator();
        while (it.hasNext()) {
            this.zzaj.add(new DataSet(it.next(), list3));
        }
        this.zzio = new ArrayList(list2.size());
        Iterator<RawBucket> it2 = list2.iterator();
        while (it2.hasNext()) {
            this.zzio.add(new Bucket(it2.next(), list3));
        }
    }

    private DataReadResult(List<DataSet> list, List<Bucket> list2, Status status) {
        this.zzaj = list;
        this.zzin = status;
        this.zzio = list2;
        this.zzip = 1;
        this.zzav = new ArrayList();
    }

    public static DataReadResult zza(Status status, List<DataType> list, List<DataSource> list2) {
        ArrayList arrayList = new ArrayList();
        Iterator<DataSource> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(DataSet.create(it.next()));
        }
        Iterator<DataType> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(DataSet.create(new DataSource.Builder().setDataType(it2.next()).setType(1).setName("Default").build()));
        }
        return new DataReadResult(arrayList, Collections.emptyList(), status);
    }

    private static void zza(DataSet dataSet, List<DataSet> list) {
        for (DataSet dataSet2 : list) {
            if (dataSet2.getDataSource().equals(dataSet.getDataSource())) {
                dataSet2.zza(dataSet.getDataPoints());
                return;
            }
        }
        list.add(dataSet);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof DataReadResult) {
                DataReadResult dataReadResult = (DataReadResult) obj;
                if (this.zzin.equals(dataReadResult.zzin) && Objects.equal(this.zzaj, dataReadResult.zzaj) && Objects.equal(this.zzio, dataReadResult.zzio)) {
                }
            }
            return false;
        }
        return true;
    }

    public List<Bucket> getBuckets() {
        return this.zzio;
    }

    public DataSet getDataSet(DataSource dataSource) {
        for (DataSet dataSet : this.zzaj) {
            if (dataSource.equals(dataSet.getDataSource())) {
                return dataSet;
            }
        }
        return DataSet.create(dataSource);
    }

    public DataSet getDataSet(DataType dataType) {
        for (DataSet dataSet : this.zzaj) {
            if (dataType.equals(dataSet.getDataType())) {
                return dataSet;
            }
        }
        return DataSet.create(new DataSource.Builder().setDataType(dataType).setType(1).build());
    }

    public List<DataSet> getDataSets() {
        return this.zzaj;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzin;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzin, this.zzaj, this.zzio);
    }

    public String toString() {
        Object string;
        Object string2;
        Objects.ToStringHelper toStringHelperAdd = Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zzin);
        if (this.zzaj.size() > 5) {
            int size = this.zzaj.size();
            StringBuilder sb = new StringBuilder(21);
            sb.append(size);
            sb.append(" data sets");
            string = sb.toString();
        } else {
            string = this.zzaj;
        }
        Objects.ToStringHelper toStringHelperAdd2 = toStringHelperAdd.add("dataSets", string);
        if (this.zzio.size() > 5) {
            int size2 = this.zzio.size();
            StringBuilder sb2 = new StringBuilder(19);
            sb2.append(size2);
            sb2.append(" buckets");
            string2 = sb2.toString();
        } else {
            string2 = this.zzio;
        }
        return toStringHelperAdd2.add("buckets", string2).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        ArrayList arrayList = new ArrayList(this.zzaj.size());
        Iterator<DataSet> it = this.zzaj.iterator();
        while (it.hasNext()) {
            arrayList.add(new RawDataSet(it.next(), this.zzav));
        }
        SafeParcelWriter.writeList(parcel, 1, arrayList, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        ArrayList arrayList2 = new ArrayList(this.zzio.size());
        Iterator<Bucket> it2 = this.zzio.iterator();
        while (it2.hasNext()) {
            arrayList2.add(new RawBucket(it2.next(), this.zzav));
        }
        SafeParcelWriter.writeList(parcel, 3, arrayList2, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzip);
        SafeParcelWriter.writeTypedList(parcel, 6, this.zzav, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final void zzb(DataReadResult dataReadResult) {
        Iterator<DataSet> it = dataReadResult.getDataSets().iterator();
        while (it.hasNext()) {
            zza(it.next(), this.zzaj);
        }
        for (Bucket bucket : dataReadResult.getBuckets()) {
            Iterator<Bucket> it2 = this.zzio.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    this.zzio.add(bucket);
                    break;
                }
                Bucket next = it2.next();
                if (next.zza(bucket)) {
                    Iterator<DataSet> it3 = bucket.getDataSets().iterator();
                    while (it3.hasNext()) {
                        zza(it3.next(), next.getDataSets());
                    }
                }
            }
        }
    }

    public final int zzz() {
        return this.zzip;
    }
}