package com.ido.ble.gps.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.ido.ble.data.manage.database.DaoSession;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class HealthGpsDao extends AbstractDao<HealthGps, Long> {
    public static final String TABLENAME = "HEALTH_GPS";

    public static class Properties {
        public static final Property HealthGpsId = new Property(0, Long.class, "healthGpsId", true, "_id");
        public static final Property DId = new Property(1, Long.TYPE, "dId", false, "D_ID");
        public static final Property Year = new Property(2, Integer.class, "year", false, "YEAR");
        public static final Property Month = new Property(3, Integer.class, "month", false, "MONTH");
        public static final Property Day = new Property(4, Integer.class, "day", false, "DAY");
        public static final Property Hour = new Property(5, Integer.class, "hour", false, "HOUR");
        public static final Property Minute = new Property(6, Integer.class, "minute", false, "MINUTE");
        public static final Property Second = new Property(7, Integer.class, "second", false, "SECOND");
        public static final Property Data_interval = new Property(8, Integer.class, "data_interval", false, "DATA_INTERVAL");
        public static final Property Date = new Property(9, Long.class, "date", false, "DATE");
    }

    public HealthGpsDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HealthGpsDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"HEALTH_GPS\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"D_ID\" INTEGER NOT NULL ,\"YEAR\" INTEGER,\"MONTH\" INTEGER,\"DAY\" INTEGER,\"HOUR\" INTEGER,\"MINUTE\" INTEGER,\"SECOND\" INTEGER,\"DATA_INTERVAL\" INTEGER,\"DATE\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_GPS\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthGps healthGps) {
        sQLiteStatement.clearBindings();
        Long healthGpsId = healthGps.getHealthGpsId();
        if (healthGpsId != null) {
            sQLiteStatement.bindLong(1, healthGpsId.longValue());
        }
        sQLiteStatement.bindLong(2, healthGps.getDId());
        if (healthGps.getYear() != null) {
            sQLiteStatement.bindLong(3, r0.intValue());
        }
        if (healthGps.getMonth() != null) {
            sQLiteStatement.bindLong(4, r0.intValue());
        }
        if (healthGps.getDay() != null) {
            sQLiteStatement.bindLong(5, r0.intValue());
        }
        if (healthGps.getHour() != null) {
            sQLiteStatement.bindLong(6, r0.intValue());
        }
        if (healthGps.getMinute() != null) {
            sQLiteStatement.bindLong(7, r0.intValue());
        }
        if (healthGps.getSecond() != null) {
            sQLiteStatement.bindLong(8, r0.intValue());
        }
        if (healthGps.getData_interval() != null) {
            sQLiteStatement.bindLong(9, r0.intValue());
        }
        Long date = healthGps.getDate();
        if (date != null) {
            sQLiteStatement.bindLong(10, date.longValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthGps healthGps) {
        databaseStatement.clearBindings();
        Long healthGpsId = healthGps.getHealthGpsId();
        if (healthGpsId != null) {
            databaseStatement.bindLong(1, healthGpsId.longValue());
        }
        databaseStatement.bindLong(2, healthGps.getDId());
        if (healthGps.getYear() != null) {
            databaseStatement.bindLong(3, r0.intValue());
        }
        if (healthGps.getMonth() != null) {
            databaseStatement.bindLong(4, r0.intValue());
        }
        if (healthGps.getDay() != null) {
            databaseStatement.bindLong(5, r0.intValue());
        }
        if (healthGps.getHour() != null) {
            databaseStatement.bindLong(6, r0.intValue());
        }
        if (healthGps.getMinute() != null) {
            databaseStatement.bindLong(7, r0.intValue());
        }
        if (healthGps.getSecond() != null) {
            databaseStatement.bindLong(8, r0.intValue());
        }
        if (healthGps.getData_interval() != null) {
            databaseStatement.bindLong(9, r0.intValue());
        }
        Long date = healthGps.getDate();
        if (date != null) {
            databaseStatement.bindLong(10, date.longValue());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthGps healthGps) {
        if (healthGps != null) {
            return healthGps.getHealthGpsId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthGps healthGps) {
        return healthGps.getHealthGpsId() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public HealthGps readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = i + 2;
        Integer numValueOf = cursor.isNull(i3) ? null : Integer.valueOf(cursor.getInt(i3));
        int i4 = i + 3;
        Integer numValueOf2 = cursor.isNull(i4) ? null : Integer.valueOf(cursor.getInt(i4));
        int i5 = i + 4;
        Integer numValueOf3 = cursor.isNull(i5) ? null : Integer.valueOf(cursor.getInt(i5));
        int i6 = i + 5;
        Integer numValueOf4 = cursor.isNull(i6) ? null : Integer.valueOf(cursor.getInt(i6));
        int i7 = i + 6;
        Integer numValueOf5 = cursor.isNull(i7) ? null : Integer.valueOf(cursor.getInt(i7));
        int i8 = i + 7;
        Integer numValueOf6 = cursor.isNull(i8) ? null : Integer.valueOf(cursor.getInt(i8));
        int i9 = i + 8;
        int i10 = i + 9;
        return new HealthGps(lValueOf, j, numValueOf, numValueOf2, numValueOf3, numValueOf4, numValueOf5, numValueOf6, cursor.isNull(i9) ? null : Integer.valueOf(cursor.getInt(i9)), cursor.isNull(i10) ? null : Long.valueOf(cursor.getLong(i10)));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthGps healthGps, int i) {
        int i2 = i + 0;
        healthGps.setHealthGpsId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        healthGps.setDId(cursor.getLong(i + 1));
        int i3 = i + 2;
        healthGps.setYear(cursor.isNull(i3) ? null : Integer.valueOf(cursor.getInt(i3)));
        int i4 = i + 3;
        healthGps.setMonth(cursor.isNull(i4) ? null : Integer.valueOf(cursor.getInt(i4)));
        int i5 = i + 4;
        healthGps.setDay(cursor.isNull(i5) ? null : Integer.valueOf(cursor.getInt(i5)));
        int i6 = i + 5;
        healthGps.setHour(cursor.isNull(i6) ? null : Integer.valueOf(cursor.getInt(i6)));
        int i7 = i + 6;
        healthGps.setMinute(cursor.isNull(i7) ? null : Integer.valueOf(cursor.getInt(i7)));
        int i8 = i + 7;
        healthGps.setSecond(cursor.isNull(i8) ? null : Integer.valueOf(cursor.getInt(i8)));
        int i9 = i + 8;
        healthGps.setData_interval(cursor.isNull(i9) ? null : Integer.valueOf(cursor.getInt(i9)));
        int i10 = i + 9;
        healthGps.setDate(cursor.isNull(i10) ? null : Long.valueOf(cursor.getLong(i10)));
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(HealthGps healthGps, long j) {
        healthGps.setHealthGpsId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}