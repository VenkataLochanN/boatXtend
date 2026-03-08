package com.amazon.identity.auth.device.datastore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.identity.auth.device.dataobject.RequestedScope;
import com.amazon.identity.auth.device.utils.MAPUtils;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class RequestedScopeDataSource extends AbstractDataSource<RequestedScope> {
    private static RequestedScopeDataSource INSTANCE;
    private static final String LOG_TAG = RequestedScopeDataSource.class.getName();
    private static final String[] ALL_COLUMNS = RequestedScope.ALL_COLUMNS;

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public String getTableName() {
        return DatabaseHelper.requestedScopeTable;
    }

    private RequestedScopeDataSource(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    public static synchronized RequestedScopeDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RequestedScopeDataSource(MAPUtils.getMAPdatabase(context));
        }
        return INSTANCE;
    }

    public static void resetInstance() {
        INSTANCE = null;
        MAPUtils.resetDatabaseInstance();
    }

    public List<RequestedScope> findByScope(String str) {
        return findAllRowsBySingleColumn(ALL_COLUMNS[RequestedScope.COL_INDEX.SCOPE.colId], str);
    }

    public List<RequestedScope> findByAppFamilyId(String str) {
        return findAllRowsBySingleColumn(ALL_COLUMNS[RequestedScope.COL_INDEX.APP_FAMILY_ID.colId], str);
    }

    public List<RequestedScope> findByDirectedId(String str) {
        return findAllRowsBySingleColumn(ALL_COLUMNS[RequestedScope.COL_INDEX.DIRECTED_ID.colId], str);
    }

    public List<RequestedScope> findByAuthorizationAccessTokenId(long j) {
        return findAllRowsBySingleColumn(ALL_COLUMNS[RequestedScope.COL_INDEX.AUTHORIZATION_ACCESS_TOKEN_ID.colId], "" + j);
    }

    public List<RequestedScope> findByAuthorizationRefreshTokenId(long j) {
        return findAllRowsBySingleColumn(ALL_COLUMNS[RequestedScope.COL_INDEX.AUTHORIZATION_REFRESH_TOKEN_ID.colId], "" + j);
    }

    public RequestedScope findByPrimaryKey(String str, String str2, String str3) {
        return findOneRow(new String[]{ALL_COLUMNS[RequestedScope.COL_INDEX.SCOPE.colId], ALL_COLUMNS[RequestedScope.COL_INDEX.APP_FAMILY_ID.colId], ALL_COLUMNS[RequestedScope.COL_INDEX.DIRECTED_ID.colId]}, new String[]{str, str2, str3});
    }

    public int deleteByScope(String str) {
        return deleteRowsBySingleColumn(ALL_COLUMNS[RequestedScope.COL_INDEX.SCOPE.colId], str);
    }

    public int deleteByAppFamilyId(String str) {
        return deleteRowsBySingleColumn(ALL_COLUMNS[RequestedScope.COL_INDEX.APP_FAMILY_ID.colId], str);
    }

    public int deleteByDirectedId(String str) {
        return deleteRowsBySingleColumn(ALL_COLUMNS[RequestedScope.COL_INDEX.DIRECTED_ID.colId], str);
    }

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public String getLogTag() {
        return LOG_TAG;
    }

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public RequestedScope cursorToObject(Cursor cursor) {
        if (cursor != null && cursor.getCount() != 0) {
            try {
                RequestedScope requestedScope = new RequestedScope();
                requestedScope.setRowId(cursor.getLong(getColumnIndex(cursor, RequestedScope.COL_INDEX.ROW_ID.colId)));
                requestedScope.setScopeValue(cursor.getString(getColumnIndex(cursor, RequestedScope.COL_INDEX.SCOPE.colId)));
                requestedScope.setAppFamilyId(cursor.getString(getColumnIndex(cursor, RequestedScope.COL_INDEX.APP_FAMILY_ID.colId)));
                requestedScope.setDirectedId(cursor.getString(getColumnIndex(cursor, RequestedScope.COL_INDEX.DIRECTED_ID.colId)));
                requestedScope.setAuthorizationAccessTokenId(cursor.getLong(getColumnIndex(cursor, RequestedScope.COL_INDEX.AUTHORIZATION_ACCESS_TOKEN_ID.colId)));
                requestedScope.setAuthorizationRefreshTokenId(cursor.getLong(getColumnIndex(cursor, RequestedScope.COL_INDEX.AUTHORIZATION_REFRESH_TOKEN_ID.colId)));
                return requestedScope;
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