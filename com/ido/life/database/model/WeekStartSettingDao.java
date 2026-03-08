package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.ido.life.bean.SavePrivateSafeSettingBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class WeekStartSettingDao extends AbstractDao<WeekStartSetting, Long> {
    public static final String TABLENAME = "WEEK_START_SETTING";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property WeekStart = new Property(2, Integer.TYPE, "WeekStart", false, SavePrivateSafeSettingBean.WEEK_START);
        public static final Property HasUpload = new Property(3, Boolean.TYPE, "HasUpload", false, "HAS_UPLOAD");
        public static final Property CreateTime = new Property(4, Long.TYPE, "CreateTime", false, "CREATE_TIME");
        public static final Property UpdateTime = new Property(5, Long.TYPE, "UpdateTime", false, "UPDATE_TIME");
        public static final Property HasSyncDeviceSuccess = new Property(6, Boolean.TYPE, "HasSyncDeviceSuccess", false, "HAS_SYNC_DEVICE_SUCCESS");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public WeekStartSettingDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public WeekStartSettingDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"WEEK_START_SETTING\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"WEEK_START\" INTEGER NOT NULL ,\"HAS_UPLOAD\" INTEGER NOT NULL ,\"CREATE_TIME\" INTEGER NOT NULL ,\"UPDATE_TIME\" INTEGER NOT NULL ,\"HAS_SYNC_DEVICE_SUCCESS\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"WEEK_START_SETTING\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, WeekStartSetting weekStartSetting) {
        databaseStatement.clearBindings();
        Long id = weekStartSetting.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, weekStartSetting.getUserId());
        databaseStatement.bindLong(3, weekStartSetting.getWeekStart());
        databaseStatement.bindLong(4, weekStartSetting.getHasUpload() ? 1L : 0L);
        databaseStatement.bindLong(5, weekStartSetting.getCreateTime());
        databaseStatement.bindLong(6, weekStartSetting.getUpdateTime());
        databaseStatement.bindLong(7, weekStartSetting.getHasSyncDeviceSuccess() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, WeekStartSetting weekStartSetting) {
        sQLiteStatement.clearBindings();
        Long id = weekStartSetting.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, weekStartSetting.getUserId());
        sQLiteStatement.bindLong(3, weekStartSetting.getWeekStart());
        sQLiteStatement.bindLong(4, weekStartSetting.getHasUpload() ? 1L : 0L);
        sQLiteStatement.bindLong(5, weekStartSetting.getCreateTime());
        sQLiteStatement.bindLong(6, weekStartSetting.getUpdateTime());
        sQLiteStatement.bindLong(7, weekStartSetting.getHasSyncDeviceSuccess() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(WeekStartSetting weekStartSetting) {
        super.attachEntity(weekStartSetting);
        weekStartSetting.__setDaoSession(this.daoSession);
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
    public WeekStartSetting readEntity(Cursor cursor, int i) {
        WeekStartSetting weekStartSetting = new WeekStartSetting();
        readEntity(cursor, weekStartSetting, i);
        return weekStartSetting;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, WeekStartSetting weekStartSetting, int i) {
        int i2 = i + 0;
        weekStartSetting.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        weekStartSetting.setUserId(cursor.getLong(i + 1));
        weekStartSetting.setWeekStart(cursor.getInt(i + 2));
        weekStartSetting.setHasUpload(cursor.getShort(i + 3) != 0);
        weekStartSetting.setCreateTime(cursor.getLong(i + 4));
        weekStartSetting.setUpdateTime(cursor.getLong(i + 5));
        weekStartSetting.setHasSyncDeviceSuccess(cursor.getShort(i + 6) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(WeekStartSetting weekStartSetting, long j) {
        weekStartSetting.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(WeekStartSetting weekStartSetting) {
        if (weekStartSetting != null) {
            return weekStartSetting.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(WeekStartSetting weekStartSetting) {
        return weekStartSetting.getId() != null;
    }
}