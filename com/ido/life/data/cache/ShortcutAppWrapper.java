package com.ido.life.data.cache;

import com.autonavi.base.amap.mapcore.AeUtil;
import com.google.android.gms.fitness.FitnessActivities;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShortcutAppWrapper.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\u000e\u0010\u0013\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u000bJ<\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u0004H\u0016J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0013\u0010\b\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\rR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/ido/life/data/cache/ShortcutAppWrapper;", "T", "Ljava/io/Serializable;", "type", "", "isFirstInGroup", "", "isLastInGroup", AeUtil.ROOT_DATA_PATH_OLD_NAME, "(IZZLjava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "()Z", "getType", "()I", "component1", "component2", "component3", "component4", "copy", "(IZZLjava/lang/Object;)Lcom/ido/life/data/cache/ShortcutAppWrapper;", "equals", FitnessActivities.OTHER, "", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class ShortcutAppWrapper<T> implements Serializable {
    private final T data;
    private final boolean isFirstInGroup;
    private final boolean isLastInGroup;
    private final int type;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ShortcutAppWrapper copy$default(ShortcutAppWrapper shortcutAppWrapper, int i, boolean z, boolean z2, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            i = shortcutAppWrapper.type;
        }
        if ((i2 & 2) != 0) {
            z = shortcutAppWrapper.isFirstInGroup;
        }
        if ((i2 & 4) != 0) {
            z2 = shortcutAppWrapper.isLastInGroup;
        }
        if ((i2 & 8) != 0) {
            obj = shortcutAppWrapper.data;
        }
        return shortcutAppWrapper.copy(i, z, z2, obj);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsFirstInGroup() {
        return this.isFirstInGroup;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsLastInGroup() {
        return this.isLastInGroup;
    }

    public final T component4() {
        return this.data;
    }

    public final ShortcutAppWrapper<T> copy(int type, boolean isFirstInGroup, boolean isLastInGroup, T data) {
        return new ShortcutAppWrapper<>(type, isFirstInGroup, isLastInGroup, data);
    }

    public String toString() {
        return "ShortcutAppWrapper(type=" + this.type + ", isFirstInGroup=" + this.isFirstInGroup + ", isLastInGroup=" + this.isLastInGroup + ", data=" + this.data + ")";
    }

    public ShortcutAppWrapper(int i, boolean z, boolean z2, T t) {
        this.type = i;
        this.isFirstInGroup = z;
        this.isLastInGroup = z2;
        this.data = t;
    }

    public final int getType() {
        return this.type;
    }

    public final boolean isFirstInGroup() {
        return this.isFirstInGroup;
    }

    public /* synthetic */ ShortcutAppWrapper(int i, boolean z, boolean z2, Object obj, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? false : z2, obj);
    }

    public final boolean isLastInGroup() {
        return this.isLastInGroup;
    }

    public final T getData() {
        return this.data;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            ShortcutAppWrapper shortcutAppWrapper = (ShortcutAppWrapper) other;
            return this.type == shortcutAppWrapper.type && !(Intrinsics.areEqual(this.data, shortcutAppWrapper.data) ^ true);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.ido.life.data.cache.ShortcutAppWrapper<*>");
    }

    public int hashCode() {
        int i = this.type * 31;
        T t = this.data;
        return i + (t != null ? t.hashCode() : 0);
    }
}