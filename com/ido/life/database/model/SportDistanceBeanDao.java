package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class SportDistanceBeanDao extends AbstractDao<SportDistanceBean, Long> {
    public static final String TABLENAME = "SportDistance";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property Date = new Property(2, String.class, "Date", false, "DATE");
        public static final Property TotalDistance = new Property(3, Float.TYPE, "TotalDistance", false, "TOTAL_DISTANCE");
        public static final Property OrignalItems = new Property(4, String.class, "OrignalItems", false, "ORIGNAL_ITEMS");
        public static final Property Items = new Property(5, String.class, "Items", false, "ITEMS");
        public static final Property SourceMac = new Property(6, String.class, "SourceMac", false, "SOURCE_MAC");
        public static final Property DeviceName = new Property(7, String.class, "DeviceName", false, "DEVICE_NAME");
        public static final Property TimeStamp = new Property(8, Long.TYPE, "TimeStamp", false, "TIME_STAMP");
        public static final Property LoadDetail = new Property(9, Boolean.TYPE, "LoadDetail", false, "LOAD_DETAIL");
        public static final Property UploadSuccess = new Property(10, Boolean.TYPE, "UploadSuccess", false, "UPLOAD_SUCCESS");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public SportDistanceBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public SportDistanceBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"SportDistance\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"DATE\" TEXT,\"TOTAL_DISTANCE\" REAL NOT NULL ,\"ORIGNAL_ITEMS\" TEXT,\"ITEMS\" TEXT,\"SOURCE_MAC\" TEXT,\"DEVICE_NAME\" TEXT,\"TIME_STAMP\" INTEGER NOT NULL ,\"LOAD_DETAIL\" INTEGER NOT NULL ,\"UPLOAD_SUCCESS\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"SportDistance\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, SportDistanceBean sportDistanceBean) {
        databaseStatement.clearBindings();
        Long id = sportDistanceBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, sportDistanceBean.getUserId());
        String date = sportDistanceBean.getDate();
        if (date != null) {
            databaseStatement.bindString(3, date);
        }
        databaseStatement.bindDouble(4, sportDistanceBean.getTotalDistance());
        String orignalItems = sportDistanceBean.getOrignalItems();
        if (orignalItems != null) {
            databaseStatement.bindString(5, orignalItems);
        }
        String items = sportDistanceBean.getItems();
        if (items != null) {
            databaseStatement.bindString(6, items);
        }
        String sourceMac = sportDistanceBean.getSourceMac();
        if (sourceMac != null) {
            databaseStatement.bindString(7, sourceMac);
        }
        String deviceName = sportDistanceBean.getDeviceName();
        if (deviceName != null) {
            databaseStatement.bindString(8, deviceName);
        }
        databaseStatement.bindLong(9, sportDistanceBean.getTimeStamp());
        databaseStatement.bindLong(10, sportDistanceBean.getLoadDetail() ? 1L : 0L);
        databaseStatement.bindLong(11, sportDistanceBean.getUploadSuccess() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, SportDistanceBean sportDistanceBean) {
        sQLiteStatement.clearBindings();
        Long id = sportDistanceBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, sportDistanceBean.getUserId());
        String date = sportDistanceBean.getDate();
        if (date != null) {
            sQLiteStatement.bindString(3, date);
        }
        sQLiteStatement.bindDouble(4, sportDistanceBean.getTotalDistance());
        String orignalItems = sportDistanceBean.getOrignalItems();
        if (orignalItems != null) {
            sQLiteStatement.bindString(5, orignalItems);
        }
        String items = sportDistanceBean.getItems();
        if (items != null) {
            sQLiteStatement.bindString(6, items);
        }
        String sourceMac = sportDistanceBean.getSourceMac();
        if (sourceMac != null) {
            sQLiteStatement.bindString(7, sourceMac);
        }
        String deviceName = sportDistanceBean.getDeviceName();
        if (deviceName != null) {
            sQLiteStatement.bindString(8, deviceName);
        }
        sQLiteStatement.bindLong(9, sportDistanceBean.getTimeStamp());
        sQLiteStatement.bindLong(10, sportDistanceBean.getLoadDetail() ? 1L : 0L);
        sQLiteStatement.bindLong(11, sportDistanceBean.getUploadSuccess() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(SportDistanceBean sportDistanceBean) {
        super.attachEntity(sportDistanceBean);
        sportDistanceBean.__setDaoSession(this.daoSession);
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
    public SportDistanceBean readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = i + 2;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        float f2 = cursor.getFloat(i + 3);
        int i4 = i + 4;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 5;
        String string3 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 6;
        String string4 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 7;
        return new SportDistanceBean(lValueOf, j, string, f2, string2, string3, string4, cursor.isNull(i7) ? null : cursor.getString(i7), cursor.getLong(i + 8), cursor.getShort(i + 9) != 0, cursor.getShort(i + 10) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, SportDistanceBean sportDistanceBean, int i) {
        int i2 = i + 0;
        sportDistanceBean.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        sportDistanceBean.setUserId(cursor.getLong(i + 1));
        int i3 = i + 2;
        sportDistanceBean.setDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        sportDistanceBean.setTotalDistance(cursor.getFloat(i + 3));
        int i4 = i + 4;
        sportDistanceBean.setOrignalItems(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 5;
        sportDistanceBean.setItems(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 6;
        sportDistanceBean.setSourceMac(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 7;
        sportDistanceBean.setDeviceName(cursor.isNull(i7) ? null : cursor.getString(i7));
        sportDistanceBean.setTimeStamp(cursor.getLong(i + 8));
        sportDistanceBean.setLoadDetail(cursor.getShort(i + 9) != 0);
        sportDistanceBean.setUploadSuccess(cursor.getShort(i + 10) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(SportDistanceBean sportDistanceBean, long j) {
        sportDistanceBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(SportDistanceBean sportDistanceBean) {
        if (sportDistanceBean != null) {
            return sportDistanceBean.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(SportDistanceBean sportDistanceBean) {
        return sportDistanceBean.getId() != null;
    }
}