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
public class HomeCardDao extends AbstractDao<HomeCard, Long> {
    public static final String TABLENAME = "HOME_CARD";
    private final ListConvert HideValueListConverter;
    private final ListConvert ValueListConverter;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.class, "UserId", false, "USER_ID");
        public static final Property ValueList = new Property(2, String.class, "ValueList", false, "VALUE_LIST");
        public static final Property HideValueList = new Property(3, String.class, "HideValueList", false, "HIDE_VALUE_LIST");
        public static final Property UploadSuccess = new Property(4, Boolean.TYPE, "UploadSuccess", false, "UPLOAD_SUCCESS");
        public static final Property CreateTime = new Property(5, Long.TYPE, "CreateTime", false, "CREATE_TIME");
        public static final Property UpdateTime = new Property(6, Long.TYPE, "UpdateTime", false, "UPDATE_TIME");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public HomeCardDao(DaoConfig daoConfig) {
        super(daoConfig);
        this.ValueListConverter = new ListConvert();
        this.HideValueListConverter = new ListConvert();
    }

    public HomeCardDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.ValueListConverter = new ListConvert();
        this.HideValueListConverter = new ListConvert();
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"HOME_CARD\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER,\"VALUE_LIST\" TEXT,\"HIDE_VALUE_LIST\" TEXT,\"UPLOAD_SUCCESS\" INTEGER NOT NULL ,\"CREATE_TIME\" INTEGER NOT NULL ,\"UPDATE_TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HOME_CARD\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, HomeCard homeCard) {
        databaseStatement.clearBindings();
        Long id = homeCard.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        Long userId = homeCard.getUserId();
        if (userId != null) {
            databaseStatement.bindLong(2, userId.longValue());
        }
        List<Integer> valueList = homeCard.getValueList();
        if (valueList != null) {
            databaseStatement.bindString(3, this.ValueListConverter.convertToDatabaseValue(valueList));
        }
        List<Integer> hideValueList = homeCard.getHideValueList();
        if (hideValueList != null) {
            databaseStatement.bindString(4, this.HideValueListConverter.convertToDatabaseValue(hideValueList));
        }
        databaseStatement.bindLong(5, homeCard.getUploadSuccess() ? 1L : 0L);
        databaseStatement.bindLong(6, homeCard.getCreateTime());
        databaseStatement.bindLong(7, homeCard.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, HomeCard homeCard) {
        sQLiteStatement.clearBindings();
        Long id = homeCard.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        Long userId = homeCard.getUserId();
        if (userId != null) {
            sQLiteStatement.bindLong(2, userId.longValue());
        }
        List<Integer> valueList = homeCard.getValueList();
        if (valueList != null) {
            sQLiteStatement.bindString(3, this.ValueListConverter.convertToDatabaseValue(valueList));
        }
        List<Integer> hideValueList = homeCard.getHideValueList();
        if (hideValueList != null) {
            sQLiteStatement.bindString(4, this.HideValueListConverter.convertToDatabaseValue(hideValueList));
        }
        sQLiteStatement.bindLong(5, homeCard.getUploadSuccess() ? 1L : 0L);
        sQLiteStatement.bindLong(6, homeCard.getCreateTime());
        sQLiteStatement.bindLong(7, homeCard.getUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(HomeCard homeCard) {
        super.attachEntity(homeCard);
        homeCard.__setDaoSession(this.daoSession);
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
    public HomeCard readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        Long lValueOf2 = cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3));
        int i4 = i + 2;
        List<Integer> listConvertToEntityProperty = cursor.isNull(i4) ? null : this.ValueListConverter.convertToEntityProperty(cursor.getString(i4));
        int i5 = i + 3;
        return new HomeCard(lValueOf, lValueOf2, listConvertToEntityProperty, cursor.isNull(i5) ? null : this.HideValueListConverter.convertToEntityProperty(cursor.getString(i5)), cursor.getShort(i + 4) != 0, cursor.getLong(i + 5), cursor.getLong(i + 6));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, HomeCard homeCard, int i) {
        int i2 = i + 0;
        homeCard.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        homeCard.setUserId(cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3)));
        int i4 = i + 2;
        homeCard.setValueList(cursor.isNull(i4) ? null : this.ValueListConverter.convertToEntityProperty(cursor.getString(i4)));
        int i5 = i + 3;
        homeCard.setHideValueList(cursor.isNull(i5) ? null : this.HideValueListConverter.convertToEntityProperty(cursor.getString(i5)));
        homeCard.setUploadSuccess(cursor.getShort(i + 4) != 0);
        homeCard.setCreateTime(cursor.getLong(i + 5));
        homeCard.setUpdateTime(cursor.getLong(i + 6));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(HomeCard homeCard, long j) {
        homeCard.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(HomeCard homeCard) {
        if (homeCard != null) {
            return homeCard.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(HomeCard homeCard) {
        return homeCard.getId() != null;
    }
}