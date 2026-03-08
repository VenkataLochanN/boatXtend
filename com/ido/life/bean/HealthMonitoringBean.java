package com.ido.life.bean;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.fitness.FitnessActivities;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HealthMonitoringBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0003H\u0016J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/ido/life/bean/HealthMonitoringBean;", "Ljava/io/Serializable;", "type", "", "statusValue", NotificationCompat.CATEGORY_STATUS, "", "(IIZ)V", "getStatus", "()Z", "setStatus", "(Z)V", "getStatusValue", "()I", "setStatusValue", "(I)V", "getType", "component1", "component2", "component3", "copy", "equals", FitnessActivities.OTHER, "", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class HealthMonitoringBean implements Serializable {
    private boolean status;
    private int statusValue;
    private final int type;

    public static /* synthetic */ HealthMonitoringBean copy$default(HealthMonitoringBean healthMonitoringBean, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = healthMonitoringBean.type;
        }
        if ((i3 & 2) != 0) {
            i2 = healthMonitoringBean.statusValue;
        }
        if ((i3 & 4) != 0) {
            z = healthMonitoringBean.status;
        }
        return healthMonitoringBean.copy(i, i2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getStatusValue() {
        return this.statusValue;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getStatus() {
        return this.status;
    }

    public final HealthMonitoringBean copy(int type, int statusValue, boolean status) {
        return new HealthMonitoringBean(type, statusValue, status);
    }

    public String toString() {
        return "HealthMonitoringBean(type=" + this.type + ", statusValue=" + this.statusValue + ", status=" + this.status + ")";
    }

    public HealthMonitoringBean(int i, int i2, boolean z) {
        this.type = i;
        this.statusValue = i2;
        this.status = z;
    }

    public final int getType() {
        return this.type;
    }

    public final int getStatusValue() {
        return this.statusValue;
    }

    public final void setStatusValue(int i) {
        this.statusValue = i;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ HealthMonitoringBean(int i, int i2, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i3 & 4) != 0) {
            z = true;
            if (i2 != 1) {
                z = false;
            }
        }
        this(i, i2, z);
    }

    public final boolean getStatus() {
        return this.status;
    }

    public final void setStatus(boolean z) {
        this.status = z;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            return this.type == ((HealthMonitoringBean) other).type;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.ido.life.bean.HealthMonitoringBean");
    }

    public int hashCode() {
        return this.type;
    }
}