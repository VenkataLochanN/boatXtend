package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class WalkDayDataDao extends AbstractDao<WalkDayData, Long> {
    public static final String TABLENAME = "WALK_DAY_DATA";
    private DaoSession daoSession;
    private final ConvertListColorItem wearDurationListConverter;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "userId", false, "USER_ID");
        public static final Property Date = new Property(2, String.class, "date", false, "DATE");
        public static final Property ReachSeconds = new Property(3, Integer.TYPE, "reachSeconds", false, "REACH_SECONDS");
        public static final Property TargetSteps = new Property(4, Integer.TYPE, "targetSteps", false, "TARGET_STEPS");
        public static final Property StartTime = new Property(5, String.class, "startTime", false, "START_TIME");
        public static final Property EndTime = new Property(6, String.class, "endTime", false, "END_TIME");
        public static final Property Items = new Property(7, String.class, "items", false, "ITEMS");
        public static final Property SourceMac = new Property(8, String.class, "sourceMac", false, "SOURCE_MAC");
        public static final Property DeviceName = new Property(9, String.class, "deviceName", false, "DEVICE_NAME");
        public static final Property WearDurationList = new Property(10, String.class, "wearDurationList", false, "WEAR_DURATION_LIST");
        public static final Property SedentaryDuration = new Property(11, Integer.TYPE, "sedentaryDuration", false, "SEDENTARY_DURATION");
        public static final Property TargetWalkDuration = new Property(12, Integer.TYPE, "targetWalkDuration", false, "TARGET_WALK_DURATION");
        public static final Property Timestamp = new Property(13, Long.TYPE, "timestamp", false, "TIMESTAMP");
        public static final Property Uploaded = new Property(14, Boolean.TYPE, "uploaded", false, "UPLOADED");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public WalkDayDataDao(DaoConfig daoConfig) {
        super(daoConfig);
        this.wearDurationListConverter = new ConvertListColorItem();
    }

    public WalkDayDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.wearDurationListConverter = new ConvertListColorItem();
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"WALK_DAY_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"DATE\" TEXT,\"REACH_SECONDS\" INTEGER NOT NULL ,\"TARGET_STEPS\" INTEGER NOT NULL ,\"START_TIME\" TEXT,\"END_TIME\" TEXT,\"ITEMS\" TEXT,\"SOURCE_MAC\" TEXT,\"DEVICE_NAME\" TEXT,\"WEAR_DURATION_LIST\" TEXT,\"SEDENTARY_DURATION\" INTEGER NOT NULL ,\"TARGET_WALK_DURATION\" INTEGER NOT NULL ,\"TIMESTAMP\" INTEGER NOT NULL ,\"UPLOADED\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"WALK_DAY_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, WalkDayData walkDayData) {
        databaseStatement.clearBindings();
        Long id = walkDayData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, walkDayData.getUserId());
        String date = walkDayData.getDate();
        if (date != null) {
            databaseStatement.bindString(3, date);
        }
        databaseStatement.bindLong(4, walkDayData.getReachSeconds());
        databaseStatement.bindLong(5, walkDayData.getTargetSteps());
        String startTime = walkDayData.getStartTime();
        if (startTime != null) {
            databaseStatement.bindString(6, startTime);
        }
        String endTime = walkDayData.getEndTime();
        if (endTime != null) {
            databaseStatement.bindString(7, endTime);
        }
        String items = walkDayData.getItems();
        if (items != null) {
            databaseStatement.bindString(8, items);
        }
        String sourceMac = walkDayData.getSourceMac();
        if (sourceMac != null) {
            databaseStatement.bindString(9, sourceMac);
        }
        String deviceName = walkDayData.getDeviceName();
        if (deviceName != null) {
            databaseStatement.bindString(10, deviceName);
        }
        List<Integer> wearDurationList = walkDayData.getWearDurationList();
        if (wearDurationList != null) {
            databaseStatement.bindString(11, this.wearDurationListConverter.convertToDatabaseValue(wearDurationList));
        }
        databaseStatement.bindLong(12, walkDayData.getSedentaryDuration());
        databaseStatement.bindLong(13, walkDayData.getTargetWalkDuration());
        databaseStatement.bindLong(14, walkDayData.getTimestamp());
        databaseStatement.bindLong(15, walkDayData.getUploaded() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, WalkDayData walkDayData) {
        sQLiteStatement.clearBindings();
        Long id = walkDayData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, walkDayData.getUserId());
        String date = walkDayData.getDate();
        if (date != null) {
            sQLiteStatement.bindString(3, date);
        }
        sQLiteStatement.bindLong(4, walkDayData.getReachSeconds());
        sQLiteStatement.bindLong(5, walkDayData.getTargetSteps());
        String startTime = walkDayData.getStartTime();
        if (startTime != null) {
            sQLiteStatement.bindString(6, startTime);
        }
        String endTime = walkDayData.getEndTime();
        if (endTime != null) {
            sQLiteStatement.bindString(7, endTime);
        }
        String items = walkDayData.getItems();
        if (items != null) {
            sQLiteStatement.bindString(8, items);
        }
        String sourceMac = walkDayData.getSourceMac();
        if (sourceMac != null) {
            sQLiteStatement.bindString(9, sourceMac);
        }
        String deviceName = walkDayData.getDeviceName();
        if (deviceName != null) {
            sQLiteStatement.bindString(10, deviceName);
        }
        List<Integer> wearDurationList = walkDayData.getWearDurationList();
        if (wearDurationList != null) {
            sQLiteStatement.bindString(11, this.wearDurationListConverter.convertToDatabaseValue(wearDurationList));
        }
        sQLiteStatement.bindLong(12, walkDayData.getSedentaryDuration());
        sQLiteStatement.bindLong(13, walkDayData.getTargetWalkDuration());
        sQLiteStatement.bindLong(14, walkDayData.getTimestamp());
        sQLiteStatement.bindLong(15, walkDayData.getUploaded() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(WalkDayData walkDayData) {
        super.attachEntity(walkDayData);
        walkDayData.__setDaoSession(this.daoSession);
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
    public WalkDayData readEntity(Cursor cursor, int i) {
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
        String string5 = cursor.isNull(i9) ? null : cursor.getString(i9);
        int i10 = i + 9;
        String string6 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 10;
        return new WalkDayData(lValueOf, j, string, i4, i5, string2, string3, string4, string5, string6, cursor.isNull(i11) ? null : this.wearDurationListConverter.convertToEntityProperty(cursor.getString(i11)), cursor.getInt(i + 11), cursor.getInt(i + 12), cursor.getLong(i + 13), cursor.getShort(i + 14) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, WalkDayData walkDayData, int i) {
        int i2 = i + 0;
        walkDayData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        walkDayData.setUserId(cursor.getLong(i + 1));
        int i3 = i + 2;
        walkDayData.setDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        walkDayData.setReachSeconds(cursor.getInt(i + 3));
        walkDayData.setTargetSteps(cursor.getInt(i + 4));
        int i4 = i + 5;
        walkDayData.setStartTime(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 6;
        walkDayData.setEndTime(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 7;
        walkDayData.setItems(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 8;
        walkDayData.setSourceMac(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 9;
        walkDayData.setDeviceName(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 10;
        walkDayData.setWearDurationList(cursor.isNull(i9) ? null : this.wearDurationListConverter.convertToEntityProperty(cursor.getString(i9)));
        walkDayData.setSedentaryDuration(cursor.getInt(i + 11));
        walkDayData.setTargetWalkDuration(cursor.getInt(i + 12));
        walkDayData.setTimestamp(cursor.getLong(i + 13));
        walkDayData.setUploaded(cursor.getShort(i + 14) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(WalkDayData walkDayData, long j) {
        walkDayData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(WalkDayData walkDayData) {
        if (walkDayData != null) {
            return walkDayData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(WalkDayData walkDayData) {
        return walkDayData.getId() != null;
    }
}