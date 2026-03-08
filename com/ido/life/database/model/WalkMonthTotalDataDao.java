package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class WalkMonthTotalDataDao extends AbstractDao<WalkMonthTotalData, Long> {
    public static final String TABLENAME = "WALK_MONTH_TOTAL_DATA";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "userId", false, "USER_ID");
        public static final Property AvgDayHour = new Property(2, Float.TYPE, "avgDayHour", false, "AVG_DAY_HOUR");
        public static final Property Month = new Property(3, String.class, "month", false, "MONTH");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public WalkMonthTotalDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public WalkMonthTotalDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"WALK_MONTH_TOTAL_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"AVG_DAY_HOUR\" REAL NOT NULL ,\"MONTH\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"WALK_MONTH_TOTAL_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, WalkMonthTotalData walkMonthTotalData) {
        databaseStatement.clearBindings();
        Long id = walkMonthTotalData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, walkMonthTotalData.getUserId());
        databaseStatement.bindDouble(3, walkMonthTotalData.getAvgDayHour());
        String month = walkMonthTotalData.getMonth();
        if (month != null) {
            databaseStatement.bindString(4, month);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, WalkMonthTotalData walkMonthTotalData) {
        sQLiteStatement.clearBindings();
        Long id = walkMonthTotalData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, walkMonthTotalData.getUserId());
        sQLiteStatement.bindDouble(3, walkMonthTotalData.getAvgDayHour());
        String month = walkMonthTotalData.getMonth();
        if (month != null) {
            sQLiteStatement.bindString(4, month);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(WalkMonthTotalData walkMonthTotalData) {
        super.attachEntity(walkMonthTotalData);
        walkMonthTotalData.__setDaoSession(this.daoSession);
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
    public WalkMonthTotalData readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        float f2 = cursor.getFloat(i + 2);
        int i3 = i + 3;
        return new WalkMonthTotalData(lValueOf, j, f2, cursor.isNull(i3) ? null : cursor.getString(i3));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, WalkMonthTotalData walkMonthTotalData, int i) {
        int i2 = i + 0;
        walkMonthTotalData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        walkMonthTotalData.setUserId(cursor.getLong(i + 1));
        walkMonthTotalData.setAvgDayHour(cursor.getFloat(i + 2));
        int i3 = i + 3;
        walkMonthTotalData.setMonth(cursor.isNull(i3) ? null : cursor.getString(i3));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(WalkMonthTotalData walkMonthTotalData, long j) {
        walkMonthTotalData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(WalkMonthTotalData walkMonthTotalData) {
        if (walkMonthTotalData != null) {
            return walkMonthTotalData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(WalkMonthTotalData walkMonthTotalData) {
        return walkMonthTotalData.getId() != null;
    }
}