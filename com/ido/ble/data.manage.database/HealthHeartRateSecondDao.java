package com.ido.ble.data.manage.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.ido.ble.data.manage.database.HealthHeartRateHighLowItem;
import com.ido.ble.data.manage.database.HealthHeartRateSecondItem;
import com.ido.ble.data.manage.database.HealthHeartRateSecond_Interval;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class HealthHeartRateSecondDao extends AbstractDao<HealthHeartRateSecond, Long> {
    public static final String TABLENAME = "HEALTH_HEART_RATE_SECOND";
    private final HealthHeartRateSecond_Interval.HealthHeartRateSecondIntervalConvert hrIntervalConverter;
    private final HealthHeartRateHighLowItem.HealthHeartRateHighLowItemConvert hr_dataConverter;
    private final HealthHeartRateSecondItem.HealthHeartRateSecondItemConvert itemsConverter;

    public static class Properties {
        public static final Property HrInterval = new Property(0, String.class, "hrInterval", false, "HR_INTERVAL");
        public static final Property Items = new Property(1, String.class, "items", false, "ITEMS");
        public static final Property Hr_data = new Property(2, String.class, "hr_data", false, "HR_DATA");
        public static final Property Year = new Property(3, Integer.TYPE, "year", false, "YEAR");
        public static final Property Month = new Property(4, Integer.TYPE, "month", false, "MONTH");
        public static final Property Day = new Property(5, Integer.TYPE, "day", false, "DAY");
        public static final Property StartTime = new Property(6, Integer.TYPE, "startTime", false, "START_TIME");
        public static final Property SilentHR = new Property(7, Integer.TYPE, "silentHR", false, "SILENT_HR");
        public static final Property DataId = new Property(8, Long.class, "dataId", true, "_id");
        public static final Property DId = new Property(9, Long.TYPE, "dId", false, "D_ID");
        public static final Property Date = new Property(10, Date.class, "date", false, "DATE");
        public static final Property Hr_data_count = new Property(11, Integer.TYPE, "hr_data_count", false, "HR_DATA_COUNT");
    }

    public HealthHeartRateSecondDao(DaoConfig daoConfig) {
        super(daoConfig);
        this.hrIntervalConverter = new HealthHeartRateSecond_Interval.HealthHeartRateSecondIntervalConvert();
        this.itemsConverter = new HealthHeartRateSecondItem.HealthHeartRateSecondItemConvert();
        this.hr_dataConverter = new HealthHeartRateHighLowItem.HealthHeartRateHighLowItemConvert();
    }

    public HealthHeartRateSecondDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.hrIntervalConverter = new HealthHeartRateSecond_Interval.HealthHeartRateSecondIntervalConvert();
        this.itemsConverter = new HealthHeartRateSecondItem.HealthHeartRateSecondItemConvert();
        this.hr_dataConverter = new HealthHeartRateHighLowItem.HealthHeartRateHighLowItemConvert();
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"HEALTH_HEART_RATE_SECOND\" (\"HR_INTERVAL\" TEXT,\"ITEMS\" TEXT,\"HR_DATA\" TEXT,\"YEAR\" INTEGER NOT NULL ,\"MONTH\" INTEGER NOT NULL ,\"DAY\" INTEGER NOT NULL ,\"START_TIME\" INTEGER NOT NULL ,\"SILENT_HR\" INTEGER NOT NULL ,\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"D_ID\" INTEGER NOT NULL ,\"DATE\" INTEGER,\"HR_DATA_COUNT\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_HEART_RATE_SECOND\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthHeartRateSecond healthHeartRateSecond) {
        sQLiteStatement.clearBindings();
        List<HealthHeartRateSecond_Interval> hrInterval = healthHeartRateSecond.getHrInterval();
        if (hrInterval != null) {
            sQLiteStatement.bindString(1, this.hrIntervalConverter.convertToDatabaseValue(hrInterval));
        }
        List<HealthHeartRateSecondItem> items = healthHeartRateSecond.getItems();
        if (items != null) {
            sQLiteStatement.bindString(2, this.itemsConverter.convertToDatabaseValue(items));
        }
        List<HealthHeartRateHighLowItem> hr_data = healthHeartRateSecond.getHr_data();
        if (hr_data != null) {
            sQLiteStatement.bindString(3, this.hr_dataConverter.convertToDatabaseValue(hr_data));
        }
        sQLiteStatement.bindLong(4, healthHeartRateSecond.getYear());
        sQLiteStatement.bindLong(5, healthHeartRateSecond.getMonth());
        sQLiteStatement.bindLong(6, healthHeartRateSecond.getDay());
        sQLiteStatement.bindLong(7, healthHeartRateSecond.getStartTime());
        sQLiteStatement.bindLong(8, healthHeartRateSecond.getSilentHR());
        Long dataId = healthHeartRateSecond.getDataId();
        if (dataId != null) {
            sQLiteStatement.bindLong(9, dataId.longValue());
        }
        sQLiteStatement.bindLong(10, healthHeartRateSecond.getDId());
        Date date = healthHeartRateSecond.getDate();
        if (date != null) {
            sQLiteStatement.bindLong(11, date.getTime());
        }
        sQLiteStatement.bindLong(12, healthHeartRateSecond.getHr_data_count());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthHeartRateSecond healthHeartRateSecond) {
        databaseStatement.clearBindings();
        List<HealthHeartRateSecond_Interval> hrInterval = healthHeartRateSecond.getHrInterval();
        if (hrInterval != null) {
            databaseStatement.bindString(1, this.hrIntervalConverter.convertToDatabaseValue(hrInterval));
        }
        List<HealthHeartRateSecondItem> items = healthHeartRateSecond.getItems();
        if (items != null) {
            databaseStatement.bindString(2, this.itemsConverter.convertToDatabaseValue(items));
        }
        List<HealthHeartRateHighLowItem> hr_data = healthHeartRateSecond.getHr_data();
        if (hr_data != null) {
            databaseStatement.bindString(3, this.hr_dataConverter.convertToDatabaseValue(hr_data));
        }
        databaseStatement.bindLong(4, healthHeartRateSecond.getYear());
        databaseStatement.bindLong(5, healthHeartRateSecond.getMonth());
        databaseStatement.bindLong(6, healthHeartRateSecond.getDay());
        databaseStatement.bindLong(7, healthHeartRateSecond.getStartTime());
        databaseStatement.bindLong(8, healthHeartRateSecond.getSilentHR());
        Long dataId = healthHeartRateSecond.getDataId();
        if (dataId != null) {
            databaseStatement.bindLong(9, dataId.longValue());
        }
        databaseStatement.bindLong(10, healthHeartRateSecond.getDId());
        Date date = healthHeartRateSecond.getDate();
        if (date != null) {
            databaseStatement.bindLong(11, date.getTime());
        }
        databaseStatement.bindLong(12, healthHeartRateSecond.getHr_data_count());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthHeartRateSecond healthHeartRateSecond) {
        if (healthHeartRateSecond != null) {
            return healthHeartRateSecond.getDataId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthHeartRateSecond healthHeartRateSecond) {
        return healthHeartRateSecond.getDataId() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public HealthHeartRateSecond readEntity(Cursor cursor, int i) {
        long j;
        int i2 = i + 0;
        Date date = null;
        List<HealthHeartRateSecond_Interval> listConvertToEntityProperty = cursor.isNull(i2) ? null : this.hrIntervalConverter.convertToEntityProperty(cursor.getString(i2));
        int i3 = i + 1;
        List<HealthHeartRateSecondItem> listConvertToEntityProperty2 = cursor.isNull(i3) ? null : this.itemsConverter.convertToEntityProperty(cursor.getString(i3));
        int i4 = i + 2;
        List<HealthHeartRateHighLowItem> listConvertToEntityProperty3 = cursor.isNull(i4) ? null : this.hr_dataConverter.convertToEntityProperty(cursor.getString(i4));
        int i5 = cursor.getInt(i + 3);
        int i6 = cursor.getInt(i + 4);
        int i7 = cursor.getInt(i + 5);
        int i8 = cursor.getInt(i + 6);
        int i9 = cursor.getInt(i + 7);
        int i10 = i + 8;
        Long lValueOf = cursor.isNull(i10) ? null : Long.valueOf(cursor.getLong(i10));
        long j2 = cursor.getLong(i + 9);
        int i11 = i + 10;
        if (cursor.isNull(i11)) {
            j = j2;
        } else {
            j = j2;
            date = new Date(cursor.getLong(i11));
        }
        return new HealthHeartRateSecond(listConvertToEntityProperty, listConvertToEntityProperty2, listConvertToEntityProperty3, i5, i6, i7, i8, i9, lValueOf, j, date, cursor.getInt(i + 11));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthHeartRateSecond healthHeartRateSecond, int i) {
        int i2 = i + 0;
        healthHeartRateSecond.setHrInterval(cursor.isNull(i2) ? null : this.hrIntervalConverter.convertToEntityProperty(cursor.getString(i2)));
        int i3 = i + 1;
        healthHeartRateSecond.setItems(cursor.isNull(i3) ? null : this.itemsConverter.convertToEntityProperty(cursor.getString(i3)));
        int i4 = i + 2;
        healthHeartRateSecond.setHr_data(cursor.isNull(i4) ? null : this.hr_dataConverter.convertToEntityProperty(cursor.getString(i4)));
        healthHeartRateSecond.setYear(cursor.getInt(i + 3));
        healthHeartRateSecond.setMonth(cursor.getInt(i + 4));
        healthHeartRateSecond.setDay(cursor.getInt(i + 5));
        healthHeartRateSecond.setStartTime(cursor.getInt(i + 6));
        healthHeartRateSecond.setSilentHR(cursor.getInt(i + 7));
        int i5 = i + 8;
        healthHeartRateSecond.setDataId(cursor.isNull(i5) ? null : Long.valueOf(cursor.getLong(i5)));
        healthHeartRateSecond.setDId(cursor.getLong(i + 9));
        int i6 = i + 10;
        healthHeartRateSecond.setDate(cursor.isNull(i6) ? null : new Date(cursor.getLong(i6)));
        healthHeartRateSecond.setHr_data_count(cursor.getInt(i + 11));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 8;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(HealthHeartRateSecond healthHeartRateSecond, long j) {
        healthHeartRateSecond.setDataId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}