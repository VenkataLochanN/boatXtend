package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class UnitSettingDao extends AbstractDao<UnitSetting, Long> {
    public static final String TABLENAME = "UNIT_SETTING";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property Unit = new Property(2, Integer.TYPE, "Unit", false, "UNIT");
        public static final Property HasUpload = new Property(3, Boolean.TYPE, "HasUpload", false, "HAS_UPLOAD");
        public static final Property HasSyncDeviceSuccess = new Property(4, Boolean.TYPE, "HasSyncDeviceSuccess", false, "HAS_SYNC_DEVICE_SUCCESS");
        public static final Property CreateTime = new Property(5, Long.TYPE, "CreateTime", false, "CREATE_TIME");
        public static final Property UpdateTime = new Property(6, Long.TYPE, "UpdateTime", false, "UPDATE_TIME");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public UnitSettingDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public UnitSettingDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"UNIT_SETTING\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"UNIT\" INTEGER NOT NULL ,\"HAS_UPLOAD\" INTEGER NOT NULL ,\"HAS_SYNC_DEVICE_SUCCESS\" INTEGER NOT NULL ,\"CREATE_TIME\" INTEGER NOT NULL ,\"UPDATE_TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"UNIT_SETTING\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, UnitSetting unitSetting) {
        databaseStatement.clearBindings();
        Long id = unitSetting.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, unitSetting.getUserId());
        databaseStatement.bindLong(3, unitSetting.getUnit());
        databaseStatement.bindLong(4, unitSetting.getHasUpload() ? 1L : 0L);
        databaseStatement.bindLong(5, unitSetting.getHasSyncDeviceSuccess() ? 1L : 0L);
        databaseStatement.bindLong(6, unitSetting.getCreateTime());
        databaseStatement.bindLong(7, unitSetting.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, UnitSetting unitSetting) {
        sQLiteStatement.clearBindings();
        Long id = unitSetting.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, unitSetting.getUserId());
        sQLiteStatement.bindLong(3, unitSetting.getUnit());
        sQLiteStatement.bindLong(4, unitSetting.getHasUpload() ? 1L : 0L);
        sQLiteStatement.bindLong(5, unitSetting.getHasSyncDeviceSuccess() ? 1L : 0L);
        sQLiteStatement.bindLong(6, unitSetting.getCreateTime());
        sQLiteStatement.bindLong(7, unitSetting.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(UnitSetting unitSetting) {
        super.attachEntity(unitSetting);
        unitSetting.__setDaoSession(this.daoSession);
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
    public UnitSetting readEntity(Cursor cursor, int i) {
        UnitSetting unitSetting = new UnitSetting();
        readEntity(cursor, unitSetting, i);
        return unitSetting;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, UnitSetting unitSetting, int i) {
        int i2 = i + 0;
        unitSetting.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        unitSetting.setUserId(cursor.getLong(i + 1));
        unitSetting.setUnit(cursor.getInt(i + 2));
        unitSetting.setHasUpload(cursor.getShort(i + 3) != 0);
        unitSetting.setHasSyncDeviceSuccess(cursor.getShort(i + 4) != 0);
        unitSetting.setCreateTime(cursor.getLong(i + 5));
        unitSetting.setUpdateTime(cursor.getLong(i + 6));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(UnitSetting unitSetting, long j) {
        unitSetting.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(UnitSetting unitSetting) {
        if (unitSetting != null) {
            return unitSetting.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(UnitSetting unitSetting) {
        return unitSetting.getId() != null;
    }
}