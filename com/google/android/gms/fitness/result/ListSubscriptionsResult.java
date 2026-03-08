package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ListSubscriptionsResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<ListSubscriptionsResult> CREATOR = new zzg();
    private final Status zzin;
    private final List<Subscription> zzir;

    public ListSubscriptionsResult(List<Subscription> list, Status status) {
        this.zzir = list;
        this.zzin = status;
    }

    public static ListSubscriptionsResult zzd(Status status) {
        return new ListSubscriptionsResult(Collections.emptyList(), status);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ListSubscriptionsResult) {
                ListSubscriptionsResult listSubscriptionsResult = (ListSubscriptionsResult) obj;
                if (this.zzin.equals(listSubscriptionsResult.zzin) && Objects.equal(this.zzir, listSubscriptionsResult.zzir)) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzin;
    }

    public List<Subscription> getSubscriptions() {
        return this.zzir;
    }

    public List<Subscription> getSubscriptions(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (Subscription subscription : this.zzir) {
            if (dataType.equals(subscription.zzq())) {
                arrayList.add(subscription);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzin, this.zzir);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zzin).add("subscriptions", this.zzir).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getSubscriptions(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}