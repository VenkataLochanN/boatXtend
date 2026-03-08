package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class TempUnitSettingDao extends AbstractDao<TempUnitSetting, Long> {
    public static final String TABLENAME = "TEMP_UNIT_SETTING";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property Temp = new Property(2, Integer.TYPE, "Temp", false, "TEMP");
        public static final Property HasUpload = new Property(3, Boolean.TYPE, "HasUpload", false, "HAS_UPLOAD");
        public static final Property HasSyncDeviceSuccess = new Property(4, Boolean.TYPE, "HasSyncDeviceSuccess", false, "HAS_SYNC_DEVICE_SUCCESS");
        public static final Property CreateTime = new Property(5, Long.TYPE, "CreateTime", false, "CREATE_TIME");
        public static final Property UpdateTime = new Property(6, Long.TYPE, "UpdateTime", false, "UPDATE_TIME");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public TempUnitSettingDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public TempUnitSettingDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"TEMP_UNIT_SETTING\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"TEMP\" INTEGER NOT NULL ,\"HAS_UPLOAD\" INTEGER NOT NULL ,\"HAS_SYNC_DEVICE_SUCCESS\" INTEGER NOT NULL ,\"CREATE_TIME\" INTEGER NOT NULL ,\"UPDATE_TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"TEMP_UNIT_SETTING\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, TempUnitSetting tempUnitSetting) {
        databaseStatement.clearBindings();
        Long id = tempUnitSetting.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, tempUnitSetting.getUserId());
        databaseStatement.bindLong(3, tempUnitSetting.getTemp());
        databaseStatement.bindLong(4, tempUnitSetting.getHasUpload() ? 1L : 0L);
        databaseStatement.bindLong(5, tempUnitSetting.getHasSyncDeviceSuccess() ? 1L : 0L);
        databaseStatement.bindLong(6, tempUnitSetting.getCreateTime());
        databaseStatement.bindLong(7, tempUnitSetting.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, TempUnitSetting tempUnitSetting) {
        sQLiteStatement.clearBindings();
        Long id = tempUnitSetting.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, tempUnitSetting.getUserId());
        sQLiteStatement.bindLong(3, tempUnitSetting.getTemp());
        sQLiteStatement.bindLong(4, tempUnitSetting.getHasUpload() ? 1L : 0L);
        sQLiteStatement.bindLong(5, tempUnitSetting.getHasSyncDeviceSuccess() ? 1L : 0L);
        sQLiteStatement.bindLong(6, tempUnitSetting.getCreateTime());
        sQLiteStatement.bindLong(7, tempUnitSetting.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(TempUnitSetting tempUnitSetting) {
        super.attachEntity(tempUnitSetting);
        tempUnitSetting.__setDaoSession(this.daoSession);
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
    public TempUnitSetting readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        return new TempUnitSetting(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.getLong(i + 1), cursor.getInt(i + 2), cursor.getShort(i + 3) != 0, cursor.getShort(i + 4) != 0, cursor.getLong(i + 5), cursor.getLong(i + 6));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, TempUnitSetting tempUnitSetting, int i) {
        int i2 = i + 0;
        tempUnitSetting.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        tempUnitSetting.setUserId(cursor.getLong(i + 1));
        tempUnitSetting.setTemp(cursor.getInt(i + 2));
        tempUnitSetting.setHasUpload(cursor.getShort(i + 3) != 0);
        tempUnitSetting.setHasSyncDeviceSuccess(cursor.getShort(i + 4) != 0);
        tempUnitSetting.setCreateTime(cursor.getLong(i + 5));
        tempUnitSetting.setUpdateTime(cursor.getLong(i + 6));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(TempUnitSetting tempUnitSetting, long j) {
        tempUnitSetting.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(TempUnitSetting tempUnitSetting) {
        if (tempUnitSetting != null) {
            return tempUnitSetting.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(TempUnitSetting tempUnitSetting) {
        return tempUnitSetting.getId() != null;
    }
}