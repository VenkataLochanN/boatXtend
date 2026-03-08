package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Session;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SessionStopResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<SessionStopResult> CREATOR = new zzi();
    private final List<Session> zzgn;
    private final Status zzin;

    public SessionStopResult(Status status, List<Session> list) {
        this.zzin = status;
        this.zzgn = Collections.unmodifiableList(list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof SessionStopResult) {
                SessionStopResult sessionStopResult = (SessionStopResult) obj;
                if (this.zzin.equals(sessionStopResult.zzin) && Objects.equal(this.zzgn, sessionStopResult.zzgn)) {
                }
            }
            return false;
        }
        return true;
    }

    public List<Session> getSessions() {
        return this.zzgn;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzin;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzin, this.zzgn);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zzin).add("sessions", this.zzgn).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 3, getSessions(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}