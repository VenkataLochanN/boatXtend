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
public class ActiveTimeDayDataDao extends AbstractDao<ActiveTimeDayData, Long> {
    public static final String TABLENAME = "ACTIVE_TIME_DAY_DATA";
    private DaoSession daoSession;
    private final ConvertListColorItem wearDurationListConverter;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "userId", false, "USER_ID");
        public static final Property Date = new Property(2, String.class, "date", false, "DATE");
        public static final Property SourceMac = new Property(3, String.class, "sourceMac", false, "SOURCE_MAC");
        public static final Property DeviceName = new Property(4, String.class, "deviceName", false, "DEVICE_NAME");
        public static final Property Items = new Property(5, String.class, "items", false, "ITEMS");
        public static final Property TotalDistance = new Property(6, Integer.TYPE, "totalDistance", false, "TOTAL_DISTANCE");
        public static final Property MediumOrHigherSeconds = new Property(7, Integer.TYPE, "mediumOrHigherSeconds", false, "MEDIUM_OR_HIGHER_SECONDS");
        public static final Property TargetTotalSeconds = new Property(8, Integer.TYPE, "targetTotalSeconds", false, "TARGET_TOTAL_SECONDS");
        public static final Property Timestamp = new Property(9, Long.TYPE, "timestamp", false, "TIMESTAMP");
        public static final Property TargetExerciseDuration = new Property(10, Integer.TYPE, "targetExerciseDuration", false, "TARGET_EXERCISE_DURATION");
        public static final Property TotalSeconds = new Property(11, Integer.TYPE, "totalSeconds", false, "TOTAL_SECONDS");
        public static final Property LoadDetail = new Property(12, Boolean.TYPE, "loadDetail", false, "LOAD_DETAIL");
        public static final Property WearDurationList = new Property(13, String.class, "wearDurationList", false, "WEAR_DURATION_LIST");
        public static final Property TotalWearDuration = new Property(14, Integer.TYPE, "totalWearDuration", false, "TOTAL_WEAR_DURATION");
        public static final Property Uploaded = new Property(15, Boolean.TYPE, "uploaded", false, "UPLOADED");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public ActiveTimeDayDataDao(DaoConfig daoConfig) {
        super(daoConfig);
        this.wearDurationListConverter = new ConvertListColorItem();
    }

    public ActiveTimeDayDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.wearDurationListConverter = new ConvertListColorItem();
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"ACTIVE_TIME_DAY_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"DATE\" TEXT,\"SOURCE_MAC\" TEXT,\"DEVICE_NAME\" TEXT,\"ITEMS\" TEXT,\"TOTAL_DISTANCE\" INTEGER NOT NULL ,\"MEDIUM_OR_HIGHER_SECONDS\" INTEGER NOT NULL ,\"TARGET_TOTAL_SECONDS\" INTEGER NOT NULL ,\"TIMESTAMP\" INTEGER NOT NULL ,\"TARGET_EXERCISE_DURATION\" INTEGER NOT NULL ,\"TOTAL_SECONDS\" INTEGER NOT NULL ,\"LOAD_DETAIL\" INTEGER NOT NULL ,\"WEAR_DURATION_LIST\" TEXT,\"TOTAL_WEAR_DURATION\" INTEGER NOT NULL ,\"UPLOADED\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"ACTIVE_TIME_DAY_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ActiveTimeDayData activeTimeDayData) {
        databaseStatement.clearBindings();
        Long id = activeTimeDayData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, activeTimeDayData.getUserId());
        String date = activeTimeDayData.getDate();
        if (date != null) {
            databaseStatement.bindString(3, date);
        }
        String sourceMac = activeTimeDayData.getSourceMac();
        if (sourceMac != null) {
            databaseStatement.bindString(4, sourceMac);
        }
        String deviceName = activeTimeDayData.getDeviceName();
        if (deviceName != null) {
            databaseStatement.bindString(5, deviceName);
        }
        String items = activeTimeDayData.getItems();
        if (items != null) {
            databaseStatement.bindString(6, items);
        }
        databaseStatement.bindLong(7, activeTimeDayData.getTotalDistance());
        databaseStatement.bindLong(8, activeTimeDayData.getMediumOrHigherSeconds());
        databaseStatement.bindLong(9, activeTimeDayData.getTargetTotalSeconds());
        databaseStatement.bindLong(10, activeTimeDayData.getTimestamp());
        databaseStatement.bindLong(11, activeTimeDayData.getTargetExerciseDuration());
        databaseStatement.bindLong(12, activeTimeDayData.getTotalSeconds());
        databaseStatement.bindLong(13, activeTimeDayData.getLoadDetail() ? 1L : 0L);
        List<Integer> wearDurationList = activeTimeDayData.getWearDurationList();
        if (wearDurationList != null) {
            databaseStatement.bindString(14, this.wearDurationListConverter.convertToDatabaseValue(wearDurationList));
        }
        databaseStatement.bindLong(15, activeTimeDayData.getTotalWearDuration());
        databaseStatement.bindLong(16, activeTimeDayData.getUploaded() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ActiveTimeDayData activeTimeDayData) {
        sQLiteStatement.clearBindings();
        Long id = activeTimeDayData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, activeTimeDayData.getUserId());
        String date = activeTimeDayData.getDate();
        if (date != null) {
            sQLiteStatement.bindString(3, date);
        }
        String sourceMac = activeTimeDayData.getSourceMac();
        if (sourceMac != null) {
            sQLiteStatement.bindString(4, sourceMac);
        }
        String deviceName = activeTimeDayData.getDeviceName();
        if (deviceName != null) {
            sQLiteStatement.bindString(5, deviceName);
        }
        String items = activeTimeDayData.getItems();
        if (items != null) {
            sQLiteStatement.bindString(6, items);
        }
        sQLiteStatement.bindLong(7, activeTimeDayData.getTotalDistance());
        sQLiteStatement.bindLong(8, activeTimeDayData.getMediumOrHigherSeconds());
        sQLiteStatement.bindLong(9, activeTimeDayData.getTargetTotalSeconds());
        sQLiteStatement.bindLong(10, activeTimeDayData.getTimestamp());
        sQLiteStatement.bindLong(11, activeTimeDayData.getTargetExerciseDuration());
        sQLiteStatement.bindLong(12, activeTimeDayData.getTotalSeconds());
        sQLiteStatement.bindLong(13, activeTimeDayData.getLoadDetail() ? 1L : 0L);
        List<Integer> wearDurationList = activeTimeDayData.getWearDurationList();
        if (wearDurationList != null) {
            sQLiteStatement.bindString(14, this.wearDurationListConverter.convertToDatabaseValue(wearDurationList));
        }
        sQLiteStatement.bindLong(15, activeTimeDayData.getTotalWearDuration());
        sQLiteStatement.bindLong(16, activeTimeDayData.getUploaded() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(ActiveTimeDayData activeTimeDayData) {
        super.attachEntity(activeTimeDayData);
        activeTimeDayData.__setDaoSession(this.daoSession);
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
    public ActiveTimeDayData readEntity(Cursor cursor, int i) {
        int i2;
        List<Integer> listConvertToEntityProperty2;
        int i3 = i + 0;
        Long lValueOf = cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3));
        long j = cursor.getLong(i + 1);
        int i4 = i + 2;
        String string = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string2 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        String string3 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 5;
        String string4 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = cursor.getInt(i + 6);
        int i9 = cursor.getInt(i + 7);
        int i10 = cursor.getInt(i + 8);
        long j2 = cursor.getLong(i + 9);
        int i11 = cursor.getInt(i + 10);
        int i12 = cursor.getInt(i + 11);
        boolean z = cursor.getShort(i + 12) != 0;
        int i13 = i + 13;
        if (cursor.isNull(i13)) {
            listConvertToEntityProperty2 = null;
            i2 = i11;
        } else {
            i2 = i11;
            listConvertToEntityProperty2 = this.wearDurationListConverter.convertToEntityProperty(cursor.getString(i13));
        }
        return new ActiveTimeDayData(lValueOf, j, string, string2, string3, string4, i8, i9, i10, j2, i2, i12, z, listConvertToEntityProperty2, cursor.getInt(i + 14), cursor.getShort(i + 15) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ActiveTimeDayData activeTimeDayData, int i) {
        int i2 = i + 0;
        activeTimeDayData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        activeTimeDayData.setUserId(cursor.getLong(i + 1));
        int i3 = i + 2;
        activeTimeDayData.setDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        activeTimeDayData.setSourceMac(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 4;
        activeTimeDayData.setDeviceName(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 5;
        activeTimeDayData.setItems(cursor.isNull(i6) ? null : cursor.getString(i6));
        activeTimeDayData.setTotalDistance(cursor.getInt(i + 6));
        activeTimeDayData.setMediumOrHigherSeconds(cursor.getInt(i + 7));
        activeTimeDayData.setTargetTotalSeconds(cursor.getInt(i + 8));
        activeTimeDayData.setTimestamp(cursor.getLong(i + 9));
        activeTimeDayData.setTargetExerciseDuration(cursor.getInt(i + 10));
        activeTimeDayData.setTotalSeconds(cursor.getInt(i + 11));
        activeTimeDayData.setLoadDetail(cursor.getShort(i + 12) != 0);
        int i7 = i + 13;
        activeTimeDayData.setWearDurationList(cursor.isNull(i7) ? null : this.wearDurationListConverter.convertToEntityProperty(cursor.getString(i7)));
        activeTimeDayData.setTotalWearDuration(cursor.getInt(i + 14));
        activeTimeDayData.setUploaded(cursor.getShort(i + 15) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(ActiveTimeDayData activeTimeDayData, long j) {
        activeTimeDayData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(ActiveTimeDayData activeTimeDayData) {
        if (activeTimeDayData != null) {
            return activeTimeDayData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ActiveTimeDayData activeTimeDayData) {
        return activeTimeDayData.getId() != null;
    }
}