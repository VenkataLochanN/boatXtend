package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class CalorieDayDataDao extends AbstractDao<CalorieDayData, Long> {
    public static final String TABLENAME = "CALORIE_DAY_DATA";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "userId", false, "USER_ID");
        public static final Property Date = new Property(2, String.class, "date", false, "DATE");
        public static final Property TotalCalorie = new Property(3, Integer.TYPE, "totalCalorie", false, "TOTAL_CALORIE");
        public static final Property ActivityCalorie = new Property(4, Integer.TYPE, "activityCalorie", false, "ACTIVITY_CALORIE");
        public static final Property SourceMac = new Property(5, String.class, "sourceMac", false, "SOURCE_MAC");
        public static final Property DeviceName = new Property(6, String.class, "deviceName", false, "DEVICE_NAME");
        public static final Property Items = new Property(7, String.class, "items", false, "ITEMS");
        public static final Property ActivityItems = new Property(8, String.class, "activityItems", false, "ACTIVITY_ITEMS");
        public static final Property Timestamp = new Property(9, Long.TYPE, "timestamp", false, "TIMESTAMP");
        public static final Property TargetCalorie = new Property(10, Integer.TYPE, "targetCalorie", false, "TARGET_CALORIE");
        public static final Property LoadDetail = new Property(11, Boolean.TYPE, "loadDetail", false, "LOAD_DETAIL");
        public static final Property Uploaded = new Property(12, Boolean.TYPE, "uploaded", false, "UPLOADED");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public CalorieDayDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public CalorieDayDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"CALORIE_DAY_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"DATE\" TEXT,\"TOTAL_CALORIE\" INTEGER NOT NULL ,\"ACTIVITY_CALORIE\" INTEGER NOT NULL ,\"SOURCE_MAC\" TEXT,\"DEVICE_NAME\" TEXT,\"ITEMS\" TEXT,\"ACTIVITY_ITEMS\" TEXT,\"TIMESTAMP\" INTEGER NOT NULL ,\"TARGET_CALORIE\" INTEGER NOT NULL ,\"LOAD_DETAIL\" INTEGER NOT NULL ,\"UPLOADED\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"CALORIE_DAY_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, CalorieDayData calorieDayData) {
        databaseStatement.clearBindings();
        Long id = calorieDayData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, calorieDayData.getUserId());
        String date = calorieDayData.getDate();
        if (date != null) {
            databaseStatement.bindString(3, date);
        }
        databaseStatement.bindLong(4, calorieDayData.getTotalCalorie());
        databaseStatement.bindLong(5, calorieDayData.getActivityCalorie());
        String sourceMac = calorieDayData.getSourceMac();
        if (sourceMac != null) {
            databaseStatement.bindString(6, sourceMac);
        }
        String deviceName = calorieDayData.getDeviceName();
        if (deviceName != null) {
            databaseStatement.bindString(7, deviceName);
        }
        String items = calorieDayData.getItems();
        if (items != null) {
            databaseStatement.bindString(8, items);
        }
        String activityItems = calorieDayData.getActivityItems();
        if (activityItems != null) {
            databaseStatement.bindString(9, activityItems);
        }
        databaseStatement.bindLong(10, calorieDayData.getTimestamp());
        databaseStatement.bindLong(11, calorieDayData.getTargetCalorie());
        databaseStatement.bindLong(12, calorieDayData.getLoadDetail() ? 1L : 0L);
        databaseStatement.bindLong(13, calorieDayData.getUploaded() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, CalorieDayData calorieDayData) {
        sQLiteStatement.clearBindings();
        Long id = calorieDayData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, calorieDayData.getUserId());
        String date = calorieDayData.getDate();
        if (date != null) {
            sQLiteStatement.bindString(3, date);
        }
        sQLiteStatement.bindLong(4, calorieDayData.getTotalCalorie());
        sQLiteStatement.bindLong(5, calorieDayData.getActivityCalorie());
        String sourceMac = calorieDayData.getSourceMac();
        if (sourceMac != null) {
            sQLiteStatement.bindString(6, sourceMac);
        }
        String deviceName = calorieDayData.getDeviceName();
        if (deviceName != null) {
            sQLiteStatement.bindString(7, deviceName);
        }
        String items = calorieDayData.getItems();
        if (items != null) {
            sQLiteStatement.bindString(8, items);
        }
        String activityItems = calorieDayData.getActivityItems();
        if (activityItems != null) {
            sQLiteStatement.bindString(9, activityItems);
        }
        sQLiteStatement.bindLong(10, calorieDayData.getTimestamp());
        sQLiteStatement.bindLong(11, calorieDayData.getTargetCalorie());
        sQLiteStatement.bindLong(12, calorieDayData.getLoadDetail() ? 1L : 0L);
        sQLiteStatement.bindLong(13, calorieDayData.getUploaded() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(CalorieDayData calorieDayData) {
        super.attachEntity(calorieDayData);
        calorieDayData.__setDaoSession(this.daoSession);
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
    public CalorieDayData readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = i + 2;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = cursor.getInt(i + 3);
        int i5 = cursor.getInt(i + 4);
        int i6 = i + 5;
        String string2 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 6;
        String string3 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 7;
        String string4 = cursor.isNull(i8) ? null : cursor.getString(i8);
        int i9 = i + 8;
        return new CalorieDayData(lValueOf, j, string, i4, i5, string2, string3, string4, cursor.isNull(i9) ? null : cursor.getString(i9), cursor.getLong(i + 9), cursor.getInt(i + 10), cursor.getShort(i + 11) != 0, cursor.getShort(i + 12) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, CalorieDayData calorieDayData, int i) {
        int i2 = i + 0;
        calorieDayData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        calorieDayData.setUserId(cursor.getLong(i + 1));
        int i3 = i + 2;
        calorieDayData.setDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        calorieDayData.setTotalCalorie(cursor.getInt(i + 3));
        calorieDayData.setActivityCalorie(cursor.getInt(i + 4));
        int i4 = i + 5;
        calorieDayData.setSourceMac(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 6;
        calorieDayData.setDeviceName(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 7;
        calorieDayData.setItems(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 8;
        calorieDayData.setActivityItems(cursor.isNull(i7) ? null : cursor.getString(i7));
        calorieDayData.setTimestamp(cursor.getLong(i + 9));
        calorieDayData.setTargetCalorie(cursor.getInt(i + 10));
        calorieDayData.setLoadDetail(cursor.getShort(i + 11) != 0);
        calorieDayData.setUploaded(cursor.getShort(i + 12) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(CalorieDayData calorieDayData, long j) {
        calorieDayData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(CalorieDayData calorieDayData) {
        if (calorieDayData != null) {
            return calorieDayData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(CalorieDayData calorieDayData) {
        return calorieDayData.getId() != null;
    }
}