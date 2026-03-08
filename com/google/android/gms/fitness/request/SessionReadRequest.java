package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzck;
import com.google.android.gms.internal.fitness.zzcl;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class SessionReadRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SessionReadRequest> CREATOR = new zzaw();
    private final List<DataType> zzah;
    private final List<DataSource> zzgm;
    private final boolean zzgw;
    private final String zzia;
    private final String zzib;
    private boolean zzic;
    private final List<String> zzid;
    private final zzck zzie;
    private final long zzs;
    private final long zzt;

    public static class Builder {
        private String zzia;
        private String zzib;
        private long zzs = 0;
        private long zzt = 0;
        private List<DataType> zzah = new ArrayList();
        private List<DataSource> zzgm = new ArrayList();
        private boolean zzif = false;
        private boolean zzgw = false;
        private List<String> zzid = new ArrayList();

        public SessionReadRequest build() {
            Preconditions.checkArgument(this.zzs > 0, "Invalid start time: %s", Long.valueOf(this.zzs));
            long j = this.zzt;
            Preconditions.checkArgument(j > 0 && j > this.zzs, "Invalid end time: %s", Long.valueOf(this.zzt));
            return new SessionReadRequest(this);
        }

        public Builder enableServerQueries() {
            this.zzgw = true;
            return this;
        }

        public Builder excludePackage(String str) {
            Preconditions.checkNotNull(str, "Attempting to use a null package name");
            if (!this.zzid.contains(str)) {
                this.zzid.add(str);
            }
            return this;
        }

        public Builder read(DataSource dataSource) {
            Preconditions.checkNotNull(dataSource, "Attempting to add a null data source");
            if (!this.zzgm.contains(dataSource)) {
                this.zzgm.add(dataSource);
            }
            return this;
        }

        public Builder read(DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            if (!this.zzah.contains(dataType)) {
                this.zzah.add(dataType);
            }
            return this;
        }

        public Builder readSessionsFromAllApps() {
            this.zzif = true;
            return this;
        }

        public Builder setSessionId(String str) {
            this.zzib = str;
            return this;
        }

        public Builder setSessionName(String str) {
            this.zzia = str;
            return this;
        }

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            this.zzs = timeUnit.toMillis(j);
            this.zzt = timeUnit.toMillis(j2);
            return this;
        }
    }

    private SessionReadRequest(Builder builder) {
        this(builder.zzia, builder.zzib, builder.zzs, builder.zzt, (List<DataType>) builder.zzah, (List<DataSource>) builder.zzgm, builder.zzif, builder.zzgw, (List<String>) builder.zzid, (zzck) null);
    }

    public SessionReadRequest(SessionReadRequest sessionReadRequest, zzck zzckVar) {
        this(sessionReadRequest.zzia, sessionReadRequest.zzib, sessionReadRequest.zzs, sessionReadRequest.zzt, sessionReadRequest.zzah, sessionReadRequest.zzgm, sessionReadRequest.zzic, sessionReadRequest.zzgw, sessionReadRequest.zzid, zzckVar);
    }

    SessionReadRequest(String str, String str2, long j, long j2, List<DataType> list, List<DataSource> list2, boolean z, boolean z2, List<String> list3, IBinder iBinder) {
        this.zzia = str;
        this.zzib = str2;
        this.zzs = j;
        this.zzt = j2;
        this.zzah = list;
        this.zzgm = list2;
        this.zzic = z;
        this.zzgw = z2;
        this.zzid = list3;
        this.zzie = zzcl.zzh(iBinder);
    }

    private SessionReadRequest(String str, String str2, long j, long j2, List<DataType> list, List<DataSource> list2, boolean z, boolean z2, List<String> list3, zzck zzckVar) {
        this(str, str2, j, j2, list, list2, z, z2, list3, zzckVar == null ? null : zzckVar.asBinder());
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof SessionReadRequest) {
                SessionReadRequest sessionReadRequest = (SessionReadRequest) obj;
                if (Objects.equal(this.zzia, sessionReadRequest.zzia) && this.zzib.equals(sessionReadRequest.zzib) && this.zzs == sessionReadRequest.zzs && this.zzt == sessionReadRequest.zzt && Objects.equal(this.zzah, sessionReadRequest.zzah) && Objects.equal(this.zzgm, sessionReadRequest.zzgm) && this.zzic == sessionReadRequest.zzic && this.zzid.equals(sessionReadRequest.zzid) && this.zzgw == sessionReadRequest.zzgw) {
                }
            }
            return false;
        }
        return true;
    }

    public List<DataSource> getDataSources() {
        return this.zzgm;
    }

    public List<DataType> getDataTypes() {
        return this.zzah;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzt, TimeUnit.MILLISECONDS);
    }

    public List<String> getExcludedPackages() {
        return this.zzid;
    }

    public String getSessionId() {
        return this.zzib;
    }

    public String getSessionName() {
        return this.zzia;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzs, TimeUnit.MILLISECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzia, this.zzib, Long.valueOf(this.zzs), Long.valueOf(this.zzt));
    }

    public boolean includeSessionsFromAllApps() {
        return this.zzic;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("sessionName", this.zzia).add("sessionId", this.zzib).add("startTimeMillis", Long.valueOf(this.zzs)).add("endTimeMillis", Long.valueOf(this.zzt)).add("dataTypes", this.zzah).add("dataSources", this.zzgm).add("sessionsFromAllApps", Boolean.valueOf(this.zzic)).add("excludedPackages", this.zzid).add("useServer", Boolean.valueOf(this.zzgw)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getSessionName(), false);
        SafeParcelWriter.writeString(parcel, 2, getSessionId(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzs);
        SafeParcelWriter.writeLong(parcel, 4, this.zzt);
        SafeParcelWriter.writeTypedList(parcel, 5, getDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 6, getDataSources(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, includeSessionsFromAllApps());
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzgw);
        SafeParcelWriter.writeStringList(parcel, 9, getExcludedPackages(), false);
        zzck zzckVar = this.zzie;
        SafeParcelWriter.writeIBinder(parcel, 10, zzckVar == null ? null : zzckVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}