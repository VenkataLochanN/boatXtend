package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class UserMedalInfoDao extends AbstractDao<UserMedalInfo, Long> {
    public static final String TABLENAME = "UserMedal";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property MedalId = new Property(2, Integer.TYPE, "MedalId", false, "MEDAL_ID");
        public static final Property CreateTime = new Property(3, String.class, "CreateTime", false, "CREATE_TIME");
        public static final Property Date = new Property(4, String.class, "Date", false, "DATE");
        public static final Property ShowToUser = new Property(5, Boolean.TYPE, "ShowToUser", false, "SHOW_TO_USER");
        public static final Property UploadSuccess = new Property(6, Boolean.TYPE, "UploadSuccess", false, "UPLOAD_SUCCESS");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public UserMedalInfoDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public UserMedalInfoDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"UserMedal\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"MEDAL_ID\" INTEGER NOT NULL ,\"CREATE_TIME\" TEXT,\"DATE\" TEXT,\"SHOW_TO_USER\" INTEGER NOT NULL ,\"UPLOAD_SUCCESS\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"UserMedal\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, UserMedalInfo userMedalInfo) {
        databaseStatement.clearBindings();
        Long id = userMedalInfo.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, userMedalInfo.getUserId());
        databaseStatement.bindLong(3, userMedalInfo.getMedalId());
        String createTime = userMedalInfo.getCreateTime();
        if (createTime != null) {
            databaseStatement.bindString(4, createTime);
        }
        String date = userMedalInfo.getDate();
        if (date != null) {
            databaseStatement.bindString(5, date);
        }
        databaseStatement.bindLong(6, userMedalInfo.getShowToUser() ? 1L : 0L);
        databaseStatement.bindLong(7, userMedalInfo.getUploadSuccess() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, UserMedalInfo userMedalInfo) {
        sQLiteStatement.clearBindings();
        Long id = userMedalInfo.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, userMedalInfo.getUserId());
        sQLiteStatement.bindLong(3, userMedalInfo.getMedalId());
        String createTime = userMedalInfo.getCreateTime();
        if (createTime != null) {
            sQLiteStatement.bindString(4, createTime);
        }
        String date = userMedalInfo.getDate();
        if (date != null) {
            sQLiteStatement.bindString(5, date);
        }
        sQLiteStatement.bindLong(6, userMedalInfo.getShowToUser() ? 1L : 0L);
        sQLiteStatement.bindLong(7, userMedalInfo.getUploadSuccess() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(UserMedalInfo userMedalInfo) {
        super.attachEntity(userMedalInfo);
        userMedalInfo.__setDaoSession(this.daoSession);
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
    public UserMedalInfo readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = cursor.getInt(i + 2);
        int i4 = i + 3;
        String string = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 4;
        return new UserMedalInfo(lValueOf, j, i3, string, cursor.isNull(i5) ? null : cursor.getString(i5), cursor.getShort(i + 5) != 0, cursor.getShort(i + 6) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, UserMedalInfo userMedalInfo, int i) {
        int i2 = i + 0;
        userMedalInfo.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        userMedalInfo.setUserId(cursor.getLong(i + 1));
        userMedalInfo.setMedalId(cursor.getInt(i + 2));
        int i3 = i + 3;
        userMedalInfo.setCreateTime(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 4;
        userMedalInfo.setDate(cursor.isNull(i4) ? null : cursor.getString(i4));
        userMedalInfo.setShowToUser(cursor.getShort(i + 5) != 0);
        userMedalInfo.setUploadSuccess(cursor.getShort(i + 6) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(UserMedalInfo userMedalInfo, long j) {
        userMedalInfo.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(UserMedalInfo userMedalInfo) {
        if (userMedalInfo != null) {
            return userMedalInfo.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(UserMedalInfo userMedalInfo) {
        return userMedalInfo.getId() != null;
    }
}