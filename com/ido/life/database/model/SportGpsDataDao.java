package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class SportGpsDataDao extends AbstractDao<SportGpsData, Long> {
    public static final String TABLENAME = "SPORT_GPS_DATA";
    private DaoSession daoSession;
    private final ConvertGps gpsDataConverter;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property GpsData = new Property(1, String.class, "gpsData", false, "GPS_DATA");
        public static final Property TimeMillis = new Property(2, Long.TYPE, "timeMillis", false, "TIME_MILLIS");
        public static final Property UserId = new Property(3, Long.TYPE, "userId", false, "USER_ID");
        public static final Property IsDown = new Property(4, Boolean.TYPE, "isDown", false, "IS_DOWN");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public SportGpsDataDao(DaoConfig daoConfig) {
        super(daoConfig);
        this.gpsDataConverter = new ConvertGps();
    }

    public SportGpsDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.gpsDataConverter = new ConvertGps();
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"SPORT_GPS_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"GPS_DATA\" TEXT,\"TIME_MILLIS\" INTEGER NOT NULL ,\"USER_ID\" INTEGER NOT NULL ,\"IS_DOWN\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"SPORT_GPS_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, SportGpsData sportGpsData) {
        databaseStatement.clearBindings();
        Long id = sportGpsData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        SportGps gpsData = sportGpsData.getGpsData();
        if (gpsData != null) {
            databaseStatement.bindString(2, this.gpsDataConverter.convertToDatabaseValue(gpsData));
        }
        databaseStatement.bindLong(3, sportGpsData.getTimeMillis());
        databaseStatement.bindLong(4, sportGpsData.getUserId());
        databaseStatement.bindLong(5, sportGpsData.getIsDown() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, SportGpsData sportGpsData) {
        sQLiteStatement.clearBindings();
        Long id = sportGpsData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        SportGps gpsData = sportGpsData.getGpsData();
        if (gpsData != null) {
            sQLiteStatement.bindString(2, this.gpsDataConverter.convertToDatabaseValue(gpsData));
        }
        sQLiteStatement.bindLong(3, sportGpsData.getTimeMillis());
        sQLiteStatement.bindLong(4, sportGpsData.getUserId());
        sQLiteStatement.bindLong(5, sportGpsData.getIsDown() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(SportGpsData sportGpsData) {
        super.attachEntity(sportGpsData);
        sportGpsData.__setDaoSession(this.daoSession);
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
    public SportGpsData readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        return new SportGpsData(lValueOf, cursor.isNull(i3) ? null : this.gpsDataConverter.convertToEntityProperty(cursor.getString(i3)), cursor.getLong(i + 2), cursor.getLong(i + 3), cursor.getShort(i + 4) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, SportGpsData sportGpsData, int i) {
        int i2 = i + 0;
        sportGpsData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        sportGpsData.setGpsData(cursor.isNull(i3) ? null : this.gpsDataConverter.convertToEntityProperty(cursor.getString(i3)));
        sportGpsData.setTimeMillis(cursor.getLong(i + 2));
        sportGpsData.setUserId(cursor.getLong(i + 3));
        sportGpsData.setIsDown(cursor.getShort(i + 4) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(SportGpsData sportGpsData, long j) {
        sportGpsData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(SportGpsData sportGpsData) {
        if (sportGpsData != null) {
            return sportGpsData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(SportGpsData sportGpsData) {
        return sportGpsData.getId() != null;
    }
}