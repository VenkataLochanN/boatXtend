package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class ServerDaysSleepDataDao extends AbstractDao<ServerDaysSleepData, Long> {
    public static final String TABLENAME = "SERVER_DAYS_SLEEP_DATA";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "userId", false, "USER_ID");
        public static final Property AvgAwakeSeconds = new Property(2, Integer.TYPE, "avgAwakeSeconds", false, "AVG_AWAKE_SECONDS");
        public static final Property AvgLightlySeconds = new Property(3, Integer.TYPE, "avgLightlySeconds", false, "AVG_LIGHTLY_SECONDS");
        public static final Property AvgDeeplySeconds = new Property(4, Integer.TYPE, "avgDeeplySeconds", false, "AVG_DEEPLY_SECONDS");
        public static final Property AvgEyeMovementSeconds = new Property(5, Integer.TYPE, "avgEyeMovementSeconds", false, "AVG_EYE_MOVEMENT_SECONDS");
        public static final Property AvgTotalSeconds = new Property(6, Integer.TYPE, "avgTotalSeconds", false, "AVG_TOTAL_SECONDS");
        public static final Property AwakeRatio = new Property(7, Integer.TYPE, "awakeRatio", false, "AWAKE_RATIO");
        public static final Property LightlyRatio = new Property(8, Integer.TYPE, "lightlyRatio", false, "LIGHTLY_RATIO");
        public static final Property DeeplyRatio = new Property(9, Integer.TYPE, "deeplyRatio", false, "DEEPLY_RATIO");
        public static final Property EyeMovementRatio = new Property(10, Integer.TYPE, "eyeMovementRatio", false, "EYE_MOVEMENT_RATIO");
        public static final Property AvgScore = new Property(11, Integer.TYPE, "avgScore", false, "AVG_SCORE");
        public static final Property AvgBreathRate = new Property(12, Integer.TYPE, "avgBreathRate", false, "AVG_BREATH_RATE");
        public static final Property AvgStartTimeMinute = new Property(13, Integer.TYPE, "avgStartTimeMinute", false, "AVG_START_TIME_MINUTE");
        public static final Property AvgEndTimeMinute = new Property(14, Integer.TYPE, "avgEndTimeMinute", false, "AVG_END_TIME_MINUTE");
        public static final Property StartDate = new Property(15, String.class, "startDate", false, "START_DATE");
        public static final Property EndDate = new Property(16, String.class, "endDate", false, "END_DATE");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public ServerDaysSleepDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ServerDaysSleepDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"SERVER_DAYS_SLEEP_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"AVG_AWAKE_SECONDS\" INTEGER NOT NULL ,\"AVG_LIGHTLY_SECONDS\" INTEGER NOT NULL ,\"AVG_DEEPLY_SECONDS\" INTEGER NOT NULL ,\"AVG_EYE_MOVEMENT_SECONDS\" INTEGER NOT NULL ,\"AVG_TOTAL_SECONDS\" INTEGER NOT NULL ,\"AWAKE_RATIO\" INTEGER NOT NULL ,\"LIGHTLY_RATIO\" INTEGER NOT NULL ,\"DEEPLY_RATIO\" INTEGER NOT NULL ,\"EYE_MOVEMENT_RATIO\" INTEGER NOT NULL ,\"AVG_SCORE\" INTEGER NOT NULL ,\"AVG_BREATH_RATE\" INTEGER NOT NULL ,\"AVG_START_TIME_MINUTE\" INTEGER NOT NULL ,\"AVG_END_TIME_MINUTE\" INTEGER NOT NULL ,\"START_DATE\" TEXT,\"END_DATE\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"SERVER_DAYS_SLEEP_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ServerDaysSleepData serverDaysSleepData) {
        databaseStatement.clearBindings();
        Long id = serverDaysSleepData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, serverDaysSleepData.getUserId());
        databaseStatement.bindLong(3, serverDaysSleepData.getAvgAwakeSeconds());
        databaseStatement.bindLong(4, serverDaysSleepData.getAvgLightlySeconds());
        databaseStatement.bindLong(5, serverDaysSleepData.getAvgDeeplySeconds());
        databaseStatement.bindLong(6, serverDaysSleepData.getAvgEyeMovementSeconds());
        databaseStatement.bindLong(7, serverDaysSleepData.getAvgTotalSeconds());
        databaseStatement.bindLong(8, serverDaysSleepData.getAwakeRatio());
        databaseStatement.bindLong(9, serverDaysSleepData.getLightlyRatio());
        databaseStatement.bindLong(10, serverDaysSleepData.getDeeplyRatio());
        databaseStatement.bindLong(11, serverDaysSleepData.getEyeMovementRatio());
        databaseStatement.bindLong(12, serverDaysSleepData.getAvgScore());
        databaseStatement.bindLong(13, serverDaysSleepData.getAvgBreathRate());
        databaseStatement.bindLong(14, serverDaysSleepData.getAvgStartTimeMinute());
        databaseStatement.bindLong(15, serverDaysSleepData.getAvgEndTimeMinute());
        String startDate = serverDaysSleepData.getStartDate();
        if (startDate != null) {
            databaseStatement.bindString(16, startDate);
        }
        String endDate = serverDaysSleepData.getEndDate();
        if (endDate != null) {
            databaseStatement.bindString(17, endDate);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ServerDaysSleepData serverDaysSleepData) {
        sQLiteStatement.clearBindings();
        Long id = serverDaysSleepData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, serverDaysSleepData.getUserId());
        sQLiteStatement.bindLong(3, serverDaysSleepData.getAvgAwakeSeconds());
        sQLiteStatement.bindLong(4, serverDaysSleepData.getAvgLightlySeconds());
        sQLiteStatement.bindLong(5, serverDaysSleepData.getAvgDeeplySeconds());
        sQLiteStatement.bindLong(6, serverDaysSleepData.getAvgEyeMovementSeconds());
        sQLiteStatement.bindLong(7, serverDaysSleepData.getAvgTotalSeconds());
        sQLiteStatement.bindLong(8, serverDaysSleepData.getAwakeRatio());
        sQLiteStatement.bindLong(9, serverDaysSleepData.getLightlyRatio());
        sQLiteStatement.bindLong(10, serverDaysSleepData.getDeeplyRatio());
        sQLiteStatement.bindLong(11, serverDaysSleepData.getEyeMovementRatio());
        sQLiteStatement.bindLong(12, serverDaysSleepData.getAvgScore());
        sQLiteStatement.bindLong(13, serverDaysSleepData.getAvgBreathRate());
        sQLiteStatement.bindLong(14, serverDaysSleepData.getAvgStartTimeMinute());
        sQLiteStatement.bindLong(15, serverDaysSleepData.getAvgEndTimeMinute());
        String startDate = serverDaysSleepData.getStartDate();
        if (startDate != null) {
            sQLiteStatement.bindString(16, startDate);
        }
        String endDate = serverDaysSleepData.getEndDate();
        if (endDate != null) {
            sQLiteStatement.bindString(17, endDate);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(ServerDaysSleepData serverDaysSleepData) {
        super.attachEntity(serverDaysSleepData);
        serverDaysSleepData.__setDaoSession(this.daoSession);
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
    public ServerDaysSleepData readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = cursor.getInt(i + 2);
        int i4 = cursor.getInt(i + 3);
        int i5 = cursor.getInt(i + 4);
        int i6 = cursor.getInt(i + 5);
        int i7 = cursor.getInt(i + 6);
        int i8 = cursor.getInt(i + 7);
        int i9 = cursor.getInt(i + 8);
        int i10 = cursor.getInt(i + 9);
        int i11 = cursor.getInt(i + 10);
        int i12 = cursor.getInt(i + 11);
        int i13 = cursor.getInt(i + 12);
        int i14 = cursor.getInt(i + 13);
        int i15 = cursor.getInt(i + 14);
        int i16 = i + 15;
        String string = cursor.isNull(i16) ? null : cursor.getString(i16);
        int i17 = i + 16;
        return new ServerDaysSleepData(lValueOf, j, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, string, cursor.isNull(i17) ? null : cursor.getString(i17));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ServerDaysSleepData serverDaysSleepData, int i) {
        int i2 = i + 0;
        serverDaysSleepData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        serverDaysSleepData.setUserId(cursor.getLong(i + 1));
        serverDaysSleepData.setAvgAwakeSeconds(cursor.getInt(i + 2));
        serverDaysSleepData.setAvgLightlySeconds(cursor.getInt(i + 3));
        serverDaysSleepData.setAvgDeeplySeconds(cursor.getInt(i + 4));
        serverDaysSleepData.setAvgEyeMovementSeconds(cursor.getInt(i + 5));
        serverDaysSleepData.setAvgTotalSeconds(cursor.getInt(i + 6));
        serverDaysSleepData.setAwakeRatio(cursor.getInt(i + 7));
        serverDaysSleepData.setLightlyRatio(cursor.getInt(i + 8));
        serverDaysSleepData.setDeeplyRatio(cursor.getInt(i + 9));
        serverDaysSleepData.setEyeMovementRatio(cursor.getInt(i + 10));
        serverDaysSleepData.setAvgScore(cursor.getInt(i + 11));
        serverDaysSleepData.setAvgBreathRate(cursor.getInt(i + 12));
        serverDaysSleepData.setAvgStartTimeMinute(cursor.getInt(i + 13));
        serverDaysSleepData.setAvgEndTimeMinute(cursor.getInt(i + 14));
        int i3 = i + 15;
        serverDaysSleepData.setStartDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 16;
        serverDaysSleepData.setEndDate(cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(ServerDaysSleepData serverDaysSleepData, long j) {
        serverDaysSleepData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(ServerDaysSleepData serverDaysSleepData) {
        if (serverDaysSleepData != null) {
            return serverDaysSleepData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ServerDaysSleepData serverDaysSleepData) {
        return serverDaysSleepData.getId() != null;
    }
}