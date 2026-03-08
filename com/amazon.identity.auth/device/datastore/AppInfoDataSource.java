package com.amazon.identity.auth.device.datastore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.utils.MAPUtils;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AppInfoDataSource extends AbstractDataSource<AppInfo> {
    private static AppInfoDataSource INSTANCE;
    private static final String LOG_TAG = AppInfoDataSource.class.getName();
    private static final String[] ALL_COLUMNS = AppInfo.ALL_COLUMNS;

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public String getTableName() {
        return DatabaseHelper.appInfoTable;
    }

    private AppInfoDataSource(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    public static synchronized AppInfoDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new AppInfoDataSource(MAPUtils.getMAPdatabase(context));
        }
        return INSTANCE;
    }

    public static void resetInstance() {
        INSTANCE = null;
        MAPUtils.resetDatabaseInstance();
    }

    public List<AppInfo> findByAppFamilyId(String str) {
        return findAllRowsBySingleColumn(ALL_COLUMNS[AppInfo.COL_INDEX.APP_FAMILY_ID.colId], str);
    }

    public AppInfo findByAppVariantId(String str) {
        return findOneRowBySingleColumn(ALL_COLUMNS[AppInfo.COL_INDEX.APP_VARIANT_ID.colId], str);
    }

    public AppInfo findByPackageName(String str) {
        return findOneRowBySingleColumn(ALL_COLUMNS[AppInfo.COL_INDEX.PACKAGE_NAME.colId], str);
    }

    public AppInfo findByPrimaryKey(String str) {
        return findByAppVariantId(str);
    }

    public int deleteByAppFamilyId(String str) {
        return deleteRowsBySingleColumn(ALL_COLUMNS[AppInfo.COL_INDEX.APP_FAMILY_ID.colId], str);
    }

    public int deleteByPackageName(String str) {
        return deleteRowsBySingleColumn(ALL_COLUMNS[AppInfo.COL_INDEX.PACKAGE_NAME.colId], str);
    }

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public String getLogTag() {
        return LOG_TAG;
    }

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public AppInfo cursorToObject(Cursor cursor) {
        if (cursor != null && cursor.getCount() != 0) {
            try {
                AppInfo appInfo = new AppInfo();
                appInfo.setRowId(cursor.getLong(getColumnIndex(cursor, AppInfo.COL_INDEX.ROW_ID.colId)));
                appInfo.setAppFamilyId(cursor.getString(getColumnIndex(cursor, AppInfo.COL_INDEX.APP_FAMILY_ID.colId)));
                appInfo.setAppVariantId(cursor.getString(getColumnIndex(cursor, AppInfo.COL_INDEX.APP_VARIANT_ID.colId)));
                appInfo.setPackageName(cursor.getString(getColumnIndex(cursor, AppInfo.COL_INDEX.PACKAGE_NAME.colId)));
                appInfo.setAllowedScopes(MAPUtils.toStringArray(cursor.getString(getColumnIndex(cursor, AppInfo.COL_INDEX.ALLOWED_SCOPES.colId)), AppInfo.DELIM));
                appInfo.setGrantedPermissions(MAPUtils.toStringArray(cursor.getString(getColumnIndex(cursor, AppInfo.COL_INDEX.GRANTED_PERMISSIONS.colId)), AppInfo.DELIM));
                appInfo.setClientId(cursor.getString(getColumnIndex(cursor, AppInfo.COL_INDEX.CLIENT_ID.colId)));
                appInfo.setAuthorizationHost(cursor.getString(getColumnIndex(cursor, AppInfo.COL_INDEX.AUTHZ_HOST.colId)));
                appInfo.setExchangeHost(cursor.getString(getColumnIndex(cursor, AppInfo.COL_INDEX.EXCHANGE_HOST.colId)));
                appInfo.setPayload(cursor.getString(getColumnIndex(cursor, AppInfo.COL_INDEX.PAYLOAD.colId)));
                return appInfo;
            } catch (Exception e2) {
                MAPLog.e(LOG_TAG, "" + e2.getMessage(), e2);
            }
        }
        return null;
    }

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public String[] getAllColumns() {
        return ALL_COLUMNS;
    }
}