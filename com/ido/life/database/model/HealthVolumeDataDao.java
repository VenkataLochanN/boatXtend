package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class HealthVolumeDataDao extends AbstractDao<HealthVolumeData, Long> {
    public static final String TABLENAME = "HEALTH_VOLUME_DATA";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Timestamp = new Property(1, Long.TYPE, "timestamp", false, "TIMESTAMP");
        public static final Property Date = new Property(2, String.class, "date", false, "DATE");
        public static final Property UserId = new Property(3, Long.TYPE, "userId", false, "USER_ID");
        public static final Property AvgValue = new Property(4, Integer.TYPE, "avgValue", false, "AVG_VALUE");
        public static final Property MaxValue = new Property(5, Integer.TYPE, "maxValue", false, "MAX_VALUE");
        public static final Property MinValue = new Property(6, Integer.TYPE, "minValue", false, "MIN_VALUE");
        public static final Property LatestValue = new Property(7, Integer.TYPE, "latestValue", false, "LATEST_VALUE");
        public static final Property Interval = new Property(8, Integer.TYPE, "interval", false, "INTERVAL");
        public static final Property SourceMac = new Property(9, String.class, "sourceMac", false, "SOURCE_MAC");
        public static final Property DeviceName = new Property(10, String.class, "deviceName", false, "DEVICE_NAME");
        public static final Property Items = new Property(11, String.class, "items", false, "ITEMS");
        public static final Property TotalSeconds = new Property(12, Integer.TYPE, "totalSeconds", false, "TOTAL_SECONDS");
        public static final Property SuperHighLevelSeconds = new Property(13, Integer.TYPE, "superHighLevelSeconds", false, "SUPER_HIGH_LEVEL_SECONDS");
        public static final Property HighLevelSeconds = new Property(14, Integer.TYPE, "highLevelSeconds", false, "HIGH_LEVEL_SECONDS");
        public static final Property NormalLevelSeconds = new Property(15, Integer.TYPE, "normalLevelSeconds", false, "NORMAL_LEVEL_SECONDS");
        public static final Property LowLevelSeconds = new Property(16, Integer.TYPE, "lowLevelSeconds", false, "LOW_LEVEL_SECONDS");
        public static final Property HasUpdate = new Property(17, Boolean.TYPE, "hasUpdate", false, "HAS_UPDATE");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public HealthVolumeDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HealthVolumeDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"HEALTH_VOLUME_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TIMESTAMP\" INTEGER NOT NULL ,\"DATE\" TEXT,\"USER_ID\" INTEGER NOT NULL ,\"AVG_VALUE\" INTEGER NOT NULL ,\"MAX_VALUE\" INTEGER NOT NULL ,\"MIN_VALUE\" INTEGER NOT NULL ,\"LATEST_VALUE\" INTEGER NOT NULL ,\"INTERVAL\" INTEGER NOT NULL ,\"SOURCE_MAC\" TEXT,\"DEVICE_NAME\" TEXT,\"ITEMS\" TEXT,\"TOTAL_SECONDS\" INTEGER NOT NULL ,\"SUPER_HIGH_LEVEL_SECONDS\" INTEGER NOT NULL ,\"HIGH_LEVEL_SECONDS\" INTEGER NOT NULL ,\"NORMAL_LEVEL_SECONDS\" INTEGER NOT NULL ,\"LOW_LEVEL_SECONDS\" INTEGER NOT NULL ,\"HAS_UPDATE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_VOLUME_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthVolumeData healthVolumeData) {
        databaseStatement.clearBindings();
        Long id = healthVolumeData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, healthVolumeData.getTimestamp());
        String date = healthVolumeData.getDate();
        if (date != null) {
            databaseStatement.bindString(3, date);
        }
        databaseStatement.bindLong(4, healthVolumeData.getUserId());
        databaseStatement.bindLong(5, healthVolumeData.getAvgValue());
        databaseStatement.bindLong(6, healthVolumeData.getMaxValue());
        databaseStatement.bindLong(7, healthVolumeData.getMinValue());
        databaseStatement.bindLong(8, healthVolumeData.getLatestValue());
        databaseStatement.bindLong(9, healthVolumeData.getInterval());
        String sourceMac = healthVolumeData.getSourceMac();
        if (sourceMac != null) {
            databaseStatement.bindString(10, sourceMac);
        }
        String deviceName = healthVolumeData.getDeviceName();
        if (deviceName != null) {
            databaseStatement.bindString(11, deviceName);
        }
        String items = healthVolumeData.getItems();
        if (items != null) {
            databaseStatement.bindString(12, items);
        }
        databaseStatement.bindLong(13, healthVolumeData.getTotalSeconds());
        databaseStatement.bindLong(14, healthVolumeData.getSuperHighLevelSeconds());
        databaseStatement.bindLong(15, healthVolumeData.getHighLevelSeconds());
        databaseStatement.bindLong(16, healthVolumeData.getNormalLevelSeconds());
        databaseStatement.bindLong(17, healthVolumeData.getLowLevelSeconds());
        databaseStatement.bindLong(18, healthVolumeData.getHasUpdate() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthVolumeData healthVolumeData) {
        sQLiteStatement.clearBindings();
        Long id = healthVolumeData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, healthVolumeData.getTimestamp());
        String date = healthVolumeData.getDate();
        if (date != null) {
            sQLiteStatement.bindString(3, date);
        }
        sQLiteStatement.bindLong(4, healthVolumeData.getUserId());
        sQLiteStatement.bindLong(5, healthVolumeData.getAvgValue());
        sQLiteStatement.bindLong(6, healthVolumeData.getMaxValue());
        sQLiteStatement.bindLong(7, healthVolumeData.getMinValue());
        sQLiteStatement.bindLong(8, healthVolumeData.getLatestValue());
        sQLiteStatement.bindLong(9, healthVolumeData.getInterval());
        String sourceMac = healthVolumeData.getSourceMac();
        if (sourceMac != null) {
            sQLiteStatement.bindString(10, sourceMac);
        }
        String deviceName = healthVolumeData.getDeviceName();
        if (deviceName != null) {
            sQLiteStatement.bindString(11, deviceName);
        }
        String items = healthVolumeData.getItems();
        if (items != null) {
            sQLiteStatement.bindString(12, items);
        }
        sQLiteStatement.bindLong(13, healthVolumeData.getTotalSeconds());
        sQLiteStatement.bindLong(14, healthVolumeData.getSuperHighLevelSeconds());
        sQLiteStatement.bindLong(15, healthVolumeData.getHighLevelSeconds());
        sQLiteStatement.bindLong(16, healthVolumeData.getNormalLevelSeconds());
        sQLiteStatement.bindLong(17, healthVolumeData.getLowLevelSeconds());
        sQLiteStatement.bindLong(18, healthVolumeData.getHasUpdate() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(HealthVolumeData healthVolumeData) {
        super.attachEntity(healthVolumeData);
        healthVolumeData.__setDaoSession(this.daoSession);
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
    public HealthVolumeData readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = i + 2;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        long j2 = cursor.getLong(i + 3);
        int i4 = cursor.getInt(i + 4);
        int i5 = cursor.getInt(i + 5);
        int i6 = cursor.getInt(i + 6);
        int i7 = cursor.getInt(i + 7);
        int i8 = cursor.getInt(i + 8);
        int i9 = i + 9;
        String string2 = cursor.isNull(i9) ? null : cursor.getString(i9);
        int i10 = i + 10;
        String string3 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 11;
        return new HealthVolumeData(lValueOf, j, string, j2, i4, i5, i6, i7, i8, string2, string3, cursor.isNull(i11) ? null : cursor.getString(i11), cursor.getInt(i + 12), cursor.getInt(i + 13), cursor.getInt(i + 14), cursor.getInt(i + 15), cursor.getInt(i + 16), cursor.getShort(i + 17) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthVolumeData healthVolumeData, int i) {
        int i2 = i + 0;
        healthVolumeData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        healthVolumeData.setTimestamp(cursor.getLong(i + 1));
        int i3 = i + 2;
        healthVolumeData.setDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        healthVolumeData.setUserId(cursor.getLong(i + 3));
        healthVolumeData.setAvgValue(cursor.getInt(i + 4));
        healthVolumeData.setMaxValue(cursor.getInt(i + 5));
        healthVolumeData.setMinValue(cursor.getInt(i + 6));
        healthVolumeData.setLatestValue(cursor.getInt(i + 7));
        healthVolumeData.setInterval(cursor.getInt(i + 8));
        int i4 = i + 9;
        healthVolumeData.setSourceMac(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 10;
        healthVolumeData.setDeviceName(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 11;
        healthVolumeData.setItems(cursor.isNull(i6) ? null : cursor.getString(i6));
        healthVolumeData.setTotalSeconds(cursor.getInt(i + 12));
        healthVolumeData.setSuperHighLevelSeconds(cursor.getInt(i + 13));
        healthVolumeData.setHighLevelSeconds(cursor.getInt(i + 14));
        healthVolumeData.setNormalLevelSeconds(cursor.getInt(i + 15));
        healthVolumeData.setLowLevelSeconds(cursor.getInt(i + 16));
        healthVolumeData.setHasUpdate(cursor.getShort(i + 17) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(HealthVolumeData healthVolumeData, long j) {
        healthVolumeData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthVolumeData healthVolumeData) {
        if (healthVolumeData != null) {
            return healthVolumeData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthVolumeData healthVolumeData) {
        return healthVolumeData.getId() != null;
    }
}