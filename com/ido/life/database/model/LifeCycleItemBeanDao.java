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
public class LifeCycleItemBeanDao extends AbstractDao<LifeCycleItemBean, Long> {
    public static final String TABLENAME = "LifeCycle";
    private final LifeCycleItemConverter ItemListConverter;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property Month = new Property(2, String.class, "Month", false, "MONTH");
        public static final Property MensesCycle = new Property(3, Integer.TYPE, "MensesCycle", false, "MENSES_CYCLE");
        public static final Property MensesDays = new Property(4, Integer.TYPE, "MensesDays", false, "MENSES_DAYS");
        public static final Property SourceMac = new Property(5, String.class, "SourceMac", false, "SOURCE_MAC");
        public static final Property DeviceName = new Property(6, String.class, "DeviceName", false, "DEVICE_NAME");
        public static final Property ItemList = new Property(7, String.class, "ItemList", false, "ITEM_LIST");
        public static final Property TimeStamp = new Property(8, Long.TYPE, "TimeStamp", false, "TIME_STAMP");
        public static final Property MensesStartDay = new Property(9, Integer.TYPE, "MensesStartDay", false, "MENSES_START_DAY");
        public static final Property OvulationDay = new Property(10, Integer.TYPE, "OvulationDay", false, "OVULATION_DAY");
        public static final Property PregnancyDayBeforeRemind = new Property(11, Integer.TYPE, "PregnancyDayBeforeRemind", false, "PREGNANCY_DAY_BEFORE_REMIND");
        public static final Property ReminderTime = new Property(12, String.class, "ReminderTime", false, "REMINDER_TIME");
        public static final Property Upload = new Property(13, Boolean.TYPE, "Upload", false, "UPLOAD");
        public static final Property NeedSyncToDevice = new Property(14, Boolean.TYPE, "NeedSyncToDevice", false, "NEED_SYNC_TO_DEVICE");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public LifeCycleItemBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
        this.ItemListConverter = new LifeCycleItemConverter();
    }

    public LifeCycleItemBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.ItemListConverter = new LifeCycleItemConverter();
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"LifeCycle\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"MONTH\" TEXT,\"MENSES_CYCLE\" INTEGER NOT NULL ,\"MENSES_DAYS\" INTEGER NOT NULL ,\"SOURCE_MAC\" TEXT,\"DEVICE_NAME\" TEXT,\"ITEM_LIST\" TEXT,\"TIME_STAMP\" INTEGER NOT NULL ,\"MENSES_START_DAY\" INTEGER NOT NULL ,\"OVULATION_DAY\" INTEGER NOT NULL ,\"PREGNANCY_DAY_BEFORE_REMIND\" INTEGER NOT NULL ,\"REMINDER_TIME\" TEXT,\"UPLOAD\" INTEGER NOT NULL ,\"NEED_SYNC_TO_DEVICE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"LifeCycle\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, LifeCycleItemBean lifeCycleItemBean) {
        databaseStatement.clearBindings();
        Long id = lifeCycleItemBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, lifeCycleItemBean.getUserId());
        String month = lifeCycleItemBean.getMonth();
        if (month != null) {
            databaseStatement.bindString(3, month);
        }
        databaseStatement.bindLong(4, lifeCycleItemBean.getMensesCycle());
        databaseStatement.bindLong(5, lifeCycleItemBean.getMensesDays());
        String sourceMac = lifeCycleItemBean.getSourceMac();
        if (sourceMac != null) {
            databaseStatement.bindString(6, sourceMac);
        }
        String deviceName = lifeCycleItemBean.getDeviceName();
        if (deviceName != null) {
            databaseStatement.bindString(7, deviceName);
        }
        List<List<Integer>> itemList = lifeCycleItemBean.getItemList();
        if (itemList != null) {
            databaseStatement.bindString(8, this.ItemListConverter.convertToDatabaseValue(itemList));
        }
        databaseStatement.bindLong(9, lifeCycleItemBean.getTimeStamp());
        databaseStatement.bindLong(10, lifeCycleItemBean.getMensesStartDay());
        databaseStatement.bindLong(11, lifeCycleItemBean.getOvulationDay());
        databaseStatement.bindLong(12, lifeCycleItemBean.getPregnancyDayBeforeRemind());
        String reminderTime = lifeCycleItemBean.getReminderTime();
        if (reminderTime != null) {
            databaseStatement.bindString(13, reminderTime);
        }
        databaseStatement.bindLong(14, lifeCycleItemBean.getUpload() ? 1L : 0L);
        databaseStatement.bindLong(15, lifeCycleItemBean.getNeedSyncToDevice() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, LifeCycleItemBean lifeCycleItemBean) {
        sQLiteStatement.clearBindings();
        Long id = lifeCycleItemBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, lifeCycleItemBean.getUserId());
        String month = lifeCycleItemBean.getMonth();
        if (month != null) {
            sQLiteStatement.bindString(3, month);
        }
        sQLiteStatement.bindLong(4, lifeCycleItemBean.getMensesCycle());
        sQLiteStatement.bindLong(5, lifeCycleItemBean.getMensesDays());
        String sourceMac = lifeCycleItemBean.getSourceMac();
        if (sourceMac != null) {
            sQLiteStatement.bindString(6, sourceMac);
        }
        String deviceName = lifeCycleItemBean.getDeviceName();
        if (deviceName != null) {
            sQLiteStatement.bindString(7, deviceName);
        }
        List<List<Integer>> itemList = lifeCycleItemBean.getItemList();
        if (itemList != null) {
            sQLiteStatement.bindString(8, this.ItemListConverter.convertToDatabaseValue(itemList));
        }
        sQLiteStatement.bindLong(9, lifeCycleItemBean.getTimeStamp());
        sQLiteStatement.bindLong(10, lifeCycleItemBean.getMensesStartDay());
        sQLiteStatement.bindLong(11, lifeCycleItemBean.getOvulationDay());
        sQLiteStatement.bindLong(12, lifeCycleItemBean.getPregnancyDayBeforeRemind());
        String reminderTime = lifeCycleItemBean.getReminderTime();
        if (reminderTime != null) {
            sQLiteStatement.bindString(13, reminderTime);
        }
        sQLiteStatement.bindLong(14, lifeCycleItemBean.getUpload() ? 1L : 0L);
        sQLiteStatement.bindLong(15, lifeCycleItemBean.getNeedSyncToDevice() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(LifeCycleItemBean lifeCycleItemBean) {
        super.attachEntity(lifeCycleItemBean);
        lifeCycleItemBean.__setDaoSession(this.daoSession);
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
    public LifeCycleItemBean readEntity(Cursor cursor, int i) {
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
        List<List<Integer>> listConvertToEntityProperty2 = cursor.isNull(i8) ? null : this.ItemListConverter.convertToEntityProperty(cursor.getString(i8));
        int i9 = i + 12;
        return new LifeCycleItemBean(lValueOf, j, string, i4, i5, string2, string3, listConvertToEntityProperty2, cursor.getLong(i + 8), cursor.getInt(i + 9), cursor.getInt(i + 10), cursor.getInt(i + 11), cursor.isNull(i9) ? null : cursor.getString(i9), cursor.getShort(i + 13) != 0, cursor.getShort(i + 14) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, LifeCycleItemBean lifeCycleItemBean, int i) {
        int i2 = i + 0;
        lifeCycleItemBean.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        lifeCycleItemBean.setUserId(cursor.getLong(i + 1));
        int i3 = i + 2;
        lifeCycleItemBean.setMonth(cursor.isNull(i3) ? null : cursor.getString(i3));
        lifeCycleItemBean.setMensesCycle(cursor.getInt(i + 3));
        lifeCycleItemBean.setMensesDays(cursor.getInt(i + 4));
        int i4 = i + 5;
        lifeCycleItemBean.setSourceMac(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 6;
        lifeCycleItemBean.setDeviceName(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 7;
        lifeCycleItemBean.setItemList(cursor.isNull(i6) ? null : this.ItemListConverter.convertToEntityProperty(cursor.getString(i6)));
        lifeCycleItemBean.setTimeStamp(cursor.getLong(i + 8));
        lifeCycleItemBean.setMensesStartDay(cursor.getInt(i + 9));
        lifeCycleItemBean.setOvulationDay(cursor.getInt(i + 10));
        lifeCycleItemBean.setPregnancyDayBeforeRemind(cursor.getInt(i + 11));
        int i7 = i + 12;
        lifeCycleItemBean.setReminderTime(cursor.isNull(i7) ? null : cursor.getString(i7));
        lifeCycleItemBean.setUpload(cursor.getShort(i + 13) != 0);
        lifeCycleItemBean.setNeedSyncToDevice(cursor.getShort(i + 14) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(LifeCycleItemBean lifeCycleItemBean, long j) {
        lifeCycleItemBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(LifeCycleItemBean lifeCycleItemBean) {
        if (lifeCycleItemBean != null) {
            return lifeCycleItemBean.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(LifeCycleItemBean lifeCycleItemBean) {
        return lifeCycleItemBean.getId() != null;
    }
}