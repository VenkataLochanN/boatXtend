package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Goal;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GoalsResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<GoalsResult> CREATOR = new zzf();
    private final Status zzin;
    private final List<Goal> zziq;

    public GoalsResult(Status status, List<Goal> list) {
        this.zzin = status;
        this.zziq = list;
    }

    public List<Goal> getGoals() {
        return this.zziq;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzin;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, getGoals(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}