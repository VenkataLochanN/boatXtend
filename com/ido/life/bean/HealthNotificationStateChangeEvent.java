package com.ido.life.bean;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;

/* JADX INFO: compiled from: HealthNotificationStateChangeEvent.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/ido/life/bean/HealthNotificationStateChangeEvent;", "", "type", "", "state", "(II)V", "getState", "()I", "getType", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class HealthNotificationStateChangeEvent {
    private final int state;
    private final int type;

    public static /* synthetic */ HealthNotificationStateChangeEvent copy$default(HealthNotificationStateChangeEvent healthNotificationStateChangeEvent, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = healthNotificationStateChangeEvent.type;
        }
        if ((i3 & 2) != 0) {
            i2 = healthNotificationStateChangeEvent.state;
        }
        return healthNotificationStateChangeEvent.copy(i, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getState() {
        return this.state;
    }

    public final HealthNotificationStateChangeEvent copy(int type, int state) {
        return new HealthNotificationStateChangeEvent(type, state);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HealthNotificationStateChangeEvent)) {
            return false;
        }
        HealthNotificationStateChangeEvent healthNotificationStateChangeEvent = (HealthNotificationStateChangeEvent) other;
        return this.type == healthNotificationStateChangeEvent.type && this.state == healthNotificationStateChangeEvent.state;
    }

    public int hashCode() {
        return (Integer.valueOf(this.type).hashCode() * 31) + Integer.valueOf(this.state).hashCode();
    }

    public String toString() {
        return "HealthNotificationStateChangeEvent(type=" + this.type + ", state=" + this.state + ")";
    }

    public HealthNotificationStateChangeEvent(int i, int i2) {
        this.type = i;
        this.state = i2;
    }

    public final int getType() {
        return this.type;
    }

    public final int getState() {
        return this.state;
    }
}