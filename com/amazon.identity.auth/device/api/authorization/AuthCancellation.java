package com.amazon.identity.auth.device.api.authorization;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;

/* JADX INFO: loaded from: classes.dex */
public final class AuthCancellation implements Parcelable {
    public static final Parcelable.Creator<AuthCancellation> CREATOR = new Parcelable.Creator<AuthCancellation>() { // from class: com.amazon.identity.auth.device.api.authorization.AuthCancellation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AuthCancellation createFromParcel(Parcel parcel) {
            return new AuthCancellation(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AuthCancellation[] newArray(int i) {
            return new AuthCancellation[i];
        }
    };
    private final Cause cause;
    private final String description;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private AuthCancellation(Parcel parcel) {
        this.cause = Cause.valueOf(parcel.readString());
        this.description = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.cause.name());
        parcel.writeString(this.description);
    }

    public enum Cause {
        FAILED_AUTHENTICATION;

        static Cause fromCode(int i) {
            return FAILED_AUTHENTICATION;
        }
    }

    AuthCancellation(Bundle bundle) {
        this(Cause.fromCode(bundle.getInt(AuthzConstants.BUNDLE_KEY.CAUSE_ID.val)), bundle.getString(AuthzConstants.BUNDLE_KEY.ON_CANCEL_DESCRIPTION.val));
    }

    public AuthCancellation(Cause cause, String str) {
        this.cause = cause;
        this.description = str;
    }

    public Cause getCause() {
        return this.cause;
    }

    public String getDescription() {
        return this.description;
    }

    public int hashCode() {
        Cause cause = this.cause;
        int iHashCode = ((cause == null ? 0 : cause.hashCode()) + 31) * 31;
        String str = this.description;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthCancellation authCancellation = (AuthCancellation) obj;
        if (this.cause != authCancellation.cause) {
            return false;
        }
        String str = this.description;
        if (str == null) {
            if (authCancellation.description != null) {
                return false;
            }
        } else if (!str.equals(authCancellation.description)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return String.format("%s {cause='%s', description='%s'}", super.toString(), this.cause.toString(), this.description);
    }
}