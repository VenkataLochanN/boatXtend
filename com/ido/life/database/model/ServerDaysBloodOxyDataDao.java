package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class ServerDaysBloodOxyDataDao extends AbstractDao<ServerDaysBloodOxyData, Long> {
    public static final String TABLENAME = "SERVER_DAYS_BLOOD_OXY_DATA";
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

    public ServerDaysBloodOxyDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ServerDaysBloodOxyDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"SERVER_DAYS_BLOOD_OXY_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"MIN_VALUE\" INTEGER NOT NULL ,\"MAX_VALUE\" INTEGER NOT NULL ,\"AVG_VALUE\" INTEGER NOT NULL ,\"TOTAL_MEASUREMENT_TIMES\" INTEGER NOT NULL ,\"START_DATE\" TEXT,\"END_DATE\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"SERVER_DAYS_BLOOD_OXY_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ServerDaysBloodOxyData serverDaysBloodOxyData) {
        databaseStatement.clearBindings();
        Long id = serverDaysBloodOxyData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, serverDaysBloodOxyData.getUserId());
        databaseStatement.bindLong(3, serverDaysBloodOxyData.getMinValue());
        databaseStatement.bindLong(4, serverDaysBloodOxyData.getMaxValue());
        databaseStatement.bindLong(5, serverDaysBloodOxyData.getAvgValue());
        databaseStatement.bindLong(6, serverDaysBloodOxyData.getTotalMeasurementTimes());
        String startDate = serverDaysBloodOxyData.getStartDate();
        if (startDate != null) {
            databaseStatement.bindString(7, startDate);
        }
        String endDate = serverDaysBloodOxyData.getEndDate();
        if (endDate != null) {
            databaseStatement.bindString(8, endDate);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ServerDaysBloodOxyData serverDaysBloodOxyData) {
        sQLiteStatement.clearBindings();
        Long id = serverDaysBloodOxyData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, serverDaysBloodOxyData.getUserId());
        sQLiteStatement.bindLong(3, serverDaysBloodOxyData.getMinValue());
        sQLiteStatement.bindLong(4, serverDaysBloodOxyData.getMaxValue());
        sQLiteStatement.bindLong(5, serverDaysBloodOxyData.getAvgValue());
        sQLiteStatement.bindLong(6, serverDaysBloodOxyData.getTotalMeasurementTimes());
        String startDate = serverDaysBloodOxyData.getStartDate();
        if (startDate != null) {
            sQLiteStatement.bindString(7, startDate);
        }
        String endDate = serverDaysBloodOxyData.getEndDate();
        if (endDate != null) {
            sQLiteStatement.bindString(8, endDate);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(ServerDaysBloodOxyData serverDaysBloodOxyData) {
        super.attachEntity(serverDaysBloodOxyData);
        serverDaysBloodOxyData.__setDaoSession(this.daoSession);
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
    public ServerDaysBloodOxyData readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = cursor.getInt(i + 2);
        int i4 = cursor.getInt(i + 3);
        int i5 = cursor.getInt(i + 4);
        int i6 = cursor.getInt(i + 5);
        int i7 = i + 6;
        int i8 = i + 7;
        return new ServerDaysBloodOxyData(lValueOf, j, i3, i4, i5, i6, cursor.isNull(i7) ? null : cursor.getString(i7), cursor.isNull(i8) ? null : cursor.getString(i8));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ServerDaysBloodOxyData serverDaysBloodOxyData, int i) {
        int i2 = i + 0;
        serverDaysBloodOxyData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        serverDaysBloodOxyData.setUserId(cursor.getLong(i + 1));
        serverDaysBloodOxyData.setMinValue(cursor.getInt(i + 2));
        serverDaysBloodOxyData.setMaxValue(cursor.getInt(i + 3));
        serverDaysBloodOxyData.setAvgValue(cursor.getInt(i + 4));
        serverDaysBloodOxyData.setTotalMeasurementTimes(cursor.getInt(i + 5));
        int i3 = i + 6;
        serverDaysBloodOxyData.setStartDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 7;
        serverDaysBloodOxyData.setEndDate(cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(ServerDaysBloodOxyData serverDaysBloodOxyData, long j) {
        serverDaysBloodOxyData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(ServerDaysBloodOxyData serverDaysBloodOxyData) {
        if (serverDaysBloodOxyData != null) {
            return serverDaysBloodOxyData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ServerDaysBloodOxyData serverDaysBloodOxyData) {
        return serverDaysBloodOxyData.getId() != null;
    }
}