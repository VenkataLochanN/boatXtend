package com.ido.life.bean;

import com.google.android.gms.fitness.FitnessActivities;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UsageDialBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/ido/life/bean/UsageDialBean;", "Ljava/io/Serializable;", "id", "", "imageUrl", "", "linkUrl", "otaFaceName", "size", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getId", "()J", "getImageUrl", "()Ljava/lang/String;", "getLinkUrl", "getOtaFaceName", "getSize", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class UsageDialBean implements Serializable {
    private final long id;
    private final String imageUrl;
    private final String linkUrl;
    private final String otaFaceName;
    private final long size;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getImageUrl() {
        return this.imageUrl;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLinkUrl() {
        return this.linkUrl;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getOtaFaceName() {
        return this.otaFaceName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getSize() {
        return this.size;
    }

    public final UsageDialBean copy(long id, String imageUrl, String linkUrl, String otaFaceName, long size) {
        Intrinsics.checkParameterIsNotNull(imageUrl, "imageUrl");
        Intrinsics.checkParameterIsNotNull(linkUrl, "linkUrl");
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        return new UsageDialBean(id, imageUrl, linkUrl, otaFaceName, size);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UsageDialBean)) {
            return false;
        }
        UsageDialBean usageDialBean = (UsageDialBean) other;
        return this.id == usageDialBean.id && Intrinsics.areEqual(this.imageUrl, usageDialBean.imageUrl) && Intrinsics.areEqual(this.linkUrl, usageDialBean.linkUrl) && Intrinsics.areEqual(this.otaFaceName, usageDialBean.otaFaceName) && this.size == usageDialBean.size;
    }

    public int hashCode() {
        int iHashCode = Long.valueOf(this.id).hashCode() * 31;
        String str = this.imageUrl;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.linkUrl;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.otaFaceName;
        return ((iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + Long.valueOf(this.size).hashCode();
    }

    public String toString() {
        return "UsageDialBean(id=" + this.id + ", imageUrl=" + this.imageUrl + ", linkUrl=" + this.linkUrl + ", otaFaceName=" + this.otaFaceName + ", size=" + this.size + ")";
    }

    public UsageDialBean(long j, String imageUrl, String linkUrl, String otaFaceName, long j2) {
        Intrinsics.checkParameterIsNotNull(imageUrl, "imageUrl");
        Intrinsics.checkParameterIsNotNull(linkUrl, "linkUrl");
        Intrinsics.checkParameterIsNotNull(otaFaceName, "otaFaceName");
        this.id = j;
        this.imageUrl = imageUrl;
        this.linkUrl = linkUrl;
        this.otaFaceName = otaFaceName;
        this.size = j2;
    }

    public final long getId() {
        return this.id;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final String getLinkUrl() {
        return this.linkUrl;
    }

    public final String getOtaFaceName() {
        return this.otaFaceName;
    }

    public final long getSize() {
        return this.size;
    }
}