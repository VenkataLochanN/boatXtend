package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class PrivateSafeSettingDao extends AbstractDao<PrivateSafeSetting, Long> {
    public static final String TABLENAME = "PRIVATE_SAFE_SETTING";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property SavePrivateData = new Property(2, Boolean.TYPE, "SavePrivateData", false, "SAVE_PRIVATE_DATA");
        public static final Property SaveSportData = new Property(3, Boolean.TYPE, "SaveSportData", false, "SAVE_SPORT_DATA");
        public static final Property SaveHealthData = new Property(4, Boolean.TYPE, "SaveHealthData", false, "SAVE_HEALTH_DATA");
        public static final Property SaveToGoogleFit = new Property(5, Boolean.TYPE, "SaveToGoogleFit", false, "SAVE_TO_GOOGLE_FIT");
        public static final Property SaveToStrava = new Property(6, Boolean.TYPE, "SaveToStrava", false, "SAVE_TO_STRAVA");
        public static final Property UploadSuccess = new Property(7, Boolean.TYPE, "UploadSuccess", false, "UPLOAD_SUCCESS");
        public static final Property CreateTime = new Property(8, Long.TYPE, "CreateTime", false, "CREATE_TIME");
        public static final Property UpdateTime = new Property(9, Long.TYPE, "UpdateTime", false, "UPDATE_TIME");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public PrivateSafeSettingDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public PrivateSafeSettingDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"PRIVATE_SAFE_SETTING\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"SAVE_PRIVATE_DATA\" INTEGER NOT NULL ,\"SAVE_SPORT_DATA\" INTEGER NOT NULL ,\"SAVE_HEALTH_DATA\" INTEGER NOT NULL ,\"SAVE_TO_GOOGLE_FIT\" INTEGER NOT NULL ,\"SAVE_TO_STRAVA\" INTEGER NOT NULL ,\"UPLOAD_SUCCESS\" INTEGER NOT NULL ,\"CREATE_TIME\" INTEGER NOT NULL ,\"UPDATE_TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"PRIVATE_SAFE_SETTING\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, PrivateSafeSetting privateSafeSetting) {
        databaseStatement.clearBindings();
        Long id = privateSafeSetting.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, privateSafeSetting.getUserId());
        databaseStatement.bindLong(3, privateSafeSetting.getSavePrivateData() ? 1L : 0L);
        databaseStatement.bindLong(4, privateSafeSetting.getSaveSportData() ? 1L : 0L);
        databaseStatement.bindLong(5, privateSafeSetting.getSaveHealthData() ? 1L : 0L);
        databaseStatement.bindLong(6, privateSafeSetting.getSaveToGoogleFit() ? 1L : 0L);
        databaseStatement.bindLong(7, privateSafeSetting.getSaveToStrava() ? 1L : 0L);
        databaseStatement.bindLong(8, privateSafeSetting.getUploadSuccess() ? 1L : 0L);
        databaseStatement.bindLong(9, privateSafeSetting.getCreateTime());
        databaseStatement.bindLong(10, privateSafeSetting.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, PrivateSafeSetting privateSafeSetting) {
        sQLiteStatement.clearBindings();
        Long id = privateSafeSetting.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, privateSafeSetting.getUserId());
        sQLiteStatement.bindLong(3, privateSafeSetting.getSavePrivateData() ? 1L : 0L);
        sQLiteStatement.bindLong(4, privateSafeSetting.getSaveSportData() ? 1L : 0L);
        sQLiteStatement.bindLong(5, privateSafeSetting.getSaveHealthData() ? 1L : 0L);
        sQLiteStatement.bindLong(6, privateSafeSetting.getSaveToGoogleFit() ? 1L : 0L);
        sQLiteStatement.bindLong(7, privateSafeSetting.getSaveToStrava() ? 1L : 0L);
        sQLiteStatement.bindLong(8, privateSafeSetting.getUploadSuccess() ? 1L : 0L);
        sQLiteStatement.bindLong(9, privateSafeSetting.getCreateTime());
        sQLiteStatement.bindLong(10, privateSafeSetting.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(PrivateSafeSetting privateSafeSetting) {
        super.attachEntity(privateSafeSetting);
        privateSafeSetting.__setDaoSession(this.daoSession);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public PrivateSafeSetting readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        return new PrivateSafeSetting(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.getLong(i + 1), cursor.getShort(i + 2) != 0, cursor.getShort(i + 3) != 0, cursor.getShort(i + 4) != 0, cursor.getShort(i + 5) != 0, cursor.getShort(i + 6) != 0, cursor.getShort(i + 7) != 0, cursor.getLong(i + 8), cursor.getLong(i + 9));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, PrivateSafeSetting privateSafeSetting, int i) {
        int i2 = i + 0;
        privateSafeSetting.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        privateSafeSetting.setUserId(cursor.getLong(i + 1));
        privateSafeSetting.setSavePrivateData(cursor.getShort(i + 2) != 0);
        privateSafeSetting.setSaveSportData(cursor.getShort(i + 3) != 0);
        privateSafeSetting.setSaveHealthData(cursor.getShort(i + 4) != 0);
        privateSafeSetting.setSaveToGoogleFit(cursor.getShort(i + 5) != 0);
        privateSafeSetting.setSaveToStrava(cursor.getShort(i + 6) != 0);
        privateSafeSetting.setUploadSuccess(cursor.getShort(i + 7) != 0);
        privateSafeSetting.setCreateTime(cursor.getLong(i + 8));
        privateSafeSetting.setUpdateTime(cursor.getLong(i + 9));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(PrivateSafeSetting privateSafeSetting, long j) {
        privateSafeSetting.setId(j);
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(PrivateSafeSetting privateSafeSetting) {
        if (privateSafeSetting != null) {
            return privateSafeSetting.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(PrivateSafeSetting privateSafeSetting) {
        return privateSafeSetting.getId() != null;
    }
}