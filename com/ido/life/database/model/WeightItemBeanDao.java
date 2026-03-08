package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class WeightItemBeanDao extends AbstractDao<WeightItemBean, Long> {
    public static final String TABLENAME = "UserWeight";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "Id");
        public static final Property Date = new Property(1, String.class, "Date", false, "Date");
        public static final Property TotalWeight = new Property(2, Float.TYPE, "TotalWeight", false, "TotalWeight");
        public static final Property Bmi = new Property(3, Float.TYPE, "Bmi", false, "Bmi");
        public static final Property TimeStamp = new Property(4, Long.TYPE, "TimeStamp", false, "TIME_STAMP");
        public static final Property LoadDetail = new Property(5, Boolean.TYPE, "loadDetail", false, "LOAD_DETAIL");
        public static final Property UserId = new Property(6, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property UploadSuccess = new Property(7, Boolean.TYPE, "UploadSuccess", false, "UPLOAD_SUCCESS");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public WeightItemBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public WeightItemBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"UserWeight\" (\"Id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"Date\" TEXT,\"TotalWeight\" REAL NOT NULL ,\"Bmi\" REAL NOT NULL ,\"TIME_STAMP\" INTEGER NOT NULL ,\"LOAD_DETAIL\" INTEGER NOT NULL ,\"USER_ID\" INTEGER NOT NULL ,\"UPLOAD_SUCCESS\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"UserWeight\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, WeightItemBean weightItemBean) {
        databaseStatement.clearBindings();
        Long id = weightItemBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String date = weightItemBean.getDate();
        if (date != null) {
            databaseStatement.bindString(2, date);
        }
        databaseStatement.bindDouble(3, weightItemBean.getTotalWeight());
        databaseStatement.bindDouble(4, weightItemBean.getBmi());
        databaseStatement.bindLong(5, weightItemBean.getTimeStamp());
        databaseStatement.bindLong(6, weightItemBean.getLoadDetail() ? 1L : 0L);
        databaseStatement.bindLong(7, weightItemBean.getUserId());
        databaseStatement.bindLong(8, weightItemBean.getUploadSuccess() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, WeightItemBean weightItemBean) {
        sQLiteStatement.clearBindings();
        Long id = weightItemBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String date = weightItemBean.getDate();
        if (date != null) {
            sQLiteStatement.bindString(2, date);
        }
        sQLiteStatement.bindDouble(3, weightItemBean.getTotalWeight());
        sQLiteStatement.bindDouble(4, weightItemBean.getBmi());
        sQLiteStatement.bindLong(5, weightItemBean.getTimeStamp());
        sQLiteStatement.bindLong(6, weightItemBean.getLoadDetail() ? 1L : 0L);
        sQLiteStatement.bindLong(7, weightItemBean.getUserId());
        sQLiteStatement.bindLong(8, weightItemBean.getUploadSuccess() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(WeightItemBean weightItemBean) {
        super.attachEntity(weightItemBean);
        weightItemBean.__setDaoSession(this.daoSession);
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
    public WeightItemBean readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        int i3 = i + 1;
        return new WeightItemBean(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.getFloat(i + 2), cursor.getFloat(i + 3), cursor.getLong(i + 4), cursor.getShort(i + 5) != 0, cursor.getLong(i + 6), cursor.getShort(i + 7) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, WeightItemBean weightItemBean, int i) {
        int i2 = i + 0;
        weightItemBean.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        weightItemBean.setDate(cursor.isNull(i3) ? null : cursor.getString(i3));
        weightItemBean.setTotalWeight(cursor.getFloat(i + 2));
        weightItemBean.setBmi(cursor.getFloat(i + 3));
        weightItemBean.setTimeStamp(cursor.getLong(i + 4));
        weightItemBean.setLoadDetail(cursor.getShort(i + 5) != 0);
        weightItemBean.setUserId(cursor.getLong(i + 6));
        weightItemBean.setUploadSuccess(cursor.getShort(i + 7) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(WeightItemBean weightItemBean, long j) {
        weightItemBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(WeightItemBean weightItemBean) {
        if (weightItemBean != null) {
            return weightItemBean.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(WeightItemBean weightItemBean) {
        return weightItemBean.getId() != null;
    }
}