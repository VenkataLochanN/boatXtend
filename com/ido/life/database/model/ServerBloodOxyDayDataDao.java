package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class ServerBloodOxyDayDataDao extends AbstractDao<ServerBloodOxyDayData, Long> {
    public static final String TABLENAME = "SERVER_BLOOD_OXY_DAY_DATA";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "userId", false, "USER_ID");
        public static final Property Date = new Property(2, String.class, "date", false, "DATE");
        public static final Property MaxValue = new Property(3, Integer.TYPE, "maxValue", false, "MAX_VALUE");
        public static final Property MinValue = new Property(4, Integer.TYPE, "minValue", false, "MIN_VALUE");
        public static final Property AvgValue = new Property(5, Integer.TYPE, "avgValue", false, "AVG_VALUE");
        public static final Property LatestValue = new Property(6, Integer.TYPE, "latestValue", false, "LATEST_VALUE");
        public static final Property MeasurementTimes = new Property(7, Integer.TYPE, "measurementTimes", false, "MEASUREMENT_TIMES");
        public static final Property SourceMac = new Property(8, String.class, "sourceMac", false, "SOURCE_MAC");
        public static final Property Items = new Property(9, String.class, "items", false, "ITEMS");
        public static final Property Timestamp = new Property(10, Long.TYPE, "timestamp", false, "TIMESTAMP");
        public static final Property Uploaded = new Property(11, Boolean.TYPE, "uploaded", false, "UPLOADED");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public ServerBloodOxyDayDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ServerBloodOxyDayDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"SERVER_BLOOD_OXY_DAY_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"DATE\" TEXT,\"MAX_VALUE\" INTEGER NOT NULL ,\"MIN_VALUE\" INTEGER NOT NULL ,\"AVG_VALUE\" INTEGER NOT NULL ,\"LATEST_VALUE\" INTEGER NOT NULL ,\"MEASUREMENT_TIMES\" INTEGER NOT NULL ,\"SOURCE_MAC\" TEXT,\"ITEMS\" TEXT,\"TIMESTAMP\" INTEGER NOT NULL ,\"UPLOADED\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"SERVER_BLOOD_OXY_DAY_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ServerBloodOxyDayData serverBloodOxyDayData) {
        databaseStatement.clearBindings();
        Long id = serverBloodOxyDayData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, serverBloodOxyDayData.getUserId());
        String date = serverBloodOxyDayData.getDate();
        if (date != null) {
            databaseStatement.bindString(3, date);
        }
        databaseStatement.bindLong(4, serverBloodOxyDayData.getMaxValue());
        databaseStatement.bindLong(5, serverBloodOxyDayData.getMinValue());
        databaseStatement.bindLong(6, serverBloodOxyDayData.getAvgValue());
        databaseStatement.bindLong(7, serverBloodOxyDayData.getLatestValue());
        databaseStatement.bindLong(8, serverBloodOxyDayData.getMeasurementTimes());
        String sourceMac = serverBloodOxyDayData.getSourceMac();
        if (sourceMac != null) {
            databaseStatement.bindString(9, sourceMac);
        }
        String items = serverBloodOxyDayData.getItems();
        if (items != null) {
            databaseStatement.bindString(10, items);
        }
        databaseStatement.bindLong(11, serverBloodOxyDayData.getTimestamp());
        databaseStatement.bindLong(12, serverBloodOxyDayData.getUploaded() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ServerBloodOxyDayData serverBloodOxyDayData) {
        sQLiteStatement.clearBindings();
        Long id = serverBloodOxyDayData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, serverBloodOxyDayData.getUserId());
        String date = serverBloodOxyDayData.getDate();
        if (date != null) {
            sQLiteStatement.bindString(3, date);
        }
        sQLiteStatement.bindLong(4, serverBloodOxyDayData.getMaxValue());
        sQLiteStatement.bindLong(5, serverBloodOxyDayData.getMinValue());
        sQLiteStatement.bindLong(6, serverBloodOxyDayData.getAvgValue());
        sQLiteStatement.bindLong(7, serverBloodOxyDayData.getLatestValue());
        sQLiteStatement.bindLong(8, serverBloodOxyDayData.getMeasurementTimes());
        String sourceMac = serverBloodOxyDayData.getSourceMac();
        if (sourceMac != null) {
            sQLiteStatement.bindString(9, sourceMac);
        }
        String items = serverBloodOxyDayData.getItems();
        if (items != null) {
            sQLiteStatement.bindString(10, items);
        }
        sQLiteStatement.bindLong(11, serverBloodOxyDayData.getTimestamp());
        sQLiteStatement.bindLong(12, serverBloodOxyDayData.getUploaded() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(ServerBloodOxyDayData serverBloodOxyDayData) {
        super.attachEntity(serverBloodOxyDayData);
        serverBloodOxyDayData.__setDaoSession(this.daoSession);
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
    public ServerBloodOxyDayData readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = i + 2;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = cursor.getInt(i + 3);
        int i5 = cursor.getInt(i + 4);
        int i6 = cursor.getInt(i + 5);
        int i7 = cursor.getInt(i + 6);
        int i8 = cursor.getInt(i + 7);
        int i9 = i + 8;
        String string2 = cursor.isNull(i9) ? null : cursor.getString(i9);
        int i10 = i + 9;
        return new ServerBloodOxyDayData(lValueOf, j, string, i4, i5, i6, i7, i8, string2, cursor.isNull(i10) ? null : cursor.getString(i10), cursor.getLong(i + 10), cursor.getShort(i + 11) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ServerBloodOxyDayData serverBloodOxyDayData, int i) {
        int i2 = i + 0;
        serverBloodOxyDayData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        serverBloodOxyDayData.setUserId(cursor.getLong(i + 1));
        int i3 = i + 2;
        serverBloodOxyDayData.setDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        serverBloodOxyDayData.setMaxValue(cursor.getInt(i + 3));
        serverBloodOxyDayData.setMinValue(cursor.getInt(i + 4));
        serverBloodOxyDayData.setAvgValue(cursor.getInt(i + 5));
        serverBloodOxyDayData.setLatestValue(cursor.getInt(i + 6));
        serverBloodOxyDayData.setMeasurementTimes(cursor.getInt(i + 7));
        int i4 = i + 8;
        serverBloodOxyDayData.setSourceMac(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 9;
        serverBloodOxyDayData.setItems(cursor.isNull(i5) ? null : cursor.getString(i5));
        serverBloodOxyDayData.setTimestamp(cursor.getLong(i + 10));
        serverBloodOxyDayData.setUploaded(cursor.getShort(i + 11) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(ServerBloodOxyDayData serverBloodOxyDayData, long j) {
        serverBloodOxyDayData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(ServerBloodOxyDayData serverBloodOxyDayData) {
        if (serverBloodOxyDayData != null) {
            return serverBloodOxyDayData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ServerBloodOxyDayData serverBloodOxyDayData) {
        return serverBloodOxyDayData.getId() != null;
    }
}