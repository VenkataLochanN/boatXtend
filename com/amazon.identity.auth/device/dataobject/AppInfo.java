package com.amazon.identity.auth.device.dataobject;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.datastore.AppInfoDataSource;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.device.utils.MAPUtils;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AppInfo extends AbstractDataObject {
    public static final String DELIM = ",";
    private String[] allowedScopes;
    private String appFamilyId;
    private String appVariantId;
    private String clientId;
    private String[] grantedPermissions;
    private String mAuthzHost;
    private String mExchangeHost;
    private String packageName;
    private JSONObject payload;
    private static final String LOG_TAG = AppInfo.class.getName();
    public static final String[] ALL_COLUMNS = {"rowid", DatabaseHelper.appInfo_AppFamilyId, DatabaseHelper.appInfo_PackageName, DatabaseHelper.appInfo_AllowedScopes, DatabaseHelper.appInfo_GrantedPermissions, DatabaseHelper.appInfo_ClientId, DatabaseHelper.appInfo_AppVariantId, DatabaseHelper.appInfo_AuthzHost, DatabaseHelper.appInfo_ExchangeHost, DatabaseHelper.appInfo_Payload};

    public enum COL_INDEX {
        ROW_ID(0),
        APP_FAMILY_ID(1),
        PACKAGE_NAME(2),
        ALLOWED_SCOPES(3),
        GRANTED_PERMISSIONS(4),
        CLIENT_ID(5),
        APP_VARIANT_ID(6),
        AUTHZ_HOST(7),
        EXCHANGE_HOST(8),
        PAYLOAD(9);

        public final int colId;

        COL_INDEX(int i) {
            this.colId = i;
        }
    }

    public AppInfo(String str, String str2, String str3, String[] strArr, String[] strArr2, String str4, String str5, String str6, JSONObject jSONObject) {
        this.appFamilyId = str;
        this.appVariantId = str2;
        this.packageName = str3;
        this.allowedScopes = strArr;
        this.grantedPermissions = strArr2;
        this.clientId = str4;
        this.payload = jSONObject;
        this.mAuthzHost = str5;
        this.mExchangeHost = str6;
    }

    private AppInfo(long j, String str, String str2, String str3, String[] strArr, String[] strArr2, String str4, String str5, String str6, JSONObject jSONObject) {
        this(str, str2, str3, strArr, strArr2, str4, str5, str6, jSONObject);
        setRowId(j);
    }

    public AppInfo() {
    }

    public String getAppFamilyId() {
        return this.appFamilyId;
    }

    public String getAppVariantId() {
        return this.appVariantId;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String[] getAllowedScopes() {
        return this.allowedScopes;
    }

    public String[] getGrantedPermissions() {
        return this.grantedPermissions;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getAuthorizationHost() {
        return this.mAuthzHost;
    }

    public String getExchangeHost() {
        return this.mExchangeHost;
    }

    private JSONObject getPayload() {
        return this.payload;
    }

    public void setAppFamilyId(String str) {
        this.appFamilyId = str;
    }

    public void setAppVariantId(String str) {
        this.appVariantId = str;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setAllowedScopes(String[] strArr) {
        this.allowedScopes = strArr;
    }

    public void setGrantedPermissions(String[] strArr) {
        this.grantedPermissions = strArr;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public void setAuthorizationHost(String str) {
        this.mAuthzHost = str;
    }

    public void setExchangeHost(String str) {
        this.mExchangeHost = str;
    }

    public void setPayload(JSONObject jSONObject) {
        this.payload = jSONObject;
    }

    public void setPayload(String str) {
        try {
            this.payload = new JSONObject(str);
        } catch (JSONException e2) {
            MAPLog.e(LOG_TAG, "Payload String not correct JSON.  Setting payload to null", e2);
        }
    }

    public String[] getAllowedRemoteScopes() {
        if (this.allowedScopes == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : this.allowedScopes) {
            if (!Scope.isLocal(str)) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public String getAttributeByKey(String str) {
        try {
            return this.payload.getString(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    public String getVersion() {
        String attributeByKey = getAttributeByKey("ver");
        return attributeByKey != null ? attributeByKey : "1";
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public ContentValues getValuesForInsert() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ALL_COLUMNS[COL_INDEX.APP_FAMILY_ID.colId], this.appFamilyId);
        contentValues.put(ALL_COLUMNS[COL_INDEX.PACKAGE_NAME.colId], this.packageName);
        contentValues.put(ALL_COLUMNS[COL_INDEX.ALLOWED_SCOPES.colId], MAPUtils.toDelimitedString(this.allowedScopes, DELIM));
        contentValues.put(ALL_COLUMNS[COL_INDEX.GRANTED_PERMISSIONS.colId], MAPUtils.toDelimitedString(this.grantedPermissions, DELIM));
        contentValues.put(ALL_COLUMNS[COL_INDEX.CLIENT_ID.colId], this.clientId);
        contentValues.put(ALL_COLUMNS[COL_INDEX.APP_VARIANT_ID.colId], this.appVariantId);
        contentValues.put(ALL_COLUMNS[COL_INDEX.AUTHZ_HOST.colId], this.mAuthzHost);
        contentValues.put(ALL_COLUMNS[COL_INDEX.EXCHANGE_HOST.colId], this.mExchangeHost);
        String str = ALL_COLUMNS[COL_INDEX.PAYLOAD.colId];
        JSONObject jSONObject = this.payload;
        contentValues.put(str, jSONObject != null ? jSONObject.toString() : null);
        return contentValues;
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public AppInfoDataSource getDataSource(Context context) {
        return AppInfoDataSource.getInstance(context);
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public boolean equals(Object obj) {
        if (!(obj instanceof AppInfo)) {
            return false;
        }
        AppInfo appInfo = (AppInfo) obj;
        return TextUtils.equals(this.appFamilyId, appInfo.getAppFamilyId()) && TextUtils.equals(this.appVariantId, appInfo.getAppVariantId()) && TextUtils.equals(this.packageName, appInfo.getPackageName()) && Arrays.equals(this.allowedScopes, appInfo.getAllowedScopes()) && Arrays.equals(this.grantedPermissions, appInfo.getGrantedPermissions()) && TextUtils.equals(this.clientId, appInfo.getClientId()) && TextUtils.equals(this.mAuthzHost, appInfo.getAuthorizationHost()) && TextUtils.equals(this.mExchangeHost, appInfo.getExchangeHost()) && payloadEquals(appInfo);
    }

    private boolean payloadEquals(AppInfo appInfo) {
        JSONObject payload = appInfo.getPayload();
        JSONObject jSONObject = this.payload;
        if (jSONObject == null) {
            return payload == null;
        }
        if (payload == null) {
            return false;
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                if (!this.payload.getString(next).equals(payload.getString(next))) {
                    MAPLog.e(LOG_TAG, "APIKeys not equal: key " + next + " not equal");
                    return false;
                }
            } catch (ClassCastException e2) {
                MAPLog.e(LOG_TAG, "APIKeys not equal: ClassCastExceptionException", e2);
                return false;
            } catch (JSONException e3) {
                MAPLog.e(LOG_TAG, "APIKeys not equal: JSONException", e3);
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public AppInfo m12clone() {
        return new AppInfo(getRowId(), this.appFamilyId, this.appVariantId, this.packageName, this.allowedScopes, this.grantedPermissions, this.clientId, this.mAuthzHost, this.mExchangeHost, this.payload);
    }

    @Override // com.amazon.identity.auth.device.dataobject.AbstractDataObject
    public String toString() {
        try {
            return this.payload.toString(4);
        } catch (Exception unused) {
            return "{ rowid=" + getRowId() + ", appFamilyId=" + this.appFamilyId + ", appVariantId=" + this.appVariantId + ", packageName=" + this.packageName + ", allowedScopes=" + Arrays.toString(this.allowedScopes) + ", grantedPermissions=" + Arrays.toString(this.grantedPermissions) + ", clientId=" + this.clientId + ", AuthzHost=" + this.mAuthzHost + ", ExchangeHost=" + this.mExchangeHost + " }";
        }
    }
}