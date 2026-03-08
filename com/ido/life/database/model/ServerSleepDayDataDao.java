package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.king.zxing.Intents;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class ServerSleepDayDataDao extends AbstractDao<ServerSleepDayData, Long> {
    public static final String TABLENAME = "SERVER_SLEEP_DAY_DATA";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "userId", false, "USER_ID");
        public static final Property Date = new Property(2, String.class, "date", false, "DATE");
        public static final Property AwakeSeconds = new Property(3, Integer.TYPE, "awakeSeconds", false, "AWAKE_SECONDS");
        public static final Property LightlySeconds = new Property(4, Integer.TYPE, "lightlySeconds", false, "LIGHTLY_SECONDS");
        public static final Property DeeplySeconds = new Property(5, Integer.TYPE, "deeplySeconds", false, "DEEPLY_SECONDS");
        public static final Property EyeMovementSeconds = new Property(6, Integer.TYPE, "eyeMovementSeconds", false, "EYE_MOVEMENT_SECONDS");
        public static final Property TotalSeconds = new Property(7, Integer.TYPE, "totalSeconds", false, "TOTAL_SECONDS");
        public static final Property StartTime = new Property(8, String.class, "startTime", false, "START_TIME");
        public static final Property EndTime = new Property(9, String.class, "endTime", false, "END_TIME");
        public static final Property Items = new Property(10, String.class, "items", false, "ITEMS");
        public static final Property SourceMac = new Property(11, String.class, "sourceMac", false, "SOURCE_MAC");
        public static final Property DeviceName = new Property(12, String.class, "deviceName", false, "DEVICE_NAME");
        public static final Property AwakeRatio = new Property(13, Integer.TYPE, "awakeRatio", false, "AWAKE_RATIO");
        public static final Property LightlyRatio = new Property(14, Integer.TYPE, "lightlyRatio", false, "LIGHTLY_RATIO");
        public static final Property DeeplyRatio = new Property(15, Integer.TYPE, "deeplyRatio", false, "DEEPLY_RATIO");
        public static final Property EyeMovementRatio = new Property(16, Integer.TYPE, "eyeMovementRatio", false, "EYE_MOVEMENT_RATIO");
        public static final Property Score = new Property(17, Integer.TYPE, "score", false, "SCORE");
        public static final Property BreathRate = new Property(18, Integer.TYPE, "breathRate", false, "BREATH_RATE");
        public static final Property Timestamp = new Property(19, Long.TYPE, "timestamp", false, "TIMESTAMP");
        public static final Property Type = new Property(20, Integer.TYPE, "type", false, Intents.WifiConnect.TYPE);
        public static final Property StartTimeMinuteOffset = new Property(21, Integer.TYPE, "startTimeMinuteOffset", false, "START_TIME_MINUTE_OFFSET");
        public static final Property EndTimeMinuteOffset = new Property(22, Integer.TYPE, "endTimeMinuteOffset", false, "END_TIME_MINUTE_OFFSET");
        public static final Property Uploaded = new Property(23, Boolean.TYPE, "uploaded", false, "UPLOADED");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public ServerSleepDayDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ServerSleepDayDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"SERVER_SLEEP_DAY_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"DATE\" TEXT,\"AWAKE_SECONDS\" INTEGER NOT NULL ,\"LIGHTLY_SECONDS\" INTEGER NOT NULL ,\"DEEPLY_SECONDS\" INTEGER NOT NULL ,\"EYE_MOVEMENT_SECONDS\" INTEGER NOT NULL ,\"TOTAL_SECONDS\" INTEGER NOT NULL ,\"START_TIME\" TEXT,\"END_TIME\" TEXT,\"ITEMS\" TEXT,\"SOURCE_MAC\" TEXT,\"DEVICE_NAME\" TEXT,\"AWAKE_RATIO\" INTEGER NOT NULL ,\"LIGHTLY_RATIO\" INTEGER NOT NULL ,\"DEEPLY_RATIO\" INTEGER NOT NULL ,\"EYE_MOVEMENT_RATIO\" INTEGER NOT NULL ,\"SCORE\" INTEGER NOT NULL ,\"BREATH_RATE\" INTEGER NOT NULL ,\"TIMESTAMP\" INTEGER NOT NULL ,\"TYPE\" INTEGER NOT NULL ,\"START_TIME_MINUTE_OFFSET\" INTEGER NOT NULL ,\"END_TIME_MINUTE_OFFSET\" INTEGER NOT NULL ,\"UPLOADED\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"SERVER_SLEEP_DAY_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ServerSleepDayData serverSleepDayData) {
        databaseStatement.clearBindings();
        Long id = serverSleepDayData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, serverSleepDayData.getUserId());
        String date = serverSleepDayData.getDate();
        if (date != null) {
            databaseStatement.bindString(3, date);
        }
        databaseStatement.bindLong(4, serverSleepDayData.getAwakeSeconds());
        databaseStatement.bindLong(5, serverSleepDayData.getLightlySeconds());
        databaseStatement.bindLong(6, serverSleepDayData.getDeeplySeconds());
        databaseStatement.bindLong(7, serverSleepDayData.getEyeMovementSeconds());
        databaseStatement.bindLong(8, serverSleepDayData.getTotalSeconds());
        String startTime = serverSleepDayData.getStartTime();
        if (startTime != null) {
            databaseStatement.bindString(9, startTime);
        }
        String endTime = serverSleepDayData.getEndTime();
        if (endTime != null) {
            databaseStatement.bindString(10, endTime);
        }
        String items = serverSleepDayData.getItems();
        if (items != null) {
            databaseStatement.bindString(11, items);
        }
        String sourceMac = serverSleepDayData.getSourceMac();
        if (sourceMac != null) {
            databaseStatement.bindString(12, sourceMac);
        }
        String deviceName = serverSleepDayData.getDeviceName();
        if (deviceName != null) {
            databaseStatement.bindString(13, deviceName);
        }
        databaseStatement.bindLong(14, serverSleepDayData.getAwakeRatio());
        databaseStatement.bindLong(15, serverSleepDayData.getLightlyRatio());
        databaseStatement.bindLong(16, serverSleepDayData.getDeeplyRatio());
        databaseStatement.bindLong(17, serverSleepDayData.getEyeMovementRatio());
        databaseStatement.bindLong(18, serverSleepDayData.getScore());
        databaseStatement.bindLong(19, serverSleepDayData.getBreathRate());
        databaseStatement.bindLong(20, serverSleepDayData.getTimestamp());
        databaseStatement.bindLong(21, serverSleepDayData.getType());
        databaseStatement.bindLong(22, serverSleepDayData.getStartTimeMinuteOffset());
        databaseStatement.bindLong(23, serverSleepDayData.getEndTimeMinuteOffset());
        databaseStatement.bindLong(24, serverSleepDayData.getUploaded() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ServerSleepDayData serverSleepDayData) {
        sQLiteStatement.clearBindings();
        Long id = serverSleepDayData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, serverSleepDayData.getUserId());
        String date = serverSleepDayData.getDate();
        if (date != null) {
            sQLiteStatement.bindString(3, date);
        }
        sQLiteStatement.bindLong(4, serverSleepDayData.getAwakeSeconds());
        sQLiteStatement.bindLong(5, serverSleepDayData.getLightlySeconds());
        sQLiteStatement.bindLong(6, serverSleepDayData.getDeeplySeconds());
        sQLiteStatement.bindLong(7, serverSleepDayData.getEyeMovementSeconds());
        sQLiteStatement.bindLong(8, serverSleepDayData.getTotalSeconds());
        String startTime = serverSleepDayData.getStartTime();
        if (startTime != null) {
            sQLiteStatement.bindString(9, startTime);
        }
        String endTime = serverSleepDayData.getEndTime();
        if (endTime != null) {
            sQLiteStatement.bindString(10, endTime);
        }
        String items = serverSleepDayData.getItems();
        if (items != null) {
            sQLiteStatement.bindString(11, items);
        }
        String sourceMac = serverSleepDayData.getSourceMac();
        if (sourceMac != null) {
            sQLiteStatement.bindString(12, sourceMac);
        }
        String deviceName = serverSleepDayData.getDeviceName();
        if (deviceName != null) {
            sQLiteStatement.bindString(13, deviceName);
        }
        sQLiteStatement.bindLong(14, serverSleepDayData.getAwakeRatio());
        sQLiteStatement.bindLong(15, serverSleepDayData.getLightlyRatio());
        sQLiteStatement.bindLong(16, serverSleepDayData.getDeeplyRatio());
        sQLiteStatement.bindLong(17, serverSleepDayData.getEyeMovementRatio());
        sQLiteStatement.bindLong(18, serverSleepDayData.getScore());
        sQLiteStatement.bindLong(19, serverSleepDayData.getBreathRate());
        sQLiteStatement.bindLong(20, serverSleepDayData.getTimestamp());
        sQLiteStatement.bindLong(21, serverSleepDayData.getType());
        sQLiteStatement.bindLong(22, serverSleepDayData.getStartTimeMinuteOffset());
        sQLiteStatement.bindLong(23, serverSleepDayData.getEndTimeMinuteOffset());
        sQLiteStatement.bindLong(24, serverSleepDayData.getUploaded() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(ServerSleepDayData serverSleepDayData) {
        super.attachEntity(serverSleepDayData);
        serverSleepDayData.__setDaoSession(this.daoSession);
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
    public ServerSleepDayData readEntity(Cursor cursor, int i) {
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
        String string3 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 10;
        String string4 = cursor.isNull(i11) ? null : cursor.getString(i11);
        int i12 = i + 11;
        String string5 = cursor.isNull(i12) ? null : cursor.getString(i12);
        int i13 = i + 12;
        return new ServerSleepDayData(lValueOf, j, string, i4, i5, i6, i7, i8, string2, string3, string4, string5, cursor.isNull(i13) ? null : cursor.getString(i13), cursor.getInt(i + 13), cursor.getInt(i + 14), cursor.getInt(i + 15), cursor.getInt(i + 16), cursor.getInt(i + 17), cursor.getInt(i + 18), cursor.getLong(i + 19), cursor.getInt(i + 20), cursor.getInt(i + 21), cursor.getInt(i + 22), cursor.getShort(i + 23) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ServerSleepDayData serverSleepDayData, int i) {
        int i2 = i + 0;
        serverSleepDayData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        serverSleepDayData.setUserId(cursor.getLong(i + 1));
        int i3 = i + 2;
        serverSleepDayData.setDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        serverSleepDayData.setAwakeSeconds(cursor.getInt(i + 3));
        serverSleepDayData.setLightlySeconds(cursor.getInt(i + 4));
        serverSleepDayData.setDeeplySeconds(cursor.getInt(i + 5));
        serverSleepDayData.setEyeMovementSeconds(cursor.getInt(i + 6));
        serverSleepDayData.setTotalSeconds(cursor.getInt(i + 7));
        int i4 = i + 8;
        serverSleepDayData.setStartTime(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 9;
        serverSleepDayData.setEndTime(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 10;
        serverSleepDayData.setItems(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 11;
        serverSleepDayData.setSourceMac(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 12;
        serverSleepDayData.setDeviceName(cursor.isNull(i8) ? null : cursor.getString(i8));
        serverSleepDayData.setAwakeRatio(cursor.getInt(i + 13));
        serverSleepDayData.setLightlyRatio(cursor.getInt(i + 14));
        serverSleepDayData.setDeeplyRatio(cursor.getInt(i + 15));
        serverSleepDayData.setEyeMovementRatio(cursor.getInt(i + 16));
        serverSleepDayData.setScore(cursor.getInt(i + 17));
        serverSleepDayData.setBreathRate(cursor.getInt(i + 18));
        serverSleepDayData.setTimestamp(cursor.getLong(i + 19));
        serverSleepDayData.setType(cursor.getInt(i + 20));
        serverSleepDayData.setStartTimeMinuteOffset(cursor.getInt(i + 21));
        serverSleepDayData.setEndTimeMinuteOffset(cursor.getInt(i + 22));
        serverSleepDayData.setUploaded(cursor.getShort(i + 23) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(ServerSleepDayData serverSleepDayData, long j) {
        serverSleepDayData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(ServerSleepDayData serverSleepDayData) {
        if (serverSleepDayData != null) {
            return serverSleepDayData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ServerSleepDayData serverSleepDayData) {
        return serverSleepDayData.getId() != null;
    }
}