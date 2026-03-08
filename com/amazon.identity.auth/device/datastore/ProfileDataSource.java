package com.amazon.identity.auth.device.datastore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.identity.auth.device.dataobject.Profile;
import com.amazon.identity.auth.device.utils.MAPUtils;
import com.amazon.identity.auth.map.device.utils.MAPLog;

/* JADX INFO: loaded from: classes.dex */
public class ProfileDataSource extends AbstractDataSource<Profile> {
    private static ProfileDataSource INSTANCE;
    private static final String LOG_TAG = ProfileDataSource.class.getName();
    private static final String[] ALL_COLUMNS = Profile.ALL_COLUMNS;

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public String getTableName() {
        return DatabaseHelper.profileTable;
    }

    public ProfileDataSource(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    public static synchronized ProfileDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ProfileDataSource(MAPUtils.getMAPdatabase(context));
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
    public String[] getAllColumns() {
        return ALL_COLUMNS;
    }

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public Profile cursorToObject(Cursor cursor) {
        if (cursor != null && cursor.getCount() != 0) {
            try {
                Profile profile = new Profile();
                profile.setId(cursor.getLong(getColumnIndex(cursor, Profile.COL_INDEX.ID.colId)));
                profile.setAppId(cursor.getString(getColumnIndex(cursor, Profile.COL_INDEX.APP_ID.colId)));
                profile.setExpirationTime(DatabaseHelper.parseDate(cursor.getString(getColumnIndex(cursor, Profile.COL_INDEX.EXPIRATION_TIME.colId))));
                profile.setData(cursor.getString(getColumnIndex(cursor, Profile.COL_INDEX.DATA.colId)));
                return profile;
            } catch (Exception e2) {
                MAPLog.e(LOG_TAG, "" + e2.getMessage(), e2);
            }
        }
        return null;
    }

    public Profile getProfile(String str) {
        return findOneRowBySingleColumn("AppId", str);
    }
}