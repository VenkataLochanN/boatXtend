package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class ServerBloodOxyMonthDataDao extends AbstractDao<ServerBloodOxyMonthData, Long> {
    public static final String TABLENAME = "SERVER_BLOOD_OXY_MONTH_DATA";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "userId", false, "USER_ID");
        public static final Property MinValue = new Property(2, Integer.TYPE, "minValue", false, "MIN_VALUE");
        public static final Property MaxValue = new Property(3, Integer.TYPE, "maxValue", false, "MAX_VALUE");
        public static final Property AvgValue = new Property(4, Integer.TYPE, "avgValue", false, "AVG_VALUE");
        public static final Property TotalMeasurementTimes = new Property(5, Integer.TYPE, "totalMeasurementTimes", false, "TOTAL_MEASUREMENT_TIMES");
        public static final Property Month = new Property(6, String.class, "month", false, "MONTH");
        public static final Property Days = new Property(7, Integer.TYPE, "days", false, "DAYS");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public ServerBloodOxyMonthDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ServerBloodOxyMonthDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"SERVER_BLOOD_OXY_MONTH_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"MIN_VALUE\" INTEGER NOT NULL ,\"MAX_VALUE\" INTEGER NOT NULL ,\"AVG_VALUE\" INTEGER NOT NULL ,\"TOTAL_MEASUREMENT_TIMES\" INTEGER NOT NULL ,\"MONTH\" TEXT,\"DAYS\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"SERVER_BLOOD_OXY_MONTH_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ServerBloodOxyMonthData serverBloodOxyMonthData) {
        databaseStatement.clearBindings();
        Long id = serverBloodOxyMonthData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, serverBloodOxyMonthData.getUserId());
        databaseStatement.bindLong(3, serverBloodOxyMonthData.getMinValue());
        databaseStatement.bindLong(4, serverBloodOxyMonthData.getMaxValue());
        databaseStatement.bindLong(5, serverBloodOxyMonthData.getAvgValue());
        databaseStatement.bindLong(6, serverBloodOxyMonthData.getTotalMeasurementTimes());
        String month = serverBloodOxyMonthData.getMonth();
        if (month != null) {
            databaseStatement.bindString(7, month);
        }
        databaseStatement.bindLong(8, serverBloodOxyMonthData.getDays());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ServerBloodOxyMonthData serverBloodOxyMonthData) {
        sQLiteStatement.clearBindings();
        Long id = serverBloodOxyMonthData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, serverBloodOxyMonthData.getUserId());
        sQLiteStatement.bindLong(3, serverBloodOxyMonthData.getMinValue());
        sQLiteStatement.bindLong(4, serverBloodOxyMonthData.getMaxValue());
        sQLiteStatement.bindLong(5, serverBloodOxyMonthData.getAvgValue());
        sQLiteStatement.bindLong(6, serverBloodOxyMonthData.getTotalMeasurementTimes());
        String month = serverBloodOxyMonthData.getMonth();
        if (month != null) {
            sQLiteStatement.bindString(7, month);
        }
        sQLiteStatement.bindLong(8, serverBloodOxyMonthData.getDays());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(ServerBloodOxyMonthData serverBloodOxyMonthData) {
        super.attachEntity(serverBloodOxyMonthData);
        serverBloodOxyMonthData.__setDaoSession(this.daoSession);
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
    public ServerBloodOxyMonthData readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 6;
        return new ServerBloodOxyMonthData(lValueOf, cursor.getLong(i + 1), cursor.getInt(i + 2), cursor.getInt(i + 3), cursor.getInt(i + 4), cursor.getInt(i + 5), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.getInt(i + 7));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ServerBloodOxyMonthData serverBloodOxyMonthData, int i) {
        int i2 = i + 0;
        serverBloodOxyMonthData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        serverBloodOxyMonthData.setUserId(cursor.getLong(i + 1));
        serverBloodOxyMonthData.setMinValue(cursor.getInt(i + 2));
        serverBloodOxyMonthData.setMaxValue(cursor.getInt(i + 3));
        serverBloodOxyMonthData.setAvgValue(cursor.getInt(i + 4));
        serverBloodOxyMonthData.setTotalMeasurementTimes(cursor.getInt(i + 5));
        int i3 = i + 6;
        serverBloodOxyMonthData.setMonth(cursor.isNull(i3) ? null : cursor.getString(i3));
        serverBloodOxyMonthData.setDays(cursor.getInt(i + 7));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(ServerBloodOxyMonthData serverBloodOxyMonthData, long j) {
        serverBloodOxyMonthData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(ServerBloodOxyMonthData serverBloodOxyMonthData) {
        if (serverBloodOxyMonthData != null) {
            return serverBloodOxyMonthData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ServerBloodOxyMonthData serverBloodOxyMonthData) {
        return serverBloodOxyMonthData.getId() != null;
    }
}