package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class TimeSetDao extends AbstractDao<TimeSet, Long> {
    public static final String TABLENAME = "TIME_SET";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property TimeFormat = new Property(2, Integer.TYPE, "TimeFormat", false, "TIME_FORMAT");
        public static final Property HasUpload = new Property(3, Boolean.TYPE, "HasUpload", false, "HAS_UPLOAD");
        public static final Property HasSyncDeviceSuccess = new Property(4, Boolean.TYPE, "HasSyncDeviceSuccess", false, "HAS_SYNC_DEVICE_SUCCESS");
        public static final Property CreateTime = new Property(5, Long.TYPE, "CreateTime", false, "CREATE_TIME");
        public static final Property UpdateTime = new Property(6, Long.TYPE, "UpdateTime", false, "UPDATE_TIME");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public TimeSetDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public TimeSetDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"TIME_SET\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"TIME_FORMAT\" INTEGER NOT NULL ,\"HAS_UPLOAD\" INTEGER NOT NULL ,\"HAS_SYNC_DEVICE_SUCCESS\" INTEGER NOT NULL ,\"CREATE_TIME\" INTEGER NOT NULL ,\"UPDATE_TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"TIME_SET\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, TimeSet timeSet) {
        databaseStatement.clearBindings();
        Long id = timeSet.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, timeSet.getUserId());
        databaseStatement.bindLong(3, timeSet.getTimeFormat());
        databaseStatement.bindLong(4, timeSet.getHasUpload() ? 1L : 0L);
        databaseStatement.bindLong(5, timeSet.getHasSyncDeviceSuccess() ? 1L : 0L);
        databaseStatement.bindLong(6, timeSet.getCreateTime());
        databaseStatement.bindLong(7, timeSet.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, TimeSet timeSet) {
        sQLiteStatement.clearBindings();
        Long id = timeSet.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, timeSet.getUserId());
        sQLiteStatement.bindLong(3, timeSet.getTimeFormat());
        sQLiteStatement.bindLong(4, timeSet.getHasUpload() ? 1L : 0L);
        sQLiteStatement.bindLong(5, timeSet.getHasSyncDeviceSuccess() ? 1L : 0L);
        sQLiteStatement.bindLong(6, timeSet.getCreateTime());
        sQLiteStatement.bindLong(7, timeSet.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(TimeSet timeSet) {
        super.attachEntity(timeSet);
        timeSet.__setDaoSession(this.daoSession);
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
    public TimeSet readEntity(Cursor cursor, int i) {
        TimeSet timeSet = new TimeSet();
        readEntity(cursor, timeSet, i);
        return timeSet;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, TimeSet timeSet, int i) {
        int i2 = i + 0;
        timeSet.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        timeSet.setUserId(cursor.getLong(i + 1));
        timeSet.setTimeFormat(cursor.getInt(i + 2));
        timeSet.setHasUpload(cursor.getShort(i + 3) != 0);
        timeSet.setHasSyncDeviceSuccess(cursor.getShort(i + 4) != 0);
        timeSet.setCreateTime(cursor.getLong(i + 5));
        timeSet.setUpdateTime(cursor.getLong(i + 6));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(TimeSet timeSet, long j) {
        timeSet.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(TimeSet timeSet) {
        if (timeSet != null) {
            return timeSet.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(TimeSet timeSet) {
        return timeSet.getId() != null;
    }
}