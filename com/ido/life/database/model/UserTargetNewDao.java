package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class UserTargetNewDao extends AbstractDao<UserTargetNew, Long> {
    public static final String TABLENAME = "USER_TARGET_NEW";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property Date = new Property(2, String.class, "Date", false, "DATE");
        public static final Property Step = new Property(3, Integer.TYPE, "Step", false, "STEP");
        public static final Property SportStep = new Property(4, Integer.TYPE, "SportStep", false, "SPORT_STEP");
        public static final Property Distance = new Property(5, Integer.TYPE, "Distance", false, "DISTANCE");
        public static final Property Calories = new Property(6, Integer.TYPE, "Calories", false, "CALORIES");
        public static final Property SleepTimes = new Property(7, Integer.TYPE, "SleepTimes", false, "SLEEP_TIMES");
        public static final Property SportTimes = new Property(8, Integer.TYPE, "SportTimes", false, "SPORT_TIMES");
        public static final Property Weight = new Property(9, Float.TYPE, "Weight", false, "WEIGHT");
        public static final Property ActivityTime = new Property(10, Integer.TYPE, "ActivityTime", false, "ACTIVITY_TIME");
        public static final Property Walk = new Property(11, Integer.TYPE, "Walk", false, "WALK");
        public static final Property UpdateTime = new Property(12, Long.TYPE, "UpdateTime", false, "UPDATE_TIME");
        public static final Property WeightUnit = new Property(13, Integer.TYPE, "WeightUnit", false, "WEIGHT_UNIT");
        public static final Property HasSyncToDevice = new Property(14, Boolean.TYPE, "HasSyncToDevice", false, "HAS_SYNC_TO_DEVICE");
        public static final Property HasUpload = new Property(15, Boolean.TYPE, "HasUpload", false, "HAS_UPLOAD");
        public static final Property CreateTime = new Property(16, Long.TYPE, "CreateTime", false, "CREATE_TIME");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public UserTargetNewDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public UserTargetNewDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"USER_TARGET_NEW\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"DATE\" TEXT,\"STEP\" INTEGER NOT NULL ,\"SPORT_STEP\" INTEGER NOT NULL ,\"DISTANCE\" INTEGER NOT NULL ,\"CALORIES\" INTEGER NOT NULL ,\"SLEEP_TIMES\" INTEGER NOT NULL ,\"SPORT_TIMES\" INTEGER NOT NULL ,\"WEIGHT\" REAL NOT NULL ,\"ACTIVITY_TIME\" INTEGER NOT NULL ,\"WALK\" INTEGER NOT NULL ,\"UPDATE_TIME\" INTEGER NOT NULL ,\"WEIGHT_UNIT\" INTEGER NOT NULL ,\"HAS_SYNC_TO_DEVICE\" INTEGER NOT NULL ,\"HAS_UPLOAD\" INTEGER NOT NULL ,\"CREATE_TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"USER_TARGET_NEW\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, UserTargetNew userTargetNew) {
        databaseStatement.clearBindings();
        Long id = userTargetNew.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, userTargetNew.getUserId());
        String date = userTargetNew.getDate();
        if (date != null) {
            databaseStatement.bindString(3, date);
        }
        databaseStatement.bindLong(4, userTargetNew.getStep());
        databaseStatement.bindLong(5, userTargetNew.getSportStep());
        databaseStatement.bindLong(6, userTargetNew.getDistance());
        databaseStatement.bindLong(7, userTargetNew.getCalories());
        databaseStatement.bindLong(8, userTargetNew.getSleepTimes());
        databaseStatement.bindLong(9, userTargetNew.getSportTimes());
        databaseStatement.bindDouble(10, userTargetNew.getWeight());
        databaseStatement.bindLong(11, userTargetNew.getActivityTime());
        databaseStatement.bindLong(12, userTargetNew.getWalk());
        databaseStatement.bindLong(13, userTargetNew.getUpdateTime());
        databaseStatement.bindLong(14, userTargetNew.getWeightUnit());
        databaseStatement.bindLong(15, userTargetNew.getHasSyncToDevice() ? 1L : 0L);
        databaseStatement.bindLong(16, userTargetNew.getHasUpload() ? 1L : 0L);
        databaseStatement.bindLong(17, userTargetNew.getCreateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, UserTargetNew userTargetNew) {
        sQLiteStatement.clearBindings();
        Long id = userTargetNew.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, userTargetNew.getUserId());
        String date = userTargetNew.getDate();
        if (date != null) {
            sQLiteStatement.bindString(3, date);
        }
        sQLiteStatement.bindLong(4, userTargetNew.getStep());
        sQLiteStatement.bindLong(5, userTargetNew.getSportStep());
        sQLiteStatement.bindLong(6, userTargetNew.getDistance());
        sQLiteStatement.bindLong(7, userTargetNew.getCalories());
        sQLiteStatement.bindLong(8, userTargetNew.getSleepTimes());
        sQLiteStatement.bindLong(9, userTargetNew.getSportTimes());
        sQLiteStatement.bindDouble(10, userTargetNew.getWeight());
        sQLiteStatement.bindLong(11, userTargetNew.getActivityTime());
        sQLiteStatement.bindLong(12, userTargetNew.getWalk());
        sQLiteStatement.bindLong(13, userTargetNew.getUpdateTime());
        sQLiteStatement.bindLong(14, userTargetNew.getWeightUnit());
        sQLiteStatement.bindLong(15, userTargetNew.getHasSyncToDevice() ? 1L : 0L);
        sQLiteStatement.bindLong(16, userTargetNew.getHasUpload() ? 1L : 0L);
        sQLiteStatement.bindLong(17, userTargetNew.getCreateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(UserTargetNew userTargetNew) {
        super.attachEntity(userTargetNew);
        userTargetNew.__setDaoSession(this.daoSession);
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
    public UserTargetNew readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        int i3 = i + 2;
        return new UserTargetNew(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.getLong(i + 1), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.getInt(i + 3), cursor.getInt(i + 4), cursor.getInt(i + 5), cursor.getInt(i + 6), cursor.getInt(i + 7), cursor.getInt(i + 8), cursor.getFloat(i + 9), cursor.getInt(i + 10), cursor.getInt(i + 11), cursor.getLong(i + 12), cursor.getInt(i + 13), cursor.getShort(i + 14) != 0, cursor.getShort(i + 15) != 0, cursor.getLong(i + 16));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, UserTargetNew userTargetNew, int i) {
        int i2 = i + 0;
        userTargetNew.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        userTargetNew.setUserId(cursor.getLong(i + 1));
        int i3 = i + 2;
        userTargetNew.setDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        userTargetNew.setStep(cursor.getInt(i + 3));
        userTargetNew.setSportStep(cursor.getInt(i + 4));
        userTargetNew.setDistance(cursor.getInt(i + 5));
        userTargetNew.setCalories(cursor.getInt(i + 6));
        userTargetNew.setSleepTimes(cursor.getInt(i + 7));
        userTargetNew.setSportTimes(cursor.getInt(i + 8));
        userTargetNew.setWeight(cursor.getFloat(i + 9));
        userTargetNew.setActivityTime(cursor.getInt(i + 10));
        userTargetNew.setWalk(cursor.getInt(i + 11));
        userTargetNew.setUpdateTime(cursor.getLong(i + 12));
        userTargetNew.setWeightUnit(cursor.getInt(i + 13));
        userTargetNew.setHasSyncToDevice(cursor.getShort(i + 14) != 0);
        userTargetNew.setHasUpload(cursor.getShort(i + 15) != 0);
        userTargetNew.setCreateTime(cursor.getLong(i + 16));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(UserTargetNew userTargetNew, long j) {
        userTargetNew.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(UserTargetNew userTargetNew) {
        if (userTargetNew != null) {
            return userTargetNew.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(UserTargetNew userTargetNew) {
        return userTargetNew.getId() != null;
    }
}