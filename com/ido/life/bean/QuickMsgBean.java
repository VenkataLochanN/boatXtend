package com.ido.life.bean;

import com.google.android.gms.fitness.FitnessActivities;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QuickMsgBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0003H\u0016J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/ido/life/bean/QuickMsgBean;", "Ljava/io/Serializable;", "index", "", "msg", "", "(ILjava/lang/String;)V", "getIndex", "()I", "getMsg", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class QuickMsgBean implements Serializable {
    private final int index;
    private final String msg;

    public static /* synthetic */ QuickMsgBean copy$default(QuickMsgBean quickMsgBean, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = quickMsgBean.index;
        }
        if ((i2 & 2) != 0) {
            str = quickMsgBean.msg;
        }
        return quickMsgBean.copy(i, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMsg() {
        return this.msg;
    }

    public final QuickMsgBean copy(int index, String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        return new QuickMsgBean(index, msg);
    }

    public String toString() {
        return "QuickMsgBean(index=" + this.index + ", msg=" + this.msg + ")";
    }

    public QuickMsgBean(int i, String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        this.index = i;
        this.msg = msg;
    }

    public final int getIndex() {
        return this.index;
    }

    public final String getMsg() {
        return this.msg;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            return this.index == ((QuickMsgBean) other).index;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.ido.life.bean.QuickMsgBean");
    }

    public int hashCode() {
        return this.index;
    }
}