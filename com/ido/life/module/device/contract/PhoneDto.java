package com.ido.life.module.device.contract;

import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PhoneDto.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\t\"\u0004\b\f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/ido/life/module/device/contract/PhoneDto;", "Ljava/io/Serializable;", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "telePhone", "isCheck", "", "isSelected", "(Ljava/lang/String;Ljava/lang/String;ZZ)V", "()Z", "setCheck", "(Z)V", "setSelected", "getName", "()Ljava/lang/String;", "getTelePhone", "component1", "component2", "component3", "component4", "copy", "equals", FitnessActivities.OTHER, "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class PhoneDto implements Serializable {
    private boolean isCheck;
    private boolean isSelected;
    private final String name;
    private final String telePhone;

    public static /* synthetic */ PhoneDto copy$default(PhoneDto phoneDto, String str, String str2, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = phoneDto.name;
        }
        if ((i & 2) != 0) {
            str2 = phoneDto.telePhone;
        }
        if ((i & 4) != 0) {
            z = phoneDto.isCheck;
        }
        if ((i & 8) != 0) {
            z2 = phoneDto.isSelected;
        }
        return phoneDto.copy(str, str2, z, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTelePhone() {
        return this.telePhone;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsCheck() {
        return this.isCheck;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsSelected() {
        return this.isSelected;
    }

    public final PhoneDto copy(String name, String telePhone, boolean isCheck, boolean isSelected) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(telePhone, "telePhone");
        return new PhoneDto(name, telePhone, isCheck, isSelected);
    }

    public String toString() {
        return "PhoneDto(name=" + this.name + ", telePhone=" + this.telePhone + ", isCheck=" + this.isCheck + ", isSelected=" + this.isSelected + ")";
    }

    public PhoneDto(String name, String telePhone, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(telePhone, "telePhone");
        this.name = name;
        this.telePhone = telePhone;
        this.isCheck = z;
        this.isSelected = z2;
    }

    public final String getName() {
        return this.name;
    }

    public final String getTelePhone() {
        return this.telePhone;
    }

    public final boolean isCheck() {
        return this.isCheck;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setCheck(boolean z) {
        this.isCheck = z;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            return !(Intrinsics.areEqual(this.telePhone, ((PhoneDto) other).telePhone) ^ true);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.ido.life.module.device.contract.PhoneDto");
    }

    public int hashCode() {
        return this.telePhone.hashCode();
    }
}