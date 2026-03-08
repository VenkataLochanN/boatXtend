package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzfa;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class Bucket extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<Bucket> CREATOR = new zze();
    public static final int TYPE_ACTIVITY_SEGMENT = 4;
    public static final int TYPE_ACTIVITY_TYPE = 3;
    public static final int TYPE_SESSION = 2;
    public static final int TYPE_TIME = 1;
    private final int zzai;
    private final List<DataSet> zzaj;
    private final int zzak;
    private boolean zzal;
    private final long zzs;
    private final long zzt;
    private final Session zzz;

    Bucket(long j, long j2, Session session, int i, List<DataSet> list, int i2, boolean z) {
        this.zzal = false;
        this.zzs = j;
        this.zzt = j2;
        this.zzz = session;
        this.zzai = i;
        this.zzaj = list;
        this.zzak = i2;
        this.zzal = z;
    }

    public Bucket(RawBucket rawBucket, List<DataSource> list) {
        long j = rawBucket.zzs;
        long j2 = rawBucket.zzt;
        Session session = rawBucket.zzz;
        int i = rawBucket.zzdv;
        List<RawDataSet> list2 = rawBucket.zzaj;
        ArrayList arrayList = new ArrayList(list2.size());
        Iterator<RawDataSet> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(new DataSet(it.next(), list));
        }
        this(j, j2, session, i, arrayList, rawBucket.zzak, rawBucket.zzal);
    }

    public static String zza(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "bug" : "segment" : "type" : "session" : "time" : "unknown";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Bucket)) {
            return false;
        }
        Bucket bucket = (Bucket) obj;
        return this.zzs == bucket.zzs && this.zzt == bucket.zzt && this.zzai == bucket.zzai && Objects.equal(this.zzaj, bucket.zzaj) && this.zzak == bucket.zzak && this.zzal == bucket.zzal;
    }

    public String getActivity() {
        return zzfa.getName(this.zzai);
    }

    public final int getActivityType() {
        return this.zzai;
    }

    public int getBucketType() {
        return this.zzak;
    }

    public DataSet getDataSet(DataType dataType) {
        for (DataSet dataSet : this.zzaj) {
            if (dataSet.getDataType().equals(dataType)) {
                return dataSet;
            }
        }
        return null;
    }

    public List<DataSet> getDataSets() {
        return this.zzaj;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzt, TimeUnit.MILLISECONDS);
    }

    public Session getSession() {
        return this.zzz;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzs, TimeUnit.MILLISECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzs), Long.valueOf(this.zzt), Integer.valueOf(this.zzai), Integer.valueOf(this.zzak));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.zzs)).add("endTime", Long.valueOf(this.zzt)).add("activity", Integer.valueOf(this.zzai)).add("dataSets", this.zzaj).add("bucketType", zza(this.zzak)).add("serverHasMoreData", Boolean.valueOf(this.zzal)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzs);
        SafeParcelWriter.writeLong(parcel, 2, this.zzt);
        SafeParcelWriter.writeParcelable(parcel, 3, getSession(), i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzai);
        SafeParcelWriter.writeTypedList(parcel, 5, getDataSets(), false);
        SafeParcelWriter.writeInt(parcel, 6, getBucketType());
        SafeParcelWriter.writeBoolean(parcel, 7, zza());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final boolean zza() {
        if (this.zzal) {
            return true;
        }
        Iterator<DataSet> it = this.zzaj.iterator();
        while (it.hasNext()) {
            if (it.next().zza()) {
                return true;
            }
        }
        return false;
    }

    public final boolean zza(Bucket bucket) {
        return this.zzs == bucket.zzs && this.zzt == bucket.zzt && this.zzai == bucket.zzai && this.zzak == bucket.zzak;
    }
}