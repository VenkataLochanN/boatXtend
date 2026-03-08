package com.ido.life.bean;

import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MotionTypeBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003JE\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010 \u001a\u00020\n2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0096\u0002J\b\u0010#\u001a\u00020\u0003H\u0016J\t\u0010$\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0011\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011¨\u0006%"}, d2 = {"Lcom/ido/life/bean/MotionTypeBean;", "Ljava/io/Serializable;", "type", "", "groupType", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "iconResId", "iconFlag", "available", "", "(IILjava/lang/String;IIZ)V", "getAvailable", "()Z", "setAvailable", "(Z)V", "getGroupType", "()I", "getIconFlag", "setIconFlag", "(I)V", "getIconResId", "getName", "()Ljava/lang/String;", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", FitnessActivities.OTHER, "", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class MotionTypeBean implements Serializable {
    private boolean available;
    private final int groupType;
    private int iconFlag;
    private final int iconResId;
    private final String name;
    private final int type;

    public static /* synthetic */ MotionTypeBean copy$default(MotionTypeBean motionTypeBean, int i, int i2, String str, int i3, int i4, boolean z, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = motionTypeBean.type;
        }
        if ((i5 & 2) != 0) {
            i2 = motionTypeBean.groupType;
        }
        int i6 = i2;
        if ((i5 & 4) != 0) {
            str = motionTypeBean.name;
        }
        String str2 = str;
        if ((i5 & 8) != 0) {
            i3 = motionTypeBean.iconResId;
        }
        int i7 = i3;
        if ((i5 & 16) != 0) {
            i4 = motionTypeBean.iconFlag;
        }
        int i8 = i4;
        if ((i5 & 32) != 0) {
            z = motionTypeBean.available;
        }
        return motionTypeBean.copy(i, i6, str2, i7, i8, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getGroupType() {
        return this.groupType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getIconResId() {
        return this.iconResId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getIconFlag() {
        return this.iconFlag;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getAvailable() {
        return this.available;
    }

    public final MotionTypeBean copy(int type, int groupType, String name, int iconResId, int iconFlag, boolean available) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new MotionTypeBean(type, groupType, name, iconResId, iconFlag, available);
    }

    public String toString() {
        return "MotionTypeBean(type=" + this.type + ", groupType=" + this.groupType + ", name=" + this.name + ", iconResId=" + this.iconResId + ", iconFlag=" + this.iconFlag + ", available=" + this.available + ")";
    }

    public MotionTypeBean(int i, int i2, String name, int i3, int i4, boolean z) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.type = i;
        this.groupType = i2;
        this.name = name;
        this.iconResId = i3;
        this.iconFlag = i4;
        this.available = z;
    }

    public final int getType() {
        return this.type;
    }

    public final int getGroupType() {
        return this.groupType;
    }

    public final String getName() {
        return this.name;
    }

    public final int getIconResId() {
        return this.iconResId;
    }

    public final int getIconFlag() {
        return this.iconFlag;
    }

    public final void setIconFlag(int i) {
        this.iconFlag = i;
    }

    public /* synthetic */ MotionTypeBean(int i, int i2, String str, int i3, int i4, boolean z, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, str, i3, i4, (i5 & 32) != 0 ? false : z);
    }

    public final boolean getAvailable() {
        return this.available;
    }

    public final void setAvailable(boolean z) {
        this.available = z;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            return this.type == ((MotionTypeBean) other).type;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.ido.life.bean.MotionTypeBean");
    }

    public int hashCode() {
        return this.type;
    }
}