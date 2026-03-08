package com.ido.life.bean;

import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppNameBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/ido/life/bean/AppNameBean;", "Ljava/io/Serializable;", "language_type", "", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "(ILjava/lang/String;)V", "getLanguage_type", "()I", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class AppNameBean implements Serializable {
    private final int language_type;
    private final String name;

    public static /* synthetic */ AppNameBean copy$default(AppNameBean appNameBean, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = appNameBean.language_type;
        }
        if ((i2 & 2) != 0) {
            str = appNameBean.name;
        }
        return appNameBean.copy(i, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getLanguage_type() {
        return this.language_type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final AppNameBean copy(int language_type, String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new AppNameBean(language_type, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppNameBean)) {
            return false;
        }
        AppNameBean appNameBean = (AppNameBean) other;
        return this.language_type == appNameBean.language_type && Intrinsics.areEqual(this.name, appNameBean.name);
    }

    public int hashCode() {
        int iHashCode = Integer.valueOf(this.language_type).hashCode() * 31;
        String str = this.name;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "AppNameBean(language_type=" + this.language_type + ", name=" + this.name + ")";
    }

    public AppNameBean(int i, String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.language_type = i;
        this.name = name;
    }

    public final int getLanguage_type() {
        return this.language_type;
    }

    public final String getName() {
        return this.name;
    }
}