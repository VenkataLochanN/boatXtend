package com.ido.life.bean;

import com.google.android.gms.fitness.FitnessActivities;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DialDeleteMessage.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/ido/life/bean/DialDeleteMessage;", "Ljava/io/Serializable;", "dialName", "", "(Ljava/lang/String;)V", "getDialName", "()Ljava/lang/String;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class DialDeleteMessage implements Serializable {
    private final String dialName;

    public static /* synthetic */ DialDeleteMessage copy$default(DialDeleteMessage dialDeleteMessage, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = dialDeleteMessage.dialName;
        }
        return dialDeleteMessage.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDialName() {
        return this.dialName;
    }

    public final DialDeleteMessage copy(String dialName) {
        Intrinsics.checkParameterIsNotNull(dialName, "dialName");
        return new DialDeleteMessage(dialName);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof DialDeleteMessage) && Intrinsics.areEqual(this.dialName, ((DialDeleteMessage) other).dialName);
        }
        return true;
    }

    public int hashCode() {
        String str = this.dialName;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "DialDeleteMessage(dialName=" + this.dialName + ")";
    }

    public DialDeleteMessage(String dialName) {
        Intrinsics.checkParameterIsNotNull(dialName, "dialName");
        this.dialName = dialName;
    }

    public final String getDialName() {
        return this.dialName;
    }
}