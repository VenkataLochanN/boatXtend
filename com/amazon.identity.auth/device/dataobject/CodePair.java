package com.amazon.identity.auth.device.dataobject;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.datastore.CodePairDataSource;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.device.utils.ScopeUtils;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class CodePair extends AbstractDataObject {
    private final String mAppId;
    private final Date mCreationTime;
    private final String mDeviceCode;
    private final Date mExpirationTime;
    private final int mInterval;
    private final String[] mScopes;
    private final String mUserCode;
    private final URI mVerificationUri;
    private static final String LOG_TAG = CodePair.class.getName();
    public static final String[] ALL_COLUMNS = {"Id", "AppId", DatabaseHelper.codePair_UserCode, DatabaseHelper.codePair_DeviceCode, DatabaseHelper.codePair_VerificationUri, DatabaseHelper.codePair_Interval, "CreationTime", "ExpirationTime", DatabaseHelper.codePair_Scopes};

    public CodePair(String str, String str2, String str3, URI uri, int i, Date date, Date date2, String[] strArr) {
        this.mAppId = str;
        this.mUserCode = str2;
        this.mDeviceCode = str3;
        this.mVerificationUri = uri;
        this.mInterval = i;
        this.mCreationTime = DatabaseHelper.truncateFractionalSeconds(date);
        this.mExpirationTime = DatabaseHelper.truncateFractionalSeconds(date2);
        this.mScopes = strArr;
    }

    public enum COL_INDEX {
        ID(0),
        APP_ID(1),
        USER_CODE(2),
        DEVICE_CODE(3),
        VERIFICATION_URI(4),
        INTERVAL(5),
        CREATION_TIME(6),
        EXPIRATION_TIME(7),
        SCOPES(8);

        public final int colId;

        COL_INDEX(int i) {
            this.colId = i;
        }
    }

    public String getAppId() {
        return this.mAppId;
    }

    public String getUserCode() {
        return this.mUserCode;
    }

    public String getDeviceCode() {
        return this.mDeviceCode;
    }

    public URI getVerificationUri() {
        return this.mVerificationUri;
    }

    public Date getCreationTime() {
        return this.mCreationTime;
    }

    public Date getExpirationTime() {
        return this.mExpirationTime;
    }

    public int getInterval() {
        return this.mInterval;
    }

    public String[] getScopes() {
        return this.mScopes;
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public ContentValues getValuesForInsert() {
        ContentValues contentValues = new ContentValues();
        SimpleDateFormat dateFormat = DatabaseHelper.getDateFormat();
        contentValues.put(ALL_COLUMNS[COL_INDEX.APP_ID.colId], this.mAppId);
        contentValues.put(ALL_COLUMNS[COL_INDEX.USER_CODE.colId], this.mUserCode);
        contentValues.put(ALL_COLUMNS[COL_INDEX.DEVICE_CODE.colId], this.mDeviceCode);
        contentValues.put(ALL_COLUMNS[COL_INDEX.VERIFICATION_URI.colId], this.mVerificationUri.toString());
        contentValues.put(ALL_COLUMNS[COL_INDEX.INTERVAL.colId], Integer.valueOf(this.mInterval));
        contentValues.put(ALL_COLUMNS[COL_INDEX.CREATION_TIME.colId], dateFormat.format(this.mCreationTime));
        contentValues.put(ALL_COLUMNS[COL_INDEX.EXPIRATION_TIME.colId], dateFormat.format(this.mExpirationTime));
        contentValues.put(ALL_COLUMNS[COL_INDEX.SCOPES.colId], ScopeUtils.convertScopeArrayToString(this.mScopes));
        return contentValues;
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public CodePairDataSource getDataSource(Context context) {
        return CodePairDataSource.getInstance(context);
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof CodePair)) {
            return false;
        }
        CodePair codePair = (CodePair) obj;
        return TextUtils.equals(this.mAppId, codePair.getAppId()) && TextUtils.equals(this.mUserCode, codePair.getUserCode()) && TextUtils.equals(this.mDeviceCode, codePair.getDeviceCode()) && areObjectsEqual(this.mVerificationUri, codePair.getVerificationUri()) && areObjectsEqual(Integer.valueOf(this.mInterval), Integer.valueOf(codePair.getInterval())) && areObjectsEqual(this.mCreationTime, codePair.getCreationTime()) && areObjectsEqual(this.mExpirationTime, codePair.getExpirationTime()) && areObjectsEqual(this.mScopes, codePair.getScopes());
    }
}