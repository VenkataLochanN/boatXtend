package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.android.gms.fitness.data.Field;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class StepDayDataDao extends AbstractDao<StepDayData, Long> {
    public static final String TABLENAME = "STEP_DAY_DATA";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "userId", false, "USER_ID");
        public static final Property Date = new Property(2, String.class, "date", false, "DATE");
        public static final Property Distances = new Property(3, Integer.TYPE, "distances", false, "DISTANCES");
        public static final Property NumSteps = new Property(4, Integer.TYPE, "numSteps", false, "NUM_STEPS");
        public static final Property Calories = new Property(5, Integer.TYPE, Field.NUTRIENT_CALORIES, false, "CALORIES");
        public static final Property TimeOfSeconds = new Property(6, Integer.TYPE, "timeOfSeconds", false, "TIME_OF_SECONDS");
        public static final Property Items = new Property(7, String.class, "items", false, "ITEMS");
        public static final Property SourceMac = new Property(8, String.class, "sourceMac", false, "SOURCE_MAC");
        public static final Property DeviceName = new Property(9, String.class, "deviceName", false, "DEVICE_NAME");
        public static final Property EffectiveSteps = new Property(10, Integer.TYPE, "effectiveSteps", false, "EFFECTIVE_STEPS");
        public static final Property MaxNumSteps = new Property(11, Integer.TYPE, "maxNumSteps", false, "MAX_NUM_STEPS");
        public static final Property TargetSteps = new Property(12, Integer.TYPE, "targetSteps", false, "TARGET_STEPS");
        public static final Property Timestamp = new Property(13, Long.TYPE, "timestamp", false, "TIMESTAMP");
        public static final Property LoadDetail = new Property(14, Boolean.TYPE, "loadDetail", false, "LOAD_DETAIL");
        public static final Property Uploaded = new Property(15, Boolean.TYPE, "uploaded", false, "UPLOADED");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public StepDayDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public StepDayDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"STEP_DAY_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"DATE\" TEXT,\"DISTANCES\" INTEGER NOT NULL ,\"NUM_STEPS\" INTEGER NOT NULL ,\"CALORIES\" INTEGER NOT NULL ,\"TIME_OF_SECONDS\" INTEGER NOT NULL ,\"ITEMS\" TEXT,\"SOURCE_MAC\" TEXT,\"DEVICE_NAME\" TEXT,\"EFFECTIVE_STEPS\" INTEGER NOT NULL ,\"MAX_NUM_STEPS\" INTEGER NOT NULL ,\"TARGET_STEPS\" INTEGER NOT NULL ,\"TIMESTAMP\" INTEGER NOT NULL ,\"LOAD_DETAIL\" INTEGER NOT NULL ,\"UPLOADED\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"STEP_DAY_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, StepDayData stepDayData) {
        databaseStatement.clearBindings();
        Long id = stepDayData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, stepDayData.getUserId());
        String date = stepDayData.getDate();
        if (date != null) {
            databaseStatement.bindString(3, date);
        }
        databaseStatement.bindLong(4, stepDayData.getDistances());
        databaseStatement.bindLong(5, stepDayData.getNumSteps());
        databaseStatement.bindLong(6, stepDayData.getCalories());
        databaseStatement.bindLong(7, stepDayData.getTimeOfSeconds());
        String items = stepDayData.getItems();
        if (items != null) {
            databaseStatement.bindString(8, items);
        }
        String sourceMac = stepDayData.getSourceMac();
        if (sourceMac != null) {
            databaseStatement.bindString(9, sourceMac);
        }
        String deviceName = stepDayData.getDeviceName();
        if (deviceName != null) {
            databaseStatement.bindString(10, deviceName);
        }
        databaseStatement.bindLong(11, stepDayData.getEffectiveSteps());
        databaseStatement.bindLong(12, stepDayData.getMaxNumSteps());
        databaseStatement.bindLong(13, stepDayData.getTargetSteps());
        databaseStatement.bindLong(14, stepDayData.getTimestamp());
        databaseStatement.bindLong(15, stepDayData.getLoadDetail() ? 1L : 0L);
        databaseStatement.bindLong(16, stepDayData.getUploaded() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, StepDayData stepDayData) {
        sQLiteStatement.clearBindings();
        Long id = stepDayData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, stepDayData.getUserId());
        String date = stepDayData.getDate();
        if (date != null) {
            sQLiteStatement.bindString(3, date);
        }
        sQLiteStatement.bindLong(4, stepDayData.getDistances());
        sQLiteStatement.bindLong(5, stepDayData.getNumSteps());
        sQLiteStatement.bindLong(6, stepDayData.getCalories());
        sQLiteStatement.bindLong(7, stepDayData.getTimeOfSeconds());
        String items = stepDayData.getItems();
        if (items != null) {
            sQLiteStatement.bindString(8, items);
        }
        String sourceMac = stepDayData.getSourceMac();
        if (sourceMac != null) {
            sQLiteStatement.bindString(9, sourceMac);
        }
        String deviceName = stepDayData.getDeviceName();
        if (deviceName != null) {
            sQLiteStatement.bindString(10, deviceName);
        }
        sQLiteStatement.bindLong(11, stepDayData.getEffectiveSteps());
        sQLiteStatement.bindLong(12, stepDayData.getMaxNumSteps());
        sQLiteStatement.bindLong(13, stepDayData.getTargetSteps());
        sQLiteStatement.bindLong(14, stepDayData.getTimestamp());
        sQLiteStatement.bindLong(15, stepDayData.getLoadDetail() ? 1L : 0L);
        sQLiteStatement.bindLong(16, stepDayData.getUploaded() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(StepDayData stepDayData) {
        super.attachEntity(stepDayData);
        stepDayData.__setDaoSession(this.daoSession);
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
    public StepDayData readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = i + 2;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = cursor.getInt(i + 3);
        int i5 = cursor.getInt(i + 4);
        int i6 = cursor.getInt(i + 5);
        int i7 = cursor.getInt(i + 6);
        int i8 = i + 7;
        String string2 = cursor.isNull(i8) ? null : cursor.getString(i8);
        int i9 = i + 8;
        String string3 = cursor.isNull(i9) ? null : cursor.getString(i9);
        int i10 = i + 9;
        return new StepDayData(lValueOf, j, string, i4, i5, i6, i7, string2, string3, cursor.isNull(i10) ? null : cursor.getString(i10), cursor.getInt(i + 10), cursor.getInt(i + 11), cursor.getInt(i + 12), cursor.getLong(i + 13), cursor.getShort(i + 14) != 0, cursor.getShort(i + 15) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, StepDayData stepDayData, int i) {
        int i2 = i + 0;
        stepDayData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        stepDayData.setUserId(cursor.getLong(i + 1));
        int i3 = i + 2;
        stepDayData.setDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        stepDayData.setDistances(cursor.getInt(i + 3));
        stepDayData.setNumSteps(cursor.getInt(i + 4));
        stepDayData.setCalories(cursor.getInt(i + 5));
        stepDayData.setTimeOfSeconds(cursor.getInt(i + 6));
        int i4 = i + 7;
        stepDayData.setItems(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 8;
        stepDayData.setSourceMac(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 9;
        stepDayData.setDeviceName(cursor.isNull(i6) ? null : cursor.getString(i6));
        stepDayData.setEffectiveSteps(cursor.getInt(i + 10));
        stepDayData.setMaxNumSteps(cursor.getInt(i + 11));
        stepDayData.setTargetSteps(cursor.getInt(i + 12));
        stepDayData.setTimestamp(cursor.getLong(i + 13));
        stepDayData.setLoadDetail(cursor.getShort(i + 14) != 0);
        stepDayData.setUploaded(cursor.getShort(i + 15) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(StepDayData stepDayData, long j) {
        stepDayData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(StepDayData stepDayData) {
        if (stepDayData != null) {
            return stepDayData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(StepDayData stepDayData) {
        return stepDayData.getId() != null;
    }
}