package com.amazon.identity.auth.device.datastore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.identity.auth.device.dataobject.CodePair;
import com.amazon.identity.auth.device.utils.MAPUtils;
import com.amazon.identity.auth.device.utils.ScopeUtils;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.net.URI;

/* JADX INFO: loaded from: classes.dex */
public class CodePairDataSource extends AbstractDataSource<CodePair> {
    private static CodePairDataSource INSTANCE;
    private static final String LOG_TAG = CodePairDataSource.class.getName();
    private static final String[] ALL_COLUMNS = CodePair.ALL_COLUMNS;

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public String getTableName() {
        return DatabaseHelper.codePairTable;
    }

    public CodePairDataSource(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    public static synchronized CodePairDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CodePairDataSource(MAPUtils.getMAPdatabase(context));
        }
        return INSTANCE;
    }

    public static void resetInstance() {
        INSTANCE = null;
        MAPUtils.resetDatabaseInstance();
    }

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public String getLogTag() {
        return LOG_TAG;
    }

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public CodePair cursorToObject(Cursor cursor) {
        if (cursor != null && cursor.getCount() != 0) {
            try {
                CodePair codePair = new CodePair(cursor.getString(getColumnIndex(cursor, CodePair.COL_INDEX.APP_ID.colId)), cursor.getString(getColumnIndex(cursor, CodePair.COL_INDEX.USER_CODE.colId)), cursor.getString(getColumnIndex(cursor, CodePair.COL_INDEX.DEVICE_CODE.colId)), new URI(cursor.getString(getColumnIndex(cursor, CodePair.COL_INDEX.VERIFICATION_URI.colId))), cursor.getInt(getColumnIndex(cursor, CodePair.COL_INDEX.INTERVAL.colId)), DatabaseHelper.parseDate(cursor.getString(getColumnIndex(cursor, CodePair.COL_INDEX.CREATION_TIME.colId))), DatabaseHelper.parseDate(cursor.getString(getColumnIndex(cursor, CodePair.COL_INDEX.EXPIRATION_TIME.colId))), ScopeUtils.convertScopeStringToArray(cursor.getString(getColumnIndex(cursor, CodePair.COL_INDEX.SCOPES.colId))));
                codePair.setRowId(cursor.getLong(getColumnIndex(cursor, CodePair.COL_INDEX.ID.colId)));
                return codePair;
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