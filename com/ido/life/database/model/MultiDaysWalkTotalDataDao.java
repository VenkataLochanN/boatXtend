package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class MultiDaysWalkTotalDataDao extends AbstractDao<MultiDaysWalkTotalData, Long> {
    public static final String TABLENAME = "MULTI_DAYS_WALK_TOTAL_DATA";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "userId", false, "USER_ID");
        public static final Property TotalHour = new Property(2, Integer.TYPE, "totalHour", false, "TOTAL_HOUR");
        public static final Property AvgHour = new Property(3, Float.TYPE, "avgHour", false, "AVG_HOUR");
        public static final Property StartDate = new Property(4, String.class, "startDate", false, "START_DATE");
        public static final Property EndDate = new Property(5, String.class, "endDate", false, "END_DATE");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public MultiDaysWalkTotalDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public MultiDaysWalkTotalDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"MULTI_DAYS_WALK_TOTAL_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"TOTAL_HOUR\" INTEGER NOT NULL ,\"AVG_HOUR\" REAL NOT NULL ,\"START_DATE\" TEXT,\"END_DATE\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"MULTI_DAYS_WALK_TOTAL_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, MultiDaysWalkTotalData multiDaysWalkTotalData) {
        databaseStatement.clearBindings();
        Long id = multiDaysWalkTotalData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, multiDaysWalkTotalData.getUserId());
        databaseStatement.bindLong(3, multiDaysWalkTotalData.getTotalHour());
        databaseStatement.bindDouble(4, multiDaysWalkTotalData.getAvgHour());
        String startDate = multiDaysWalkTotalData.getStartDate();
        if (startDate != null) {
            databaseStatement.bindString(5, startDate);
        }
        String endDate = multiDaysWalkTotalData.getEndDate();
        if (endDate != null) {
            databaseStatement.bindString(6, endDate);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, MultiDaysWalkTotalData multiDaysWalkTotalData) {
        sQLiteStatement.clearBindings();
        Long id = multiDaysWalkTotalData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, multiDaysWalkTotalData.getUserId());
        sQLiteStatement.bindLong(3, multiDaysWalkTotalData.getTotalHour());
        sQLiteStatement.bindDouble(4, multiDaysWalkTotalData.getAvgHour());
        String startDate = multiDaysWalkTotalData.getStartDate();
        if (startDate != null) {
            sQLiteStatement.bindString(5, startDate);
        }
        String endDate = multiDaysWalkTotalData.getEndDate();
        if (endDate != null) {
            sQLiteStatement.bindString(6, endDate);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(MultiDaysWalkTotalData multiDaysWalkTotalData) {
        super.attachEntity(multiDaysWalkTotalData);
        multiDaysWalkTotalData.__setDaoSession(this.daoSession);
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
    public MultiDaysWalkTotalData readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = cursor.getInt(i + 2);
        float f2 = cursor.getFloat(i + 3);
        int i4 = i + 4;
        int i5 = i + 5;
        return new MultiDaysWalkTotalData(lValueOf, j, i3, f2, cursor.isNull(i4) ? null : cursor.getString(i4), cursor.isNull(i5) ? null : cursor.getString(i5));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, MultiDaysWalkTotalData multiDaysWalkTotalData, int i) {
        int i2 = i + 0;
        multiDaysWalkTotalData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        multiDaysWalkTotalData.setUserId(cursor.getLong(i + 1));
        multiDaysWalkTotalData.setTotalHour(cursor.getInt(i + 2));
        multiDaysWalkTotalData.setAvgHour(cursor.getFloat(i + 3));
        int i3 = i + 4;
        multiDaysWalkTotalData.setStartDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 5;
        multiDaysWalkTotalData.setEndDate(cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(MultiDaysWalkTotalData multiDaysWalkTotalData, long j) {
        multiDaysWalkTotalData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(MultiDaysWalkTotalData multiDaysWalkTotalData) {
        if (multiDaysWalkTotalData != null) {
            return multiDaysWalkTotalData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(MultiDaysWalkTotalData multiDaysWalkTotalData) {
        return multiDaysWalkTotalData.getId() != null;
    }
}