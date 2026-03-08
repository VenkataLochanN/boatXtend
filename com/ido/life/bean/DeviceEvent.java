package com.ido.life.bean;

import com.google.android.gms.fitness.FitnessActivities;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeviceEvent.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/ido/life/bean/DeviceEvent;", "Ljava/io/Serializable;", "event", "", "offsetSeconds", "", "(Ljava/lang/String;J)V", "getEvent", "()Ljava/lang/String;", "getOffsetSeconds", "()J", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class DeviceEvent implements Serializable {
    private final String event;
    private final long offsetSeconds;

    public static /* synthetic */ DeviceEvent copy$default(DeviceEvent deviceEvent, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deviceEvent.event;
        }
        if ((i & 2) != 0) {
            j = deviceEvent.offsetSeconds;
        }
        return deviceEvent.copy(str, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getEvent() {
        return this.event;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getOffsetSeconds() {
        return this.offsetSeconds;
    }

    public final DeviceEvent copy(String event, long offsetSeconds) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        return new DeviceEvent(event, offsetSeconds);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceEvent)) {
            return false;
        }
        DeviceEvent deviceEvent = (DeviceEvent) other;
        return Intrinsics.areEqual(this.event, deviceEvent.event) && this.offsetSeconds == deviceEvent.offsetSeconds;
    }

    public int hashCode() {
        String str = this.event;
        return ((str != null ? str.hashCode() : 0) * 31) + Long.valueOf(this.offsetSeconds).hashCode();
    }

    public String toString() {
        return "DeviceEvent(event=" + this.event + ", offsetSeconds=" + this.offsetSeconds + ")";
    }

    public DeviceEvent(String event, long j) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.event = event;
        this.offsetSeconds = j;
    }

    public final String getEvent() {
        return this.event;
    }

    public final long getOffsetSeconds() {
        return this.offsetSeconds;
    }
}