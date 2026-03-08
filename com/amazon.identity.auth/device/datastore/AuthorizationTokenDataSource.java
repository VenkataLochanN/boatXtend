package com.amazon.identity.auth.device.datastore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.identity.auth.device.dataobject.AuthorizationToken;
import com.amazon.identity.auth.device.dataobject.AuthorizationTokenFactory;
import com.amazon.identity.auth.device.utils.MAPUtils;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AuthorizationTokenDataSource extends AbstractDataSource<AuthorizationToken> {
    private static AuthorizationTokenDataSource INSTANCE;
    private static final String LOG_TAG = AuthorizationTokenDataSource.class.getName();
    private static final String[] ALL_COLUMNS = AuthorizationToken.ALL_COLUMNS;

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public String getTableName() {
        return DatabaseHelper.authorizationTokenTable;
    }

    private AuthorizationTokenDataSource(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    public static synchronized AuthorizationTokenDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new AuthorizationTokenDataSource(MAPUtils.getMAPdatabase(context));
        }
        return INSTANCE;
    }

    public static void resetInstance() {
        INSTANCE = null;
        MAPUtils.resetDatabaseInstance();
    }

    public List<AuthorizationToken> findByAppFamilyId(String str) {
        return findAllRowsBySingleColumn(ALL_COLUMNS[AuthorizationToken.COL_INDEX.APP_FAMILY_ID.colId], str);
    }

    public AuthorizationToken findById(long j) {
        return findByRowId(j);
    }

    public AuthorizationToken findByPrimaryKey(long j) {
        return findByRowId(j);
    }

    public List<AuthorizationToken> findByDirectedId(String str) {
        return findAllRowsBySingleColumn(ALL_COLUMNS[AuthorizationToken.COL_INDEX.DIRECTED_ID.colId], str);
    }

    public int deleteByAppFamilyId(String str) {
        return deleteRowsBySingleColumn(ALL_COLUMNS[AuthorizationToken.COL_INDEX.APP_FAMILY_ID.colId], str);
    }

    public int deleteByDirectedId(String str) {
        return deleteRowsBySingleColumn(ALL_COLUMNS[AuthorizationToken.COL_INDEX.DIRECTED_ID.colId], str);
    }

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public String getLogTag() {
        return LOG_TAG;
    }

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public AuthorizationToken cursorToObject(Cursor cursor) {
        if (cursor != null && cursor.getCount() != 0) {
            try {
                AuthorizationToken authorizationToken = AuthorizationTokenFactory.getAuthorizationToken(AuthorizationToken.AUTHZ_TOKEN_TYPE.values()[cursor.getInt(getColumnIndex(cursor, AuthorizationToken.COL_INDEX.TYPE.colId))]);
                authorizationToken.setId(cursor.getLong(getColumnIndex(cursor, AuthorizationToken.COL_INDEX.ID.colId)));
                authorizationToken.setAppFamilyId(cursor.getString(getColumnIndex(cursor, AuthorizationToken.COL_INDEX.APP_FAMILY_ID.colId)));
                authorizationToken.setTokenValue(cursor.getString(getColumnIndex(cursor, AuthorizationToken.COL_INDEX.TOKEN.colId)));
                authorizationToken.setCreationTime(DatabaseHelper.parseDate(cursor.getString(getColumnIndex(cursor, AuthorizationToken.COL_INDEX.CREATION_TIME.colId))));
                authorizationToken.setExpirationTime(DatabaseHelper.parseDate(cursor.getString(getColumnIndex(cursor, AuthorizationToken.COL_INDEX.EXPIRATION_TIME.colId))));
                authorizationToken.setMiscData(cursor.getBlob(getColumnIndex(cursor, AuthorizationToken.COL_INDEX.MISC_DATA.colId)));
                authorizationToken.setDirectedId(cursor.getString(getColumnIndex(cursor, AuthorizationToken.COL_INDEX.DIRECTED_ID.colId)));
                return authorizationToken;
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