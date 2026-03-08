package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class ServerHeartRateDayDataDao extends AbstractDao<ServerHeartRateDayData, Long> {
    public static final String TABLENAME = "SERVER_HEART_RATE_DAY_DATA";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "userId", false, "USER_ID");
        public static final Property UploadSuccess = new Property(2, Boolean.TYPE, "uploadSuccess", false, "UPLOAD_SUCCESS");
        public static final Property Date = new Property(3, String.class, "date", false, "DATE");
        public static final Property WarmUpSeconds = new Property(4, Integer.TYPE, "warmUpSeconds", false, "WARM_UP_SECONDS");
        public static final Property WarmUpThreshold = new Property(5, Integer.TYPE, "warmUpThreshold", false, "WARM_UP_THRESHOLD");
        public static final Property BurnFatSeconds = new Property(6, Integer.TYPE, "burnFatSeconds", false, "BURN_FAT_SECONDS");
        public static final Property BurnFatThreshold = new Property(7, Integer.TYPE, "burnFatThreshold", false, "BURN_FAT_THRESHOLD");
        public static final Property AerobicSeconds = new Property(8, Integer.TYPE, "aerobicSeconds", false, "AEROBIC_SECONDS");
        public static final Property AerobicThreshold = new Property(9, Integer.TYPE, "aerobicThreshold", false, "AEROBIC_THRESHOLD");
        public static final Property AnaerobicSeconds = new Property(10, Integer.TYPE, "anaerobicSeconds", false, "ANAEROBIC_SECONDS");
        public static final Property AnaerobicThreshold = new Property(11, Integer.TYPE, "anaerobicThreshold", false, "ANAEROBIC_THRESHOLD");
        public static final Property LimitSeconds = new Property(12, Integer.TYPE, "limitSeconds", false, "LIMIT_SECONDS");
        public static final Property LimitThreshold = new Property(13, Integer.TYPE, "limitThreshold", false, "LIMIT_THRESHOLD");
        public static final Property MinValue = new Property(14, Integer.TYPE, "minValue", false, "MIN_VALUE");
        public static final Property MaxValue = new Property(15, Integer.TYPE, "maxValue", false, "MAX_VALUE");
        public static final Property AvgValue = new Property(16, Integer.TYPE, "avgValue", false, "AVG_VALUE");
        public static final Property SilentValue = new Property(17, Integer.TYPE, "silentValue", false, "SILENT_VALUE");
        public static final Property LatestValue = new Property(18, Integer.TYPE, "latestValue", false, "LATEST_VALUE");
        public static final Property StartTimeValue = new Property(19, Integer.TYPE, "startTimeValue", false, "START_TIME_VALUE");
        public static final Property Items = new Property(20, String.class, "items", false, "ITEMS");
        public static final Property ChartItems = new Property(21, String.class, "chartItems", false, "CHART_ITEMS");
        public static final Property HeartMonitorType = new Property(22, Integer.TYPE, "heartMonitorType", false, "HEART_MONITOR_TYPE");
        public static final Property DeviceName = new Property(23, String.class, "deviceName", false, "DEVICE_NAME");
        public static final Property SourceMac = new Property(24, String.class, "sourceMac", false, "SOURCE_MAC");
        public static final Property Timestamp = new Property(25, Long.TYPE, "timestamp", false, "TIMESTAMP");
        public static final Property LoadDetail = new Property(26, Boolean.TYPE, "loadDetail", false, "LOAD_DETAIL");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public ServerHeartRateDayDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ServerHeartRateDayDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"SERVER_HEART_RATE_DAY_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"UPLOAD_SUCCESS\" INTEGER NOT NULL ,\"DATE\" TEXT,\"WARM_UP_SECONDS\" INTEGER NOT NULL ,\"WARM_UP_THRESHOLD\" INTEGER NOT NULL ,\"BURN_FAT_SECONDS\" INTEGER NOT NULL ,\"BURN_FAT_THRESHOLD\" INTEGER NOT NULL ,\"AEROBIC_SECONDS\" INTEGER NOT NULL ,\"AEROBIC_THRESHOLD\" INTEGER NOT NULL ,\"ANAEROBIC_SECONDS\" INTEGER NOT NULL ,\"ANAEROBIC_THRESHOLD\" INTEGER NOT NULL ,\"LIMIT_SECONDS\" INTEGER NOT NULL ,\"LIMIT_THRESHOLD\" INTEGER NOT NULL ,\"MIN_VALUE\" INTEGER NOT NULL ,\"MAX_VALUE\" INTEGER NOT NULL ,\"AVG_VALUE\" INTEGER NOT NULL ,\"SILENT_VALUE\" INTEGER NOT NULL ,\"LATEST_VALUE\" INTEGER NOT NULL ,\"START_TIME_VALUE\" INTEGER NOT NULL ,\"ITEMS\" TEXT,\"CHART_ITEMS\" TEXT,\"HEART_MONITOR_TYPE\" INTEGER NOT NULL ,\"DEVICE_NAME\" TEXT,\"SOURCE_MAC\" TEXT,\"TIMESTAMP\" INTEGER NOT NULL ,\"LOAD_DETAIL\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"SERVER_HEART_RATE_DAY_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ServerHeartRateDayData serverHeartRateDayData) {
        databaseStatement.clearBindings();
        Long id = serverHeartRateDayData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, serverHeartRateDayData.getUserId());
        databaseStatement.bindLong(3, serverHeartRateDayData.getUploadSuccess() ? 1L : 0L);
        String date = serverHeartRateDayData.getDate();
        if (date != null) {
            databaseStatement.bindString(4, date);
        }
        databaseStatement.bindLong(5, serverHeartRateDayData.getWarmUpSeconds());
        databaseStatement.bindLong(6, serverHeartRateDayData.getWarmUpThreshold());
        databaseStatement.bindLong(7, serverHeartRateDayData.getBurnFatSeconds());
        databaseStatement.bindLong(8, serverHeartRateDayData.getBurnFatThreshold());
        databaseStatement.bindLong(9, serverHeartRateDayData.getAerobicSeconds());
        databaseStatement.bindLong(10, serverHeartRateDayData.getAerobicThreshold());
        databaseStatement.bindLong(11, serverHeartRateDayData.getAnaerobicSeconds());
        databaseStatement.bindLong(12, serverHeartRateDayData.getAnaerobicThreshold());
        databaseStatement.bindLong(13, serverHeartRateDayData.getLimitSeconds());
        databaseStatement.bindLong(14, serverHeartRateDayData.getLimitThreshold());
        databaseStatement.bindLong(15, serverHeartRateDayData.getMinValue());
        databaseStatement.bindLong(16, serverHeartRateDayData.getMaxValue());
        databaseStatement.bindLong(17, serverHeartRateDayData.getAvgValue());
        databaseStatement.bindLong(18, serverHeartRateDayData.getSilentValue());
        databaseStatement.bindLong(19, serverHeartRateDayData.getLatestValue());
        databaseStatement.bindLong(20, serverHeartRateDayData.getStartTimeValue());
        String items = serverHeartRateDayData.getItems();
        if (items != null) {
            databaseStatement.bindString(21, items);
        }
        String chartItems = serverHeartRateDayData.getChartItems();
        if (chartItems != null) {
            databaseStatement.bindString(22, chartItems);
        }
        databaseStatement.bindLong(23, serverHeartRateDayData.getHeartMonitorType());
        String deviceName = serverHeartRateDayData.getDeviceName();
        if (deviceName != null) {
            databaseStatement.bindString(24, deviceName);
        }
        String sourceMac = serverHeartRateDayData.getSourceMac();
        if (sourceMac != null) {
            databaseStatement.bindString(25, sourceMac);
        }
        databaseStatement.bindLong(26, serverHeartRateDayData.getTimestamp());
        databaseStatement.bindLong(27, serverHeartRateDayData.getLoadDetail() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ServerHeartRateDayData serverHeartRateDayData) {
        sQLiteStatement.clearBindings();
        Long id = serverHeartRateDayData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, serverHeartRateDayData.getUserId());
        sQLiteStatement.bindLong(3, serverHeartRateDayData.getUploadSuccess() ? 1L : 0L);
        String date = serverHeartRateDayData.getDate();
        if (date != null) {
            sQLiteStatement.bindString(4, date);
        }
        sQLiteStatement.bindLong(5, serverHeartRateDayData.getWarmUpSeconds());
        sQLiteStatement.bindLong(6, serverHeartRateDayData.getWarmUpThreshold());
        sQLiteStatement.bindLong(7, serverHeartRateDayData.getBurnFatSeconds());
        sQLiteStatement.bindLong(8, serverHeartRateDayData.getBurnFatThreshold());
        sQLiteStatement.bindLong(9, serverHeartRateDayData.getAerobicSeconds());
        sQLiteStatement.bindLong(10, serverHeartRateDayData.getAerobicThreshold());
        sQLiteStatement.bindLong(11, serverHeartRateDayData.getAnaerobicSeconds());
        sQLiteStatement.bindLong(12, serverHeartRateDayData.getAnaerobicThreshold());
        sQLiteStatement.bindLong(13, serverHeartRateDayData.getLimitSeconds());
        sQLiteStatement.bindLong(14, serverHeartRateDayData.getLimitThreshold());
        sQLiteStatement.bindLong(15, serverHeartRateDayData.getMinValue());
        sQLiteStatement.bindLong(16, serverHeartRateDayData.getMaxValue());
        sQLiteStatement.bindLong(17, serverHeartRateDayData.getAvgValue());
        sQLiteStatement.bindLong(18, serverHeartRateDayData.getSilentValue());
        sQLiteStatement.bindLong(19, serverHeartRateDayData.getLatestValue());
        sQLiteStatement.bindLong(20, serverHeartRateDayData.getStartTimeValue());
        String items = serverHeartRateDayData.getItems();
        if (items != null) {
            sQLiteStatement.bindString(21, items);
        }
        String chartItems = serverHeartRateDayData.getChartItems();
        if (chartItems != null) {
            sQLiteStatement.bindString(22, chartItems);
        }
        sQLiteStatement.bindLong(23, serverHeartRateDayData.getHeartMonitorType());
        String deviceName = serverHeartRateDayData.getDeviceName();
        if (deviceName != null) {
            sQLiteStatement.bindString(24, deviceName);
        }
        String sourceMac = serverHeartRateDayData.getSourceMac();
        if (sourceMac != null) {
            sQLiteStatement.bindString(25, sourceMac);
        }
        sQLiteStatement.bindLong(26, serverHeartRateDayData.getTimestamp());
        sQLiteStatement.bindLong(27, serverHeartRateDayData.getLoadDetail() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(ServerHeartRateDayData serverHeartRateDayData) {
        super.attachEntity(serverHeartRateDayData);
        serverHeartRateDayData.__setDaoSession(this.daoSession);
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
    public ServerHeartRateDayData readEntity(Cursor cursor, int i) {
        ServerHeartRateDayData serverHeartRateDayData = new ServerHeartRateDayData();
        readEntity(cursor, serverHeartRateDayData, i);
        return serverHeartRateDayData;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ServerHeartRateDayData serverHeartRateDayData, int i) {
        int i2 = i + 0;
        serverHeartRateDayData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        serverHeartRateDayData.setUserId(cursor.getLong(i + 1));
        serverHeartRateDayData.setUploadSuccess(cursor.getShort(i + 2) != 0);
        int i3 = i + 3;
        serverHeartRateDayData.setDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        serverHeartRateDayData.setWarmUpSeconds(cursor.getInt(i + 4));
        serverHeartRateDayData.setWarmUpThreshold(cursor.getInt(i + 5));
        serverHeartRateDayData.setBurnFatSeconds(cursor.getInt(i + 6));
        serverHeartRateDayData.setBurnFatThreshold(cursor.getInt(i + 7));
        serverHeartRateDayData.setAerobicSeconds(cursor.getInt(i + 8));
        serverHeartRateDayData.setAerobicThreshold(cursor.getInt(i + 9));
        serverHeartRateDayData.setAnaerobicSeconds(cursor.getInt(i + 10));
        serverHeartRateDayData.setAnaerobicThreshold(cursor.getInt(i + 11));
        serverHeartRateDayData.setLimitSeconds(cursor.getInt(i + 12));
        serverHeartRateDayData.setLimitThreshold(cursor.getInt(i + 13));
        serverHeartRateDayData.setMinValue(cursor.getInt(i + 14));
        serverHeartRateDayData.setMaxValue(cursor.getInt(i + 15));
        serverHeartRateDayData.setAvgValue(cursor.getInt(i + 16));
        serverHeartRateDayData.setSilentValue(cursor.getInt(i + 17));
        serverHeartRateDayData.setLatestValue(cursor.getInt(i + 18));
        serverHeartRateDayData.setStartTimeValue(cursor.getInt(i + 19));
        int i4 = i + 20;
        serverHeartRateDayData.setItems(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 21;
        serverHeartRateDayData.setChartItems(cursor.isNull(i5) ? null : cursor.getString(i5));
        serverHeartRateDayData.setHeartMonitorType(cursor.getInt(i + 22));
        int i6 = i + 23;
        serverHeartRateDayData.setDeviceName(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 24;
        serverHeartRateDayData.setSourceMac(cursor.isNull(i7) ? null : cursor.getString(i7));
        serverHeartRateDayData.setTimestamp(cursor.getLong(i + 25));
        serverHeartRateDayData.setLoadDetail(cursor.getShort(i + 26) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(ServerHeartRateDayData serverHeartRateDayData, long j) {
        serverHeartRateDayData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(ServerHeartRateDayData serverHeartRateDayData) {
        if (serverHeartRateDayData != null) {
            return serverHeartRateDayData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ServerHeartRateDayData serverHeartRateDayData) {
        return serverHeartRateDayData.getId() != null;
    }
}