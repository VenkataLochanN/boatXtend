package com.amazon.identity.auth.map.device.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.amazon.identity.auth.device.utils.MAPVersionHelper;
import java.security.InvalidParameterException;

/* JADX INFO: loaded from: classes.dex */
public final class MAPVersion implements Parcelable {
    private static final String VERSION_SEPERATOR = "\\.";
    private final int[] mVersion;
    private static final String LOG_TAG = MAPVersion.class.getName();
    public static final MAPVersion VERSION_ZERO = new MAPVersion("0.0.0");
    public static final Parcelable.Creator<MAPVersion> CREATOR = new Parcelable.Creator<MAPVersion>() { // from class: com.amazon.identity.auth.map.device.utils.MAPVersion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MAPVersion createFromParcel(Parcel parcel) {
            return new MAPVersion(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MAPVersion[] newArray(int i) {
            return new MAPVersion[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MAPVersion(Parcel parcel) {
        this.mVersion = new int[parcel.readInt()];
        parcel.readIntArray(this.mVersion);
        MAPLog.i(LOG_TAG, "MAPVersion Created from PARCEL: " + toString());
    }

    public MAPVersion(int[] iArr) {
        if (iArr == null) {
            throw new InvalidParameterException("version must not be null");
        }
        if (iArr.length == 0) {
            throw new InvalidParameterException("Version must not be empty");
        }
        this.mVersion = iArr;
        MAPLog.i(LOG_TAG, "MAPVersion Created : " + toString());
    }

    public MAPVersion(String str) {
        MAPLog.i(LOG_TAG, "MAPVersion from String : " + str);
        if (str == null) {
            throw new InvalidParameterException("version must not be null");
        }
        String[] strArrSplit = TextUtils.split(str, VERSION_SEPERATOR);
        this.mVersion = new int[strArrSplit.length];
        int i = 0;
        for (String str2 : strArrSplit) {
            try {
                this.mVersion[i] = Integer.parseInt(str2);
            } catch (NumberFormatException unused) {
                this.mVersion[i] = 0;
            }
            i++;
        }
    }

    public String toString() {
        return MAPVersionHelper.getVersionAsString(this.mVersion);
    }

    public int[] getVersionInfo() {
        return this.mVersion;
    }

    public int compare(MAPVersion mAPVersion) {
        try {
            int[] versionInfo = mAPVersion.getVersionInfo();
            int iMin = Math.min(this.mVersion.length, mAPVersion.getVersionInfo().length) - 1;
            int i = 0;
            while (i < iMin && this.mVersion[i] == versionInfo[i]) {
                i++;
            }
            Integer numValueOf = Integer.valueOf(this.mVersion[i]);
            Integer numValueOf2 = Integer.valueOf(versionInfo[i]);
            if (i == this.mVersion.length && this.mVersion.length == mAPVersion.getVersionInfo().length) {
                return 0;
            }
            if (versionInfo.length != this.mVersion.length && numValueOf.equals(numValueOf2)) {
                return Integer.valueOf(this.mVersion.length).compareTo(Integer.valueOf(versionInfo.length));
            }
            return numValueOf.compareTo(numValueOf2);
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new ArrayIndexOutOfBoundsException("1=" + toString() + " vs 2=" + mAPVersion.toString() + " " + e2.getMessage());
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        MAPLog.i(LOG_TAG, "MAPVersion writing " + this.mVersion.length + " ints to parcel");
        parcel.writeInt(this.mVersion.length);
        parcel.writeIntArray(this.mVersion);
    }
}