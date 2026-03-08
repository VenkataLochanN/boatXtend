package com.amazon.identity.auth.device.datastore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.authorization.AmazonAuthorizationServiceInterface;
import com.amazon.identity.auth.device.authorization.ThirdPartyServiceHelper;
import com.amazon.identity.auth.device.dataobject.RequestedScope;
import com.amazon.identity.auth.device.utils.LWAServiceWrapper;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATE_FORMAT_LOCAL = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_UTC = "yyyy-MM-dd HH:mm:ss'Z'";
    public static final String MAP_DB_NAME = "MAPDataStore.db";
    public static final int MAP_DB_VERSION = 9;
    private static final int THIRD_PARTY_VER_2_1_X = 5;
    private static final int THIRD_PARTY_VER_2_2_X = 6;
    private static final int THIRD_PARTY_VER_2_3_1 = 8;
    private static final int THIRD_PARTY_VER_2_3_X = 7;
    private static final int THIRD_PARTY_VER_2_4 = 9;
    private static final int THIRD_PARTY_VER_2_X = 4;
    public static final String appInfoTable = "AppInfo";
    public static final String appInfo_AllowedScopes = "AllowedScopes";
    public static final String appInfo_AppFamilyId = "AppFamilyId";
    public static final String appInfo_AppVariantId = "AppVariantId";
    public static final String appInfo_AuthzHost = "AuthzHost";
    public static final String appInfo_ClientId = "ClientId";
    public static final String appInfo_ExchangeHost = "ExchangeHost";
    public static final String appInfo_GrantedPermissions = "GrantedPermissions";
    public static final String appInfo_PackageName = "PackageName";
    public static final String appInfo_Payload = "Payload";
    public static final String authorizationCode = "Code";
    public static final String authorizationCodeTable = "AuthorizationCode";
    public static final String authorizationCode_AppId = "AppId";
    public static final String authorizationCode_AuthorizationTokenId = "AuthorizationTokenId";
    public static final String authorizationCode_Id = "Id";
    public static final String authorizationTokenTable = "AuthorizationToken";
    public static final String authorizationToken_AppFamilyId = "AppId";
    public static final String authorizationToken_CreationTime = "CreationTime";
    public static final String authorizationToken_DirectedId = "directedId";
    public static final String authorizationToken_ExpirationTime = "ExpirationTime";
    public static final String authorizationToken_Id = "Id";
    public static final String authorizationToken_MiscData = "MiscData";
    public static final String authorizationToken_Token = "Token";
    public static final String authorizationToken_Type = "type";
    public static final String codePairTable = "CodePair";
    public static final String codePair_AppId = "AppId";
    public static final String codePair_CreationTime = "CreationTime";
    public static final String codePair_DeviceCode = "DeviceCode";
    public static final String codePair_ExpirationTime = "ExpirationTime";
    public static final String codePair_Id = "Id";
    public static final String codePair_Interval = "Interval";
    public static final String codePair_Scopes = "Scopes";
    public static final String codePair_UserCode = "UserCode";
    public static final String codePair_VerificationUri = "VerificationUri";
    public static final String profileTable = "Profile";
    public static final String profile_AppId = "AppId";
    public static final String profile_Data = "Data";
    public static final String profile_ExpirationTime = "ExpirationTime";
    public static final String profile_Id = "Id";
    public static final String requestedScopeTable = "RequestedScope";
    public static final String requestedScope_AppFamilyId = "AppId";
    public static final String requestedScope_AuthorizationAccessTokenId = "AtzAccessTokenId";
    public static final String requestedScope_AuthorizationRefreshTokenId = "AtzRefreshTokenId";
    public static final String requestedScope_DirectedId = "DirectedId";
    public static final String requestedScope_Scope = "Scope";
    private static final String LOG_TAG = DatabaseHelper.class.getName();
    private static final long SECOND_MILLIS = TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS);

    public DatabaseHelper(Context context) {
        super(context, MAP_DB_NAME, (SQLiteDatabase.CursorFactory) null, 9);
        MAPLog.pii(LOG_TAG, "DatabaseHelper created ver=9", "MAP_DB_NAME=MAPDataStore.db");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        MAPLog.i(LOG_TAG, "onCreate called");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS AppInfo (AppFamilyId TEXT NOT NULL, PackageName TEXT NOT NULL, AllowedScopes TEXT, GrantedPermissions TEXT, ClientId TEXT, AppVariantId TEXT,AuthzHost TEXT, ExchangeHost TEXT, Payload TEXT,UNIQUE (PackageName), PRIMARY KEY (AppVariantId))");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS app_info_index_pkg_name ON AppInfo (PackageName)");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS app_info_index_app_variant_id ON AppInfo (AppVariantId)");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS RequestedScope (Scope TEXT NOT NULL, AppId TEXT NOT NULL, DirectedId TEXT, AtzAccessTokenId INTEGER NOT NULL, AtzRefreshTokenId INTEGER NOT NULL, PRIMARY KEY (Scope,AppId,AtzAccessTokenId,AtzRefreshTokenId))");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS requested_scope_index_scope ON RequestedScope (Scope)");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS requested_scope_index_app_id ON RequestedScope (AppId)");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS requested_scope_index_atz_access_token_id ON RequestedScope (AtzAccessTokenId)");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS requested_scope_index_directed_id ON RequestedScope (DirectedId)");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS requested_scope_index_atz_refresh_token_id ON RequestedScope (AtzRefreshTokenId)");
        sQLiteDatabase.execSQL("CREATE TRIGGER IF NOT EXISTS requested_scope_valid_atz_access_token_id BEFORE INSERT ON RequestedScope FOR EACH ROW BEGIN SELECT CASE WHEN (new.AtzAccessTokenId != " + RequestedScope.OUTCOME.UNKNOWN.longVal + " AND new." + requestedScope_AuthorizationAccessTokenId + " != " + RequestedScope.OUTCOME.REJECTED.longVal + " AND new." + requestedScope_AuthorizationAccessTokenId + " < " + RequestedScope.OUTCOME.GRANTED_LOCALLY.longVal + ") THEN RAISE(ABORT, 'Invalid authorization token ID') END; END;");
        sQLiteDatabase.execSQL("CREATE TRIGGER IF NOT EXISTS requested_scope_valid_atz_refresh_token_id BEFORE INSERT ON RequestedScope FOR EACH ROW BEGIN SELECT CASE WHEN (new.AtzRefreshTokenId != " + RequestedScope.OUTCOME.UNKNOWN.longVal + " AND new." + requestedScope_AuthorizationRefreshTokenId + " != " + RequestedScope.OUTCOME.REJECTED.longVal + " AND new." + requestedScope_AuthorizationRefreshTokenId + " < " + RequestedScope.OUTCOME.GRANTED_LOCALLY.longVal + ") THEN RAISE(ABORT, 'Invalid authorization token ID') END; END;");
        MAPLog.d(LOG_TAG, "Attempting to create authorizationTokenTable TABLE");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS AuthorizationToken (Id INTEGER PRIMARY KEY AUTOINCREMENT, AppId TEXT NOT NULL, Token TEXT NOT NULL, CreationTime DATETIME NOT NULL, ExpirationTime DATETIME NOT NULL, MiscData BLOB, type INTEGER NOT NULL, directedId TEXT )");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS authz_token_index_app_id ON AuthorizationToken (AppId)");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS authz_token_index_directed_id ON AuthorizationToken (directedId)");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS AuthorizationCode (Id INTEGER PRIMARY KEY AUTOINCREMENT, Code TEXT NOT NULL, AppId TEXT NOT NULL, AuthorizationTokenId INTEGER NOT NULL )");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS authz_code_index_app_id ON AuthorizationCode (AppId)");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS authz_code_index_token_id ON AuthorizationCode (AuthorizationTokenId)");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Profile (Id INTEGER PRIMARY KEY AUTOINCREMENT, ExpirationTime DATETIME NOT NULL, AppId TEXT NOT NULL, Data TEXT NOT NULL )");
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS profile_index_app_id ON Profile (AppId)");
        sQLiteDatabase.compileStatement("CREATE TABLE IF NOT EXISTS CodePair (Id INTEGER PRIMARY KEY AUTOINCREMENT, UserCode TEXT NOT NULL, DeviceCode TEXT NOT NULL, VerificationUri TEXT NOT NULL, Interval INTEGER NOT NULL, CreationTime DATETIME NOT NULL, ExpirationTime DATETIME NOT NULL, AppId TEXT NOT NULL, Scopes TEXT NOT NULL )").execute();
    }

    public boolean doesColumnExist(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        MAPLog.i(LOG_TAG, "Checking if COL=" + str2 + " in table=" + str + " exists");
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            MAPLog.i(LOG_TAG, "Column does NOT exist");
            return false;
        }
        Cursor cursor = null;
        try {
            try {
                Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select * from sqlite_master where name = '" + str + "' and sql like '%" + str2 + "%' ", null);
                if (cursorRawQuery == null) {
                    MAPLog.i(LOG_TAG, "Column does NOT exist");
                    cursorRawQuery.close();
                    return false;
                }
                if (cursorRawQuery.moveToFirst()) {
                    MAPLog.i(LOG_TAG, "Column does exist");
                    cursorRawQuery.close();
                    return true;
                }
                cursorRawQuery.close();
                MAPLog.i(LOG_TAG, "Column does NOT exist");
                return false;
            } catch (SQLiteException e2) {
                MAPLog.i(LOG_TAG, "SQLiteException: " + e2.getMessage());
                MAPLog.i(LOG_TAG, "Column does NOT exist");
                cursor.close();
                return false;
            }
        } catch (Throwable th) {
            cursor.close();
            throw th;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        MAPLog.i(LOG_TAG, "onUpgrade called old=" + i + " new=" + i2);
        if (i < 4 && i2 >= 4) {
            MAPLog.i(LOG_TAG, "Doing upgrades for 4");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS AuthorizationToken");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS RequestedScope");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS AppInfo");
            sQLiteDatabase.execSQL("DROP INDEX IF EXISTS RequestedScope.requested_scope_index_directed_id");
            sQLiteDatabase.execSQL("DROP INDEX IF EXISTS RequestedScope.requested_scope_index_atz_token_id");
            sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS requested_scope_valid_atz_token_id");
        }
        if (i < 5 && i2 >= 5) {
            MAPLog.i(LOG_TAG, "Doing upgrades for 5");
        }
        if (i < 6 && i2 >= 6) {
            MAPLog.i(LOG_TAG, "Doing upgrades for 6");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS AppInfo");
        }
        if ((i < 8 && i2 >= 8) || (i < 9 && i2 >= 9)) {
            MAPLog.i(LOG_TAG, "Doing upgrades for 8");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS AppInfo");
        }
        if (i2 > 9) {
            throw new IllegalStateException("Database version was updated, but no upgrade was done ver=8");
        }
        onCreate(sQLiteDatabase);
    }

    public static Date truncateFractionalSeconds(Date date) {
        if (date == null) {
            return null;
        }
        long time = date.getTime();
        long j = SECOND_MILLIS;
        date.setTime((time / j) * j);
        return date;
    }

    public static void clearAuthorizationState(Context context) {
        MAPLog.i(LOG_TAG, "Clearing Authorization Locally");
        AppInfoDataSource.getInstance(context).deleteAllRows();
        AuthorizationTokenDataSource.getInstance(context).deleteAllRows();
        RequestedScopeDataSource.getInstance(context).deleteAllRows();
        ProfileDataSource.getInstance(context).deleteAllRows();
        CodePairDataSource.getInstance(context).deleteAllRows();
    }

    public static void clearServiceAuthorizationState(Context context) throws AuthError {
        clearServiceAuthorizationState(context, new ThirdPartyServiceHelper());
    }

    public static void clearServiceAuthorizationState(Context context, ThirdPartyServiceHelper thirdPartyServiceHelper) throws AuthError {
        new LWAServiceWrapper<Bundle>() { // from class: com.amazon.identity.auth.device.datastore.DatabaseHelper.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.utils.LWAServiceWrapper
            public Bundle doWork(Context context2, AmazonAuthorizationServiceInterface amazonAuthorizationServiceInterface) throws AuthError, RemoteException {
                DatabaseHelper.clearServiceAuthorizationStateWorker(context2, amazonAuthorizationServiceInterface);
                return new Bundle();
            }
        }.execute(context, thirdPartyServiceHelper);
    }

    public static SimpleDateFormat getDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_UTC, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    public static SimpleDateFormat getDateFormatLocal() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    }

    public static Date parseDate(String str) throws ParseException {
        if (str.endsWith("Z")) {
            return getDateFormat().parse(str);
        }
        return getDateFormatLocal().parse(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void clearServiceAuthorizationStateWorker(Context context, AmazonAuthorizationServiceInterface amazonAuthorizationServiceInterface) throws AuthError, RemoteException {
        MAPLog.i(LOG_TAG, "Clearing Authorization via Service");
        Bundle bundleClearAuthorizationState = amazonAuthorizationServiceInterface.clearAuthorizationState(null, context.getPackageName());
        if (bundleClearAuthorizationState != null && bundleClearAuthorizationState.containsKey(AuthError.AUTH_ERROR_EXECEPTION)) {
            throw AuthError.extractError(bundleClearAuthorizationState);
        }
    }
}