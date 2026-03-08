package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class ServerSleepMonthDataDao extends AbstractDao<ServerSleepMonthData, Long> {
    public static final String TABLENAME = "SERVER_SLEEP_MONTH_DATA";
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
        public static final Property Month = new Property(11, String.class, "month", false, "MONTH");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public ServerSleepMonthDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ServerSleepMonthDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"SERVER_SLEEP_MONTH_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"AVG_AWAKE_SECONDS\" INTEGER NOT NULL ,\"AVG_LIGHTLY_SECONDS\" INTEGER NOT NULL ,\"AVG_DEEPLY_SECONDS\" INTEGER NOT NULL ,\"AVG_EYE_MOVEMENT_SECONDS\" INTEGER NOT NULL ,\"AVG_TOTAL_SECONDS\" INTEGER NOT NULL ,\"AWAKE_RATIO\" INTEGER NOT NULL ,\"LIGHTLY_RATIO\" INTEGER NOT NULL ,\"DEEPLY_RATIO\" INTEGER NOT NULL ,\"EYE_MOVEMENT_RATIO\" INTEGER NOT NULL ,\"MONTH\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"SERVER_SLEEP_MONTH_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ServerSleepMonthData serverSleepMonthData) {
        databaseStatement.clearBindings();
        Long id = serverSleepMonthData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, serverSleepMonthData.getUserId());
        databaseStatement.bindLong(3, serverSleepMonthData.getAvgAwakeSeconds());
        databaseStatement.bindLong(4, serverSleepMonthData.getAvgLightlySeconds());
        databaseStatement.bindLong(5, serverSleepMonthData.getAvgDeeplySeconds());
        databaseStatement.bindLong(6, serverSleepMonthData.getAvgEyeMovementSeconds());
        databaseStatement.bindLong(7, serverSleepMonthData.getAvgTotalSeconds());
        databaseStatement.bindLong(8, serverSleepMonthData.getAwakeRatio());
        databaseStatement.bindLong(9, serverSleepMonthData.getLightlyRatio());
        databaseStatement.bindLong(10, serverSleepMonthData.getDeeplyRatio());
        databaseStatement.bindLong(11, serverSleepMonthData.getEyeMovementRatio());
        String month = serverSleepMonthData.getMonth();
        if (month != null) {
            databaseStatement.bindString(12, month);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ServerSleepMonthData serverSleepMonthData) {
        sQLiteStatement.clearBindings();
        Long id = serverSleepMonthData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, serverSleepMonthData.getUserId());
        sQLiteStatement.bindLong(3, serverSleepMonthData.getAvgAwakeSeconds());
        sQLiteStatement.bindLong(4, serverSleepMonthData.getAvgLightlySeconds());
        sQLiteStatement.bindLong(5, serverSleepMonthData.getAvgDeeplySeconds());
        sQLiteStatement.bindLong(6, serverSleepMonthData.getAvgEyeMovementSeconds());
        sQLiteStatement.bindLong(7, serverSleepMonthData.getAvgTotalSeconds());
        sQLiteStatement.bindLong(8, serverSleepMonthData.getAwakeRatio());
        sQLiteStatement.bindLong(9, serverSleepMonthData.getLightlyRatio());
        sQLiteStatement.bindLong(10, serverSleepMonthData.getDeeplyRatio());
        sQLiteStatement.bindLong(11, serverSleepMonthData.getEyeMovementRatio());
        String month = serverSleepMonthData.getMonth();
        if (month != null) {
            sQLiteStatement.bindString(12, month);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(ServerSleepMonthData serverSleepMonthData) {
        super.attachEntity(serverSleepMonthData);
        serverSleepMonthData.__setDaoSession(this.daoSession);
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
    public ServerSleepMonthData readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        int i3 = i + 11;
        return new ServerSleepMonthData(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.getLong(i + 1), cursor.getInt(i + 2), cursor.getInt(i + 3), cursor.getInt(i + 4), cursor.getInt(i + 5), cursor.getInt(i + 6), cursor.getInt(i + 7), cursor.getInt(i + 8), cursor.getInt(i + 9), cursor.getInt(i + 10), cursor.isNull(i3) ? null : cursor.getString(i3));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ServerSleepMonthData serverSleepMonthData, int i) {
        int i2 = i + 0;
        serverSleepMonthData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        serverSleepMonthData.setUserId(cursor.getLong(i + 1));
        serverSleepMonthData.setAvgAwakeSeconds(cursor.getInt(i + 2));
        serverSleepMonthData.setAvgLightlySeconds(cursor.getInt(i + 3));
        serverSleepMonthData.setAvgDeeplySeconds(cursor.getInt(i + 4));
        serverSleepMonthData.setAvgEyeMovementSeconds(cursor.getInt(i + 5));
        serverSleepMonthData.setAvgTotalSeconds(cursor.getInt(i + 6));
        serverSleepMonthData.setAwakeRatio(cursor.getInt(i + 7));
        serverSleepMonthData.setLightlyRatio(cursor.getInt(i + 8));
        serverSleepMonthData.setDeeplyRatio(cursor.getInt(i + 9));
        serverSleepMonthData.setEyeMovementRatio(cursor.getInt(i + 10));
        int i3 = i + 11;
        serverSleepMonthData.setMonth(cursor.isNull(i3) ? null : cursor.getString(i3));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(ServerSleepMonthData serverSleepMonthData, long j) {
        serverSleepMonthData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(ServerSleepMonthData serverSleepMonthData) {
        if (serverSleepMonthData != null) {
            return serverSleepMonthData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ServerSleepMonthData serverSleepMonthData) {
        return serverSleepMonthData.getId() != null;
    }
}