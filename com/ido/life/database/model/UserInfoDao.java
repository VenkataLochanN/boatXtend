package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class UserInfoDao extends AbstractDao<UserInfo, Long> {
    public static final String TABLENAME = "USER_INFO";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property DisplayName = new Property(2, String.class, "DisplayName", false, "DISPLAY_NAME");
        public static final Property AvatarUrl = new Property(3, String.class, "AvatarUrl", false, "AVATAR_URL");
        public static final Property ServerImageUrl = new Property(4, Boolean.TYPE, "ServerImageUrl", false, "SERVER_IMAGE_URL");
        public static final Property Email = new Property(5, String.class, "Email", false, "EMAIL");
        public static final Property Gender = new Property(6, Integer.TYPE, "Gender", false, "GENDER");
        public static final Property Birthday = new Property(7, String.class, "Birthday", false, "BIRTHDAY");
        public static final Property HeightUnit = new Property(8, Integer.TYPE, "HeightUnit", false, "HEIGHT_UNIT");
        public static final Property Height = new Property(9, Float.TYPE, "Height", false, "HEIGHT");
        public static final Property WeightUnit = new Property(10, Integer.TYPE, "WeightUnit", false, "WEIGHT_UNIT");
        public static final Property Weight = new Property(11, Float.TYPE, "Weight", false, "WEIGHT");
        public static final Property Country = new Property(12, String.class, "Country", false, "COUNTRY");
        public static final Property City = new Property(13, String.class, "City", false, "CITY");
        public static final Property AreaCode = new Property(14, String.class, "AreaCode", false, "AREA_CODE");
        public static final Property UploadSuccess = new Property(15, Boolean.TYPE, "UploadSuccess", false, "UPLOAD_SUCCESS");
        public static final Property CreateTime = new Property(16, Long.TYPE, "CreateTime", false, "CREATE_TIME");
        public static final Property UpdateTime = new Property(17, Long.TYPE, "UpdateTime", false, "UPDATE_TIME");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public UserInfoDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public UserInfoDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"USER_INFO\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"DISPLAY_NAME\" TEXT,\"AVATAR_URL\" TEXT,\"SERVER_IMAGE_URL\" INTEGER NOT NULL ,\"EMAIL\" TEXT,\"GENDER\" INTEGER NOT NULL ,\"BIRTHDAY\" TEXT,\"HEIGHT_UNIT\" INTEGER NOT NULL ,\"HEIGHT\" REAL NOT NULL ,\"WEIGHT_UNIT\" INTEGER NOT NULL ,\"WEIGHT\" REAL NOT NULL ,\"COUNTRY\" TEXT,\"CITY\" TEXT,\"AREA_CODE\" TEXT,\"UPLOAD_SUCCESS\" INTEGER NOT NULL ,\"CREATE_TIME\" INTEGER NOT NULL ,\"UPDATE_TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"USER_INFO\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, UserInfo userInfo) {
        databaseStatement.clearBindings();
        Long id = userInfo.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, userInfo.getUserId());
        String displayName = userInfo.getDisplayName();
        if (displayName != null) {
            databaseStatement.bindString(3, displayName);
        }
        String avatarUrl = userInfo.getAvatarUrl();
        if (avatarUrl != null) {
            databaseStatement.bindString(4, avatarUrl);
        }
        databaseStatement.bindLong(5, userInfo.getServerImageUrl() ? 1L : 0L);
        String email = userInfo.getEmail();
        if (email != null) {
            databaseStatement.bindString(6, email);
        }
        databaseStatement.bindLong(7, userInfo.getGender());
        String birthday = userInfo.getBirthday();
        if (birthday != null) {
            databaseStatement.bindString(8, birthday);
        }
        databaseStatement.bindLong(9, userInfo.getHeightUnit());
        databaseStatement.bindDouble(10, userInfo.getHeight());
        databaseStatement.bindLong(11, userInfo.getWeightUnit());
        databaseStatement.bindDouble(12, userInfo.getWeight());
        String country = userInfo.getCountry();
        if (country != null) {
            databaseStatement.bindString(13, country);
        }
        String city = userInfo.getCity();
        if (city != null) {
            databaseStatement.bindString(14, city);
        }
        String areaCode = userInfo.getAreaCode();
        if (areaCode != null) {
            databaseStatement.bindString(15, areaCode);
        }
        databaseStatement.bindLong(16, userInfo.getUploadSuccess() ? 1L : 0L);
        databaseStatement.bindLong(17, userInfo.getCreateTime());
        databaseStatement.bindLong(18, userInfo.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, UserInfo userInfo) {
        sQLiteStatement.clearBindings();
        Long id = userInfo.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, userInfo.getUserId());
        String displayName = userInfo.getDisplayName();
        if (displayName != null) {
            sQLiteStatement.bindString(3, displayName);
        }
        String avatarUrl = userInfo.getAvatarUrl();
        if (avatarUrl != null) {
            sQLiteStatement.bindString(4, avatarUrl);
        }
        sQLiteStatement.bindLong(5, userInfo.getServerImageUrl() ? 1L : 0L);
        String email = userInfo.getEmail();
        if (email != null) {
            sQLiteStatement.bindString(6, email);
        }
        sQLiteStatement.bindLong(7, userInfo.getGender());
        String birthday = userInfo.getBirthday();
        if (birthday != null) {
            sQLiteStatement.bindString(8, birthday);
        }
        sQLiteStatement.bindLong(9, userInfo.getHeightUnit());
        sQLiteStatement.bindDouble(10, userInfo.getHeight());
        sQLiteStatement.bindLong(11, userInfo.getWeightUnit());
        sQLiteStatement.bindDouble(12, userInfo.getWeight());
        String country = userInfo.getCountry();
        if (country != null) {
            sQLiteStatement.bindString(13, country);
        }
        String city = userInfo.getCity();
        if (city != null) {
            sQLiteStatement.bindString(14, city);
        }
        String areaCode = userInfo.getAreaCode();
        if (areaCode != null) {
            sQLiteStatement.bindString(15, areaCode);
        }
        sQLiteStatement.bindLong(16, userInfo.getUploadSuccess() ? 1L : 0L);
        sQLiteStatement.bindLong(17, userInfo.getCreateTime());
        sQLiteStatement.bindLong(18, userInfo.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(UserInfo userInfo) {
        super.attachEntity(userInfo);
        userInfo.__setDaoSession(this.daoSession);
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
    public UserInfo readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        long j = cursor.getLong(i + 1);
        int i3 = i + 2;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        boolean z = cursor.getShort(i + 4) != 0;
        int i5 = i + 5;
        String string3 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = cursor.getInt(i + 6);
        int i7 = i + 7;
        String string4 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = cursor.getInt(i + 8);
        float f2 = cursor.getFloat(i + 9);
        int i9 = cursor.getInt(i + 10);
        float f3 = cursor.getFloat(i + 11);
        int i10 = i + 12;
        String string5 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 13;
        String string6 = cursor.isNull(i11) ? null : cursor.getString(i11);
        int i12 = i + 14;
        return new UserInfo(lValueOf, j, string, string2, z, string3, i6, string4, i8, f2, i9, f3, string5, string6, cursor.isNull(i12) ? null : cursor.getString(i12), cursor.getShort(i + 15) != 0, cursor.getLong(i + 16), cursor.getLong(i + 17));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, UserInfo userInfo, int i) {
        int i2 = i + 0;
        userInfo.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        userInfo.setUserId(cursor.getLong(i + 1));
        int i3 = i + 2;
        userInfo.setDisplayName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        userInfo.setAvatarUrl(cursor.isNull(i4) ? null : cursor.getString(i4));
        userInfo.setServerImageUrl(cursor.getShort(i + 4) != 0);
        int i5 = i + 5;
        userInfo.setEmail(cursor.isNull(i5) ? null : cursor.getString(i5));
        userInfo.setGender(cursor.getInt(i + 6));
        int i6 = i + 7;
        userInfo.setBirthday(cursor.isNull(i6) ? null : cursor.getString(i6));
        userInfo.setHeightUnit(cursor.getInt(i + 8));
        userInfo.setHeight(cursor.getFloat(i + 9));
        userInfo.setWeightUnit(cursor.getInt(i + 10));
        userInfo.setWeight(cursor.getFloat(i + 11));
        int i7 = i + 12;
        userInfo.setCountry(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 13;
        userInfo.setCity(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 14;
        userInfo.setAreaCode(cursor.isNull(i9) ? null : cursor.getString(i9));
        userInfo.setUploadSuccess(cursor.getShort(i + 15) != 0);
        userInfo.setCreateTime(cursor.getLong(i + 16));
        userInfo.setUpdateTime(cursor.getLong(i + 17));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(UserInfo userInfo, long j) {
        userInfo.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(UserInfo userInfo) {
        if (userInfo != null) {
            return userInfo.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(UserInfo userInfo) {
        return userInfo.getId() != null;
    }
}