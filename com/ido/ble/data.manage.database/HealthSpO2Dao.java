package com.ido.ble.data.manage.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import java.util.Date;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class HealthSpO2Dao extends AbstractDao<HealthSpO2, Long> {
    public static final String TABLENAME = "HEALTH_SP_O2";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property DId = new Property(1, Long.TYPE, "dId", false, "D_ID");
        public static final Property Year = new Property(2, Integer.TYPE, "year", false, "YEAR");
        public static final Property Month = new Property(3, Integer.TYPE, "month", false, "MONTH");
        public static final Property Day = new Property(4, Integer.TYPE, "day", false, "DAY");
        public static final Property StartTime = new Property(5, Integer.TYPE, "startTime", false, "START_TIME");
        public static final Property Date = new Property(6, Date.class, "date", false, "DATE");
    }

    public HealthSpO2Dao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HealthSpO2Dao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"HEALTH_SP_O2\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"D_ID\" INTEGER NOT NULL ,\"YEAR\" INTEGER NOT NULL ,\"MONTH\" INTEGER NOT NULL ,\"DAY\" INTEGER NOT NULL ,\"START_TIME\" INTEGER NOT NULL ,\"DATE\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_SP_O2\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthSpO2 healthSpO2) {
        sQLiteStatement.clearBindings();
        Long id = healthSpO2.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, healthSpO2.getDId());
        sQLiteStatement.bindLong(3, healthSpO2.getYear());
        sQLiteStatement.bindLong(4, healthSpO2.getMonth());
        sQLiteStatement.bindLong(5, healthSpO2.getDay());
        sQLiteStatement.bindLong(6, healthSpO2.getStartTime());
        Date date = healthSpO2.getDate();
        if (date != null) {
            sQLiteStatement.bindLong(7, date.getTime());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthSpO2 healthSpO2) {
        databaseStatement.clearBindings();
        Long id = healthSpO2.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, healthSpO2.getDId());
        databaseStatement.bindLong(3, healthSpO2.getYear());
        databaseStatement.bindLong(4, healthSpO2.getMonth());
        databaseStatement.bindLong(5, healthSpO2.getDay());
        databaseStatement.bindLong(6, healthSpO2.getStartTime());
        Date date = healthSpO2.getDate();
        if (date != null) {
            databaseStatement.bindLong(7, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthSpO2 healthSpO2) {
        if (healthSpO2 != null) {
            return healthSpO2.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthSpO2 healthSpO2) {
        return healthSpO2.getId() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public HealthSpO2 readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = cursor.getInt(i + 2);
        int i4 = cursor.getInt(i + 3);
        int i5 = cursor.getInt(i + 4);
        int i6 = cursor.getInt(i + 5);
        int i7 = i + 6;
        return new HealthSpO2(lValueOf, j, i3, i4, i5, i6, cursor.isNull(i7) ? null : new Date(cursor.getLong(i7)));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthSpO2 healthSpO2, int i) {
        int i2 = i + 0;
        healthSpO2.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        healthSpO2.setDId(cursor.getLong(i + 1));
        healthSpO2.setYear(cursor.getInt(i + 2));
        healthSpO2.setMonth(cursor.getInt(i + 3));
        healthSpO2.setDay(cursor.getInt(i + 4));
        healthSpO2.setStartTime(cursor.getInt(i + 5));
        int i3 = i + 6;
        healthSpO2.setDate(cursor.isNull(i3) ? null : new Date(cursor.getLong(i3)));
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
    public final Long updateKeyAfterInsert(HealthSpO2 healthSpO2, long j) {
        healthSpO2.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}