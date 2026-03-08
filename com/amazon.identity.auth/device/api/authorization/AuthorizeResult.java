package com.amazon.identity.auth.device.api.authorization;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;

/* JADX INFO: loaded from: classes.dex */
public final class AuthorizeResult implements Parcelable {
    public static final Parcelable.Creator<AuthorizeResult> CREATOR = new Parcelable.Creator<AuthorizeResult>() { // from class: com.amazon.identity.auth.device.api.authorization.AuthorizeResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AuthorizeResult createFromParcel(Parcel parcel) {
            return new AuthorizeResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AuthorizeResult[] newArray(int i) {
            return new AuthorizeResult[i];
        }
    };
    private String accessToken;
    private String authorizationCode;
    private String clientId;
    private String redirectURI;
    private User user;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    AuthorizeResult(Bundle bundle) {
        this(bundle, (User) null);
    }

    AuthorizeResult(Bundle bundle, User user) {
        this.accessToken = bundle.getString(AuthzConstants.BUNDLE_KEY.TOKEN.val);
        this.authorizationCode = bundle.getString(AuthzConstants.BUNDLE_KEY.AUTHORIZATION_CODE.val);
        this.clientId = bundle.getString(AuthzConstants.BUNDLE_KEY.CLIENT_ID.val);
        this.redirectURI = bundle.getString(AuthzConstants.BUNDLE_KEY.REDIRECT_URI.val);
        this.user = user;
    }

    private AuthorizeResult(Parcel parcel) {
        this.accessToken = parcel.readString();
        this.authorizationCode = parcel.readString();
        this.user = (User) parcel.readParcelable(User.class.getClassLoader());
        this.clientId = parcel.readString();
        this.redirectURI = parcel.readString();
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getAuthorizationCode() {
        return this.authorizationCode;
    }

    public User getUser() {
        return this.user;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getRedirectURI() {
        return this.redirectURI;
    }

    public int hashCode() {
        String str = this.accessToken;
        int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.authorizationCode;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        User user = this.user;
        int iHashCode3 = (iHashCode2 + (user == null ? 0 : user.hashCode())) * 31;
        String str3 = this.clientId;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.redirectURI;
        return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthorizeResult authorizeResult = (AuthorizeResult) obj;
        String str = this.accessToken;
        if (str == null) {
            if (authorizeResult.accessToken != null) {
                return false;
            }
        } else if (!str.equals(authorizeResult.accessToken)) {
            return false;
        }
        String str2 = this.authorizationCode;
        if (str2 == null) {
            if (authorizeResult.authorizationCode != null) {
                return false;
            }
        } else if (!str2.equals(authorizeResult.authorizationCode)) {
            return false;
        }
        User user = this.user;
        if (user == null) {
            if (authorizeResult.user != null) {
                return false;
            }
        } else if (!user.equals(authorizeResult.user)) {
            return false;
        }
        String str3 = this.clientId;
        if (str3 == null) {
            if (authorizeResult.clientId != null) {
                return false;
            }
        } else if (!str3.equals(authorizeResult.clientId)) {
            return false;
        }
        String str4 = this.redirectURI;
        if (str4 == null) {
            if (authorizeResult.redirectURI != null) {
                return false;
            }
        } else if (!str4.equals(authorizeResult.redirectURI)) {
            return false;
        }
        return true;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.accessToken);
        parcel.writeString(this.authorizationCode);
        parcel.writeParcelable(this.user, i);
        parcel.writeString(this.clientId);
        parcel.writeString(this.redirectURI);
    }
}