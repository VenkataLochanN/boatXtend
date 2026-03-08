package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class ServerMultiMonthSleepTotalDataDao extends AbstractDao<ServerMultiMonthSleepTotalData, Long> {
    public static final String TABLENAME = "SERVER_MULTI_MONTH_SLEEP_TOTAL_DATA";
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
        public static final Property StartDate = new Property(11, String.class, "startDate", false, "START_DATE");
        public static final Property EndDate = new Property(12, String.class, "endDate", false, "END_DATE");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public ServerMultiMonthSleepTotalDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ServerMultiMonthSleepTotalDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"SERVER_MULTI_MONTH_SLEEP_TOTAL_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"AVG_AWAKE_SECONDS\" INTEGER NOT NULL ,\"AVG_LIGHTLY_SECONDS\" INTEGER NOT NULL ,\"AVG_DEEPLY_SECONDS\" INTEGER NOT NULL ,\"AVG_EYE_MOVEMENT_SECONDS\" INTEGER NOT NULL ,\"AVG_TOTAL_SECONDS\" INTEGER NOT NULL ,\"AWAKE_RATIO\" INTEGER NOT NULL ,\"LIGHTLY_RATIO\" INTEGER NOT NULL ,\"DEEPLY_RATIO\" INTEGER NOT NULL ,\"EYE_MOVEMENT_RATIO\" INTEGER NOT NULL ,\"START_DATE\" TEXT,\"END_DATE\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"SERVER_MULTI_MONTH_SLEEP_TOTAL_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ServerMultiMonthSleepTotalData serverMultiMonthSleepTotalData) {
        databaseStatement.clearBindings();
        Long id = serverMultiMonthSleepTotalData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, serverMultiMonthSleepTotalData.getUserId());
        databaseStatement.bindLong(3, serverMultiMonthSleepTotalData.getAvgAwakeSeconds());
        databaseStatement.bindLong(4, serverMultiMonthSleepTotalData.getAvgLightlySeconds());
        databaseStatement.bindLong(5, serverMultiMonthSleepTotalData.getAvgDeeplySeconds());
        databaseStatement.bindLong(6, serverMultiMonthSleepTotalData.getAvgEyeMovementSeconds());
        databaseStatement.bindLong(7, serverMultiMonthSleepTotalData.getAvgTotalSeconds());
        databaseStatement.bindLong(8, serverMultiMonthSleepTotalData.getAwakeRatio());
        databaseStatement.bindLong(9, serverMultiMonthSleepTotalData.getLightlyRatio());
        databaseStatement.bindLong(10, serverMultiMonthSleepTotalData.getDeeplyRatio());
        databaseStatement.bindLong(11, serverMultiMonthSleepTotalData.getEyeMovementRatio());
        String startDate = serverMultiMonthSleepTotalData.getStartDate();
        if (startDate != null) {
            databaseStatement.bindString(12, startDate);
        }
        String endDate = serverMultiMonthSleepTotalData.getEndDate();
        if (endDate != null) {
            databaseStatement.bindString(13, endDate);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ServerMultiMonthSleepTotalData serverMultiMonthSleepTotalData) {
        sQLiteStatement.clearBindings();
        Long id = serverMultiMonthSleepTotalData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, serverMultiMonthSleepTotalData.getUserId());
        sQLiteStatement.bindLong(3, serverMultiMonthSleepTotalData.getAvgAwakeSeconds());
        sQLiteStatement.bindLong(4, serverMultiMonthSleepTotalData.getAvgLightlySeconds());
        sQLiteStatement.bindLong(5, serverMultiMonthSleepTotalData.getAvgDeeplySeconds());
        sQLiteStatement.bindLong(6, serverMultiMonthSleepTotalData.getAvgEyeMovementSeconds());
        sQLiteStatement.bindLong(7, serverMultiMonthSleepTotalData.getAvgTotalSeconds());
        sQLiteStatement.bindLong(8, serverMultiMonthSleepTotalData.getAwakeRatio());
        sQLiteStatement.bindLong(9, serverMultiMonthSleepTotalData.getLightlyRatio());
        sQLiteStatement.bindLong(10, serverMultiMonthSleepTotalData.getDeeplyRatio());
        sQLiteStatement.bindLong(11, serverMultiMonthSleepTotalData.getEyeMovementRatio());
        String startDate = serverMultiMonthSleepTotalData.getStartDate();
        if (startDate != null) {
            sQLiteStatement.bindString(12, startDate);
        }
        String endDate = serverMultiMonthSleepTotalData.getEndDate();
        if (endDate != null) {
            sQLiteStatement.bindString(13, endDate);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(ServerMultiMonthSleepTotalData serverMultiMonthSleepTotalData) {
        super.attachEntity(serverMultiMonthSleepTotalData);
        serverMultiMonthSleepTotalData.__setDaoSession(this.daoSession);
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
    public ServerMultiMonthSleepTotalData readEntity(Cursor cursor, int i) {
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
        int i12 = i + 11;
        String string = cursor.isNull(i12) ? null : cursor.getString(i12);
        int i13 = i + 12;
        return new ServerMultiMonthSleepTotalData(lValueOf, j, i3, i4, i5, i6, i7, i8, i9, i10, i11, string, cursor.isNull(i13) ? null : cursor.getString(i13));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ServerMultiMonthSleepTotalData serverMultiMonthSleepTotalData, int i) {
        int i2 = i + 0;
        serverMultiMonthSleepTotalData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        serverMultiMonthSleepTotalData.setUserId(cursor.getLong(i + 1));
        serverMultiMonthSleepTotalData.setAvgAwakeSeconds(cursor.getInt(i + 2));
        serverMultiMonthSleepTotalData.setAvgLightlySeconds(cursor.getInt(i + 3));
        serverMultiMonthSleepTotalData.setAvgDeeplySeconds(cursor.getInt(i + 4));
        serverMultiMonthSleepTotalData.setAvgEyeMovementSeconds(cursor.getInt(i + 5));
        serverMultiMonthSleepTotalData.setAvgTotalSeconds(cursor.getInt(i + 6));
        serverMultiMonthSleepTotalData.setAwakeRatio(cursor.getInt(i + 7));
        serverMultiMonthSleepTotalData.setLightlyRatio(cursor.getInt(i + 8));
        serverMultiMonthSleepTotalData.setDeeplyRatio(cursor.getInt(i + 9));
        serverMultiMonthSleepTotalData.setEyeMovementRatio(cursor.getInt(i + 10));
        int i3 = i + 11;
        serverMultiMonthSleepTotalData.setStartDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 12;
        serverMultiMonthSleepTotalData.setEndDate(cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(ServerMultiMonthSleepTotalData serverMultiMonthSleepTotalData, long j) {
        serverMultiMonthSleepTotalData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(ServerMultiMonthSleepTotalData serverMultiMonthSleepTotalData) {
        if (serverMultiMonthSleepTotalData != null) {
            return serverMultiMonthSleepTotalData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ServerMultiMonthSleepTotalData serverMultiMonthSleepTotalData) {
        return serverMultiMonthSleepTotalData.getId() != null;
    }
}