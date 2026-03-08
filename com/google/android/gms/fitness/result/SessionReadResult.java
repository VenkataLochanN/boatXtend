package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.zzae;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SessionReadResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<SessionReadResult> CREATOR = new zzh();
    private final List<Session> zzgn;
    private final Status zzin;
    private final List<zzae> zzis;

    public SessionReadResult(List<Session> list, List<zzae> list2, Status status) {
        this.zzgn = list;
        this.zzis = Collections.unmodifiableList(list2);
        this.zzin = status;
    }

    public static SessionReadResult zze(Status status) {
        return new SessionReadResult(new ArrayList(), new ArrayList(), status);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof SessionReadResult) {
                SessionReadResult sessionReadResult = (SessionReadResult) obj;
                if (this.zzin.equals(sessionReadResult.zzin) && Objects.equal(this.zzgn, sessionReadResult.zzgn) && Objects.equal(this.zzis, sessionReadResult.zzis)) {
                }
            }
            return false;
        }
        return true;
    }

    public List<DataSet> getDataSet(Session session) {
        Preconditions.checkArgument(this.zzgn.contains(session), "Attempting to read data for session %s which was not returned", session);
        ArrayList arrayList = new ArrayList();
        for (zzae zzaeVar : this.zzis) {
            if (Objects.equal(session, zzaeVar.getSession())) {
                arrayList.add(zzaeVar.getDataSet());
            }
        }
        return arrayList;
    }

    public List<DataSet> getDataSet(Session session, DataType dataType) {
        Preconditions.checkArgument(this.zzgn.contains(session), "Attempting to read data for session %s which was not returned", session);
        ArrayList arrayList = new ArrayList();
        for (zzae zzaeVar : this.zzis) {
            if (Objects.equal(session, zzaeVar.getSession()) && dataType.equals(zzaeVar.getDataSet().getDataType())) {
                arrayList.add(zzaeVar.getDataSet());
            }
        }
        return arrayList;
    }

    public List<Session> getSessions() {
        return this.zzgn;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzin;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzin, this.zzgn, this.zzis);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zzin).add("sessions", this.zzgn).add("sessionDataSets", this.zzis).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getSessions(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzis, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}