package com.amazon.identity.auth.device.dataobject;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import android.text.format.Time;
import com.amazon.identity.auth.device.datastore.AuthorizationTokenDataSource;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.map.device.AccountManagerConstants;
import com.amazon.identity.auth.map.device.token.AbstractToken;
import com.amazon.identity.auth.map.device.token.Token;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class AuthorizationToken extends AbstractDataObject implements Token {
    private static final int DEFAULT_MINIMUM_TOKEN_LIFETIME = 300;
    protected String mAppFamilyId;
    protected Date mCreationTime;
    private String mDirectedId;
    protected Date mExpirationTime;
    protected byte[] mMiscData;
    protected String mTokenValue;
    protected AUTHZ_TOKEN_TYPE mType;
    private static final String LOG_TAG = AuthorizationToken.class.getName();
    public static final String[] ALL_COLUMNS = {"Id", "AppId", DatabaseHelper.authorizationToken_Token, "CreationTime", "ExpirationTime", DatabaseHelper.authorizationToken_MiscData, "type", DatabaseHelper.authorizationToken_DirectedId};

    public enum COL_INDEX {
        ID(0),
        APP_FAMILY_ID(1),
        TOKEN(2),
        CREATION_TIME(3),
        EXPIRATION_TIME(4),
        MISC_DATA(5),
        TYPE(6),
        DIRECTED_ID(7);

        public final int colId;

        COL_INDEX(int i) {
            this.colId = i;
        }
    }

    public enum AUTHZ_TOKEN_TYPE {
        ACCESS(AccountManagerConstants.ACCESS_TOKEN_TYPE_PREFIX),
        REFRESH(AccountManagerConstants.REFRESH_TOKEN_TYPE_PREFIX);

        private final String mType;

        AUTHZ_TOKEN_TYPE(String str) {
            this.mType = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mType;
        }
    }

    public AuthorizationToken(String str, String str2, String str3, Date date, Date date2, byte[] bArr, AUTHZ_TOKEN_TYPE authz_token_type) {
        this.mAppFamilyId = str;
        this.mTokenValue = str3;
        this.mCreationTime = DatabaseHelper.truncateFractionalSeconds(date);
        this.mExpirationTime = DatabaseHelper.truncateFractionalSeconds(date2);
        this.mMiscData = bArr;
        this.mType = authz_token_type;
        this.mDirectedId = str2;
    }

    public AuthorizationToken() {
    }

    public long getId() {
        return getRowId();
    }

    public String getAppFamilyId() {
        return this.mAppFamilyId;
    }

    public String getTokenValue() {
        return this.mTokenValue;
    }

    public Date getCreationTime() {
        return this.mCreationTime;
    }

    public Date getExpirationTime() {
        return this.mExpirationTime;
    }

    public byte[] getMiscData() {
        return this.mMiscData;
    }

    public void setId(long j) {
        setRowId(j);
    }

    public void setAppFamilyId(String str) {
        this.mAppFamilyId = str;
    }

    public void setTokenValue(String str) {
        this.mTokenValue = str;
    }

    @Override // com.amazon.identity.auth.map.device.token.Token
    public String getType() {
        return this.mType.toString();
    }

    public AUTHZ_TOKEN_TYPE getTypeAsEnum() {
        return this.mType;
    }

    @Override // com.amazon.identity.auth.map.device.token.Token
    public String getDirectedId() {
        return this.mDirectedId;
    }

    public void setDirectedId(String str) {
        this.mDirectedId = str;
    }

    public void setCreationTime(Date date) {
        this.mCreationTime = DatabaseHelper.truncateFractionalSeconds(date);
    }

    public void setExpirationTime(Date date) {
        this.mExpirationTime = DatabaseHelper.truncateFractionalSeconds(date);
    }

    public void setMiscData(byte[] bArr) {
        this.mMiscData = bArr;
    }

    public boolean isRemainingLifeAcceptable() {
        return isRemainingLifeAcceptable(300);
    }

    public boolean isRemainingLifeAcceptable(int i) {
        return this.mExpirationTime.getTime() - Calendar.getInstance().getTimeInMillis() >= AbstractToken.secsToMillis((long) i);
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public ContentValues getValuesForInsert() {
        ContentValues contentValues = new ContentValues();
        SimpleDateFormat dateFormat = DatabaseHelper.getDateFormat();
        contentValues.put(ALL_COLUMNS[COL_INDEX.APP_FAMILY_ID.colId], this.mAppFamilyId);
        contentValues.put(ALL_COLUMNS[COL_INDEX.TOKEN.colId], this.mTokenValue);
        contentValues.put(ALL_COLUMNS[COL_INDEX.CREATION_TIME.colId], dateFormat.format(this.mCreationTime));
        contentValues.put(ALL_COLUMNS[COL_INDEX.EXPIRATION_TIME.colId], dateFormat.format(this.mExpirationTime));
        contentValues.put(ALL_COLUMNS[COL_INDEX.MISC_DATA.colId], this.mMiscData);
        contentValues.put(ALL_COLUMNS[COL_INDEX.TYPE.colId], Integer.valueOf(this.mType.ordinal()));
        contentValues.put(ALL_COLUMNS[COL_INDEX.DIRECTED_ID.colId], this.mDirectedId);
        return contentValues;
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public AuthorizationTokenDataSource getDataSource(Context context) {
        return AuthorizationTokenDataSource.getInstance(context);
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof AuthorizationToken)) {
            try {
                AuthorizationToken authorizationToken = (AuthorizationToken) obj;
                if (TextUtils.equals(this.mAppFamilyId, authorizationToken.getAppFamilyId()) && TextUtils.equals(this.mTokenValue, authorizationToken.getTokenValue()) && areObjectsEqual(this.mCreationTime, authorizationToken.getCreationTime()) && areObjectsEqual(this.mExpirationTime, authorizationToken.getExpirationTime()) && TextUtils.equals(getType(), authorizationToken.getType())) {
                    return TextUtils.equals(this.mDirectedId, authorizationToken.getDirectedId());
                }
                return false;
            } catch (NullPointerException e2) {
                MAPLog.e(LOG_TAG, "" + e2.toString());
            }
        }
        return false;
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public String toString() {
        return this.mTokenValue;
    }

    public String toLogString() {
        SimpleDateFormat dateFormat = DatabaseHelper.getDateFormat();
        return "{ rowid=" + getId() + ", appId=" + this.mAppFamilyId + ", token=" + this.mTokenValue + ", creationTime=" + dateFormat.format(this.mCreationTime) + ", expirationTime=" + dateFormat.format(this.mExpirationTime) + ", type=" + this.mType.toString() + ", directedId=<obscured> }";
    }

    @Override // com.amazon.identity.auth.map.device.token.Token
    public Time getLocalTimestamp() {
        Time time = new Time();
        time.set(this.mCreationTime.getTime());
        return time;
    }

    @Override // com.amazon.identity.auth.map.device.token.Token
    public Map<String, String> getData() {
        String str;
        HashMap map = new HashMap();
        try {
            str = new String(this.mMiscData, "UTF8");
        } catch (UnsupportedEncodingException unused) {
            MAPLog.i(LOG_TAG, "unable to parse misc data");
            str = null;
        }
        if (str != null) {
            String[] strArrSplit = str.split(AppInfo.DELIM);
            int length = strArrSplit.length;
            if (length % 2 == 0) {
                for (int i = 0; i < length; i += 2) {
                    map.put(strArrSplit[i], strArrSplit[i + 1]);
                }
            } else {
                MAPLog.i(LOG_TAG, "unable to parse misc data, key/value pairs do not match");
            }
        }
        return map;
    }
}