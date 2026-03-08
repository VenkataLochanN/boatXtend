package com.ido.life.module.home;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeHelper.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J\t\u0010\u001c\u001a\u00020\u000bHÆ\u0003JE\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u000b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0006HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\""}, d2 = {"Lcom/ido/life/module/home/WholeLifeCycleInfo;", "", "mensStartDate", "", "mensEndDate", "mensesDays", "", "mensesCycle", "timeStamp", "", "Upload", "", "(Ljava/lang/String;Ljava/lang/String;IIJZ)V", "getUpload", "()Z", "getMensEndDate", "()Ljava/lang/String;", "getMensStartDate", "getMensesCycle", "()I", "getMensesDays", "getTimeStamp", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class WholeLifeCycleInfo {
    private final boolean Upload;
    private final String mensEndDate;
    private final String mensStartDate;
    private final int mensesCycle;
    private final int mensesDays;
    private final long timeStamp;

    public static /* synthetic */ WholeLifeCycleInfo copy$default(WholeLifeCycleInfo wholeLifeCycleInfo, String str, String str2, int i, int i2, long j, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = wholeLifeCycleInfo.mensStartDate;
        }
        if ((i3 & 2) != 0) {
            str2 = wholeLifeCycleInfo.mensEndDate;
        }
        String str3 = str2;
        if ((i3 & 4) != 0) {
            i = wholeLifeCycleInfo.mensesDays;
        }
        int i4 = i;
        if ((i3 & 8) != 0) {
            i2 = wholeLifeCycleInfo.mensesCycle;
        }
        int i5 = i2;
        if ((i3 & 16) != 0) {
            j = wholeLifeCycleInfo.timeStamp;
        }
        long j2 = j;
        if ((i3 & 32) != 0) {
            z = wholeLifeCycleInfo.Upload;
        }
        return wholeLifeCycleInfo.copy(str, str3, i4, i5, j2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getMensStartDate() {
        return this.mensStartDate;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMensEndDate() {
        return this.mensEndDate;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getMensesDays() {
        return this.mensesDays;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getMensesCycle() {
        return this.mensesCycle;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getTimeStamp() {
        return this.timeStamp;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getUpload() {
        return this.Upload;
    }

    public final WholeLifeCycleInfo copy(String mensStartDate, String mensEndDate, int mensesDays, int mensesCycle, long timeStamp, boolean Upload) {
        Intrinsics.checkParameterIsNotNull(mensStartDate, "mensStartDate");
        Intrinsics.checkParameterIsNotNull(mensEndDate, "mensEndDate");
        return new WholeLifeCycleInfo(mensStartDate, mensEndDate, mensesDays, mensesCycle, timeStamp, Upload);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WholeLifeCycleInfo)) {
            return false;
        }
        WholeLifeCycleInfo wholeLifeCycleInfo = (WholeLifeCycleInfo) other;
        return Intrinsics.areEqual(this.mensStartDate, wholeLifeCycleInfo.mensStartDate) && Intrinsics.areEqual(this.mensEndDate, wholeLifeCycleInfo.mensEndDate) && this.mensesDays == wholeLifeCycleInfo.mensesDays && this.mensesCycle == wholeLifeCycleInfo.mensesCycle && this.timeStamp == wholeLifeCycleInfo.timeStamp && this.Upload == wholeLifeCycleInfo.Upload;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v9, types: [int] */
    public int hashCode() {
        String str = this.mensStartDate;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.mensEndDate;
        int iHashCode2 = (((((((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.valueOf(this.mensesDays).hashCode()) * 31) + Integer.valueOf(this.mensesCycle).hashCode()) * 31) + Long.valueOf(this.timeStamp).hashCode()) * 31;
        boolean z = this.Upload;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode2 + r1;
    }

    public String toString() {
        return "WholeLifeCycleInfo(mensStartDate=" + this.mensStartDate + ", mensEndDate=" + this.mensEndDate + ", mensesDays=" + this.mensesDays + ", mensesCycle=" + this.mensesCycle + ", timeStamp=" + this.timeStamp + ", Upload=" + this.Upload + ")";
    }

    public WholeLifeCycleInfo(String mensStartDate, String mensEndDate, int i, int i2, long j, boolean z) {
        Intrinsics.checkParameterIsNotNull(mensStartDate, "mensStartDate");
        Intrinsics.checkParameterIsNotNull(mensEndDate, "mensEndDate");
        this.mensStartDate = mensStartDate;
        this.mensEndDate = mensEndDate;
        this.mensesDays = i;
        this.mensesCycle = i2;
        this.timeStamp = j;
        this.Upload = z;
    }

    public final String getMensStartDate() {
        return this.mensStartDate;
    }

    public final String getMensEndDate() {
        return this.mensEndDate;
    }

    public final int getMensesDays() {
        return this.mensesDays;
    }

    public final int getMensesCycle() {
        return this.mensesCycle;
    }

    public final long getTimeStamp() {
        return this.timeStamp;
    }

    public /* synthetic */ WholeLifeCycleInfo(String str, String str2, int i, int i2, long j, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, i2, j, (i3 & 32) != 0 ? false : z);
    }

    public final boolean getUpload() {
        return this.Upload;
    }
}