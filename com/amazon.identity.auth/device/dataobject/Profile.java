package com.amazon.identity.auth.device.dataobject;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.device.datastore.ProfileDataSource;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.ido.life.util.DateUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class Profile extends AbstractDataObject {
    private static final int EXPIRATION_TIME = 3600000;
    protected String mAppFamilyId;
    protected String mData;
    protected Date mExpirationTime;
    private static final String LOG_TAG = Profile.class.getName();
    public static final String[] ALL_COLUMNS = {"Id", "ExpirationTime", "AppId", DatabaseHelper.profile_Data};

    public enum COL_INDEX {
        ID(0),
        EXPIRATION_TIME(1),
        APP_ID(2),
        DATA(3);

        public final int colId;

        COL_INDEX(int i) {
            this.colId = i;
        }
    }

    public Profile() {
    }

    public Profile(String str, String str2) {
        this(str, str2, new Date(Calendar.getInstance().getTime().getTime() + DateUtil.HOUR));
    }

    Profile(String str, String str2, Date date) {
        this.mAppFamilyId = str;
        this.mData = str2;
        this.mExpirationTime = date;
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public ContentValues getValuesForInsert() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ALL_COLUMNS[COL_INDEX.APP_ID.colId], this.mAppFamilyId);
        if (this.mExpirationTime != null) {
            contentValues.put(ALL_COLUMNS[COL_INDEX.EXPIRATION_TIME.colId], DatabaseHelper.getDateFormat().format(this.mExpirationTime));
        } else {
            contentValues.put(ALL_COLUMNS[COL_INDEX.EXPIRATION_TIME.colId], (String) null);
        }
        contentValues.put(ALL_COLUMNS[COL_INDEX.DATA.colId], this.mData);
        return contentValues;
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public ProfileDataSource getDataSource(Context context) {
        return ProfileDataSource.getInstance(context);
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Profile)) {
            try {
                Profile profile = (Profile) obj;
                if (TextUtils.equals(this.mAppFamilyId, profile.getAppFamilyId()) && areObjectsEqual(this.mExpirationTime, profile.getExpirationTime())) {
                    return dataEquals(profile);
                }
                return false;
            } catch (NullPointerException e2) {
                MAPLog.e(LOG_TAG, "" + e2.toString());
            }
        }
        return false;
    }

    public long getId() {
        return getRowId();
    }

    public String getAppFamilyId() {
        return this.mAppFamilyId;
    }

    public Date getExpirationTime() {
        return this.mExpirationTime;
    }

    public String getData() {
        return this.mData;
    }

    public Bundle getDataAsBundle() throws AuthError {
        return getDataFromJSON();
    }

    public void setId(long j) {
        setRowId(j);
    }

    public void setAppId(String str) {
        this.mAppFamilyId = str;
    }

    public void setExpirationTime(Date date) {
        this.mExpirationTime = DatabaseHelper.truncateFractionalSeconds(date);
    }

    public void setData(String str) {
        this.mData = str;
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public String toString() {
        return toLogString();
    }

    public String toLogString() {
        return "{ rowid=" + getId() + ", appId=" + this.mAppFamilyId + ", expirationTime=" + DatabaseHelper.getDateFormat().format(this.mExpirationTime) + ", data=" + this.mData + " }";
    }

    public boolean hasExpired() {
        Date date = this.mExpirationTime;
        if (date != null) {
            return date.before(Calendar.getInstance().getTime());
        }
        return true;
    }

    private Bundle getDataFromJSON() throws AuthError {
        Bundle bundle = new Bundle();
        String str = this.mData;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                try {
                    Iterator<String> itKeys = jSONObject.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        bundle.putString(next, jSONObject.getString(next));
                    }
                } catch (JSONException e2) {
                    MAPLog.e(LOG_TAG, "Unable to parse profile data in database " + e2.getMessage());
                }
            } catch (JSONException e3) {
                MAPLog.e(LOG_TAG, "JSONException while parsing profile information in database", e3);
                throw new AuthError("JSONException while parsing profile information in database", e3, AuthError.ERROR_TYPE.ERROR_JSON);
            }
        }
        return bundle;
    }

    private boolean dataEquals(Profile profile) {
        try {
            JSONObject jSONObject = new JSONObject(this.mData);
            JSONObject jSONObject2 = new JSONObject(profile.getData());
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (!jSONObject.getString(next).equals(jSONObject2.getString(next))) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return TextUtils.equals(this.mData, profile.getData());
        }
    }
}