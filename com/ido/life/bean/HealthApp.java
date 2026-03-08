package com.ido.life.bean;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HealthApp.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/ido/life/bean/HealthApp;", "Ljava/io/Serializable;", "value", "", AppMeasurementSdk.ConditionalUserProperty.NAME, "", NotificationCompat.CATEGORY_STATUS, "(ILjava/lang/String;I)V", "getName", "()Ljava/lang/String;", "getStatus", "()I", "getValue", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class HealthApp implements Serializable {
    private final String name;
    private final int status;
    private final int value;

    public static /* synthetic */ HealthApp copy$default(HealthApp healthApp, int i, String str, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = healthApp.value;
        }
        if ((i3 & 2) != 0) {
            str = healthApp.name;
        }
        if ((i3 & 4) != 0) {
            i2 = healthApp.status;
        }
        return healthApp.copy(i, str, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    public final HealthApp copy(int value, String name, int status) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new HealthApp(value, name, status);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HealthApp)) {
            return false;
        }
        HealthApp healthApp = (HealthApp) other;
        return this.value == healthApp.value && Intrinsics.areEqual(this.name, healthApp.name) && this.status == healthApp.status;
    }

    public int hashCode() {
        int iHashCode = Integer.valueOf(this.value).hashCode() * 31;
        String str = this.name;
        return ((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Integer.valueOf(this.status).hashCode();
    }

    public String toString() {
        return "HealthApp(value=" + this.value + ", name=" + this.name + ", status=" + this.status + ")";
    }

    public HealthApp(int i, String name, int i2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.value = i;
        this.name = name;
        this.status = i2;
    }

    public /* synthetic */ HealthApp(int i, String str, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i3 & 2) != 0 ? "" : str, (i3 & 4) != 0 ? 0 : i2);
    }

    public final String getName() {
        return this.name;
    }

    public final int getStatus() {
        return this.status;
    }

    public final int getValue() {
        return this.value;
    }
}