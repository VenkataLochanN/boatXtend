package com.ido.life.bean;

import com.google.android.gms.fitness.FitnessActivities;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IconTransBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004¢\u0006\u0002\u0010\u0007J\u0011\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0000H\u0096\u0002J\t\u0010\u0012\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0004HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0011\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u0004H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/ido/life/bean/IconTransBean;", "Ljava/io/Serializable;", "", "moduleType", "", "dataType", "priority", "(III)V", "getDataType", "()I", "setDataType", "(I)V", "getModuleType", "setModuleType", "getPriority", "setPriority", "compareTo", FitnessActivities.OTHER, "component1", "component2", "component3", "copy", "equals", "", "", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class IconTransBean implements Serializable, Comparable<IconTransBean> {

    /* JADX INFO: renamed from: dataType, reason: from kotlin metadata and from toString */
    private int appType;
    private int moduleType;
    private int priority;

    public static /* synthetic */ IconTransBean copy$default(IconTransBean iconTransBean, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = iconTransBean.moduleType;
        }
        if ((i4 & 2) != 0) {
            i2 = iconTransBean.appType;
        }
        if ((i4 & 4) != 0) {
            i3 = iconTransBean.priority;
        }
        return iconTransBean.copy(i, i2, i3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getModuleType() {
        return this.moduleType;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getAppType() {
        return this.appType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getPriority() {
        return this.priority;
    }

    public final IconTransBean copy(int moduleType, int dataType, int priority) {
        return new IconTransBean(moduleType, dataType, priority);
    }

    public IconTransBean(int i, int i2, int i3) {
        this.moduleType = i;
        this.appType = i2;
        this.priority = i3;
    }

    public /* synthetic */ IconTransBean(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i4 & 4) != 0 ? 0 : i3);
    }

    public final int getDataType() {
        return this.appType;
    }

    public final int getModuleType() {
        return this.moduleType;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final void setDataType(int i) {
        this.appType = i;
    }

    public final void setModuleType(int i) {
        this.moduleType = i;
    }

    public final void setPriority(int i) {
        this.priority = i;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            IconTransBean iconTransBean = (IconTransBean) other;
            return this.moduleType == iconTransBean.moduleType && this.appType == iconTransBean.appType;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.ido.life.bean.IconTransBean");
    }

    public int hashCode() {
        return (this.moduleType * 31) + this.appType;
    }

    public String toString() {
        return "IconTransBean(moduleType=" + this.moduleType + ", appType=" + this.appType + ')';
    }

    @Override // java.lang.Comparable
    public int compareTo(IconTransBean other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return other.priority - this.priority;
    }
}