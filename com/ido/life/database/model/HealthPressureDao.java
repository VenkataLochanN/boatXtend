package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class HealthPressureDao extends AbstractDao<HealthPressure, Long> {
    public static final String TABLENAME = "HEALTH_PRESSURE";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property Date = new Property(2, String.class, "Date", false, "DATE");
        public static final Property MaxPressure = new Property(3, Integer.TYPE, "MaxPressure", false, "MAX_PRESSURE");
        public static final Property MinPressure = new Property(4, Integer.TYPE, "MinPressure", false, "MIN_PRESSURE");
        public static final Property AvgPressure = new Property(5, Integer.TYPE, "AvgPressure", false, "AVG_PRESSURE");
        public static final Property LastestPressure = new Property(6, Integer.TYPE, "LastestPressure", false, "LASTEST_PRESSURE");
        public static final Property RelaxRatio = new Property(7, Integer.TYPE, "RelaxRatio", false, "RELAX_RATIO");
        public static final Property NormalRatio = new Property(8, Integer.TYPE, "NormalRatio", false, "NORMAL_RATIO");
        public static final Property MediumRatio = new Property(9, Integer.TYPE, "MediumRatio", false, "MEDIUM_RATIO");
        public static final Property HigherRatio = new Property(10, Integer.TYPE, "HigherRatio", false, "HIGHER_RATIO");
        public static final Property SourceMac = new Property(11, String.class, "SourceMac", false, "SOURCE_MAC");
        public static final Property DeviceName = new Property(12, String.class, "DeviceName", false, "DEVICE_NAME");
        public static final Property Items = new Property(13, String.class, "Items", false, "ITEMS");
        public static final Property TimeStamp = new Property(14, Long.TYPE, "TimeStamp", false, "TIME_STAMP");
        public static final Property RelaxCount = new Property(15, Integer.TYPE, "RelaxCount", false, "RELAX_COUNT");
        public static final Property NormalCount = new Property(16, Integer.TYPE, "NormalCount", false, "NORMAL_COUNT");
        public static final Property MediumCount = new Property(17, Integer.TYPE, "MediumCount", false, "MEDIUM_COUNT");
        public static final Property HigherCount = new Property(18, Integer.TYPE, "HigherCount", false, "HIGHER_COUNT");
        public static final Property LoadDetail = new Property(19, Boolean.TYPE, "loadDetail", false, "LOAD_DETAIL");
        public static final Property UploadSuccess = new Property(20, Boolean.TYPE, "UploadSuccess", false, "UPLOAD_SUCCESS");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public HealthPressureDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HealthPressureDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"HEALTH_PRESSURE\" (\"_id\" INTEGER PRIMARY KEY ,\"USER_ID\" INTEGER NOT NULL ,\"DATE\" TEXT,\"MAX_PRESSURE\" INTEGER NOT NULL ,\"MIN_PRESSURE\" INTEGER NOT NULL ,\"AVG_PRESSURE\" INTEGER NOT NULL ,\"LASTEST_PRESSURE\" INTEGER NOT NULL ,\"RELAX_RATIO\" INTEGER NOT NULL ,\"NORMAL_RATIO\" INTEGER NOT NULL ,\"MEDIUM_RATIO\" INTEGER NOT NULL ,\"HIGHER_RATIO\" INTEGER NOT NULL ,\"SOURCE_MAC\" TEXT,\"DEVICE_NAME\" TEXT,\"ITEMS\" TEXT,\"TIME_STAMP\" INTEGER NOT NULL ,\"RELAX_COUNT\" INTEGER NOT NULL ,\"NORMAL_COUNT\" INTEGER NOT NULL ,\"MEDIUM_COUNT\" INTEGER NOT NULL ,\"HIGHER_COUNT\" INTEGER NOT NULL ,\"LOAD_DETAIL\" INTEGER NOT NULL ,\"UPLOAD_SUCCESS\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_PRESSURE\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthPressure healthPressure) {
        databaseStatement.clearBindings();
        Long id = healthPressure.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, healthPressure.getUserId());
        String date = healthPressure.getDate();
        if (date != null) {
            databaseStatement.bindString(3, date);
        }
        databaseStatement.bindLong(4, healthPressure.getMaxPressure());
        databaseStatement.bindLong(5, healthPressure.getMinPressure());
        databaseStatement.bindLong(6, healthPressure.getAvgPressure());
        databaseStatement.bindLong(7, healthPressure.getLastestPressure());
        databaseStatement.bindLong(8, healthPressure.getRelaxRatio());
        databaseStatement.bindLong(9, healthPressure.getNormalRatio());
        databaseStatement.bindLong(10, healthPressure.getMediumRatio());
        databaseStatement.bindLong(11, healthPressure.getHigherRatio());
        String sourceMac = healthPressure.getSourceMac();
        if (sourceMac != null) {
            databaseStatement.bindString(12, sourceMac);
        }
        String deviceName = healthPressure.getDeviceName();
        if (deviceName != null) {
            databaseStatement.bindString(13, deviceName);
        }
        String items = healthPressure.getItems();
        if (items != null) {
            databaseStatement.bindString(14, items);
        }
        databaseStatement.bindLong(15, healthPressure.getTimeStamp());
        databaseStatement.bindLong(16, healthPressure.getRelaxCount());
        databaseStatement.bindLong(17, healthPressure.getNormalCount());
        databaseStatement.bindLong(18, healthPressure.getMediumCount());
        databaseStatement.bindLong(19, healthPressure.getHigherCount());
        databaseStatement.bindLong(20, healthPressure.getLoadDetail() ? 1L : 0L);
        databaseStatement.bindLong(21, healthPressure.getUploadSuccess() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthPressure healthPressure) {
        sQLiteStatement.clearBindings();
        Long id = healthPressure.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, healthPressure.getUserId());
        String date = healthPressure.getDate();
        if (date != null) {
            sQLiteStatement.bindString(3, date);
        }
        sQLiteStatement.bindLong(4, healthPressure.getMaxPressure());
        sQLiteStatement.bindLong(5, healthPressure.getMinPressure());
        sQLiteStatement.bindLong(6, healthPressure.getAvgPressure());
        sQLiteStatement.bindLong(7, healthPressure.getLastestPressure());
        sQLiteStatement.bindLong(8, healthPressure.getRelaxRatio());
        sQLiteStatement.bindLong(9, healthPressure.getNormalRatio());
        sQLiteStatement.bindLong(10, healthPressure.getMediumRatio());
        sQLiteStatement.bindLong(11, healthPressure.getHigherRatio());
        String sourceMac = healthPressure.getSourceMac();
        if (sourceMac != null) {
            sQLiteStatement.bindString(12, sourceMac);
        }
        String deviceName = healthPressure.getDeviceName();
        if (deviceName != null) {
            sQLiteStatement.bindString(13, deviceName);
        }
        String items = healthPressure.getItems();
        if (items != null) {
            sQLiteStatement.bindString(14, items);
        }
        sQLiteStatement.bindLong(15, healthPressure.getTimeStamp());
        sQLiteStatement.bindLong(16, healthPressure.getRelaxCount());
        sQLiteStatement.bindLong(17, healthPressure.getNormalCount());
        sQLiteStatement.bindLong(18, healthPressure.getMediumCount());
        sQLiteStatement.bindLong(19, healthPressure.getHigherCount());
        sQLiteStatement.bindLong(20, healthPressure.getLoadDetail() ? 1L : 0L);
        sQLiteStatement.bindLong(21, healthPressure.getUploadSuccess() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(HealthPressure healthPressure) {
        super.attachEntity(healthPressure);
        healthPressure.__setDaoSession(this.daoSession);
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
    public HealthPressure readEntity(Cursor cursor, int i) {
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
        int i9 = cursor.getInt(i + 8);
        int i10 = cursor.getInt(i + 9);
        int i11 = cursor.getInt(i + 10);
        int i12 = i + 11;
        String string2 = cursor.isNull(i12) ? null : cursor.getString(i12);
        int i13 = i + 12;
        String string3 = cursor.isNull(i13) ? null : cursor.getString(i13);
        int i14 = i + 13;
        return new HealthPressure(lValueOf, j, string, i4, i5, i6, i7, i8, i9, i10, i11, string2, string3, cursor.isNull(i14) ? null : cursor.getString(i14), cursor.getLong(i + 14), cursor.getInt(i + 15), cursor.getInt(i + 16), cursor.getInt(i + 17), cursor.getInt(i + 18), cursor.getShort(i + 19) != 0, cursor.getShort(i + 20) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthPressure healthPressure, int i) {
        int i2 = i + 0;
        healthPressure.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        healthPressure.setUserId(cursor.getLong(i + 1));
        int i3 = i + 2;
        healthPressure.setDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        healthPressure.setMaxPressure(cursor.getInt(i + 3));
        healthPressure.setMinPressure(cursor.getInt(i + 4));
        healthPressure.setAvgPressure(cursor.getInt(i + 5));
        healthPressure.setLastestPressure(cursor.getInt(i + 6));
        healthPressure.setRelaxRatio(cursor.getInt(i + 7));
        healthPressure.setNormalRatio(cursor.getInt(i + 8));
        healthPressure.setMediumRatio(cursor.getInt(i + 9));
        healthPressure.setHigherRatio(cursor.getInt(i + 10));
        int i4 = i + 11;
        healthPressure.setSourceMac(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 12;
        healthPressure.setDeviceName(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 13;
        healthPressure.setItems(cursor.isNull(i6) ? null : cursor.getString(i6));
        healthPressure.setTimeStamp(cursor.getLong(i + 14));
        healthPressure.setRelaxCount(cursor.getInt(i + 15));
        healthPressure.setNormalCount(cursor.getInt(i + 16));
        healthPressure.setMediumCount(cursor.getInt(i + 17));
        healthPressure.setHigherCount(cursor.getInt(i + 18));
        healthPressure.setLoadDetail(cursor.getShort(i + 19) != 0);
        healthPressure.setUploadSuccess(cursor.getShort(i + 20) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(HealthPressure healthPressure, long j) {
        healthPressure.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthPressure healthPressure) {
        if (healthPressure != null) {
            return healthPressure.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthPressure healthPressure) {
        return healthPressure.getId() != null;
    }
}