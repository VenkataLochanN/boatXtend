package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class ServerMultiMonthBloodOxyTotalDataDao extends AbstractDao<ServerMultiMonthBloodOxyTotalData, Long> {
    public static final String TABLENAME = "SERVER_MULTI_MONTH_BLOOD_OXY_TOTAL_DATA";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "userId", false, "USER_ID");
        public static final Property MinValue = new Property(2, Integer.TYPE, "minValue", false, "MIN_VALUE");
        public static final Property MaxValue = new Property(3, Integer.TYPE, "maxValue", false, "MAX_VALUE");
        public static final Property AvgValue = new Property(4, Integer.TYPE, "avgValue", false, "AVG_VALUE");
        public static final Property TotalMeasurementTimes = new Property(5, Integer.TYPE, "totalMeasurementTimes", false, "TOTAL_MEASUREMENT_TIMES");
        public static final Property StartDate = new Property(6, String.class, "startDate", false, "START_DATE");
        public static final Property EndDate = new Property(7, String.class, "endDate", false, "END_DATE");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public ServerMultiMonthBloodOxyTotalDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ServerMultiMonthBloodOxyTotalDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"SERVER_MULTI_MONTH_BLOOD_OXY_TOTAL_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"MIN_VALUE\" INTEGER NOT NULL ,\"MAX_VALUE\" INTEGER NOT NULL ,\"AVG_VALUE\" INTEGER NOT NULL ,\"TOTAL_MEASUREMENT_TIMES\" INTEGER NOT NULL ,\"START_DATE\" TEXT,\"END_DATE\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"SERVER_MULTI_MONTH_BLOOD_OXY_TOTAL_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ServerMultiMonthBloodOxyTotalData serverMultiMonthBloodOxyTotalData) {
        databaseStatement.clearBindings();
        Long id = serverMultiMonthBloodOxyTotalData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, serverMultiMonthBloodOxyTotalData.getUserId());
        databaseStatement.bindLong(3, serverMultiMonthBloodOxyTotalData.getMinValue());
        databaseStatement.bindLong(4, serverMultiMonthBloodOxyTotalData.getMaxValue());
        databaseStatement.bindLong(5, serverMultiMonthBloodOxyTotalData.getAvgValue());
        databaseStatement.bindLong(6, serverMultiMonthBloodOxyTotalData.getTotalMeasurementTimes());
        String startDate = serverMultiMonthBloodOxyTotalData.getStartDate();
        if (startDate != null) {
            databaseStatement.bindString(7, startDate);
        }
        String endDate = serverMultiMonthBloodOxyTotalData.getEndDate();
        if (endDate != null) {
            databaseStatement.bindString(8, endDate);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ServerMultiMonthBloodOxyTotalData serverMultiMonthBloodOxyTotalData) {
        sQLiteStatement.clearBindings();
        Long id = serverMultiMonthBloodOxyTotalData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, serverMultiMonthBloodOxyTotalData.getUserId());
        sQLiteStatement.bindLong(3, serverMultiMonthBloodOxyTotalData.getMinValue());
        sQLiteStatement.bindLong(4, serverMultiMonthBloodOxyTotalData.getMaxValue());
        sQLiteStatement.bindLong(5, serverMultiMonthBloodOxyTotalData.getAvgValue());
        sQLiteStatement.bindLong(6, serverMultiMonthBloodOxyTotalData.getTotalMeasurementTimes());
        String startDate = serverMultiMonthBloodOxyTotalData.getStartDate();
        if (startDate != null) {
            sQLiteStatement.bindString(7, startDate);
        }
        String endDate = serverMultiMonthBloodOxyTotalData.getEndDate();
        if (endDate != null) {
            sQLiteStatement.bindString(8, endDate);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(ServerMultiMonthBloodOxyTotalData serverMultiMonthBloodOxyTotalData) {
        super.attachEntity(serverMultiMonthBloodOxyTotalData);
        serverMultiMonthBloodOxyTotalData.__setDaoSession(this.daoSession);
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
    public ServerMultiMonthBloodOxyTotalData readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = cursor.getInt(i + 2);
        int i4 = cursor.getInt(i + 3);
        int i5 = cursor.getInt(i + 4);
        int i6 = cursor.getInt(i + 5);
        int i7 = i + 6;
        int i8 = i + 7;
        return new ServerMultiMonthBloodOxyTotalData(lValueOf, j, i3, i4, i5, i6, cursor.isNull(i7) ? null : cursor.getString(i7), cursor.isNull(i8) ? null : cursor.getString(i8));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ServerMultiMonthBloodOxyTotalData serverMultiMonthBloodOxyTotalData, int i) {
        int i2 = i + 0;
        serverMultiMonthBloodOxyTotalData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        serverMultiMonthBloodOxyTotalData.setUserId(cursor.getLong(i + 1));
        serverMultiMonthBloodOxyTotalData.setMinValue(cursor.getInt(i + 2));
        serverMultiMonthBloodOxyTotalData.setMaxValue(cursor.getInt(i + 3));
        serverMultiMonthBloodOxyTotalData.setAvgValue(cursor.getInt(i + 4));
        serverMultiMonthBloodOxyTotalData.setTotalMeasurementTimes(cursor.getInt(i + 5));
        int i3 = i + 6;
        serverMultiMonthBloodOxyTotalData.setStartDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 7;
        serverMultiMonthBloodOxyTotalData.setEndDate(cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(ServerMultiMonthBloodOxyTotalData serverMultiMonthBloodOxyTotalData, long j) {
        serverMultiMonthBloodOxyTotalData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(ServerMultiMonthBloodOxyTotalData serverMultiMonthBloodOxyTotalData) {
        if (serverMultiMonthBloodOxyTotalData != null) {
            return serverMultiMonthBloodOxyTotalData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ServerMultiMonthBloodOxyTotalData serverMultiMonthBloodOxyTotalData) {
        return serverMultiMonthBloodOxyTotalData.getId() != null;
    }
}