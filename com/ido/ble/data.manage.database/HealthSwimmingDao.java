package com.ido.ble.data.manage.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.android.gms.fitness.data.Field;
import com.ido.ble.data.manage.database.HealthSwimmingDetail;
import com.ido.ble.event.stat.one.d;
import com.king.zxing.Intents;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class HealthSwimmingDao extends AbstractDao<HealthSwimming, Long> {
    public static final String TABLENAME = "HEALTH_SWIMMING";
    private final HealthSwimmingDetail.HealthSwimmingItemListConvert itemsConverter;

    public static class Properties {
        public static final Property Year = new Property(0, Integer.TYPE, "year", false, "YEAR");
        public static final Property Month = new Property(1, Integer.TYPE, "month", false, "MONTH");
        public static final Property Day = new Property(2, Integer.TYPE, "day", false, "DAY");
        public static final Property Hour = new Property(3, Integer.TYPE, "hour", false, "HOUR");
        public static final Property Minute = new Property(4, Integer.TYPE, "minute", false, "MINUTE");
        public static final Property Second = new Property(5, Integer.TYPE, "second", false, "SECOND");
        public static final Property Type = new Property(6, Integer.TYPE, "type", false, Intents.WifiConnect.TYPE);
        public static final Property Calories = new Property(7, Integer.TYPE, Field.NUTRIENT_CALORIES, false, "CALORIES");
        public static final Property Distance = new Property(8, Integer.TYPE, "distance", false, "DISTANCE");
        public static final Property Duration = new Property(9, Integer.TYPE, d.C, false, "DURATION");
        public static final Property Trips = new Property(10, Integer.TYPE, "trips", false, "TRIPS");
        public static final Property AverageSWOLF = new Property(11, Integer.TYPE, "averageSWOLF", false, "AVERAGE_SWOLF");
        public static final Property TotalStrokesNumber = new Property(12, Integer.TYPE, "totalStrokesNumber", false, "TOTAL_STROKES_NUMBER");
        public static final Property SwimmingPosture = new Property(13, Integer.TYPE, "swimmingPosture", false, "SWIMMING_POSTURE");
        public static final Property PoolDistance = new Property(14, Integer.TYPE, "poolDistance", false, "POOL_DISTANCE");
        public static final Property ConfirmDistance = new Property(15, Integer.TYPE, "confirmDistance", false, "CONFIRM_DISTANCE");
        public static final Property Items = new Property(16, String.class, "items", false, "ITEMS");
        public static final Property DataId = new Property(17, Long.class, "dataId", true, "_id");
        public static final Property DId = new Property(18, Long.TYPE, "dId", false, "D_ID");
        public static final Property Date = new Property(19, Date.class, "date", false, "DATE");
    }

    public HealthSwimmingDao(DaoConfig daoConfig) {
        super(daoConfig);
        this.itemsConverter = new HealthSwimmingDetail.HealthSwimmingItemListConvert();
    }

    public HealthSwimmingDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.itemsConverter = new HealthSwimmingDetail.HealthSwimmingItemListConvert();
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"HEALTH_SWIMMING\" (\"YEAR\" INTEGER NOT NULL ,\"MONTH\" INTEGER NOT NULL ,\"DAY\" INTEGER NOT NULL ,\"HOUR\" INTEGER NOT NULL ,\"MINUTE\" INTEGER NOT NULL ,\"SECOND\" INTEGER NOT NULL ,\"TYPE\" INTEGER NOT NULL ,\"CALORIES\" INTEGER NOT NULL ,\"DISTANCE\" INTEGER NOT NULL ,\"DURATION\" INTEGER NOT NULL ,\"TRIPS\" INTEGER NOT NULL ,\"AVERAGE_SWOLF\" INTEGER NOT NULL ,\"TOTAL_STROKES_NUMBER\" INTEGER NOT NULL ,\"SWIMMING_POSTURE\" INTEGER NOT NULL ,\"POOL_DISTANCE\" INTEGER NOT NULL ,\"CONFIRM_DISTANCE\" INTEGER NOT NULL ,\"ITEMS\" TEXT,\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"D_ID\" INTEGER NOT NULL ,\"DATE\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HEALTH_SWIMMING\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HealthSwimming healthSwimming) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, healthSwimming.getYear());
        sQLiteStatement.bindLong(2, healthSwimming.getMonth());
        sQLiteStatement.bindLong(3, healthSwimming.getDay());
        sQLiteStatement.bindLong(4, healthSwimming.getHour());
        sQLiteStatement.bindLong(5, healthSwimming.getMinute());
        sQLiteStatement.bindLong(6, healthSwimming.getSecond());
        sQLiteStatement.bindLong(7, healthSwimming.getType());
        sQLiteStatement.bindLong(8, healthSwimming.getCalories());
        sQLiteStatement.bindLong(9, healthSwimming.getDistance());
        sQLiteStatement.bindLong(10, healthSwimming.getDuration());
        sQLiteStatement.bindLong(11, healthSwimming.getTrips());
        sQLiteStatement.bindLong(12, healthSwimming.getAverageSWOLF());
        sQLiteStatement.bindLong(13, healthSwimming.getTotalStrokesNumber());
        sQLiteStatement.bindLong(14, healthSwimming.getSwimmingPosture());
        sQLiteStatement.bindLong(15, healthSwimming.getPoolDistance());
        sQLiteStatement.bindLong(16, healthSwimming.getConfirmDistance());
        List<HealthSwimmingDetail> items = healthSwimming.getItems();
        if (items != null) {
            sQLiteStatement.bindString(17, this.itemsConverter.convertToDatabaseValue(items));
        }
        Long dataId = healthSwimming.getDataId();
        if (dataId != null) {
            sQLiteStatement.bindLong(18, dataId.longValue());
        }
        sQLiteStatement.bindLong(19, healthSwimming.getDId());
        Date date = healthSwimming.getDate();
        if (date != null) {
            sQLiteStatement.bindLong(20, date.getTime());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HealthSwimming healthSwimming) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, healthSwimming.getYear());
        databaseStatement.bindLong(2, healthSwimming.getMonth());
        databaseStatement.bindLong(3, healthSwimming.getDay());
        databaseStatement.bindLong(4, healthSwimming.getHour());
        databaseStatement.bindLong(5, healthSwimming.getMinute());
        databaseStatement.bindLong(6, healthSwimming.getSecond());
        databaseStatement.bindLong(7, healthSwimming.getType());
        databaseStatement.bindLong(8, healthSwimming.getCalories());
        databaseStatement.bindLong(9, healthSwimming.getDistance());
        databaseStatement.bindLong(10, healthSwimming.getDuration());
        databaseStatement.bindLong(11, healthSwimming.getTrips());
        databaseStatement.bindLong(12, healthSwimming.getAverageSWOLF());
        databaseStatement.bindLong(13, healthSwimming.getTotalStrokesNumber());
        databaseStatement.bindLong(14, healthSwimming.getSwimmingPosture());
        databaseStatement.bindLong(15, healthSwimming.getPoolDistance());
        databaseStatement.bindLong(16, healthSwimming.getConfirmDistance());
        List<HealthSwimmingDetail> items = healthSwimming.getItems();
        if (items != null) {
            databaseStatement.bindString(17, this.itemsConverter.convertToDatabaseValue(items));
        }
        Long dataId = healthSwimming.getDataId();
        if (dataId != null) {
            databaseStatement.bindLong(18, dataId.longValue());
        }
        databaseStatement.bindLong(19, healthSwimming.getDId());
        Date date = healthSwimming.getDate();
        if (date != null) {
            databaseStatement.bindLong(20, date.getTime());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HealthSwimming healthSwimming) {
        if (healthSwimming != null) {
            return healthSwimming.getDataId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HealthSwimming healthSwimming) {
        return healthSwimming.getDataId() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public HealthSwimming readEntity(Cursor cursor, int i) {
        int i2;
        List<HealthSwimmingDetail> listConvertToEntityProperty;
        int i3 = cursor.getInt(i + 0);
        int i4 = cursor.getInt(i + 1);
        int i5 = cursor.getInt(i + 2);
        int i6 = cursor.getInt(i + 3);
        int i7 = cursor.getInt(i + 4);
        int i8 = cursor.getInt(i + 5);
        int i9 = cursor.getInt(i + 6);
        int i10 = cursor.getInt(i + 7);
        int i11 = cursor.getInt(i + 8);
        int i12 = cursor.getInt(i + 9);
        int i13 = cursor.getInt(i + 10);
        int i14 = cursor.getInt(i + 11);
        int i15 = cursor.getInt(i + 12);
        int i16 = cursor.getInt(i + 13);
        int i17 = cursor.getInt(i + 14);
        int i18 = cursor.getInt(i + 15);
        int i19 = i + 16;
        if (cursor.isNull(i19)) {
            i2 = i16;
            listConvertToEntityProperty = null;
        } else {
            i2 = i16;
            listConvertToEntityProperty = this.itemsConverter.convertToEntityProperty(cursor.getString(i19));
        }
        int i20 = i + 17;
        Long lValueOf = cursor.isNull(i20) ? null : Long.valueOf(cursor.getLong(i20));
        int i21 = i + 19;
        return new HealthSwimming(i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i2, i17, i18, listConvertToEntityProperty, lValueOf, cursor.getLong(i + 18), cursor.isNull(i21) ? null : new Date(cursor.getLong(i21)));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HealthSwimming healthSwimming, int i) {
        healthSwimming.setYear(cursor.getInt(i + 0));
        healthSwimming.setMonth(cursor.getInt(i + 1));
        healthSwimming.setDay(cursor.getInt(i + 2));
        healthSwimming.setHour(cursor.getInt(i + 3));
        healthSwimming.setMinute(cursor.getInt(i + 4));
        healthSwimming.setSecond(cursor.getInt(i + 5));
        healthSwimming.setType(cursor.getInt(i + 6));
        healthSwimming.setCalories(cursor.getInt(i + 7));
        healthSwimming.setDistance(cursor.getInt(i + 8));
        healthSwimming.setDuration(cursor.getInt(i + 9));
        healthSwimming.setTrips(cursor.getInt(i + 10));
        healthSwimming.setAverageSWOLF(cursor.getInt(i + 11));
        healthSwimming.setTotalStrokesNumber(cursor.getInt(i + 12));
        healthSwimming.setSwimmingPosture(cursor.getInt(i + 13));
        healthSwimming.setPoolDistance(cursor.getInt(i + 14));
        healthSwimming.setConfirmDistance(cursor.getInt(i + 15));
        int i2 = i + 16;
        healthSwimming.setItems(cursor.isNull(i2) ? null : this.itemsConverter.convertToEntityProperty(cursor.getString(i2)));
        int i3 = i + 17;
        healthSwimming.setDataId(cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3)));
        healthSwimming.setDId(cursor.getLong(i + 18));
        int i4 = i + 19;
        healthSwimming.setDate(cursor.isNull(i4) ? null : new Date(cursor.getLong(i4)));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 17;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(HealthSwimming healthSwimming, long j) {
        healthSwimming.setDataId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}