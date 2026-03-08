package com.ido.life.bean;

import com.google.android.gms.fitness.FitnessActivities;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShortcutAppData.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\u001f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0003H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011¨\u0006\u001b"}, d2 = {"Lcom/ido/life/bean/ShortcutAppData;", "Ljava/io/Serializable;", "size_type", "", "widgets_type", "(II)V", "showTypes", "", "(IILjava/util/List;)V", "(ILjava/util/List;)V", "getShowTypes", "()Ljava/util/List;", "setShowTypes", "(Ljava/util/List;)V", "getSize_type", "()I", "setSize_type", "(I)V", "getWidgets_type", "setWidgets_type", "equals", "", FitnessActivities.OTHER, "", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ShortcutAppData implements Serializable {
    private List<Integer> showTypes;
    private int size_type;
    private int widgets_type;

    public final List<Integer> getShowTypes() {
        return this.showTypes;
    }

    public final void setShowTypes(List<Integer> list) {
        this.showTypes = list;
    }

    public final int getSize_type() {
        return this.size_type;
    }

    public final void setSize_type(int i) {
        this.size_type = i;
    }

    public final int getWidgets_type() {
        return this.widgets_type;
    }

    public final void setWidgets_type(int i) {
        this.widgets_type = i;
    }

    public ShortcutAppData(int i, int i2) {
        this.size_type = i;
        this.widgets_type = i2;
    }

    public ShortcutAppData(int i, int i2, List<Integer> list) {
        this.size_type = i;
        this.widgets_type = i2;
        this.showTypes = list;
    }

    public ShortcutAppData(int i, List<Integer> list) {
        this.widgets_type = i;
        this.showTypes = list;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            return this.widgets_type == ((ShortcutAppData) other).widgets_type;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.ido.life.bean.ShortcutAppData");
    }

    public int hashCode() {
        return this.widgets_type;
    }

    public String toString() {
        return "ShortcutAppData(size_type=" + this.size_type + ", widgets_type=" + this.widgets_type + ')';
    }
}