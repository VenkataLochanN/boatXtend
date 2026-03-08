package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public final class CredentialPickerConfig extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<CredentialPickerConfig> CREATOR = new zzf();
    private final boolean mShowCancelButton;
    private final boolean zzcq;

    @Deprecated
    private final boolean zzcr;
    private final int zzcs;
    private final int zzy;

    public static class Builder {
        private boolean zzcq = false;
        private boolean mShowCancelButton = true;
        private int zzct = 1;

        public CredentialPickerConfig build() {
            return new CredentialPickerConfig(this);
        }

        @Deprecated
        public Builder setForNewAccount(boolean z) {
            this.zzct = z ? 3 : 1;
            return this;
        }

        public Builder setPrompt(int i) {
            this.zzct = i;
            return this;
        }

        public Builder setShowAddAccountButton(boolean z) {
            this.zzcq = z;
            return this;
        }

        public Builder setShowCancelButton(boolean z) {
            this.mShowCancelButton = z;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Prompt {
        public static final int CONTINUE = 1;
        public static final int SIGN_IN = 2;
        public static final int SIGN_UP = 3;
    }

    CredentialPickerConfig(int i, boolean z, boolean z2, boolean z3, int i2) {
        this.zzy = i;
        this.zzcq = z;
        this.mShowCancelButton = z2;
        if (i < 2) {
            this.zzcr = z3;
            this.zzcs = z3 ? 3 : 1;
        } else {
            this.zzcr = i2 == 3;
            this.zzcs = i2;
        }
    }

    private CredentialPickerConfig(Builder builder) {
        this(2, builder.zzcq, builder.mShowCancelButton, false, builder.zzct);
    }

    @Deprecated
    public final boolean isForNewAccount() {
        return this.zzcs == 3;
    }

    public final boolean shouldShowAddAccountButton() {
        return this.zzcq;
    }

    public final boolean shouldShowCancelButton() {
        return this.mShowCancelButton;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, shouldShowAddAccountButton());
        SafeParcelWriter.writeBoolean(parcel, 2, shouldShowCancelButton());
        SafeParcelWriter.writeBoolean(parcel, 3, isForNewAccount());
        SafeParcelWriter.writeInt(parcel, 4, this.zzcs);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzy);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}